package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

// UserDaoTest Class -> TDD 방식으로 진행
public class UserDaoTest {
    // BeforeEach -> Test Code 실행전 작업해주는 곳
    // setUp -> Test Code 실행전 작업해주는 method
    @BeforeEach
    void setUp() {
        // ResourceDatabasePopulator : DB 에 초기 Data 를 적재하는 Script Loader 임[constructor]
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        // db_schema.sql 라는 Script file 을 ClassPathResource 에서 Read 해서 Script 파일을 추가해주는 작업
        populator.addScript(new ClassPathResource("db_schema.sql"));
        // DatabasePopulatorUtils : DataSource를 지정하여 자동으로 SQL 을 실행해주기 위한 작업[Class]
        // execute : SQL 구문을 수행해주기 위한 method
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }
}
