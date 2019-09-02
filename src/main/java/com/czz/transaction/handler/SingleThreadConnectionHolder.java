package com.czz.transaction.handler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author chengzhzh@datangmobile.com
 * @create 2019-09-02 8:43
 * 把公共的调用方法 用threadLocal 封装
 */
public class SingleThreadConnectionHolder {
    private static ThreadLocal<ConnectionHolder> threadLocal = new ThreadLocal<>();

    private static ConnectionHolder getConnectionHolder(){
        ConnectionHolder connectionHolder = threadLocal.get();
        if (connectionHolder == null) {
            connectionHolder = new ConnectionHolder();
            threadLocal.set(connectionHolder);
        }
        return connectionHolder;
    }

    public static Connection getConnection(DataSource dataSource) throws SQLException {
        return getConnectionHolder().getConnectionByDataSource(dataSource);
    }
}
