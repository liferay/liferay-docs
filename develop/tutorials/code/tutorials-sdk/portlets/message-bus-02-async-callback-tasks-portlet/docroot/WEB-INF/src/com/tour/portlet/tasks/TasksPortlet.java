package com.tour.portlet.tasks;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
        String roadieResponse = ParamUtil.getString(request, "roadieResponse");
        String inventoryResponse = ParamUtil.getString(request, "inventoryResponse");
        
        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                Task.class.getName(), request);

        Task task = null;
        
        if (taskId <= 0) {
        	task = TaskLocalServiceUtil.addTask(
                name, description, status, serviceContext);
        	
        	long newTaskId = task.getTaskId();
        	
        	// Begin Message Bus
 	       
        	JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

        	jsonObject.put("taskId", newTaskId);
        	jsonObject.put("name", name);
        	jsonObject.put("description", description);
        	jsonObject.put("status", status);
        	jsonObject.put("responseDestinationName", "tour/manager/task");

        	MessageBusUtil.sendMessage("tour/roadie/setup", jsonObject.toString());
           
           // End Message Bus
            
        }
        else {
        	task = TaskLocalServiceUtil.getTask(taskId);

        	task = TaskLocalServiceUtil.updateTask(
                taskId, name, description, status, roadieResponse, inventoryResponse, serviceContext);
        }

        return task;
		
	}
	
	private static Log _log = LogFactoryUtil.getLog(TasksPortlet.class);

}
