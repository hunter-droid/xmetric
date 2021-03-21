import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import xmetric.es.EsRestClient;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fx.yu
 * @version 1.0
 * @date 2021/3/20 8:26 上午
 */
public class EsRestClientTest {

    @Test
    public void elasticsearchClient() throws IOException {
        RestHighLevelClient restHighLevelClient =  EsRestClient.getClient();

        IndexRequest request = new IndexRequest("metric");
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("appId", "10001");
        jsonMap.put("post_date", new Date());
        jsonMap.put("age", 23);
        jsonMap.put("gender", "男");
        jsonMap.put("height", 180);
        request.source(jsonMap);

        IndexResponse response = null;
        response = restHighLevelClient.index(request, RequestOptions.DEFAULT);

    }
}