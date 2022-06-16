package com.team_three.base_check.vo;

import lombok.Data;

@Data
public class UserRegeditVO {
    private String username;

    private String machineguid;

    private Integer baseline;

    private Integer danger;

    private Integer safe;
}
