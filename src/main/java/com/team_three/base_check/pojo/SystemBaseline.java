package com.team_three.base_check.pojo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * (SystemBaseline)实体类
 *
 * @author Charles_Yang
 * @since 2022-06-10 17:06:01
 */
@ApiModel(value = "SystemBaseline",description = "$tableInfo.comment")
public class SystemBaseline implements Serializable {
    private static final long serialVersionUID = 825276735426771318L;
    
	@ApiModelProperty(name = "machineguid",notes = "${column.comment}",dataType = "String",required = true)
    private String machineguid;
    
	@ApiModelProperty(name = "oscaption",notes = "${column.comment}",dataType = "String",required = true)
    private String oscaption;
    
	@ApiModelProperty(name = "osversion",notes = "${column.comment}",dataType = "String",required = true)
    private String osversion;
    
	@ApiModelProperty(name = "diskcaption",notes = "${column.comment}",dataType = "String",required = true)
    private String diskcaption;
    
	@ApiModelProperty(name = "interfacetype",notes = "${column.comment}",dataType = "String",required = true)
    private String interfacetype;
    
	@ApiModelProperty(name = "networkcaption",notes = "${column.comment}",dataType = "String",required = true)
    private String networkcaption;
    
	@ApiModelProperty(name = "ipaddress",notes = "${column.comment}",dataType = "String",required = true)
    private String ipaddress;
    
	@ApiModelProperty(name = "macaddress",notes = "${column.comment}",dataType = "String",required = true)
    private String macaddress;
    
	@ApiModelProperty(name = "updatehotfixid",notes = "${column.comment}",dataType = "String",required = true)
    private String updatehotfixid;
    
	@ApiModelProperty(name = "installedon",notes = "${column.comment}",dataType = "String",required = true)
    private String installedon;


    public String getMachineguid() {
        return machineguid;
    }

    public void setMachineguid(String machineguid) {
        this.machineguid = machineguid;
    }
        
    public String getOscaption() {
        return oscaption;
    }

    public void setOscaption(String oscaption) {
        this.oscaption = oscaption;
    }
        
    public String getOsversion() {
        return osversion;
    }

    public void setOsversion(String osversion) {
        this.osversion = osversion;
    }
        
    public String getDiskcaption() {
        return diskcaption;
    }

    public void setDiskcaption(String diskcaption) {
        this.diskcaption = diskcaption;
    }
        
    public String getInterfacetype() {
        return interfacetype;
    }

    public void setInterfacetype(String interfacetype) {
        this.interfacetype = interfacetype;
    }
        
    public String getNetworkcaption() {
        return networkcaption;
    }

    public void setNetworkcaption(String networkcaption) {
        this.networkcaption = networkcaption;
    }
        
    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }
        
    public String getMacaddress() {
        return macaddress;
    }

    public void setMacaddress(String macaddress) {
        this.macaddress = macaddress;
    }
        
    public String getUpdatehotfixid() {
        return updatehotfixid;
    }

    public void setUpdatehotfixid(String updatehotfixid) {
        this.updatehotfixid = updatehotfixid;
    }
        
    public String getInstalledon() {
        return installedon;
    }

    public void setInstalledon(String installedon) {
        this.installedon = installedon;
    }

}