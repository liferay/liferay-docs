# Using Workflow Scripts [](id=using-workflow-scripts)

You can use Developer Studio to edit workflow scripts; it recognizes multiple
script languages, so you can choose one you're comfortable with. Developer
Studio provides you many script editing features so you can quickly implement
business logic in your workflows. 

Developer Studio supports several script languages: 

- Beanshell 
- Drl 
- Groovy 
- JavaScript 
- Python 
- Ruby 

Let's dive back into our `ticket-process` workflow definition and create a
script. It's not guaranteed that every ticket submitted has a resolution. If the
issue was due to a silly user error, there's no reason to change the product. In
such cases the developer will resolve the ticket and indicate there is no
resolution in the product (i.e., no modifications are were made). Regardless,
we'll have the developer fill out an online Dynamic Data List (DDL; see the tutorial on [Publsihing and Configuring Workflow](/develop/tutorials/-/knowledge_base/6-2/publishing-and-configuring-workflows)) form to
initiate a workflow for each of her tickets. Once the workflow is invoked, its
associated DDL record is accessible from our workflow's context. Let's use a
condition node to handle the ticket based on the DDL record. 

To set up the workflow process we described above, we'll need to add a
*Condition* node and two transitions. 

1.  Drag and drop a *Condition* node onto your workflow diagram. A *Create New
    Condition Node* menu should appear.

2.  Name the node *Resolution*.

3.  Choose a script language for the condition node. Select *Groovy* and you'll
    see how easy it is to embed Java code. In our Groovy script, we'll access
    the DDL record to determine whether the ticket warrants a modification to
    the product. If it does, we'll assign it to a developer via the Developer
    task node. Otherwise we'll end the workflow by transitioning to the
    workflow's EndNode. 

4.  From the *Create New Condition Node* menu, add two transitions--one to the
    *Developer* node and the other to the *EndNode* state. We'll add the
    transition to the *Developer* node first. 

    Click the green plus sign and select *Existing Node* from the menu. An
    entry for the transition appears in the named list of *Condition
    transitions*. 

    Click the browse icon in the entry and select the *Developer* node.

5.  Add a transition to the *EndNode* state in the same manner that added the
    transition to the *Developer* node in the previous step.

6.  Click *Finish*. 

<!--When I select the green plus sign I get a list to choose from; Existing
Node, New Condition, New Fork, New Join, New State, New Task. I do not see a
transition icon to select. -->

Here's a snapshot of the *Create New Condition Node* menu configured for the
ticket process workflow. 

![Figure 1: When creating a condition node, you can set your preferred script language, name, and condition transitions.](../../images/kaleo-12.png)

Before adding a script to our condition node, let's make some changes to our
workflow transitions: 

- Add a transition from the *Developer* task node to the *Pass To QA* fork node.
- Add a transition from the *StartNode* state node to the *Resolution* condition
  node.
- Delete the transition that currently connects the *StartNode* state node to
  the *Developer* task node.
- Delete the transition that currently connects the *Developer* task node to the 
  *EndNode*.

To add a transition from one node to another, do the following:

1.  Click the transition icon from the palette. Your pointer's icon shows as a
    plug indicating you are in *connector* mode.

2.  Select a node on your workflow diagram from which the transition will start.
    A dotted line appears with one end connected to the selected node and the
    other end following your pointer.

3.  Select a node to which the transition will end. The dotted line changes into
    a fixed ray with the arrow pointing to the transition's end node.

4.  To exit connector mode, hit *Escape* on your keyboard and click your pointer
    at empty space in your workflow diagram.

You may notice the error marking on the condition node. When you hover over
the marking, a hint indicates a script must be specified for the node.

Open the script editor for your *Resolution* condition node by doing one of the
following: 

- Select the node and click *Edit Script* from the *Script* tab of the
  Properties view. 
- Click the *Edit Script* tool from the node's floating palette. 
- Right-click the node and select *Edit Script*. 

We set our default script language to Groovy, so the Java/Groovy editor appears.
To learn more about the Groovy editor, see the [Groovy User Guide](http://groovy.codehaus.org/User+Guide). 
If you set the script language to another language, the editor for that specific 
language appears. The editor runs in the context of editing the specific node 
you selected. Anything you type in the script editor for this condition node is 
written inside the `<script></script>` tags for the `<condition/>` element that 
represents our node in our workflow definition's XML file (in our case,
`ticket-process-definition.xml`). 

+$$$

**Note:** Developer Studio lets you use multiple script editors even while
modifying the same workflow definition XML file. 

$$$

The *Palette* view is much different from when you were working in the workflow
diagram; it's associated with your Java/Groovy script editor now and includes
folders containing the following entities for your script: 

- Context Variables 
- Dynamic Data Lists 
- Roles 
- Scripts 
- Status Updates 

You can expand and collapse a folder by clicking its name bar. 

Here's a snapshot of the palette with the *Context Variables* folder open:

![Figure 2: Each script editor is associated with a palette that contains helpful snippets of code you can insert.](../../images/kaleo-14.png)

Drag and drop an entity from your palette onto your Java/Groovy editor and code
representing that entity appears in the editor. The inserted code is free of
compile errors and warnings because the editor is running in the context of
Liferay Portal. All of the Liferay Portal APIs are available to you. In the
editor you can invoke code-assist and access built in Kaleo workflow variables. 

Let's get the DDL record that's being worked on in our workflow process. We'll
need the `serviceContext` entity, under `Context Variables` in the palette. To
learn more about Service Context and its parameters, see Chapter 6.

Let's use Designer's palette features in conjunction with our Java/Groovy editor
to implement our condition: 

1.  Drag and drop the *serviceContext* entity from the *Context Variables*
    folder in your palette onto the script editor. This grabs the Service
    Context. 

2.  Drag and drop the *ddlRecord* entity from the *Dynamic Data Lists* folder in
    your palette onto the script editor. We get the `ddlRecordId` from the
    Service Context and use that ID to look up the DDL record via Liferay
    service utility `DDLRecordLocalServiceUtil`.

Append the following Java code to the `DDLRecordLocalServiceUtil` script: 

	Field field = ddlRecord.getField("status");

	String status = GetterUtil.getString(field.getValue());
	if (status.contains("not")) {
		returnValue = "No"
	}
	else {
		returnValue = "Yes"
	}


We're pulling out the status from the DDL record and returning a value
indicating "Yes" to continue fixing the ticket issue or "No" to transition to
the workflow's end state. 

Add the following to the script's imports to finish things up: 

	import com.liferay.portlet.dynamicdatamapping.storage.Field;

Now the script accurately implements the condition logic we want. As a
reminder, all of the code was injected into our workflow's XML file within the
`<condition/>` element that represents our condition node. Here's what this
block of XML looks like, including the Java in our Groovy script: 

    <condition>
        <name>Resolution</name>
        <script><![CDATA[import com.liferay.portal.kernel.util.GetterUtil;
            import com.liferay.portal.kernel.workflow.WorkflowConstants;
            import com.liferay.portal.service.ServiceContext;
            import com.liferay.portlet.dynamicdatamapping.storage.Field;
            import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
            import com.liferay.portlet.dynamicdatalists.service.DDLRecordLocalServiceUtil;

            long companyId = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID));
            ServiceContext serviceContext = (ServiceContext) workflowContext.get(WorkflowConstants.CONTEXT_SERVICE_CONTEXT);

            long classPK = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
            DDLRecord ddlRecord = DDLRecordLocalServiceUtil.getRecord(classPK);

            Field field = ddlRecord.getField("status");

            String status = GetterUtil.getString(field.getValue());
            if (status.contains("not")) {
              returnValue = "No"
            }
            else {
              returnValue = "Yes"
            }]]></script>
        <script-language>groovy</script-language>
        <transitions>
            <transition>
                <name>Yes</name>
                <target>Developer</target>
            </transition>
            <transition>
                <name>No</name>
                <target>EndNode</target>
            </transition>
        </transitions>
    </condition>

+$$$

 **Note:** Make sure you correctly name
 the transitions stemming from the condition node. The "No" transition should
 point to the EndNode, while the "Yes" transition should point to the Developer.
 If the condition script's return values don't match the transition names, the
 workflow engine won't know which transition to use. 

$$$

Here's a snapshot of our current ticket process workflow after inserting the
condition node. If your transition names don't match the ones in this 
screenshot, you can change them by simply double clicking the transition names 
and editing them. 

![Figure 3: The ticket process workflow after inserting the condition node.](../../images/kaleo-30.png)

We need to create a valid DDL record to invoke this workflow properly. If you've
never set up a DDL record before, or don't even know what a DDL does, don't
fret. We've got you covered in the tutorial on [Publishing and Configuring Workflows](/develop/tutorials/-/knowledge_base/6-2/publishing-and-configuring-workflows). 

Next, you might want to look at the tutorial on [leveraging template editors for notifications](/develop/tutorials/-/knowledge_base/6-2/leveraging-template-editors-for-notifications). 

<!-- ## Related Topics -->
