# What's New in Liferay 6.2 APIs? [](id=whats-new-in-liferay-6-2-apis-liferay-portal-6-2-dev-guide-en)

---

![Note](../../images/tip-pen-paper.png) This chapter is under construction. 

---

Liferay Portal 6.2 offers a host of new features and updates to the previous
release. Our guide to *Using Liferay Portal 6.2* shows you how to use these
features and updates and this guide shows you how to leverage them in the
applications you develop. In this chapter, we want to highlight some of the
changes to Liferay Portal's application programming interface (API). We've added
APIs for the new features and improved APIs for previously existing features. In
some cases we've modified portal's API and removed previously deprecated
interfaces. The Javadoc for Liferay Portal's entire API is availabe at
[http://docs.liferay.com/portal/6.2/](http://docs.liferay.com/portal/6.2/). But
we'll describe some of the most notable additions and changes here in this
chapter. 

<!-- NOTE TO CONTRIBUTERS

Add content describing your API changes. If a section related to your feature
already exists, integrate your content with that section. Otherwise, add a new
section and content describing the your feature's new or modified API. 

Example,

    ## FeatureXYZ

    Describe your new/modified API here

Briefly describe your feature, even if it's an existing feature from the
previous release. Explain what the new or modified API does and whow how to use
it by way of code example. 

If your feature is already described in another chapter of the Dev Guide,
consider describing the API change there instead of here in this chapter. If you
do write about it in another chapter, still mention the API change here in this
chapter and refer to that other chapter and section. That way readers can locate
your API change description from this chapter.

Example,

    ## Message Bus FeatureXYZ

    You can now to x, y, and z in the Message Bus API. See the Using Message Bus
    section of Chapter 6 for details. 

-->

## Application Display Templates [](id=application-display-templates-liferay-portal-6-2-dev-guide-14-en)

A portlet's Display Settings (*Configuration* &rarr; *Setup* &rarr; *Display
Settings*) let you customize its display. They come built in with Liferay, so
you don't have to do anything special to enable them for your custom portlets.
But what if you need settings in addition to Liferay's display settings? You
could develop a theme or hook with the display options you need, but it'd be
nice if you could apply particular display options to specific portlet
instances without having to redeploy any plugins. Ideally, you should be able
to provide authorized portal users the ability to apply custom display settings
to portlets. This would save portlet developers from having to change portlet
configuration code every time you need new settings. 

Be of good cheer! That's precisely what Application Display Templates (ADTs)
provide-- the ability to add custom display settings to your portlets from the
portal. This isn't actually a new concept in Liferay. In some portlets (e.g.,
*Web Content*, *Documents and Media*, and *Dynamic Data Lists*, you can already
add as many display options (or templates) as you want. Now you can add them to
your custom portlets, too.

You can use the Application Display Templates API to add this new feature to
your plugins. Let's get started learning how.

### Using the Application Display Templates API [](id=application-display-templates-api-liferay-portal-6-2-dev-guide-14-en)

To leverage the ADT API, there are several steps you need to follow. These
steps involve registering your portlet to use ADTs, defining permissions, and
exposing the ADT functionality to users. 

1.  Register your custom `PortletDisplayTemplateHandler`

    To join the exclusive ADT club, your portlet must sign a contract,
    committing itself to fulfill all the Application Display Templates
    requirements. In other words, you have to create your own
    `PortletDisplayTemplateHandler` implementation by extending the
    `BasePortletDisplayTemplateHandler` methods. You can check the
    `TemplateHandler` interface
    [javadoc](http://docs.liferay.com/portal/6.2/javadocs/) to learn about each
    method.
 
    Once you've created the handler, declare it in the proper section of your
    `liferay-portlet.xml`, like this:

        <?xml version="1.0"?>
        <!DOCTYPE liferay-portlet-app PUBLIC "-//Liferay//DTD Portlet Application 6.2.0//EN"
        "http://www.liferay.com/dtd/liferay-portlet-app_6_2_0.dtd">

        <liferay-portlet-app>
            <portlet>
                <portlet-name>MyApp</portlet-name>
                <template-handler>
                    org.my.app.template.MyAppPortletDisplayTemplateHandler
                </template-handler>
            </portlet>
        </liferay-portlet-app>

2.  Declare permissions

    Since the ability to add Application Display Templates is new to your
    portlet, you should ensure you can grant specific permissions for it. Just add
    the action key `ADD_PORTLET_DISPLAY_TEMPLATE` to your portlet's `default.xml`
    file, like this:

        <?xml version="1.0"?>
        <!DOCTYPE resource-action-mapping PUBLIC 
        "-//Liferay//DTD Resource Action Mapping 6.2.0//EN"
        "http://www.liferay.com/dtd/liferay-resource-action-mapping_6_2_0.dtd">

        <resource-action-mapping>
            <portlet-resource>
                <portlet-name>MyApp</portlet-name>
                <permissions>
                    <supports>
                        <action-key>ADD_PORTLET_DISPLAY_TEMPLATE</action-key>
                    <supports>
                <permissions>
            <portlet-resource>
        <resource-action-mapping>
	 
3.  Add display settings to your portlet's `configuration.jsp`

    Now that your portlet officially supports Application Display Templates,
    you'll want to expose the ADT option to your users. Just include the
    `liferay-ui:ddm-template-selector` taglib in your portlet's `configuration.jsp`,
    providing the required information, like this:
 
        <aui:form action="<%= configurationURL %>" method="post" name="fm">
            <aui:fieldset>

            <%
             TemplateHandler templateHandler = TemplateHandlerRegistryUtil.getT\
             emplateHandler(MyType.class.getName());
            %>

            <liferay-ui:ddm-template-selector
                classNameId="<%= PortalUtil.getClassNameId(templateHandler.getC\
                lassName()) %>"
                displayStyle="<%= displayStyle %>"
                displayStyleGroupId="<%= displayStyleGroupId %>"
                refreshURL="<%= PortalUtil.getCurrentURL(request) %>"
                showEmptyOption="<%= true %>"
            />

            </aui:fieldset>
         </aui:form>
 
4.  Render Application Display Templates in your views

    <!--I found that this actually goes into the portlet's view_resources.jsp
    (in docroot/html/portletname), by searching the jukebox portlet-->

    You're almost finished, but you still have to extend your view code to
    render your portlet with the selected Application Display Template. Here is
    where you decide exactly which part of your view will be rendered by the
    Application Display Template and what will be available in the template
    context. You'll be adding some code to your `view_resources.jsp`, similar to
    this:
 
        <%
        List<MyType> myList = getMyList();
    
        long ddmTemplateId =
            PortletDisplayTemplateUtil.getPortletDisplayTemplateDDMTemplateId(
                displayStyleGroupId, displayStyle);
        Map<String, Object> contextObjects = new HashMap<String, Object>();
        contextObjects.put("myExtraObject", someExtraObject);
        %>
    
        <c:choose>
            <c:when test="<%= portletDisplayDDMTemplateId > 0 %>">
                 <%= PortletDisplayTemplateUtil.renderDDMTemplate(pageContext, \
                 ddmTemplateId, myList, contextObjects) %>
            </c:when>
            <c:otherwise>
                <%= //Default view code %>
            </c:otherwise>
        </c:choose>

<!--Do we need to explain exactly what this code block would do if someone copied it in verbatim?-->

<!-- We need to point out where this code specifies the rendering based on the
ADT. It's not obvious how it works. Jim --> 

<!--I also need to follow these steps for the event listing portlet and add the
appropriate code so it is a concrete example that actually accomplishes
something. Russ -->

### Recommendations [](id=adt-recommendations-liferay-portal-6-2-dev-guide-14-en)

You've harnessed a lot of power by learning to leverage the ADT API. Be
careful, for with great power, comes great responsibility! To that end, let's
talk about some practices you can use to to optimize your portlet's performance
and security. 
 
First let's talk about security. You may want to hide some classes or packages
from the template context, to limit the operations that ADTs can perform on
your portal. Liferay provides some portal properties to define the restricted
classes, packages, and variables. You can override the following portal
properties via the `portal-ext.properties` file.

    freemarker.engine.restricted.classes 
    freemarker.engine.restricted.packages
    freemarker.engine.restricted.variables
    velocity.engine.restricted.classes 
    velocity.engine.restricted.packages
    velocity.engine.restricted.variables

In particular, you may want to add `serviceLocator` to the list of default
values assigned to the `freemarker.engine.restricted.variables` and
`velocity.engine.restricted.variables` portal properties. Make sure to only add
to the classes, packages, and variables restricted by default by
`portal.properties`. Descriptions of Liferay Portal's FreeMarker engine and
Velocity engine properties are available on
[docs.liferay.com](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html). 

Application Display Templates introduce additional processing tasks when your
portlet is rendered. To minimize negative effects on performance, make your
templates as minimal as possible by focusing on the presentation, while using
the existing API for complex operations. The best way to make Application
Display Templates efficient is to know your template context well, and
understand what you can use from it. Fortunately, you don't need to memorize
the context information, thanks to Liferay's advanced template editor!

<!-- It's not clear what the template editor provides with respect to context
info. It would be good to clarify what it provides and/or how it provides that
info. Jim --> 

Finally, don't forget to run performance tests and tune the template cache
options by overriding the following portal properties: 

    freemarker.engine.resource.modification.check.interval 
    velocity.engine.resource.modification.check.interval

<!-- Before transitioning into the next section, we should summarize what the
reader has learned from this section about ADTs. Jim -->

No matter which Liferay APIs you're using, you'll need to understand Liferay's
deprecation policy. That way you'll know when methods from our API's are
deprecated, and you can make any necessary changes. We'll describe the
deprecation policy next. 

## Liferay's Deprecation Policy [](id=liferays-deprecation-policy-liferay-portal-6-2-dev-guide-02-en)

Methods in Liferay's APIs are deprecated when they're no longer called by
Liferay internally. Method deprecation occurs during major and minor releases of
Liferay. A change in the first or second digits of consecutive Liferay releases
indicates a major or minor release, respectively. For example, the release of
Liferay Portal 6.2.0 after 5.2.0 was a major release; whereas the release of
6.2.0 after 6.1.30 was a minor release. Major and minor releases can have API
deprecations. 

APIs should not be deprecated between mainenance releases. Mainenance releases
are signified by a change in the third digit of the release number. For example,
the release of Liferay Portal 6.1.30 after 6.1.20 was a maintenance release and
therefore should have no API deprecations. 

To understand Liferays releases, see [Using Liferay Portal
6.2](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/understanding-liferays-releases-liferay-portal-6-2-user-guide-15-en)

## Summary [](id=summary-liferay-portal-6-2-dev-guide-14-en)

That about wraps up our chapter on Liferay's APIs. Next, we'll reflect on what
we've learned in this guide and conclude our journey together. 
