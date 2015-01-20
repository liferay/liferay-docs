# Creating New Audience Targeting Rule Types [](id=creating-new-audience-targeting-rule-types)

In the Audience Targeting application, a User Segment is defined as a group
of users that matches a set of rules. Out of the box, Liferay provides several
types of rules such as age range, gender, location...

Developers will be able to extend the set of available types of rules by
creating and deploying their own OSGi plugins which contain a class implementing
the [Rule Interface](https://github.com/liferay/liferay-apps-content-targeting/blob/master/content-targeting-api/service/com/liferay/content/targeting/api/model/Rule.java).

OSGi plugins can be quickly hot deployed and undeployed, managing their own
dependencies and providing new services that other OSGi plugins can consume.
In the case of a rule OSGi plugin, it will be consumed by the Audience
Targeting application.

Adding a new type of rule to the Audience Targeting application is easy.
For example, let's see how to create a very simple rule called `Time Zone`.

1. In the root of the Audience Targeting project, run the create_rule command
(depending on your OS):
	
        ./create_rule.sh time-zone "Time Zone"
        create_rule.bat time-zone "Time Zone"
	
2. Move to the newly generated folder `rule-time-zone`. Notice that all the
necessary files have been added by the create_rule command. If you now run the
`ant deploy` command in the root of the `rule-time-zone` folder, you'll find
this new rule listed when creating or editing a User Segment in the Audience
Targeting application.
3. Of course you still need to make some changes to define how your rule works.
Open the `TimezoneRule.java` file. Some of the methods that you can implement to
modify your rule behavior are:
	* `processRule`: This method will handle the information provided by the
	administrator when configuring this rule through the Rule GUI.
	For example, to store the selected time zone in the typeSettings field in the
	database from a select.
	* `evaluate`: This method determines whether a given user matches the rule
	with the information that has been stored. For example, it checks the time
	zone in the user profile and compares with the one stored in the database by
	the processRule method. If they match, it will return true.
	* `getFormHtml`: This method will return the HTML displayed to
	administrators when configuring a Rule through the Rule GUI. The BaseRule
	class already implements this method including a freemarker template placed
	in templates/ct_fields.ftl. For example, for the Time Zone Rule, you may add
	a selector with the available time zones.
	* `getIcon`: Configure the icon displayed in the Rule GUI. You should use
	the name of a FontAwesome icon. For example: "icon-coffee" or "icon-globe"
	(See <a href="http://fortawesome.github.io/Font-Awesome/3.2.1/">Font Awesome documentation</a>)
	* `getName`: The name of your Rule. (It can be localized)
	* `getSummary`: A description of the Rule once it is configured. This will
	be used to help administrators. For example, if the Time Zone Rule has been
	configured to GMT Zone, then the summary may be "Users who are in Timezone
	GMT".
4. Finally, deploy the rule plugin in the Liferay server. The new rule will be
available in the add/edit User Segment form. When the User Segment admin selects
it, the piece of GUI defined by the developer (eg. the time zone selector) is
added to the add/edit User Segment form so that the admin can set a value for
that specific user segment.

<!-- ## Customize the Rules Engine -->

## Troubleshooting [](id=troubleshooting)

### If Changes Are Not Visible After Deploy [](id=if-changes-are-not-visible-after-deploy)

If the plugin has been successfully deployed but the changes are not visible,
check that the generated .jar in the /dist folder of the plugins SDK actually
contains the latest modifications.

Also set this property to `0` to always retrieve the Freemarker code from the
template instead of the cache:

    freemarker.engine.resource.modification.check.interval=0

## More information [](id=more-information)

In the [liferay-plugins](https://github.com/liferay/liferay-plugins)
repository, plugins are laid out in a software development kit (SDK) -- the
Liferay Plugins SDK. All Liferay plugin types, including portlets, themes,
layout templates, hooks, and EXT plugins, can be created and maintained in the
SDK. [The Plugins SDK]
(http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/leveraging-the-plugins-sdk-liferay-portal-6-2-dev-guide-02-en)
chapter of Liferay's [Development Guide]
(http://www.liferay.com/documentation/liferay-portal/6.2/development)
explains how to create, build, and deploy your plugins.

There are many other options for developing new Liferay plugins using the
Plugins SDK. Consult the [Liferay Development
Guide](http://www.liferay.com/documentation/liferay-portal/6.2/development) for
indispensable explanations, examples, and reference material on the Liferay
Plugins SDK and surrounding technologies.

Also, check out Liferay IDE. The [Liferay IDE
project](http://www.liferay.com/community/liferay-projects/liferay-ide) provides
an Eclipse-based Liferay development environment to help you build and maintain
Liferay projects easily.

Finally, consider using Maven to build Liferay Plugins. For excellent overviews
of Maven support for Liferay, check out [Mika Koivisto's
presentation](http://www.slideshare.net/koivimik/developing-liferay-plugins-with-maven)
and [Getting Started with Liferay Maven
SDK](http://www.liferay.com/web/mika.koivisto/blog/-/blogs/12322618).
