/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe de exemplo para configuração da conexão com MySQL.
 *
 * Configure os dados abaixo conforme o seu ambiente local.
 *
 * @author Nicolau Alfredo
 */
public class ConexaoExemplo {

    public static Connection getConnection() {

        Connection con;

        String driver = "com.mysql.cj.jdbc.Driver";

        String url
                = "jdbc:mysql://localhost:3306/nome_da_base";

        String user = "seu_usuario_mysql";

        String password = "sua_senha_mysql";

        try {

            Class.forName(driver);

            con = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException ex) {

            System.err.println("Erro na conexão com a base de dados: " + ex.getMessage());

            return null;
        }

        return con;
    }

    public static void closeConnection(Connection conn) {
        close(conn, null, null);
    }

    public static void closeConnection(Connection conn, PreparedStatement ps) {
        close(conn, ps, null);
    }

    public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) {
        close(conn, ps, rs);
    }

    private static void close(Connection conn, PreparedStatement ps, ResultSet rs) {

        try {

            if (rs != null) {
                rs.close();
            }

            if (ps != null) {
                ps.close();
            }

            if (conn != null) {
                conn.close();
            }

        } catch (SQLException ex) {

            System.err.println("Erro ao desalocar recurso: " + ex.getMessage());
        }
    }
}
