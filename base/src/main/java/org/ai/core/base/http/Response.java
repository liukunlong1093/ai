package org.ai.core.base.http;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.ai.core.base.config.code.BooleanCode;
import org.ai.core.base.config.code.CodeFileConfig;
import org.ai.core.base.exception.BaseException;

import java.io.Serializable;
import java.util.Map;


/**
 * 框架统一的API响应对象。
 * 使用泛型以适应不同类型的返回数据。
 *
 * @param <T> 响应数据的类型。
 */
@Schema(description = "统一API响应对象模型")
@Data
@Slf4j
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "业务编码。成功时固定为约定值（如200），失败时为具体的业务错误码。",
            requiredMode = Schema.RequiredMode.REQUIRED, // 明确标注此字段为必填
            example = "200")
    private int code;

    @Schema(description = "操作是否成功。根据 'code' 字段自动计算得出。",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "true")
    private boolean success;

    @Schema(description = "提示信息。用于向用户展示操作结果的简短描述。",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "操作成功")
    private String message;

    @Schema(description = "返回的业务数据。当操作成功且有数据返回时，此字段包含具体内容。")
    private T data;

    /**
     * 将构造函数私有化，强制使用静态工厂方法创建实例。
     * 这种设计模式可以确保对象状态的一致性，并提供更清晰的API。
     */
    private Response() {}

    private Response(int code, String message, T data) {
        this.code = code;
        // 根据业务编码判断操作是否成功。
        // 建议将成功的 code 定义为常量，例如 CommonCode.SUCCESS
        this.success = (code == BooleanCode.SUCCESS_1);
        this.message = message;
        this.data = data;
    }

    // --- 静态工厂方法 (Success) ---

    /**
     * 创建一个表示成功的响应，不包含数据。
     *
     * @return 成功的响应对象。
     */
    public static <T> Response<T> success() {
        return new Response<>(BooleanCode.SUCCESS_1, "操作成功", null);
    }

    /**
     * 创建一个表示成功的响应，并携带数据。
     *
     * @param data 业务数据。
     * @return 成功的响应对象。
     */
    public static <T> Response<T> success(T data) {
        return new Response<>(BooleanCode.SUCCESS_1, "操作成功", data);
    }

    /**
     * 创建一个表示成功的响应，携带数据和自定义消息。
     *
     * @param data    业务数据。
     * @param message 自定义成功消息。
     * @return 成功的响应对象。
     */
    public static <T> Response<T> success(T data, String message) {
        return new Response<>(BooleanCode.SUCCESS_1, message, data);
    }



    /**
     * 创建一个表示失败的响应，使用默认的错误码和消息。
     *
     * @return 失败的响应对象。
     */
    public static <T> Response<T> fail() {
        String message = CodeFileConfig.getValue(String.valueOf(BooleanCode.FAILURE_0));
        return new Response<>(BooleanCode.FAILURE_0, message, null);
    }

    /**
     * 创建一个表示失败的响应，根据错误码自动查找对应的消息。
     *
     * @param code 错误码。
     * @return 失败的响应对象。
     */
    public static <T> Response<T> fail(int code) {
        String message = CodeFileConfig.getValue(String.valueOf(code));
        return new Response<>(code, message, null);
    }

    /**
     * 创建一个表示失败的响应，使用指定的错误码和自定义消息。
     *
     * @param code    错误码。
     * @param message 自定义错误消息。
     * @return 失败的响应对象。
     */
    public static <T> Response<T> fail(int code, String message) {
        return new Response<>(code, message, null);
    }

    /**
     * 创建一个表示失败的响应，用于动态替换消息模板中的占位符。
     *
     * @param code   错误码。
     * @param params 用于替换消息模板占位符的参数Map。
     * @return 失败的响应对象。
     */
    public static <T> Response<T> fail(int code, Map<String, String> params) {
        String message = CodeFileConfig.getValue(String.valueOf(code), params);
        return new Response<>(code, message, null);
    }


    // --- 实例方法 ---

    /**
     * 检查响应是否成功，如果失败，则抛出包含错误信息的 {@link BaseException}。
     * 方法名清晰地表达了其行为：“获取数据，否则抛出异常”。
     * 主要用于服务消费者（客户端）调用API后，进行结果校验。
     *
     * @return 如果成功，返回业务数据 T。
     * @throws BaseException 如果响应是失败状态。
     */
    public T getDataOrThrow() throws BaseException {
        if (!this.success) {
            log.warn("API调用校验失败 -> code: {}, message: {}", this.code, this.message);
            throw new BaseException(this.code, this.message);
        }
        return this.data;
    }

    /**
     * 检查响应是否成功，如果失败，则返回 null。
     * 适用于不关心失败具体原因，只关心是否成功获取到数据的场景。
     *
     * @return 如果成功，返回业务数据 T；否则返回 null。
     */
    public T getDataOrNull() {
        if (!this.success) {
            log.warn("API调用失败，将返回null -> code: {}, message: {}", this.code, this.message);
            return null;
        }
        return this.data;
    }
}