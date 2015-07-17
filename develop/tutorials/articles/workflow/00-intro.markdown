# Workflow

*[This is an introductory story, feel free to skip the italics to get to the
details]*

*Rupert fit in well with the rest of ThisCorp's employees. He dressed in
business casual attire (except on Blue Jeans day, which happened once per
quarter) arrived at work on time, and liked to accompany his fellow employees
to the tavern for drinks on Friday at 5:30 p.m. Rupert reeeeaaaaallllyyy liked
this Friday tradition. One slow week in January, Rupert rounded up a group of
fellow-minded colleagues to join him for drinks on Thursday. He just couldn't
wait until Friday this week. Unfortunately, Rupert's enthusiasm led him to
drink far too many libations, and his discretion was still compromised when he
arrived at work on Friday (on time, wearing fresh khakis and a brand new polo
shirt). He went about his  usual Friday tasks (Rupert wrote a blog post on
ThisCorp's blog page each Friday) in a bit of a stupor. After lunch time he
felt quite recovered and was surprised when his supervisor, Rufelia Sacker,
stormed into his office.*

*"Rupert, we need to talk", Rufelia said in a calm voice that belied her
anger.*

*"Sure, Rufelia. Did you see my blog post on our plans to merge with
OtherCorp?" Probed Rupert.*

*"Yes, Rupert. That's why I'm here."*

*At this point Rupert was very concerned. Because of his morning stupor, he
couldn't remember the details of his blog post, so he simply said, "Okay".*

*Rufelia handed him a printed version of the thiscorp.com Blog page, with his
blog post featured at the top. "I printed us both a copy of your post so we can
look at it together. I printed our ThisCorp Guide to Official Web
Communications memo as well. Did you ever get that memo, Rupert?"*

*"Yeah, I have the memo right here", Rupert replied, as he gently patted a
stack of papers on his desk.*

*Rufelia ignored him and slid the familiar pink folder onto his desk. "Let's
look at your post now."*

*Rupert took the printout and read it in horror. Rufelia was saying something,
but all Rupert could think about now was the state of his resume.*

![Figure 1: ThisCorp should have enabled workflow for Blog posts.](../../images/thiscorp-blog.png)

Workflow is important. Applications accepting user created content usually need
to support an approval process so that low quality, objectionable, or otherwise
undesirable data is not inadvertently published. 

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

![Figure 2: Kaleo Designer for Java gives you a powerful environment for designing, modifying, and publishing Kaleo workflows.](../../images/kaleo-designer-for-java.png)

Additionally, if you have a Liferay Portal Enterprise Subscription, you have
access to the Kaleo Designer directly from Liferay Portal (*Control Panel* &rarr;
*Configuration* &rarr; *Kaleo Designer*).

Kaleo Designer for Java is a workflow development tool that can be accessed in
[Liferay Developer
Studio](https://dev.liferay.com/develop/learning-paths/mvc/-/knowledge_base/6-2/developing-applications-with-liferay-developer-stu),
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
- [Installing Kaleo Designer for Java](/develop/tutorials/-/knowledge_base/6-2/installing-kaleo-forms-ee)
- [Creating workflow projects in Kaleo Designer for Java](creating-a-workflow-project-with-kaleo)
- [Designing and creating a workflow](/develop/tutorials/-/knowledge_base/6-2/designing-workflow-definitions)
- [Using workflow scripts](/develop/tutorials/-/knowledge_base/6-2/using-workflow-scripts)
- [Leveraging template editors for notifications](/develop/tutorials/-/knowledge_base/6-2/notification-templates)
- [Publishing and using workflows in the portal, including the use of Dynamic Data Lists and Kaleo Forms](/develop/tutorials/-/knowledge_base/6-2/publishing-and-using-workflows)


