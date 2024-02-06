package Model.DAO;

import Model.DatabasePool;
import Model.entity.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO
{
    public boolean addCompany(String providedCompanyName, String providedAddress) throws SQLException
    {
        String sql = "INSERT INTO company (company_name, address) VALUES (?, ?)";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置参数
            pstmt.setString(1, providedCompanyName);
            pstmt.setString(2, providedAddress);

            // 执行插入
            int affectedRows = pstmt.executeUpdate();

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return false;
    }

    public boolean updateCompany(int providedID, String providedCompanyName, String providedAddress) throws SQLException
    {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE company SET ");
        boolean shouldSetComma = false;

        if (providedCompanyName != null)
        {
            sqlBuilder.append("company_name = ?");
            shouldSetComma = true;
        }

        if (providedAddress != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("address = ?");
            shouldSetComma = true;
        }

        sqlBuilder.append(" WHERE company_id = ?");

        String sql = sqlBuilder.toString();

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            int paramIndex = 1;

            if (providedCompanyName != null)
                pstmt.setString(paramIndex++, providedCompanyName);

            if (providedAddress != null)
                pstmt.setString(paramIndex++, providedAddress);

            pstmt.setInt(paramIndex, providedID);

            // 执行插入
            int affectedRows = pstmt.executeUpdate();

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return false;
    }

    public boolean deleteCompany(int companyIdToRemove) throws SQLException
    {
        String sql = "DELETE FROM company WHERE company_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置要删除的company_id
            pstmt.setInt(1, companyIdToRemove);

            // 执行删除
            int affectedRows = pstmt.executeUpdate();

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return false;
    }

    public List<Company> getAllCompanies() throws SQLException
    {
        String sql = "SELECT * FROM company";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Company> companies = new ArrayList<>();

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Company company = new Company(
                        rs.getInt("company_id"),
                        rs.getString("company_name"),
                        rs.getString("address")
                );
                companies.add(company);
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

            return companies;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return null;
    }

    public List<Company> getCompanyByName(String providedName) throws SQLException
    {
        String sql = "SELECT * FROM company WHERE company_name = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Company> companies = new ArrayList<>();

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);
            // 设置参数
            pstmt.setString(1, providedName);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Company company = new Company(
                        rs.getInt("company_id"),
                        rs.getString("company_name"),
                        rs.getString("address")
                );
                companies.add(company);
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

            return companies;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return null;
    }
}
