import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.oyx.generator.CodeGenerator;
import com.github.oyx.generator.config.CodeGeneratorConfig;
import com.github.oyx.generator.config.FileCreateConfig;
import com.github.oyx.generator.type.EntityFiledType;
import com.github.oyx.generator.type.EntityType;
import com.github.oyx.generator.type.GenerateType;

/**
 * 测试代码生成权限系统的代码
 *
 * @author oyx
 * @date 2019/05/25
 */
public class TestAuthorityGenerator {
    /***
     * 注意，想要在这里直接运行，需要手动增加 mysql 驱动
     * @param args
     */
    public static void main(String[] args) {
//        CodeGeneratorConfig build = buildDefaultsEntity();
//        CodeGeneratorConfig build = buildAuthSuperEntity();
        CodeGeneratorConfig build = buildAuthEntity();
//        CodeGeneratorConfig build = buildCommonEntity();
//        CodeGeneratorConfig build = buildCommonSuperEntity();
//        CodeGeneratorConfig build = buildCoreEntity();

        build.setUsername("root");
        build.setPassword("123456");
        System.out.println("输出路径：");
        System.out.println(System.getProperty("user.dir") + "/oyx-backend/oyx-authority");
        build.setProjectRootPath(System.getProperty("user.dir") + "/oyx-backend/oyx-authority");

        FileCreateConfig fileCreateConfig = new FileCreateConfig(null);
//        FileCreateConfig fileCreateConfig = new FileCreateConfig(GenerateType.OVERRIDE);
        fileCreateConfig.setGenerateEntity(GenerateType.OVERRIDE);
        fileCreateConfig.setGenerateEnum(GenerateType.OVERRIDE);
        fileCreateConfig.setGenerateDto(GenerateType.OVERRIDE);
        fileCreateConfig.setGenerateXml(GenerateType.OVERRIDE);
        fileCreateConfig.setGenerateDao(GenerateType.IGNORE);
        fileCreateConfig.setGenerateServiceImpl(GenerateType.IGNORE);
        fileCreateConfig.setGenerateService(GenerateType.IGNORE);
        fileCreateConfig.setGenerateController(GenerateType.IGNORE);
        build.setFileCreateConfig(fileCreateConfig);

        //手动指定枚举类 生成的路径
        Set<EntityFiledType> filedTypes = new HashSet<>();
        filedTypes.addAll(Arrays.asList(
                EntityFiledType.builder().name("httpMethod").table("c_common_opt_log")
                        .packagePath("com.github.oyx.common.enums.HttpMethod").gen(GenerateType.IGNORE).build()
                , EntityFiledType.builder().name("httpMethod").table("c_auth_resource")
                        .packagePath("com.github.oyx.common.enums.HttpMethod").gen(GenerateType.IGNORE).build()
                , EntityFiledType.builder().name("dsType").table("c_auth_role")
                        .packagePath("com.github.oyx.database.mybatis.auth.DataScopeType").gen(GenerateType.OVERRIDE).build()
        ));
        build.setFiledTypes(filedTypes);
        CodeGenerator.run(build);
    }

    public static CodeGeneratorConfig buildDefaultsEntity() {
        List<String> tables = Arrays.asList(
                "d_global_user",
                "d_tenant"
        );
        CodeGeneratorConfig build = CodeGeneratorConfig.
                build("authority", "", "oyx", "d_", tables);
        build.setSuperEntity(EntityType.ENTITY);
        build.setChildPackageName("defaults");
        build.setUrl("jdbc:mysql://127.0.0.1:3306/oyx_base_8888?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull");
        return build;
    }

    public static CodeGeneratorConfig buildAuthEntity() {
        List<String> tables = Arrays.asList(
//                "c_auth_application"
//                "c_auth_menu"
//                , "c_auth_micro_service"
//                , "c_auth_resource"
                "c_auth_role"
//                "c_auth_user"
        );
        CodeGeneratorConfig build = CodeGeneratorConfig.
                build("authority", "", "oyx", "c_auth_", tables);
        build.setSuperEntity(EntityType.ENTITY);
        build.setChildPackageName("auth");
        build.setUrl("jdbc:mysql://127.0.0.1:3306/oyx_base_0000?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull");
        return build;
    }

    public static CodeGeneratorConfig buildAuthSuperEntity() {
        List<String> tables = Arrays.asList(
                "c_auth_role_authority"
                , "c_auth_role_org"
                , "c_auth_user_role"
        );
        CodeGeneratorConfig build = CodeGeneratorConfig.
                build("authority", "", "oyx", "c_auth_", tables);
        build.setSuperEntity(EntityType.SUPER_ENTITY);
        build.setChildPackageName("auth");
        build.setUrl("jdbc:mysql://127.0.0.1:3306/oyx_base_0000?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull");
        return build;
    }

    public static CodeGeneratorConfig buildCommonEntity() {
        List<String> tables = Arrays.asList(
                "c_common_area"
                , "c_common_dictionary"
                , "c_common_dictionary_item"
        );
        CodeGeneratorConfig build = CodeGeneratorConfig.
                build("authority", "", "oyx", "c_common_", tables);
        build.setSuperEntity(EntityType.ENTITY);
        build.setChildPackageName("common");
        build.setUrl("jdbc:mysql://127.0.0.1:3306/oyx_base_0000?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull");
        return build;
    }

    public static CodeGeneratorConfig buildCommonSuperEntity() {
        List<String> tables = Arrays.asList(
//                "c_common_opt_log"
                "c_common_login_log"
        );
        CodeGeneratorConfig build = CodeGeneratorConfig.
                build("authority", "", "oyx", "c_common_", tables);
        build.setSuperEntity(EntityType.SUPER_ENTITY);
        build.setChildPackageName("common");
        build.setUrl("jdbc:mysql://127.0.0.1:3306/oyx_base_0000?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull");
        return build;
    }

    public static CodeGeneratorConfig buildCoreEntity() {
        List<String> tables = Arrays.asList(
                "c_core_org"
                , "c_core_station"
        );
        CodeGeneratorConfig build = CodeGeneratorConfig.
                build("authority", "", "oyx", "c_core_", tables);
        build.setSuperEntity(EntityType.ENTITY);
        build.setChildPackageName("core");
        build.setUrl("jdbc:mysql://127.0.0.1:3306/oyx_base_0000?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull");
        return build;
    }
}
