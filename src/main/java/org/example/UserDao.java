package org.example;

import java.sql.*;

// UserDao Class -> User Data Info
public class UserDao {

    /*
    // Create method add -> object [UserId, Password, UserName, UserEmail]
    public void create(User user) throws SQLException {
        // 1. Hard Coding 으로 user object 생성과정을 보여줌
        Connection con = null; // Connection 하는 값을 설정
        PreparedStatement ps = null; // PreparedStatement 하는 값을 설정

        try {
            // connection 을 불러옴
            con = ConnectionManager.getConnection();
            // SQL 문을 생성해주는 Query -> Table 에 값을 넣어주기 위해 설정한 data 수 만큼 설정함
            String sql = "INSERT INTO USERS VALUES (?,?,?,?)";
            // PreparedStatement 값이 con 에서 설정한 sql 문을 불러옴
            ps = con.prepareStatement(sql);
            // sql 값을 설정해주기 위해 기초적인 작업 -> values 값을 얻어오고 values 를 update 를 함
            // 1 인자는 usrId
            ps.setString(1, user.getUserId());
            // 2 인자는 userPassword
            ps.setString(2, user.getPassword());
            // 3 인자는 userName
            ps.setString(3, user.getName());
            // 4 인자는 userEmail
            ps.setString(4, user.getEmail());

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
     */

    // Setter 를 사용하므로써 가독성이 좋아지고, 라이브러리를 사용하여 간편하게 사용하고자 하는 것만 호출하여 사용하는 점에서 유지보수에도 용이함
    public void create(User user) throws SQLException {
        // Jdbc Template call
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        // SQL 문을 생성해주는 Query -> Table 에 값을 넣어주기 위해 설정한 data 수 만큼 설정함
        String sql = "INSERT INTO USERS VALUES (?,?,?,?)";
        // DataSource update -> setting 작업![user, sql, new PreparedStatementSetter]
        jdbcTemplate.executeUpdate(user ,sql, ps -> {
            // 1 인자는 usrId
            ps.setString(1, user.getUserId());
            // 2 인자는 userPassword
            ps.setString(2, user.getPassword());
            // 3 인자는 userName
            ps.setString(3, user.getName());
            // 4 인자는 userEmail
            ps.setString(4, user.getEmail());
        });
    }

    /*
    // 1. User Data FindByUserId method add -> UserId
    public User findByUserId(String userId) throws SQLException {
        // 1. Hard Coding 으로 user object 생성과정을 보여줌
        Connection con = null; // Connection 하는 값을 설정
        PreparedStatement ps = null; // PreparedStatement 하는 값을 설정
        ResultSet rs = null; // ResultSet 은 조회를 하기 위함임!

        // userId 를 조회하기 위한 작업
        try {
            // con 은 ConnectionManager Class 에 있는 getConnection method 를 불러옴!
            con = ConnectionManager.getConnection();
            // userId 로 조회하기 위한 SQL Query 문
            String sql = "SELECT userId, password, name, email FROM USERS WHERE userId = ?";
            // sql query  문을 연결해주기 위한 작업 -> 조회를 위한 작업(1)
            ps = con.prepareStatement(sql);
            // index 와 인자를 담아두는 작업 -> 조회하기 위한 작업(2)
            ps.setString(1, userId);

            // 지역변수에 query 문을 담아줌
            rs = ps.executeQuery();

            // User constructor -> null
            User user = null;
            // DB의 결과값이 있다면 userId 를 받아오는 작업
            if (rs.next()) {
                // user 를 활용하여 userId, userPassword, userName, userEmail value 를 받아옴
                user = new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
            // user value 반환해줌
            return user;
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
     */

    // 2. User Data FindByUserId method add -> UserId
    public User findByUserId(String userId) throws SQLException {
        // Jdbc Template Call
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        // userId 로 조회하기 위한 SQL Query 문
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userId = ?";
        // executeQuery method 를 불러 DataSource
        // setter 를 이용하여 userId 를 담아옴 -> select 하기 위함
        // map method 를 활용하여 User Object 의 값들을 얻어옴! -> package 화[간편하게 call 만하면 찾아오기 쉽게 하기 위함과 유지보수에도 용이함]
        return (User) jdbcTemplate.executeQuery(sql, ps ->
            // 1 번째 index 값에 userId 인자 값을 담기 위한 작업 -> userId 를 이미 set 하고 있기 때문에, 굳이 또 재사용할 필요가 없음
            ps.setString(1, userId),
                    rs ->  // user 를 활용하여 userId, userPassword, userName, userEmail value 를 받아옴
                            new User(
                            rs.getString("userId"),
                            rs.getString("password"),
                            rs.getString("name"),
                            rs.getString("email")
        ));
    }
}
