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
   
    The `create_rule command automatically created default files that allow the
    plugin to be deployable right away. Before jumping in and learning about the
    important files you'll need to focus on editing, you can deploy the project
    to see how it currently looks in Portal.

3. Run the `ant deploy` command in the root of the newly generated folder.
   You'll find this new rule listed when creating or editing a user segment in
   the Audience Targeting application.

4. To view your new Weather rule, navigate to your portal's *Admin* &rarr; *Site
   Administration* &rarr; *Configuration* &rarr; *Audience Targeting* menu.
   Click *Add User Segment* and scroll down to the Rules form and you'll notice
   a Sample drop-down menu with the Weather rule you just deployed.

    ![Figure 1: Although your new Weather rule is very bare bones, it is deployable to your Portal straight out-of-the-box.](../../images/default-sample-rule.png)

    The default rule is not configured to evaluate anything yet, but you're able
    to drag and drop the rule onto the form, as shown above.

Awesome! You've deployed your rule plugin. Next, you'll need to learn about the
components that were generated for you and how to edit them to create a
functional Audience Targeting rule.

There are three components you can specify for your rule:

- *Rule Behavior*
- *UI for Configuration (optional)*
- *Language Keys (optional)*

The behavior of your rule is controlled from a generated Java class file located
in your rule's `src/com/liferay/content/targeting/rule/<RULE_NAME>` directory.
The rule's UI and language keys can be configured in the
`templates/ct_fields.ftl` and `content/Language.properties` files, respectively.
You'll learn more about the latter two components later on.

You'll begin creating your rule's functionality by specifying its behavior via
the Java class (e.g., `WeatherRule.java`). This class already implements the
`Rule` interface (required), and extends the `BaseRule` class. Furthermore,
multiple methods are present in the Java class by default, but will require
modifications before you have a working rule.

If you navigate back to your Weather rule inside of your portal, you'll notice
your new rule is listed under its own Sample category and has a puzzle piece
icon. You'll change these next by modifying a couple generated default methods.




Excellent! You now know how to create a custom rule type for your Audience
Targeting application. For working examples of the default rules included in the
Audience Targeting app, visit the Audience Targeting
[project](https://github.com/liferay/liferay-apps-content-targeting) page and
study the folders with the `rule-` prefix.

<!-- ## Customize the Rules Engine -->
