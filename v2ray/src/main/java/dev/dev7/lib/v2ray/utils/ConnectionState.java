package dev.dev7.lib.v2ray.utils;


public class ConnectionState {
    private String connectedState = "Disconnected";

    public String getConnectedState() {
        return connectedState;
    }

    public void setConnectedState(String newState) {
        connectedState = newState;
    }
}
