package xmetric.builder;

import xmetric.appender.IMetricAppender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author fx.yu
 * @version 1.0
 * @date 2021/3/20 3:23 下午
 */
public class MetricBuilder {

    private Map<String, String> tags;

    private List<IMetricAppender> metrics;

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public MetricBuilder(List<IMetricAppender> metrics) {
        this.metrics = metrics;
        this.tags=new HashMap<>();
    }

    public MetricBuilder withTag(String name, String value) {
        tags.put(name, value);
        return this;
    }

    public MetricBuilder withTags(Map<String, String> tags) {
        if (tags == null || tags.size() == 0) {
            return this;
        }
        tags.forEach((k, v) -> {
            this.tags.put(k, Optional.ofNullable(v).orElse("nil"));
        });
        return this;
    }

    public void recordOne(String metricName) {

        metrics.forEach(metric -> {
            metric.recordOne(metricName, this);
        });
    }

    public void recordOne(String metricName, long timeUsed) {
        metrics.forEach(metric -> {
            metric.recordOne(metricName, timeUsed, this);
        });
    }
}
