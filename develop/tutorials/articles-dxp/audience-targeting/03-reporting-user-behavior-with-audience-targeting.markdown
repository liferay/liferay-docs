# Reporting User Behavior with Audience Targeting [](id=reporting-user-behavior-with-audience-targeting)

In the Audience Targeting application, a report is a tool that allows
administrators to analyze the behavior of users in a campaign or belonging to
specific user segments.

Developers are able to extend the set of available reports by creating and
deploying their own OSGi plugins which contain a class implementing the
`Report` Interface.

OSGi plugins can be hot deployed and undeployed, manage their own dependencies,
and provide new services that other OSGi plugins can consume. In the case of a
report OSGi plugin, it is consumed by the Audience Targeting application.

Adding a new report to the Audience Targeting application is easy. In this
tutorial, you'll learn how to create a report and deploy it to your Liferay
server. The first thing you should do is create a module.

1. Create a module for deploying a report using your favorite third party tool.
   A Blade CLI
   [contenttargetingreport](/develop/tutorials/-/knowledge_base/7-0/content-targeting-report-template)
   template is available to help you get started quickly by setting all the
   default configuration for you, and it contains boilerplate code so you can
   skip the file creation steps and get started right away.

2. Make sure your module specifies the dependencies necessary for an Audience
   Targeting report. For example, you should specify the Content Targeting API
   and necessary Liferay packages. For example, examine the example
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

Once you've created your module and specified its dependencies, you'll need to
define your report's behavior. The behavior of your report is controlled from a
Java class file that you create.

1. Create a unique package name in the module's `src` directory, and create a
   new Java class in that package. To follow naming conventions, your class name
   should begin with the report name you're creating, and end with *Report*
   (e.g., `HitsByCountryReport.java`). Your Java class should implement the
   `Report` interface.

2. Directly above the class's declaration, insert the following code:

        @Component(immediate = true, service = Report.class)

    This annotation declares the implementation class of the Component, and
    specifies to immediately start the module once deployed to @product@.

Before diving deeper into your `-Report` class, it's important to understand
what is available for you to extend from this class. It is required to implement
the `Report` interface, but there are `Report` extension classes you can extend
from in your custom report, which provide helpful utilities. For example, you
can extend the `BaseJSPReport` class for support when generating your report's
UI using JSPs.

Since Liferay 7.0, JSP is the preferred technology for Audience Targeting
extension views. FreeMarker views, however, are still supported through their
respective base classes (e.g., `BaseFreemarkerReport`). If you're interested in
using a technology besides JSP or FreeMarker to implement your UI, you can add a
method `getHTML` to your `-Report` class.

The `getHTML` method is used to retrieve the HTML created by the technology you
choose, and to return it as a string that is viewable from your report. If you
plan, therefore, on using an alternative to JSP or FreeMarker, you must override
this method by creating and modifying it in your `-Report` class. This tutorial
demonstrates implementing the UI using JSP, and assumes the `Report` interface
is implemented by extending the `BaseJSPReport` class.

Of course, you still need to make some changes to define how your report works.
Here are some methods you can implement to modify your report's behavior.
   
<!-- The below method descriptions are the Javadoc copied from the `Report`
interface. Since the source code is not accessible and the Javadoc for Audience
Targeting is not currently published, I've provided some methods and
descriptions until the Javadoc is available publicly. -Cody -->

- `activate`: Does processing when the report is installed.
- `deActivate`: Does processing when the report is uninstalled.
- `getDescription`: Returns the localized description.
- `getHtml`: returns the HTML displayed to administrators when accessing this
   report from a campaign. The `BaseJSPReport` class already implements this
   method. For example, for a *Hits By Country* report, you could display the
   country flag and the number of hits for each country.
- `getIcon`: Returns the Font Awesome CSS class for the report icon.
- `getName`: returns a name for your report. The name can be localized.
- `getReportKey`: Returns the key that identifies the report.
- `getReportType`: determines the associated resource for the report. By
   default, the resource is a Campaign, but you can change it to User Segment
   to create a report for this type of resource.
- `isVisible`: Returns `true`if the report is visible for a specific element.
- `updateReport`: called by the report UI to generate or update the report.
   Therefore, it should process the available information (e.g., from the
   Analytics service) and persist it so it can be displayed as a table/chart.

There are also a few methods available to make your reports editable. For
example, suppose you're developing a report that tracks hits by country for a
web content article. Instead of reporting the hits for all countries, you can
generate different reports for different countries. To do this, you'd need a new
view for the administrators to select the countries to filter for each *Hits By
Country* report instance and store this information.

- `getEditHtml`: returns the HTML displayed to administrators when creating
   or editing this report from a campaign. The `BaseJSPReport` already
   implements this method. For example, for a *Hits By Country* report, you
   could display a selector with all the available countries.
- `isInstantiable`: by returning `true`, you declare your report as
   editable. The default value is `false`.
- `processEditReport`: this method is very similar to the `processRule` method
   reviewed in the
   [Creating New Audience Targeting Rule Types](/develop/tutorials/-/knowledge_base/7-0/creating-new-audience-targeting-rule-types)
   tutorial. This method is called when you click *Save* in the edit report form.
   The return type is stored by default in the `typeSettings` field of the
   `reportInstance` entity, so that you can later retrieve this value to filter
   the results by the selected country.

Once you've wired up your view layer (e.g., JSP, FreeMarker, etc.), you can also
configure language keys for the report. You can learn more about language keys
and how to create, use, and generate them by visiting the
[Internationalization](/develop/tutorials/-/knowledge_base/7-0/internationalization)
tutorials.

Finally, deploy the report plugin to the Liferay server. The new report is
listed under the *Reports* section when editing a specific campaign in the
Audience Targeting application. If you made your report editable, then you will
find your report listed in the Add Report button of the same section. Click the
*Update Report* option to force your report to obtain and display the latest
information.

Congratulations! You're now equipped with the knowledge necessary to create your
own report types for your Audience Targeting app. Remember that if you want to
generate reports based on the information that Audience Targeting is already
tracking, you can use the Audience Targeting Custom Report type and create your
own reports as described in the
[Tracking User Actions with Audience Targeting](/develop/tutorials/-/knowledge_base/7-0/tracking-user-actions-with-audience-targeting)
tutorial.

## Related Topics [](id=related-topics)

[Tracking User Actions with Audience Targeting](/develop/tutorials/-/knowledge_base/7-0/tracking-user-actions-with-audience-targeting)

[Creating Modules with Blade CLI](/develop/tutorials/-/knowledge_base/7-0/creating-modules-with-blade-cli)

[Internationalization](/develop/tutorials/-/knowledge_base/7-0/internationalization)
