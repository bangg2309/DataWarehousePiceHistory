package pricehistory.configuration;

import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import pricehistory.util.PropUtil;

import java.sql.SQLException;

@Getter
@Setter
@NoArgsConstructor
public class DatabaseConfig {
    private static Jdbi jdbi;

    private static String host = PropUtil.getProp("db.host");
    private static String port = PropUtil.getProp("db.port");
    private static String username = PropUtil.getProp("db.username");
    private static String password = PropUtil.getProp("db.password");
    private static String databaseName = PropUtil.getProp("db.databaseName");


    private static void makeConnect() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://" + host + ":" + port + "/"
                + databaseName);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        try {
            dataSource.setUseCompression(true);
            dataSource.setAutoReconnect(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
        jdbi = Jdbi.create(dataSource);
        jdbi.installPlugin(new SqlObjectPlugin());
    }


    public static Jdbi get() {
        if (jdbi == null) makeConnect();
        return jdbi;
    }


}