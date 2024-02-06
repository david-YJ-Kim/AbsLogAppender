package com.abs.wfs.appender.util;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.abs.wfs.appender.vo.SolaceConnectionInfoVo;
import com.solacesystems.jcsmp.*;

public class SolaceResourceInstance {

    private static SolaceResourceInstance instance;


    private JCSMPSession session = null;

    private XMLMessageProducer messageProducer = null;


    private JCSMPProperties properties = null;

    private SolaceConnectionInfoVo vo = null;

    private SolaceResourceInstance(){}

    public static SolaceResourceInstance getInstance(){
        if(instance == null){
            instance = new SolaceResourceInstance();
        }
        return instance;
    }


    public void  doAppend(String destinationName, ILoggingEvent eventObject){
        System.out.println(eventObject.getMessage());


        if(instance.messageProducer == null ||
                instance.session == null){
            try {
                instance.generateResources();
            } catch (JCSMPException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }


        TextMessage message = JCSMPFactory.onlyInstance().createMessage(TextMessage.class);
        SDTMap customHeader = JCSMPFactory.onlyInstance().createMap();
        try {
            customHeader.putString(WfsAppenderConstant.logLevel, eventObject.getLevel().toString());
            customHeader.putString(WfsAppenderConstant.threadName, eventObject.getThreadName());
            customHeader.putLong(WfsAppenderConstant.timestamp, eventObject.getTimeStamp());
        } catch (SDTException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        message.setProperties(customHeader);
        message.setText(eventObject.getMessage());


        if(destinationName.contains("_")){
            Queue queue = JCSMPFactory.onlyInstance().createQueue(destinationName);
            try {
                SolaceResourceInstance.getInstance().getMessageProducer().send(message, queue);
            } catch (JCSMPException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }else {

            Topic topic = JCSMPFactory.onlyInstance().createTopic(destinationName);
            try {
                SolaceResourceInstance.getInstance().getMessageProducer().send(message, topic);
            } catch (JCSMPException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }

    public void setConnectionVo(SolaceConnectionInfoVo connectionVo){
        instance.vo = connectionVo;
    }

    public SolaceConnectionInfoVo getConnectionVo(){
        return instance.vo;
    }

    public void setProperties(SolaceConnectionInfoVo connectionVo){
        instance.properties = SolaceConnectionUtil.generateProperties(connectionVo);
    }


    public XMLMessageProducer getMessageProducer() {
        return messageProducer;
    }

    public JCSMPSession getSession() {
        return session;
    }

    public void generateResources() throws JCSMPException {

        instance.session = JCSMPFactory.onlyInstance().createSession(instance.properties);
        instance.session.connect();
        instance.messageProducer = instance.session.getMessageProducer();

    }
}
