package com.team_three.base_check.pojo;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * (HardwareBaseline)实体类
 *
 * @author Charles_Yang
 * @since 2022-06-10 14:09:17
 */
@ApiModel(value = "HardwareBaseline",description = "$tableInfo.comment")
public class HardwareBaseline implements Serializable {
    private static final long serialVersionUID = -47118516525791306L;
    
	@ApiModelProperty(name = "machineguid",notes = "${column.comment}",dataType = "String",required = true)
    private String machineguid;
    
	@ApiModelProperty(name = "infoOs",notes = "${column.comment}",dataType = "String",required = true)
    private String infoOs;
    
	@ApiModelProperty(name = "infoVersion",notes = "${column.comment}",dataType = "String",required = true)
    private String infoVersion;
    
	@ApiModelProperty(name = "infoFullname",notes = "${column.comment}",dataType = "String",required = true)
    private String infoFullname;
    
	@ApiModelProperty(name = "infoOsArchitecture",notes = "${column.comment}",dataType = "String",required = true)
    private String infoOsArchitecture;
    
	@ApiModelProperty(name = "infoMuLanguages",notes = "${column.comment}",dataType = "String",required = true)
    private String infoMuLanguages;
    
	@ApiModelProperty(name = "infoSerialnumber",notes = "${column.comment}",dataType = "String",required = true)
    private String infoSerialnumber;
    
	@ApiModelProperty(name = "infoCpuCount",notes = "${column.comment}",dataType = "String",required = true)
    private String infoCpuCount;
    
	@ApiModelProperty(name = "infoMainboard",notes = "${column.comment}",dataType = "String",required = true)
    private String infoMainboard;
    
	@ApiModelProperty(name = "infoBoardModel",notes = "${column.comment}",dataType = "String",required = true)
    private String infoBoardModel;
    
	@ApiModelProperty(name = "infoSystemtype",notes = "${column.comment}",dataType = "String",required = true)
    private String infoSystemtype;
    
	@ApiModelProperty(name = "infoPhysicalMemory",notes = "${column.comment}",dataType = "String",required = true)
    private String infoPhysicalMemory;
    
	@ApiModelProperty(name = "infoCpuName",notes = "${column.comment}",dataType = "String",required = true)
    private String infoCpuName;
    
	@ApiModelProperty(name = "infoClockSpeed",notes = "${column.comment}",dataType = "String",required = true)
    private String infoClockSpeed;
    
	@ApiModelProperty(name = "infoNumberCore",notes = "${column.comment}",dataType = "String",required = true)
    private String infoNumberCore;
    
	@ApiModelProperty(name = "infoDataWidth",notes = "${column.comment}",dataType = "String",required = true)
    private String infoDataWidth;
    
	@ApiModelProperty(name = "infoSocketDesigination",notes = "${column.comment}",dataType = "String",required = true)
    private String infoSocketDesigination;
    
	@ApiModelProperty(name = "infoL2Cache",notes = "${column.comment}",dataType = "String",required = true)
    private String infoL2Cache;
    
	@ApiModelProperty(name = "infoL3Cache",notes = "${column.comment}",dataType = "String",required = true)
    private String infoL3Cache;


    public String getMachineguid() {
        return machineguid;
    }

    public void setMachineguid(String machineguid) {
        this.machineguid = machineguid;
    }
        
    public String getInfoOs() {
        return infoOs;
    }

    public void setInfoOs(String infoOs) {
        this.infoOs = infoOs;
    }
        
    public String getInfoVersion() {
        return infoVersion;
    }

    public void setInfoVersion(String infoVersion) {
        this.infoVersion = infoVersion;
    }
        
    public String getInfoFullname() {
        return infoFullname;
    }

    public void setInfoFullname(String infoFullname) {
        this.infoFullname = infoFullname;
    }
        
    public String getInfoOsArchitecture() {
        return infoOsArchitecture;
    }

    public void setInfoOsArchitecture(String infoOsArchitecture) {
        this.infoOsArchitecture = infoOsArchitecture;
    }
        
    public String getInfoMuLanguages() {
        return infoMuLanguages;
    }

    public void setInfoMuLanguages(String infoMuLanguages) {
        this.infoMuLanguages = infoMuLanguages;
    }
        
    public String getInfoSerialnumber() {
        return infoSerialnumber;
    }

    public void setInfoSerialnumber(String infoSerialnumber) {
        this.infoSerialnumber = infoSerialnumber;
    }
        
    public String getInfoCpuCount() {
        return infoCpuCount;
    }

    public void setInfoCpuCount(String infoCpuCount) {
        this.infoCpuCount = infoCpuCount;
    }
        
    public String getInfoMainboard() {
        return infoMainboard;
    }

    public void setInfoMainboard(String infoMainboard) {
        this.infoMainboard = infoMainboard;
    }
        
    public String getInfoBoardModel() {
        return infoBoardModel;
    }

    public void setInfoBoardModel(String infoBoardModel) {
        this.infoBoardModel = infoBoardModel;
    }
        
    public String getInfoSystemtype() {
        return infoSystemtype;
    }

    public void setInfoSystemtype(String infoSystemtype) {
        this.infoSystemtype = infoSystemtype;
    }
        
    public String getInfoPhysicalMemory() {
        return infoPhysicalMemory;
    }

    public void setInfoPhysicalMemory(String infoPhysicalMemory) {
        this.infoPhysicalMemory = infoPhysicalMemory;
    }
        
    public String getInfoCpuName() {
        return infoCpuName;
    }

    public void setInfoCpuName(String infoCpuName) {
        this.infoCpuName = infoCpuName;
    }
        
    public String getInfoClockSpeed() {
        return infoClockSpeed;
    }

    public void setInfoClockSpeed(String infoClockSpeed) {
        this.infoClockSpeed = infoClockSpeed;
    }
        
    public String getInfoNumberCore() {
        return infoNumberCore;
    }

    public void setInfoNumberCore(String infoNumberCore) {
        this.infoNumberCore = infoNumberCore;
    }
        
    public String getInfoDataWidth() {
        return infoDataWidth;
    }

    public void setInfoDataWidth(String infoDataWidth) {
        this.infoDataWidth = infoDataWidth;
    }
        
    public String getInfoSocketDesigination() {
        return infoSocketDesigination;
    }

    public void setInfoSocketDesigination(String infoSocketDesigination) {
        this.infoSocketDesigination = infoSocketDesigination;
    }
        
    public String getInfoL2Cache() {
        return infoL2Cache;
    }

    public void setInfoL2Cache(String infoL2Cache) {
        this.infoL2Cache = infoL2Cache;
    }
        
    public String getInfoL3Cache() {
        return infoL3Cache;
    }

    public void setInfoL3Cache(String infoL3Cache) {
        this.infoL3Cache = infoL3Cache;
    }

}