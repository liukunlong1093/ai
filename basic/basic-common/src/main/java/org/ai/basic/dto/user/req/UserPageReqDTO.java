package org.ai.basic.dto.user.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户分页查询请求")
public class UserPageReqDTO extends PageReqDTO {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "昵称")
    private String nickname;
}