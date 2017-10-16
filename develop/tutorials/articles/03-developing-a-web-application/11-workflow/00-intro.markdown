# Using Workflow

The Guestbook application accepts submissions from guest users, so there's no
telling what people could post. Illegal data, objectionable content, the entire
contents of Don Quixote, all of these and more are possibilities. You can make
sure user posts don't run afoul of the law or policy by enabling *workflow* in
your application. 

Workflow is a review process meant to ensure that a submitted entity doesn't
reach the *approved* status before it's reviewed. In the Guestbook Application's
code, an initially submitted entry should be marked as a *draft* and sent
through the workflow framework. It comes back to the application code ready to
have any relevant fields updated in the database based on its status. The status
of each entity should also be used in the view layer to ensure that only
reviewed entities are displayed to end users.

+$$$

**Note:** The exact review process is defined separately from the code that
enables workflow. An XML file provides the definition of a workflow in
@product@. If you're a Liferay Digital Enterprise subscriber, you have access to
the [Kaleo Workflow
Designer](/discover/portal/-/knowledge_base/7-0/kaleo-designer), which offers a
convenient drag-and-drop user interface for designing workflow definition files.
@product@ is shipped with a workflow definition called the *Single Approver*
definition, but you can write your own workflow definitions according to your
organization's requirements. 

A few additional definitions are included in @product@'s source code, which you
can use to see how workflow definitions are defined. To discover how to access
these files, see
[here](/discover/portal/-/knowledge_base/7-0/enabling-workflow).

$$$

This Learning Path instructs the reader in workflow-enabling the Guestbook
App's `Guestbook` and `Entry` entities to ensure that only approved content is
published after review.

![Figure 1: Enable workflow in your assets, just like @product@'s own assets.](../../../images/workflow-config.png)

There are five steps to enabling workflow:

1.  Update the service layer to set each entity's status fields.

2.  Send the entity to @product@'s workflow framework. 

3.  Add *getter* methods that account for an entity's workflow status.

4.  Handle the entity as it returns from the workflow framework.

5.  Update the user interface to account for workflow status.

The first three steps happen in the service layer, so that's a good place to
start.

