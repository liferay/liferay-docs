/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.nosester.portlet.eventlisting.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link EventLocalService}.
 * </p>
 *
 * @author    Joe Bloggs
 * @see       EventLocalService
 * @generated
 */
public class EventLocalServiceWrapper implements EventLocalService,
	ServiceWrapper<EventLocalService> {
	public EventLocalServiceWrapper(EventLocalService eventLocalService) {
		_eventLocalService = eventLocalService;
	}

	/**
	* Adds the event to the database. Also notifies the appropriate model listeners.
	*
	* @param event the event
	* @return the event that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.nosester.portlet.eventlisting.model.Event addEvent(
		com.nosester.portlet.eventlisting.model.Event event)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.addEvent(event);
	}

	/**
	* Creates a new event with the primary key. Does not add the event to the database.
	*
	* @param eventId the primary key for the new event
	* @return the new event
	*/
	public com.nosester.portlet.eventlisting.model.Event createEvent(
		long eventId) {
		return _eventLocalService.createEvent(eventId);
	}

	/**
	* Deletes the event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param eventId the primary key of the event
	* @return the event that was removed
	* @throws PortalException if a event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.nosester.portlet.eventlisting.model.Event deleteEvent(
		long eventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.deleteEvent(eventId);
	}

	/**
	* Deletes the event from the database. Also notifies the appropriate model listeners.
	*
	* @param event the event
	* @return the event that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.nosester.portlet.eventlisting.model.Event deleteEvent(
		com.nosester.portlet.eventlisting.model.Event event)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.deleteEvent(event);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _eventLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.nosester.portlet.eventlisting.model.Event fetchEvent(
		long eventId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.fetchEvent(eventId);
	}

	/**
	* Returns the event with the primary key.
	*
	* @param eventId the primary key of the event
	* @return the event
	* @throws PortalException if a event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.nosester.portlet.eventlisting.model.Event getEvent(long eventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.getEvent(eventId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @return the range of events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.nosester.portlet.eventlisting.model.Event> getEvents(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.getEvents(start, end);
	}

	/**
	* Returns the number of events.
	*
	* @return the number of events
	* @throws SystemException if a system exception occurred
	*/
	public int getEventsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.getEventsCount();
	}

	/**
	* Updates the event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param event the event
	* @return the event that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.nosester.portlet.eventlisting.model.Event updateEvent(
		com.nosester.portlet.eventlisting.model.Event event)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.updateEvent(event);
	}

	/**
	* Updates the event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param event the event
	* @param merge whether to merge the event with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the event that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.nosester.portlet.eventlisting.model.Event updateEvent(
		com.nosester.portlet.eventlisting.model.Event event, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.updateEvent(event, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _eventLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_eventLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _eventLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.nosester.portlet.eventlisting.model.Event addEvent(
		java.lang.String name, java.lang.String description,
		java.util.Date date, long locationId,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _eventLocalService.addEvent(name, description, date, locationId,
			serviceContext);
	}

	public java.util.List<com.nosester.portlet.eventlisting.model.Event> findByEventNameEventDescriptionLocationName(
		java.lang.String eventName, java.lang.String eventDescription,
		java.lang.String locationName, int begin, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.findByEventNameEventDescriptionLocationName(eventName,
			eventDescription, locationName, begin, end);
	}

	public java.util.List<com.nosester.portlet.eventlisting.model.Event> getEventsByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.getEventsByGroupId(groupId);
	}

	public java.util.List<com.nosester.portlet.eventlisting.model.Event> getEventsByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.getEventsByGroupId(groupId, start, end);
	}

	public int getEventsCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.getEventsCountByGroupId(groupId);
	}

	public com.nosester.portlet.eventlisting.model.Event updateEvent(
		long eventId, java.lang.String name, java.lang.String description,
		java.util.Date date, long locationId,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _eventLocalService.updateEvent(eventId, name, description, date,
			locationId, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public EventLocalService getWrappedEventLocalService() {
		return _eventLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedEventLocalService(EventLocalService eventLocalService) {
		_eventLocalService = eventLocalService;
	}

	public EventLocalService getWrappedService() {
		return _eventLocalService;
	}

	public void setWrappedService(EventLocalService eventLocalService) {
		_eventLocalService = eventLocalService;
	}

	private EventLocalService _eventLocalService;
}