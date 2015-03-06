# Reporting User Behavior with Audience Targeting [](id=reporting-user-behavior-with-audience-targeting)

In the Audience Targeting application, a report is a tool that allows
administrators to analyze the behavior of users in a campaign or belonging to
specific user segments.

Developers are able to extend the set of available reports by creating and
deploying their own OSGi plugins which contain a class implementing the
[Report Interface](https://github.com/liferay/liferay-apps-content-targeting/blob/master/content-targeting-api/service/com/liferay/content/targeting/api/model/Report.java).

OSGi plugins can be hot deployed and undeployed, manage their own dependencies,
and provide new services that other OSGi plugins can consume. In the case of a
report OSGi plugin, it is consumed by the Audience Targeting application.

To follow this tutorial, you must first have the Audience Targeting
[project](https://github.com/liferay/liferay-apps-content-targeting) and
Plugins SDK configured properly. Visit the
[Installing the Audience Targeting Project](/develop/tutorials/-/knowledge_base/6-2/creating-new-audience-targeting-rule-types#installing-the-audience-targeting-project)
section for information on how to do this.

Adding a new report to the Audience Targeting application is easy. In this
tutorial, you'll learn how to create a report and deploy it to your Liferay
server.

1. In the root of the Audience Targeting project, run the `create_report`
   command (depending on your OS). For example, the command below shows creating
   a `hits-by-country` report named *Hits by Country*.

        create_report.bat hits-by-country "Hits by Country"

    or

        ./create_report.sh hits-by-country "Hits by Country"

2. Move to the newly generated folder that has your report's name prefixed with
   `report-` (e.g., `report-hits-by-country`). Notice that all the necessary
   files have been added by the `create_report` command. If you now run the `ant
   deploy` command in the root of the newly generated folder, you'll find this
   new report listed when you select the Reports action in the Action menu of a
   specific campaign in the Audience Targeting application.

3. Of course, you still need to make some changes to define how your report
   works. Open the Java class file that was created
   (e.g.,`HitsByCountryReport.java`). Here are some of the methods that you can
   implement to modify your report behavior:

    * `getReportType`: determines the associated resource for the report. By
    default, the resource is a Campaign, but you can change it to User Segment
    to create a report for this type of resource.

    * `updateReport`: called by the report UI to generate or update the report.
    Therefore, it should process the available information (e.g., from the
    Analytics service) and persist it so it can be displayed as a table/chart.

    * `getHtml`: returns the HTML displayed to administrators when accessing
    this report from a campaign. The `BaseReport` class already implements this
    method including a FreeMarker template placed in `templates/ct_report.ftl`.
    For example, for a *Hits By Country* report, you could display the country
    flag and the number of hits for each country.

    * `getIcon`: configures the icon displayed in the Report GUI. You should use
    the name of a FontAwesome icon. For example: *"icon-coffee"* or
    *"icon-globe"*
    (See [Font Awesome documentation](http://fortawesome.github.io/Font-Awesome/3.2.1/)).

    * `getName`: the name of your report (it can be localized).

4. Finally, deploy the report plugin to the Liferay server. The new report is
   listed when you select the Reports action in the Actions menu of a specific
   campaign in the Audience Targeting application. Click the *Update Report*
   button to force your report to obtain and display the latest information.

Congratulations! You're now equipped with the knowledge necessary to create a
custom report for your Audience Targeting app. For working examples of the
default reports included in the Audience Targeting app, visit the Audience
Targeting [project](https://github.com/liferay/liferay-apps-content-targeting)
page and study the folders with the `report-` prefix.
