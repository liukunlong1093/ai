package org.ai.basic.common.domain.dto.res;

import lombok.Data;

import java.util.Date;

@Data
public class BaseUserResDTO {

    private Long id;

    private String username;

    private String email;

    private String phone;

    private String nickname;

    private String avatar;

    private Integer status;

    private Date createTime;

}