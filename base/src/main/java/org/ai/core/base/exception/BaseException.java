package org.ai.core.base.exception;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * 框架统一的检查型异常基类 (Checked Exception)。
 * <p>
 * 所有需要调用方强制处理的业务异常都应继承此类。
 * 它封装了业务错误码(code)和详细描述(desc)，用于服务间的错误传递和统一异常处理。
 */
public class BaseException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * 业务错误码，用于前后端交互和问题定位。
     * 设为 final，确保异常对象一旦创建就不会被修改，增强不可变性。
     */
    private final int code;

    /**
     * 错误的详细描述信息。
     * final 保证不可变性。
     */
    private final String desc;


    /**
     * 主构造函数，所有其他构造函数最终都应调用它。
     *
     * @param code      业务错误码。
     * @param desc      错误描述。
     * @param cause     原始的 cause 异常，用于异常链追踪。
     */
    public BaseException(int code, String desc, Throwable cause) {
        // 调用父类构造函数，传入原始异常，保留完整的异常堆栈信息。
        super(cause);
        this.code = code;
        this.desc = desc;
    }

    /**
     * 构造一个带有错误码和描述的异常。
     *
     * @param code 业务错误码。
     * @param desc 错误描述。
     */
    public BaseException(int code, String desc) {
        this(code, desc, null);
    }

    /**
     * 使用另一个异常来构造业务异常，用于异常包装。
     *
     * @param code  业务错误码。
     * @param cause 原始异常。
     */
    public BaseException(int code, Throwable cause) {
        // 如果只提供了 cause，则错误描述尝试从 cause 中获取。
        this(code, cause != null ? cause.getMessage() : null, cause);
    }

    /**
     * 获取业务错误码。
     * @return 错误码。
     */
    public int getCode() {
        return code;
    }

    /**
     * 获取错误描述信息。
     * @return 错误描述。
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 重写 getMessage() 方法，提供包含错误码的、更具可读性的日志信息。
     *
     * @return 返回格式如 "[CODE:1001] 用户名不能为空" 的字符串。
     */
    @Override
    public String getMessage() {
        // 如果 desc 为空，则使用父类（通常是 cause）的 message。
        String message = StringUtils.isNotBlank(desc) ? desc : super.getMessage();
        return String.format("[CODE:%d] %s", code, message);
    }

    /**
     * 获取完整的异常堆栈信息字符串。
     *
     * @return 堆栈信息字符串。
     */
    public String getStackMessage() {
        return ExceptionUtils.getStackTrace(this);
    }
}
