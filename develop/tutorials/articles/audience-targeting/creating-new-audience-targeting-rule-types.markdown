# Creating New Audience Targeting Rule Types [](id=creating-new-audience-targeting-rule-types)

In the Audience Targeting application, a User Segment is defined as a group
of users that matches a set of rules. Out of the box, Liferay provides several
types of rules such as age range, gender, location, etc.

Developers are able to extend the set of available types of rules by creating
and deploying their own OSGi plugins which contain a class implementing the
[Rule Interface](https://github.com/liferay/liferay-apps-content-targeting/blob/master/content-targeting-api/service/com/liferay/content/targeting/api/model/Rule.java).

OSGi plugins can be hot deployed and undeployed, manage their own
dependencies, and provide new services that other OSGi plugins can consume. In
the case of a rule OSGi plugin, it is consumed by the Audience Targeting
application.

Before you begin creating a custom rule type, you'll learn how/where to install
the Audience Targeting project for usage, as well as a few tips along the way.

## Installing the Audience Targeting Project [](id=installing-the-audience-targeting-project)

Before you can take advantage of the Audience Targeting project's scripts for
creating rules, reports, and tracking actions, you must first install the
project itself. To do this, you'll need to install the Audience Targeting SDK.

So why is this SDK useful for Audience Targeting? The Audience Targeting project
is included in the SDK, meaning you can run useful development scripts from the
project that automatically create customizable rule, report, and tracking action
plugins. It essentially provides an entire Audience Targeting development
environment.

You can download the Audience Targeting SDK from the following
[wiki](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Audience+Targeting).
To learn more about how to install and use a plugins SDK, visit the
[Plugins SDK](/develop/tutorials/-/knowledge_base/6-2/plugins-sdk) tutorials.

<!-- There's not an official downloads page because the WCM team is working
on a new SDK based on Gradle and they want that one to replace this one. Once
that one is available (~end Feb 2015), an official downloads page link will be
available. -Cody -->

Once you've completed this, unzip it. Inside the SDK, you'll work from
`apps/content-targeting` directory.

+$$$

**Tip:** When a plugin has been successfully deployed but the changes are not
visible, check that the generated `.jar` in the `/dist` folder of the plugins
SDK actually contains the latest modifications.

Also set the following property to `0` to always retrieve the FreeMarker code
from the template instead of the cache:

    freemarker.engine.resource.modification.check.interval=0

$$$

Next, you'll learn how to create a custom rule type using the Audience Targeting
project you just installed.

## Creating a Custom Rule Type [](id=creating-a-custom-rule-type)

Adding a new type of rule to the Audience Targeting application is easy. In this
tutorial, you'll learn how to create a rule and deploy it to your Liferay
server.

1. In the root of the Audience Targeting project, run the `create_rule` command
   (depending on your OS) from a command prompt. For example, the command below
   shows creating a `weather` rule named *Weather*:

        create_rule.bat weather "Weather"

    or

        ./create_rule.sh weather "Weather"

2. Move to the newly generated folder that has your rule's name prefixed with
   `rule-` (e.g., `rule-weather`). Open the folder and study what has been
   generated for you automatically.
   
    The `create_rule` command automatically created default files that allow the
    plugin to be deployable right away. Before jumping in and learning about the
    important files you'll need to modify, you can deploy the project to see how
    it currently looks in Portal.

3. Run the `ant deploy` command in the root of the newly generated folder.
   You'll find this new rule listed when creating or editing a user segment in
   the Audience Targeting application.

4. To view your new rule, navigate to your portal's *Admin* &rarr; *Site
   Administration* &rarr; *Configuration* &rarr; *Audience Targeting* menu.
   Click *Add User Segment* and scroll down to the Rules form and you'll notice
   a Sample drop-down menu with the rule you just deployed.

    ![Figure 1: Although your new rule is very bare bones, it is deployable to your Portal straight out-of-the-box.](../../images/default-sample-rule.png)

    The default rule is not configured to evaluate anything yet, but you're able
    to drag and drop the rule onto the form, as shown above.

Awesome! You've deployed your rule plugin. Next, you'll need to learn about the
components that were generated for you and how to edit them to create a
functional Audience Targeting rule.

There are three components you can specify for your rule:

- *Rule Behavior*
- *UI for Configuration (optional)*
- *Language Keys (optional)*

The behavior of your rule is controlled from a Java class file located in your
rule's `src/com/liferay/content/targeting/rule/<RULE_NAME>` directory. The
rule's UI and language keys can be configured in the `templates/ct_fields.ftl`
and `content/Language.properties` files, respectively. You'll learn more about
the latter two components later on.

You'll begin creating your rule's functionality by specifying its behavior via
the Java class (e.g., `WeatherRule.java`) that implements the Rule Interface.
The class generated for you already implements the `Rule` interface (required),
and extends the `BaseRule` class (it is not mandatory to extend this class, but
BaseRule provides some useful utilities such as the support to generate the UI
of your rule using freemarker). Furthermore, multiple methods are present in the
Java class by default, but will require modifications before you have a working
 rule.

If you navigate back to your rule inside of your portal, you'll notice it's
listed under its own Sample category and has a puzzle piece icon. You'll change
these next by modifying a couple generated default methods.

1. Open your rule's Java class file and find the `getIcon` method. This method
   configures the icon displayed in the Rule GUI. Replace the *"icon-puzzle"*
   return value with the name of a FontAwesome icon (e.g., *"icon-coffee"* or
   *"icon-globe"*) that appropriately fits your rule. Visit the [Font Awesome
   documentation](http://fortawesome.github.io/Font-Awesome/3.2.1/) for a
   complete list of values.

2. Locate the `getRuleCategoryKey` method and replace its return value with the
   category key you'd like your rule to reside in. For example, to categorize
   your rule in the *Session Attributes* category, replace the
   `SampleRuleCategory.KEY` return value with the
   `SessionAttributesRuleCategory.KEY` return value.

3. Deploy your rule again by running `ant deploy` from the command prompt. Your
   updated rule should now have its customized icon and reside in the category
   you specified.

    ![Figure 2: For a Weather rule, the sun icon is chosen, and the Sesssion Attributes category is configured for the rule's residing place.](../../images/icon-category-rule.png)

Now that you've modified some basic features in your Java class, you'll need to
develop the UI for your rule's configuration. As you read earlier, your rule
project already has an FTL template, which is used to show the rule's form.
Since your rule is extending `BaseRule`, the FreeMarker language is already
supported.

If you're interested in another technology besides FreeMarker, create the
`getFormHTML(...)` method in your `<RULE_NAME>Rule.java` class. For further
details on this method, visit the
[BaseRule](https://github.com/liferay/liferay-apps-content-targeting/blob/master/content-targeting-api/service/com/liferay/content/targeting/api/model/BaseRule.java)
class.

The `getFormHTML` method is configured for FreeMarker templates in the
`BaseRule` class. Therefore, if you plan on using an alternative to FreeMarker,
you'll need override this method by creating and modifying it in the
`<RULE_NAME>Rule.java` class. This method is used to retrieve the HTML created
by the technology you choose, and return it as a string that is viewable from
your rule's form.

For an example to illustrate what a drop-down menu would look like in a
FreeMarker template (e.g., `ct_fields.ftl`), study the code snippet below:

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
Then there are several options to choose from that describe the type of weather.
You would change the name and labels to match the *select* drop-down box/values
you prefer.

![Figure 3: This example rule uses the *select* drop-down box.](../../images/select-box-rule.png)

For other working examples of FreeMarker templates used for rule configuration,
visit the Audience Targeting
[project](https://github.com/liferay/liferay-apps-content-targeting/tree/samples2015)
on Github.

+$$$

**Note:** Recall the last component of Audience Targeting rules: Language Keys.
To learn more about language keys and how to create, use, and generate them,
visit the
[Localization](/develop/tutorials/-/knowledge_base/6-2/localization) tutorials.

$$$

Now you'll jump back into modifying your rule's behavior via the
`<RULE_NAME>Rule.java` class.

1. Find the `processRule` method in your Java class. This method is called when
   you click *Save* after selecting your rule in the Rules form. The portlet's
   request and response, the rule instance's ID, and the values from the form
   are received when the method is called.

    In some cases, you may need to retrieve info from the portlet's request
    and response, or the rule's ID. However, for this tutorial, you'll only need
    the `values` parameter. This parameter represents all the values on the form
    you're saving. 

2. You'll need to return the string value for the selected entity you chose for
   your rule type. For example, recall the FreeMarker code example you studied
   earlier. To retrieve the selected value from the *select* box, you'd need to
   retrieve the *weather* value:

        @Override
        public String processRule(
            PortletRequest request, PortletResponse response, String id,
            Map<String, String> values) {

            return values.get("weather");
        }

    The return value is stored in the `typeSettings` of the rule instance.

3. The next method you'll need to modify is the `populateContext` method. This
   method injects the value the user selected into the variable you're returning
   in the `processRule` method. For example, the following `populateContext`
   method populates a *weather* context variable.

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

    First, this method checks if the value is available from the request. If
    not, it checks for the value in the database. Then the context map is
    updated by assigning the string key to the object value.

Excellent! You've processed your rule and populated the rule's context. The last
step you'll need to take is specifying what your rule should evaluate. The
evaluation process determines whether the user matches the rule.

1. Find the boolean `evaluate` method in your `<RULE_NAME>Rule.java` class.
   Insert logic that obtains the real time user value of what you plan on
   evaluating. For example:

        ...
        String userWeather = getUserWeather(anonymousUser);

2. Insert logic that retrieves the value you stored in the `processRule` method.
   For this example, the value would be retrieved from the rule instance's type
   settings:

        String weather = ruleInstance.getTypeSettings();

3. Now that you have both the user value and the rule value, check to see if the
values match. If they match, return `true`; otherwise, return `false`:

        if (Validator.equals(userWeather, weather)) {
            return true;
        }

        return false;

4. Finally, deploy the rule plugin in the Liferay server. Your new rule is fully
   functional, and the GUI you've defined is added to the Add/Edit User Segment
   form so that administrators can set a value for that specific user segment.

If you were to deploy this rule (or a similar one in production) you may want to
consider adding some cache for the values of the weather in different locations
since obtaining the weather on every request would be very inefficient and it
could slow down your portal. Rule plugins do support Service Builder, so they
can even persist complex information into the database (besides the typeSettings
field already managed by the framework in the Rule Instance table).

The method deleteData in your Rule class can be used to delete any data you may
have created for the rule that is currently being deleted.

If your rule handles data or references to data that can be staged (for example,
a reference to a page or web content), you may need to use the methods
exportData and importData to manage the content properly. See an example in the
[VisitedContentRule](https://github.com/liferay/liferay-apps-content-targeting/blob/master/rule-visited/src/com/liferay/content/targeting/rule/visited/ContentVisitedRule.java)

Excellent! You now know how to create a custom rule type for your Audience
Targeting application. For working examples of the default rules included in the
Audience Targeting app, visit the Audience Targeting
[project](https://github.com/liferay/liferay-apps-content-targeting) page and
study the folders with the `rule-` prefix.

<!-- ## Customize the Rules Engine -->
