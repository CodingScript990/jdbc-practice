package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// JDBC Template[Library] -> connection, preparedStatement call
public class JdbcTemplate {
    // executeUpdate method add -> object [UserId, Password, UserName, UserEmail]
    public void executeUpdate(User user, String sql, PreparedStatementSetter pss) throws SQLException {
        // 1. Hard Coding 으로 user object 생성과정을 보여줌
        Connection con = null; // Connection 하는 값을 설정
        PreparedStatement ps = null; // PreparedStatement 하는 값을 설정

        try {
            // connection 을 불러옴
            con = ConnectionManager.getConnection();
            // 외부로 부터 SQL 을 받아오는 작업
            // PreparedStatement 값이 con 에서 설정한 sql 문을 불러옴
            ps = con.prepareStatement(sql);
            // 값을 전달해주는 역할만 수행하도록 작업함!
            pss.setter(ps);

            // Data Update
            ps.executeUpdate();
        } finally {
            // ps data 들이 null 값이 아니라면 조건문 실행
            if (ps != null) {
                // 조건에 해당되면 close method 실행
                ps.close();
            }
            // connection 도 null 값이 아니라면 종료해줘라!
            if (con != null) {
                con.close();
            }
        }
    }

    // ExecuteQuery -> userId 필요없음! 이유는 이미 ps.setString(1, userId) 로 받아오기 때문임
    public Object executeQuery(String sql, PreparedStatementSetter pss, RowMapper rowMapper) throws SQLException {
        // 1. Hard Coding 으로 user object 생성과정을 보여줌
        Connection con = null; // Connection 하는 값을 설정
        PreparedStatement ps = null; // PreparedStatement 하는 값을 설정
        ResultSet rs = null; // ResultSet 은 조회를 하기 위함임!

        // userId 를 조회하기 위한 작업
        try {
            // con 은 ConnectionManager Class 에 있는 getConnection method 를 불러옴!
            con = ConnectionManager.getConnection();
            // sql query  문을 연결해주기 위한 작업 -> 조회를 위한 작업(1)
            ps = con.prepareStatement(sql);
            // 몇개의 PreparedStatement 를 받아 울지 모르기에 받아 올 수 있는 setter method add
            pss.setter(ps);

            // 지역변수에 query 문을 담아줌
            rs = ps.executeQuery();

            // Object constructor -> null
            // Object 로 하는 이유는 User 종속이 걸리기 때문에 이렇게 작업하는 이유임
            Object obj = null;
            // DB의 결과값이 있다면 userId 를 받아오는 작업
            if (rs.next()) {
                return rowMapper.map(rs);
            }
            // object value 반환해줌
            return obj;
        } finally {
            // resultSet 이 null 이 아니면?
            if (rs != null) {
                rs.close();
            }
            // prepareStatement 이 null 이 아니라면?
            if (ps != null) {
                ps.close();
            }
            // connection 이 null 이 아니라면?
            if (con != null) {
                con.close();
            }
        }
    }
}
