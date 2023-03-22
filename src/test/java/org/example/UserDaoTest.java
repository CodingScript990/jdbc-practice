package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

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

    // DB Schema SQL 작업
    // CreateTest Method add
    @Test
    void createTest() throws SQLException {
        // UserDao Constructor call -> Data Access Object 의 뜻임
        UserDao userDao = new UserDao();
        // User Data add -> DB Schema Create
        // userDao 에서 create method 를 활용해서 User 라는 생성자를 통해 Data 를 생성해주는 작업
        userDao.create(new User("pay", "password", "name", "email"));
        // 해당 UserId 정보를 찾아옴!
        User user = userDao.findByUserId("pay");
        // user 의 정보가 생성한 user 의 정보와 일치한가를 test
        assertThat(user).isEqualTo(new User("pay", "password", "name", "email"));
    }
}
