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

1.  [Create a module](/develop/tutorials/-/knowledge_base/7-0/starting-module-development#creating-a-module) 
    project for deploying a rule. A Blade CLI [content-targeting-rule](/develop/reference/-/knowledge_base/7-0/content-targeting-rule-template)
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
    [`com.liferay.content.targeting.api.model.Rule` interface](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/Rule.html).

    It is required to implement the
    [Rule](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/Rule.html)
    interface, but there are `Rule`
    extension classes that provide helpful utilities that you can extend. For
    example, your rule can extend the
    [BaseJSPRule](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/BaseJSPRule.html)
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
[Rule](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/Rule.html)
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
