package Model.DAO;

import Model.DatabasePool;
import Model.entity.Project;
import Model.entity.Secretary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SecretaryDAO
{
    public boolean addSecretary(int providedEmployeeId, String providedName, String providedGender,
                                int providedAge, String providedHireDate, String providedDuties) throws SQLException {
        String sql = "INSERT INTO secretary (employee_id, name, gender, age, hire_date, duties) VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置参数
            pstmt.setInt(1, providedEmployeeId);
            pstmt.setString(2, providedName);
            pstmt.setString(3, String.valueOf(providedGender));
            pstmt.setInt(4, providedAge);
            pstmt.setString(5, providedHireDate);
            pstmt.setString(6, providedDuties);

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

    public boolean deleteSecretary(int employeeIdToDelete) throws SQLException {
        String sql = "DELETE FROM secretary WHERE employee_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置要删除的employee_id
            pstmt.setInt(1, employeeIdToDelete);

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

    public boolean updateSecretary(int employeeIdToUpdate, String newName, String newGender,
                                   String newAge, String newHireDate, String newDuties) throws SQLException
    {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE secretary SET ");
        boolean shouldSetComma = false;

        if (newName != null)
        {
            sqlBuilder.append("name = ?");
            shouldSetComma = true;
        }

        if (newGender != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("gender = ?");
            shouldSetComma = true;
        }

        if (newAge != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("age = ?");
            shouldSetComma = true;
        }

        if (newHireDate != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("hire_date = ?");
            shouldSetComma = true;
        }

        if (newDuties != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("duties = ?");
            shouldSetComma = true;
        }

        sqlBuilder.append(" WHERE employee_id = ?");

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

            if (newName != null)
                pstmt.setString(paramIndex++, newName);

            if (newGender != null)
                pstmt.setString(paramIndex++, newGender);

            if (newAge != null)
                pstmt.setInt(paramIndex++, Integer.parseInt(newAge));

            if (newHireDate != null)
                pstmt.setString(paramIndex++, newHireDate);

            if (newDuties != null)
                pstmt.setString(paramIndex++, newDuties);

            pstmt.setInt(paramIndex, employeeIdToUpdate);

            // 执行更新
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

    public List<Secretary> getAllSecretaries() throws SQLException
    {
        List<Secretary> secretaries = new ArrayList<>();
        String sql = "SELECT * FROM secretary";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                int employee_id = rs.getInt("employee_id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                java.util.Date hire_date = rs.getDate("hire_date");
                String duties = rs.getString("duties");

                secretaries.add(new Secretary(employee_id, name, gender, age, hire_date, duties));
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return secretaries;
    }

    public List<Secretary> getSecretaryByName(String providedName) throws SQLException
    {
        List<Secretary> secretaries = new ArrayList<>();
        String sql = "SELECT * FROM secretary WHERE name = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置参数
            pstmt.setString(1, providedName);

            rs = pstmt.executeQuery();

            while (rs.next())
            {
                int employee_id = rs.getInt("employee_id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                java.util.Date hire_date = rs.getDate("hire_date");
                String duties = rs.getString("duties");

                secretaries.add(new Secretary(employee_id, name, gender, age, hire_date, duties));
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return secretaries;
    }
}
