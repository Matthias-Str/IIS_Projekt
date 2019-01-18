package thi.iis.project.pruefungen.pojos;

import java.util.ArrayList;

import net.sf.json.JSONObject;

/**
 * Helper Class that defines format of message body that is send to camunda
 * using rest api
 * 
 * @author Katrin Krüger
 *
 */
public class CamundaRESTJSON {
    String messageName;
    Boolean resultEnabled;
    ArrayList<CorrelationKey> correlationKeys = new ArrayList<>();
    ArrayList<ProcessVariable> processVariables = new ArrayList<>();

    public CamundaRESTJSON() {

    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public Boolean getResultEnabled() {
        return resultEnabled;
    }

    public void setResultEnabled(Boolean resultEnabled) {
        this.resultEnabled = resultEnabled;
    }

    public ArrayList<CorrelationKey> getCorrelationKeys() {
        return correlationKeys;
    }

    public void setCorrelationKeys(ArrayList<CorrelationKey> correlationKeys) {
        this.correlationKeys = correlationKeys;
    }

    public ArrayList<ProcessVariable> getProcessVariables() {
        return processVariables;
    }

    public void setProcessVariables(ArrayList<ProcessVariable> processVariables) {
        this.processVariables = processVariables;
    }

    /**
     * Convert the Object to JSON
     * @return JSONObject messageContent that is send to camunda
     */
    public JSONObject toJson() {
        JSONObject result = new JSONObject();

        result.put("messageName", messageName);
        result.put("resultEnabled", resultEnabled);

        if (correlationKeys.size() != 0) {
            JSONObject kKeys = new JSONObject();
            for (CorrelationKey kk : correlationKeys) {
                kKeys.put(kk.getName(), kk.toJson());
            }
            result.put("correlationKeys", kKeys);
        }

        JSONObject pVar = new JSONObject();
        for (ProcessVariable pv : processVariables) {
            pVar.put(pv.getName(), pv.toJson());
        }
        result.put("processVariables", pVar);

        return result;
    }

}
