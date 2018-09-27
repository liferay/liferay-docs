# Creating a Custom Rule Type [](id=creating-a-custom-rule-type)

Adding a new type of rule to the Audience Targeting application is easy. First,
you must create a module and ensure it has the necessary Content Targeting API 
dependencies.

1.  [Create a module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#creating-a-module) 
    project for deploying a rule. A Blade CLI [content-targeting-rule](/develop/reference/-/knowledge_base/7-1/content-targeting-rule-template)
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
    [Accessing the Content Targeting API](/develop/tutorials/-/knowledge_base/7-1/accessing-the-content-targeting-api)
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
    [Selecting a UI Technology](/develop/tutorial/-/knowledge_base/7-1/best-practices-for-rules#selecting-a-ui-technology)
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
[Social Rules](/discover/portal/-/knowledge_base/7-1/liferay-audience-targeting-rules#social-rules)
section for more details.

$$$

Next you'll define the view/save lifecycle for the weather rule.
