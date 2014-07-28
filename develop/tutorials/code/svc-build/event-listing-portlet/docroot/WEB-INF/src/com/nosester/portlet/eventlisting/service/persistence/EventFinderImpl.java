package com.nosester.portlet.eventlisting.service.persistence;

import java.util.List;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;
import com.nosester.portlet.eventlisting.model.Event;
import com.nosester.portlet.eventlisting.model.Location;
import com.nosester.portlet.eventlisting.model.impl.EventImpl;
import com.nosester.portlet.eventlisting.service.EventLocalServiceUtil;

/**
 * @author Joe Bloggs
 */
public class EventFinderImpl extends BasePersistenceImpl<Event> implements EventFinder {

	public List<Event> findByEventNameEventDescriptionLocationName(String eventName, String eventDescription, String locationName, int begin, int end) {

		Session session = null;
		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_EVENTNAME_EVENTDESCRIPTON_LOCATIONNAME);

			SQLQuery q = session.createSQLQuery(sql);
			q.setCacheable(false);
			q.addEntity("Event_Event", EventImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(eventName);
			qPos.add(eventDescription);
			qPos.add(locationName);

			return (List<Event>)QueryUtil.list(q, getDialect(), begin, end);
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}

		return null;
	}

	public List<Event> findByEventNameEventDescriptionLocationNameDQ(String eventName, String eventDescription, String locationName) {
		
		Session session = null;
		try {
			session = openSession();
			
			DynamicQuery locationQuery = DynamicQueryFactoryUtil.forClass(Location.class)
					.add(RestrictionsFactoryUtil.eq("name", locationName))
					.setProjection(ProjectionFactoryUtil.property("locationId"));
			
			Order order = OrderFactoryUtil.desc("modifiedDate");
			
			DynamicQuery eventQuery = DynamicQueryFactoryUtil.forClass(Event.class)
					.add(RestrictionsFactoryUtil.eq("name", eventName))
					.add(RestrictionsFactoryUtil.eq("description", eventDescription))
					.add(PropertyFactoryUtil.forName("locationId").in(locationQuery))
					.addOrder(order);
			
			List<Event> events = EventLocalServiceUtil.dynamicQuery(eventQuery);
			
			return events;
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}
		
		return null;
	}

	public static final String FIND_BY_EVENTNAME_EVENTDESCRIPTON_LOCATIONNAME = EventFinder.class.getName() + ".findByEventNameEventDescriptionLocationName";

}