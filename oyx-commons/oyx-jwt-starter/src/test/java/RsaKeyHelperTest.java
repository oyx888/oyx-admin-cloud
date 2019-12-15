import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

import cn.hutool.core.io.FileUtil;
import lombok.SneakyThrows;

/**
 * @author OYX
 * @date 2019-12-13 16:22
 */
public class RsaKeyHelperTest {

    @SneakyThrows
    public static void main(String[] args){
        //自定义 随机密码,  请修改这里
        String password = "oyx$%^@#";

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(password.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();

        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();

        FileUtil.writeBytes(publicKeyBytes, "F:\\xuexi\\oyx-admin-cloud\\oyx-commons\\oyx-jwt-starter\\src/main/resources/pub.key");
        FileUtil.writeBytes(privateKeyBytes, "F:\\xuexi\\oyx-admin-cloud\\oyx-commons\\oyx-jwt-starter\\src/main/resources/pri.key");
    }
}
