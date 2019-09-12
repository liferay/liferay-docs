---
header-id: creating-a-custom-rule-type
---

# Creating a Custom Rule Type

[TOC levels=1-4]

First, you must create a module and ensure it has the necessary Content 
Targeting API dependencies.

| **Note:** To view the Javadoc for the Content Targeting classes mentioned in
| this article, download the
| [Javadoc JAR](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc).

1.  [Create a module](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#creating-a-module) 
    project for deploying a rule. A Blade CLI
    [content-targeting-rule](/docs/7-1/reference/-/knowledge_base/r/content-targeting-rule-template)
    template is available to help you get started quickly. It sets the default
    configuration for you, and it contains boilerplate code so you can skip the
    file creation steps and get started right away. To use it, use this Blade
    command: 

    ```bash
    blade create -t content-targeting-rule weather-rule
    ```

2.  Make sure the dependencies are up to date, as sometimes the template gets
    out of sync with the latest release. Here are the dependency versions you
    should see in a Gradle based rule:

    ```groovy
    dependencies {
        compileOnly group: "com.liferay.content-targeting", name: "com.liferay.content.targeting.analytics.api", version: "5.0.0"
        compileOnly group: "com.liferay.content-targeting", name: "com.liferay.content.targeting.anonymous.users.api", version: "3.0.0"
        compileOnly group: "com.liferay.content-targeting", name: "com.liferay.content.targeting.api", version: "5.0.0"
        compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "3.6.2"
        compileOnly group: "com.liferay.portal", name: "com.liferay.util.taglib", version: "2.0.0"
        compileOnly group: "javax.portlet", name: "portlet-api", version: "3.0.0"
        compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
        compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
    }
    ```

    You can learn more about exposing the Content Targeting API in the
    [Accessing the Content Targeting API](/docs/7-1/tutorials/-/knowledge_base/t/accessing-the-content-targeting-api)
    tutorial. Once you've created your module and specified its dependencies,
    you must define your rule's behavior. How your rule behaves is controlled
    by a Java class file that you create.

3.  In the module's `src` directory appears a generated class. To follow naming
    conventions, your class name should begin with the rule name you're
    creating, and end with *Rule* (e.g., `WeatherRule.java`). Your Java class
    should implement the 
    [`com.liferay.content.targeting.api.model.Rule`](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc)
    interface.

    You must implement the `Rule` interface, but there are `Rule` extension
    classes that provide helpful utilities that you can extend. For example,
    your rule can extend the
    [`com.liferay.content.targeting.api.model.BaseJSPRule`](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc)
    class to support generating your rule's UI using JSPs. This tutorial
    demonstrates implementing the UI using a JSP and assumes the `Rule`
    interface is implemented by extending the `BaseJSPRule` class. For more
    information on choosing a UI for your rule, see 
    [Selecting a UI Technology](/develop/tutorial/-/knowledge_base/7-1/best-practices-for-audience-targeting#selecting-a-ui-technology).

4.  Directly above the class's declaration should be the following annotation:

    ```java
    @Component(immediate = true, service = Rule.class)
    ```

    This annotation declares the implementation class of the Component, and
    specifies to start the module immediately once deployed to @product@.

Now that your Java class is set up, you must define how your rule works by
implementing the `Rule` interface's methods. You'll begin implementing these
methods next.

| **Note:** If you're planning on developing a social rule type that classifies
| users based on their social network profile, remember that the specific social
| network's SSO (Single Sign On) must be enabled and configured properly. Visit
| the [Social Rules](/docs/7-1/user/-/knowledge_base/u/liferay-audience-targeting-rules#social-rules)
| section for more details.

Next you'll define the view/save lifecycle for the weather rule.
