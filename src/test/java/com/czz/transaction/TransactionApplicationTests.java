package com.czz.transaction;

import com.czz.transaction.servie.UserService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionApplicationTests {
    public  final String CONNECTIONURL = "jdbc:mysql://39.98.200.95:3306/czz_java";
    public  final String Driver = "com.mysql.jdbc.Driver";
    public  final String USERNAME = "root";
    public  final String PASSWORD = "Shikp@2019";

    @Test
    public void contextLoads() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(Driver);
        basicDataSource.setUsername(USERNAME);
        basicDataSource.setPassword(PASSWORD);
        basicDataSource.setUrl(CONNECTIONURL);

        final UserService userService = new UserService(basicDataSource);

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                userService.action();
            }).start();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
