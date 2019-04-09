package com.zyz.hawkeye.http;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HawkeyeResponse<T> {
    private T data;

    @ApiModelProperty("是否成功")
    private Boolean result;

    private String errorMsg;

    public static<T> HawkeyeResponse<T> success(T data) {
        return new HawkeyeResponse<>(data, true, null);
    }

    public static<T> HawkeyeResponse<T> error(String errorMsg) {
        return new HawkeyeResponse<>(null, false, errorMsg);
    }
}
