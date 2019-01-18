package thi.iis.project.pruefungen.pojos;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.sf.json.JSONObject;

/**
 * Defines format of an pair of value and type that is send to camunda using
 * rest api
 * 
 * @author Katrin Krüger
 *
 */
public class ValueType {
    Object value;
    String type;

    public ValueType() {

    }

    public ValueType(Object value, String type) {
        this.value = value;
        this.type = type;
    }

    public JSONObject toJson() {
        JSONObject result = new JSONObject();
        if (value instanceof Date) {
            Date d = (Date) value;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            result.put("value", sdf.format(d));
        } else if (value instanceof Calendar) {
            Date d = ((Calendar) value).getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            result.put("value", sdf.format(d));
        } else {
            result.put("value", value);
        }
        result.put("type", type);
        return result;
    }
}
