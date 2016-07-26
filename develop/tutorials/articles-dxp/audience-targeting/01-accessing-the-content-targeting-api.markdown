# Accessing the Content Targeting API

The [Audience Targeting](https://www.liferay.com/marketplace/-/mp/application/43707761)
application available in Liferay's Marketplace can be used to target content to
specific audiences. You might want to take the next step and use the
Content Targeting API. For instance, you could list user segments in your own
application or update a campaign when someone creates a calendar event. Using
the Content Targeting API, you can unleash the power of Audience Targeting to
the realms outside of Liferay's default applications.

In this tutorial, you'll learn how to give your application access to the
Content Targeting API. Then you can view some examples of how to use the Java
and JSON APIs that are available. 

## Exposing the Content Targeting API

Configuring your app to have access to the Content Targeting API requires only
one line of code. This line of code is a dependency that should be added to your
Gradle project. Follow the instructions below to add the Content Targeting API
dependency:

1. Open the `build.gradle` file in your app's project folder.

2. Find the `dependencies` declaration and add the following line within that
   declaration:

        provided group: "com.liferay.content-targeting", name: "com.liferay.content.targeting.api", version: "4.0.0"

    The complete declaration should look like this:

        dependencies {
            ...
            provided group: "com.liferay.content-targeting", name: "com.liferay.content.targeting.api", version: "4.0.0"
        }

Your app now has access to the Content Targeting API and can take advantage of
everything Audience Targeting has to offer. In the next section, you'll learn
how to use the Content Targeting API by studying a few examples.

## Using the Content Targeting API

There are two ways you'll learn how to call the Content Targeting API: making
direct calls to the Java API and making direct calls to the JSON API. 

Suppose you'd like to display a list of existing user segments in your portlet.
First you need to obtain an implementation of the `UserSegmentLocalService`
provided by Audience Targeting. You can do this by adding the following code to
your Portlet class (e.g., the class that extends the `MVCPortlet` class):

    @Reference(unbind = "-")
    protected void setUserSegmentLocalService(
        UserSegmentLocalService userSegmentLocalService) {

        _userSegmentLocalService = userSegmentLocalService;
    }

    private UserSegmentLocalService _userSegmentLocalService;

When an implementation of the `UserSegmentLocalService` is available (i.e., the
Audience Targeting app has been installed) the `_userSegmentLocalService` field
is set with it. Otherwise, the portlet won't be available till this dependency
is resolved.

It's a good practice to access Audience Targeting services this way instead of
using util classes (e.g., `UserSegmentLocalServiceUtil.java`). Not only is the
dependency management better, but you also won't be tied to a specific
implementation of the service.

The next step is to use the service to obtain a list of existing user segments
and make it available to your view layer as a request attribute. To do this, add
the following method to your portlet class:

    @Override
    public void doView(
            RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
            WebKeys.THEME_DISPLAY);

        List<UserSegment> userSegments = null;

        try {
            userSegments = _userSegmentLocalService.getUserSegments(
                themeDisplay.getScopeGroupId());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        renderRequest.setAttribute("userSegments", userSegments);

        super.doView(renderRequest, renderResponse);
    }

<!-- The above code is not quality code, for the following reasons: 1) Not all
portlets have doView methods. We should show only the relevant code. 2) In
catching an exception, you never keep the default implementation of printing the
stack trace. Please fix. -Rich -->

Notice that the `userSegments` list is populated by calling
`UserSegmentLocalService`'s `getUserSegments` method. This service is part
of the Content Targeting API. 

To finish off this example, some logic needs to be added to your portlet's
`view.jsp`:

    <h2>User Segments</h2>

    <ul>

    <%
    List<UserSegment> userSegments = (List<UserSegment>)request.getAttribute("userSegments");

    for (UserSegment userSegment : userSegments) {
    %>

        <li><%= userSegment.getName(locale) %></li>

    <%
    }
    %>

    </ul>

This logic uses the `UserSegment` object to list the existing user segments.
That's it! By importing the `UserSegment` and `UserSegmentLocalService` classes
into your files, you have direct access to your portal's user segments via the
Content Targeting Java API. 

You can also do this using the JSON API.

Suppose you'd like to display a list of existing campaigns in your portlet using
the JSON API. You could do this in your portlet by opening its `view.jsp` file
and using the following code:

    <h2>Campaigns</h2>

    <ul id="<portlet:namespace/>campaigns">
    </ul>

    <aui:script use="aui-base">
        var campaignsList = A.one('#<portlet:namespace/>campaigns');

        Liferay.Service(
            '/ct.campaign/get-campaigns',
            {
            groupId: '<%= scopeGroupId %>'
            },
            function(response) {
                if (response.length) {
                    A.Array.each(response, function(item) {
                        campaignsList.append('<li>' + item.name + '</li>');
                    });
                }
            }
        );
    </aui:script>

Notice that the Content Targeting API is called to retrieve the existing
campaigns:

    ...
    Liferay.Service(
        '/ct.campaign/get-campaigns',
        {
    ...

Then, each campaign is listed in the `campaignsList` and displayed in your
portlet for users to see.

If you'd like to view all the available methods (with examples) exposed in the
JSON API by Audience Targeting, you can visit the `/api/jsonws` URL (e.g.,
`localhost:8080/api/jsonws`). As you can see, accessing the Content Targeting
JSON API is just as easy as accessing the related Java API.

You've learned how easy it is to expose the Content Targeting API and use it in
your application to unleash its power!

## Related Topics

[Creating New Audience Rule Types](/develop/tutorials/-/knowledge_base/7-0/creating-new-audience-targeting-rule-types)

[Targeting Content to Your Audience](/discover/portal/-/knowledge_base/7-0/targeting-content-to-your-audience)

[Running Service Builder and Understanding the Generated Code](/develop/tutorials/-/knowledge_base/7-0/running-service-builder-and-understanding-the-generated-code)
