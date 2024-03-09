package com.abs.wfs.appender;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AsyncAppenderBase;
import com.abs.wfs.appender.util.SolaceConnectionUtil;
import com.abs.wfs.appender.util.SolaceResourceInstance;
import com.abs.wfs.appender.util.WfsAppenderConstant;
import com.abs.wfs.appender.vo.SolaceConnectionInfoVo;
import com.solacesystems.jcsmp.*;

public class WfsLogAppender extends AsyncAppenderBase<ILoggingEvent> {



    private String url;

    private String port;
    private String vpn;
    private String password;
    private String username;

    private String destinationName;

    PatternLayoutEncoder encoder;

    protected void append(ILoggingEvent eventObject) {

        if(SolaceResourceInstance.getInstance().getConnectionVo() == null){
            SolaceConnectionInfoVo vo = new SolaceConnectionInfoVo();
            vo.setHost(url + ":" + port);
            vo.setUserName(username + "_" + System.currentTimeMillis());
            vo.setVpnName(vpn);
            vo.setPassword(password);
            SolaceResourceInstance.getInstance().setConnectionVo(vo);
            SolaceResourceInstance.getInstance().setProperties(vo);
        }



        try{

          SolaceResourceInstance.getInstance().doAppend(destinationName, eventObject);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }



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
