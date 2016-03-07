# Enabling Workflow [](id=enabling-workflow)

The workflow functionality bundled with the Business Productivity Suite comes
with one pre-installed workflow definition called the Single Approver Workflow. This
workflow requires one approval before an asset can be published. One of the
conveniences of using Liferay's workflow engine is that any roles specified in
the workflow definition are created automatically when the definition is
deployed. This provides a level of integration with the portal that third party
engines cannot match. The Single Approver Workflow contains three roles, each
with different scopes. You can deduce the scope of each role by its name: Site
Content Reviewer, Organization Content Reviewer and Portal Content Reviewer.

![Figure 1: The Single Approver Definition comes included with Liferay's Business Productivity workflow functionality.](../../../images/single-approver.png)

In addition to the Single Approver definition, there are some workflow
definitions that ship with Liferay but are not pre-installed, since they're
primarily included for test cases. They can be found in the Liferay source code
in
`modules/portal-workflow/portal-workflow-kaleo-service/src/main/resources/META-INF/definitions`,
or in the `com.liferay.workflow.kaleo.service.jar` in your Liferay installation
(see the diagrams
[here](/develop/tutorials/-/knowledge_base/6-2/designing-a-kaleo-workflow-definition)).
Once you have the XML definition files locally, you can upload them into
Liferay. Check out the article on uploading definitions to do that <!--LINK-->.

Alternatively, you can look at the pre-existing definitions to get ideas while
designing and building your own definition. 

A word about designing workflows. If you are a CE <!--Change if necessary-->
customer, you'll have to get comfortable working with XML to create workflow
definitions. There's a tutorial in the documentation for developers that will
help you learn how to do that<!--LINK-->. 

<!-- If you're a DXP customer, you have access to the Kaleo Designer
workflow builder, which gives you a nice user interface where you drag and drop
workflow nodes onto a canvas to create your workflow definitions. If you want to
start with one of the definitions mentioned above, editing it to suit your needs
would be a simple task with the Kaleo Designer. -->

<!--Picture of Workflow Designer-->

Next learn to create a workflow process definition using the Kaleo Workflow Designer.
