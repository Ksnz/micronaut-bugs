package example.micronaut;

import io.micronaut.context.annotation.EachProperty;
import io.micronaut.core.convert.format.MapFormat;
import io.micronaut.core.naming.conventions.StringConvention;

import java.util.Map;

@EachProperty("some-property")
public class SomeProperty {
    private String stringValue;
    private Map<String, Map<String, Double>> mapValue;

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Map<String, Map<String, Double>> getMapValue() {
        return mapValue;
    }

    public void setMapValue(@MapFormat(keyFormat = StringConvention.RAW) Map<String, Map<String, Double>> mapValue) {
        this.mapValue = mapValue;
    }
}
