package org.ai.core.base.config.code;

/**
 * 定义了框架中最基础的成功与失败业务状态码。
 * <p>
 * 将这些基础状态码作为常量管理，可以提高代码的可读性，
 * 并便于在整个应用程序中统一成功与失败的判断标准。
 * <p>
 * 设计为 final class 并提供私有构造函数，是为了防止这个常量类被继承或实例化。
 */
public final class BooleanCode {

    /**
     * 私有化构造函数，确保该类不会被外部实例化。
     */
    private BooleanCode() {}

    /**
     * 操作成功的状态码。
     * 在 Response 对象中，当 code 等于此值时，success 字段将为 true。
     * 注意：原始代码中使用的是 1，但业界更常见的是使用 200 (HTTP OK)。您可以根据团队规范调整。
     */
    public static final int SUCCESS_1 = 1;

    /**
     * 默认的操作失败状态码。
     * 当一个操作失败但没有指定具体的业务错误码时，可以使用此默认值。
     */
    public static final int FAILURE_0 = 0;

}
