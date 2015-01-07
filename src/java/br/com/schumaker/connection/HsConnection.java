package br.com.schumaker.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author hudson.sales
 */
public class HsConnection {

    public static Connection getConnection() {
        Connection c = null;
        try {
            Driver d = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
            String URL = "jdbc:mysql://127.0.0.1:3306/database";
            c = DriverManager.getConnection(URL, "root", "secret");
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, "Erro:\n" + ex, "Conexão MySQL", JOptionPane.ERROR_MESSAGE);
        }
        return c;
    }
}
