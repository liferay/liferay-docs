# Workflow [](id=workflow)

Applications accepting user created content usually need to support an approval
process so that low quality, objectionable, or otherwise undesirable data is
not inadvertently published. 

Liferay Portal includes a workflow engine called *Kaleo*. Kaleo allows portal
administrators to set up workflows for their organization's needs; the workflow
calls users to participate in processes designed for them. Kaleo workflows,
called *process definitions*, are essentially XML documents. Kaleo supports a
host of XML element types to trigger decisive actions in your business process
instances. You can fine-tune your process definition's logic by incorporating
scripts and templates. 

The *Kaleo Forms EE* app from Marketplace includes Liferay's *Kaleo Workflow
Designer* that lets you create and modify portal workflows in your browser. With
Kaleo Designer for Java, you can design and publish Kaleo workflows from Liferay
Developer Studio! 

![Figure 1: Kaleo Designer for Java gives you a powerful environment for designing, modifying, and publishing Kaleo workflows.](../../images/kaleo-designer-for-java.png)

Additionally, if you have a Liferay Portal Enterprise Subscription, you have
access to the Kaleo Designer directly from Liferay Portal (*Control Panel* &rarr;
*Configuration* &rarr; *Kaleo Designer*).

Kaleo Designer for Java is a workflow development tool that can be accessed in
[Liferay Developer Studio](/develop/tutorials/-/knowledge_base/6-2/developing-applications-with-liferay-developer-stu),
providing workflow developers with a robust set of functionality:

- A graphical workflow editor to visualize the workflow as it's developed, and
  the ability to toggle between the source XML and the graphical editor.

- Drag and drop capability for adding workflow nodes, with easy access to the
  source XML for each node.

- A Freemarker template editor for creating detailed workflow notifications.

- A Java/Groovy editor for scripting that lets you delegate workflow decisions
  to your custom business logic APIs, or access Liferay's APIs.

Although Kaleo Designer for Java is the tool of choice for EE workflow
designers, CE workflow designers can write Kaleo workflows too. But they are
limited to writing them in their favorite XML editor. All Kaleo process
definitions must follow the schema
[http://www.liferay.com/dtd/liferay-workflow-definition_6_2_0.xsd](http://www.liferay.com/dtd/liferay-workflow-definition_6_2_0.xsd).

We'll cover the following workflow topics:

- [Enabling your application's entities for workflow](/develop/tutorials/-/knowledge_base/6-2/workflow-enabling-entities)
- [Installing Kaleo Designer for Java](/develop/tutorials/-/knowledge_base/6-2/installing-kaleo-designer-for-java-liferay-portal-6-2-dev-guide-07-en)
- [Creating workflow projects in Kaleo Designer for Java](creating-a-workflow-project-with-kaleo)
- [Designing and creating a workflow](/develop/tutorials/-/knowledge_base/6-2/designing-a-kaleo-workflow-definition)
- [Using workflow scripts](/develop/tutorials/-/knowledge_base/6-2/using-workflow-scripts)
- [Leveraging template editors for notifications](/develop/tutorials/-/knowledge_base/6-2/leveraging-template-editors-for-notifications)
- [Publishing and configuring workflows in the portal, including the use of Dynamic Data Lists and Kaleo Forms](/develop/tutorials/-/knowledge_base/6-2/publishing-and-configuring-workflows)


