package org.ai.basic.common.domain.dto.req;

import lombok.Data;

@Data
public class UserCreateReqDTO {

    private String username;

    private String password;

    private String email;

    private String phone;

    private String nickname;

}