package com.team_three.base_check.pojo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * (AccountBaseline)实体类
 *
 * @author Charles_Yang
 * @since 2022-06-10 16:10:35
 */
@ApiModel(value = "AccountBaseline",description = "$tableInfo.comment")
public class AccountBaseline implements Serializable {
    private static final long serialVersionUID = 194931084984204003L;
    
	@ApiModelProperty(name = "caption",notes = "${column.comment}",dataType = "String",required = true)
    private String caption;
    
	@ApiModelProperty(name = "mac",notes = "${column.comment}",dataType = "String",required = true)
    private String mac;
    
	@ApiModelProperty(name = "description",notes = "${column.comment}",dataType = "String",required = true)
    private String description;
    
	@ApiModelProperty(name = "domain",notes = "${column.comment}",dataType = "String",required = true)
    private String domain;
    
	@ApiModelProperty(name = "name",notes = "${column.comment}",dataType = "String",required = true)
    private String name;
    
	@ApiModelProperty(name = "passwordrequired",notes = "${column.comment}",dataType = "String",required = true)
    private String passwordrequired;
    
	@ApiModelProperty(name = "sid",notes = "${column.comment}",dataType = "String",required = true)
    private String sid;
    
	@ApiModelProperty(name = "status",notes = "${column.comment}",dataType = "String",required = true)
    private String status;

        
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
        
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
        
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
        
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
        
    public String getPasswordrequired() {
        return passwordrequired;
    }

    public void setPasswordrequired(String passwordrequired) {
        this.passwordrequired = passwordrequired;
    }
        
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
        
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}