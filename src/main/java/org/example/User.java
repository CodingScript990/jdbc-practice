package org.example;

import java.util.Objects;

// User Class -> Object[DB Data]
public class User {
    // Field add -> data 초기값
    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    // User method -> Object's[userId, userPassword, UserName, UserEmail]
    // Schema 에서 설정한 대로 들고옴
    public User(String userId, String password, String name, String email) {
        // 인자를 받아오는 작업 -> userId ,password ,name ,email
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    // Getter Add
    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // String type 을 비교할려면 Hash Code, Equals method 가 필요함!
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(password, user.password) && Objects.equals(name, user.name) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, password, name, email);
    }
}
