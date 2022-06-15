package com.team_three.base_check.vo;

import lombok.Data;

@Data
public class UserHardwareVO {

    private String username;

    private String machineguid;

    private String infoCpuName;

    private String infoBoardModel;

    private String infoPhysicalMemory;
}
