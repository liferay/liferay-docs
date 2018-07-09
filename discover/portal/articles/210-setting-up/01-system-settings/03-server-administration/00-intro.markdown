# Server Administration [](id=server-administration)

Server Administration lets you manage and monitor your @product@ server. Access
the application by clicking *Control Panel* &rarr; *Configuration* &rarr;
*Server Administration*. 

![Figure 1: The Resources tab of Server Administration shows a graph of your server's memory usage.](../../../../images/server-admin-memory.png)

Server Administration's functionality is segmented into these tabs: 

**Resources:** View memory usage and perform management tasks like running 
the garbage collector, clearing the database cache, and more. For more 
information, see 
[Resources](/discover/portal/-/knowledge_base/7-1/server-administration-resources).

**Log Levels:** View and set logging levels. You can make dynamic 
modifications of log levels for any class hierarchy in @product@. Custom 
objects not on the list can be added with the *Add Category* tab. Changes to 
the log level near the top of the class hierarchy (such as at `com.liferay`) 
also change log levels for all the classes under that hierarchy. 
Modifications unnecessarily high in the hierarchy generate too many messages 
to be useful. 

**Properties:** View JVM and portal properties. This tab has two sub-tabs: 
System Properties and Portal Properties. The System Properties tab shows an 
exhaustive list of system properties for the JVM, as well as many @product@ 
system properties. You can use this information for debugging purposes or to 
check the currently running configuration. The Portal Properties tab shows 
an exhaustive list of the current portal property values. For explanations 
of these properties, see the 
[portal properties reference documentation](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html). 

**Data Migration:** Migrate documents from one repository to another. For 
example, you can migrate your documents to a new repository on a different 
disk or in a new format. Before running this operation, 
[configure the repository hook](/discover/deployment/-/knowledge_base/7-1/document-repository-configuration)
in a `portal-ext.properties` file. Then, in this tab, select the Document 
Library hook that represents the desired destination and click *Execute*. 
After the process completes, shut down @product@, make the new repository 
the default in the `portal-ext.properties` file, and restart. 

**Mail:** Instead of using a `portal-ext.properties` file to configure a 
mail server, you can configure a mail server from this tab. If your 
[message boards receive mail](/discover/portal/-/knowledge_base/7-1/user-subscriptions-and-mailing-lists), 
you can connect a POP mail server. If @product@ sends mail (useful for 
sending notifications to users), you can connect to an SMTP server. Note 
that if you configure mail server settings here in System Settings, these 
settings override any mail server settings in your `portal-ext.properties` 
file. 

**External Services:** Configure external services for generating file 
previews. For more information, see 
[the article on External Services](/discover/portal/-/knowledge_base/7-1/server-administration-external-services).

**Script:** A scripting console for executing migration or management code. 
The Groovy scripting language is supported out of the box. <!-- See the 
scripting article(/discover/portal/-/knowledge_base/7-1/using-liferays-script-engine)
for more information and examples on using the scripting console-->. 

**Shutdown:** Schedule a shutdown that notifies logged-in users of the
impending shutdown. You can define the number of minutes until the shutdown and
a message to display. @product@ displays the message at the top of users' pages
for the duration of time you specified. When the time expires, all pages
display a message saying the portal has been shut down. The server must then be
restarted to restore access. 

