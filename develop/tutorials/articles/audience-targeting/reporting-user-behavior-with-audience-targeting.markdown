# Reporting User Behavior with Audience Targeting [](id=reporting-user-behavior-with-audience-targeting)

In the Audience Targeting application, a report is a tool that allow
administrators to analyze the behavior of users belonging to specific user
segments or in the context of a campaign.

Developers will be able to extend the set of available reports by creating and
deploying their own OSGi plugins which contain a class implementing the
[Report Interface](https://github.com/liferay/liferay-apps-content-targeting/blob/master/content-targeting-api/service/com/liferay/content/targeting/api/model/Report.java).

OSGi plugins can be quickly hot deployed and undeployed, managing their own
dependencies and providing new services that other OSGi plugins can consume.
In the case of a report OSGi plugin, it will be consumed by the Audience
Targeting application.

Adding a new report to the Audience Targeting application is easy. For example,
let's see how to create a very simple report for campaigns called
`Hits by country`.

1. In the root of the Audience Targeting project, run the create_report command
(depending on your OS):

        ./create_report.sh hits-by-country "Hits by Country"
        create_report.bat hits-by-country "Hits by Country"

2. Move to the newly generated folder `report-hits-by-country`. Notice that all
the necessary files have been added by the create_report command. If you now run
the `ant deploy` command in the root of the `report-hits-by-country` folder,
you'll find this new report listed when you select the Reports action in the
action menu of an specific campaign in the Audience Targeting application.
3. Of course you still need to make some changes to define how your report
works. Open the `HitsByCountryReport.java` file. Some of the methods that you
can implement to modify your report behavior are:
	* `getReportType`: This method determines which resource the report is
	associated to. By default that is a resource of the type Campaign, but you
	can change it to User Segment to create a report for this type of resources.
	* `updateReport`: This method will be called by the report UI to generate or
	update the report. Therefore it should process the available information
	(e.g. from the Analytics service) and persist it so that it can be
	* `getHtml`: This method will return the HTML displayed to administrators
	when accessing this report from a campaign. The BaseReport class already
	implements this method including a freemarker template placed  in
	templates/ct_report.ftl. For example, for the Hits By Country Report, you
	may display a the country flag and the number of hits for each country.
	* `getIcon`: Configure the icon displayed in the Report GUI. You should use
	the name of a FontAwesome icon. For example: "icon-coffee" or "icon-globe"
	(See <a href="http://fortawesome.github.io/Font-Awesome/3.2.1/">Font Awesome documentation</a>)
	* `getName`: The name of your Report. (It can be localized)
4. Finally, deploy the report plugin in the Liferay server. The new report will
be listed when you select the Reports action in the  action menu of an specific
campaign in the Audience Targeting application. Click the Update Report button
to force your report to obtain and display the latest information.
