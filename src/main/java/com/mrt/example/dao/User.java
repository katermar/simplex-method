package com.mrt.example.dao;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "users_table", schema = "users")
public class User {

    private Integer id;

    private String username;

    private @JsonIgnore String password;

//    @Column (name = "role") // there i want to restrict values that @param role can take USER_ROLE and ADMIN_ROLE
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    @Column(name = "username", nullable = false, length = 45)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "role", nullable = true, length = 45)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    @Override

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        User user = (User) o;
//
//        if (!id.equals(user.id)) return false;
//        if (!username.equals(user.username)) return false;
//        if (!password.equals(user.password)) return false;
//        return role.equals(user.role);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id.hashCode();
//        result = 31 * result + username.hashCode();
//        result = 31 * result + password.hashCode();
//        result = 31 * result + role.hashCode();
//        return result;
//    }
}

