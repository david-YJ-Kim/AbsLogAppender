package com.abs.wfs.appender;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

public class WfsLogAppender extends AppenderBase<ILoggingEvent> {



    private String url;

    private String port;
    private String vpn;
    private String password;
    private String username;

    private String destinationName;

    PatternLayoutEncoder encoder;

    protected void append(ILoggingEvent eventObject) {

        System.out.print("========================================");
        System.out.println("url : "+ url);
        System.out.println("port : "+ port);
        System.out.println("vpn : "+ vpn);
        System.out.println("password : "+ password);
        System.out.println("destinationName : "+ destinationName);

        System.out.println(eventObject.getMessage());

    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getVpn() {
        return vpn;
    }

    public void setVpn(String vpn) {
        this.vpn = vpn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }
}
