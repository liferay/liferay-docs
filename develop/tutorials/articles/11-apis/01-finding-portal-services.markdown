# Finding Portal Services [](id=finding-portal-services)

Liferay's Javadocs are easy to browse and well-organized. Here's how you find
the *Organization* services: 

1. In your browser, open up the Javadocs:
   [http://docs.liferay.com/portal/6.1/javadocs/](http://docs.liferay.com/portal/6.1/javadocs/) 

2. Under *Portal Services*, click the link for the `com.liferay.portal.service`
   package, since the services for the Organization entity belong to the
   *Portal* scope. 

3. Find and click on the `-ServiceUtil` class (in this case
   `OrganizationLocalServiceUtil`) in the class summary table or the list of
   classes.

It's just that easy!

Similarly, if you want to search for one of Liferay's built-in portlet services,
no problem. But, when looking up the package, instead of clicking on the link
for the service package of the *portal*, click on the link for the service
package of the *portlet*. The portlet service packages use the naming convention
`com.liferay.portlet.[portlet-name].service`, where `[portlet-name]` is replaced

Now you're ready to invoke Liferay services.
