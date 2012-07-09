# Kaleo Forms: Defining Business Processes [](id=lp-6-1-ugen11-kaleo-forms-defining-business-processes-head-ref-0)

In the last chapter, we looked at the elements that comprise a workflow definition and discussed how to create a workflow definition. In this chapter, we introduce the Kaleo Workflow Designer for Liferay EE which allows you to create workflow definitions using an intuitive UI. Using the workflow designer saves you the time and trouble of having to deal directly with the XML.

### Kaleo forms and workflow designer [](id=lp-6-1-ugen06-kaleo-forms-and-workflow-designer-0)

![EE Only Feature](../../images/ee-only-image/ee-feature-web.png)

Developers who are used to working with XML are generally able to create workflow definitions. Other users may not be so comfortable with it. In fact, even skilled developers can make mistakes that break a definition and require time to troubleshoot. To help streamline the creation of workflow definitions and empower more users to create custom workflows, Liferay provides the Kaleo Workflow Designer in Liferay 6.1 EE.

There are two pieces to the workflow designer: *Kaleo Forms* and *Kaleo Designer*. Kaleo Forms is an extension of the Dynamic Data Lists feature (covered in chapter 9). This enables you to create web forms and basic applications, then apply a workflow to govern the processing of those forms. Kaleo Designer is a drag and drop interface for creating new workflow definitions. It can be used in conjunction with Kaleo Forms or standalone to create workflow definitions without having to write XML.

Let's look at Kaleo Forms first. 

#### Kaleo Forms [](id=lp-6-1-ugen06-kaleo-forms-0)

Add the Workflow Forms portlet to a page. The initial state, the *Summary* tab displays the same information you might have seen in *My Workflow Tasks* in the control panel. Any forms available for processing through the workflow can be initiated through the *Submit New* button, as the below image indicates. 

![Figure 11.1: Kaleo Forms give you a convenient way to manage all available workflows in the portal. ](../../images/kaleo-forms-initial-view.png)

Click on *Processes* to view any existing workflow processes or to create new ones. All available processes are listed here. If you're coming here for the first time, however, there won't be any, so let's create one. Click *Add*. You'll see the screen below. 

![Figure 11.2: The Workflow Process Creation Page](../../images/kaleo-workflow-add-process.png)

Set a name and a description. Next, you'll define an *Entry Definition* and an *Initial Form*, choose or create a *workflow* and *Workflow Task Forms*.

**Entry Definition:** This is a Dynamic Data List Definition, you can use an existing definition here or create a custom one through the UI.

**Initial Form:** You can customize the display of the Entry Definition with the Inital Form. This can include things like adding Pagination or altering some other display feature.

**Workflow:** You can choose any existing workflow definition here or define a new one.

**Workflow Task Forms:** This is where you can define how the workflow definition interacts with form definitions. You can trigger a workflow action to occur along with a form action, such as a notification, or have a multistep process where part of the form is completed but needs to be approved before the user can complete another part of the form. 

You probably noticed when choosing a workflow, you also have the option to create a new one. This is where the Kaleo Workflow Designer can help you build a workflow without having to write any XML. Let's see how this works. 

#### Kaleo Designer [](id=lp-6-1-ugen06-kaleo-designer-0)

Kaleo Designer provides a drag and drop interface for users to create custom workflows. It's an incredibly powerful tool for managing workflow defintions. The Workflow Designer can only be accessed through the Kaleo Forms portlet but definitions created can be used for other processes as well.

![Figure 11.3: The Workflow Designer](../../images/kaleo-workflow-designer.png)

There are seven types of nodes you can add to a defintion. The node types are **Condition**, **End**, **Fork**, **Join**, **Start**, **State** and **Task**. If you've read the entire chapter, you'll notice Start and End aren't node types we've previously discussed; that's because they're actually just State nodes, with certain fields prefilled to help streamline the creation process. Since every workflow has a start and end state, you'd have to do this anyway. 

![Figure 11.4: The Node Configuration Menu](../../images/kaleo-designer-submenu.png)

Each node you add has a pop-up edit menu which lets you add anchors, attach tasks or delete the node. Anchors connect nodes so each connection needs an anchor. To transition to the next node, you'd add an anchor, create a new node, then click the first anchor and drag it to the anchor on the new node. This creates a transition.

To get a feel for how the designer works, let's use the workflow designer to duplicate the default workflow definition. When we choose the option to *Add Defintion* from the Kaleo Forms portlet, it creates a blank workflow defintion with start and end nodes. To make this work, we'll add two tasks, fill in the relevant information, assign the tasks properly and create the transitions.

First add two tasks, then use the edit icon to name them *Review* and *Update*. Next, connect the nodes so Review has four nodes, as follows: one receiving the transition from **StartNode**, one sending a transition to **Update**, one receiving a transition from **Update** and one sending a transition to **EndNode**.

<!-- | TODO: I fixed this screenshot, but not before I deleted this todo -->
![Figure 11.5: Your workflow should look something like this.](../../images/kaleo-designer-basic-workflow.png)

Next, we want to add the correct assignments and notifications. Click on *Review*. The box on the left shows all the proprerties of the Review node. In the *assignments* category, assign the task to the *Portal Content Reviewer* role. Click on *Notifications* and create a notification with the type *On Assignment*. Now move to the Update node and assign it to the *Content Creator* with its own notification.

<!-- | TODO: The below paragraph is confusing; please fix. -->
Next let's go through all of the transitions and make sure they're named correctly. What are the transitions? Every time you created an arrow from one node to another it created a transtion. By default, these transitions get system generated names so we'll rename them all to something more human readable. First click on the arrow going from the Start node to the Review node and set the name as *Submit* and set *Default* to true--we'll leave all the others as false. Set the name of the transition from Review to Update to *Reject* and the one from Update to Review to *Resubmit*. Lastly, set the name of the transition from Review to the EndNode to *Approve*.

Now let's take a look at the generated XML. It should look a lot like our default workflow, only a tiny bit messier, as the nodes display in the order they were created, not in the logical order that happens when a human writes the code. Save your definition and it's ready to use.

## Summary [](id=summ-28)

<!-- | TODO: This summary is too thin. It should summarize what you covered in the chapter. Going back and looking at the chapter headings can help you write a good summary. | --> 
As you can see, Liferay Portal and the Kaleo Workflow engine combine to create a very robust environment for web content management. Simple workflows can be managed using the default configuration and GUI tools, while more complex workflows can be created to meet the workflow management needs of almost any portal. Through this chapter and the previous one, we've taken a look at the various elements of a workflow and shown how to use those elements to create your own custom workflows. We've also seen how to properly use the various elements of a workflow like Assignments and Notifications, as well as newer and more advanced features like Parallel Workflows, Timers and Custom Scripts.

It's not enough to understand each individual step of the workflow process; one of the keys to using Kaleo workflow is to understand how each step interacts with the other elements. If you understand these relationships, you can figure out which features will to work best for your organization. We hope you'll use the information we've covered on workflow to craft suitable processes for you portal.
