# Enabling workflow [](id=enabling-workflow)

Liferay's Kaleo workflow engine can be installed for both CE and EE versions of
Liferay. The web plugin's name is `kaleo-web` and is bundled in the *Kaleo
Forms EE* and *Kaleo Workflow CE* apps on Liferay marketplace, wich you can
access through the Apps section of the Control Panel. Installing the plugin
adds a *Workflow* option under the *Configuration* section of your Control
Panel. 

The `kaleo-web` plugin comes bundled with one worfklow called the Single
Approver Workflow. This workflow requires one approval before an asset can be
published. One of the conveniences of using Liferay's workflow engine is that
any roles specified in the workflow definition are created automatically when
the definition is deployed. This provides a level of integration with the
portal that third party engines cannot match. The Single Approver Workflow
contains three roles, each with different scopes. You can deduce the scope of
each role by its name: Site Content Reviewer, Organization Content Reviewer and
Portal Content Reviewer.

Let's jump right in and create a workflow process definition.
