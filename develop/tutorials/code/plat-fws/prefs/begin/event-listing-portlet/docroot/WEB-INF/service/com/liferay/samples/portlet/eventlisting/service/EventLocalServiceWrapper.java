/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.samples.portlet.eventlisting.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EventLocalService}.
 *
 * @author Joe Bloggs
 * @see EventLocalService
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
	@Override
	public com.liferay.samples.portlet.eventlisting.model.Event addEvent(
		com.liferay.samples.portlet.eventlisting.model.Event event)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.addEvent(event);
	}

	/**
	* Creates a new event with the primary key. Does not add the event to the database.
	*
	* @param eventId the primary key for the new event
	* @return the new event
	*/
	@Override
	public com.liferay.samples.portlet.eventlisting.model.Event createEvent(
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
	@Override
	public com.liferay.samples.portlet.eventlisting.model.Event deleteEvent(
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
	@Override
	public com.liferay.samples.portlet.eventlisting.model.Event deleteEvent(
		com.liferay.samples.portlet.eventlisting.model.Event event)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.deleteEvent(event);
	}

	@Override
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
	@Override
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.samples.portlet.eventlisting.model.impl.EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.samples.portlet.eventlisting.model.impl.EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
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
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.samples.portlet.eventlisting.model.Event fetchEvent(
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
	@Override
	public com.liferay.samples.portlet.eventlisting.model.Event getEvent(
		long eventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.getEvent(eventId);
	}

	@Override
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.samples.portlet.eventlisting.model.impl.EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @return the range of events
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.samples.portlet.eventlisting.model.Event> getEvents(
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
	@Override
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
	@Override
	public com.liferay.samples.portlet.eventlisting.model.Event updateEvent(
		com.liferay.samples.portlet.eventlisting.model.Event event)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.updateEvent(event);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _eventLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_eventLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _eventLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public com.liferay.samples.portlet.eventlisting.model.Event addEvent(
		long userId, long groupId, java.lang.String name,
		java.lang.String description, int month, int day, int year, int hour,
		int minute, long locationId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.addEvent(userId, groupId, name, description,
			month, day, year, hour, minute, locationId, serviceContext);
	}

	@Override
	public java.util.List<com.liferay.samples.portlet.eventlisting.model.Event> getEventsByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.getEventsByGroupId(groupId);
	}

	@Override
	public java.util.List<com.liferay.samples.portlet.eventlisting.model.Event> getEventsByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.getEventsByGroupId(groupId, start, end);
	}

	@Override
	public int getEventsCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.getEventsCountByGroupId(groupId);
	}

	@Override
	public com.liferay.samples.portlet.eventlisting.model.Event updateEvent(
		long userId, long eventId, java.lang.String name,
		java.lang.String description, int month, int day, int year, int hour,
		int minute, long locationId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _eventLocalService.updateEvent(userId, eventId, name,
			description, month, day, year, hour, minute, locationId,
			serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public EventLocalService getWrappedEventLocalService() {
		return _eventLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedEventLocalService(EventLocalService eventLocalService) {
		_eventLocalService = eventLocalService;
	}

	@Override
	public EventLocalService getWrappedService() {
		return _eventLocalService;
	}

	@Override
	public void setWrappedService(EventLocalService eventLocalService) {
		_eventLocalService = eventLocalService;
	}

	private EventLocalService _eventLocalService;
}