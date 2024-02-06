package Model.DAO;

import Model.DatabasePool;
import Model.entity.Director;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectorDAO
{
    public boolean addDirector(int providedEmployeeId, String providedStartDate, int providedTenureYears, int providedLabId) throws SQLException
    {
        String sql = "INSERT INTO director (employee_id, start_date, tenure_years, lab_id) VALUES (?, ?, ?, ?)";

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
            pstmt.setString(2, providedStartDate);
            pstmt.setInt(3, providedTenureYears);
            pstmt.setInt(4, providedLabId);

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

    public boolean deleteDirector(int labIdToDelete) throws SQLException
    {
        String sql = "DELETE FROM director WHERE lab_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置要删除的lab_id
            pstmt.setInt(1, labIdToDelete);

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

    public boolean updateDirector(String newEmployeeId, String newStartDate, String newTenureYears, int labIdToUpdate) throws SQLException
    {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE director SET ");
        boolean shouldSetComma = false;

        if (newStartDate != null) {
            sqlBuilder.append("start_date = ?");
            shouldSetComma = true;
        }

        if (newTenureYears != null) {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("tenure_years = ?");
            shouldSetComma = true;
        }

        if (newEmployeeId != null) {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("employee_id = ?");
            shouldSetComma = true;
        }

        sqlBuilder.append(" WHERE lab_id = ?");

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

            if (newStartDate != null)
                pstmt.setString(paramIndex++, newStartDate);

            if (newTenureYears != null)
                pstmt.setInt(paramIndex++, Integer.parseInt(newTenureYears));

            if (newEmployeeId != null)
                pstmt.setInt(paramIndex++, Integer.parseInt(newEmployeeId));

            pstmt.setInt(paramIndex, labIdToUpdate);

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

    public List<Director> getAllDirectors() throws SQLException {
        String sql = "SELECT * FROM director";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();

            List<Director> directors = new ArrayList<>();

            while (rs.next()) {
                Director director = new Director(
                        rs.getInt("employee_id"),
                        rs.getDate("start_date"),
                        rs.getInt("tenure_years"),
                        rs.getInt("lab_id")
                );

                directors.add(director);
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

            return directors;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return null;
    }

    public List<Director> getDirectorsByLabId(int labId) throws SQLException {
        String sql = "SELECT * FROM director WHERE lab_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置参数
            pstmt.setInt(1, labId);

            rs = pstmt.executeQuery();

            List<Director> directors = new ArrayList<>();

            while (rs.next()) {
                Director director = new Director(
                        rs.getInt("employee_id"),
                        rs.getDate("start_date"),
                        rs.getInt("tenure_years"),
                        rs.getInt("lab_id")
                );

                directors.add(director);
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

            return directors;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return null;
    }
}
