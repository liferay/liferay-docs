# Creating a Maven Repository

To create a Maven repository using Nexus, download
[Nexus](https://help.sonatype.com/display/NXRM2/Download) and follow the
instructions on Nexus'
[Installation page](https://help.sonatype.com/display/NXRM2/Installing+and+Running)
to install and start it. 

To create your own repository using Nexus, follow these steps: 

1.  Open your web browser; navigate to your Nexus repository server (e.g.,
    [http://localhost:8081/nexus](http://localhost:8081/nexus)) and log in. The
    default user name is `admin` with password `admin123`. 

2.  Click on *Repositories* and navigate to *Add...* &rarr; *Hosted Repository*. 

    ![Figure 1: Adding a repository to hold your Liferay artifacts is easy with Nexus.](../../../images/maven-nexus-create-repo.png)

    To learn more about each type of Nexus repository, read Sonatype's
    [Managing Repositories](http://books.sonatype.com/nexus-book/reference/confignx-sect-manage-repo.html)
    guide.

3.  Enter repository properties appropriate for the type of artifacts it will
    hold. If you're installing release version artifacts into the repository,
    specify *Release* as the repository policy. Below are example repository
    property values: 

    - **Repository ID:** *liferay-releases*
    - **Repository Name:** *Liferay Release Repository*
    - **Provider:** *Maven2*
    - **Repository Policy:** *Release*

    To create a snapshot repository, choose *Snapshot* for the Repository Policy
    and update the ID and name accordingly.

4.  Click *Save*.

Voila! You've created a repository for your Liferay releases (i.e.,
`liferay-releases`) and/or Liferay snapshots (i.e., `liferay-snapshots`). To
learn how to deploy your Liferay Maven artifacts to a Nexus repository, see the
[Deploying Liferay Maven Artifacts to a Repository](/developer/reference/-/knowledge_base/7-2/deploying-liferay-maven-artifacts-to-a-repository)
tutorial.

See the
[Configuring Local Maven Settings to Access Repositories](/developer/reference/-/knowledge_base/7-2/configuring-local-maven-settings-to-access-repositories)
to configure your new repository servers in your Maven settings to install
artifacts to them. 
