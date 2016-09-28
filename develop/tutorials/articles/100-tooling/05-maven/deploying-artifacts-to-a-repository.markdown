# Deploying Artifacts to a Repository

You may find it worthwhile to share your Liferay artifacts with teammates. You
can do this by creating your own Nexus repository.

Here's how you do it:

1.  Make sure you've created a repository server to hold the Liferay Maven
    artifacts. If you haven't, see the
    [Managing Maven Repositories](/develop/tutorials/-/knowledge_base/7-0/getting-started-with-maven#managing-maven-repositories)
    section of the *Getting Started with Maven* tutorial for instructions. 

2.  Verify that the repository intended to hold your Liferay artifacts is
    specified as a server in Maven's `settings.xml` file. If it isn't, see the
    [Configuring Local Maven Settings](/develop/tutorials/-/knowledge_base/7-0/getting-started-with-maven#configuring-local-maven-settings)
    section of the *Getting Started with Maven* tutorial for instructions on
    adding an entry for the server. 

3.  Navigate to the directory holding the Liferay artifacts you want to install
    to your Maven repository. If you unzipped the artifacts from a downloaded
    zip file, the artifacts are in a directory that follows the naming
    convention `liferay-portal-maven-[version]`. If you built the artifacts
    yourself, they'll be in a time-stamped directory in your `liferay-portal`
    repository directory. 

4.  Create a `build.[user name].properties` file in this directory. In the new
    properties file, specify values for the properties `lp.maven.repository.id`
    and `lp.maven.repository.url`. These refer to your repository's ID and URL,
    respectively. 

    Here are some example property values:

        lp.maven.repository.id=liferay-releases
        lp.maven.repository.url=http://localhost:8081/nexus/content/repositories/liferay-releases

    If you created a repository in Nexus, as demonstrated in the
    [Managing Maven Repositories](/develop/tutorials/-/knowledge_base/7-0/getting-started-with-maven#managing-maven-repositories)
    section of the *Getting Started with Maven* tutorial, you can specify that
    repository's ID and URL. 

5.  To deploy to your release repository server, execute the following command: 

        ant deploy

    Your console shows output from the artifacts being deployed into your
    repository server.

To verify your artifacts are deployed, navigate to the Repositories page of
your Nexus server and select your repository. A window appears below displaying
the Liferay artifacts now deployed to your repository.

![Figure 2: Your repository server now provides access to your Liferay Maven artifacts.](../../images/maven-verify-deployment2.png)
