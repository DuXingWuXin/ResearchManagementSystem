package Model.DAO;

import Model.DatabasePool;
import Model.entity.Project_Researcher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Project_ResearcherDAO
{
    public boolean addProjectResearcher(int providedProjectId, int providedEmployeeId, String providedJoinDate,
                                        double providedWorkload, double providedControlFunds) throws SQLException
    {
        String sql = "INSERT INTO project_researchers (project_id, employee_id, join_date, workload, control_funds) VALUES (?, ?, ?, ?, ?)";

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
            pstmt.setInt(2, providedEmployeeId);
            pstmt.setString(3, providedJoinDate);
            pstmt.setDouble(4, providedWorkload);
            pstmt.setDouble(5, providedControlFunds);

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

    public boolean deleteProjectResearcher(int projectIdToRemove, int employeeIdToRemove) throws SQLException
    {
        String sql = "DELETE FROM project_researchers WHERE project_id = ? AND employee_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置要删除的project_id和employee_id
            pstmt.setInt(1, projectIdToRemove);
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

    public boolean updateProjectResearcher(int providedProjectId, int providedEmployeeId, String providedJoinDate,
                                        String providedWorkload, String providedControlFunds) throws SQLException
    {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE project_researchers SET ");
        boolean shouldSetComma = false;

        if (providedJoinDate != null)
        {
            sqlBuilder.append("join_date = ?");
            shouldSetComma = true;
        }

        if (providedWorkload != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("workload = ?");
            shouldSetComma = true;
        }

        if (providedControlFunds != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("control_funds = ?");
            shouldSetComma = true;
        }

        sqlBuilder.append(" WHERE project_id = ? AND employee_id = ?");

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

            if (providedJoinDate != null)
                pstmt.setString(paramIndex++, providedJoinDate);

            if (providedWorkload != null)
                pstmt.setDouble(paramIndex++, Double.parseDouble(providedWorkload));

            if (providedControlFunds != null)
                pstmt.setDouble(paramIndex++, Double.parseDouble(providedControlFunds));

            pstmt.setInt(paramIndex++, providedProjectId);
            pstmt.setInt(paramIndex, providedEmployeeId);

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

    public List<Project_Researcher> getProjectResearchersByProjectId(int projectId) throws SQLException {
        String sql = "SELECT * FROM project_researchers WHERE project_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Project_Researcher> projectResearchers = new ArrayList<>();

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置参数
            pstmt.setInt(1, projectId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Project_Researcher projectResearcher = new Project_Researcher(
                        rs.getInt("participation_id"),
                        rs.getInt("project_id"),
                        rs.getInt("employee_id"),
                        rs.getDate("join_date"),
                        rs.getDouble("workload"),
                        rs.getDouble("control_funds")
                );
                projectResearchers.add(projectResearcher);
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return projectResearchers;
    }
}
