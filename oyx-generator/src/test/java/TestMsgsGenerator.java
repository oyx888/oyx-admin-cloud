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
public class TestMsgsGenerator {
    /***
     * 注意，想要在这里直接运行，需要手动增加 mysql 驱动
     * @param args
     */
    public static void main(String[] args) {
        CodeGeneratorConfig build = buildAuthEntity();

        build.setUsername("root");
        build.setPassword("root");
        System.out.println("输出路径：");
        System.out.println(System.getProperty("user.dir") + "/oyx-backend/oyx-msgs");
        build.setProjectRootPath(System.getProperty("user.dir") + "/oyx-backend/oyx-msgs");

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
                EntityFiledType.builder().name("providerType").table("sms_template")
                        .packagePath("com.github.oyx.sms.enumeration.ProviderType").gen(GenerateType.IGNORE).build()
        ));
        build.setFiledTypes(filedTypes);
        CodeGenerator.run(build);
    }


    public static CodeGeneratorConfig buildAuthEntity() {
        List<String> tables = Arrays.asList(
//                "sms_template"
                "sms_task"
        );
        CodeGeneratorConfig build = CodeGeneratorConfig.
                build("msgs", "sms", "oyx", "", tables);
        build.setSuperEntity(EntityType.ENTITY);
        build.setChildPackageName("");
        build.setUrl("jdbc:mysql://127.0.0.1:3306/oyx_base_0000?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull");
        return build;
    }
}
