package org.ai.basic.common.domain.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "分页查询通用响应")
public class PageResDTO<T> {

    @Schema(description = "总记录数")
    private long total;

    @Schema(description = "当前页数据")
    private List<T> records;

    public PageResDTO(long total, List<T> records) {
        this.total = total;
        this.records = records;
    }
}