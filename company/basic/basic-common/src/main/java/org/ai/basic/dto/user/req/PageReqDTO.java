package org.ai.basic.dto.user.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "分页查询基础请求")
public class PageReqDTO {

    @Schema(description = "页码", example = "1")
    private int page = 1;

    @Schema(description = "每页数量", example = "10")
    private int size = 10;
}