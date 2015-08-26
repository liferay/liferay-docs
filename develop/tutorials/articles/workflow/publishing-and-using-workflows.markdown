# Publishing and Configuring Workflows [](id=publishing-and-configuring-workflows)

Once a workflow is developed, it must be published to Liferay. After that,
there's even more workflow configuration possbility, such as associating the
workflow with a Dynamic Data List, and using Kaleo Forms to run the workflow.

## Publishing Workflows to the Server [](id=publishing-workflows-to-the-server)

After you create a new workflow or modify an existing one, you'll have to
publish it onto your Liferay server before your site's members can use it. Let's
publish the `ticket process` workflow definition onto your Liferay server. 

To publish your `ticket process` workflow definition: 

1.  Right-click the *Kaleo Workflows* folder listed under your Liferay server in
    the *Servers* view. 

2.  Select *Upload new workflow...* to bring up the *workspace files* browser. 

3.  Browse for your workflow definition file and select it for publishing. 

Alternatively, you can publish your new workflow XML file by dragging it from
your *Package Explorer* view onto your Liferay server in your *Servers* view. 

If you are not using the Kaleo Designer for Java plugin and you'd like to
publish workflow definitions you've written in XML, you can do so by uploading
them from within Liferay Portal's workflow configuration screens. For details,
see the chapter on [Using Workflow](/discover/portal/-/knowledge_base/6-2/using-workflow) 
in *Discover* &rarr; *Portal*.

+$$$

**Note:** To update your Kaleo Workflows
folder with the latest workflow versions created or modified in Liferay Portal
using Kaleo Workflow Designer from the *Kaleo Forms* portlet, right click *Kaleo
Workflows* under your server and select *Refresh*. 

$$$

You probably understand why it's necessary to publish new workflow definitions
onto the Liferay server; it might be less clear why you need to republish
existing workflow definitions that you've modified. When you save changes to a
workflow, they're not immediately available in your portal; it's still using the
previous version of the workflow. Developer Studio saves the workflow as a
draft, so you can work on multiple iterations of the same version until you're
ready to publish your changes. Once you publish, you now have a new version to
make changes on top of. For example, you might be working on *Version 1* of your
workflow definition; as you make changes, you save them in multiple drafts. When
you are finished with all of your changes, you publish the workflow triggering
creation of a new version (*Version 2*) of the workflow. The new version is made
available on the server immediately for your workflow administrators to
associate with asset publications, DDLs, and with Kaleo Forms. 

Unlike other Java editors, Developer Studio lets you test your workflow
definition as a draft. You can also publish your workflow definition straight to
Liferay Portal for quick and easy configuration. 

Are you ready for our *Kaleo Designer for Java* finale? You just have to
activate the workflow in your Liferay Portal, then we'll set up the DDL record
and try out our new workflow. Let's activate! 

## Using Workflows in Liferay Portal [](id=using-workflows-in-liferay-portal)

Let's put some finishing touches on your workflow and test drive it in Liferay
Portal. Before you can use a workflow definition, it must be activated in your
Liferay Portal. Navigate to the *Control Panel* and, under the *Configuration*
heading, select *Workflow*. Then, in the *Definitions* tab, click on the
*Actions* button and select *Activate*.

A workflow definition can be associated with publication of an asset or DDL
record. Let's associate our ticket process workflow definition with a DDL record
that lets a developer indicate whether she'll fix a ticket's issue. You can find
detailed instructions for creating a DDL by visiting the section [Defining data types](discover/portal/-/knowledge_base/6-2/building-a-list-platform-in-liferay-and-defining-data-)
in *Discover* &rarr; *Portal*. We'll demonstrate how easy it is.

## Using Dynamic Data Lists with Workflows [](id=using-dynamic-data-lists-with-workflows)

Let's associate our workflow with a Dynamic Data List (DDL) record. To learn
more about DDLs, visit [Using Web Forms and Dynamic Data Lists](/discover/portal/-/knowledge_base/6-2/using-web-forms-and-dynamic-data-lists)
in *Discover* &rarr; *Portal*.

First we'll create a data definition that lets the user select a status value. 

1. In Liferay Portal, go to *Site Administration* &rarr; *Content* &rarr;
   *Dynamic Data Lists*.

2. Click the *Manage Data Definitions* link, then *Add* a new data definition.

3. Name the data definition *Status*. Then, in the *Fields* tab, drag and drop
   the *Select* field onto the canvas.

    ![Figure 1: Creating data definitions for your DDL is a snap with Liferay's graphical drag and drop interface.](../../images/kaleo-35.png)

4. In the *Settings* tab, double click the *Name* property to open the property
   editor--enter *status*, in lowercase, as the value. Then click *Save*.

5. Edit the *Options* setting; give your *status* field option values of `fix`
   with label "Fix" and `not` with label "Do not fix". 

6. Click *Save*. 

Recall the code we inserted for our condition node:

    Field field = ddlRecord.getField("status");

    String status = GetterUtil.getString(field.getValue());
    if (status.contains("not")) {
        returnValue = "No"
    }
    else {
        returnValue = "Yes"
    }

In our code, the `getField()` method ingests the value of our DDL field named
"status". When the script is invoked, if the value for the status field is
`not`, the value *No* is returned and our workflow transitions to our EndNode
state. Otherwise, the workflow transitions to our Developer task node. 

After you create the data definition, create a DDL. Make sure you select the 
ticket process workflow and the Status data definition when creating this DDL. 
*Save* the DDL. 

Now our DDL is set for use inside our ticket process workflow! Let's use the
Kaleo Forms portlet to test our new workflow definition! 

## Using Kaleo Forms to Run Workflows [](id=using-kaleo-forms-to-run-workflows)

Let's use the Kaleo Forms portlet to invoke our workflow from Liferay Portal.
Deploy the Kaleo Forms portlet to your portal and add it to a page on your site.
You can learn how to use Kaleo Forms in the [Kaleo Forms](/discover/portal/-/knowledge_base/6-2/kaleo-forms-defining-business-processes)
section of *Discover* &rarr; *Portal*; we'll demonstrate its use here by using it
with our ticket process workflow.

1.  Create a new *Process* in Kaleo Forms; name it *Ticket Process*. 

2.  Select the *status* entry definition we created earlier. 

3.  Add an *Initial Form* based on our *status* data definition. 

4.  Select our ticket process workflow. 

5.  Leave *Workflow Task Forms* unassigned for our demonstration. 

![Figure 2: To test the ticket process workflow, create a new Kaleo Forms *Process*--provide a name, entry definition, and workflow.](../../images/kaleo-41.png)

After saving, select the *Summary* tab in Kaleo Forms, click the *Submit New*
button, and select *Ticket Process*.

Now you can interact with the DDL and progress throughout the ticket process
using Kaleo Forms! Joe Bloggs is assigned the task once the ticket reaches the 
QA and Pass to QA tasks. Remember to sign in as Joe Bloggs to access the tasks
assigned to him. Mr. Bloggs should also receive an email when the Project
Management node is activated. 

You successfully created a workflow definition and created a workflow process
within Liferay Portal! You're officially a workflow master (you can get your
framed certification at the front desk on your way out--tell them we sent you)! 

Have you noticed that there's a lot of depth to Liferay IDE? You can easily come
across difficult questions and run into very specific problems, but someone else
might have already solved your issue or answered your question. So where would
you go to find out? Don't reinvent the wheel, visit the [Liferay IDE Community
page](http://www.liferay.com/community/liferay-projects/liferay-ide/overview)!
On the *Forums* page, you can look up resolutions to specific errors and ask
questions. Be sure to fully describe any problems you have to ensure you get a
working answer. You can even track known issues from the *Issue Tracker* page. 

<!-- ## Related Topics -->
