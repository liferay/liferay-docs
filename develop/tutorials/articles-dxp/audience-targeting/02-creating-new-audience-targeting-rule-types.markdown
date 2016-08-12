# Creating New Audience Targeting Rule Types [](id=creating-new-audience-targeting-rule-types)

In the Audience Targeting application, a User Segment is defined as a group of
users that match a set of rules. Out of the box, Liferay provides several types
of rules that are based on characteristics such as age range, gender, location,
and so on. Visit the
[Liferay Audience Targeting Rules](/discover/portal/-/knowledge_base/7-0/liferay-audience-targeting-rules)
article for information on each rule type and their configuration options. 

In some cases, the rules available to you by default may not be enough. If there
is functionality not available in the Audience Targeting application that you
want, you can create your own rule type. You can do this by creating a module.

Creating a rule type involves targeting what you want to evaluate. Suppose you
own an Outdoor Sporting Goods store. On your website, you'd like to promote
goods that are appropriate for the current weather. For example, if the user is
from Los Angeles and it's raining the day they visit your website, you could
show content for new umbrellas. If it's sunny, however, umbrella related content
wouldn't make sense. Therefore, you could display content for sunglasses
instead. For this example, your evaluation entity would be weather.

Once you've decided what to evaluate, you'll need to decide how to obtain a
value to evaluate. In the instance of weather, you need to retrieve the user's
location so you can obtain that location's weather. 

Lastly, you'll need to let administrators set the value that should be compared
with the user's current weather. This could be a UI component consisting of a
drop-down list of weather options. Therefore, if an adminstrator sets *rainy* as
the value for the rule, the rule could be added to a user segment targeted for
rain-related goods. When the user visits a site, their user segment assignment
is decided by whether their pre-set weather value (rainy) matches their
location's current weather. If it matches, rain-related content targeted for
that user segment is displayed; otherwise, the user won't be grouped into that
user segment, and will see differing content, like a promotion for sunglasses.

![Figure 1: This diagram breaks down the evaluation process for the weather rule.](../../images-dxp/weather-rule-diagram.png)

Now that you have an idea of how to plan your custom rule's development, you'll
begin creating one yourself!

## Creating a Custom Rule Type [](id=creating-a-custom-rule-type)

Adding a new type of rule to the Audience Targeting application is easy. First,
you must create a module and ensure it has the necessary Content Targeting
dependencies.

1. Create a module project for deploying a rule. A Blade CLI
   [contenttargetingrule](/develop/tutorials/-/knowledge_base/7-0/content-targeting-rule-template)
   template is available to help you get started quickly. It sets the default
   configuration for you, and it contains boilerplate code so you can skip the
   file creation steps and get started right away.

2. Make sure your module specifies the dependencies necessary for an Audience
   Targeting rule. For example, you should specify the Content Targeting API and
   necessary Liferay packages. For example, this is the example `build.gradle`
   file used from a Gradle based rule:

        dependencies {
            compileOnly group: "com.liferay.content-targeting", name: "com.liferay.content.targeting.analytics.api", version: "3.0.0"
            compileOnly group: "com.liferay.content-targeting", name: "com.liferay.content.targeting.anonymous.users.api", version: "2.0.2"
            compileOnly group: "com.liferay.content-targeting", name: "com.liferay.content.targeting.api", version: "4.0.0"
            compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.3.0"
            compileOnly group: "com.liferay.portal", name: "com.liferay.util.taglib", version: "2.0.0"
            compileOnly group: "javax.portlet", name: "portlet-api", version: "2.0"
            compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
            compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
        }

    Once you've created your module and specified its dependencies, you'll need
    to define your rule's behavior. How your rule behaves is controlled by a
    Java class file that you create.

3. Create a unique package name in the module's `src` directory, and create a
   new Java class in that package. To follow naming conventions, your class name
   should begin with the rule name you're creating, and end with *Rule* (e.g.,
   `WeatherRule.java`). Your Java class should implement the `Rule` interface.

    It is required to implement the `Rule` interface, but there are `Rule`
    extension classes that provide helpful utilities that you can extend. For
    example, the weather rule extends the `BaseJSPRule` class to support
    generating your rule's UI using JSPs. This tutorial demonstrates
    implementing the UI using JSP, and assumes the `Rule` interface is
    implemented by extending the `BaseJSPRule` class. For more information on
    choosing a UI for your rule, see the
    [Best Practices for Rules]()
    tutorial.

4. Directly above the class's declaration, insert the following code:

        @Component(immediate = true, service = Rule.class)

    This annotation declares the implementation class of the Component and
    specifies to immediately start the module once deployed to @product@.

Now that your Java class is set up, you'll need to begin defining how your rule
works by implementing the `Rule` interface's methods. Here are some of the
methods that you can implement to modify your rule behavior:

<!-- The below method descriptions are the Javadoc copied from the `Rule`
interface. Since the source code is not accessible and the Javadoc for Audience
Targeting is not currently published, I've provided some methods and
descriptions until the Javadoc is available publicly. -Cody -->

- `activate`: Does processing when the rule is installed.
- `deActivate`: Does processing when the rule is uninstalled.
- `deleteData`: Removes any additional data added by this rule when the rule
  instance is removed.
- `evaluate(HttpServletRequest, RuleInstance, AnonymousUser)`: Returns `true` if
  the user complies with the rule instance in real time. The evaluation is
  completed correctly after the user makes a request.
- `evaluate(Map, RuleInstance, AnonymousUser)`: Returns `true` if the user
  complies with the rule instance in an offline mode. The evaluation is
  completed without having a user request. This will only be called if the rule
  supports offline evaluation. A context map can be optionally passed with some
  context variables.
- `exportData`: Exports any additional data added by this rule when the rule
  instance is exported.
- `getCacheTime`: Returns the time in milliseconds that the evaluation of this
  rule can be cached. For example, an Age rule can be cached at least 1 day and
  a Geolocation rule could be cached 5 minutes. This value can be configurable
  by adding a custom configuration to your component. A value of `0` means that
  the evaluation can not be cached.
- `getDescription`: Returns the rule's localized description.
- `getFormHTML`: Returns the HTML code containing the form fields required to
  edit the rule instance configuration, based on the context.
- `getIcon`: Returns the Font Awesome CSS class for the rule icon.
- `getName`: Returns the rule's localized name.
- `getRuleCategoryKey`: Returns the key that identifies the category of the
  rule.
- `getRuleKey`: Returns the key that identifies the rule. The rule instances of
  this rule are identified by their rule key.
- `getShortDescription`: Returns the rule's localized short description.
- `getSummary`: Returns the rule instance's localized summary.
- `importData`: Imports any additional data added by this rule when the rule
  instance is imported.
- `isInstantiable`: Returns `true` if the rule can be used more than once with
  different values for a user segment.
- `isVisible`: Returns `true` if the rule is visible.
- `processRule`: Returns the result of evaluating the rule form fields in the
  context of the request and response.
- `supportsOfflineEvaluation`: Returns `true` if the rule can be evaluated
  offline (without the user request in real time). If this is set to `true`, the
  method `evaluate(Map, RuleInstance, AnonymousUser)` should be implemented.

+$$$

**Note:** If you're planning on developing a social rule type that classifies
users based on their social network profile, it's important to remember that
the specific social network's SSO (Single Sign On) must be enabled and
configured properly. Visit the
[Social Rules](/discover/portal/-/knowledge_base/7-0/liferay-audience-targeting-rules#social-rules)
section for more details.

$$$

The first thing you'll define in your weather rule is the User Segment Editor
lifecycle.

## Defining a Rule's View/Save Lifecycle

To see how easy it is to modify a rule's behavior, you'll begin defining the
weather rule's class. This assumes that you followed the instructions above,
creating the `WeatherRule` class and extending `BaseJSPRule`. If you used the
`contenttargetingrule` Blade CLI template, your project is already extending
`BaseJSPRule` and has a default `view.jsp` file already created. This section
covers how to define a rule's view/save lifecycle.

1. Add the activation and deactivation methods to your class.

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

    These methods call the super class `BaseRule` to implement necessary logging
    and processing for when your rule starts and stops. Make sure to include the 
    [@Activate](https://osgi.org/javadoc/r6/cmpn/org/osgi/service/component/annotations/Activate.html)
    and
    [@Deacitvate](https://osgi.org/javadoc/r6/cmpn/org/osgi/service/component/annotations/Deactivate.html)
    annotations, which are required.

2. Define the rule's icon and the category it should reside in when displayed in
   the User Segments Editor.

        @Override
        public String getIcon() {
            return "icon-sun";
        }

        @Override
        public String getRuleCategoryKey() {
            return SessionAttributesRuleCategory.KEY;
        }

    The `getIcon` method should return a
    [Font Awesome](http://fortawesome.github.io/Font-Awesome/3.2.1/) CSS class,
    which is used to render the rule's icon in the editor. The weather rule's
    category is set to Session Attributes. Available category classes include
    `BehaviourRuleCategory`, `SessionAttributesRuleCategory`,
    `SocialRuleCategory`, and `UserAttributesRoleCategory`.

    ![Figure 2: This example Weather rule was modified to reside in the Session Attributes category.](../../images-dxp/new-category-rule.png)

3. Add the following method:

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
    rule's lifecycle.

    ![Figure 3: This diagram shows the lifecycle for an Audience Targeting rule.](../../images-dxp/rule-lifecycle.png)

    When the user opens the User Segment Editor, the render phase begins for the
    rule. The `getFormHtml(...)` method is invoked to retrieve the HTML to
    display. You don't have to worry about implementing this method because it
    is already taken care of in the `BaseJSPRule` class your extending. It calls
    the `populateContext` method.

    You'll notice the `populateContext(...)` method is not available in the
    interface. This is because it's not needed in all cases. It's available by
    extending the `BaseJSPRule` class, and you'll need to add more logic to it
    for the weather rule. The goal of the `populateContext` method is to
    generate a map with all the parameters your JSP view needs to render the
    rule's HTML. This map is stored in the `context` variable, which is
    pre-populated with basic values in the Portlet logic, and then each rule
    contributes their specific parameters to it. The `populateContext` method
    above populates a `weather` context variable with the `weather` value from
    the `values` map parameter.

    For the weather rule, the `populateContext` method accounts for three use
    cases:

    3a. The rule was added but has no set values yet. In this case, the default
        values defined by the developer are injected (e.g., `weather="sunny"`).
    
    3b. The rule was added and a value is set, but the request failed to
        complete (e.g., due to an error). In this case, the `values` parameter
        of the `populateContext` method contains the values that were intended
        to be saved, and they are injected so that they are displayed in the
        rule's view together with the error message.

    3c. The rule was added and a value was successfully set. In this case, the
        `values` parameter is empty and you have to obtain the value to display
        in the form from storage and inject it in the context so it's displayed
        in the rule's HTML. The weather rule uses the `typeSettings` field of
        the rule instance, but complex rules could use services to store values.

    You can think of the `populateContext` method as the intermediary between
    your JSP and your backend code. Once the HTML is successfully retrieved for
    the user, and they've set the weather value, they'll click *Save*, which
    begins the action phase.

4. Add the following method:

        @Override
        public String processRule(
            PortletRequest portletRequest, PortletResponse portletResponse,
            String id, Map<String, String> values) {

            return values.get("weather");
        }

    The `processRule(...)` method is invoked when the action phase is initiated.
    The `values` parameter only contains the value(s) the user added in the
    form. The logic you can add to a `processRule` method is outlined below.

    4a. Obtain the value(s) from the `values` parameter.

    4b. (Optional) Validate the data consistency and possible errors. If
    anything is wrong, throw an `InvalidRuleException` and prohibit the values
    from being stored. In the weather rule scenario, when the rule is reloaded
    after an exception is thrown in the form, case 3b from the previous step
    occurs.

    4c. Return the value to be stored in the `typeSettings` field of the rule
    instance. The `typeSettings` field is managed by the framework in the Rule
    Instance table. If your rule has its own storage mechanism, then you should
    call your services in the `processRule` method.

    Once the rule processing ends, the form is reloaded and the lifecycle
    restarts again. The value(s) selected in the rule are stored and are ready
    to be accessed once user segment evaluation begins. There are a couple more
    methods you'll need to add to the `WeatherRule` class before defining the
    rule's evaluation.

5. Define a way to retrieve the rule's localized summary. In many instances, you
   can do this by retrieving the rule's resource bundle. For the weather rule,
   you only need to return the rule's type settings.

        @Override
        public String getSummary(RuleInstance ruleInstance, Locale locale) {
            return ruleInstance.getTypeSettings();
        }

6. Set the servlet context for your rule.

        @Override
        @Reference(
            target = "(osgi.web.symbolicname=weather)",
            unbind = "-"
        )
        public void setServletContext(ServletContext servletContext) {
            super.setServletContext(servletContext);
        }

    This is only required in rules extending the `BaseJSPRule` class. The
    servlet context must be set for the rule to render its own JSP files. The
    `setServletContext` method is automatically invoked when the rule module is
    installed and resolved in Liferay. Make sure the `osgi.web.symbolicname` in
    the `target` property of the `@Reference` annotation is set to the same
    value as the `Bundle-SymbolicName` defined in the `bnd.bnd file of the
    module.

Next, you'll learn how to evaluate a rule that is configured and saved to a user
segment.

## Evaluating a Rule









To view a sample rule and its UI configuration, download the sample
[weather rule](https://customer.liferay.com/documents/10738/200086/weather.zip).

If you wanted to create user segment rules based on the type of weather a user
is experiencing, you could create a drop-down menu that lets the administrator
select a weather type to associate with that user segment rule. Here's a code
snippet from the weather rule's JSP template (`view.jsp`) that could be applied
to this example:

    <%
    Map<String, Object> context = (Map<String, Object>)request.getAttribute("context");

    String weather = (String)context.get("weather");
    %>

    <aui:fieldset>
        <aui:select name="weather" value="<%= weather %>">
            <aui:option label="sunny" value="sunny" />
            <aui:option label="clouds" value="clouds" />
            <aui:option label="mist" value="mist" />
            <aui:option label="snow" value="snow" />
        </aui:select>
    </aui:fieldset>

This JSP code creates a `select` drop-down box with the name `weather`.
Then it specifies several options associated with different types of weather.
You could borrow from this JSP code and change the name and labels for a
`select` drop-down box and values appropriate for your rule plugin.

![Figure 3: This example rule uses a `select` drop-down box.](../../images-dxp/select-box-rule.png)

The last step you'll need to take is specifying what your rule should evaluate.
The evaluation process determines whether a user matches the rule.

1. Find the `evaluate` method in the `WeatherRule` class. There is logic that
   obtains the runtime user's value for what you plan to evaluate.

        ...
        String userWeather = getUserWeather(anonymousUser);

    You can look at this method's code in the
    [downloadable ZIP file](https://customer.liferay.com/documents/10738/200086/weather.zip)
    for the sample weather rule. 

    <!-- Why would I want to look at the method's code? Surely, we're not asking
    people to read code to figure out how to do something, right? We're
    documenting it here. -Rich --> 

2. The weather rule now must retrieve the value stored in the type settings by
   using the `processRule` method.

        String weather = ruleInstance.getTypeSettings();

3. Now that the rule has both the user's value and the rule's value, it should
   check whether they match. If they match, return `true`; otherwise, return
   `false`.

        if (Validator.equals(userWeather, weather)) {
            return true;
        }

        return false;

4. Finally, deploy your rule plugin to the Liferay server. Your new rule is
   fully functional, and the UI you've defined is added to the Add/Edit User
   Segment form so that administrators can set a value for that specific user
   segment.

Now you've created and examined a fully functional rule and have the knowledge to
create your own.

<!-- ## Customize the Rules Engine -->

## Related Topics [](id=related-topics)

[Creating Modules with Blade CLI](/develop/tutorials/-/knowledge_base/7-0/creating-modules-with-blade-cli)

[Internationalization](/develop/tutorials/-/knowledge_base/7-0/internationalization)

[Service Builder Persistence](/develop/tutorials/-/knowledge_base/7-0/service-builder-persistence)
