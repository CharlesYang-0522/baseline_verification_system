package com.team_three.base_check.pojo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * (ShadowBaseline)实体类
 *
 * @author Charles_Yang
 * @since 2022-06-17 10:31:46
 */
@ApiModel(value = "ShadowBaseline",description = "$tableInfo.comment")
public class ShadowBaseline implements Serializable {
    private static final long serialVersionUID = 358056270980015840L;
    
	@ApiModelProperty(name = "machineguid",notes = "${column.comment}",dataType = "String",required = true)
    private String machineguid;
    
	@ApiModelProperty(name = "detect",notes = "${column.comment}",dataType = "String",required = true)
    private String detect;
    
	@ApiModelProperty(name = "shadowuser",notes = "${column.comment}",dataType = "String",required = true)
    private String shadowuser;
    
	@ApiModelProperty(name = "updatetime",notes = "${column.comment}",dataType = "String",required = true)
    private String updatetime;

        
    public String getMachineguid() {
        return machineguid;
    }

    public void setMachineguid(String machineguid) {
        this.machineguid = machineguid;
    }
        
    public String getDetect() {
        return detect;
    }

    public void setDetect(String detect) {
        this.detect = detect;
    }
        
    public String getShadowuser() {
        return shadowuser;
    }

    public void setShadowuser(String shadowuser) {
        this.shadowuser = shadowuser;
    }
        
    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

}