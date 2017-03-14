import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by kingsley.zhang on 2017/3/14.
 */
@SuppressWarnings("WeakerAccess")
public final class JsonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> T fromJson(String jsonString, Class<T> type) {
        try {
            return MAPPER.readValue(jsonString, type);
        } catch (IOException e) {
            LOGGER.warn("解析 json 异常. json = {}, type = {}", jsonString, type, e);
        }
        return null;
    }

    public static String toJson(Object o) {
        try {
            return MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            LOGGER.warn("Java 转 json 异常. object = {}", o, e);
        }
        return null;
    }

}