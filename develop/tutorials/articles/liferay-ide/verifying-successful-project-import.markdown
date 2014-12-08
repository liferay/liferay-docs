# Verifying Successful Project Import [](id=verifying-successful-project-import)

Once you've imported a project, you can verify that Liferay IDE correctly 
recognizes it as a Liferay project. 

Here's how you verify that your project imported correctly as a Liferay project: 

1.  Once the project is imported, you should see a new project inside Eclipse
    and it should have an "L" overlay image; the "L" is for Liferay! 

    ![Figure 1: Look for an "L" overlay image to verify that the project imported successfully as a Liferay project.](../../images/ide-liferay-project-w-overlay-image.png)

    If your project doesn't have the "L" overlay image, it needs to be faceted
    for Liferay and/or it needs to be targeted for a Liferay Runtime
    environment. If it needs faceting, follow steps in the tutorial
    *Faceting a Plugin Project for Liferay IDE*. <!-- TODO convert to link -->

2.  Check the project's target runtime and facets to make sure they're
    configured properly for your plugin project:

    In the *Package Explorer*, right click your project's name and select
    *Properties* &rarr; *Targeted Runtimes*. In the *Targeted Runtimes* section,
    make sure that the proper Liferay Runtime is selected. 

3.  Check the project's facets to make sure they're configured properly for a
    *Liferay IDE* project:

    In the *Properties* window, click *Project Facets* and make sure that the
    Liferay plugin facets are properly configured. If your project is not
    faceted, you can follow the tutorial *Faceting a Plugin Project for Liferay
    IDE* to add the necessary facets. <!-- TODO convert to link -->

    ![Figure 2: Make sure that your project's Liferay plugin facets are properly configured.](../../images/ide-project-facets.png)

4.  Click the *OK* button. 

Congratulations on verifying that your project imported properly! 

Here are some related topics:

[Setting Up Liferay Maven Facets in Existing Liferay Maven Plugin Projects](/develop/tutorials/-/knowledge_base/6-2/setting-up-liferay-maven-facets-in-existing-liferay-maven-plugin-projects)

[Deploying Plugins](/develop/tutorials/-/knowledge_base/6-2/deploying-plugins)

