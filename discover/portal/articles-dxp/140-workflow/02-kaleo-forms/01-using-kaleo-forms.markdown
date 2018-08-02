# Creating Kaleo Forms Process [](id=creating-kaleo-forms-process)

To start creating a Kaleo Forms Process you need to get to Kaleo Forms Admin: 

1.  Go to *Site Administration* &rarr; *Content*.

2.  Select *Kaleo Forms Admin*.

    Kaleo Forms appears with a listing of the defined processes. If you're 
    coming here for the first time, there won't be any, so create one. 

3.  Click the *Add* (![Add](../../../images-dxp/icon-add.png)) button to open 
    the New Process Wizard and begin the first step in creating a Kaleo 
    Process. 

## Creating the Process

In the first step, enter the basic information about the process:

1.  Enter *Spa Order Process* for the *Name* and add a *Description*.

2.  Click *Next*.

![Figure 1: Add a Kaleo Forms Process to link a form with a workflow definition.](../../../images-dxp/kaleo-forms-add.png)

## Creating the Form [](id=creating-the-form)

In the second step of the New Process Wizard, define the fields that can appear
in your process's forms. There are two methods:

1.  Choose a predefined field set. To use a field set that already exists, pick
    one from the list of field sets by clicking the Actions menu
    (![Actions](../../../images-dxp/icon-actions.png)) next to its entry and
    selecting *Choose*. 

2.  Create a new field set/data definition. Use the *Add Field Set* button to
    add a new field set. If you don't understand what this means, see the
    documentation on
    [creating data definitions](/discover/portal/-/knowledge_base/7-1/creating-data-definitions)
    first.
    - For this example, Add a new field set: 
        - A Text field called *Customer Name* 
        - A Text Box called *Requested Spa Technician*
        - A Select field called *Services Requested* with these options: 
            - Massage
            - Fish Pedicure
            - Sauna
            - Acupuncture
            - Moon Mud Facial Mask
        - A Boolean field called *Returning Customer?*
        - A Date field called *Preferred Date*
        - A Text field called *Preferred Time*
        - A Date field called *Available Date*
        - A Text field called *Available Time*
        - A Boolean field called *Approved*
        - A Text Box field called *Managerial Comments*

Click *Next* to move to the next step in the wizard: adding a workflow for
the process.

![Figure 2: In the second step of the New Process Wizard, define and choose the fields for your form.](../../../images-dxp/kaleo-forms-fields.png)

## Selecting a Workflow [](id=choosing-a-workflow)

In the third step of the New Process Wizard you select a workflow to use for
your forms. The Single Approver workflow is included by default. See how it's
defined by opening it in the Kaleo Designer.

1.  Click the actions menu (![Actions](../../../images-dxp/icon-actions.png))

2.  Select *Edit*. 

The graphical interface for editing or defining a workflow appears below the
Single Approver edit screen's Details section. 

If you don't want to base your workflow on the Single Approver definition (or
any definition already available in your system), add a new workflow from
scratch using the Kaleo Designer. Click the *Add Workflow* button to get
started.

- For the Spa Order Process, make a new workflow definition called *Spa Order
    Workflow*:

![Figure 3: The Spa Order Process has three tasks that happen sequentially.](../../../images-dxp/kaleo-forms-spa-order-definition.png)

The definition has a StartNode and three task nodes. Develop it in the graphical
designer built into Kaleo Forms. There's a more detailed write-up of the
Kaleo Designer [here](/discover/portal/-/knowledge_base/7-1/kaleo-designer) if
you're not already familiar with it. 

This workflow exhibits simple linear processing, so that the initial form is
filled out (in the StartNode) and then the workflow moves to the first task
(Technician Claim), then to the second task (Managerial Approval), and finally,
to the third task (Final Order).

The task assignments of this workflow are as follows:

- Technician Claim: Assigned to the Spa Technician Role.
- Managerial Approval: Assigned to the Spa Manager Role.
- Final Order: Assigned to the Asset Creator.

+$$$

**Note:** Create the Spa Technician Role and the Spa Manager Role as 
[Site Roles](/discover/portal/-/knowledge_base/7-1/roles-and-permissions), 
and give them
[permission](/discover/portal/-/knowledge_base/7-1/defining-role-permissions)
to access the Kaleo Forms application. In the role's page for defining
permissions, they're under Site Administration &rarr; Content &rarr; Kaleo Forms
Admin. Give both roles all of the permissions available for Kaleo Forms Admin.

$$$

Once the workflow is done, select it:

1.  Click *Actions* &rarr; *Choose* on the workflow you just created.

2.  Click *Next*.

Now that you have the workflow definition completed assigned, you must create
and assign forms for each workflow task. 

