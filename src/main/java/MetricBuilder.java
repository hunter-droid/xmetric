import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author fx.yu
 * @date 2021/3/16 16:21
 */
public class MetricBuilder implements IMetricBuilder {

    private Map<String, String> tags;

    public MetricBuilder() {
        this.tags = new HashMap<String, String>();
    }

    public IMetricBuilder withTags(Map<String, String> tags) {
        if (tags == null || tags.size() == 0) {
            return this;
        }
        tags.forEach((k, v) -> {
            this.tags.put(k, Optional.ofNullable(v).orElse("nil"));
        });
        return this;
    }

    public IMetricBuilder withTag(String name, String value) {
        this.tags.put(name, Optional.ofNullable(value).orElse("nil"));
        return this;
    }

    public void recordOne() {

    }
}
