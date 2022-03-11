package com.caterer.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.datatables.DataTablesRepositoryFactoryBean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class, basePackages = "repositoryDataTables")
public class DataTablesConfig {
}
