package model.organization;

import util.annotation.UserTree;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wojciech on 2015-08-12.
 */
@Entity
@Table(name = "users", schema = "public")
public class User {
    @UserTree
    @Id
    @SequenceGenerator(name="user_seq", sequenceName="user_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_seq")
    @Column(name = "user_id", nullable = false, insertable = true, updatable = true)
    private long userId;
    @UserTree
    @Basic
    @Column(name = "username", nullable = false, insertable = true, updatable = true, length = 30)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, insertable = true, updatable = true, length = 30)
    private String password;
    @UserTree
    @Basic
    @Column(name = "email", nullable = false, insertable = true, updatable = true, length = 100)
    private String email;
    @UserTree
    @Basic
    @Column(name = "last_login", nullable = true, insertable = true, updatable = true)
    private Timestamp lastLogin;
    @UserTree
    @OneToMany(mappedBy = "user", fetch=FetchType.EAGER)
    private Set<Employee> employees = new HashSet<Employee>();

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return userId;
    }

    public void setId(Long userId) {
        this.userId = userId;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
