package com.pricehistory.service;

import com.pricehistory.configuration.DatabaseConfig;
import com.pricehistory.dao.FileConfigDAO;
import com.pricehistory.dao.FileLogDAO;
import com.pricehistory.model.FileConfig;
import com.pricehistory.util.PropUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class CrawlDataService {
    String sourceName = PropUtil.getProp("source.name.dienmayxanh");

    private FileConfig loadFileConfig(String sourceName) {
        return DatabaseConfig.get().withHandle(handle ->
                handle.attach(FileConfigDAO.class)
                        .findFileConfig(sourceName)
                        .orElseThrow(() -> new RuntimeException("Khong tim thay file config")));
    }

    private boolean isRecordTodayExistAndNotByStatus(String status) {
        int count = DatabaseConfig.get().withHandle(handle ->
                handle.attach(FileLogDAO.class)
                        .findFileLogsTodayNotByStatus(status)
                        .orElse(0));
        return count > 0;
    }

    public void extractsourceDienmayxanh() throws IOException {

        if (isRecordTodayExistAndNotByStatus("FL")) {
            System.out.println("Hom nay da lay du lieu ve");
            return;
        }

        FileConfig fileConfig = loadFileConfig(sourceName);

        String sourceUrl = fileConfig.getSourceUrl();
        String filePath = fileConfig.getFilePath();

        // Lấy ngày hiện tại và định dạng theo yyyy-MM-dd
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Thay thế {date} bằng ngày hiện tại
        filePath = filePath.replace("{date}", currentDate);


        // Tạo FileWriter để ghi dữ liệu vào file CSV
        FileWriter csvWriter = new FileWriter(filePath, StandardCharsets.UTF_8);
        csvWriter.append("Name,Product_Code,Image_url,Brand,Price,Price_Sale,Discount,Created Date\n");

        int pageIndex = 0;
        int totalProducts = 0;

        while (true) {
            try {
                String urlWithPage = sourceUrl + "#c=1943   &o=13&pi=" + pageIndex;
                Document doc = Jsoup.connect(urlWithPage).get();
                Elements products = doc.select("li.item");
                if (products.isEmpty()) {
                    System.out.println("Không còn sản phẩm nào trên trang " + pageIndex);
                    break;
                }

                totalProducts += products.size();

                for (Element product : products) {
                    String name = product.select("a[data-name]").attr("data-name").replace(",", " ");
                    String image_url = product.select("img.thumb").attr("data-src");
                    String brand_name = product.select("a[data-brand]").attr("data-brand").replace(",", " ");
                    String price = product.select(".price-old").text().replace(",", " ");
                    String price_sale = product.select("strong.price").text().replace(",", " ");
                    String discount = product.select(".percent").text().replace("-", "").replace("%", "").trim();
                    String product_code = name.split(" ")[name.split(" ").length - 1];
                    String createdDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                    // Chuyển đổi discount sang số thập phân
                    double discountValue = discount.isEmpty() ? 0.0 : Double.parseDouble(discount) / 100.0;

                    // Ghi dữ liệu vào file CSV
                    csvWriter.append(String.join(",", name, product_code, image_url, brand_name, price, price_sale, String.valueOf(discountValue), createdDate));
                    csvWriter.append("\n");
                }
                if (pageIndex == 9) {
                    break;
                }
                pageIndex++;

            } catch (IOException e) {
                System.err.println("Lỗi khi kết nối URL: " + e.getMessage());
                break; // Dừng vòng lặp nếu có lỗi kết nối
            }
        }

        // Đóng file sau khi ghi xong
        csvWriter.flush();
        csvWriter.close();


        if (totalProducts == 0) {
            System.err.println("Khong lay duoc du lieu");
            return;
        }

        // Ghi dữ liệu vào log
        String finalFilePath = filePath;
        int finalTotalProducts = totalProducts;
        DatabaseConfig.get().withHandle(handle -> {
            try {
                handle.attach(FileLogDAO.class)
                        .insertFileLog(fileConfig.getId(), finalFilePath, "ER", new Date(), finalTotalProducts);
                System.out.println("Lay du lieu thanh cong");
            } catch (Exception e) {
                System.err.println("Loi khong lay duoc du lieu: " + e.getMessage());
            }
            return null;
        });
    }
}
