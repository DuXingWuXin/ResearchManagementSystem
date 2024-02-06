package Model.DAO;

import Model.DatabasePool;
import Model.entity.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO
{
    public boolean addContact(int providedCompanyId, String providedName, String providedOfficePhone, String providedMobilePhone,
                              String providedEmail, String providedRole) throws SQLException
    {
        String sql = "INSERT INTO contact (company_id, name, office_phone, mobile_phone, email, role) VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置参数
            pstmt.setInt(1, providedCompanyId);
            pstmt.setString(2, providedName);
            pstmt.setString(3, providedOfficePhone);
            pstmt.setString(4, providedMobilePhone);
            pstmt.setString(5, providedEmail);
            pstmt.setString(6, providedRole);

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

    public boolean updateContact(int providedContactId, String providedCompanyId, String providedName, String providedOfficePhone, String providedMobilePhone,
                              String providedEmail, String providedRole) throws SQLException
    {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE contact SET ");
        boolean shouldSetComma = false;

        if (providedCompanyId != null)
        {
            sqlBuilder.append("company_id = ?");
            shouldSetComma = true;
        }

        if (providedName != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("name = ?");
            shouldSetComma = true;
        }

        if (providedOfficePhone != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("office_phone = ?");
            shouldSetComma = true;
        }

        if (providedMobilePhone != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("mobile_phone = ?");
            shouldSetComma = true;
        }

        if (providedEmail != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("email = ?");
            shouldSetComma = true;
        }

        if (providedRole != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("role = ?");
            shouldSetComma = true;
        }

        sqlBuilder.append(" WHERE contact_id = ?");

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

            if (providedCompanyId != null)
                pstmt.setInt(paramIndex++, Integer.parseInt(providedCompanyId));

            if (providedName != null)
                pstmt.setString(paramIndex++, providedName);

            if (providedOfficePhone != null)
                pstmt.setString(paramIndex++, providedOfficePhone);

            if (providedMobilePhone != null)
                pstmt.setString(paramIndex++, providedMobilePhone);

            if (providedEmail != null)
                pstmt.setString(paramIndex++, providedEmail);

            if (providedRole != null)
                pstmt.setString(paramIndex++, providedRole);

            pstmt.setInt(paramIndex, providedContactId);

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

    public boolean deleteContact(int contactIdToRemove) throws SQLException
    {
        String sql = "DELETE FROM contact WHERE contact_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置要删除的contact_id
            pstmt.setInt(1, contactIdToRemove);

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

    public List<Contact> getContactsByCompanyId(int companyId) throws SQLException {
        String sql = "SELECT * FROM contact WHERE company_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Contact> contacts = new ArrayList<>();

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置参数
            pstmt.setInt(1, companyId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Contact contact = new Contact(
                        rs.getInt("contact_id"),
                        rs.getInt("company_id"),
                        rs.getString("name"),
                        rs.getString("office_phone"),
                        rs.getString("mobile_phone"),
                        rs.getString("email"),
                        rs.getString("role")
                );
                contacts.add(contact);
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return contacts;
    }
}
