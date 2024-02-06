package Model.DAO;

import Model.DatabasePool;
import Model.entity.Subtopic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubtopicDAO
{
    public boolean addSubtopic(int providedProjectId, String providedHead, String providedEndDate, double providedTotalFunds, String providedTechnologyCondition)
            throws SQLException
    {
        String sql = "INSERT INTO subtopic (project_id, head, end_date, total_funds, technology_condition) VALUES (?, ?, ?, ?, ?)";

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
            pstmt.setString(2, providedHead);
            pstmt.setString(3, providedEndDate);
            pstmt.setDouble(4, providedTotalFunds);
            pstmt.setString(5, providedTechnologyCondition);

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

    public boolean deleteSubtopic(int subtopicIdToRemove) throws SQLException
    {
        String sql = "DELETE FROM subtopic WHERE subtopic_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置要删除的subtopic_id
            pstmt.setInt(1, subtopicIdToRemove);

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

    public boolean updateSubtopic(String providedProjectId, int providedSubtopicID, String providedHead, String providedEndDate, String providedTotalFunds, String providedTechnologyCondition)
            throws SQLException
    {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE subtopic SET ");
        boolean shouldSetComma = false;

        if (providedProjectId != null)
        {
            sqlBuilder.append("project_id = ?");
            shouldSetComma = true;
        }

        if (providedHead != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("head = ?");
            shouldSetComma = true;
        }

        if (providedEndDate != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("end_date = ?");
            shouldSetComma = true;
        }

        if (providedTotalFunds != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("total_funds = ?");
            shouldSetComma = true;
        }

        if (providedTechnologyCondition != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("technology_condition = ?");
            shouldSetComma = true;
        }

        sqlBuilder.append(" WHERE subtopic_id = ?");

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

            if (providedProjectId != null)
                pstmt.setInt(paramIndex++, Integer.parseInt(providedProjectId));

            if (providedHead != null)
                pstmt.setString(paramIndex++, providedHead);

            if (providedEndDate != null)
                pstmt.setString(paramIndex++, providedEndDate);

            if (providedTotalFunds != null)
                pstmt.setDouble(paramIndex++, Double.parseDouble(providedTotalFunds));

            if (providedTechnologyCondition != null)
                pstmt.setString(paramIndex++, providedTechnologyCondition);

            pstmt.setInt(paramIndex, providedSubtopicID);

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

    public List<Subtopic> getSubtopicsByProjectId(int projectId) throws SQLException {
        String sql = "SELECT * FROM subtopic WHERE project_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Subtopic> subtopics = new ArrayList<>();

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置参数
            pstmt.setInt(1, projectId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Subtopic subtopic = new Subtopic(
                        rs.getInt("subtopic_id"),
                        rs.getInt("project_id"),
                        rs.getString("head"),
                        rs.getString("end_date"),
                        rs.getDouble("total_funds"),
                        rs.getString("technology_condition")
                );
                subtopics.add(subtopic);
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return subtopics;
    }
}
