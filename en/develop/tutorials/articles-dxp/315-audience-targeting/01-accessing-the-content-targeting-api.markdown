---
header-id: accessing-the-content-targeting-api
---

# Accessing the Content Targeting API

[TOC levels=1-4]

With the Content Targeting API you can integrate Audience Targeting features 
with third party applications or customize how Liferay's applications interact 
with Audience Targeting. For example, you could list user segments in your own 
application or update a campaign when someone creates a calendar event.

In this tutorial, you'll learn how to give your application access to the
Content Targeting API. Then you can view some examples of how to use the Java
and JSON APIs that are available. 

## Exposing the Content Targeting API

Configuring your app to have access to the Content Targeting API requires only
one line of code. This line of code is a dependency that should be added to your
project's build file. Follow the instructions below to add the Content Targeting
API dependency for a Gradle project.

1.  Open the `build.gradle` file in your app's project folder.

2.  Find the `dependencies { ... }` declaration and add the following line 
    within that section:

    ```groovy
    compileOnly group: "com.liferay.content-targeting", name: "com.liferay.content.targeting.api", version: 5.0.0"
    ```

Your app now has access to the Content Targeting API and can take advantage of
everything Audience Targeting has to offer. Next, you'll learn how to use the
Content Targeting API by studying a few examples.

## Using the Content Targeting Java API

You can call the Content Targeting API through Java or through JSON. 

To display a list of existing user segments in your portlet using Java,

1.  Obtain an implementation of the `UserSegmentLocalService` provided by
    Audience Targeting by adding the following code to your Portlet class (e.g.,
    the class that extends the `MVCPortlet` class):

    ```java
    @Reference(unbind = "-")
    protected void setUserSegmentLocalService(
        UserSegmentLocalService userSegmentLocalService) {

        _userSegmentLocalService = userSegmentLocalService;
    }

    private UserSegmentLocalService _userSegmentLocalService;
    ```

    When the Audience Targeting application is installed and an implementation 
    of the `UserSegmentLocalService` is available, the 
    `_userSegmentLocalService` field is populated. Otherwise, the portlet is
    unavailable till this dependency is resolved.

2.  Use the service to obtain a list of existing user segments and make it
    available to your view layer as a request attribute. To do this, add logic
    to your portlet class that obtains user segments and exposes them in
    a request attribute, like this:

    ```java
    ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
        WebKeys.THEME_DISPLAY);

    List<UserSegment> userSegments = null;

    try {
        userSegments = _userSegmentLocalService.getUserSegments(
            themeDisplay.getScopeGroupId());
    }
    catch (Exception e) {
        _log.error(e, e);
    }

    renderRequest.setAttribute("userSegments", userSegments);

    private static final Log _log = LogFactoryUtil.getLog(MyPortlet.class)
    ```

    Notice that the `userSegments` list is populated by calling
    `UserSegmentLocalService`'s `getUserSegments` method. This service is part
    of the Content Targeting API. 

3.  Add this logic to your portlet's `view.jsp`:

    ```markup
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
    ```

    This logic uses the `UserSegment` object to list the existing user segments.
    That's it! By importing the `UserSegment` and `UserSegmentLocalService` 
    classes into your files, you have direct access to your portal's user 
    segments through the Content Targeting Java API. 

## Using the Content Targeting JSON API

You could do the same thing using the JSON API.

1.  Open your portlet's `view.jsp` file and insert the following code:

    ```markup
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
    ```

    Notice that the Content Targeting API is called to retrieve the existing
    campaigns:

    ```markup
    Liferay.Service(
        '/ct.campaign/get-campaigns',
        {
    ```

    Then, each campaign is listed in the `campaignsList` and displayed in your
    portlet for users to see.

If you want to view all the available methods (with examples) exposed in the
JSON API by Audience Targeting, you can visit the `/api/jsonws` URL (e.g.,
`localhost:8080/api/jsonws`). As you can see, accessing the Content Targeting
JSON API is just as easy as accessing the related Java API.

You've learned how to expose the Content Targeting API and use it in
your application. Next you'll learn to create custom rule types.
