package Model.DAO;

import Model.DatabasePool;
import Model.entity.Company;
import Model.entity.Project_Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Project_CompanyDAO
{
    public boolean addProjectCompany(int providedProjectId, int providedCompanyId, String providedRole) throws SQLException
    {
        String sql = "INSERT INTO project_company (project_id, company_id, role) VALUES (?, ?, ?)";

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
            pstmt.setInt(2, providedCompanyId);
            pstmt.setString(3, providedRole); // 使用枚举的名称作为字符串

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

    public boolean updateProjectCompany(int providedProjectId, int providedCompanyId, String providedRole) throws SQLException
    {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE project_company SET ");

        sqlBuilder.append("project_id = ?");
        if (providedRole != null)
            sqlBuilder.append(", role = ?");

        sqlBuilder.append(" WHERE company_id = ?");

        String sql = sqlBuilder.toString();

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            int paramIndex = 2;

            pstmt.setInt(1, providedProjectId);

            if (providedRole != null)
                pstmt.setString(paramIndex++, providedRole);

            pstmt.setInt(paramIndex, providedCompanyId);

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

    public boolean deleteProjectCompany(int projectIdToRemove, int companyIdToRemove) throws SQLException
    {
        String sql = "DELETE FROM project_company WHERE project_id = ? AND company_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置要删除的project_id、company_id
            pstmt.setInt(1, projectIdToRemove);
            pstmt.setInt(2, companyIdToRemove);

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

    public List<Project_Company> getAllProjectCompany() throws SQLException
    {
        String sql = "SELECT * FROM project_company";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Project_Company> project_companies = new ArrayList<>();

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Project_Company project_company = new Project_Company(
                        rs.getInt("project_id"),
                        rs.getInt("company_id"),
                        rs.getString("role")
                );
                project_companies.add(project_company);
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return project_companies;
    }

    public List<Project_Company> getCompanyByCompanyID(int providedID) throws SQLException
    {
        String sql = "SELECT * FROM project_company WHERE company_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Project_Company> project_companies = new ArrayList<>();

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);
            // 设置参数
            pstmt.setInt(1, providedID);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Project_Company project_company = new Project_Company(
                        rs.getInt("project_id"),
                        rs.getInt("company_id"),
                        rs.getString("role")
                );
                project_companies.add(project_company);
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return project_companies;
    }
}
