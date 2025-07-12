package org.ai.basic.common.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户自定义查询请求")
public class UserQueryReqDTO {

    @Schema(description = "查询条件，格式为JSON字符串")
    private String query;

    @Schema(description = "排序字段")
    private String sort;
}