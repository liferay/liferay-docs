# Liferay's Logging System [](id=liferays-logging-system-lp-6-2-use-useportal)

Liferay uses Log4j extensively to implement logging for nearly every class in
the portal. If you need to debug something specific while the system is running,
you can use the control panel to set logging levels by class dynamically.

To view the log levels, go to the control panel, click *Server Administration*
in the Server section, and then click the *Log Levels* tab.

A paginated list of logging categories appears. These categories correspond to
Liferay classes that have log messages in them. By default, all categories are
set to display messages only if there is an error that occurs in the class. This
is why you see ERROR displayed in all of the drop-down list boxes on the right
side of the portlet.

Each category is filtered by its place in the class hierarchy. For example, if
you wanted to see logging for a specific class that is registered in Liferay,
you would browse to that specific class and change its log level to something
that is more descriptive, such as DEBUG. Once you click the *Save* button at the
bottom of the list, you'll start seeing DEBUG messages from that class in your
application server's log file.

If you're not sure which class you want to see log messages for, you can find a
place higher up in the hierarchy and select the package name instead of an
individual class name. If you do this, messages for every class lower in the
hierarchy will be displayed in your application server's log file.

![Figure 18.7: Log levels can be dynamically changed at runtime whenever you need to debug an issue. ](../../images/maintaining-log-levels.png) 

Be careful when you do this. If you set the log level to DEBUG somewhere near
the top of the hierarchy (such as `com.liferay`, for example), you may wind up
with a lot of messages in your log file. This could make it difficult to find
the one you were looking for, and causes the server to do more work writing
messages to the log. 

If you want to set the log level for one of your own classes in a deployed
plugin, you can register that class with Liferay to can control the log levels
more easily, so long as your class uses Log4J to do its logging.

You will first need to implement Log4J logging in your class, with a statement
such as the following (taken from Liferay's `JCRStore` class):

	private static Log _log = LogFactory.getLog(JCRStore.class);

You would then use this `_log` variable to create log messages in your code for
the various logging levels:

	_log.error("Reindexing " + node.getName(), e1);

To enable your logging messages to appear in your server's log file via the
control panel, click the *Add Category* tab on the same *Log Levels* page.

![Figure 18.8: Adding your own logging classes is easy. To add a logging class, just specify it in this field.](../../images/maintaining-add-log-category.png) 

You'll see you can add a logging category. Put in the fully qualified name of
your class or of the package that contains the classes whose log messages you
want to view, choose a log level, then click the *Save* button. You will now
start to see log messages from your own class or classes in the server's log
file.

Logs are great for figuring out issues in production. But what if Liferay
contacts you via its support channel with a bug fix or a security enhancement?
Read on to learn how to patch Liferay.
