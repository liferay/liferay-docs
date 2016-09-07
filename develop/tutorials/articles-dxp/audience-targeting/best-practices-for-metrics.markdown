# Best Practices for Metrics

In this tutorial, you'll learn about best practices to keep in mind when
creating Audience Targeting Metrics. Before going through some best practices,
you should understand the four components you can specify for a metric:

- *Rule Behavior*
- *Tracking Mechanism*
- *UI for Configuration (optional)*
- *Language Keys (optional)*

You discuss metric behavior and its UI configuration in great detail in the
[Tracking User Actions with Audience Targeting](/develop/tutorials/-/knowledge_base/7-0/tracking-user-actions-with-audience-targeting)
tutorial. To learn more about language keys and how to create, use, and generate
them, visit the
[Internationalization](/develop/tutorials/-/knowledge_base/7-0/internationalization)
tutorials.

Audience Targeting gives you the option to choose whatever frontend technology
you like. In the next section, you'll learn how to use your preferred technology
for displaying content in Audience Targeting metrics.

## Selecting a UI Technology

Since Liferay 7.0, JSP is the preferred technology for Audience Targeting
extension views. FreeMarker views, however, are still supported through their
respective base classes (e.g., `BaseFreemarkerRule`). If you're interested in
using a technology besides JSP or FreeMarker to implement your UI, you can add a
method `getFormHTML` to your `-Rule` class. Here's an example of implementing
the `getFormHTML` method:

    @Override
    public String getFormHTML(
        TrackingActionInstance trackingActionInstance,
        Map<String, Object> context, Map<String, String> values) {

        String content = "";

        try {
            populateContext(trackingActionInstance, context, values);

            content = ContentTargetingContextUtil.includeJSP(
                _servletContext, getFormTemplatePath(), context);
        }
        catch (Exception e) {
            _log.error(
                "Error while processing form template " +
                    getFormTemplatePath(),
                e);
        }

        return content;
    }

The `getFormHTML` is used to retrieve the HTML created by the technology you
choose, and to return it as a string that is viewable from your metric's form.
If you plan, therefore, on using an alternative to JSP or FreeMarker, you
must override this method by creating and modifying it in your `-TrackingAction`
class.

## Related Topics [](id=related-topics)

[Tracking User Actions with Audience Targeting](/develop/tutorials/-/knowledge_base/7-0/tracking-user-actions-with-audience-targeting)

[Internationalization](/develop/tutorials/-/knowledge_base/7-0/internationalization)

[Service Builder Persistence](/develop/tutorials/-/knowledge_base/7-0/service-builder-persistence)
