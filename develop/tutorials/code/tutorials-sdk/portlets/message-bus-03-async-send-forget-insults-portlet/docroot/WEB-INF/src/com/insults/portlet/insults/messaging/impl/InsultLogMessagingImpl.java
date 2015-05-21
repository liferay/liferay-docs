package com.insults.portlet.insults.messaging.impl;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.service.ServiceContext;
import com.liferay.sample.service.InsultLogLocalServiceUtil;


public class InsultLogMessagingImpl implements MessageListener {

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
    	
    	String payload = (String)message.getPayload();
    	JSONObject jsonObject = JSONFactoryUtil.createJSONObject(payload);

    	String insult = (String) jsonObject.getString("insult");
        String rating = (String) jsonObject.getString("rating");
        
        // Create a new insult log item...
        
        ServiceContext serviceContext = new ServiceContext();
        InsultLogLocalServiceUtil.addInsultLog(insult, rating, serviceContext);
        
    }

    private static Log _log = LogFactoryUtil.getLog(InsultLogMessagingImpl.class);

}
