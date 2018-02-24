package pojo;

import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
"adjustmentType",
"adjustmentAmount"
})
public class AdjustmentList {

    @JsonProperty("adjustmentType")
    private String adjustmentType;
    @JsonProperty("adjustmentAmount")
    private Integer adjustmentAmount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    
    @JsonProperty("adjustmentType")
    public String getAdjustmentType() {
        return adjustmentType;
    }

    @JsonProperty("adjustmentType")
    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    @JsonProperty("adjustmentAmount")
    public Integer getAdjustmentAmount() {
        return adjustmentAmount;
    }

    @JsonProperty("adjustmentAmount")
    public void setAdjustmentAmount(Integer adjustmentAmount) {
        this.adjustmentAmount = adjustmentAmount;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
