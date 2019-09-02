package com.czz.transaction.actions;

import com.czz.transaction.handler.SingleThreadConnectionHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author chengzhzh@datangmobile.com
 * @create 2019-09-02 8:51
 */
public class UserOrderDao {
    private DataSource dataSource;

    public UserOrderDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void order() throws SQLException {
        Connection connection = SingleThreadConnectionHolder.getConnection(dataSource);
        System.out.println("当前用户订单线程"+ Thread.currentThread().getName()+"，使用管道："+ connection.hashCode());
    }
}
