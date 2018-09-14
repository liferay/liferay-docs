# Installing Kaleo Designer for Java [](id=installing-kaleo-designer-for-java-liferay-portal-6-2-dev-guide-07-en)

![EE Only Feature](../../images/ee-feature-web.png)

Kaleo Designer for Java lets you incorporate back-end Java development and
scripting in your workflows. Its graphical interface lets you drag and drop
nodes into your workflow. A shortcut on each node gives you easy access to the
node's XML, letting you edit its current implementation to make subtle
modifications or inject new business logic. In addition, Liferay Developer
Studio comes bundled with a Java/Groovy editor (made available by Spring
Source), giving you the same rich editing experience you're accustomed in
editing Kaleo [Groovy](http://groovy.codehaus.org/Documentation) scripts. From
the editor you can delegate workflow decisions to your custom business logic
APIs or access any of the Liferay Portal APIs. In Developer Studio, you can
leverage editors for Beanshell, Drl, JavaScript, Python, and Ruby scripting
languages. You can also leverage the editor for the [FreeMarker](http://freemarker.sourceforge.net/)
template language. Kaleo Designer for Java gives you a rich tool set for
creating/editing workflows, manipulating workflow nodes, and implementing
business logic. But there's more!

With Kaleo Designer for Java, you can remotely add and update workflow
definitions directly to and from your Liferay server. You can publish your
workflow drafts to your portal by simply dragging the workflow file onto your
portal server in Developer Studio's *Servers* tab. The *Servers* tab shows
workflows you've published from studio and gives you access to workflows already
published on the portal server. You can edit existing workflows and create
custom business logic in Developer Studio locally, then republish them on your
portal; you don't have to navigate back and forth from your portal and Developer
Studio to complete these tasks. As you can see, Kaleo Designer for Java is a
powerful application for creating, modifying, and publishing workflows in
Liferay Developer Studio. 

Although Kaleo Designer for Java is the tool of choice for EE workflow
designers, CE workflow designers can write Kaleo workflows too. But they are
limited to writing them in their favorite XML editor. All Kaleo process
definitions must follow the schema
[http://www.liferay.com/dtd/liferay-workflow-definition_6_2_0.xsd](http://www.liferay.com/dtd/liferay-workflow-definition_6_2_0.xsd).

The Kaleo Designer is automatically bundled with Liferay Developer Studio.
Additionally, the Kaleo Forms EE app now comes bundled with Liferay Enterprise
Edition. If you still need to install it from Liferay Marketplace, follow these
steps.

The *Kaleo Forms EE* app from Liferay Marketplace includes three
plugins--`kaleo-forms-portlet`, `kaleo-designer-portlet`, and `kaleo-web`
plugins. Here's how to download and install *Kaleo Forms EE*:

1.  Go to [Liferay Marketplace](https://www.liferay.com/marketplace).

2.  Click on [EE
    Marketplace](http://www.liferay.com/marketplace/-/mp/category/12729032) in
    the left navigation area.

3.  Download and install the *Kaleo Forms EE* app.

+$$$

**Note:** The *Kaleo Forms EE* app comes with an existing workflow designer
that's used *within* Liferay Portal. It's used to design workflow configuration
and is described in the [Kaleo Forms: Defining Business Processes](/discover/portal/-/knowledge_base/6-2/kaleo-forms-defining-business-processes)
chapter of *Discover* &rarr; *Portal*. Refer to the [Using Workflow](/discover/portal/-/knowledge_base/6-2/using-workflow)
chapter of *Discover* &rarr; *Portal* if you're unfamiliar with basic Kaleo workflow
concepts or want to know how to design your workflow within Liferay Portal. 

$$$

**Note:** After downloading and installing the Kaleo Forms EE application, you must
restart the Liferay Server. For Developer Studio to connect to the Kaleo APIs to
open workflow definitions in Portal 6.2, you must make sure to use the username
and password of a Portal Administrator in your server's settings. 

1. Stop your 6.2 server.

2. Open the server's configuration editor by double-clicking the server from
within the Servers view in Studio.

3. In the configuration editor under Liferay Settings add your portal admin
username and password. Save the configuration changes. 

4. Start the server.

A *Kaleo Workflows* folder automatically appears underneath the server instance
in the Servers view of Developer Studio. 

![Figure 1: In Studio's *Servers* view, your server's Kaleo Workflows folder shows workflows published on your portal.](../../images/kaleo-1.png)

Developer Studio retrieves all workflow definitions published on your portal
server. As mentioned previously, the Kaleo Designer for Java lets you remotely
add and update Kaleo workflow definitions directly to and from the portal
server. The *Single Approver* workflow comes already installed with the Kaleo
app. 

To open a workflow, double click it in the Kaleo Workflows folder. This
retrieves the workflow definition from the Liferay server so you can edit it in
Developer Studio. 

+$$$

**Note:** When you open a workflow, you'll be prompted to choose whether to
switch to the Kaleo designer perspective. Clicking *Yes* lets you use the
perspective's helpful features, including the palette toolbar, properties view,
and outline view. 

$$$

Once you have Kaleo Designer for Java installed, get into the flow (pun
intended) by [creating your own workflow](/develop/tutorials/-/knowledge_base/6-2/creating-a-workflow-definition-with-kaleo-designer-for-java) using the Kaleo Workflow Designer for
Java. 

<!-- ## Related Topics -->

