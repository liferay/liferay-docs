# Managing LCS Servers [](id=managing-lcs-servers)

Clicking a server in the Dashboard or environment view takes you to the server 
view. Server view provides detailed information about a server, including 
statistics and performance metrics. To protect your users' privacy, LCS doesn't 
gather, store, or analyze user data. 

Server view is segmented into six tabs: 

**Page Analytics:** Displays metrics on page views and load times.

**Snapshot Metrics:** Displays application, JVM, and server metrics.

**Fix Packs:** Displays the server's available and installed fix packs.

**Portal Properties:** Displays your portal's properties and their settings.

**Details:** Displays general information about your @product@ installation, 
Java version, and hardware.
 
**Server Settings:** View or change your server's name, location, and 
description. You can also unregister the server from LCS. 

+$$$

**Note:** LCS doesn't support Snapshot Metrics for servers running on JBoss or 
WildFly. In this scenario, you may see a console message indicating that LCS 
doesn't support server metrics for your application server. You may also see a 
benign `NullPointerException` for the LCS `TaskSchedulerServiceImpl` and 
`ScheduleTasksCommand`. 

$$$

## Page Analytics [](id=page-analytics)

Page Analytics appears by default when you enter server view. Page Analytics
shows page views and load times for the selected site and time period. By
default, all sites are selected. You can select a specific site from the *Site*
selector menu. You can also select a different time period in the *Period* and
*Ending At* fields. The arrows next to the *Ending At* field move the selected
time period up or down, respectively, by one period. For example, if you select
*One Hour* in the *Period* field, pressing the right arrow next to *Ending At*
moves the selected time period up by one hour. Note that at the beginning of the
current time period, it can take up to 15 minutes for data to become available.
Also note that data is available for three months from the time LCS collected
it. 

By default, load times and page views for all pages are plotted against time in 
separate graphs. Below these graphs, a table displays summary statistics of data
over the same time period, for each page. If you click a page in the table, the
graphs plot the data for just that page. If you can't find the page you're
looking for, you can search for it in the *Search* box at the top of the table.
To plot data for all pages again, click the *All Pages* row at the bottom of the
table. 

Load times are also color coded to indicate speed. The *Load Times* graph's 
background is red for values above 3,000 ms, orange for values from 2,000 to 
3,000 ms, and green for values less than 2,000 ms. Likewise, the table displays 
all load times greater than 3,000 ms in red text. 

![Figure 1: The Page Analytics interface in the LCS Server view.](../../../images-dxp/lcs-page-analytics-01.png)

## Snapshot Metrics [](id=snapshot-metrics)

To view other metrics and statistics of your server's performance, click the 
*Snapshot Metrics* tab near the top of the page. These metrics are broken down 
into three main categories: *Application*, *JVM*, and *Server*. Application is 
selected by default when you click the Snapshot Metrics button. 

The Application category also has three other categories: *Pages*, *Portlets*, 
and *Cache*. Pages lists the frequency that specific pages load, along with
their average load times. Portlets lists the same statistics, but for specific
portlets in your server. The Cache category lists Liferay Single VM metrics and
Hibernate metrics. The following screenshot shows the statistics in the Portlets
category.

![Figure 2: The LCS application metrics show portlet performance statistics, like frequency of use and average load time.](../../../images-dxp/lcs-server-metrics-application-portlets.png)

The JVM category, as its name indicates, shows statistics about the JVM running 
on your server. This includes data on the garbage collector and memory. The 
number of runs, total time, and average time are listed for each garbage 
collector item. The memory metrics are presented in a bar chart that shows the 
usage of the PS Survivor Space, PS Old Gen, PS Eden Space, Code Cache, and PS 
Perm Gen.

![Figure 3: The LCS JVM metrics show performance data for memory and the garbage collector.](../../../images-dxp/lcs-server-metrics-jvm.png)

Server is the third category in Snapshot Metrics. The Server category shows 
additional information about how your server is running. For example, a 
horizontal bar graph shows the number of current threads running on your server. 
Similarly, horizontal bar graphs represent the JDBC connection pools. 

![Figure 4: The LCS server metrics show current threads and JDBC connection pools.](../../../images-dxp/lcs-metrics-server.png)

Note that in Snapshot Metrics, the application and garbage collector metrics are 
based on data collected by LCS from server registration to the present. Memory 
and server metrics, however, show only the current state. 

## Fix Packs [](id=fix-packs)

To view your server's fix packs, click the Fix Packs tab near the top of the 
page. The available and installed fix packs appear in separate tables. The 
available fix packs table functions exactly like the Fix Packs table in 
environment view for downloading and installing fix packs. 

![Figure 5: The Fix Packs tab displays your server's available and installed fix packs.](../../../images-dxp/lcs-server-fix-packs.png)

## Portal Properties [](id=portal-properties)

The *Portal Properties* tab lets you view your portal's property values in
a searchable table. This gives you a convenient display for your portal property
settings. The properties in this table are organized into the following
categories: 

**Default Values:** The default values for your portal's properties. 

**Custom Values:** Any custom values you've set for your portal's properties. 
This includes any property values you change via a `portal-ext.properties` 
file.

**Dynamic Properties:** Any property values set at runtime. For example, the 
[Liferay Home](https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/installing-product#liferay-home) 
folder's location depends on your configuration. To specify this folder when
setting any properties that require it, use `${liferay.home}` instead of an
absolute directory path. 

You can display any combination of these categories by selecting the 
corresponding checkboxes from the gear icon next to the search box at the 
top-right of the table. For example, by checking the *Show Default Values* and 
*Show Custom Values* checkboxes, the table shows your portal's default and 
custom property values. To show only the custom values, select only *Show Custom 
Values*. 

![Figure 6: Click the gear icon to select the type of portal properties to show in the table.](../../../images-dxp/lcs-server-portal-properties.png)

## Details [](id=details)

The *Details* tab shows general information about your server. There are three 
tabs under Details: *Software*, *Java*, and *Hardware*. Each shows information, 
respectively, about your @product@ installation, Java installation, and 
hardware. This information is useful to the Liferay Support team in the event 
you need their assistance. 

![Figure 7: The Details tab shows information about your server.](../../../images-dxp/lcs-server-details.png)

## Server Settings [](id=server-settings)

Finally, the *Server Settings* tab lets you view and edit your server's name,
location, and description. You can also unregister your server from LCS. 

![Figure 8: You can use the Server Settings tab to give your server a fun name.](../../../images-dxp/lcs-server-settings.png)
