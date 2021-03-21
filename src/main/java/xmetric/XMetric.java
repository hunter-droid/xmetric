package xmetric;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import xmetric.appender.IMetricAppender;
import xmetric.builder.MetricBuilder;
import xmetric.config.MetricConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.*;

/**
 * @author fx.yu
 * @date 2021/3/16 16:29
 */
public class XMetric {

    private static List<IMetricAppender> metrics = new ArrayList<>();

    static {
        //1. 获取配置文件metric.json

        MetricConfig config = getMetricConfig();

        //2. 解析配置文件创建实例
        config.getAppenders().forEach(appender -> {
            IMetricAppender metricAppender = getAppenderInstance(config, appender);
            if (metricAppender != null) {
                metrics.add(metricAppender);
            }
        });

    }

    private static MetricConfig getMetricConfig() {
        Gson gson = new Gson();
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:json/xmetric.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String content ="";
        try {
            content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gson.fromJson(content
                , MetricConfig.class);
    }

    private static IMetricAppender getAppenderInstance(MetricConfig config, String appender) {
        try {
            Class clazz = Class.forName(MessageFormat.format("xmetric.appender.{0}", appender));
            // 获取到指定的构造方法
            Constructor constructor = clazz.getConstructor(new Class[]{MetricConfig.class});
            // 通过获取到的构造方法创建对象
            IMetricAppender metricAppender = (IMetricAppender) constructor.newInstance(config);
            return metricAppender;

        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MetricBuilder withTags(Map<String, String> tags) {
        return new MetricBuilder(metrics).withTags(tags);
    }

    public static MetricBuilder withTag(String name, String value) {
        return new MetricBuilder(metrics).withTag(name, value);
    }
}
