import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by kingsley.zhang on 2017/3/14.
 */
public class Http {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpApi.class);

    public static void main(String[] args) {
        HttpApi api = new HttpApi();
        try {
            String userName = "kingsley.zhang";
            HttpResult<String> userInfo = api.getUserInfo(userName);
            LOGGER.debug("userInfo = {}", JsonUtil.toJson(userInfo));
            HttpResult<String> validateResult = api.validateToken(userName, userInfo.getData());
            LOGGER.debug("validate result = {}", JsonUtil.toJson(validateResult));
        } catch (IOException e) {
            LOGGER.warn("IOException", e);
        }
    }
}
