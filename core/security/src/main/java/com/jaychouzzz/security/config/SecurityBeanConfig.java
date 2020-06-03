package com.jaychouzzz.security.config;

import com.jaychouzzz.security.handler.MyLoginFailureHandler;
import com.jaychouzzz.security.handler.MyLoginSuccessfulHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Classname SecurityBeanConfig
 * @description security bean config相关   需要第三方应用扩展时需要@ConditionalOnMissingBean
 * @Author chuanfang
 * @Date 2020/5/14 15:03
 * @Version 1.0
 */
@Configuration
public class SecurityBeanConfig {

    private static final String VERIFY_SQL = "select count(*) from information_schema.tables where table_name='persistent_logins'";

    /**
     * 缺省配置
     * @return 登录成功处理器
     */
    @Bean
    @ConditionalOnMissingBean(MyLoginSuccessfulHandler.class)
    public MyLoginSuccessfulHandler myLoginSuccessfulHandler() {
        return new MyLoginSuccessfulHandler();
    }

    /**
     * 登录失败处理器
     * @return 处理器
     */
    @Bean
    @ConditionalOnMissingBean(MyLoginFailureHandler.class)
    public MyLoginFailureHandler myLoginFailureHandler() {
        return new MyLoginFailureHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 缺省配置
     * 记住我相关的token仓库
     * @return token仓库
     */
    @Bean
    @ConditionalOnMissingBean(PersistentTokenRepository.class)
    public PersistentTokenRepository tokenRepository(DataSource dataSource) {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        //有persistent_logins 则为true 否则为false
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(VERIFY_SQL);
            while (resultSet.next()) {
                int count = resultSet.getInt(1);
                if(count==0) {
                    repository.setCreateTableOnStartup(true);
                }else {
                    repository.setCreateTableOnStartup(false);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return repository;
    }
}
