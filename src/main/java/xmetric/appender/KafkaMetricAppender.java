package xmetric.appender;

import xmetric.appender.MetricAppender;
import xmetric.builder.MetricBuilder;
import xmetric.config.MetricConfig;

/**
 * @author fx.yu
 * @date 2021/3/16 16:28
 */
public class KafkaMetricAppender extends MetricAppender {
    public KafkaMetricAppender(MetricConfig metricConfig) {
        super(metricConfig);
    }

    @Override
    public void metricOne(String metricName, MetricBuilder metricBuilder) throws Exception {

    }

    @Override
    public void metricOne(String metricName, long timeUsed, MetricBuilder metricBuilder) throws Exception {

    }
}
