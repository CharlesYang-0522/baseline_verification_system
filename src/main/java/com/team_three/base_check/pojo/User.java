package com.team_three.base_check.pojo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * (User)实体类
 *
 * @author Charles_Yang
 * @since 2022-06-09 22:47:08
 */
@ApiModel(value = "User",description = "$tableInfo.comment")
public class User implements Serializable {
    private static final long serialVersionUID = -17289600959719238L;
    
	@ApiModelProperty(name = "id",notes = "${column.comment}",dataType = "Integer",required = true)
    private Integer id;
    
	@ApiModelProperty(name = "username",notes = "${column.comment}",dataType = "String",required = true)
    private String username;
    
	@ApiModelProperty(name = "password",notes = "${column.comment}",dataType = "String",required = true)
    private String password;
    
	@ApiModelProperty(name = "role",notes = "${column.comment}",dataType = "String",required = true)
    private String role;

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
        
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}