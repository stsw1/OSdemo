package com.tject.config;
import com.tject.common.handlers.JsonListTypeHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;

import javax.sql.DataSource;
import java.util.List;

//@Configuration
public class MyBatisConfig {

    //@Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        // 注册自定义 TypeHandler
        sqlSessionFactoryBean.getObject().getConfiguration().getTypeHandlerRegistry()
                .register(List.class, JdbcType.VARCHAR, JsonListTypeHandler.class);

        return sqlSessionFactoryBean.getObject();
    }
}
