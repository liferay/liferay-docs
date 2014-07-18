package com.tour.portlet.tasks.messaging.impl;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.tour.portlet.tasks.model.Task;
import com.tour.portlet.tasks.service.TaskLocalServiceUtil;

public class TasksMessagingImpl implements MessageListener {
	
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

        long taskId = jsonObject.getLong("taskId");
        String roadieResponse = (String) jsonObject.getString("roadieResponse");
        String inventoryResponse = (String) jsonObject.getString("inventoryResponse");
        
        // Persists responses...
        
        Task task = TaskLocalServiceUtil.getTask(taskId);
        
        if (roadieResponse.equals("Yes")) {
        	task.setRoadieResponse("Yes");
        }
        
        if (inventoryResponse.equals("Yes")) {
        	task.setInventoryResponse("Yes");
        }
        
        TaskLocalServiceUtil.updateTask(task);
        
    }

    private static Log _log = LogFactoryUtil.getLog(TasksMessagingImpl.class);

}
