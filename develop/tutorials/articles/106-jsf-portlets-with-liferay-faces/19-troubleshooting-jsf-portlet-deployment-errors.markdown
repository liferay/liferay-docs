# Troubleshooting JSF Portlet Deployment Errors [](id=troubleshooting-jsf-portlet-deployment-errors)

Many of us have experienced it; you go to deploy a new application into your
portal instance and see the dreaded deployment error messages. What do you do?
Is the error message helpful? How can I find the solution?

If you're experiencing deployment problems with deploying a JSF portlet to
Liferay Portal, there's a simple process you can follow to solve them. Instead 
of trying to figure out what's wrong with your code, it's usually easier to 
compare your portlet to a working example and find the differences between the 
two. This strategy is recommended by the Liferay Faces team. This tutorial 
creates a working example for this purpose. 

For each step below, follow the instructions (if applicable) that pertain to
your specific app server. You can follow these fundamental steps for any app
server.

1. Download a Liferay + *[app server]* bundle.

2. Determine the correct version of Liferay Faces. Visit the
   [Understanding the Liferay Faces Version Scheme](/develop/tutorials/-/knowledge_base/6-2/understanding-the-liferay-faces-version-scheme)
   tutorial for more information.

3. Upgrade your app server's Mojarra version (if applicable) so it's identical
   to the one used by Liferay Faces. Visit the *Upgrading Mojarra* section of
   your respective app server's installation tutorial found in the
   [Installation and Setup](/discover/deployment/-/knowledge_base/6-2/installation-and-setup)
   section for more information.

4. Upgrade your app server's Weld version (if applicable) so it's identical
   to the one used by Liferay Faces. Visit the *Upgrading Weld* section of
   your respective app server's installation tutorial found in the
   [Installation and Setup](/discover/deployment/-/knowledge_base/6-2/installation-and-setup)
   section for more information.

5. Download and build Liferay Faces from its project source code. Visit the
   [Building Liferay Faces from Source](/develop/tutorials/-/knowledge_base/6-2/building-liferay-faces-from-source)
   tutorial for more information.

6. Build the `jsf-applicant-portlet` from liferay-faces on Github and deploy it to your
   portal bundle. The following commands do this using the JBoss profile:

        cd demo/jsf-applicant-portlet
        mvn -P jboss clean package
        cp target/jsf-applicant-portlet*.war $LIFERAY_HOME/deploy

7. Build the `primefaces-applicant-portlet` from liferay-faces on Github and
   deploy it to your portal bundle. For example, the commands below do this
   using the JBoss profile:

        cd demo/primefaces-applicant-portlet
        mvn -P jboss clean package
        cp target/primefaces-applicant-portlet*.war $LIFERAY_HOME/deploy

    +$$$

    **Note:** In some cases, you may need to clean and reinstall all projects in
    the Git repo before you can succesfully build the demo portlets. To do
    this, run `mvn clean install` from the top-level directory.

    $$$

8. Examine the working example WARs and find out how they are different from the
   WARs that you are having trouble deploying.

Typically, deployment errors are associated with dependencies. For example, you
might be including the `portal-service.jar` inside the `WEB-INF/lib` directory,
which could cause a `ClassCastException`. Whichever case is causing your
deployment errors, comparing your portlet with a verified JSF portlet example is
the most efficient way to find your problem.
