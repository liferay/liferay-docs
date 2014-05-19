# Verifying Successful Project Import

Once you've imported a project, you can verify that Liferay IDE correctly 
recognizes it as a Liferay project. This verification is also useful for 
non-Liferay projects that you've converted to Liferay inside of Liferay IDE. 

## Verifying Projects in Liferay IDE

Here's how you verify that your project is a Liferay project: 

1.  Once the project is imported, you should see a new project inside Eclipse
    and it should have an "L" overlay image; the "L" is for Liferay! 

    ![Figure 1: Look for an "L" overlay image to verify that the import succeeded.](../../images/ide-liferay-project-w-overlay-image.png)

2.  Check the project's target runtime and facets to make sure it's configured
    as a *Liferay IDE* project:

    2.1. In the *Package Explorer*, right click *<project-name>* &rarr;
         *Properties* &rarr; *Targeted Runtimes*. 

    2.2. In the *Properties* window, click *Project Facets* and make sure that
    the Liferay plugin facets are properly configured. 

    ![Figure 2: Make sure that your project's Liferay plugin facets are properly configured.](../../images/ide-project-facets.png)

Great! You've confirmed that your import or conversion was successful; you can 
now make revisions to your configured Liferay IDE project. 
