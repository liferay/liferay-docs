# Creating Kaleo Forms Process [](id=creating-kaleo-forms-process)

To start creating a Kaleo Forms Process you need to get to Kaleo Forms Admin: 

1.  Go to *Site Administration* (your site's menu) &rarr; *Content* &rarr; 
    *Kaleo Forms Admin*. The Kaleo Forms app appears with a list of any defined 
    processes. 

2.  Click the *Add* button 
    (![Add](../../../images-dxp/icon-add.png)) 
    to open the New Process Wizard. 

3.  Give the process a name and a description, then click *Next*. 

    ![Figure 1: Add a Kaleo Forms Process to link a form with a workflow definition.](../../../images-dxp/kaleo-forms-add.png)

4.  Define the fields that can appear in your process's forms. There are two 
    ways to do this: 

    -   Use an existing field set. To do this, click the field set's Actions 
        button 
        (![Actions](../../../images-dxp/icon-actions.png)) 
        and select *Choose*.

    -   Create a new field set/data definition. To do this, click the 
        *Add Field Set* button. If you need help with this, see the 
        documentation on
        [creating data definitions](/discover/portal/-/knowledge_base/7-1/creating-data-definitions). 

    Click *Next* to move to the wizard's next step. 

    ![Figure 2: Define and choose your form's fields.](../../../images-dxp/kaleo-forms-fields.png)

5.  Select a workflow to use for your forms. To do this, click the workflow's 
    *Actions* button 
    (![Actions](../../../images-dxp/icon-actions.png)) 
    and select *Choose*. 

    You can also edit an existing workflow or create a new one: 

    -   To edit a workflow, click its *Actions* button 
        (![Actions](../../../images-dxp/icon-actions.png)) 
        and select *Edit*. 

    -   To begin creating a new workflow, click the *Add Workflow* button. 

    In either case, you use the same UI to edit/create the workflow. This UI is 
    called 
    [Kaleo Designer](/discover/portal/-/knowledge_base/7-1/kaleo-designer). It 
    lets you create your workflow graphically instead of via code. 

    Once you select a workflow to use with your forms, click *Next*. 

    ![Figure 3: This example workflow has three tasks that happen sequentially.](../../../images-dxp/kaleo-forms-spa-order-definition.png)

6.  Create a form to use for each workflow task. To do this, click each task's 
    *Actions* button 
    (![Actions](../../../images-dxp/icon-actions.png)) 
    and select *Assign Form*. On the screen that appears, click the *Add* button 
    (![Add](../../../images-dxp/icon-add.png)) 
    and then create the form. 

    Click *Save* when you're finished. Your process is done, and appears in 
    Kaleo Forms Admin's table. 

    ![Figure 4: Assign a form to each task in the workflow, and for the initial state.](../../../images-dxp/kaleo-forms-task-forms.png)
