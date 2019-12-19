import java.util.ArrayList;
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
 * @author OYX
 * @date 2019-12-19 20:56
 */
public class TenantGenerator {

    /***
     * 注意，想要在这里直接运行，需要手动增加 mysql 驱动
     * @param args
     */
    public static void main(String[] args) {
        CodeGeneratorConfig build = buildTenantEntity();

        build.setUsername("root");
        build.setPassword("123456");
        System.out.println("输出路径：");
        System.out.println(System.getProperty("user.dir") + "/oyx-backend/oyx-authority");
        build.setProjectRootPath(System.getProperty("user.dir") + "/oyx-backend/oyx-authority");

//        FileCreateConfig fileCreateConfig = new FileCreateConfig(null);
        FileCreateConfig fileCreateConfig = new FileCreateConfig(GenerateType.OVERRIDE);
//        fileCreateConfig.setGenerateEntity(GenerateType.OVERRIDE);
//        fileCreateConfig.setGenerateEnum(GenerateType.OVERRIDE);
//        fileCreateConfig.setGenerateDto(GenerateType.OVERRIDE);
//        fileCreateConfig.setGenerateXml(GenerateType.OVERRIDE);
//        fileCreateConfig.setGenerateDao(GenerateType.OVERRIDE);
//        fileCreateConfig.setGenerateServiceImpl(GenerateType.OVERRIDE);
//        fileCreateConfig.setGenerateService(GenerateType.OVERRIDE);
//        fileCreateConfig.setGenerateController(GenerateType.OVERRIDE);
        build.setFileCreateConfig(fileCreateConfig);

        //手动指定枚举类 生成的路径
        Set<EntityFiledType> filedTypes = new HashSet<>();
        filedTypes.addAll(Arrays.asList(
                EntityFiledType.builder().name("type").table("d_tenant")
                        .packagePath("com.github.oyx.authority.enumeration.defaults.TenantTypeEnum").gen(GenerateType.OVERRIDE).build()
                , EntityFiledType.builder().name("status").table("d_tenant")
                        .packagePath("com.github.oyx.authority.enumeration.defaults.TenantStatusEnum").gen(GenerateType.OVERRIDE).build()
        ));
        build.setFiledTypes(filedTypes);
        CodeGenerator.run(build);
    }
    
    public static CodeGeneratorConfig buildTenantEntity(){
        List<String> tables = Arrays.asList(
            "d_tenant"
        );
        CodeGeneratorConfig build = CodeGeneratorConfig.build("authority", "", "oyx", "d_", tables);
        build.setSuperEntity(EntityType.ENTITY);
        build.setChildPackageName("defaults");
        build.setUrl("jdbc:mysql://127.0.0.1:3306/oyx_defaults?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull");
        return build;
    }
}
