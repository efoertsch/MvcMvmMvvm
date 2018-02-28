package com.fisincorporated.mvprxbus.messages;

// For passing on bus
public class LogEntries {

    private  String logEntries;

    public LogEntries(String logText) {
        this.logEntries = logText;
    }

    public String getLogEntries() {
        return logEntries;
    }
}
