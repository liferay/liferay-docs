package com.nosester.portlet.eventlisting.service.persistence;

import java.util.List;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;
import com.nosester.portlet.eventlisting.model.Event;
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
	
	public List<Event> findByUserId(long userId) {
		
		Session session = null;
		try {
			session = openSession();
			
			DynamicQuery dq = DynamicQueryFactoryUtil.forClass(Event.class)
					.add(RestrictionsFactoryUtil.eq("userId", userId));
			
			List<Event> events =  (List<Event>) EventLocalServiceUtil.dynamicQuery(dq);

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