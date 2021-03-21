package xmetric.appender;

import xmetric.builder.MetricBuilder;
import xmetric.config.MetricConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author fx.yu
 * @date 2021/3/16 16:21
 */
public abstract class MetricAppender implements IMetricAppender {


    protected MetricConfig metricConfig;

    public MetricAppender(MetricConfig metricConfig) {
        this.metricConfig = metricConfig;
    }

    @Override
    public void recordOne(String metricName, MetricBuilder metricBuilder) {
        try {
            metricOne(metricName, metricBuilder);
        } catch (Exception e) {
        }
    }

    @Override
    public void recordOne(String metricName, long timeUsed, MetricBuilder metricBuilder) {
        try {
            metricOne(metricName, timeUsed, metricBuilder);
        } catch (Exception e) {
        }
    }

    public abstract void metricOne(String metricName, MetricBuilder metricBuilder) throws Exception;


    public abstract void metricOne(String metricName, long timeUsed, MetricBuilder metricBuilder) throws Exception;
}
