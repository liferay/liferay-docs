# Developing Custom SQL Queries

Service Builder's finder methods facilitate searching for entities by their
attributes--their column values. Add the column as a parameter for the finder in
your `service.xml` file, run Service Builder, and it generates the finder method
in your persistence layer and adds methods to your service layer that invoke the
finder. But what if you'd like to do more complicated searches that incorporate
attributes from multiple entities? 

For example, consider the
[Event Listing](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/svc-build/event-listing-portlet)
portlet. Suppose you want to find an event based on its name, description, and
location name. In the Event Listing portlet, the event entity refers to its
location by the location's ID, not its name. That is, the event entity table,
`Event_Event`, refers to an event's location by its long integer ID in the
table's `locationId` column. But you need to access the *name* of the event's
location. Of course, with SQL you can join the event and location tables to
include the location name. But how would you incorporate custom SQL into your
portlet? And how would you invoke the SQL from your service? Service Builder
lets you do this by specifying the SQL as *Liferay custom SQL* and invoking it
in your service via a *custom finder method*.

Liferay custom SQL is a Service Builder-supported method for performing complex
and custom queries against the database. Invoking custom SQL from a finder
method in your persistence layer is straightforward. And Service Builder helps
you generate the interfaces to your finder method. It's easy to do by following
these steps: 

1. Specify your custom SQL. 

2. Implement your finder method. 

3. Access your finder method from your service. 

Next, using the Event Listing portlet as an example, you'll learn how to
accomplish these steps. 

## Step 1: Specify Your Custom SQL

After you've tested your SQL, you must specify it in a particular file for
Liferay to access it. Liferay's `CustomSQLUtil` class looks up custom SQL from a
file called `default.xml` in your portlet project's
`docroot/WEB-INF/src/custom-sql/` folder. You must create the `custom-sql`
folder and create the `default.xml` file in that `custom-sql` folder. The
`default.xml` file must adhere to the following format: 

    <custom-sql>
        <sql id="[fully-qualified class name + method]">
        SQL query wrapped in <![CDATA[...]]>
        No terminating semi-colon
        </sql>
    </custom-sql>

You can add a `custom-sql` element for every custom SQL query you'd like for
your portlet, as long as each query has a unique ID. The convention we recommend
using for the ID value is the fully-qualified class name of the finder followed
by a dot (`.`) character and the name of the finder method. More detail on the
finder class and finder methods is in Step 2. 

In the Event Listing project, the following ID value was used for the query: 

    com.nosester.portlet.eventlisting.service.persistence.\
    EventFinder.findByEventNameEventDescriptionLocationName

Custom SQL must be wrapped in character data (`CDATA`) for the `sql` element.
Importantly, the SQL must *not* be terminated with a semi-colon. Following these
rules, the `default.xml` file of the Event Listing project specifies an SQL
query that joins the Event and Location tables: 

    <?xml version="1.0" encoding="UTF-8"?>
    <custom-sql>
        <sql id="com.nosester.portlet.eventlisting.service.persistence.EventFinder.\
    findByEventNameEventDescriptionLocationName">
            SELECT Event_Event.*
            FROM Event_Event
            INNER JOIN 
                Event_Location ON Event_Event.locationId = Event_Location.locationId
            WHERE
                (Event_Event.name LIKE ?) AND
                (Event_Event.description LIKE ?) AND
                (Event_Location.name LIKE ?)
        </sql>
    </custom-sql>

If you copy the XML fragment above, make sure to delete the backslash (`\`)
character from the end of the ID so that the finder method name
`findByEventNameEventDescriptionLocationName` immediately follows the package
path specified below:

    com.nosester.portlet.eventlisting.service.persistence.

Now that you've specified some custom SQL, the next step is to implement a
finder method to invoke it. The method name for the finder should match the ID
you just specified for the `sql` element. 

## Step 2: Implement Your Finder Method

It's time to implement the finder method to invoke the custom SQL query. This
should be done in the service's persistence layer, since it's SQL invoked on a
relational database. You'll rely on Service Builder to generate the interface
for it. But before you do that, you need to create the implementation of the
finder. 

The first step is to create a `*FinderImpl` class in the service persistence
package. The Event Listing project includes the `EventFinderImpl` class in the
`com.nosester.portlet.eventlisting.service.persistence.impl` package. Your
class, like `EventFinderImpl`, should extend `BasePersistenceImpl<Event>`.

Run Service Builder to generate the `*Finder` interface and the `*Util` class
for the finder. Service Builder generates the `*Finder` interface and the
`*FinderUtil` utility class based on the `*FinderImpl` class. Modify your
`*FinderImpl` class to have it implement the `*Finder` interface you just
generated: 

    public class *FinderImpl extends BasePersistenceImpl<Event>
        implements EventFinder {

    }

Now you can create our finder method in your `EventFinderImpl` class. Add your
finder method and static field to the `*FinderImpl` class. Here's the
`EventFinderImpl` class:

    public List<Event> findByEventNameEventDescriptionLocationName(
        String eventName, String eventDescription, String locationName,
        int begin, int end) {
                
        Session session = null;
        try {
            session = openSession();

            String sql = CustomSQLUtil.get(
                FIND_BY_EVENTNAME_EVENTDESCRIPTON_LOCATIONNAME);
            
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("Event_Event", EventImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);  
            qPos.add(eventName);
            qPos.add(eventDescription);
            qPos.add(locationName);
            
            return (List<Event>) QueryUtil.list(q, getDialect(), begin, end);
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

    public static final String FIND_BY_EVENTNAME_EVENTDESCRIPTON_LOCATIONNAME =
        EventFinder.class.getName() +
            ".findByEventNameEventDescriptionLocationName";

Remember to import the required classes. The following imports are required for
`EventFinderImpl`: 

    import java.util.List;

    import com.liferay.portal.kernel.dao.orm.QueryPos;
    import com.liferay.portal.kernel.dao.orm.QueryUtil;
    import com.liferay.portal.kernel.dao.orm.SQLQuery;
    import com.liferay.portal.kernel.dao.orm.Session;
    import com.liferay.portal.kernel.exception.SystemException;
    import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
    import com.liferay.util.dao.orm.CustomSQLUtil;

    import com.nosester.portlet.eventlisting.model.Event;
    import com.nosester.portlet.eventlisting.model.impl.EventImpl;

The custom finder method opens a new Hibernate session and uses Liferay's
`CustomSQLUtil.get(String id)` method to get the custom SQL to use for the
database query. The `FIND_BY_EVENTNAME_EVENTDESCRIPTON_LOCATIONNAME` static
field contains the custom SQL query's ID. The
`FIND_BY_EVENTNAME_EVENTDESCRIPTON_LOCATIONNAME` string is based on the
fully-qualified class name of the `*Finder` interface (`EventFinder`) and
the name of the finder method (`findByEventNameEventDescriptionLocationName`). 

Awesome! Custom SQL is in place and your finder method is implemented. Next,
you'll call the finder method from your service. 

## Step 3: Access Your Finder Method from Your Service

So far, you created a `*FinderImpl` class and generated a `*FinderUtil` utility
class. However, your portlet class should not use the finder utility class
directly; only a local or remote service implementation (i.e.,
`*LocalServiceImpl` or `*ServiceImpl`) in your plugin project should invoke the
`*FinderUtil` class. This encourages a proper separation of concerns: the
portlet classes invoke business logic of the services and the services in turn
access the data model using the persistence layer's finder classes. So you'll
add a method in the `*LocalServiceImpl` class that invokes the finder method
implementation via the `*FinderUtil` class. Then you'll provide the portlet and
JSPs access to this service method by rebuilding the service.

The following method in `EventLocalServiceImpl` invokes the finder method
discussed in step 2: 

    public List<Event> findByEventNameEventDescriptionLocationName(String eventName,
        String eventDescription, String locationName, int begin, int end)
    throws SystemException {
		
        return EventFinderUtil.findByEventNameEventDescriptionLocationName(
            eventName, eventDescription, locationName, begin, end);
    }	

After you've added a service method to invoke your finder method, run Service
Builder to generate the interface and make your finder service method available
in the `EventLocalServiceUtil` class. 

Now you can indirectly call the finder method from your portlet class or from a
JSP. To call the finder method in the Event Listing project, just calling
`EventLocalServiceUtil.findByEventNameEventDescriptionLocationName(...)`! 

<!-- Note, the view JSP currently does not call
EventLocalServiceUtil.findByEventNameEventDescriptionLocationName(...). We may
want to include this in the solution. - Jim --> 

Congratulations on developing a custom SQL query and custom finder for your
portlet! 
