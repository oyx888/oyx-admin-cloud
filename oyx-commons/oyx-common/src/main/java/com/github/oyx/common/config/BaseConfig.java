package com.github.oyx.common.config;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.oyx.common.converter.EnumDeserializer;

import static com.github.oyx.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;

/**
 * @author OYX
 * @date 2019-12-10 13:55
 */
public abstract class BaseConfig {

    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder){
        builder.simpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
        ObjectMapper objectMapper = builder.createXmlMapper(false)
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .timeZone(TimeZone.getTimeZone("Asia/Shanghai")).build();

        objectMapper
                .setLocale(Locale.CHINA)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                //忽略未知字段
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                //该特性决定parser是否允许JSON字符串包含非引号控制字符（值小于32的ASCII字符，包含制表符和换行符）。 如果该属性关闭，则如果遇到这些字符，则会抛出异常。JSON标准说明书要求所有控制符必须使用引号，因此这是一个非标准的特性
                .configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true)
                // 忽略不能转移的字符
                .configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true)
                //单引号处理
                .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
                //日期格式
                .setDateFormat(new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT));

        //反序列化时，属性不存在的兼容处理
        objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        SimpleModule simpleModule = new SimpleModule()
                .addSerializer(Long.class, ToStringSerializer.instance)
                .addSerializer(Long.TYPE, ToStringSerializer.instance)
                .addSerializer(BigInteger.class, ToStringSerializer.instance)
                .addSerializer(BigDecimal.class, ToStringSerializer.instance)
                .addDeserializer(Enum.class, EnumDeserializer.INSTANCE);
        return null;
    }
}
