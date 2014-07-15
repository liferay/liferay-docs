package com.tour.portlet.tasks;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusException;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.tour.portlet.tasks.model.Task;
import com.tour.portlet.tasks.service.TaskLocalServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * Portlet implementation class TasksPortlet
 */
public class TasksPortlet extends MVCPortlet {
	
	public void addTask(ActionRequest request, ActionResponse response)
            throws Exception {

        _updateTask(request);

        sendRedirect(request, response);
    }
	
	public void deleteTask(ActionRequest request, ActionResponse response)
	        throws Exception {

	        long taskId = ParamUtil.getLong(request, "taskId");

	        TaskLocalServiceUtil.deleteTask(taskId);

	        sendRedirect(request, response);
	    }
	
	public void updateTask(ActionRequest request, ActionResponse response)
	        throws Exception {

	        _updateTask(request);

	        sendRedirect(request, response);
	    }
	
	private Task _updateTask(ActionRequest request)
	        throws PortalException, SystemException {
		
		long taskId = ParamUtil.getLong(request, "taskId");
        String name = ParamUtil.getString(request, "name");
        String description = ParamUtil.getString(request, "description");
        String status = ParamUtil.getString(request, "status");
        
        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                Task.class.getName(), request);

        Task task = null;
        
        if (taskId <= 0) {
        	task = TaskLocalServiceUtil.addTask(
                name, description, status, serviceContext);
        	
        	// Begin Message Bus
 	       
            Message message = new Message();
            message.put("name", name);
            message.put("description", description);
            message.put("status", status);
            message.setResponseId("1111");
            message.setResponseDestinationName("tour/manager/task");
           
            try {
        	   
        	    String roadieResponse = (String) MessageBusUtil.sendSynchronousMessage(
        		        "tour/roadie/setup", message, 10000);
        	    
        	    if (roadieResponse.equals("RECEIVED")) {
        	      SessionMessages.add(request, "success");
        	    }
        	   
        	    System.out.println(
        		        "Tour manager received response from roadie for " +
        		        name + ": " + roadieResponse);
        	   
            } catch (MessageBusException e) {
        	    e.printStackTrace();
            }
           
           // End Message Bus
            
        }
        else {
        	task = TaskLocalServiceUtil.getTask(taskId);

        	task = TaskLocalServiceUtil.updateTask(
                taskId, name, description, status, serviceContext);
        }

        return task;
		
	}
	
	private static Log _log = LogFactoryUtil.getLog(TasksPortlet.class);

}
