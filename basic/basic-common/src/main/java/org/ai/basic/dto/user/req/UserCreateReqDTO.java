package org.ai.basic.dto.user.req;

import lombok.Data;

@Data
public class UserCreateReqDTO {

    private String username;

    private String password;

    private String email;

    private String phone;

    private String nickname;

}