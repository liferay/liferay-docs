package com.tour.portlet.tasks.messaging.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.service.ServiceContext;
import com.tour.portlet.tasks.service.SetupLocalServiceUtil;

public class SetupMessagingImpl implements MessageListener {

	public void receive(Message message) {
        try {
            doReceive(message);
        }
        catch (Exception e) {
            _log.error("Unable to process message " + message, e);
        }
    }

    protected void doReceive(Message message)
        throws Exception {
    	
    	// Receives message...

        String name = (String) message.get("name");
        String description = (String) message.get("description");
        String status = (String) message.get("status");

        System.out.println("Roadies received setup request for " +
            name);
        
        // Create a new setup item...
        
        ServiceContext serviceContext = new ServiceContext();
        SetupLocalServiceUtil.addSetup(name, description, status, serviceContext);
        
        // Response...
        
        Message responseMessage = MessageBusUtil.createResponseMessage(message);

        responseMessage.put("name", name);
        responseMessage.put("description", description);
        responseMessage.setPayload("RECEIVED");

        MessageBusUtil.sendMessage(responseMessage.getDestinationName(), responseMessage);
        
    }

    private static Log _log = LogFactoryUtil.getLog(SetupMessagingImpl.class);

}
