# Accessing the Content Targeting API [](id=accessing-the-content-targeting-api)

The [Audience Targeting](https://www.liferay.com/marketplace/-/mp/application/43707761)
application available in Liferay's Marketplace can be used to target content to
specific audiences. You might want to take this to the next step and use the
Content Targeting API. For instance, you could list user segments in your own
application or update a campaign when someone creates a calendar event. Using
the Content Targeting API, you can unleash the power of Audience Targeting to
the realms outside of Liferay's default applications.

In this tutorial, you'll learn how to give your application access to the
Content Targeting API. Then you can view some examples of how to use the Java
and JSON APIs that are available. 

## Exposing the Content Targeting API [](id=exposing-the-content-targeting-api)

Configuring your app to have access to the Content Targeting API requires only
one line of code. This line of code is a dependency that should be added to your
dependencies list via Ivy.  Follow the instructions below to add the Content
Targeting API dependency:

1.  Open the `ivy.xml` file in your app's `ROOT` folder.

2.  Find the `<dependencies>` tag and place the following `dependency` within
    that tag. 

        <dependency org="com.liferay.content-targeting" name="com.liferay.content.targeting.api" rev="2.0.0"/>

    The complete tag should look like this: 

        <dependencies defaultconf="default">
            <dependency org="com.liferay.content-targeting" name="com.liferay.content.targeting.api" rev="2.0.0"/>
        </dependencies>

Your app now has access to the Content Targeting API and can now take advantage
of everything Audience Targeting has to offer. In the next section, you'll learn
how to use the Content Targeting API by studying a few examples.

## Using the Content Targeting API [](id=using-the-content-targeting-api)

There are two ways you'll learn how to call the Content Targeting API: making
direct calls to the Java API and making direct calls to the JSON API. You'll
dive into calling the Java API first.

Suppose you'd like to display a list of existing user segments in your portlet.
You could do this by opening your Portlet class (the class that extends the
`MVCPortlet` class) and adding the following method:

    @Override
    public void doView(
            RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
            WebKeys.THEME_DISPLAY);

        List<UserSegment> userSegments = null;

        try {
            userSegments = UserSegmentLocalServiceUtil.getUserSegments(
                themeDisplay.getScopeGroupId());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        renderRequest.setAttribute("userSegments", userSegments);

        super.doView(renderRequest, renderResponse);
    }

Notice that the `userSegments` list is populated by calling
`UserSegmentLocalServiceUtil`'s `getUserSegments` method. This service is part
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

This logic uses the `UserSegment` object to list the existing user segments in
the Portal. That's it! By importing the `UserSegment` and
`UserSegmentLocalServiceUtil` classes into your files, you have direct access to
your portal's user segments via the Content Targeting Java API. 

Lastly, you'll explore a small example of accessing the Content Targeting
JSON API.

Suppose you'd like to display a list of existing campaigns in your portlet using
the JSON API. You could do this in your portlet by opening its `view.jsp` file
and using the following code:

    <h2>Campaigns</h2>

    <ul id="<portlet:namespace/>campaigns">
    </ul>

    <aui:script use="aui-base">
        var campaignsList = A.one('#<portlet:namespace/>campaigns');

        Liferay.Service(
            'content-targeting-api.campaign/get-campaigns',
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
        'content-targeting-api.campaign/get-campaigns',
        {
    ...

Then, each campaign is listed in the `campaignsList` and displayed in your
portlet for users to see.

If you'd like to view all the available methods (with examples) exposed in the
JSON API by Audience Targeting, you can visit your portal's `/api/jsonws` URL
(e.g., `localhost:8080/api/jsonws`). As you can see, accessing the Content
Targeting JSON API is just as easy as accessing the related Java API.

You've learned how easy it is to expose the Content Targeting API and use it in
a custom app to unleash its power! If you'd like to peruse the entire Content
Targeting API, visit the API's
[Github page](https://github.com/liferay/liferay-apps-content-targeting/tree/master/content-targeting-api).
If you're interested in seeing the examples that were discussed in this tutorial
working in a finished portlet, download and deploy the
[Sample Client Portlet](/documents/10184/487286/sample-client-portlet-6.2.0.1.war).

## Related Topics [](id=related-topics)

[Creating New Audience Rule Types](/develop/tutorials/-/knowledge_base/6-2/creating-new-audience-targeting-rule-types)

[Targeting Content to Your Audience](/discover/portal/-/knowledge_base/6-2/targeting-content-to-your-audience)

[Running Service Builder and Understanding the Generated Code](/develop/tutorials/-/knowledge_base/6-2/running-service-builder-and-understanding-the-generated-code)
