package com.czz.transaction.handler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chengzhzh@datangmobile.com
 * @create 2019-09-02 8:29
 */
public class ConnectionHolder {
    private Map<DataSource,Connection> map = new HashMap<>();

    public Connection getConnectionByDataSource(DataSource dataSource) throws SQLException {
        Connection connection = map.get(dataSource);

        if (connection== null || connection.isClosed()) {
            connection = dataSource.getConnection();
            map.put(dataSource,connection);
        }
        return connection;
    }
}
