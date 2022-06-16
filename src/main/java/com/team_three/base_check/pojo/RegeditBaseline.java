package com.team_three.base_check.pojo;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * (RegeditBaseline)实体类
 *
 * @author Charles_Yang
 * @since 2022-06-16 09:18:45
 */
@ApiModel(value = "RegeditBaseline",description = "$tableInfo.comment")
public class RegeditBaseline implements Serializable {
    private static final long serialVersionUID = 462729134342063793L;
    
	@ApiModelProperty(name = "machineguid",notes = "${column.comment}",dataType = "String",required = true)
    private String machineguid;
    
	@ApiModelProperty(name = "name",notes = "${column.comment}",dataType = "String",required = true)
    private String name;
    
	@ApiModelProperty(name = "state",notes = "${column.comment}",dataType = "String",required = true)
    private String state;
    
	@ApiModelProperty(name = "importance",notes = "${column.comment}",dataType = "String",required = true)
    private String importance;
    
	@ApiModelProperty(name = "standardvalue",notes = "${column.comment}",dataType = "String",required = true)
    private String standardvalue;
    
	@ApiModelProperty(name = "actualvalue",notes = "${column.comment}",dataType = "String",required = true)
    private String actualvalue;
    
	@ApiModelProperty(name = "comparemethod",notes = "${column.comment}",dataType = "String",required = true)
    private String comparemethod;
    
	@ApiModelProperty(name = "description",notes = "${column.comment}",dataType = "String",required = true)
    private String description;
    
	@ApiModelProperty(name = "updatetime",notes = "${column.comment}",dataType = "String",required = true)
    private String updatetime;

        
    public String getMachineguid() {
        return machineguid;
    }

    public void setMachineguid(String machineguid) {
        this.machineguid = machineguid;
    }
        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
        
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
        
    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }
        
    public String getStandardvalue() {
        return standardvalue;
    }

    public void setStandardvalue(String standardvalue) {
        this.standardvalue = standardvalue;
    }
        
    public String getActualvalue() {
        return actualvalue;
    }

    public void setActualvalue(String actualvalue) {
        this.actualvalue = actualvalue;
    }
        
    public String getComparemethod() {
        return comparemethod;
    }

    public void setComparemethod(String comparemethod) {
        this.comparemethod = comparemethod;
    }
        
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
        
    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

}