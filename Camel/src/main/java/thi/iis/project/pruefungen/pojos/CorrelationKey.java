package thi.iis.project.pruefungen.pojos;

import net.sf.json.JSONObject;

/**
 * Helper Class that defines format of a Correlation Key that is send to camunda
 * using rest api
 * 
 * @author Katrin Krüger
 *
 */
public class CorrelationKey {
    String name;
    ValueType valueType;

    public CorrelationKey() {
        super();
    }

    public CorrelationKey(String name, ValueType valueType) {
        this.name = name;
        this.valueType = valueType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }

    public JSONObject toJson() {
        return valueType.toJson();
    }
}
