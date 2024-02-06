package Model.DAO;

import Model.DatabasePool;
import Model.entity.Office;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class  OfficeDAO
{
    public boolean addOffice(int labId, String officeLocation, double officeArea) throws SQLException
    {
        String sql = "INSERT INTO office (lab_id, office_location, office_area) VALUES (?, ?, ?)";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, labId);
            pstmt.setString(2, officeLocation);
            pstmt.setDouble(3, officeArea);

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

    public boolean deleteOffice(int officeId)  throws SQLException
    {
        String sql = "DELETE FROM office WHERE office_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, officeId);

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

    public boolean updateOffice(int officeId, String labId, String officeLocation, String officeArea) throws SQLException
    {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE office SET ");
        boolean shouldSetComma = false;

        if (labId != null)
        {
            sqlBuilder.append("lab_id = ?");
            shouldSetComma = true;
        }

        if (officeLocation != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("office_location = ?");
            shouldSetComma = true;
        }

        if (officeArea != null)
        {
            if (shouldSetComma)
                sqlBuilder.append(", ");

            sqlBuilder.append("office_area = ?");
            shouldSetComma = true;
        }

        sqlBuilder.append(" WHERE office_id = ?");

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

            if (labId != null)
                pstmt.setInt(paramIndex++, Integer.parseInt(labId));

            if (officeLocation != null)
                pstmt.setString(paramIndex++, officeLocation);

            if (officeArea != null)
                pstmt.setDouble(paramIndex++, Double.parseDouble(officeArea));

            pstmt.setInt(paramIndex, officeId);

            int affectedRows = pstmt.executeUpdate();

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

            return affectedRows > 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return false;
    }

    public List<Office> getOfficesByLabId(int labId) throws SQLException
    {
        List<Office> offices = new ArrayList<>();
        String sql = "SELECT * FROM office WHERE lab_id = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            connection = DatabasePool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);  // 开始事务

            pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, labId);

            rs = pstmt.executeQuery();

            while (rs.next())
            {
                int officeId = rs.getInt("office_id");
                String officeLocation = rs.getString("office_location");
                double officeArea = rs.getDouble("office_area");
                offices.add(new Office(officeId, labId, officeLocation, officeArea));
            }

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        return offices;
    }

//    public static void main(String[] args) throws SQLException
//    {
//        OfficeDAO officeDAO = new OfficeDAO();
//        System.out.println(officeDAO.addOffice(2,"华南理工大学", 100.1));
//        System.out.println(officeDAO.updateOffice(2,2,null, 200.1));
//        System.out.println(officeDAO.getOfficesByLabId(2)==null);
//        System.out.println(officeDAO.deleteOffice(2));
//    }
}
