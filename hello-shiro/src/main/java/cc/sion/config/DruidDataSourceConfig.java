package cc.sion.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile({"test"})
public class DruidDataSourceConfig {

    @Value("${druid.url}")
    private String url;
    @Value("${druid.username}")
    private String username;
    @Value("${druid.password}")
    private String password;

    @Value("${druid.initialSize}")
    private int initialSize;
    @Value("${druid.maxActive}")
    private int maxActive;
    @Value("${druid.minIdle}")
    private int minIdle;
    @Value("${druid.maxWait}")
    private int maxWait;
    @Value("${druid.validationQuery}")
    private String validationQuery;
    @Value("${druid.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${druid.testOnReturn}")
    private boolean testOnReturn;
    @Value("${druid.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${druid.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;
    @Value("${druid.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;
    @Value("${druid.removeAbandoned}")
    private boolean removeAbandoned;
    @Value("${druid.removeAbandonedTimeout}")
    private int removeAbandonedTimeout;
    @Value("${druid.logAbandoned}")
    private boolean logAbandoned;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {

        DruidDataSource ds = new DruidDataSource();
        ds.setUrl(url);

        ds.setUsername(username);
        ds.setPassword(username);

        ds.setInitialSize(initialSize);
        ds.setMaxActive(maxActive);
        ds.setMinIdle(minIdle);
        ds.setMaxWait(maxWait);

        ds.setValidationQuery(validationQuery);

        ds.setTestOnBorrow(testOnBorrow);
        ds.setTestOnReturn(testOnReturn);
        ds.setTestWhileIdle(testWhileIdle);

        ds.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);

        ds.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        ds.setRemoveAbandoned(removeAbandoned);
        ds.setRemoveAbandonedTimeout(removeAbandonedTimeout);
        ds.setLogAbandoned(logAbandoned);

        return ds;
    }
}