# Creating New Audience Targeting Rule Types [](id=creating-new-audience-targeting-rule-types)

In the Audience Targeting application, a User Segment is defined as a group
of users that matches a set of rules. Out of the box, Liferay provides several
types of rules such as age range, gender, location, etc.

Developers will be able to extend the set of available types of rules by
creating and deploying their own OSGi plugins which contain a class implementing
the [Rule Interface](https://github.com/liferay/liferay-apps-content-targeting/blob/master/content-targeting-api/service/com/liferay/content/targeting/api/model/Rule.java).

OSGi plugins can be quickly hot deployed and undeployed, managing their own
dependencies and providing new services that other OSGi plugins can consume.
In the case of a rule OSGi plugin, it will be consumed by the Audience
Targeting application.

Before you begin creating a custom rule type, you'll learn how/where to install
the Audience Targeting project for usage, and a couple tips along the way.

## Installing the Audience Targeting Project [](id=installing-the-audience-targeting-project)

Before you can take advantage of the Audience Targeting project's scripts for
creating rules, reports, and tracking actions, you must first install the
project itself. To do this, you'll need to install the Audience Targeting SDK.

So why is this plugins SDK useful for Audience Targeting? By using the Audience
Targeting SDK, you can take full advantage of the development environment it
presents to developers.

You can download the Audience Targeting SDK from the following
[wiki](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Audience+Targeting).

Once you've completed this, the root directory you'll need to work from in the
SDK for the Audience Targeting tutorials is the `apps/content-targeting`
directory.

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
   (depending on your OS). For example, the command below shows creating a
   `time-zone` rule named *Time Zone*:

        create_rule.[bat|sh] time-zone "Time Zone"

2. Move to the newly generated folder that has your rule's name prefixed with
   `rule-` (e.g., `rule-time-zone`). Notice that all the necessary files have
   been added by the `create_rule` command. If you now run the `ant deploy`
   command in the root of the newly generated folder, you'll find this new rule
   listed when creating or editing a user segment in the Audience Targeting
   application.

3. Of course, you still need to make some changes to define how your rule works.
   Open the Java class file that was created (e.g., `TimeZoneRule.java`). Some
   of the methods that you can implement to modify your rule behavior are:

    * `processRule`: handles the information provided by the administrator when
    configuring this rule through the Rule GUI. For example, to store the
    selected time zone in the `typeSettings` field in the database from a
    *select*.

    * `evaluate`: determines whether a given user matches the rule with the
    information that has been stored. For example, it checks the time zone in
    the user profile and compares with the one stored in the database by the
    `processRule` method. If they match, it returns `true`.

    * `getFormHtml`: returns the HTML displayed to administrators when
    configuring a Rule through the Rule GUI. The `BaseRule` class already
    implements this method including a FreeMarker template placed in
    `templates/ct_fields.ftl`. For example, for the Time Zone Rule, you may add a
    selector with the available time zones.

    * `getIcon`: configures the icon displayed in the Rule GUI. You should use
    the name of a FontAwesome icon. For example: *icon-coffee* or *icon-globe*
    (See [Font Awesome documentation](http://fortawesome.github.io/Font-Awesome/3.2.1/))

    * `getName`: the name of your rule (it can be localized).

    * `getSummary`: the description of the Rule once it is configured. This is
    used to help administrators. For example, if the Time Zone Rule has been
    configured to GMT Zone, then the summary may be *Users who are in Timezone
    GMT*.

4. Finally, deploy the rule plugin in the Liferay server. The new rule will be
   available in the Add/Edit User Segment form. When the User Segment admin
   selects it, the GUI defined by the developer (e.g., the time zone selector)
   is added to the Add/Edit User Segment form so that the admin can set a value
   for that specific user segment.

Excellent! You now know how to create a custom rule type for your Audience
Targeting application. For working examples of the default rules included in the
Audience Targeting app, visit the Audience Targeting
[project](https://github.com/liferay/liferay-apps-content-targeting) page, and
study the folders with the `rule-` prefix.

<!-- ## Customize the Rules Engine -->
