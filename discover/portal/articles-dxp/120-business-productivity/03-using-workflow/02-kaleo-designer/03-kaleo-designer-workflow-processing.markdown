# Affecting the Processing of Workflow Definitions [](id=affecting-the-processing-of-workflow-definitions)

Workflow definitions all contain nodes: a Start Node, and End node, and at least
one Task node. You might know that for the workflow to progress from one node to
the other, you need Transitions. In this article you'll learn about using
transitions to move the asset through the workflow from node to node, but you'll
also learn about some other features that affect the processing of the workflow. 

- Transitions
- Forks
- Joins
- Conditions

Start by learning about the ever important Transition.

## Transitions [](id=transitions)

What are transitions? Workflow transitions connect one node to another. On
exiting the first node, processing continues to the node pointed to by the
transition. Every time you create an arrow from one node to another, Kaleo
Designer creates a transition. 

![Figure 1: You connect nodes and direct workflow processing with transitions. The Single Approver workflow has transitions named Submit, Resubmit, Reject, and Approve.](../../../../images-dxp/kaleo-designer-single-approver.png)

Each node you add has a pop-up menu letting you delete the node. As you
hover your mouse over the edges of a node, notice your mouse pointer changes to
a cross. The cross indicates you can connect the current node to another node.
Hold down your mouse button and drag the mouse to start drawing your transition
to another node. If you stop before reaching the edge of the next node, a pop-up
displays node types you can create and connect to on-the-fly. To connect with an
existing node, continue dragging the connector to that node.

When developing workflows in Kaleo Designer, make sure you go through all the
transitions and name them appropriately. By default, these transitions get system
generated names, so rename them all to something more human-readable, as they're
displayed to workflow users as links that will send the workflown item to the
next step in the workflow.

![Figure 2: In the Single Approver workflow, a user in the Review task can choose to Approve or Reject the asset, which sends the asset either to the EndNode or to the Update task.](../../../../images-dxp/kaleo-designer-transition-link.png)

To rename transitions, click on the arrow representing the transition
and use the Setting tab to set the name just like you do for a node.

## Forks and Joins [](id=forks-and-joins)

Sometimes you don't need to wait for one task to be completed before moving on
to another one. Instead, you want to do two or more things at the same time. To
do this, transition to a fork node, make two transitions from the fork to
your parallel tasks, and then come back together using a join node.

![Figure 3: Forks and Joins are used to enable parallel processing in the
workflow.](../../../../images-dxp/kaleo-designer-fork-join.png)

With a regular Join node, for the workflow to proceed beyond the join, the
transition from both parallel executions must be invoked. However, if you use a
Join XOR node instead, the workflow proceeds as long as the transition from one
of the parallel executions is invoked.

Keep in mind that you must balance your fork and join nodes. In other words, for
every fork, there must be a join that brings the parallel workflow threads back
together.

## Conditions [](id=conditions)

Sometimes you need to inspect an asset or its execution context, and depending
on the result, send it to the appropriate transition. You need a node for a
script that concludes by setting a value to one of your transitions.

From the *Category Specific Approval* (`category-specific-definition.xml`),
this is the script in the condition node that starts the workflow (coming
directly from the start node):

    import com.liferay.asset.kernel.model.AssetCategory;
    import com.liferay.asset.kernel.model.AssetEntry;
    import com.liferay.asset.kernel.model.AssetRenderer;
    import com.liferay.asset.kernel.model.AssetRendererFactory;
    import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
    import com.liferay.portal.kernel.util.GetterUtil;
    import com.liferay.portal.kernel.workflow.WorkflowConstants;
    import com.liferay.portal.kernel.workflow.WorkflowHandler;
    import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

    import java.util.List;

    String className = (String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);

    WorkflowHandler workflowHandler = WorkflowHandlerRegistryUtil.getWorkflowHandler(className);

    AssetRendererFactory assetRendererFactory = workflowHandler.getAssetRendererFactory();

    long classPK = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

    AssetRenderer assetRenderer = workflowHandler.getAssetRenderer(classPK);

    AssetEntry assetEntry = assetRendererFactory.getAssetEntry(assetRendererFactory.getClassName(), assetRenderer.getClassPK());

    List<AssetCategory> assetCategories = assetEntry.getCategories();

    returnValue = "Content Review";

    for (AssetCategory assetCategory : assetCategories) {
        String categoryName = assetCategory.getName();

        if (categoryName.equals("legal")) {
            returnValue = "Legal Review";

            return;
        }
    }

This example checks the asset category to choose the processing path, whether to
transition to the *Legal Review* task or the *Content Review* task.

You may be wondering what that `returnValue` variable is. It's the variable that
points from the condition to a transition, and its value must match a valid
transition in the workflow definition. This script looks up the asset in
question, retrieves its [asset category](/discover/portal/-/knowledge_base/7-0/organizing-content-with-tags-and-categories#defining-categories-for-content),
and sets an initial `returnValue`. Then it checks to see if the asset has been
marked with the *legal* category. If not it goes through *Content Review* (the
content-review task in the workflow), and if it does it goes through *Legal
Review* (the legal-review task in the workflow).

Now you're equipped with the basic knowledge to design beautiful, effective
workflows so that your assets can be properly reviewed before they're published
in your sites.

## Related Topics [](id=related-topics)

[Workflow Forms](/discover/portal/-/knowledge_base/7-0/workflow-forms)

[Using Workflow](/discover/portal/-/knowledge_base/7-0/enabling-workflow)

[Liferay's Workflow Framework](/develop/tutorials/-/knowledge_base/7-0/liferays-workflow-framework)

[Creating Simple Applications](/discover/portal/-/knowledge_base/7-0/creating-simple-applications)
