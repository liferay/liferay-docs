/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.tour.portlet.tasks.service.impl;

import com.tour.portlet.tasks.service.TaskLocalServiceUtil;
import com.tour.portlet.tasks.service.base.TaskLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.tour.portlet.tasks.model.Task;
import com.liferay.portal.service.ServiceContext;

/**
 * The implementation of the task local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.tour.portlet.tasks.service.TaskLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author nickgaskill
 * @see com.tour.portlet.tasks.service.base.TaskLocalServiceBaseImpl
 * @see com.tour.portlet.tasks.service.TaskLocalServiceUtil
 */
public class TaskLocalServiceImpl extends TaskLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.tour.portlet.tasks.service.TaskLocalServiceUtil} to access the task local service.
	 */
	
	public Task addTask(String name, String description, String status, ServiceContext serviceContext)
	  throws PortalException, SystemException {
		
	    long taskId = counterLocalService.increment(Task.class.getName());

	    Task task = taskPersistence.create(taskId);

	    task.setName(name);
	    task.setDescription(description);
	    task.setStatus(status);
	    
	    super.addTask(task);
	    
	    return task;
		
	}
	
	public Task deleteTask(Task task) throws SystemException {

	    return taskPersistence.remove(task);
	}
	
	public Task deleteTask(long taskId)
		throws PortalException, SystemException {

		Task task = taskPersistence.findByPrimaryKey(taskId);

		return deleteTask(task);
	}
	
	public Task getTask(long taskId)
		throws SystemException, PortalException {

		return taskPersistence.findByPrimaryKey(taskId);
	}
	
	public Task updateTask(long taskId, String name, String description, String status, ServiceContext serviceContext)
			  throws PortalException, SystemException {
		
		Task task = TaskLocalServiceUtil.fetchTask(taskId);
		
		task.setName(name);
	    task.setDescription(description);
	    task.setStatus(status);
	    
	    super.updateTask(task);
	    
	    return task;
		
	}
	
}