---
header-id: using-a-tracking-mechanism
---

# Using a Tracking Mechanism

[TOC levels=1-4]

An administrator has successfully configured and saved your custom metric to 
his or her report. Now what? Your metric needs to fulfill its purpose, which is 
to track the `view` event type for the defined newsletter. To do this, you must 
define a tracking mechanism. For your newsletter, you'll use a transparent 
image as the tracking mechanism, which would have the *View* tracking event 
capability. Whenever the image is viewed, the newsletter metric computes and 
stores the information.

For the newsletter metric, you'll use a tracking mechanism provided by the
Audience Targeting app.

1.  Set the analytics processor that the Content Targeting API provides
    for tracking events. Add the following method and private field:

    ```java
    @Reference
    protected void setAnalyticsProcessor(AnalyticsProcessor analyticsProcessor) {
        _analyticsProcessor = analyticsProcessor;
    }

    private AnalyticsProcessor _analyticsProcessor;
    ```

    The analytics processor contains a servlet to track analytics from Liferay 
    pages (views, clicks, etc.) and an API to leverage this tracking mechanism. 
    In the `setAnalyticsProcesoor(...)` method, you're obtaining a reference of 
    the current analytics processor to build the URL used to generate a 
    transparent image. All you have to do is insert the generated URL into your 
    newsletter's HTML, and the transparent image tracks who reads it. 
    Everything is processed by the default Audience Targeting Analytics system 
    automatically.

    Now that you've obtained a reference of the analytics processor, you need to
    add logic for generating the appropriate tracking URL.

2.  Replace the `populateContext` method with the updated method:

    ```java
    @Override
    protected void populateContext(
        TrackingActionInstance trackingActionInstance,
        Map<String, Object> context, Map<String, String> values) {

        String alias = StringPool.BLANK;
        String elementId = StringPool.BLANK;
        String eventType = StringPool.BLANK;
        String trackImageHTML = StringPool.BLANK;

        if (!values.isEmpty()) {
            alias = values.get("alias");
            elementId = values.get("elementId");
            eventType = values.get("eventType");
        }
        else if (trackingActionInstance != null) {
            alias = trackingActionInstance.getAlias();
            elementId = trackingActionInstance.getElementId();
            eventType = trackingActionInstance.getEventType();

            String trackImageURL = _analyticsProcessor.getTrackingURL(
                trackingActionInstance.getCompanyId(), 0, 0, "", 0,
                Campaign.class.getName(),
                new long[] {trackingActionInstance.getCampaignId()},
                trackingActionInstance.getElementId(), "view", "");

            trackImageHTML = "<img alt=\"\" src=\"" + trackImageURL + "\" />";
        }

        context.put("alias", alias);
        context.put("elementId", elementId);
        context.put("eventType", eventType);
        context.put("eventTypes", getEventTypes());
        context.put("trackImageHTML", trackImageHTML);
    }
    ```

    This updated method creates a new variable named `trackImageHTML`,
    retrieves a tracking URL using the analytics processor, and then populates
    the `trackImageHTML` context variable. When creating a new metric, the
    transparent image's URL field is not present in the metric's form. When the
    metric is initially saved, however, the URL is generated using the analytics
    processor and is available for copying.

Excellent! You've obtained the analytics processor and can create the
transparent image tracking mechanism. The newsletter metric's behavior is
defined and complete. The last thing you need to do is create a JSP template.
