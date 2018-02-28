package com.fisincorporated.mvprxbus.messages;

// For passing on bus
public class LogUpdate {

    private String logUpdate;

    public LogUpdate(String logUpdate) {
        this.logUpdate = logUpdate;
    }

    public String getLogUpdate() {
        return logUpdate;
    }
}
