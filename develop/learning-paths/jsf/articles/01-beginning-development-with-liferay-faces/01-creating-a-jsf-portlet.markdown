# Creating a JSF Portlet

Since you're configured with Liferay IDE/Developer Studio, you can jump right in
to creating a JSF application. Applications in Liferay Portal are called
*portlets*, so you'll create a JSF portlet project. 

1. Go to *File* &rarr; *New* &rarr; *Liferay JSF Portlet*. Unless you've already
   created a JSF project in your workspace, you'll receive a message stating
   that there are no suitable Liferay plugin projects available for a Liferay
   JSF Portlet. Select *Yes* to open a New Liferay Plugin Project wizard to
   create the JSF plugin project, first. The new plugin project wizard appears. 

    ![Figure 1: Liferay IDE/Developer Studio's new plugin project wizard makes it very easy to create a portlet project.](../../images/lds-new-jsf-plugin-project.png)

2. Fill in the *Project name* and *Display name* as *guestbook-jsf-portlet* and
   *Guestbook*, respectively. 

3. Leave the *Use default location* checkbox checked. By default, the default
   location is set to your current Plugins SDK. Select the *Ant
   (liferay-plugins-sdk)* option for your build type, and the appropriate
   Plugins SDK and Liferay runtime. You also have the option of choosing the
   *Maven* build type, which is also popular with JSF portlets. You can visit
   the [Maven tutorials](/develop/tutorials/-/knowledge_base/6-2/maven) to learn
   how to build plugins using Maven. 

    If you'd like to use Maven to build your guestbook portlet and continue
    following along in this learning path, visit the
    [Developing Liferay Faces Portlets with Maven](/develop/tutorials/-/knowledge_base/6-2/developing-liferay-faces-portlets-with-maven)
    tutorial to update yourself on the key differences between using the Plugins
    SDK and using Maven. 

4. Select *Portlet* for the Plugin type. 

5. Make sure the *Include Sample Code* checkbox is checked and the *Launch New
   Portlet Wizard after project is created* checkbox is **not** checked (if
   necessary), and click *Next*. 

6. Since you'll be using the JSF portlet framework, select the *JSF 2.x* radio
   option and click *Next*. 

7. The next screen in the wizard offers available JSF UI component suites. You
   can read each UI component suite's summary and learn what each can do for
   you. For the guestbook portlet, you'll use the *Liferay Faces Alloy* UI
   component suite, so select that radio option and click *Finish*. 

    ![Figure 2: The list of component suites includes the JSF Standard suite, ICEfaces, Liferay Faces Alloy, PrimeFaces, and RichFaces.](../../images/jsf-ui-component-suite-wizard.png)

Your project can now be found in the Package Explorer on the left side of your
development environment. You've just created a blank JSF Liferay project. Projects
can be filled with portlets. Your next step is to create the portlet that will
live inside your portlet project. You'll do this in the New Liferay JSF Portlet
wizard that is launched after the project is created. 

1. Specify the Portlet class as `javax.portlet.faces.GenericFacesPortlet` (if
   necessary). Then click *Next*. 

2. Enter `guestbook-jsf` for the portlet name. The following fields are edited
   accordingly. 

3. Uncheck the *Create view files* checkbox. You'll create your own view files
   manually. 

4. Click *Finish*. 

Awesome! You just created a JSF portlet! However, the current JSF portlet is
very bare bones and needs some further development so it can serve a purpose.
Just for kicks, deploy the JSF portlet to your Liferay Portal instance to see
what it looks like. 

To deploy this portlet, simply drag the project from the Package Explorer onto
your Liferay server. 

![Figure 3: Drag and drop your project onto the Liferay server to deploy it.](../../images/deploy-jsf-portlet.png)

+$$$

**Note:** Notice in your Console that there are many *DEBUG* messages. By
default, your portlet has been configured to print everything in your console in
debug mode. If you'd like to not run your portlet in debug mode, navigate to
your portlet's `docroot/WEB-INF/faces-config.xml` file and remove the following
code: 

    <lifecycle>
        <phase-listener>com.liferay.faces.util.lifecycle.DebugPhaseListener</phase-listener>
    </lifecycle>

Throughout this learning path, debug mode will be kept on. 

$$$

You can now view the bare bones JSF guestbook by clicking the *Add* button (plus
sign) and choosing *Applications*. Then from the Sample category, drag the JSF
Guestbook onto the page. The portlet display a simple message, which comes by
default when creating a Liferay portlet through IDE/Developer Studio. 

![Figure 4: The JSF Guestbook only displays a default message--for now.](../../images/jsf-guestbook-bare.png)

+$$$

**Note:** Liferay Faces has a specific versioning scheme it follows to keep
releases concise and separate from other releases. A concise versioning scheme
also makes it easier to differentiate between a Liferay Faces version's
compatibility with other platforms. To learn more, visit the
[Understanding the Liferay Faces Version Scheme](/develop/tutorials/-/knowledge_base/6-2/understanding-the-liferay-faces-version-scheme)
tutorial. 

$$$

Remember reading briefly about how the Liferay Faces Bridge is used to keep JSF
portlets compatible with Liferay Portal? You'll dive into the bridge and its
usage next. 
