package com.github.oyx.swagger2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *  swagger2 属性配置
 *  必须配置 prefix ，才能有提示
 * @author OYX
 * @date 2019-12-7 15:53
 */
@Data
@ConfigurationProperties(prefix = "oyx.swagger")
public class SwaggerProperties {

    private Boolean enable = true;

    private Boolean production= false;

    private Basic basic = new Basic();

    /**
     * 标题
     */
    private String title = "在线文档";
    private String group = "";

    /**
     *描述
     */
    private String description = "oyx-admin-cloud 在线文档";

    /**
     * 版本
     */
    private String version = "1.0";

    /**
     * 许可证
     **/
    private String license = "";

    /**
     * 许可证URL
     **/
    private String licenseUrl = "";

    /**
     * 服务条款URL
     **/
    private String termsOfServiceUrl = "";

    private Contact contact = new Contact();

    /**
     * swagger会解析的包路径
     **/
    private String basePackage = "com.github.oyx";

    /**
     * swagger会解析的url规则
     **/
    private List<String> basePath = new ArrayList<>();
    /**
     * 在basePath基础上需要排除的url规则
     **/
    private List<String> excludePath = new ArrayList<>();

    /**
     * 分组文档
     **/
    private Map<String, DocketInfo> docket = new LinkedHashMap<>();

    /**
     * host信息
     **/
    private String host = "";

    /**
     * 排序
     */
    private Integer order = 1;

    /**
     * 全局参数配置
     **/
    private List<GlobalOperationParameter> globalOperationParameters;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Basic{
        private Boolean enable = false;
        private String username = "oyx";
        private String password ="oyx";
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Contact{
        private String name = "oyx";
        private String url = "https://github.com/oyx888/oyx-admin-cloud";
        private String email ="278812951@qq.com";
    }

    @Data
    public static class GlobalOperationParameter {
        /**
         * 参数名
         **/
        private String name;

        /**
         * 描述信息
         **/
        private String description = "全局参数";

        /**
         * 指定参数类型
         **/
        private String modelRef = "String";

        /**
         * 参数放在哪个地方:header,query,path,body.form
         **/
        private String parameterType = "header";

        /**
         * 参数是否必须传
         **/
        private Boolean required = false;
        /**
         * 默认值
         */
        private String defaultValue = "";
        /**
         * 允许为空
         */
        private Boolean allowEmptyValue = true;
        /**
         * 排序
         */
        private int order = 1;
    }

    @Data
    public static class DocketInfo {
        /**
         * 标题
         **/
        private String title = "在线文档";
        /**
         * 自定义组名
         */
        private String group = "";
        /**
         * 描述
         **/
        private String description = "oyx-admin-cloud 在线文档";
        /**
         * 版本
         **/
        private String version = "1.0";
        /**
         * 许可证
         **/
        private String license = "";
        /**
         * 许可证URL
         **/
        private String licenseUrl = "";
        /**
         * 服务条款URL
         **/
        private String termsOfServiceUrl = "";

        private Contact contact = new Contact();

        /**
         * swagger会解析的包路径
         **/
        private String basePackage = "";

        /**
         * swagger会解析的url规则
         **/
        private List<String> basePath = new ArrayList<>();
        /**
         * 在basePath基础上需要排除的url规则
         **/
        private List<String> excludePath = new ArrayList<>();

        private List<GlobalOperationParameter> globalOperationParameters;

        /**
         * 排序
         */
        private Integer order = 1;

        public String getGroup() {
            if (group == null || "".equals(group)) {
                return title;
            }
            return group;
        }
    }
}
