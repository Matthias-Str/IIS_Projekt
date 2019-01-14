package thi.iis.project.pruefungen.pojos;

import java.util.ArrayList;

import net.sf.json.JSONObject;

public class CamundaRESTJSON {
    String messageName;
    Boolean resultEnabled;
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

    public ArrayList<ProcessVariable> getProcessVariables() {
        return processVariables;
    }

    public void setProcessVariables(ArrayList<ProcessVariable> processVariables) {
        this.processVariables = processVariables;
    }

    public JSONObject toJson() {
        JSONObject result = new JSONObject();

        result.put("messageName", messageName);
        result.put("resultEnabled", resultEnabled);
        JSONObject pVar = new JSONObject();
        for (ProcessVariable pv : processVariables) {
            pVar.put(pv.getName(), pv.toJson());
        }
        result.put("processVariables", pVar);

        return result;
    }

}
