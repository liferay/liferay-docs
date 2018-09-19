# Defining the Metric's UI [](id=defining-the-metrics-ui)

The Java code you've added to this point has assumed that there are three
configurable fields for your newsletter metric: 

- *Name:* used in reports that count the number of times a metric has been
  triggered. This is also known as the newsletter's alias.
- *Newsletter ID:* used to differentiate between newsletters.
- *Event Type:* used to differentiate several actions on the same newsletter,
  such as opening the newsletter or clicking on a link.

To let administrators set these values, you must define a UI so your metric can
be configured during the view/save lifecycle. Remember that you must also define
a field to display the generated transparent image's URL. Create a `view.jsp`
file in your metric's module (e.g.,
`/src/main/resources/META-INF/resources/view.jsp`) and add the following logic:

    <%
    Map<String, Object> context = (Map<String, Object>)request.getAttribute("context");

    String alias = (String)context.get("alias");
    String elementId = (String)context.get("elementId");
    String eventType = (String)context.get("eventType");
    List<String> eventTypes = (List<String>)context.get("eventTypes");
    String trackImageHTML = (String)context.get("trackImageHTML");
    %>

    <aui:input helpMessage="name-help" label="name" name='<%= ContentTargetingUtil.GUID_REPLACEMENT + "alias" %>' type="text" value="<%= alias %>">
        <aui:validator name="required" />
    </aui:input>

    <aui:input helpMessage="enter-the-id-of-the-newsletter-to-be-tracked" label="newsletter-id" name='<%= ContentTargetingUtil.GUID_REPLACEMENT + "elementId" %>' type="text" value="<%= elementId %>">
        <aui:validator name="required" />
    </aui:input>

    <c:if test="<%= ListUtil.isNotEmpty(eventTypes) %>">
        <aui:select label="event-type" name='<%= ContentTargetingUtil.GUID_REPLACEMENT + "eventType" %>'>

            <%
            for (String curEventType : eventTypes) {
            %>

                <aui:option label="<%= curEventType %>" selected="<%= curEventType.equals(eventType) %>" value="<%= curEventType %>" />

            <%
            }
            %>

        </aui:select>
    </c:if>

    <c:if test="<%= !Validator.isBlank(trackImageHTML) %>">
        <span class="h5">
            <liferay-ui:message key="paste-this-code-at-the-beginning-of-your-newsletter" />
        </span>
        <label for='<%= renderResponse.getNamespace() + ContentTargetingUtil.GUID_REPLACEMENT + "trackImageHTML" %>' key="paste-this-code-at-the-beginning-of-your-newsletter" /></label>

        <liferay-ui:input-resource id='<%= renderResponse.getNamespace() + ContentTargetingUtil.GUID_REPLACEMENT + "trackImageHTML" %>' url="<%= trackImageHTML %>" />
    </c:if>

First you instantiate the `context` variable and its attributes you configured
in your Java class's `populateContext` method. Then you specify the appropriate
fields Name, Newsletter ID, and Event Type. Finally, you present the generated
transparent image URL.

Notice that the input field names in the JSP are prefixed with
`ContentTargetingUtil.GUID_REPLACEMENT`. This prefix is required for
multi-instantiable metrics, which are metrics that return `true` in the
`isInstantiable` method of their `-TrackingAction` class and can be added more
than once to the Metrics form.

![Figure 3: Once you've saved the metric, you can copy the generated transparent image URL into your newsletter's HTML to track who views it.](../../images-dxp/metric-generated-url.png)

Congratulations! You've created the newsletter metric and can now track whether
users viewed a newsletter. You can test if the metric is working by copying the
generated tracking image HTML into an email HTML editor, sending it, and opening
it as if it were an actual newsletter. Then open the custom report containing
the newsletter metric and select *Update Report*. A chart and table with the
newsletter's view count is shown.

You can view the finished version of the newsletter metric by downloading its
[ZIP file](https://customer.liferay.com/documents/10738/200086/newsletter.zip/589ea9a1-9473-4409-acc6-c41c6d20728a).

Now you've created and examined a fully functional metric and have the knowledge
to create your own.
