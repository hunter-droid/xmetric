package xmetric.config;

import xmetric.config.EsConfig;

import java.util.List;
import java.util.Optional;

/**
 * @author fx.yu
 * @date 2021/3/16 16:34
 */
public class MetricConfig {

    private String appId;

    private String pattern;

    private List<EsConfig> esConfigs;

    private List<String> appenders;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPattern() {
        return Optional.ofNullable(pattern).orElse("yyyyMMdd");
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public List<EsConfig> getEsConfigs() {
        return esConfigs;
    }

    public void setEsConfigs(List<EsConfig> esConfigs) {
        this.esConfigs = esConfigs;
    }

    public List<String> getAppenders() {
        return appenders;
    }

    public void setAppenders(List<String> appenders) {
        this.appenders = appenders;
    }
}
