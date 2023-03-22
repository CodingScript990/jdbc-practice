package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;

// RowMapper Interface ->
public interface RowMapper {
    // Object 를 받아오기 위한 작업
    Object map(ResultSet rs) throws SQLException;
}
