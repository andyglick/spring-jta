package org.springsource.examples.crm.services.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springsource.examples.crm.services.config.CrmConfiguration;

import javax.sql.DataSource;

/**
 * Defines the configuration of several objects specific to our JDBC based solution
 *
 * @author Josh Long
 */
@Configuration
public class JdbcConfiguration extends CrmConfiguration{

  @Bean
  public PlatformTransactionManager transactionManager() {
    DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
    dataSourceTransactionManager.setDataSource(this.dataSource());
    return dataSourceTransactionManager;
  }

  @Bean
  public TransactionTemplate transactionTemplate() {
    TransactionTemplate tt = new TransactionTemplate();
    tt.setTransactionManager(this.transactionManager());
    return tt;
  }

  @Bean
  public JdbcTemplate jdbcTemplate() {
    DataSource ds = dataSource();
    return new JdbcTemplate(ds);
  }
}
