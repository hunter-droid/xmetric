package xmetric.appender;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import xmetric.builder.MetricBuilder;
import xmetric.config.MetricConfig;
import xmetric.es.EsRestClient;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fx.yu
 * @date 2021/3/16 16:28
 */
public class EsMetricAppender extends MetricAppender {


    public EsMetricAppender(MetricConfig metricConfig) {
        super(metricConfig);
    }

    @Override
    public void metricOne(String metricName, MetricBuilder metricBuilder) throws IOException {

        String indexName = MessageFormat.format("x-metric-{0}"
                , new SimpleDateFormat(metricConfig.getPattern()));

        IndexRequest indexRequest = new IndexRequest(indexName);

        Map<String, Object> map = new HashMap<>();
        if (metricBuilder.getTags() != null && metricBuilder.getTags().size() > 0) {
            map.putAll(metricBuilder.getTags());
        }
        map.put("appId", metricConfig.getAppId());
        map.put("metricName", metricName);
        map.put("metricTime", new Date());
        indexRequest.source(map);

        EsRestClient.getInstance().index(indexRequest, RequestOptions.DEFAULT);
    }

    @Override
    public void metricOne(String metricName, long timeUsed, MetricBuilder metricBuilder) {

    }


}
