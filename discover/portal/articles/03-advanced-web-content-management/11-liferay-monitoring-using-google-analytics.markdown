# Liferay Monitoring using Google Analytics [](id=liferay-monitoring-using-google-analytics)

Liferay includes built-in support for Google Analytics, allowing administrators
to make use of Google's tool set for analyzing site traffic data. When you sign
up for Google Analytics, a snippet of code is provided which needs to be added
to your web pages to allow Google's system to register the page hit. It can be a
tedious process to add this code to every page on a site, especially if it's a
large site and there is a lot of user-generated content.

This problem can be solved in Liferay by putting Google's code into a custom
theme written specifically for the web site on which the portal is running.
Doing this, however, requires a theme developer to make specific changes to the
theme and it prevents users from using the many themes that are freely available
for Liferay "out of the box."

Because of this, support for Google Analytics has been built into Liferay, and
can be turned on through a simple user interface. This allows Liferay
administrators to make use of Google Analytics on a site by site basis and turn
it on and off when needed. You can sign up for Google Analytics at the Google
Analytics site here:
[http://www.google.com/analytics](http://www.google.com/analytics).

To enable Google Analytics support, navigate to *Site Administration* in the
Control Panel, expand the *Configuration* area in menu at the left side of the
screen, then click on *Site Settings*. Click on *Analytics* and you'll see a
very simple form, pictured below. 

![Figure 3.32: Setting up Google Analytics for your site is very easy: sign up for Google Analytics, receive an ID, and then enter it into the Google Analytics ID field.](../../images/maintaining-google-analytics.png)

Enter your Google Analytics ID (which should have been provided to you when you
signed up for the service) in the field and click *Save*. All the pages in the
site you selected will now have the Google Analytics code in them and will be
tracked. 

This is a fairly simple procedure, and it gives you the ability to take
advantage of some great tools to help you visualize who's coming to your site
and from where. 
