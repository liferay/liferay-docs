# Creating New Audience Targeting Rule Types [](id=creating-new-audience-targeting-rule-types)

In the Audience Targeting application, a User Segment is defined as a group of
users that match a set of rules. Out of the box, Liferay provides several types
of rules that are based on characteristics such as age range, gender, location,
etc. Visit the
[Liferay Audience Targeting Rules](/discover/portal/-/knowledge_base/6-2/liferay-audience-targeting-rules)
article for information on each rule type, and their configuration options. To
extend the set of available rule types, you can create a class that implements
the
[Rule](https://github.com/liferay/liferay-apps-content-targeting/blob/samples-v1.1/content-targeting-api/service/com/liferay/content/targeting/api/model/Rule.java)
interface and deploy the class in your own OSGi plugin. 

OSGi plugins can be hot-deployed and undeployed, they manage their own
dependencies, and they can provide new services that other OSGi plugins can
consume. The Audience Targeting application can consume services from rule OSGi
plugins. 

This tutorial shows you how to create a custom rule type and deploy it in an
OSGi plugin. But before you begin creating a rule type, you must learn how to
install and use the Audience Targeting project. This tutorial covers all of
these things, plus it gives you helpful tips. 

## Installing the Audience Targeting Project [](id=installing-the-audience-targeting-project)

Before you can take advantage of the Audience Targeting project's scripts for
creating rules, reports, and tracking actions, you must first install the
project itself. To do this, you should install the Audience Targeting SDK.

So what does Audience Targeting SDK provide that's useful for Audience
Targeting? First, the Audience Targeting SDK contains a Liferay Plugins SDK that
facilitates deploying audience targeting plugins to Liferay Portal. Additionally,
the Audience Targeting SDK includes the Audience Targeting project, so you can
leverage its development scripts to generate customizable rule, report, and
tracking action plugins. The Audience Targeting SDK essentially provides an
Audience Targeting development environment. 

You can download the Audience Targeting SDK from
[here](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Audience+Targeting).
Once you've downloaded the Audience Targeting SDK installation's
[ZIP file](/documents/10184/359829/rule-weather.zip), 
unzip it. 

<!-- There's not an official downloads page because the WCM team is working
on a new SDK based on Gradle and they want that one to replace this one. Once
that one is available (~end Feb 2015), an official downloads page link will be
available. -Cody -->

You'll want the Plugins SDK to point to your application server. To learn more
about setting up and using the Plugins SDK included with the Audience
Targeting SDK, you can visit the 
[Plugins SDK](/develop/tutorials/-/knowledge_base/6-2/plugins-sdk) tutorials.

+$$$

**Tip:** You can specify the following [portal property](/discover/reference)
setting so that the FreeMarker code is always retrieved from the template,
instead of from the cache: 

    freemarker.engine.resource.modification.check.interval=0

$$$

Next, you'll learn how to create a custom rule type using the Audience Targeting
SDK that you just installed.

## Creating a Custom Rule Type [](id=creating-a-custom-rule-type)

Adding a new type of rule to the Audience Targeting application is easy. In this
part of the tutorial, you'll learn how to create a rule and deploy it to your
Liferay server.

1. In the root Audience Targeting project folder `apps/content-targeting`, run
   the `create_rule` command (appropriate for your OS) from a command prompt.
   For example, the command below creates a rule project with 
   `weather` for its project name and *Weather* as its display name: 

        create_rule.bat weather "Weather"

    or

        ./create_rule.sh weather "Weather"

2. Navigate to the newly generated project folder that has your rule's name
   prefixed with `rule-` (e.g., `rule-weather`). Open the folder and study
   what's been generated.
   
    The `create_rule` command created default files that make the plugin
    deployable. 

3. Now is a convenient time to deploy the project to see how it currently looks
   in Portal.

    To deploy the plugin project, open a terminal to your plugin project's
    directory and run the `ant deploy` command. You'll find this new rule listed
    when creating or editing a user segment in the Audience Targeting
    application. 

    +$$$

    **Tip:** If a plugin has been successfully deployed, but its changes aren't
    visible, check that the generated `.jar` in the Plugins SDK's `/dist`
    folder contains the latest modifications. if it doesn't contain them, delete
    it and then redeploy the plugin. 

    $$$

4. To view your new rule, navigate to your portal's *Admin* &rarr; *Site
   Administration* &rarr; *Configuration* &rarr; *Audience Targeting* menu. To
   see the rule you just deployed, click *Add User Segment*, scroll down to the
   Rules form, and expand the *Sample* drop-down menu.

    ![Figure 1: Although your new rule is very bare bones, it is deployable to your portal straight out of the box.](../../images/default-sample-rule.png)

    The default rule doesn't evaluate anything yet, but you can
    drag and drop the rule onto the form, as shown above.

Awesome! You've deployed your rule plugin. Next, you'll need to learn about the
components that were generated for you and how to edit them to create a
functional Audience Targeting rule.

There are three components you can specify for your rule:

- *Rule Behavior*
- *UI for Configuration (optional)*
- *Language Keys (optional)*

The behavior of your rule is controlled from a Java class file located in your
rule's `src/com/liferay/content/targeting/rule/[RULE_NAME]` folder. The rule's
UI and language keys can be configured in the `src/templates/ct_fields.ftl` and
`src/content/Language.properties` files, respectively. You'll learn more about
the latter two components later on.

+$$$

**Note:** If you're planning on developing a social rule type that classifies
users based on their social network profile, it's important to remember that
they will not work properly unless the specific social network's SSO (Single
Sign On) is enabled and configured properly. Visit the
[Social Rules](/discover/portal/-/knowledge_base/6-2/liferay-audience-targeting-rules#social-rules)
section for more details.

$$$

Now, you can begin creating your rule's functionality by specifying its behavior
in the `-Rule` class (e.g., `WeatherRule.java`) that the SDK generated for
you. This class implements the
[Rule](https://github.com/liferay/liferay-apps-content-targeting/blob/samples-v1.1/content-targeting-api/service/com/liferay/content/targeting/api/model/Rule.java)
interface (required), and extends the
[BaseRule](https://github.com/liferay/liferay-apps-content-targeting/blob/samples-v1.1/content-targeting-api/service/com/liferay/content/targeting/api/model/BaseRule.java)
class. It's not mandatory to extend `BaseRule`, but it provides some helpful
utilities, such as support for generating your rule's UI using FreeMarker.
Note that there are multiple methods in the generated `-Rule` class; you must
modify them to create a working rule. 

If you navigate back to your rule deployed in your portal, notice that it's
listed under a category named Sample and that it uses a puzzle piece icon.
You can change both a rule's category and icon by modifying their respective
generated default methods. 

1. Open your rule's Java class file and find the `getIcon` method. This method
   configures the icon displayed in the Rules UI. You can replace the return
   value *"icon-puzzle"* with the name of a Font Awesome icon (e.g.,
   *"icon-coffee"* or *"icon-globe"*) that appropriately fits your rule. For a
   complete listing of icons that you can specify, you can visit
   [Font Awesome](http://fortawesome.github.io/Font-Awesome/3.2.1/).

2. Locate the `getRuleCategoryKey` method and replace its return value with the
   key name of the category in which you'd like your rule to reside. For
   example, to categorize your rule in the *Session Attributes* category, 
   replace the return value `SampleRuleCategory.KEY` with the value
   `SessionAttributesRuleCategory.KEY`, and make sure to import that class.
   There are several category classes listed 
   [here](https://github.com/liferay/liferay-apps-content-targeting/tree/samples-v1.1/content-targeting-api/service/com/liferay/content/targeting/rule/categories)
   in the
   [liferay-apps-content-targeting](https://github.com/liferay/liferay-apps-content-targeting/tree/samples-v1.1)
   repository.

3. Redeploy your rule plugin by running `ant deploy` from the command prompt.
   Now your rule uses its new icon and resides in the category you specified.

    ![Figure 2: This example Weather rule was modified to use the sun icon and to reside in the Session Attributes category.](../../images/icon-category-rule.png)

Now that you've modified some basic features in your `-Rule` class, you'll need
to develop the UI for your rule's configuration. As you read earlier, your rule
project already has an FTL template, which is used to show the rule's form.
Since a generated rule Java class extends `BaseRule` by default, your rule
already supports using the FreeMarker language. 

If you're interested in using a technology besides FreeMarker to implement your
UI, you can add a method `getFormHTML` to your `-Rule` class. For further
details on this method, see the
[BaseRule](https://github.com/liferay/liferay-apps-content-targeting/blob/samples-v1.1/content-targeting-api/service/com/liferay/content/targeting/api/model/BaseRule.java)
class.

The `getFormHTML` method is configured for FreeMarker templates in the
`BaseRule` class. This method is used to retrieve the HTML created by the
technology you choose, and to return it as a string that is viewable from your
rule's form. If you plan, therefore, on using an alternative to FreeMarker, you
must override this method by creating and modifying it in your `-Rule` class.
This tutorials demonstrates implementing the UI using FreeMarker. 

If you wanted, for example, to create user segment rules based on the type of
weather a user is experiencing, you could create a drop-down menu that lets the
administrator select a weather type to associate with that user segment rule.
Here's a code snippet from a FreeMarker template (`ct_fields.ftl`) that
could be applied to this example: 

    <@aui["fieldset"]>
        <@aui["select"] name="weather">
            <@aui["option"] label="sunny" selected=(weather == "sunny") value="sunny" />
            <@aui["option"] label="clouds" selected=(weather == "clouds") value="clouds" />
            <@aui["option"] label="mist" selected=(weather == "mist") value="mist" />
            <@aui["option"] label="snow" selected=(weather == "snow") value="snow" />
            ...
        </@>
    </@>

This FreeMarker code creates a *select* drop-down box with the name *weather*.
Then it specifies several options associated with different types of weather.
You could borrow from this FreeMarker code and change the name and labels for a
*select* drop-down box and values appropriate for your rule plugin.

![Figure 3: This example rule uses a *select* drop-down box.](../../images/select-box-rule.png)

For other working examples of FreeMarker templates used for rule configuration,
visit the Audience Targeting
[project](https://github.com/liferay/liferay-apps-content-targeting/tree/samples2015)
on GitHub.

+$$$

**Note:** Recall the last component of Audience Targeting rules: Language Keys.
To learn more about language keys and how to create, use, and generate them,
visit the
[Localization](/develop/tutorials/-/knowledge_base/6-2/localization) tutorials.

$$$

Now you'll jump back into modifying your rule's behavior via the `-Rule` class.

1. Find the `processRule` method in your `-Rule` class. This method is called
   when you click *Save* after selecting your rule in the Rules form. The
   portlet's request and response, the rule instance's ID, and the values from
   the form can be used by this method.

    In some cases, you may need to retrieve info from the portlet's request
    and response or the rule's ID. This tutorial demonstrates using the
    `values` parameter. This parameter represents all the values on the form
    you're saving. 

2. If you wanted to process one of the form's values, you could do that from the
   `processRule` method. You'll need to return the string value for the selected
   entity you chose for your rule type. For example, recall the FreeMarker code
   example you studied earlier. To retrieve the selected value from the select
   box, you'd need to retrieve the weather value:

        @Override
        public String processRule(
            PortletRequest request, PortletResponse response, String id,
            Map<String, String> values) {

            return values.get("weather");
        }

    The return value is stored in the `typeSettings` of the rule instance. The
    `typeSettings` field is managed by the framework in the Rule Instance table.

3. The next method you'd modify is the `populateContext` method. This
   method takes the value the user selected and injects it into the `context`
   map parameter. For example, the following `populateContext` method populates
   a `weather` context variable with the *weather* value of the `values` map
   parameter. 

        @Override
        protected void populateContext(
            RuleInstance ruleInstance, Map<String, Object> context,
            Map<String, String> values) {

            String weather = "sunny";

            if (!values.isEmpty()) {
                // Values from Request

                weather = values.get("weather");
            }
            else if (ruleInstance != null) {
                // Values from Database

                weather = ruleInstance.getTypeSettings();
            }

            context.put("weather", weather);
        }

    In this example implementation, this method checks if the values are
    available from the request. If they're not available, it checks for the
    values in the database.  Then the context map is updated by assigning the
    string key to the object value. 

Excellent! You've processed your rule and populated the rule's context. The last
step you'll need to take is specifying what your rule should evaluate. The
evaluation process determines whether a user matches the rule.

1. Find the `evaluate` method in your `-Rule` class. Insert logic that
   obtains the runtime user's value for what you plan to evaluate. For example:

        ...
        String userWeather = getUserWeather(anonymousUser);

    You can look at this method's code in the downloadable ZIP file for the
    sample weather rule. 

2. Insert logic that retrieves the value you stored in the type settings, using
   the `processRule` method. For the weather example, you could retrieve the value
   from the rule instance's type settings:

        String weather = ruleInstance.getTypeSettings();

3. Now that you have both the user's value and the rule's value, check whether
   they match. If they match, return `true`; otherwise, return `false`. 

        if (Validator.equals(userWeather, weather)) {
            return true;
        }

        return false;

4. Finally, deploy your rule plugin to the Liferay server. Your new rule is fully
   functional, and the UI you've defined is added to the Add/Edit User Segment
   form so that administrators can set a value for that specific user segment.

Excellent! You've created and deployed a fully functional rule.

Here are some things to consider as you implement and deploy rules:

- If you deploy your rule into a production environment, you may want to
consider adding your values to the cache (e.g., weather in different locations),
since obtaining the same value on every request is very inefficient and could
result in slowing down your portal.

- As an alternative to storing complex information in the `typeSettings` field
which is managed by the framework in the Rule Instance table, you may want to
consider persisting to a database by using 
[Service Builder](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/service-builder),
which is supported in the Rule plugins. 

- You can override
[BaseRule](https://github.com/liferay/liferay-apps-content-targeting/blob/samples-v1.1/content-targeting-api/service/com/liferay/content/targeting/api/model/BaseRule.java)'s
`deleteData` method in your `-Rule`, so that it deletes any data associated with
the rule that is currently being deleted.

- If your rule handles data or references to data that can be staged (e.g., a
reference to a page or web content article), you may need to override
[BaseRule](https://github.com/liferay/liferay-apps-content-targeting/blob/samples-v1.1/content-targeting-api/service/com/liferay/content/targeting/api/model/BaseRule.java)'s
`exportData` and `importData` methods, to manage the content properly. To see an
example of how these methods are used, visit the 
[ContentVisitedRule](https://github.com/liferay/liferay-apps-content-targeting/blob/samples-v1.1/rule-visited/src/com/liferay/content/targeting/rule/visited/ContentVisitedRule.java)
class.

You now know how to create a custom rule type for your Audience Targeting
application. For working examples of the default rules included in the Audience
Targeting app, visit the Audience Targeting
[project](https://github.com/liferay/liferay-apps-content-targeting/tree/samples-v1.1) page and
study the folders with the `rule-` prefix. To view the final solution of a
deployable sample weather rule, you can download its
[ZIP file](/documents/10184/359829/rule-weather.zip).

<!-- ## Customize the Rules Engine -->

## Related Topics [](id=related-topics)

[Developing with the Plugins SDK](/develop/tutorials/-/knowledge_base/6-2/plugins-sdk)

[Localization](/develop/tutorials/-/knowledge_base/6-2/localization)

[Using Service Builder to Generate a Persistence Framework](/develop/learning-paths/-/knowledge_base/6-2/using-service-builder-to-generate-a-persistence-fr)
