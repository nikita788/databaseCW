package com.course.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@NamedStoredProcedureQuery(
        name = UserEntity.UserAuth,
        procedureName = "USER_AUTH",
        parameters = {
                @StoredProcedureParameter(type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, mode = ParameterMode.OUT)
        }
)
@Entity
@Table(name = "USERS")
public class UserEntity {

    public static final String UserAuth = "userAuth";

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASS_HASH")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
