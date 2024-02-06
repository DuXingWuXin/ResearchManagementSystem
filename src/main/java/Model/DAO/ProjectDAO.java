package Model.DAO;

import Model.DatabasePool;
import Model.entity.Laboratory;
import Model.entity.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectDAO
{
    public boolean addProject(int providedProjectId, String providedProjectName, String providedHead, String providedContent,
                              double providedTotalFunds, String providedStartDate, String providedEndDate) throws SQLException
    {
        String sql = "INSERT INTO project (project_id, project_name, head, content, total_funds, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?, ?)";

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
            pstmt.setString(2, providedProjectName);
            pstmt.setString(3, providedHead);
            pstmt.setString(4, providedContent);
            pstmt.setDouble(5, providedTotalFunds);
            pstmt.setString(6, providedStartDate);
            pstmt.setString(7, providedEndDate);

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

    public boolean deleteProject(int projectIdToRemove) throws SQLException
    {
        String sql = "DELETE FROM project WHERE project_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置要删除的project_id
            pstmt.setInt(1, projectIdToRemove);

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

    public boolean updateProject(int providedProjectId, String providedProjectName, String providedHead, String providedContent,
                              String providedTotalFunds, String providedStartDate, String providedEndDate) throws SQLException
    {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE project SET ");
        boolean shouldSetComma = false;

        if (providedProjectName != null)
        {
            sqlBuilder.append("project_name = ?");
            shouldSetComma = true;
        }

        if (providedHead != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("head = ?");
            shouldSetComma = true;
        }

        if (providedContent != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("content = ?");
            shouldSetComma = true;
        }

        if (providedTotalFunds != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("total_funds = ?");
            shouldSetComma = true;
        }

        if (providedStartDate != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("start_date = ?");
            shouldSetComma = true;
        }

        if (providedEndDate != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("end_date = ?");
            shouldSetComma = true;
        }

        sqlBuilder.append(" WHERE project_id = ?");

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

            if (providedProjectName != null)
                pstmt.setString(paramIndex++, providedProjectName);

            if (providedHead != null)
                pstmt.setString(paramIndex++, providedHead);

            if (providedContent != null)
                pstmt.setString(paramIndex++, providedContent);

            if (providedTotalFunds != null)
                pstmt.setDouble(paramIndex++, Double.parseDouble(providedTotalFunds));

            if (providedStartDate != null)
                pstmt.setString(paramIndex++, providedStartDate);

            if (providedEndDate != null)
                pstmt.setString(paramIndex++, providedEndDate);

            pstmt.setInt(paramIndex, providedProjectId);

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

    public List<Project> getAllProjects() throws SQLException
    {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM project";

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
                int projectId = rs.getInt("project_id");
                String projectName = rs.getString("project_name");
                String projectHead = rs.getString("head");
                String projectContent = rs.getString("content");
                double projectFund = rs.getDouble("total_funds");
                Date projectStartDate = rs.getDate("start_date");
                Date projectEndDate = rs.getDate("end_date");

                projects.add(new Project(projectId, projectName, projectHead, projectContent, projectFund, projectStartDate, projectEndDate));
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return projects;
    }

    public Project getProjectByName(String providedName) throws SQLException
    {
        String sql = "SELECT * FROM project WHERE project_name = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置参数
            pstmt.setString(1, providedName);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Project(
                        rs.getInt("project_id"),
                        rs.getString("project_name"),
                        rs.getString("head"),
                        rs.getString("content"),
                        rs.getDouble("total_funds"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date")
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
