package com.abs.wfs.appender.util;

import com.abs.wfs.appender.vo.SolaceConnectionInfoVo;
import com.solacesystems.jcsmp.*;

public class SolaceConnectionUtil {

    private static int defaultReconnectRetries = 20;
    private static int defaultRetiresPerHost = 5;


    public static JCSMPProperties generateProperties(SolaceConnectionInfoVo vo){

        JCSMPProperties properties = new JCSMPProperties();

        properties.setProperty(JCSMPProperties.HOST, vo.getHost());
        properties.setProperty(JCSMPProperties.USERNAME, vo.getUserName());
        properties.setProperty(JCSMPProperties.VPN_NAME, vo.getVpnName());
        properties.setProperty(JCSMPProperties.PASSWORD, vo.getPassword());

        JCSMPChannelProperties channelProperties = new JCSMPChannelProperties();
        channelProperties.setReconnectRetries(vo.getReconnectRetries() == null ? defaultReconnectRetries : vo.getReconnectRetries());      // recommended settings
        channelProperties.setConnectRetriesPerHost(vo.getRetriesPerHost() == null ? defaultRetiresPerHost : vo.getRetriesPerHost());  // recommended settings

        properties.setProperty(JCSMPProperties.CLIENT_CHANNEL_PROPERTIES, channelProperties);

        System.out.println("===============================");
        System.out.println(properties.toString());
        return properties;
    }

}
