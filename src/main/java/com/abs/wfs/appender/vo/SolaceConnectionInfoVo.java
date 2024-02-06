package com.abs.wfs.appender.vo;

public class SolaceConnectionInfoVo {

    private String host;
    private String userName;
    private String vpnName;
    private String password;

    private Integer reconnectRetries;
    private Integer retriesPerHost;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVpnName() {
        return vpnName;
    }

    public void setVpnName(String vpnName) {
        this.vpnName = vpnName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getReconnectRetries() {
        return reconnectRetries;
    }

    public void setReconnectRetries(Integer reconnectRetries) {
        this.reconnectRetries = reconnectRetries;
    }

    public Integer getRetriesPerHost() {
        return retriesPerHost;
    }

    public void setRetriesPerHost(Integer retriesPerHost) {
        this.retriesPerHost = retriesPerHost;
    }
}
