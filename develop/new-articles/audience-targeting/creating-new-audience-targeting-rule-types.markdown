# Creating New Audience Targeting Rule Types [](id=creating-new-audience-targeting-rule-types)

In the Audience Targeting application, a User Segment is defined as a group of
users that match a set of rules. Out of the box, Liferay provides several types
of rules that are based on characteristics such as age range, gender, location,
etc. Visit the
[Liferay Audience Targeting Rules](/discover/portal/-/knowledge_base/7-0/liferay-audience-targeting-rules)
article for information on each rule type, and their configuration options. To
extend the set of available rule types, you can create a class that implements
the `Rule` interface and deploy the class in your own OSGi plugin.

OSGi plugins can be hot-deployed and undeployed, they manage their own
dependencies, and they can provide new services that other OSGi plugins can
consume. The Audience Targeting application can consume services from rule OSGi
plugins. 

This tutorial shows you how to create a custom rule type and deploy it in an
OSGi plugin. But before you begin creating a rule type, you must learn how to
install and use the Audience Targeting development tools. This tutorial covers
all of these things, plus it gives you helpful tips.

## Installing the Audience Targeting Development Tools [](id=installing-the-audience-targeting-development-tools)

Since Liferay 7.0, the Audience Targeting development tools to create extension
points such as Rule Types are based on Blade CLI. The Blade CLI is the easiest
way for Liferay developers to create new Liferay modules. Visit the
[Blade CLI](/develop/tutorials/-/knowledge_base/7-0/tooling)
chapter for more information on Blade CLI and how to use it.

In this tutorial, you’ll learn how to use Audience Targeting Blade CLI templates
standalone. You can, however, use Blade CLI from a Liferay Workspace, which
offers additional benefits. You can learn more about creating modules with a
Liferay Workspace in the
[Creating Modules in a Workspace tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-modules-in-a-workspace).

Next, you'll learn how to create a custom rule type using the Audience Targeting
Blade CLI templates.

## Creating a Custom Rule Type [](id=creating-a-custom-rule-type)

Adding a new type of rule to the Audience Targeting application is easy. In this
part of the tutorial, you'll learn how to create a rule and deploy it to your
Liferay server.

1. Assuming that the `blade` executable is available on your system path, run
   the `create -t contenttargetingrule` command from a command prompt.
   For example, the command below creates a rule project with 
   `weather` for its project name and `WeatherRule` as its class name within the
   `com.liferay.content.targeting.rule` package:

        blade create -t contenttargetingrule -p com.liferay -c Weather weather

2. Navigate to the newly generated project folder that has your rule's name.
	Open the folder and study what's been generated.
   
    The `create -t contenttargetingrule` command created default files that make
    the plugin deployable.

3. Now is a convenient time to deploy the project to see how it currently looks
   in Portal.

    To deploy the plugin project, open a terminal to your plugin project's
    directory and run the `blade deploy` command. You'll find this new rule
    listed when creating or editing a user segment in the Audience Targeting
    application.

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
rule's `src/main/java/com/liferay/content/targeting/rule/[RULE_NAME]` folder.
The rule's UI and language keys can be configured in the
`src/main/resources/META-INF/resources/view.jsp` and
`src/main/resources/content/Language.properties` files, respectively. You'll
learn more about the latter two components later on.

+$$$

**Note:** If you're planning on developing a social rule type that classifies
users based on their social network profile, it's important to remember that
they will not work properly unless the specific social network's SSO (Single
Sign On) is enabled and configured properly. Visit the
[Social Rules](/discover/portal/-/knowledge_base/7-0/liferay-audience-targeting-rules#social-rules)
section for more details.

$$$

Now, you can begin creating your rule's functionality by specifying its behavior
in the `-Rule` class (e.g., `WeatherRule.java`) that Blade CLI generated for
you. This class implements the Rule interface (required), and extends the
BaseJSPRule class. It's not mandatory to extend `BaseJSPRule`, but it provides
some helpful utilities, such as support for generating your rule's UI using JSP.
Note that there are multiple methods in the generated `-Rule` class; you must
modify them to create a working rule. 

If you navigate back to your rule deployed in your portal, notice that it's
listed under a category named *Sample*. You can change a rule's category by
modifying its generated default methods.

1. Locate the `getRuleCategoryKey` method and replace its return value with the
   key name of the category in which you'd like your rule to reside. For
   example, to categorize your rule in the *Session Attributes* category, 
   replace the return value `SampleRuleCategory.KEY` with the value
   `SessionAttributesRuleCategory.KEY`, and make sure to import that class.

2. Redeploy your rule plugin by running `blade deploy` from the command prompt.
   Now your rule uses its new icon and resides in the category you specified.

    ![Figure 2: This example Weather rule was modified to reside in the Session Attributes category.](../../images/icon-category-rule.png)

Now that you've modified some basic features in your `-Rule` class, you'll need
to develop the UI for your rule's configuration. As you read earlier, your rule
project already has a JSP file, which is used to show the rule's form.
Since a generated rule Java class extends `BaseJSPRule` by default, your rule
already supports using JSP pages.

Since Liferay 7.0, JSP is the preferred technology for Audience Targeting
extension views. However, FreeMarker views are still supported through their
respective base classes (e.g. `BaseFreemarkerRule`). If you're interested in
using a technology besides JSP or FreeMarker to implement your UI, you can add a
method `getFormHTML` to your `-Rule` class.

The `getFormHTML` is used to retrieve the HTML created by the technology you
choose, and to return it as a string that is viewable from your rule's form.
If you plan, therefore, on using an alternative to JSP or FreeMarker, you
must override this method by creating and modifying it in your `-Rule` class.
This tutorials demonstrates implementing the UI using JSP.

If you wanted, for example, to create user segment rules based on the type of
weather a user is experiencing, you could create a drop-down menu that lets the
administrator select a weather type to associate with that user segment rule.
Here's a code snippet from a JSP template (`view.jsp`) that could be applied to
this example:

	<aui:fieldset>
		<aui:select name="weather">
			<aui:option label="sunny" selected="<%= weather == "sunny" %>" value="sunny" />
			<aui:option label="clouds" selected="<%= weather == "clouds" %>" value="clouds" />
			<aui:option label="mist" selected="<%= weather == "mist" %>" value="mist" />
			<aui:option label="snow" selected="<%= weather == "snow" %>" value="snow" />
		</aui:select>
	</aui:fieldset>

This JSP code creates a *select* drop-down box with the name *weather*.
Then it specifies several options associated with different types of weather.
You could borrow from this JSP code and change the name and labels for a
*select* drop-down box and values appropriate for your rule plugin.

![Figure 3: This example rule uses a *select* drop-down box.](../../images/select-box-rule.png)

+$$$

**Note:** Recall the last component of Audience Targeting rules: Language Keys.
To learn more about language keys and how to create, use, and generate them,
visit the
[Localization](/develop/tutorials/-/knowledge_base/7-0/localization) tutorials.

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
   the `processRule` method. For the weather example, you could retrieve the
   value from the rule instance's type settings:

        String weather = ruleInstance.getTypeSettings();

3. Now that you have both the user's value and the rule's value, check whether
   they match. If they match, return `true`; otherwise, return `false`. 

        if (Validator.equals(userWeather, weather)) {
            return true;
        }

        return false;

4. Finally, deploy your rule plugin to the Liferay server. Your new rule is
   fully functional, and the UI you've defined is added to the Add/Edit User
   Segment form so that administrators can set a value for that specific user
   segment.

Excellent! You've created and deployed a fully functional rule.

Here are some things to consider as you implement and deploy rules:

- If you deploy your rule into a production environment, you may want to
consider adding your values to the cache (e.g., weather in different locations),
since obtaining the same value on every request is very inefficient and could
result in slowing down your portal.

- As an alternative to storing complex information in the `typeSettings` field
which is managed by the framework in the Rule Instance table, you may want to
consider persisting to a database by using 
[Service Builder](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/service-builder),
which is supported in the Rule plugins. 

- You can override BaseJSPRule `deleteData` method in your `-Rule`, so that it
deletes any data associated with the rule that is currently being deleted.

- If your rule handles data or references to data that can be staged (e.g., a
reference to a page or web content article), you may need to override
BaseJSPRule `exportData` and `importData` methods, to manage the content
properly.

You now know how to create a custom rule type for your Audience Targeting
application.

<!-- ## Customize the Rules Engine -->

## Related Topics [](id=related-topics)

[Creating Modules with Blade CLI](/develop/tutorials/-/knowledge_base/7-0/creating-modules-with-blade-cli)

[Localization](/develop/tutorials/-/knowledge_base/7-0/localization)

[Using Service Builder to Generate a Persistence Framework](/develop/tutorials/-/knowledge_base/7-0/using-service-builder-to-generate-a-persistence-fr)
