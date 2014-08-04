# Writing Your First JSF Application

Getting started with a JSF application for Liferay is very easy. This learning
path guides you step-by-step for creating and developing a JSF application. In
particular, you'll develop a guestbook application which users can use to add
messages to a page. When you're finished, the final guestbook application will
look like this: 

<!--
![Figure 1: Insert final JSF application when it's fully developed (change current image).](../../images/my-first-app.png)
-->

You may be wondering how and why JSF is so easy to deploy to Liferay. Isn't JSF
a separtate technology? JSF portlets use *Liferay Faces Bridge*, which supports
deploying JSF web applications as portlets on any JSR 286 (Portlet 2.0)
compliant portlet container, like Liferay Portal 5.2, 6.0, 6.1, and 6.2. You'll
explore more about the bridge later on, but you won't have to worry about it
when creating your JSF application, since it's configured behind the scenes. 

<!-- Check to see if bridge is automatically configured in a JSF portlet project
when it's created using IDE/LDS. -Cody -->

Ready to begin creating a JSF application for Liferay Portal? 

## Creating a JSF Portlet

Since you're configured with Liferay IDE/Developer Studio, you can jump right in
to creating a JSF application. Applications in Liferay Portal are called
*portlets*, so you'll create a JSF portlet project. 

1. Go to *File* &rarr; *New* &rarr; *Liferay JSF Portlet*. Unless you've already
   created a JSF project in your workspace, you'll receive a message stating
   that there are no suitable Liferay plugin projects available for a Liferay
   JSF Portlet. Select *Yes* to open a New Liferay Plugin Project wizard to
   create the JSF plugin project, first. The new plugin project wizard appears. 

    ![Figure 2: Liferay IDE/Developer Studio's new plugin project wizard makes it very easy to create a portlet project.](../../images/lds-new-jsf-plugin-project.png)

2. Fill in the *Project name* and *Display name* as *jsf-guestbook-portlet* and
   *JSF Guestbook*, respectively. 

3. Leave the *Use default location* checkbox checked. By default, the default
   location is set to your current Plugins SDK. Select the *Ant
   (liferay-plugins-sdk)* option for your build type, and the appropriate
   Plugins SDK and Liferay runtime. 

4. Select *Portlet* for the Plugin type. 

5. Make sure the *Include Sample Code* and *Launch New Portlet Wizard after
   project is created* are unchecked (if necessary), and click *Next*. 

6. Since you'll be using the JSF portlet framework, select the *JSF 2.x* radio
   option and click *Next*. 

7. The next screen in the wizard offers available JSF UI component suites. You
   can read each UI component suite's summary and learn what each can do for
   you. For the guestbook portlet, you'll use the *JSF standard* UI component
   suite, so select that radio option and click *Finish*. 

    ![Figure 3: The list of component suites includes the JSF Standard suite, ICEfaces, Liferay Faces Alloy, PrimeFaces, and RichFaces.](../../images/jsf-ui-component-suite-wizard.png)

Your project can now be found in the Package Explorer on the left side of your
development environment. You've just created a blank JSF Liferay project. Projects
can be filled with portlets. Your next step is to create the portlet that will
live inside your portlet project. You'll do this in the New Liferay JSF Portlet
wizard that is launched after the project is created. 

1. Specify the Portlet class as
`com.liferay.faces.bridge.GenericLiferayFacesPortlet`. Then click *Next*. 

2. Enter `jsf-guestbook` for the portlet name. The following fields are edited
accordingly. 

3. Click *Finish*. 

Awesome! You just created a JSF portlet! However, the current JSF portlet is
very bare bones and needs some further development so it can serve a purpose.
Just for kicks, deploy the JSF portlet to your Liferay Portal instance to see
what it looks like.

To deploy this portlet, simply drag the project from the Package Explorer onto
your Liferay server. 

![Figure 4: Drag and drop your project onto the Liferay server to deploy it.](../../images/deploy-jsf-portlet.png)

You can now view the bare bones JSF guestbook by clicking the *Add* button (plus
sign) and choosing *Applications*. Then, from the Sample category drag the JSF
Guestbook onto the page. The portlet only has a default message, which comes
by default when creating a Liferay portlet through IDE/Developer Studio.

![Figure 5: The JSF Guestbook only displays a default message--for now.](../../images/jsf-guestbook-bare.png)

Remember reading briefly about how the Liferay Faces Bridge is used to keep JSF
portlets compatible with Liferay Portal? You'll dive into the bridge and its
usage next.

## Using the Liferay Faces Bridge
