package Model;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabasePool
{
    private static BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setUrl(jdbcConfiguration.url);
        dataSource.setUsername(jdbcConfiguration.user);
        dataSource.setPassword(jdbcConfiguration.pwd);
        dataSource.setDriverClassName(jdbcConfiguration.DRIVER);

        dataSource.setInitialSize(5); // 初始化连接数
        dataSource.setMaxTotal(10); // 最大连接数
        dataSource.setMaxIdle(5); // 最大的空闲连接数
        dataSource.setMinIdle(2); // 最小的空闲连接数
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet)
    {
        try {
            if (resultSet != null)
                resultSet.close();

            if (preparedStatement != null)
                preparedStatement.close();

            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}