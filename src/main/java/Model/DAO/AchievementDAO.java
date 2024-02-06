package Model.DAO;

import Model.DatabasePool;
import Model.entity.Achievement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class AchievementDAO
{
    public boolean addAchievement(int providedProjectId, String providedAchievementName,
                                  String providedGetTime, int providedRankNumber, String providedType) throws SQLException {
        String sql = "INSERT INTO achievement (project_id, achievement_name, get_time, rank_number, type) VALUES (?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置参数
            pstmt.setInt(1, providedProjectId);
            pstmt.setString(2, providedAchievementName);
            pstmt.setString(3, providedGetTime);
            pstmt.setInt(4, providedRankNumber);
            pstmt.setString(5, providedType); // 将枚举类型转换为字符串

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

    public boolean deleteAchievement(int achievementIdToDelete) throws SQLException
    {
        String sql = "DELETE FROM achievement WHERE achievement_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置要删除的achievement_id
            pstmt.setInt(1, achievementIdToDelete);

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

    public boolean updateAchievement(int achievementIdToUpdate, String projectIdToUpdate, String newAchievementName,
                                     String newGetTime, String newRankNumber, String newType) throws SQLException
    {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE achievement SET ");
        boolean shouldSetComma = false;

        if (projectIdToUpdate != null)
        {
            sqlBuilder.append("project_id = ?");
            shouldSetComma = true;
        }

        if (newAchievementName != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("achievement_name = ?");
            shouldSetComma = true;
        }

        if (newGetTime != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("get_time = ?");
            shouldSetComma = true;
        }

        if (newRankNumber != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("rank_number = ?");
            shouldSetComma = true;
        }

        if (newType != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("type = ?");
            shouldSetComma = true;
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

            if (newAchievementName != null)
                pstmt.setString(paramIndex++, newAchievementName);

            if (newGetTime != null)
                pstmt.setString(paramIndex++, newGetTime);

            if (newRankNumber != null)
                pstmt.setInt(paramIndex++, Integer.parseInt(newRankNumber));

            if (newType != null)
                pstmt.setString(paramIndex++, newType);

            if (projectIdToUpdate != null)
                pstmt.setInt(paramIndex++, Integer.parseInt(projectIdToUpdate));

            pstmt.setInt(paramIndex, achievementIdToUpdate);

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

    public int selectAchievement(int providedProjectId, String providedAchievementName,
                                 String providedGetTime, int providedRankNumber, String providedType) throws SQLException
    {
        String sql = "SELECT achievement_id FROM achievement WHERE project_id = ? AND achievement_name = ? AND get_time = ? AND rank_number = ? AND type = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置参数
            pstmt.setInt(1, providedProjectId);
            pstmt.setString(2, providedAchievementName);
            pstmt.setString(3, providedGetTime);
            pstmt.setInt(4, providedRankNumber);
            pstmt.setString(5, providedType); // 将枚举类型转换为字符串

            rs = pstmt.executeQuery();

            if (rs.next())
            {
                int achievementId = rs.getInt("achievement_id");

                connection.commit();  // 提交事务
                connection.setAutoCommit(true);  // 恢复自动提交模式

                return achievementId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return 0;
    }

    public List<Achievement> getAllAchievements() throws SQLException {
        String sql = "SELECT * FROM achievement";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();

            List<Achievement> achievements = new ArrayList<>();

            while (rs.next()) {
                Achievement achievement = new Achievement(
                        rs.getInt("achievement_id"),
                        rs.getInt("project_id"),
                        rs.getString("achievement_name"),
                        rs.getDate("get_time"),
                        rs.getInt("rank_number"),
                        rs.getString("type")
                );

                achievements.add(achievement);
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

            return achievements;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return null;
    }

    public List<Achievement> getAchievementsByProjectId(int projectId) throws SQLException
    {
        String sql = "SELECT * FROM achievement WHERE project_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置参数
            pstmt.setInt(1, projectId);

            rs = pstmt.executeQuery();

            List<Achievement> achievements = new ArrayList<>();

            while (rs.next()) {
                Achievement achievement = new Achievement(
                        rs.getInt("achievement_id"),
                        rs.getInt("project_id"),
                        rs.getString("achievement_name"),
                        rs.getDate("get_time"),
                        rs.getInt("rank_number"),
                        rs.getString("type")
                );

                achievements.add(achievement);
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

            return achievements;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return null;
    }

    public List<Achievement> getAchievementsByAchievementId(int providedAchievementID) throws SQLException
    {
        String sql = "SELECT * FROM achievement WHERE achievement_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置参数
            pstmt.setInt(1, providedAchievementID);

            rs = pstmt.executeQuery();

            List<Achievement> achievements = new ArrayList<>();

            while (rs.next()) {
                Achievement achievement = new Achievement(
                        rs.getInt("achievement_id"),
                        rs.getInt("project_id"),
                        rs.getString("achievement_name"),
                        rs.getDate("get_time"),
                        rs.getInt("rank_number"),
                        rs.getString("type")
                );

                achievements.add(achievement);
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

            return achievements;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return null;
    }
}
