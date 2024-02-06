package Model.DAO;

import Model.DatabasePool;
import Model.entity.Paper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaperDAO
{
    public boolean addPaper(int providedAchievementId, String providedInformation) throws SQLException
    {
        String sql = "INSERT INTO papers (achievement_id, information) VALUES (?, ?)";

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
            pstmt.setString(2, providedInformation);

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

    public boolean updatePaper(int providedAchievementId, String providedInformation) throws SQLException
    {
        String sql = "UPDATE papers SET information = ? WHERE achievement_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置参数
            pstmt.setString(1, providedInformation);
            pstmt.setInt(2, providedAchievementId);

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

    public boolean deletePaper(int achievementIdToRemove) throws SQLException
    {
        String sql = "DELETE FROM papers WHERE achievement_id = ?";

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

    public Paper getPaperByAchievementId(int achievementId) throws SQLException
    {
        String sql = "SELECT * FROM papers WHERE achievement_id = ?";

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

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

            if (rs.next()) {
                return new Paper(
                        rs.getInt("achievement_id"),
                        rs.getString("information")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return null;
    }
}
