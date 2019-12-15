import com.github.oyx.auth.utils.JwtHelper;
import com.github.oyx.auth.utils.JwtUserInfo;
import com.github.oyx.auth.utils.Token;

/**
 * jwt 生成和解析 测试类
 * @author OYX
 * @date 2019-12-15 14:23
 */
public class JwtHelperTest {
    public static void main(String[] args){
        JwtUserInfo jwtInfo = new JwtUserInfo(1L, "oyx", "132312", 1L, 1L);
        int expire = 7200;

        //生成Token  注意： 确保该模块 zuihou-jwt-starter/src/main/resources 目录下已经有了私钥
        Token token = JwtHelper.generateUserToken(jwtInfo, "pri.key", expire);
        System.out.println(token);

        //解析Token  注意： 确保该模块 zuihou-jwt-starter/src/main/resources 目录下已经有了公钥
        JwtUserInfo jwtFromToken = JwtHelper.getJwtFromToken(token.getToken(), "pub.key");
        System.out.println(jwtFromToken);
    }
}
