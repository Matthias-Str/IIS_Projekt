package thi.iis.project.pruefungen.pojos;

import net.sf.json.JSONObject;

public class ProcessVariable {
    String name;
    ValueType valueType;

    public ProcessVariable() {

    }

    public ProcessVariable(String name, ValueType valueType) {
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
