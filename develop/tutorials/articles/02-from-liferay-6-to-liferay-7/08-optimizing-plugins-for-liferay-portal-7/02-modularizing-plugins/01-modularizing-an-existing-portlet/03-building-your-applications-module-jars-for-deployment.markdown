# Building Your Application's Module JARs for Deployment [](id=building-your-module-jars-for-deployment)

Now it's time to build your modules and deploy them to your @product@ instance.
To build your project, run `gradlew build` from your project's root directory.

Once your project successfully builds, check all of your modules' `/build/libs`
folders. There should be a newly generated JAR file in each, which is the file
you'll need to deploy to @product@. You can deploy each JAR by running `blade
deploy` from each module's root directory.

+$$$

**Note:** If you deploy your modules out of order, you might receive error
messages. For instance, if you try deploying your web client module first,
you'll receive errors if it relies on the service implementation and service
API modules. Once each module's dependencies are met, they will successfully be
deployed for use in Liferay. For more information on checking each module's
dependencies, see the
[Using the Felix Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)
article.

$$$

Once you've successfully deployed your modules, you can list them from the Gogo
shell as shown below.

![Figure 1: Once you've connected to your Liferay instance in your Gogo shell prompt, run *lb* to list your new converted modules.](../../../../images/deploy-converted-modules.png)

This tutorial explained how to convert your traditional application into the
modular format of a @product-ver@ style applicaton. Specifically, you learned
how to

- Create a web client (`*-web`) module that holds your application's portlet
  classes and UI.
- Create a service implementation module (`*-service`) and a service API module
  (`*-api`).
- Run Service Builder to generate code for your application's service and API
  modules.
- Wire your modules together by declaring their dependencies on each another.
- Build your modules and deploy them to your @product@ installation.

Great job!

**Related Topics**

[Portlets](/develop/tutorials/-/knowledge_base/7-0/portlets)

[Service Builder](/develop/tutorials/-/knowledge_base/7-0/service-builder)
