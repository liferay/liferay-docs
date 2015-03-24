# Setting up Your LCS Account [](id=setting-up-your-lcs-account)

To use LCS, you must have an account at
[Liferay.com](http://www.liferay.com/). Once you've logged in with your Liferay
user credentials, you're taken to the *Projects* screen where you can join an
existing project or create a new one. 

![Figure 4.1: When creating your LCS account, you can join an existing project or create a new one.](../../images/lcs-projects-01.png)

The list of existing projects, if present, is the list of projects associated 
with the domain of your email address. This is typically the company your email 
address is associated with. For example, if your email address is 
`joebloggs@janesblogfactory.com`, you're then presented with a list of existing 
projects registered to users with the `@janesblogfactory.com` domain. This lets 
you quickly and easily join the LCS projects that are associated with your 
company. 

Note that if you're already a member of any projects that don't have an 
administrator, you're given the option to become the administrator. These 
projects are listed in the *Available Projects Without an Administrator* 
section. To become the administrator for one of these projects, check the 
checkbox next to it and click *Save*.

Any other existing projects in your domain are listed in the 
*My Company Projects* section. To request access to a project, check its 
checkbox and click *Save*. Your request is then sent to the project 
administrator for approval. Note that it's possible for the project 
administrator to pre-assign you a role in a project (more on LCS roles in a 
moment). In this case, you won't have to wait for approval. For more information 
on user management in LCS, please see the *Managing LCS Users in Your Project* 
section of this guide.

Alternatively, you can create a new project and make yourself its administrator.
To do so, click the checkbox next to *Create a project and become an
administrator* in the *New Project* section of the Projects screen. The section
then expands to let you name the project and choose to go to directly to the
project's *Dashboard* (more on the Dashboard later). Give your project a name
and then click *Save*. 

![Figure 4.2: Check the checkbox and click *Save* to begin creating a new project.](../../images/lcs-projects-02.png)

![Figure 4.3: Give your new project a name and click *Save*.](../../images/lcs-new-project.png)

So what exactly can an LCS Administrator do? Fantastic question! As LCS 
Administrator, you can assign roles to the rest of the users in your project. 
Each user of LCS needs to have an assigned role. The following roles are 
available: 

- LCS Administrator: All LCS functionality is available to administrators. This 
  is the only role that can manage the roles of other users.
- LCS Environment Manager: All LCS functionality is available in the scope of 
  an environment, with the exception of managing other users.
- LCS Environment Viewer: Has read-only access in the scope of an environment.

You should note that each of these LCS roles assume that the user already has 
the LCS User role in his or her Liferay.com account. The LCS User role is
granted automatically the first time the user enters their LCS account. The
actions that can be performed by each of the LCS roles are detailed in the below
permissions matrix. 

**LCS Permissions Matrix**

Action | &nbsp;LCS Administrator | &nbsp;LCS Environment Manager | &nbsp;LCS Environment Viewer |
------ | ----------------------- | ----------------------------- | ---------------------------- |
Access LCS | true | true | true |
Access Any Environment | true | false | false |
Access a Particular Environment | true | true | true |
Manage Users in Any Environment | true | false | false |
Manage Users in a Particular Environment | true | true | false |
Invite Users to LCS | true | false | false |
Create and Delete Environments | true | false | false |
Edit Any Environment | true | false | false |
Edit a Particular Environment | true | true | false |
Server Registration in Any Environment | true | false | false |
Server Registration in a Particular Environment | true | true | false |
Install Fix Packs in Any Environment | true | false | false |
Install Fix Packs in a Particular Environment | true | true | false |

Now that your LCS account has been set up and you have an understanding of the 
LCS roles, you can get your portal ready for LCS.
