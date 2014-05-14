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

package com.liferay.samples.portlet.eventlisting.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.samples.portlet.eventlisting.model.Event;

/**
 * The persistence interface for the event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Joe Bloggs
 * @see EventPersistenceImpl
 * @see EventUtil
 * @generated
 */
public interface EventPersistence extends BasePersistence<Event> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EventUtil} to access the event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the events where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.samples.portlet.eventlisting.model.Event> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the events where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.samples.portlet.eventlisting.model.impl.EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @return the range of matching events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.samples.portlet.eventlisting.model.Event> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the events where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.samples.portlet.eventlisting.model.impl.EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.samples.portlet.eventlisting.model.Event> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first event in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event
	* @throws com.liferay.samples.portlet.eventlisting.NoSuchEventException if a matching event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.samples.portlet.eventlisting.model.Event findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.samples.portlet.eventlisting.NoSuchEventException;

	/**
	* Returns the first event in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event, or <code>null</code> if a matching event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.samples.portlet.eventlisting.model.Event fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last event in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event
	* @throws com.liferay.samples.portlet.eventlisting.NoSuchEventException if a matching event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.samples.portlet.eventlisting.model.Event findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.samples.portlet.eventlisting.NoSuchEventException;

	/**
	* Returns the last event in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event, or <code>null</code> if a matching event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.samples.portlet.eventlisting.model.Event fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the events before and after the current event in the ordered set where groupId = &#63;.
	*
	* @param eventId the primary key of the current event
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next event
	* @throws com.liferay.samples.portlet.eventlisting.NoSuchEventException if a event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.samples.portlet.eventlisting.model.Event[] findByGroupId_PrevAndNext(
		long eventId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.samples.portlet.eventlisting.NoSuchEventException;

	/**
	* Removes all the events where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of events where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching events
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the event in the entity cache if it is enabled.
	*
	* @param event the event
	*/
	public void cacheResult(
		com.liferay.samples.portlet.eventlisting.model.Event event);

	/**
	* Caches the events in the entity cache if it is enabled.
	*
	* @param events the events
	*/
	public void cacheResult(
		java.util.List<com.liferay.samples.portlet.eventlisting.model.Event> events);

	/**
	* Creates a new event with the primary key. Does not add the event to the database.
	*
	* @param eventId the primary key for the new event
	* @return the new event
	*/
	public com.liferay.samples.portlet.eventlisting.model.Event create(
		long eventId);

	/**
	* Removes the event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param eventId the primary key of the event
	* @return the event that was removed
	* @throws com.liferay.samples.portlet.eventlisting.NoSuchEventException if a event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.samples.portlet.eventlisting.model.Event remove(
		long eventId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.samples.portlet.eventlisting.NoSuchEventException;

	public com.liferay.samples.portlet.eventlisting.model.Event updateImpl(
		com.liferay.samples.portlet.eventlisting.model.Event event)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the event with the primary key or throws a {@link com.liferay.samples.portlet.eventlisting.NoSuchEventException} if it could not be found.
	*
	* @param eventId the primary key of the event
	* @return the event
	* @throws com.liferay.samples.portlet.eventlisting.NoSuchEventException if a event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.samples.portlet.eventlisting.model.Event findByPrimaryKey(
		long eventId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.samples.portlet.eventlisting.NoSuchEventException;

	/**
	* Returns the event with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param eventId the primary key of the event
	* @return the event, or <code>null</code> if a event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.samples.portlet.eventlisting.model.Event fetchByPrimaryKey(
		long eventId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the events.
	*
	* @return the events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.samples.portlet.eventlisting.model.Event> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.samples.portlet.eventlisting.model.Event> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.samples.portlet.eventlisting.model.impl.EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.samples.portlet.eventlisting.model.Event> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the events from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of events.
	*
	* @return the number of events
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}