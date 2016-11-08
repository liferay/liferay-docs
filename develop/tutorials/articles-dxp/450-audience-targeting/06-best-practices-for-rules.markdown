# Best Practices for Rules [](id=best-practices-for-rules)

In this tutorial, you'll learn about best practices to keep in mind when
creating Audience Targeting Rules. Before going through some best practices, you
should understand the three components you can specify for a rule:

- *Rule Behavior*
- *UI for Configuration (optional)*
- *Language Keys (optional)*

You discuss rule behavior and its UI configuration in great detail in the
[Creating New Audience Targeting Rule Types](/develop/tutorials/-/knowledge_base/7-0/creating-new-audience-targeting-rule-types)
tutorial. To learn more about language keys and how to create, use, and generate
them, visit the
[Internationalization](/develop/tutorials/-/knowledge_base/7-0/internationalization)
tutorials.

Audience Targeting gives you the option to choose whatever frontend technology
you like. In the next section, you'll learn how to use your preferred technology
for displaying content in Audience Targeting rules.

## Selecting a UI Technology [](id=selecting-a-ui-technology)

Since Liferay 7.0, JSP is the preferred technology for Audience Targeting
extension views. FreeMarker views, however, are still supported through their
respective base classes (e.g., `BaseFreemarkerRule`). If you're interested in
using a technology besides JSP or FreeMarker to implement your UI, you can add a
method `getFormHTML` to your `-Rule` class. Here's an example of implementing
the `getFormHTML` method:

    @Override
    public String getFormHTML(
        RuleInstance ruleInstance, Map<String, Object> context,
        Map<String, String> values) {

        String content = "";

        try {
            populateContext(ruleInstance, context, values);

            content = ContentTargetingContextUtil.parseTemplate(
                getClass(), getFormTemplatePath(), context);
        }
        catch (Exception e) {
            _log.error(
                "Error while processing template " + getFormTemplatePath(), e);
        }

        return content;
    }

The `getFormHTML` is used to retrieve the HTML created by the technology you
choose, and to return it as a string that is viewable from your rule's form.
If you plan, therefore, on using an alternative to JSP or FreeMarker, you
must override this method by creating and modifying it in your `-Rule` class.

## Other Best Practices [](id=other-best-practices)

Here are some things to consider as you implement and deploy Audience Targeting
rules:

- As an alternative to storing complex information in the `typeSettings` field,
which is managed by the framework in the Rule Instance table, you may want to
consider persisting to a database by using 
[Service Builder](/develop/tutorials/-/knowledge_base/7-0/business-logic-and-data-access),
which is supported for Rule plugins. 

- If you deploy your rule into a production environment, you may want to
consider adding your values to the cache (e.g., weather in different locations),
since obtaining the same value on every request is very inefficient and could
result in slowing down your portal. For example, when the `evaluate` method is
called, you could obtain the current user ID, current user's weather forecast,
and the time at which the user first visited the page. Then you could evaluate
the rule only when the cached time is over three hours old. This would prevent
the rule from evaluating every time the user visited the page. This is best done
using services.

<!-- No code examples for adding values to cache because this is not implemented
in any custom rules yet. -Cody -->

- You can override the `BaseJSPRule.deleteData` method in your `-Rule`, so that
it deletes any data associated with the rule that is currently being deleted.

- If your rule handles data or references to data that can be staged (e.g., a
reference to a page or web content article), you may need to override the
`BaseRule.exportData` and `BaseRule.importData` methods, to manage the content
properly.

## Related Topics [](id=related-topics)

[Creating New Audience Targeting Rule Types](/develop/tutorials/-/knowledge_base/7-0/creating-new-audience-targeting-rule-types)

[Internationalization](/develop/tutorials/-/knowledge_base/7-0/internationalization)

[Service Builder Persistence](/develop/tutorials/-/knowledge_base/7-0/service-builder-persistence)
