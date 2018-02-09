# Using Workflow [](id=using-workflow)

The Guestbook application accepts submissions from any logged in user, so 
there's no telling what people could post. Illegal data, objectionable content, 
the entire contents of Don Quixote: all of these and more are possibilities. 
You can make sure user posts don't run afoul of the law or policy by enabling 
*workflow* in your application. 

Workflow is a review process that ensures a submitted entity isn't published
before it's reviewed. To prevent posting objectionable content, an initially
submitted Guestbook entry should be marked as a *draft* and sent through the
workflow framework. It comes back to the application code ready to have any
relevant fields updated in the database based on its status. The view layer must
filter entities by status to display only reviewed entities.

+$$$

**Note:** The exact review process is defined separately from the code that
enables workflow. An XML file provides the definition of a workflow in
@product@. If you're a Liferay Digital Enterprise subscriber, you have access to
the Kaleo Workflow Designer, which offers a convenient drag-and-drop user
interface for designing workflow definition files. You can read more about this
in Liferay DXP's
[documentation](https://customer.liferay.com/documentation/7.0/admin/-/official_documentation/portal/kaleo-designer).
@product@ comes with a workflow definition called the *Single Approver*
definition, but you can write your own workflow definitions according to your
organization's requirements. 

A few additional definitions are included in @product@'s source code, which you
can use to see how workflow definitions are defined. To discover how to access
these files, see
[here](/discover/portal/-/knowledge_base/7-0/enabling-workflow).

$$$

This section instructs the reader in workflow-enabling the Guestbook App's
`Guestbook` and `Entry` entities to ensure that only approved content is
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

<a class="go-link btn btn-primary" href="/develop/tutorials/-/knowledge_base/7-0/supporting-workflow-at-the-service-layer">Let's Go!<span class="icon-circle-arrow-right"></span></a>
