/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nicolau Alfredo
 */
public abstract class AbstractGenericDAO<T>
        implements GenericoDAO<T> {

    protected void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println(
                        "Error closing ResultSet: "
                        + ex.getLocalizedMessage()
                );
            }
        }
    }

    protected int parsePageNumber(String pageNumber) {

        if (pageNumber == null || pageNumber.trim().isEmpty()) {
            return 1;
        }

        try {
            int page = Integer.parseInt(pageNumber.trim());

            if (page < 1) {
                return 1;
            }

            return page;

        } catch (NumberFormatException ex) {
            return 1;
        }
    }

    protected int calculateOffset(int page, int totalPerPage) {

        int offset = (page * totalPerPage) - totalPerPage;

        if (offset < 0) {
            return 0;
        }

        return offset;
    }

    protected int calculateTotalPages(
            int totalRecords,
            int totalPerPage
    ) {

        int totalPages = (int) Math.ceil(
                totalRecords / (double) totalPerPage
        );

        if (totalPages < 1) {
            return 1;
        }

        return totalPages;
    }

    protected void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {

        closeResultSet(rs);

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                System.err.println(
                        "Error closing PreparedStatement: "
                        + ex.getLocalizedMessage()
                );
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.err.println(
                        "Error closing Connection: "
                        + ex.getLocalizedMessage()
                );
            }
        }
    }
}
