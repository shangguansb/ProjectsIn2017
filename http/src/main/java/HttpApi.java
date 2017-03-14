import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.qunar.base.meerkat.http.QunarHttpClient;
import com.qunar.base.meerkat.http.data.PostParameter;
import com.qunar.base.meerkat.http.tools.Interceptors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by kingsley.zhang on 2017/3/14.
 */
@SuppressWarnings("WeakerAccess")
public class HttpApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpApi.class);
    private static final String URL_GET_USER_INFO
            = "http://l-qmexp1.f.dev.cn6.qunar.com:10000/httpDemo/userToken/getUserInfo";
    private static final String URL_VALIDATE_TOKEN
            = "http://l-qmexp1.f.dev.cn6.qunar.com:10000/httpDemo/userToken/validateToken";

    private static final QunarHttpClient CLIENT;

    static {
        CLIENT = QunarHttpClient.createDefaultClient();
        CLIENT.allowCookiePolicy();
        Interceptors.gzip(CLIENT, true);

        LOGGER.debug("CLIENT = {}", CLIENT);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                CLIENT.close();
                LOGGER.debug("关闭 CLIENT.");
            }
        });
    }

    public HttpResult<String> getUserInfo(String userName) throws IOException {
        Preconditions.checkNotNull(userName, "userName must be not null.");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(userName), "userName must not be empty");
        return JsonUtil.fromJson(CLIENT.httpGet(URL_GET_USER_INFO + "?userName=" + userName), HttpResult.class);
    }

    public HttpResult<String> validateToken(String userName, String userToken) throws IOException {
        Preconditions.checkNotNull(userName, "userName must be not null.");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(userName), "userName must not be empty");
        Preconditions.checkNotNull(userToken, "userToken must be not null.");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(userToken), "userToken must not be empty");
        return JsonUtil.fromJson(CLIENT.httpPost(URL_VALIDATE_TOKEN,
                new PostParameter().put("userName", userName).put("userToken", userToken)), HttpResult.class);
    }


}