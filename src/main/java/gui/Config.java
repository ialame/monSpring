package com.pca.gui; /**
 * Created by fxc on 14/01/2019.
 */

import java.util.Map;

public class Config {
    private Map<String, String> parameters;
    private Map<String, String> globals;

    public Map<String, String> getParameters() {
        return parameters;
    }
    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
    public Map<String, String> getGlobals() {
        return globals;
    }
    public void setGlobals(Map<String, String> globals) {
        this.globals = globals;
    }


}