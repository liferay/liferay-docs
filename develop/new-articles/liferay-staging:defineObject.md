# <liferay-staging:defineObjects> tag

**Dear Liferay devs**,

Thanks to the recent changes made to the <liferay-staging:defineObjects /> now it can (and should :) hint, hint) be used to help you out in your portlets in staging environment.
Basically, if you know that your portlet will be staged (ever), add this tag with your portlet’s portlet id to the portlet like this:

    <liferay-staging:defineObjects portletId="<%= PortletKeys.DOCUMENT_LIBRARY %>" />

After you add this you are good to go! Go ahead and use the group, groupId, liveGroup, liveGroupId, stagingGroup, stagingGroupId, scopeGroup, scopeGroupId, privateLayout variables on your JSPs as you will, they are in the pageContext so you don't have to declare them!

To force the correct usage of this tag, it now handles the variables a bit more strictly, so you should be aware of these things:

1. By default (if staging is turned OFF for the site) it only sets group, groupId and the privateLayout variables. The others (liveGroup, liveGroupId, stagingGroup, stagingGroupId) will be null, 0L, null, 0L respectively, so you should properly handle this in your JPSs. This way you can tell if staging is on or not, since the liveGroup and stagingGroup will only be set, if staging is turned on.

2. If staging is ON, you can use the liveGroup, liveGroupId, stagingGroup, stagingGroupId variables also.

3. If you set the portletId in the tag and the portlet is staged, you can rely on the scopeGroup, scopeGroupId and also the group and groupId variables also which will be the same as the stagingGroup and stagingGroupId respectively.


**TL;DR** Add the tag with the correct portletId to your JSP and you are all set up!

Hope this can help you guys. Regards,<br>
Levente Hudák<br>
Core Software Engineer @ Liferay Hungary
