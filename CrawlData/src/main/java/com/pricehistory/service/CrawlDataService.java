package com.pricehistory.service;

import com.pricehistory.configuration.DatabaseConfig;
import com.pricehistory.dao.FileConfigDAO;
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


public class CrawlDataService {
    String sourceName = PropUtil.getProp("source.name.dienmayxanh");

    public FileConfig loadFileConfig(String sourceName) {
        return DatabaseConfig.get().withHandle(handle ->
                handle.attach(FileConfigDAO.class)
                        .findFileConfig(sourceName)
                        .orElseThrow(() -> new RuntimeException("File config not found")));
    }

    public void extractsourceDienmayxanh() throws IOException {
        FileConfig fileConfig = loadFileConfig(sourceName);

        String sourceUrl = fileConfig.getSourceUrl();
        String filePath = fileConfig.getFilePath();
        String dateFormat = fileConfig.getDateFormat();

        // Kết nối và tải trang web
        Document doc = Jsoup.connect(sourceUrl).get();
        // Tạo FileWriter để ghi dữ liệu vào file CSV
        FileWriter csvWriter = new FileWriter(filePath, StandardCharsets.UTF_8);
        csvWriter.append("Name,Product_Code,Image_url,Brand,Price,Price_Sale,Discount,Created Date\n");

        // Lấy danh sách sản phẩm từ trang
        Elements products = doc.select("li.item");
        System.out.println("Số lượng sản phẩm: " + products.size());

        for (Element product : products) {
            String name = product.select("a[data-name]").attr("data-name").replace(",", " "); // Lấy tên sản phẩm
            String image_url = product.select("img.thumb").attr("data-src"); // Lấy đường dẫn hình ảnh
            String brand_name = product.select("a[data-brand]").attr("data-brand").replace(",", " "); // Lấy thương hiệu sản phẩm
            String price = product.select(".price-old").text().replace(",", " "); // Lấy giá cũ
            String price_sale = product.select("strong.price").text().replace(",", " "); // Lấy giá hiện tại
            String discount = product.select(".percent").text().replace("-", "").replace("%", "").trim(); // Lấy thông tin giảm giá và chuyển về dạng số
            String product_code = name.split(" ")[name.split(" ").length - 1]; // Tách mã sản phẩm
            String createdDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

            // Chuyển đổi discount sang số thập phân
            double discountValue = discount.isEmpty() ? 0.0 : Double.parseDouble(discount) / 100.0;

            // Ghi dữ liệu vào file CSV
            csvWriter.append(String.join(",", name, product_code, image_url, brand_name, price, price_sale, String.valueOf(discountValue), createdDate));
            csvWriter.append("\n");
        }

        // Đóng file sau khi ghi xong
        csvWriter.flush();
        csvWriter.close();

        System.out.println("Dữ liệu đã được ghi vào file refrigerators.csv");
    }
}
