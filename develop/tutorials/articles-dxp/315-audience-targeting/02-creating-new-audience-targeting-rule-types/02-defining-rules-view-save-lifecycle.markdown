---
header-id: defining-a-rules-view-save-lifecycle
---

# Defining a Rule's View/Save Lifecycle

[TOC levels=1-4]

The view/save lifecycle describes the process behind the scenes when an 
administrator applies a rule to a user segment using the User Segment Editor. 
You'll implement that now.

When the user opens the User Segment Editor, the render phase begins for the 
rule creation. During the render phase, the HTML for the form is generated 
and, if necessary, the `context` map is generated with any parameters that you 
need to create the form. Once the HTML is successfully retrieved and the user 
has set the values and clicked *Save*, the action phase begins.
        
When the action phase begins, the `processRule(...)` method takes the values
provided by the form and persists them. Once the rule processing ends, the form 
is reloaded and the lifecycle restarts again. The value(s) selected in the rule 
are stored and are ready to be accessed once user segment evaluation begins.

In this section, you'll begin defining the weather rule's Java class. This
assumes that you followed the instructions in the previous
[tutorial](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-custom-rule-type),
creating the `WeatherRule` class and extending
`com.liferay.content.targeting.api.model.BaseJSPRule`.

| **Note:** To view the Javadoc for the Content Targeting classes mentioned in
| this article, download the
| [Javadoc JAR](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc).

If you used the `content-targeting-rule` Blade CLI template, your project 
already extends `BaseJSPRule` and has a default `view.jsp` file already
created. 

1.  If you didn't use the template, add the activation and deactivation methods
    to your class.

    ```java
    @Activate
    @Override
    public void activate() {
        super.activate();
    }

    @Deactivate
    @Override
    public void deActivate() {
        super.deActivate();
    }
    ```

    These methods call the super class
    [`com.liferay.content.targeting.api.model.BaseRule`](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc)
    to implement necessary logging and processing for when your rule starts and
    stops. Make sure to include the
    [@Activate](https://osgi.org/javadoc/r6/cmpn/org/osgi/service/component/annotations/Activate.html)
    and
    [@Deactivate](https://osgi.org/javadoc/r6/cmpn/org/osgi/service/component/annotations/Deactivate.html)
    annotations, which are required.

2.  Define the category for the Rule when displayed in the User Segment Editor.
    Find the `getRuleCategoryKey()` method and replace it with the code below: 

    ```java
    @Override
    public String getRuleCategoryKey() {
        return SessionAttributesRuleCategory.KEY;
    }
    ```

    This code puts the weather rule in the Session Attributes category. To
    put your rule into the appropriate category, use the `getRuleCategoryKey`
    method to return the category class's key. Available category classes
    include
    [`com.liferay.content.targeting.rule.categories.BehaviourRuleCategory`](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc),
    [`com.liferay.content.targeting.rule.categories.SessionAttributesRuleCategory`](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc),
    [`com.liferay.content.targeting.rule.categories.SocialRuleCategory`](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc),
    and
    [`com.liferay.content.targeting.rule.categories.UserAttributesRoleCategory`](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc).

    ![Figure 1: This example Weather rule was modified to reside in the Session Attributes category.](../../../images-dxp/new-category-rule.png)

3.  Find the `populateContext()` method and replace it with the code below:

    ```java
    @Override
    protected void populateContext(
        RuleInstance ruleInstance, Map<String, Object> context,
        Map<String, String> values) {

        String weather = "";

        if (!values.isEmpty()) {
            weather = GetterUtil.getString(values.get("weather"));
        }
        else if (ruleInstance != null) {
            weather = ruleInstance.getTypeSettings();
        }

        context.put("weather", weather);
    }
    ```

    To understand what this method accomplishes, you must examine the rule's
    configuration lifecycle.

    ![Figure 2: An Audience Targeting rule must be configured by the user and processed before it can become part of a User Segment.](../../../images-dxp/rule-lifecycle.png)

    When the user opens the User Segment Editor, the render phase begins for the
    rule. The `getFormHTML(...)` method retrieves the HTML to display. You don't
    have to worry about implementing this method because it's already
    implemented in the `BaseJSPRule` class you're extending. The `getFormHTML`
    method calls the `populateContext(...)` method.

    You'll notice the `populateContext` method is not available in the
    [`com.liferay.content.targeting.api.model.Rule`](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc)
    interface. This is because it's not needed in all cases. It's available by
    extending the `BaseJSPRule` class, and it needs more logic for the weather
    rule. 

    The `populateContext` method generates a map with all the parameters your
    JSP view needs to render the rule's HTML. This map is stored in the
    `context` variable. This variable is a map defining the form evaluation
    context for Audience Targeting rules. Each rule contributes its specific
    parameters to it. The `populateContext` method above populates a `weather`
    context variable with the `weather` values from the `values` map parameter,
    which is then passed to the JSP.

    For the weather rule, the `populateContext` method accounts for three use
    cases:

    a. The rule was added but has no set values yet. In this case, the default
       values defined by the developer are injected (e.g., `weather=""`).

    b. The rule was added and a value is set, but the request failed to
       complete (e.g., due to an error). In this case, the `values` parameter
       of the `populateContext` method contains the values that were intended
       to be saved, and they are injected so that they are displayed in the
       rule's view together with the error message.

    c. The rule was added and a value was successfully set. In this case, the
       `values` parameter is empty, and you must obtain the values that the
       form should display from storage and inject them in the context so
       they appear in the rule's HTML. The weather rule uses the `typeSettings`
       field of the rule instance, but complex rules could use services to
       store values.

    You can think of the `populateContext` method as the intermediary between
    your JSP and your back-end code. Creating the weather rule's UI using a JSP
    is covered in
    [Defining the Rule's UI](/docs/7-1/tutorials/-/knowledge_base/t/defining-the-rules-ui). 
    Once the HTML is successfully retrieved and the user has set the weather
    value and clicked *Save*, the action phase begins. 

4.  Replace the `processRule()` method with this code:

    ```java
    @Override
    public String processRule(
        PortletRequest portletRequest, PortletResponse portletResponse,
        String id, Map<String, String> values) {

        return values.get("weather");
    }
    ```

    The `processRule(...)` method is invoked when the action phase is initiated.
    The `values` parameter only contains the value(s) the user added in the
    form. The logic you could add to a `processRule` method is outlined below.

    a. Obtain the value(s) from the `values` parameter.

    b. (Optional) Validate the data consistency and possible errors. If
       anything is wrong, throw an
       [`com.liferay.content.targeting.exception.InvalidRuleException`](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc)
       and prohibit the values from being stored. In the weather rule scenario,
       when the rule is reloaded after an exception is thrown in the form, case 3b
       from the previous step occurs.

    c. Return the value to be stored in the rule instance's `typeSettings`
       field. The `typeSettings` field is managed by the framework in the Rule
       Instance table. If your rule has its own storage mechanism, then you should
       call your services in the `processRule` method.

    Once the rule processing ends, the form is reloaded and the lifecycle
    restarts again. The value(s) selected in the rule are stored and are ready
    to be accessed once user segment evaluation begins. You must add two more
    methods to the `WeatherRule` class before defining the rule's evaluation.

5.  Define a way to retrieve the rule's localized summary. In many instances,
    you can do this by combining keys in the rule's resource bundle with the
    information stored for the rule. For the weather rule, you can return
    the rule's type settings, which contains the selected weather condition. Replace
    the generated `getSummary()` method with this one: 

    ```java
    @Override
    public String getSummary(RuleInstance ruleInstance, Locale locale) {
        return ruleInstance.getTypeSettings();
    }
    ```

6.  Set the servlet context for your rule. This method was generated and can be
    left alone:

    ```java
    @Override
    @Reference(
        target = "(osgi.web.symbolicname=weather)",
        unbind = "-"
    )
    public void setServletContext(ServletContext servletContext) {
        super.setServletContext(servletContext);
    }
    ```

    Setting the servlet context is only required for rules extending the
    `BaseJSPRule` class. The servlet context must be set for the rule to render
    its own JSP files. The `setServletContext` method is invoked automatically
    when the rule module is installed and resolved in Liferay. Make sure the
    `osgi.web.symbolicname` in the `target` property of the `@Reference`
    annotation is set to the same value as the `Bundle-SymbolicName` defined in
    the `bnd.bnd` file of the module.

Next, you'll learn how to evaluate a rule that is configured and saved to a user
segment.
