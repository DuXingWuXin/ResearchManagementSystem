package Model.DAO;

import Model.DatabasePool;
import Model.entity.Laboratory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LaboratoryDAO
{
    public boolean addLaboratory(int providedID, String providedName, String providedIntroduction, String providedEmployeeID) throws SQLException
    {
        String sql = "INSERT INTO laboratory (lab_id, lab_name, lab_introduction, employee_id) VALUES (?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            // 设置参数
            pstmt.setInt(1,providedID);
            pstmt.setString(2, providedName);
            pstmt.setString(3, providedIntroduction);

            if (!providedEmployeeID.isEmpty()) {
                pstmt.setInt(4, Integer.parseInt(providedEmployeeID));
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }

            // 执行插入
            int affectedRows = pstmt.executeUpdate();

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return false;
    }

    public boolean deleteLaboratory(int labIdToDelete) throws SQLException
    {
        String sql = "DELETE FROM laboratory WHERE lab_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
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
        }finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return false;
    }

    public boolean updateLaboratory(int labIdToUpdate, String newLabName, String newLabIntroduction, String providedEmployeeID) throws SQLException
    {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE laboratory SET ");
        boolean shouldSetComma = false;

        if (newLabName != null)
        {
            sqlBuilder.append("lab_name = ?");
            shouldSetComma = true;
        }

        if (newLabIntroduction != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("lab_introduction = ?");
            shouldSetComma = true;
        }

        if (providedEmployeeID != null)
        {
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

        try
        {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            int paramIndex = 1;

            if (newLabName != null)
                pstmt.setString(paramIndex++, newLabName);

            if (newLabIntroduction != null)
                pstmt.setString(paramIndex++, newLabIntroduction);

            if (providedEmployeeID != null)
                pstmt.setString(paramIndex++, providedEmployeeID);

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

    public List<Laboratory> getAllLaboratories() throws SQLException
    {
        List<Laboratory> laboratories = new ArrayList<>();
        String sql = "SELECT * FROM laboratory";

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
                int labId = rs.getInt("lab_id");
                String labName = rs.getString("lab_name");
                String labIntroduction = rs.getString("lab_introduction");
                int employeeId = rs.getInt("employee_id");
                laboratories.add(new Laboratory(labId, labName, labIntroduction, employeeId));
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return laboratories;
    }

    public List<Laboratory> getLaboratoriesByName(String labNameToFind) throws SQLException
    {
        List<Laboratory> laboratories = new ArrayList<>();
        String sql = "SELECT * FROM laboratory WHERE lab_name = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, labNameToFind);

            rs = pstmt.executeQuery();

            while (rs.next())
            {
                int labId = rs.getInt("lab_id");
                String labName = rs.getString("lab_name");
                String labIntroduction = rs.getString("lab_introduction");
                int employeeId = rs.getInt("employee_id");
                laboratories.add(new Laboratory(labId, labName, labIntroduction, employeeId));
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return laboratories;
    }

//    public static void main(String[] args) throws SQLException
//    {
//        LaboratoryDAO laboratoryDAO = new LaboratoryDAO();
//        System.out.println(laboratoryDAO.addLaboratory("人工智能","华南理工大学"));
//        System.out.println(laboratoryDAO.updateLaboratory(3,null, "中山大学"));
//        System.out.println(laboratoryDAO.getAllLaboratories()==null);
//        System.out.println(laboratoryDAO.getLaboratoriesByName("人工智能"));
//        System.out.println(laboratoryDAO.addLaboratory("大数据","华南理工大学"));
//        System.out.println(laboratoryDAO.deleteLaboratory(3));
//    }
}
