# Reporting User Behavior with Audience Targeting

In the Audience Targeting application, a report is a tool that allows
administrators to analyze the behavior of users in a campaign or belonging to
specific user segments.

Developers are able to extend the set of available reports by creating and
deploying their own OSGi plugins which contain a class implementing the
Report Interface.

OSGi plugins can be hot deployed and undeployed, manage their own dependencies,
and provide new services that other OSGi plugins can consume. In the case of a
report OSGi plugin, it is consumed by the Audience Targeting application.

To follow this tutorial, you must first have the Audience Targeting development
tools installed. Visit the
[Installing the Audience Targeting Development Tools](/develop/tutorials/-/knowledge_base/7-0/creating-new-audience-targeting-rule-types#installing-the-audience-targeting-development-tools)
section for information on how to do this.

Adding a new report to the Audience Targeting application is easy. In this
tutorial, you'll learn how to create a report and deploy it to your Liferay
server.

1. Run the `blade create -t contenttargetingreport` command from a command
   prompt. For example, the command below creates a report project with
   `hits-by-country` for its project name and `HitsByCountryReport` as its class
   name within the `com.liferay.content.targeting.report` package:

        blade create -t contenttargetingreport -p com.liferay -c HitsByCountry hits-by-country

2. Move to the newly generated project folder that has your report's name. Open
   the folder and study what's been generated. Notice that the
   `blade create -t contenttargetingreport` command created all the necessary
   files to make the plugin deployable.

3. Now is a convenient time to deploy the project to see how it currently looks
   in Portal.

    To deploy the plugin project, start a @product@ instance, open a terminal to
    your plugin project's directory, and run the `blade deploy` command. You'll
    find this new report listed under the *Reports* section when viewing a
    campaign.

4. Of course, you still need to make some changes to define how your report
   works. Open the Java class file that was created
   (e.g.,`HitsByCountryReport.java`). Here are some of the methods that you can
   implement to modify your report behavior:

    * `getReportType`: determines the associated resource for the report. By
    default, the resource is a Campaign, but you can change it to User Segment
    to create a report for this type of resource.

    * `getName`: returns a name for your report. The name can be localized.

    * `updateReport`: called by the report UI to generate or update the report.
    Therefore, it should process the available information (e.g., from the
    Analytics service) and persist it so it can be displayed as a table/chart.

    * `getHtml`: returns the HTML displayed to administrators when accessing
    this report from a campaign. The `BaseJSPReport` class already implements
    this method including a JSP view placed in
    `resources/META-INF/resources/view.jsp`. For example, for a *Hits By
    Country* report, you could display the country flag and the number of hits
    for each country.

5. You have the option to make your reports editable. For example, instead of
   reporting the hits for all countries, you can generate different reports for
   different countries. To do this, you'd need a new view for the administrators
   to select the countries to filter for each *Hits By Country* report instance
   and store this information.

    * `isInstantiable`: by returning `true`, you declare your report as
    editable. The default value is `false`.

    * `getEditHtml`: returns the HTML displayed to administrators when creating
    or editing this report from a campaign. The `BaseJSPReport` already
    implements this method including a JSP view placed in
    `resouces/META-INF/resources/edit.jsp`. For example, for a *Hits By Country*
    report, you could display a selector with all the available countries.

	* `processEditReport`: this method is very similar to the `processRule`
	method reviewed in the
	[Creating New Audience Targeting Rule Types](/develop/tutorials/-/knowledge_base/7-0/creating-new-audience-targeting-rule-types)
	tutorial. This method is called when you click *Save* in the edit report
	form. The return type is stored by default in the `typeSettings` field
	of the `reportInstance` entity, so that you can later retrieve this value to
	filter the results by the selected country.

6. Finally, redeploy the report plugin to the Liferay server. The new report is
   listed under the *Reports* section when editing a specific campaign in the
   Audience Targeting application. If you made your report editable, then you
   will find your report listed in the Add Report button of the same section.
   Click the *Update Report* option to force your report to obtain and display
   the latest information.

Congratulations! You're now equipped with the knowledge necessary to create your
own report types for your Audience Targeting app. Remember that if you want to
generate reports based on the information that Audience Targeting is already
tracking, you can use the Audience Targeting Custom Report type and create your
own metrics as described in the
[Tracking User Actions with Audience Targeting](/develop/tutorials/-/knowledge_base/7-0/tracking-user-actions-with-audience-targeting)
tutorial.

## Related Topics

[Tracking User Actions with Audience Targeting](/develop/tutorials/-/knowledge_base/7-0/tracking-user-actions-with-audience-targeting)

[Creating Modules with Blade CLI](/develop/tutorials/-/knowledge_base/7-0/creating-modules-with-blade-cli)

[Internationalization](/develop/tutorials/-/knowledge_base/7-0/internationalization)
