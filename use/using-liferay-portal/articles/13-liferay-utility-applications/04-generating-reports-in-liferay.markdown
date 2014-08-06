# Generating Reports in Liferay [](id=generating-reports-in-liferay)

![EE Only Feature](../../images/ee-feature-web.png)

Liferay's Reports portlet allows administrators to create reports and schedule
report generation runs. In order to work, the Reports portlet needs to be
combined with an appropriate implementation such as the JasperReports web
plugin. You can use Liferay's Reports portlet to create professional reports
containing charts, images, subreports, etc. When a report is generated, data is
dynamically pulled from Liferay's database into a template. Data can be pulled
into Jasper reports via JDBC and Jasper reports can be exported to many
different file formats including PDF, HTML, XLS, RTF, CSV, or XML. The Reports
and JasperReports plugins are available as apps on Liferay Marketplace. You can
purchase, download, and install the Reports and JasperReports apps directly from
your Liferay instance's Control Panel interface or you can navigate to
[http://www.liferay.com/marketplace](http://www.liferay.com/marketplace) in your
browser, purchase and download the apps, and copy the `.lpkg` files to your
Liferay instance's `/deploy` folder.

## Using the Reports Portlet

Once you've installed the Reports and Jasper Reports EE applications, log in to
your portal as an administrator and navigate to *Site Administration*. If your
applications have been successfully deployed, you'll find a *Reports Admin*
entry in the *Configuration* section on the left menu.

![Figure 13.22: To check that the Reports EE and Jasper Reports EE applications have been successfully deployed to your Liferay server, look for the *Reports Admin* entry in the Configuration section of your Site Administration page.](../../images/reports-admin-site-admin.png)

The Reports Admin portlet has three tabs:

- *Reports*
- *Definitions*
- *Sources*

The Reports tab shows a list of all generated reports. The Definitions tab shows
a list of report definitions. The most important features of a report definition
are the data source, which determines where to find the data to be displayed in
a report, and the template, which determines which information to display and
how to display it. The Sources tab allows you to add new data sources which can
be selected by report definitions. Note: your portal's database is automatically
set up as a default data source called *Portal*. The *Portal* data source does
not appear on the Sources tab but is selectable on the form for adding/editing a
report definition.

![Figure 13.23: Use the *Sources* tab of the Reports Admin portlet to define data sources for report definitions. Use the *Definitions* tab to define report definitions, generate reports, and schedule reports for generation. Use the *Reports* tab to browse through and download generated reports.](../../images/reports-admin-portlet.png)

In order to generate a report, you need to a have one or more report definitions
configured. To create a report definition, you need a data source and a report
template. If you'd like to use your portal's database as your report
definition's data source, use the default data source called *Portal*. If you'd
like to use a different data source, navigate to the *Sources* tab of the
Reports Admin portlet and click on the *Add Source* button. Enter a name for the
new data source and enter the JDBC connection information:

- Driver Class Name
- URL
- User Name
- Password

For example, to connect to a MySQL database called *data_source* installed on
the Liferay server, you could use the following credentials:

- Driver Class Name: *com.mysql.jdbc.Driver*
- URL: *jdbc:mysql://localhost/data_source?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false*
- User Name: *[MySQL User Name]*
- Password: *[MySQL Password]*

Click on the *Test Database Connection* button to make sure that you've entered
the connection information correctly. Liferay will respond with a success or
error message depending on whether or not it was able to connect to the data
source. From the Reports Admin portlet's Sources tab, you can see a list of all
the data sources that have been added. You can configure data sources'
permissions to customize who can edit them and you can delete data sources that
should no longer be used.

Once your data source has been saved, make sure that your report template is
ready for use. If you're using Jasper and need to create a Jasper template,
consider using a tool like iReport Designer or Jaspersoft Studio to create your
template. iReport is built on top of Netbeans IDE while Jaspersoft Studio is
Eclipse-based. 

Once you've created a Jasper template (`.jrxml` file), you're
ready to create a report definition. Liferay handles compiling the template,
populating the template with data, and exporting the report. For more
information on using iReport Designer and on the Jasper Report lifecycle, please
refer to Jaspersoft's [wiki](http://community.jaspersoft.com/wiki/ireport-designer-getting-started) and
[documentation](http://community.jaspersoft.com/documentation?version=7114).

To add a report definition, navigate to the *Definitions* tab of the Reports
Admin portlet and click *Add Report Definition*. Enter a definition name and,
optionally, a description. Select a data source for your report definition.
Remember that you can use your portal's database as your database by selecting
the default *Portal* data source. Then select a Jasper template (`.jrxml` file)
for your report template. Optionally, you can add report parameters and values
to your report definition and they'll be injected into the template at runtime
when the report is generated. Lastly, you can configure the permissions of your
new report definition. By default, new reports, report definitions, and data
sources are site-scoped. For this reason, new report definitions are set to be
viewable by site members. When you've completed your new report definition,
click *Save*.

Now you're ready to use your report definition to generate reports manually or
schedule them to be generated. From the Report Admin portlet's Definitions tab,
click *Actions* &rarr; *Add Report* next to your report definition. You can
choose any of the following reports formats:

- CSV
- HTML
- PDF
- RTF
- TXT
- XLS
- XML

You can configure email notifications or specific email recipients. Email
notifications just inform users that a report has been generated and provide a
link to the report's location in the portal. Email recipients actually receive
copies of the report as email attachments. You can customize the account from
which report notifications and deliveries are sent as well as the messages
themselves from the configuration window of the Reports Admin portlet. When
generating a report, you can also configure the permissions of the report to be
generated. By default, generated reports are site-scoped and are viewable by
site members.

To configure reports to be generated on a schedule, click *Actions* &rarr; *Add
Schedule* next to your report definition from the Report Admin portlet's
Definitions tab. You can select a start date and, optionally, an end date. You
can also select how often to repeat the report generation event:

- *Never*
- *Daily*
- *Weekly*
- *Monthly*
- *Yearly*

As on the *Add Report* form, you can select a report format, configure email
notifications and email recipients, and configure the permissions of the reports
to be generated. When you're done setting up your report generation schedule,
click *Save*. Great! Now your reports will automatically be generated on the
dates you've configured. You can create multiple reports from a single report
definition. You can also edit a report definition, configure a report
definition's permissions, or delete a report definition from the Report Admin
portlet's Definitions tab.

Once one or more reports have been generated, they'll appear in the Reports
Admin portlet's Reports tab. To edit a report's permissions or to delete a
report, use the *Actions* button next to the report's name. To view details
about a report, click on its name. From the details view of a report, you can
download the report by clicking *Actions* &rarr; *Download* next to the report
file. You can also deliver the report by clicking *Actions* &rarr; *Deliver
Report*, choosing an email recipient, and clicking *Deliver*. To delete the
report file, click *Actions* &rarr; *Delete*.

## Configuring the Reports Admin Portlet

To configure the Reports Admin portlet, navigate to the Reports Admin portlet in
the Control Panel, click on the *wrench* icon at the top right corner of the
portlet, and select *Configuration*. There are three tabs:

- *Email From*
- *Delivery Email*
- *Notifications Email*

The Email From tab allows you to customize the name and email address of the
account that sends report notifications within your portal. For example, you
could set the name to *Reports Admin* and the email address to
*reports@liferay.com*. The Delivery Email tab allows you to customize the email
message that's sent when a report is delivered to a portal user. When a report
is delivered to a portal user, it's included as an attachment to this email
message. Finally, the Notifications Email tab allows you to customize the
message that's sent when a report notification is sent to a portal user. In this
case, the report is not included as an attachment; the email message just
provides a link to the report's location in the portal.

## Using the Reports Display Portlet

Installing the Reports EE and JasperReports EE apps not only adds the Reports
Admin portlet to the Control Panel but also makes the Reports Display portlet
available. The Reports Display portlet provides the same functionality as the
Reports tab of the Reports Admin portlet. The Reports Display portlet allows
users to download or deliver reports but not to add, edit, or delete report
definitions or data sources.

![Figure 13.24: Here, the Reports Display portlet shows only a single report.](../../images/reports-display-portlet.png)

The Reports Display portlet is intended to be placed within a site's private
pages to allow site members to access site-scoped reports. It can also be placed
within a site's public pages. In this case, reports will only be displayed to
users that have permission to view the reports. For example, if a guest views a
site's public page to which the Reports Display portlet has been added,
site-scoped reports will not be visible; only reports for which the View
permission has been added to the Guest role will appear in the Reports Display
portlet.

## Creating a Sample Jasper Report in Liferay

To create a sample Jasper report in Liferay, follow this section's instructions.
We'll use your portal's database as our report definition's data source and
we'll use a simple Jasper template that was created using iReport Designer.

1. Save the following Jasper template to a file named `sample-report.jrxml`
   somewhere on your machine's file system.

        <?xml version="1.0" encoding="UTF-8"?>
        <jasperReport xmlns="http://jasperreports.sourceforge.net/jasperreports" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd" name="sample-report2" language="groovy" pageWidth="595" pageHeight="842" columnWidth="555" leftMargin="20" rightMargin="20" topMargin="20" bottomMargin="20">
                <property name="ireport.zoom" value="1.0"/>
                <property name="ireport.x" value="0"/>
                <property name="ireport.y" value="0"/>
                <queryString language="SQL">
                        <![CDATA[select * from User_]]>
                </queryString>
                <field name="userId" class="java.lang.Long">
                        <fieldDescription><![CDATA[]]></fieldDescription>
                </field>
                <field name="screenName" class="java.lang.String">
                        <fieldDescription><![CDATA[]]></fieldDescription>
                </field>
                <field name="emailAddress" class="java.lang.String">
                        <fieldDescription><![CDATA[]]></fieldDescription>
                </field>
                <field name="createDate" class="java.sql.Timestamp">
                        <fieldDescription><![CDATA[]]></fieldDescription>
                </field>
                <field name="modifiedDate" class="java.sql.Timestamp">
                        <fieldDescription><![CDATA[]]></fieldDescription>
                </field>
                <group name="userId">
                        <groupExpression><![CDATA[$F{userId}]]></groupExpression>
                </group>
                <group name="screenName">
                        <groupExpression><![CDATA[$F{screenName}]]></groupExpression>
                </group>
                <group name="emailAddress">
                        <groupExpression><![CDATA[$F{emailAddress}]]></groupExpression>
                </group>
                <group name="createDate">
                        <groupExpression><![CDATA[$F{createDate}]]></groupExpression>
                </group>
                <background>
                        <band splitType="Stretch"/>
                </background>
                <title>
                        <band height="79" splitType="Stretch">
                                <staticText>
                                        <reportElement x="0" y="0" width="555" height="51"/>
                                        <textElement>
                                                <font size="24" isBold="true"/>
                                        </textElement>
                                        <text><![CDATA[Sample Report: Users]]></text>
                                </staticText>
                        </band>
                </title>
                <pageHeader>
                        <band height="35" splitType="Stretch"/>
                </pageHeader>
                <columnHeader>
                        <band height="61" splitType="Stretch">
                                <staticText>
                                        <reportElement x="0" y="0" width="63" height="20"/>
                                        <textElement>
                                                <font size="14" isItalic="true"/>
                                        </textElement>
                                        <text><![CDATA[userId]]></text>
                                </staticText>
                                <staticText>
                                        <reportElement x="63" y="0" width="90" height="20"/>
                                        <textElement>
                                                <font size="14" isItalic="true"/>
                                        </textElement>
                                        <text><![CDATA[screenName]]></text>
                                </staticText>
                                <staticText>
                                        <reportElement x="153" y="0" width="128" height="20"/>
                                        <textElement>
                                                <font size="14" isItalic="true"/>
                                        </textElement>
                                        <text><![CDATA[emailAddress]]></text>
                                </staticText>
                                <staticText>
                                        <reportElement x="281" y="0" width="141" height="20"/>
                                        <textElement>
                                                <font size="14" isItalic="true"/>
                                        </textElement>
                                        <text><![CDATA[createDate]]></text>
                                </staticText>
                                <staticText>
                                        <reportElement x="422" y="0" width="133" height="20"/>
                                        <textElement>
                                                <font size="14" isItalic="true"/>
                                        </textElement>
                                        <text><![CDATA[modifiedDate]]></text>
                                </staticText>
                        </band>
                </columnHeader>
                <detail>
                        <band height="125" splitType="Stretch">
                                <textField>
                                        <reportElement x="0" y="0" width="63" height="20"/>
                                        <textElement/>
                                        <textFieldExpression><![CDATA[$F{userId}]]></textFieldExpression>
                                </textField>
                                <textField>
                                        <reportElement x="63" y="0" width="90" height="20"/>
                                        <textElement/>
                                        <textFieldExpression><![CDATA[$F{screenName}]]></textFieldExpression>
                                </textField>
                                <textField>
                                        <reportElement x="153" y="0" width="128" height="20"/>
                                        <textElement/>
                                        <textFieldExpression><![CDATA[$F{emailAddress}]]></textFieldExpression>
                                </textField>
                                <textField>
                                        <reportElement x="281" y="0" width="141" height="20"/>
                                        <textElement/>
                                        <textFieldExpression><![CDATA[$F{createDate}]]></textFieldExpression>
                                </textField>
                                <textField>
                                        <reportElement x="422" y="0" width="133" height="20"/>
                                        <textElement/>
                                        <textFieldExpression><![CDATA[$F{modifiedDate}]]></textFieldExpression>
                                </textField>
                        </band>
                </detail>
                <columnFooter>
                        <band height="45" splitType="Stretch"/>
                </columnFooter>
                <pageFooter>
                        <band height="54" splitType="Stretch"/>
                </pageFooter>
                <summary>
                        <band height="42" splitType="Stretch"/>
                </summary>
        </jasperReport>

2. Log in to your portal as an administrator, navigate to Site Administration,
   and navigate to the Reports Admin portlet.

3. Navigate to the Definitions tab and click *Add Report Definition*.

4. For the definition name, enter *Jasper Test*.

5. Leave the data source selection as the default: *Portal*.

6. Browse to and select the `sample-report.jrxml` template that you created in
   step 1, then click *Save* to create your report definition.

7. Click *Actions* &rarr; *Add Report* next to your Jasper Test report
   definition, choose the PDF report format, and click *Generate*.

8. Navigate to the Reports Admin portlet's Reports tab and click on the report
   you generated.

9. Click *Actions* &rrar; *Download* next to the `sample-report.pdf` file.

This report should list all of your portal's users, displaying the userId,
screenName, emailAddress, createDate, and modifiedDate of each user.

![Figure 13.25: This reports lists all portal users by userId, screenName, emailAddress, createDate, and modifiedDate.](../../images/sample-report-users.png)

Now that we understand how to generate Jasper reports in Liferay, let's see what
the Knowledge base application has to offer.
