import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author fx.yu
 * @date 2021/3/16 16:29
 */
public class XMetric {

    private static IMetricBuilder metricBuilder;

    static {
        metricBuilder = new EsMetricBuilder();
    }

    public static IMetricBuilder withTags(Map<String, String> tags) {
        return metricBuilder.withTags(tags);
    }

    public static IMetricBuilder withTag(String name, String value) {
        return metricBuilder.withTag(name, value);
    }

    public static void recordOne() {
        metricBuilder.recordOne();
    }
}
