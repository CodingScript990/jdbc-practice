package org.example;

import java.sql.PreparedStatement;
import java.sql.SQLException;

// PreparedStatementSetter Interface -> JdbcTemplate
public interface PreparedStatementSetter {
    // PreparedStatement 를 받아서 Setting 을 하겠다는 의미임
    void setter(PreparedStatement ps) throws SQLException;
}
