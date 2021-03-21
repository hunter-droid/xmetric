package xmetric.appender;

import xmetric.builder.MetricBuilder;

import java.util.Map;

/**
 * @author fx.yu
 * @date 2021/3/16 16:19
 */
public interface IMetricAppender {

    void recordOne(String metricName, MetricBuilder metricBuilder);

    void recordOne(String metricName, long timeUsed, MetricBuilder metricBuilder);
}
