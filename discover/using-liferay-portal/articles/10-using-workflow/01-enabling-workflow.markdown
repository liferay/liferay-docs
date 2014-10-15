# Enabling Workflow [](id=enabling-workflow)

Liferay's Kaleo workflow engine can be installed for both CE and EE versions of
Liferay. The web plugin's name is `kaleo-web` and is bundled in the *Kaleo Forms
EE* and *Kaleo Workflow CE* apps on Liferay marketplace. Installing the plugin
adds a number of new options to the control panel:

-   My Workflow Tasks

-   Workflow Configuration

-   My Submissions

-   Workflow

There is one workflow that comes bundled with the `kaleo-web` plugin: Single
Approver Workflow. This workflow requires one approval before an asset can be
published. One of the conveniences of using Liferay's workflow engine is that
any roles specified in the workflow definition are created automatically when
the definition is deployed. This provides a level of integration with the portal
that third party engines cannot match. The Single Approver Workflow contains
three roles each with different scopes. The scope of each role can be deduced by
their names: Site Content Reviewer, Organization Content Reviewer and Portal
Content Reviewer. 

Let's jump right in and create a workflow process definition. 
