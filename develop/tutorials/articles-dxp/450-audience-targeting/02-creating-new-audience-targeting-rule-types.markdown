# Creating New Audience Targeting Rule Types [](id=creating-new-audience-targeting-rule-types)

In the Audience Targeting application, a User Segment is defined as a group of
users that match a set of rules. Out of the box, Liferay provides several types
of rules that are based on characteristics such as age range, gender, location,
and so on. You combine these rules to create User Segments. For example, if you
want to target probable buyers of a shoe that has a particular style, you might
create a User Segment composed of Females over 40 who live in urban areas. 

The Audience Targeting app ships with many rules you can use make up User
Segments, but it's also extensible. This means that if there isn't a rule that
already fits your case, you can create it yourself! 

Creating a rule type involves targeting what you want to evaluate. Suppose you
own an Outdoor Sporting Goods store. On your website, you'd like to promote
goods that are appropriate for the current weather. If a user is from Los
Angeles and it's raining the day he or she visits your website, you could show
that user new umbrellas. If it's sunny, however, you could show the user
sunglasses instead. For this example, your evaluation entity would be weather
based on the user's location. To make this work, you'll need to do two things: 

1. Retrieve the user's location so you can obtain that location's weather. 

2. Let administrators set the value that should be compared with the user's
   current weather, using a UI component like a selection list of weather
   options. 

With this design, an administrator can set *rainy* as the value for the rule,
and the rule could be added to a user segment targeted for rain-related goods.
When users visit your site, their user segment assignments come from matching
the weather in their current locations with the rule's preset weather value
(rainy). On a match, you show rain-related content; otherwise, the user is part
of a different User Segment and sees that segment's content, like a promotion
for sunglasses.

![Figure 1: This diagram breaks down the evaluation process for the weather rule.](../../images-dxp/weather-rule-diagram.png)

Now that you have an idea of how to plan your custom rule's development, you'll
begin creating one yourself!

## Creating a Custom Rule Type [](id=creating-a-custom-rule-type)

Adding a new type of rule to the Audience Targeting application is easy. First,
you must create a module and ensure it has the necessary Content Targeting API
dependencies.

1.  Create a module project for deploying a rule. A Blade CLI
    [contenttargetingrule](/develop/tutorials/-/knowledge_base/7-0/content-targeting-rule-template)
    template is available to help you get started quickly. It sets the default
    configuration for you, and it contains boilerplate code so you can skip the
    file creation steps and get started right away.

2.  Make sure your module specifies the dependencies necessary for an Audience
    Targeting rule. For example, you should specify the Content Targeting API
    and necessary Liferay packages. For example, this is the example
    `build.gradle` file used from a Gradle based rule:

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

    You can learn more about exposing the Content Targeting API in the
    [Accessing the Content Targeting API](/develop/tutorials/-/knowledge_base/7-0/accessing-the-content-targeting-api)
    tutorial. Once you've created your module and specified its dependencies,
    you'll need to define your rule's behavior. How your rule behaves is
    controlled by a Java class file that you create.

3.  Create a unique package name in the module's `src` directory, and create a
    new Java class in that package. To follow naming conventions, your class
    name should begin with the rule name you're creating, and end with *Rule*
    (e.g., `WeatherRule.java`). Your Java class should implement the
    [`com.liferay.content.targeting.api.model.Rule` interface](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/Rule.html).

    It is required to implement the
    [Rule](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/Rule.html)
    interface, but there are `Rule`
    extension classes that provide helpful utilities that you can extend. For
    example, your rule can extend the
    [BaseJSPRule](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/BaseJSPRule.html)
    class to support generating your rule's UI using JSPs. This tutorial demonstrates implementing the UI using a JSP,
    and assumes the `Rule` interface is implemented by extending the `BaseJSPRule` class. For more information on
    choosing a UI for your rule, see the
    [Selecting a UI Technology](/develop/tutorial/-/knowledge_base/7-0/best-practices-for-rules#selecting-a-ui-technology)
    section.

4.  Directly above the class's declaration, insert the following code:

        @Component(immediate = true, service = Rule.class)

    This annotation declares the implementation class of the Component and
    specifies to immediately start the module once deployed to @product@.

Now that your Java class is set up, you'll need to define how your rule works by
implementing the
[Rule](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/Rule.html)
interface's methods. You'll begin implementing these methods next.

+$$$

**Note:** If you're planning on developing a social rule type that classifies
users based on their social network profile, it's important to remember that
the specific social network's SSO (Single Sign On) must be enabled and
configured properly. Visit the
[Social Rules](/discover/portal/-/knowledge_base/7-0/liferay-audience-targeting-rules#social-rules)
section for more details.

$$$

The first thing you'll define in your weather rule is the view/save lifecycle.

## Defining a Rule's View/Save Lifecycle [](id=defining-a-rules-view-save-lifecycle)

This section covers how to define a rule's view/save lifecycle. This is when
a user applies a rule to a user segment using the User Segment Editor.

In this section, you'll begin defining the weather rule's Java class. This
assumes that you followed the instructions above, creating the `WeatherRule`
class and extending
[BaseJSPRule](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/BaseJSPRule.html).
If you used the `contenttargetingrule` Blade CLI template, your project is
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
    [BaseRule](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/BaseRule.html)
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
    include [BehaviourRuleCategory](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/rule/categories/BehaviorRuleCategory.html),
    [SessionAttributesRuleCategory](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/rule/categories/SessionAttributesRuleCategory.html),
    [SocialRuleCategory](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/rule/categories/SocialRuleCategory.html),
    and
    [UserAttributesRoleCategory](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/rule/categories/UserAttributesRuleCategory.html).

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
    [BaseJSPRule](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/BaseJSPRule.html)
    class you're extending. The `getFormHTML`
    method calls the `populateContext(...)` method.

    You'll notice the `populateContext` method is not available in the
    [Rule](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/Rule.html)
    interface. This is because it's not needed in all cases. It's available by
    extending the
    [BaseJSPRule](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/BaseJSPRule.html)
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
    [Defining the Rule's UI](/develop/tutorials/-/knowledge_base/7-0/creating-new-audience-targeting-rule-types#defining-the-rules-ui)
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
    [InvalidRuleException](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/exception/InvalidRuleException.html)
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
    [BaseJSPRule](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/BaseJSPRule.html)
    class. The servlet context must be set for the rule to render its own JSP
    files. The `setServletContext` method is invoked automatically when the rule
    module is installed and resolved in Liferay. Make sure the
    `osgi.web.symbolicname` in the `target` property of the `@Reference`
    annotation is set to the same value as the `Bundle-SymbolicName` defined in
    the `bnd.bnd` file of the module.

Next, you'll learn how to evaluate a rule that is configured and saved to a user
segment.

## Evaluating a Rule [](id=evaluating-a-rule)

Imagine an administrator has successfully configured and saved your custom rule
to his or her user segment. Now what? Your rule needs to fulfill its purpose,
which is to evaluate the preset weather value compared to a user's weather value
visiting the site. If the user's value matches the preset value (along with the
segment's other rules), that user is added to the user segment. 

1.  You must implement the `evaluate(...)` rule to begin the evaluation
    process. This method is part of the user segmentation lifecycle. When a page
    is loaded, Liferay invokes the `evaluate` method of the rule to determine
    if the current user belongs to the user segment. For the weather rule, add
    this `evaluate` method:

        @Override
        public boolean evaluate(
                HttpServletRequest request, RuleInstance ruleInstance,
                AnonymousUser anonymousUser)
            throws Exception {

            String userWeather = getUserWeather(anonymousUser);

            String weather = ruleInstance.getTypeSettings();

            if (Validator.equals(userWeather, weather)) {
                return true;
            }

            return false;
        }

    You acquire the user's weather by calling the `getUserWeather` method, which
    you'll define later. Then you get the preset weather value by accessing
    the rule instance's `typeSettings` parameter. Finally, you compare the two
    values. If they match, return `true`; otherwise return `false`. Remember
    that users are only added to User Segments when all the Rules in the User
    Segment return true.

2.  Next, you need to retrieve the user's weather. As you learned earlier,
    you must access the user's location first. Add the logic below to do
    this.

        protected String getCityFromUserProfile(long contactId, long companyId)
            throws PortalException, SystemException {
 
            List<Address> addresses = AddressLocalServiceUtil.getAddresses(companyId, Contact.class.getName(), contactId);
 
            if (addresses.isEmpty()) {
                return null;
            }
 
            Address address = addresses.get(0);

            return address.getCity() + StringPool.COMMA + address.getCountry().getA2();
        }

    This method retrieves the location by accessing the user's profile
    information. You could also have used a geo-location service to find this by
    the user's IP address. Once you have the user's location, you can find the
    current weather for that location.

3.  Add the following method to retrieve a user's weather forecast.

        protected String getUserWeather(AnonymousUser anonymousUser)
            throws PortalException, SystemException {

            User user = anonymousUser.getUser();

            String city = getCityFromUserProfile(user.getContactId(), user.getCompanyId());

            Http.Options options = new Http.Options();

            String location = HttpUtil.addParameter(API_URL, "q", city);
            location = HttpUtil.addParameter(location, "format", "json");

            options.setLocation(location);

            int weatherCode = 0;

            try {
                String text = HttpUtil.URLtoString(options);

                JSONObject jsonObject = JSONFactoryUtil.createJSONObject(text);

                weatherCode = jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
            }
            catch (Exception e) {
                _log.error(e);
            }

            return getWeatherFromCode(weatherCode);
        }

        private static Log _log = LogFactoryUtil.getLog(WeatherRule.class);

    This method calls the `getCityFromUserProfile` method to acquire the user's
    location. Then it retrieves the weather code for that location from a
    weather service. 

4.  Set the `API_URL` field to the Open Weather Map's API URL:

        private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather";

    For the weather rule, you can access Open Weather Map's APIs to retrieve the
    weather code.
    
5.  The last thing is to convert the weather code to a string you can evaluate
    (e.g., `sunny`). Add the following method to convert Open Weather Map's
    weather codes:

        protected String getWeatherFromCode(int code) {
            if (code == 800 || code == 801) {
                return "sunny";
            }
            else if (code > 801 && code < 805) {
                return "clouds";
            }
            else if (code >= 600 && code < 622) {
                return "snow";
            }
            else if (code >= 500 && code < 532) {
                return "rain";
            }
 
            return null;
        }

    All possible weather codes are
    [here](http://openweathermap.org/weather-conditions).

Excellent! You've implemented the `evaluate` method and added the necessary
logic in your `-Rule` class to acquire a user's local weather. The weather
rule's behavior is defined and complete. The last thing you need to do is create
a JSP template.

## Defining the Rule's UI [](id=defining-the-rules-ui)

The Java code you've added to this point has assumed that a preset weather value
is available for comparing during the evaluation process. To let administrators
set that value, you must define a UI so your rule can be configured during the
view/save lifecycle. Create a `view.jsp`
file in your rule's module (e.g.,
`/src/main/resources/META-INF/resources/view.jsp`) and add the following logic:

    <%
    Map<String, Object> context = (Map<String, Object>)request.getAttribute("context");

    String weather = (String)context.get("weather");
    %>

    <aui:fieldset>
        <aui:select name="weather" value="<%= weather %>">
            <aui:option label="sunny" value="sunny" />
            <aui:option label="clouds" value="clouds" />
            <aui:option label="snow" value="snow" />
            <aui:option label="rain" value="rain" />
        </aui:select>
    </aui:fieldset>

The `weather` variable in the `context` map should be set for the weather rule.
When the user selects an option, it's passed from the view template to the
`populateContext` method.

![Figure 4: The weather rule uses a `select` drop-down box to set the weather value.](../../images-dxp/select-box-rule.png)

+$$$

The weather rule uses JSP templates to display the rule's view. Audience
Targeting, however, is compatible with any UI technology. Visit the
[Selecting a UI Technology](/develop/tutorial/-/knowledge_base/7-0/best-practices-for-rules#selecting-a-ui-technology)
section for details on how to use other UI technologies like FreeMarker.

$$$

Congratulations! You've created the weather rule and can now target users based
on their weather conditions. You can view the finished version of the weather
rule by downloading its
[ZIP file](https://customer.liferay.com/documents/10738/200086/weather.zip).

Now you've created and examined a fully functional rule and have the knowledge
to create your own.

<!-- ## Customize the Rules Engine -->

## Related Topics [](id=related-topics)

[Best Practices for Rules](/develop/tutorials/-/knowledge_base/7-0/best-practices-for-rules)

[Creating Modules with Blade CLI](/develop/tutorials/-/knowledge_base/7-0/creating-modules-with-blade-cli)

[Internationalization](/develop/tutorials/-/knowledge_base/7-0/internationalization)

[Service Builder Persistence](/develop/tutorials/-/knowledge_base/7-0/service-builder-persistence)
