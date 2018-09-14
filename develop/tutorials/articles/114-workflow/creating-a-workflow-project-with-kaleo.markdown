# Creating a Workflow Definition with Kaleo Designer for Java [](id=creating-a-workflow-definition-with-kaleo-designer-for-java)

Developer Studio makes it easy for you to write custom business logic that
enhances Kaleo workflows. Learn to create your own workflow in Developer Studio
and discover features from Kaleo Designer for Java (Designer) along the way.

To begin demonstrating Designer's features, this tutorial creates a workflow
definition for a software ticketing process: view it in full
[here](https://www.github.com/liferay/liferay-docs/blob/6.2.x/develop/tutorials/code/workflow-defs/ticket-process.xml).
Are you ready to roll? Let's get started! 


![Figure 1: Check out the adventures of Liferay's mascots, Ray and Alloy at http://www.liferay.com/ray](../../images/kaleo-15.png)

1.  Create new workflow definitions by going to *File* &rarr; *New* &rarr;
    *Liferay Kaleo Workflow*. Alternatively, you can select *Liferay Kaleo
    Workflow* from the toolbar button shown in the figure below. 

    ![Figure 2: Create a new workflow definition locally by selecting *Liferay Kaleo Workflow* from the toolbar button.](../../images/kaleo-3.png)

2.  The *Create New Kaleo Workflow File* wizard will guide you through the steps
    necessary to complete the initial setup of your new workflow definition. 

    The first window you'll see is the *Create Kaleo Workflow* form. There are
    several fields here:  

    *Project*: Specify an existing Liferay project to house your workflow
    definition.  

    *Folder*: Specify where in the project the workflow definition XML file will
    be stored.

    *Name*: Give your workflow definition a descriptive name. We've chosen
    *Ticket Process* for our example. 

    *Initial state name* and *Final state name*: Name your workflow's initial
    and final state nodes.

    *Default script type*: Choose a default script type; Designer will bring
    its editor up when you're done creating the workflow.

    *Default template type*: Choose a default template editor; Designer will
    open it when you're done creating the workflow. 

    The snapshot below displays the setup menu for our
    ticket process workflow definition.

    When you're finished in this window click *Next*. 
    
    ![Figure 3: The Create Kaleo Workflow form in Liferay Developer Studio lets you select a script type and template type for your workflow.](../../images/kaleo-new-workflow-01.png)

    You're directed to the *Choose Assignment Type* window next.
    Provide an *Initial task name*, then choose an assignment type from the list
    of options.  
    
    To follow our workflow example, select *Assign to asset creator* and name
    the task `Developer`. When the workflow's `Developer` task is invoked, the
    creator of the workflow's asset is assigned to it. In Liferay Portal, each Kaleo
    workflow is associated with an asset type. Ina later tutorial, the workflow will
    be associated with a Dynamic Data List (DDL). 

    ![Figure 4: When choosing an assignment type for a task node, you are given multiple options.](../../images/kaleo-4.png)

    +$$$

    **Note:** You must specify a Liferay project as a home for the workflow
    definitions you create. 

    $$$

3.  Click *Finish* to complete the initial workflow definition setup. 

<!--This type of instruction, in step 2, with heirarchical steps actually looks
bad in my Markdown plugin for Jedit; very crammed -->

The graphical workflow diagram appears, which is convenient for editing the
workflow. Graphical features and toolbars allow you to customize your workflow
definition. The *Palette* view is one of Designer's most commonly used tools.

## Palette and Floating Palette [](id=palette-and-floating-palette)

The *Palette* lets you graphically customize your workflow with nodes and
transitions. In addition, you can choose different behaviors for your mouse
pointer. 

![Figure 5: The palette toolbar lets you customize your workflow with additional nodes and transitions.](../../images/kaleo-6.png)

Here are your pointer options: 

- *Select*: The default pointer setting used for selecting options on the
  workflow diagram by clicking the icon. 
- *Marquee*: Used for drawing an invisible selection box around multiple icons.
  This is useful when you want to manipulate multiple nodes and/or transitions
  on the workflow diagram. 

Once you've created a project, you'll want to start really fleshing it out.
Learn to [design and create workflows in the next tutorial](/develop/tutorials/-/knowledge_base/6-2/designing-a-kaleo-workflow-definition).

<!-- ## Related Topics -->
