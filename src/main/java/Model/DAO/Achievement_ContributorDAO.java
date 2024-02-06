package Model.DAO;

import Model.DatabasePool;
import Model.entity.Achievement_Contributor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class  Achievement_ContributorDAO
{
    public boolean addContributor(int providedAchievementId, int providedEmployeeId) throws SQLException
    {
        String sql = "INSERT INTO achievement_contributors (achievement_id, employee_id) VALUES (?, ?)";

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
            pstmt.setInt(2, providedEmployeeId);

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

    public boolean updateContributor(int providedAchievementId, int providedEmployeeId) throws SQLException
    {
        String sql = "UPDATE achievement_contributors SET employee_id = ? WHERE achievement_id = ?";

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

    public boolean deleteContributor(int achievementIdToRemove, int employeeIdToRemove) throws SQLException
    {
        String sql = "DELETE FROM achievement_contributors WHERE achievement_id = ? AND employee_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置要删除的achievement_id和employee_id
            pstmt.setInt(1, achievementIdToRemove);
            pstmt.setInt(2, employeeIdToRemove);

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

    public List<Achievement_Contributor> getContributorsByAchievementId(int achievementId) throws SQLException
    {
        String sql = "SELECT * FROM achievement_contributors WHERE achievement_id = ?";

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

            List<Achievement_Contributor> contributors = new ArrayList<>();

            while (rs.next()) {
                Achievement_Contributor contributor = new Achievement_Contributor(
                        rs.getInt("achievement_id"),
                        rs.getInt("employee_id")
                );

                contributors.add(contributor);
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

            return contributors;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return null;
    }
}
