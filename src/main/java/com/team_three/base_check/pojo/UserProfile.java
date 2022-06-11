package com.team_three.base_check.pojo;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * (UserProfile)实体类
 *
 * @author Charles_Yang
 * @since 2022-06-10 09:55:09
 */
@ApiModel(value = "UserProfile",description = "$tableInfo.comment")
public class UserProfile implements Serializable {
    private static final long serialVersionUID = 869296673997413440L;
    
	@ApiModelProperty(name = "id",notes = "${column.comment}",dataType = "Integer",required = true)
    private Integer id;
    
	@ApiModelProperty(name = "username",notes = "${column.comment}",dataType = "String",required = true)
    private String username;
    
	@ApiModelProperty(name = "mac",notes = "${column.comment}",dataType = "String",required = true)
    private String mac;
    
	@ApiModelProperty(name = "updateTime",notes = "${column.comment}",dataType = "Date",required = true)
    private Date updateTime;

        
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
        
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
        
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}