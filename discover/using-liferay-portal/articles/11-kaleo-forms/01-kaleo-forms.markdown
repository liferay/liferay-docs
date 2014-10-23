# Kaleo Forms [](id=kaleo-forms)

Add the Kaleo Forms portlet to a page. In the portlet's initial state, the
*Summary* tab displays similar information to what you might see in the *My
Workflow Tasks* portion of the Control Panel. Because the Kaleo Forms portlet is
scoped to Kaleo Processes, tasks related to other assets cannot be managed from
Kaleo Forms. Any forms available for processing through the workflow can be
initiated through the *Submit New* button, as the below image indicates.

![Figure 11.1: Kaleo Forms give you a convenient way to manage all available workflows in the portal. ](../../images/kaleo-forms-initial-view.png)

Click on *Processes* to view any existing workflow processes or to create new
ones. All available processes are listed here. If you're coming here for the
first time, however, there won't be any, so let's create one. Click *Add*.
You'll see the screen below. 

![Figure 11.2: The New Process page gives you several options when creating a new workflow.](../../images/kaleo-workflow-add-process.png)

Set a name and a description. Next, you'll define an *Entry Definition* and an
*Initial Form*, choose or create a *workflow* and *Workflow Task Forms*.

**Entry Definition:** This is a Dynamic Data List Definition, you can use an
existing definition here or create a custom one through the UI.

**Initial Form:** You can customize the display of the Entry Definition with the
Initial Form. This can include things like adding Pagination or altering some
other display feature.

**Workflow:** You can choose any existing workflow definition here or define a
new one.

**Workflow Task Forms:** This is where you can define how the workflow
definition interacts with form definitions. You can trigger a workflow action to
occur along with a form action, such as a notification, or have a multi-step
process where part of the form is completed but needs to be approved before the
user can complete another part of the form. 

You probably noticed when choosing a workflow, you also have the option to
create a new one. This is where the Kaleo Workflow Designer can help you build a
workflow without having to write any XML. Let's see how this works. 
