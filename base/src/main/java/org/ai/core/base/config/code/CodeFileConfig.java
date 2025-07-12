package org.ai.core.base.config.code;
import lombok.extern.slf4j.Slf4j;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 错误码与对应消息的配置文件加载与管理类。
 * <p>
 * 此类负责在服务启动时从指定的 properties 文件中加载所有的 code-message 映射关系，
 * 并提供静态方法供系统在需要时根据 code 获取对应的提示消息。
 */
@Slf4j
public final class CodeFileConfig {

    /**
     * 指定错误码配置文件的路径，该文件应位于 classpath 的根目录下。
     */
    private static final String CODE_FILE_PATH = "/error-codes.properties";

    /**
     * 使用 ConcurrentHashMap 存储错误码和消息的映射，保证线程安全。
     * Key: 错误码 (String)
     * Value: 错误消息 (String)
     */
    private static final Map<String, String> CODE_MESSAGE_MAP = new ConcurrentHashMap<>();

    // 静态初始化区块：在类被加载时执行一次，用于加载配置文件。
    static {
        Properties properties = new Properties();
        try (InputStream is = CodeFileConfig.class.getResourceAsStream(CODE_FILE_PATH);
             // 使用 UTF-8 编码读取，避免中文乱码
             InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {

            if (is == null) {
                log.error("错误码配置文件未找到，路径: {}", CODE_FILE_PATH);
                throw new IllegalStateException("Cannot find " + CODE_FILE_PATH + " on classpath");
            }

            properties.load(reader);
            // 将 Properties 的内容转存到我们的 Map 中
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                CODE_MESSAGE_MAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
            log.info("成功加载错误码配置文件: {}", CODE_FILE_PATH);

        } catch (Exception e) {
            log.error("加载错误码配置文件 {} 时发生严重错误。", CODE_FILE_PATH, e);
            // 在生产环境中，如果核心配置文件加载失败，应考虑快速失败，阻止服务启动。
            throw new RuntimeException("Failed to load error code configuration.", e);
        }
    }

    /**
     * 私有化构造函数，防止外部实例化此工具类。
     */
    private CodeFileConfig() {}

    /**
     * 根据错误码获取对应的提示消息。
     *
     * @param code 错误码
     * @return 对应的提示消息。如果找不到，则直接返回 code 本身。
     */
    public static String getValue(String code) {
        return CODE_MESSAGE_MAP.getOrDefault(code, code);
    }

    /**
     * 根据错误码获取对应的消息模板，并使用提供的参数替换占位符。
     * <p>
     * 示例：
     * 文件中配置: user.not.found=用户 {username} 不存在。
     * 调用: getValue("user.not.found", Map.of("username", "Alice"))
     * 返回: "用户 Alice 不存在。"
     *
     * @param code 错误码
     * @param args 用于替换占位符的参数 Map
     * @return 格式化后的提示消息
     */
    public static String getValue(String code, Map<String, String> args) {
        String messageTemplate = getValue(code);
        if (args == null || args.isEmpty()) {
            return messageTemplate;
        }

        // 简单的占位符替换 {key}
        for (Map.Entry<String, String> entry : args.entrySet()) {
            messageTemplate = messageTemplate.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return messageTemplate;
    }
}
