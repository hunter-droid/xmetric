package xmetric.es;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import xmetric.config.EsConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fx.yu
 * @date 2021/3/16 16:51
 */
public class EsRestClient {
    private static RestHighLevelClient restHighLevelClient;

    public static RestHighLevelClient getInstance(List<EsConfig> esConfigs) {
        if (restHighLevelClient != null) {
            return restHighLevelClient;
        }
        HttpHost httpHosts[] = new HttpHost[esConfigs.size()];
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        esConfigs.forEach(config -> {
            HttpHost httpHost = new HttpHost(config.getHost(), config.getPort(), "http");
            httpHosts[esConfigs.indexOf(config)] = httpHost;
            if (config.getUserName() != null)
                credentialsProvider.setCredentials(new AuthScope(httpHost)
                        , new UsernamePasswordCredentials(config.getUserName(), config.getPassWord()));
        });
        RestClientBuilder builder = RestClient.builder(httpHosts);
        builder.setHttpClientConfigCallback(f -> f.setDefaultCredentialsProvider(credentialsProvider));
        restHighLevelClient = new RestHighLevelClient(builder);
        return restHighLevelClient;
    }
}
