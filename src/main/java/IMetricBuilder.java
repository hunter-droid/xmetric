import java.util.Map;

/**
 * @author fx.yu
 * @date 2021/3/16 16:19
 */
public interface IMetricBuilder {

    IMetricBuilder withTags(Map<String, String> tags);

    IMetricBuilder withTag(String name, String value);

    void recordOne();
}
