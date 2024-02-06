package Model.DAO;

import Model.DatabasePool;
import Model.entity.Researcher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResearcherDAO
{
    public boolean addResearcher(int providedEmployeeId, String providedName, String providedGender,
                                 String providedTitle, int providedAge, String providedResearchArea, int providedLabId) throws SQLException
    {
        String sql = "INSERT INTO researcher (employee_id, name, gender, title, age, research_area, lab_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

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
            pstmt.setString(3, providedGender);
            pstmt.setString(4, providedTitle);
            pstmt.setInt(5, providedAge);
            pstmt.setString(6, providedResearchArea);
            pstmt.setInt(7, providedLabId);

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

    public boolean deleteResearcher(int employeeIdToDelete) throws SQLException
    {
        String sql = "DELETE FROM researcher WHERE employee_id = ?";

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

    public boolean updateResearcher(int employeeIdToUpdate, String newName, String newGender,
                                    String newTitle, String newAge, String newResearchArea, String newLabId) throws SQLException
    {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE researcher SET ");
        boolean shouldSetComma = false;

        if (newName != null) {
            sqlBuilder.append("name = ?");
            shouldSetComma = true;
        }

        if (newGender != null) {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("gender = ?");
            shouldSetComma = true;
        }

        if (newTitle != null) {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("title = ?");
            shouldSetComma = true;
        }

        if (newAge != null) {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("age = ?");
            shouldSetComma = true;
        }

        if (newResearchArea != null) {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("research_area = ?");
            shouldSetComma = true;
        }

        if (newLabId != null) {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("lab_id = ?");
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
                pstmt.setString(paramIndex++, String.valueOf(newGender));

            if (newTitle != null)
                pstmt.setString(paramIndex++, newTitle);

            if (newAge != null)
                pstmt.setInt(paramIndex++, Integer.parseInt(newAge));

            if (newResearchArea != null)
                pstmt.setString(paramIndex++, newResearchArea);

            if (newLabId != null)
                pstmt.setInt(paramIndex++, Integer.parseInt(newLabId));

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

    public List<Researcher> getAllResearchers() throws SQLException
    {
        String sql = "SELECT * FROM researcher";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();

            List<Researcher> researchers = new ArrayList<>();

            while (rs.next()) {
                Researcher researcher = new Researcher(
                        rs.getInt("employee_id"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("title"),
                        rs.getInt("age"),
                        rs.getString("research_area"),
                        rs.getInt("lab_id")
                );

                researchers.add(researcher);
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

            return researchers;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return null;
    }

    public List<Researcher> getResearchersByLabId(int labId) throws SQLException
    {
        String sql = "SELECT * FROM researcher WHERE lab_id = ?";

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

            List<Researcher> researchers = new ArrayList<>();

            while (rs.next()) {
                Researcher researcher = new Researcher(
                        rs.getInt("employee_id"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("title"),
                        rs.getInt("age"),
                        rs.getString("research_area"),
                        rs.getInt("lab_id")
                );

                researchers.add(researcher);
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

            return researchers;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return null;
    }

    public List<Researcher> getResearcherByName(String name) throws SQLException
    {
        String sql = "SELECT * FROM researcher WHERE name = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置参数
            pstmt.setString(1, name);

            rs = pstmt.executeQuery();

            List<Researcher> researchers = new ArrayList<>();

            while (rs.next()) {
                Researcher researcher = new Researcher(
                        rs.getInt("employee_id"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("title"),
                        rs.getInt("age"),
                        rs.getString("research_area"),
                        rs.getInt("lab_id")
                );

                researchers.add(researcher);
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

            return researchers;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return null;
    }

    public List<Researcher> getResearchersByEmployeeID(int employeeId) throws SQLException
    {
        String sql = "SELECT * FROM researcher WHERE employee_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置参数
            pstmt.setInt(1, employeeId);

            rs = pstmt.executeQuery();

            List<Researcher> researchers = new ArrayList<>();

            while (rs.next()) {
                Researcher researcher = new Researcher(
                        rs.getInt("employee_id"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("title"),
                        rs.getInt("age"),
                        rs.getString("research_area"),
                        rs.getInt("lab_id")
                );

                researchers.add(researcher);
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

            return researchers;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return null;
    }
}
