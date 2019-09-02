package com.czz.transaction.servie;

import com.czz.transaction.actions.UserAccountDao;
import com.czz.transaction.actions.UserOrderDao;
import com.czz.transaction.manager.TransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author chengzhzh@datangmobile.com
 * @create 2019-09-02 8:55
 */
public class UserService {

    private UserOrderDao userOrderDao;
    private UserAccountDao userAccountDao;
    private TransactionManager transactionManager;


    public UserService(DataSource dataSource) {
        userOrderDao = new UserOrderDao(dataSource);
        userAccountDao = new UserAccountDao(dataSource);;
        transactionManager = new TransactionManager(dataSource);;
    }

    public void action(){
        try {
            transactionManager.star();
            userAccountDao.buy();
            userOrderDao.order();
        } catch (SQLException e) {
            e.printStackTrace();
            transactionManager.rollback();
        }
    }
}
