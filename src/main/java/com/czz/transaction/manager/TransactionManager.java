package com.czz.transaction.manager;

import com.czz.transaction.handler.SingleThreadConnectionHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author chengzhzh@datangmobile.com
 * @create 2019-09-02 8:48
 */
public class TransactionManager {
    private DataSource dataSource;

    public TransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws SQLException {
        return SingleThreadConnectionHolder.getConnection(dataSource);
    }

    public void star() throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
    }

    public void rollback(){
        try {
            Connection connection = getConnection();
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void close() throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        connection.close();
    }
}
