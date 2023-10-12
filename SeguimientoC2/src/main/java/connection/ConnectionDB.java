package connection;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public class ConexionBaseDatos {
        private static String url = "jdbc:mysql://localhost:3306/notas";
        private static String username = "root";
        private static String password = "admin";
        private static BasicDataSource pool;
        public static BasicDataSource getInstance() throws SQLException {
            if (pool == null) {
            pool = new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(username);
            pool.setPassword(password);
            pool.setInitialSize(3);
            pool.setMinIdle(3);
            pool.setMaxIdle(8);
            pool.setMaxTotal(8);//numero maximo de conexiones incluyendo actias e inactivas
        }
            return pool;
        }
        public static Connection getConnection() throws SQLException {
            return getInstance().getConnection();
        }
    }

    /*private static String url = "jdbc:mysql://localhost:3306/seguimientoc2";
    private static String username = "root";
    private static String password = "";

    public static Connection getInstance() throws SQLException, ClassNotFoundException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Coudn't connect to database!");
            throw new RuntimeException(ex);
        }
    }*/
}
