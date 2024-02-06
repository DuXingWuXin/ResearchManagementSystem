package Model.DAO;

import Model.DatabasePool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public int login(String providedId, String providedPwd) throws SQLException
    {
        // SQL查询
        String sql = "SELECT user_role FROM user WHERE id = ? AND pwd = ?";

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
            pstmt.setString(1, providedId);
            pstmt.setString(2, providedPwd);

            // 执行查询并获取结果
            rs = pstmt.executeQuery();

            connection.commit();  // 提交事务
            connection.setAutoCommit(true);  // 恢复自动提交模式

            // 如果查询成功
            if (rs.next())
                return rs.getInt("user_role");
            // 0表示管理员，1表示员工

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DatabasePool.closeConnection(connection, pstmt, rs);
        }

        // 表示登录失败
        return 10;
    }

//    public static void main(String[] args)  throws SQLException
//    {
//        UserDAO userDAO = new UserDAO();
//        System.out.print(userDAO.login(666,"admin"));
//    }
}
