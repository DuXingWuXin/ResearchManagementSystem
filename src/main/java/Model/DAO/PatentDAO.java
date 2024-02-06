package Model.DAO;

import Model.DatabasePool;
import Model.entity.Patent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatentDAO
{
    public boolean addPatent(int providedAchievementId, String providedPatentType, String providedInformation) throws SQLException
    {
        String sql = "INSERT INTO patents (achievement_id, patent_type, information) VALUES (?, ?, ?)";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置参数
            pstmt.setInt(1, providedAchievementId);
            pstmt.setString(2, providedPatentType);
            pstmt.setString(3, providedInformation);

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

    public boolean updatePatent(int providedAchievementId, String providedPatentType, String providedInformation) throws SQLException
    {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE patents SET ");

        boolean shouldSetComma = false;

        if (providedPatentType != null)
        {
            sqlBuilder.append("patent_type = ?");
            shouldSetComma = true;
        }

        if (providedInformation != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("information = ?");
        }

        sqlBuilder.append(" WHERE achievement_id = ?");

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

            if (providedPatentType != null)
                pstmt.setString(paramIndex++, providedPatentType);

            if (providedInformation != null)
                pstmt.setString(paramIndex++, providedInformation);

            pstmt.setInt(paramIndex, providedAchievementId);
            
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

    public boolean deletePatent(int achievementIdToRemove) throws SQLException
    {
        String sql = "DELETE FROM patents WHERE achievement_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置要删除的achievement_id
            pstmt.setInt(1, achievementIdToRemove);

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

    public Patent getPatentByAchievementId(int achievementId) throws SQLException
    {
        String sql = "SELECT * FROM patents WHERE achievement_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置参数
            pstmt.setInt(1, achievementId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Patent(
                        rs.getInt("achievement_id"),
                        rs.getString("patent_type"),
                        rs.getString("information")
                );
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return null;
    }
}
