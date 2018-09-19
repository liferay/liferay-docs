# Defining a Rule's View/Save Lifecycle [](id=defining-a-rules-view-save-lifecycle)

This section covers how to define a rule's view/save lifecycle. This is when
a user applies a rule to a user segment using the User Segment Editor.

In this section, you'll begin defining the weather rule's Java class. This
assumes that you followed the instructions above, creating the `WeatherRule`
class and extending
[BaseJSPRule](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/BaseJSPRule.html).
If you used the `content-targeting-rule` Blade CLI template, your project is
already extending `BaseJSPRule` and has a default `view.jsp` file already
created. 

1.  Add the activation and deactivation methods to your class.

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

    These methods call the super class
    [BaseRule](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/BaseRule.html)
    to implement necessary logging and processing for when your rule starts and
    stops. Make sure to include the
    [@Activate](https://osgi.org/javadoc/r6/cmpn/org/osgi/service/component/annotations/Activate.html)
    and
    [@Deactivate](https://osgi.org/javadoc/r6/cmpn/org/osgi/service/component/annotations/Deactivate.html)
    annotations, which are required.

2.  Define the category for the Rule when displayed in the User Segment Editor.

        @Override
        public String getRuleCategoryKey() {
            return SessionAttributesRuleCategory.KEY;
        }

    This code puts the weather rule in the Session Attributes category. To
    put your rule into the appropriate category, use the `getRuleCategoryKey`
    method to return the category class's key. Available category classes
    include [BehaviourRuleCategory](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/rule/categories/BehaviorRuleCategory.html),
    [SessionAttributesRuleCategory](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/rule/categories/SessionAttributesRuleCategory.html),
    [SocialRuleCategory](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/rule/categories/SocialRuleCategory.html),
    and
    [UserAttributesRoleCategory](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/rule/categories/UserAttributesRuleCategory.html).

    ![Figure 2: This example Weather rule was modified to reside in the Session Attributes category.](../../images-dxp/new-category-rule.png)

3.  Add the following method:

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

    To understand what this method accomplishes, you'll need to examine the
    rule's configuration lifecycle.

    ![Figure 3: An Audience Targeting rule must be configured by the user and processed before it can become part of a User Segment.](../../images-dxp/rule-lifecycle.png)

    When the user opens the User Segment Editor, the render phase begins for the
    rule. The `getFormHTML(...)` method retrieves the HTML to display. You don't
    have to worry about implementing this method because it's already
    implemented in the
    [BaseJSPRule](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/BaseJSPRule.html)
    class you're extending. The `getFormHTML`
    method calls the `populateContext(...)` method.

    You'll notice the `populateContext` method is not available in the
    [Rule](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/Rule.html)
    interface. This is because it's not needed in all cases. It's available by
    extending the
    [BaseJSPRule](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/BaseJSPRule.html)
    class, and you'll need to add more logic to it for the weather rule. 
    
    The goal of the `populateContext` method is to generate a map with all the
    parameters your JSP view needs to render the rule's HTML. This map is stored
    in the `context` variable, which is pre-populated with basic values in the
    Portlet logic, and then each rule contributes its specific parameters to
    it. The `populateContext` method above populates a `weather` context
    variable with the `weather` values from the `values` map parameter, which is
    then passed to the JSP.

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
    `values` parameter is empty, and you have to obtain the values from storage
    that the form should display and inject them in the context so they're
    displayed in the rule's HTML. The weather rule uses the `typeSettings` field
    of the rule instance, but complex rules could use services to store values.

    You can think of the `populateContext` method as the intermediary between
    your JSP and your backend code. You can see how to create the weather rule's
    UI using a JSP by seeing the
    [Defining the Rule's UI](/develop/tutorials/-/knowledge_base/7-1/creating-new-audience-targeting-rule-types#defining-the-rules-ui)
    section. Once the HTML is successfully retrieved and the user has set the
    weather value and clicked *Save*, the action phase begins. 

4.  Add the following method:

        @Override
        public String processRule(
            PortletRequest portletRequest, PortletResponse portletResponse,
            String id, Map<String, String> values) {

            return values.get("weather");
        }

    The `processRule(...)` method is invoked when the action phase is initiated.
    The `values` parameter only contains the value(s) the user added in the
    form. The logic you could add to a `processRule` method is outlined below.

    a. Obtain the value(s) from the `values` parameter.

    b. (Optional) Validate the data consistency and possible errors. If
    anything is wrong, throw an
    [InvalidRuleException](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/exception/InvalidRuleException.html)
    and prohibit the values from being stored. In the weather rule scenario,
    when the rule is reloaded after an exception is thrown in the form, case 3b
    from the previous step occurs.

    c. Return the value to be stored in the rule instance's `typeSettings`
    field. The `typeSettings` field is managed by the framework in the Rule
    Instance table. If your rule has its own storage mechanism, then you should
    call your services in the `processRule` method.

    Once the rule processing ends, the form is reloaded and the lifecycle
    restarts again. The value(s) selected in the rule are stored and are ready
    to be accessed once user segment evaluation begins. There are a couple more
    methods you'll need to add to the `WeatherRule` class before defining the
    rule's evaluation.

5.  Define a way to retrieve the rule's localized summary. In many instances,
    you can do this by combining keys in the rule's resource bundle with the
    information stored for the rule. For the weather rule, you can return
    the rule's type settings, which contains the selected weather condition.

        @Override
        public String getSummary(RuleInstance ruleInstance, Locale locale) {
            return ruleInstance.getTypeSettings();
        }

6.  Set the servlet context for your rule.

        @Override
        @Reference(
            target = "(osgi.web.symbolicname=weather)",
            unbind = "-"
        )
        public void setServletContext(ServletContext servletContext) {
            super.setServletContext(servletContext);
        }

    This is only required for rules extending the
    [BaseJSPRule](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/BaseJSPRule.html)
    class. The servlet context must be set for the rule to render its own JSP
    files. The `setServletContext` method is invoked automatically when the rule
    module is installed and resolved in Liferay. Make sure the
    `osgi.web.symbolicname` in the `target` property of the `@Reference`
    annotation is set to the same value as the `Bundle-SymbolicName` defined in
    the `bnd.bnd` file of the module.

Next, you'll learn how to evaluate a rule that is configured and saved to a user
segment.
