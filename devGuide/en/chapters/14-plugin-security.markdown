
# Plugin Security Management [](id=lp-6-1-dgen11-plugin-security-management-0)

We all wish cyberspace were free of malicious software and unwanted bugs. Since
it isn't, we need to guard ourselves and our portals from these evils. Enter
Liferay Portal's Plugin Security Manager! It's like a super-hero in a cape and
tights, except, well, it's not.

In its quest for peace within your portal, the Plugin Security Manager pledges
to:

- Protect your portal and host system from unwanted side affects and malicious
  software introduced by plugins.
- Control plugin access to your portal, host system, and network by requiring
  that plugins specify ahead of time the portal resources they intend to access.

Let's go over some scenarios that could apply to you with regard to trying new
plugins, and then maybe the importance of this will be clear.

- A flashy new plugin has arrived on Liferay Marketplace and you want to give it
  a whirl. But naturally, you want to know the parts of your system it will
  access.
- A colleague finds an interesting plugin after scouring the web for something
  that can help streamline processes at your workplace. Of course, you don't
  know whether you can truly trust the plugin creator--this plugin was found
  outside the Liferay Marketplace. If the plugin isn't open source, you have no
  way of knowing if it does anything nefarious.
- Upper management requests your corporate branch and other branches use a
  standard set of plugins on your portal instances. This set of plugins,
  however, was written by an outside firm, and you need to know there will be no
  tampering with your proprietary files.

These are just a few scenarios that may ring true for you. When you're
responsible for keeping your system running well 24x7, you can't be too cautious
in protecting your portal, system and network.

## How plugin security works [](id=lp-6-1-dgen11-how-plugin-security-works-0)

When the Plugin Security Manager is enabled for your plugin, it checks your
plugin's *Portal Access Control List (PACL)*. This list describes what APIs the
plugin accesses, so people deploying the plugin can review what it does without
seeing its source code. If the plugin tries to access anything that's not on
this list, the plugin's request is stopped dead in its tracks with the security
manager logging information on the attempt to access unauthorized APIs or
resources. 

Access to APIs and resources is authorized by means of property values specified
in the plugin's `liferay-plugin-package.properties` file. This file must be
specified in your plugin's `WEB-INF` directory. These security manager
properties are collectively known as the plugin's PACL. 

As you develop plugins for Liferay Marketplace or for distribution within your
organization, you'll need to set the security manager properties appropriately.
Before we dive into the intricacies of these properties, let's consider a plugin
development approach that involves designing an app for the security manager
from the ground up. 

## Developing Plugins with security in mind [](id=lp-6-1-dgen11-developing-plugins-with-security-in-mind-0)

At the start of plugin developement, you may not have a clear picture of all the
aspects of the portal you'll need to access, and that's fine. In fact, we
suggest you go ahead and develop your plugin first and address your plugin's
PACL later. But, as you develop your plugin there are some common security
pitfalls, highlighted in the next section, that we'll show you how to avoid.
After you develop your plugin you'll dig whole-heartedly into security
management by generating and fine-tuning you plugin's PACL. Don't worry, we'll
guide you through it. 

If you're developing a plugin as part of a free app, writing a PACL for your
plugin and enabling the security manager are optional, and you can skip this
chapter. Otherwise, read on. 

Here is the suggested approach for developing secure plugins: 

- Consider common security pitfalls.
- Develop your plugin. 
- Build your plugin's PACL using Liferay's PACL Policy Generation tool. 
- Test your plugin thoroughly, with the security manager enabled. 
- Add to your plugin's security policy, as needed.
- Convert your policy's absolute file paths into relative paths. 

Let's go over each part of this approach. 

### Consider Common Security Pitfalls [](id=common-security-issues-liferay-portal-6-1-dev-guide-en)

As you develop your plugin, you need to anticipate your plugin's actions in
light of Liferay's secured environment. The security manager leverages the Java
SE Security Architecture. So understanding Java SE Security and learning the few
requirements that Liferay's security manager adds on top of it will benefit you
as you develop plugins. The extensive Java SE Security Architecture
documentation is available for you to read at
[http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html](http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html).
But we'll highlight a couple common mistakes developers make that violate
Liferay's secured environment: 

- Invoking a method, irectly or indirectly, without considering whether it can
  throw a security exception.
- Using external libraries or frameworks that access classloaders outside of
  your plugin.  

You wouldn't intentionally make these kinds of mistakes, but you'd be surprised
at how easily you can make them if you're not being careful enough. We'll
consider scenarios that illustrate both of these mistakes and explain how to
avoid making them in your plugin. Let's consider security exceptions first. 

When you're running on Liferay Portal with the security manager enabled, you
must only access authorized resources. If you invoke a method declared as
throwing a security exception (i.e., `java.lang.SecurityException`) and you're
not authorized to access the resources the method uses, the method throws the
security exception and the Security Manger stops your plugin dead in its tracks.
Security exceptions are unchecked, meaning that the compiler doesn't require
your code to handle them. But since methods that throw security exceptions are
declared as throwing them, you should check their signatures as you're
designing your plugin. If they throw security exceptions, handle them
appropriately with try/catch blocks. Keep in mind that you not only need to
handle security exceptions of methods your plugin invokes *directly*, but you
also need to handle the security exceptions of the underlying methods your
plugin invokes *indirectly*.   

For example, you may be using a file utility that calls `java.io.File`'s
`canRead` method. Since the `canRead` method can throw a `SecurityException`,
your plugin will violate security if it invokes the utility on a file that
you're not authorized to access. So, be aware of all security exceptions thrown
by methods your plugin calls directly and indirectly.

Operations involving reflection, and similar activities, typically can throw
security exceptions. The Java SE Security documentation explains how to deal
with them. In many cases, you can declare your plugin's permissions to avoid
running into these exceptions. We'll go over your plugin's permissions and
security policies later in this chapter. 

The second common mistake you should avoid is allowing your plugin to bring up
classloaders unintentionally, via other frameworks or libraries. Consider the
following Spring configuration from a plugin:

    <bean id="userServiceBeanFactory"
        class="com.liferay.portal.service.UserLocalServiceUtil"
        factory-method="getService"
    />

It declares a factory bean that calls a method on a Liferay class. This seems
reasonable, right? Unfortunately, Spring tries to grab the classloader for the
factory class. Since the factory class does not belong to the plugin, the
security manager balks at the plugin's attempt to access the classloader for the
factory class. The security manager doesn't allow applications to get arbitrary
classloaders because the classloaders can add, access, and modify classes that
your plugin is unauthorized to access. Using Spring in this manner violates the
secured environment.

How do you get around this issue? You could simply invoke the method directly
like this: 

    UserLocalServiceUtil.getService()

But if you insist on using a Spring factory bean, you can do the following:

1. Write a class *inside* your plugin to act as a factory. Your factory class
   should declare a class that wraps the type of instance your factory returns.
   Your factory should also implement a method that returns the instance,
   wrapped in the class you declared. 

2. Configure a Spring factory bean that uses your plugin's factory class. 
    
Here's what your plugin's new factory class could look like:

        package test;
    
        // Add imports here ...
    
        public class FactoryUtil {
    
            public static UserLocalService getUserLocalService() {
                TestUserLocalServiceWrapper localServiceWrapper =
                    new TestUserLocalServiceWrapper(
                        UserLocalServiceUtil.getService());
    
                return localServiceWrapper;
            }
    
            private static class TestUserLocalServiceWrapper
                extends UserLocalServiceWrapper {
     
                public TestUserLocalServiceWrapper(
                    UserLocalService userLocalService) {

                    super(userLocalService);
                }
    
            }
    
        }

The code above declares a factory class named `FactoryUtil` that resides in a
package named `test`. The factory declares an inner class named
`TestUserLocalServiceWrapper` that extends Liferay's `UserLocalServiceWrapper`
class. Note, `UserLocalServiceWrapper` in turn wraps `UserLocalService`--the
class you want the factory to return. Lastly, the `getUserLocalService()` method
uses the original factory method, `UserLocalServiceUtil.getService()`, to get
the `UserLocalService` instance. This instance is wrapped up in your factory's
`TestUserLocalServiceWrapper` class. In your plugin, you've implemented a
factory class to access the instances you want. That wasn't so difficult, was
it? 

Your new Spring factory bean would look like the following configuration:

    <bean id="userServiceBeanFactory" 
        class="test.FactoryUtil"
        factory-method="getUserLocalService"
    />

Great! Now you know a couple alternatives to using the troublesome Spring
factory bean configuration that was accessing a classloader that didn't belong
to your plugin. 

With regards to both of the use cases we've illustrated, the main point we're
emphasizing is that you must be aware of the how the libraries you use behave
with respect to your secured environment. The better you understand Java SE
Security and Liferay's Plugin Security Management, the easier it will be for you
to write security-aware plugins. Keeping this in mind, you can proceed
confidently creating your plugin.

### Develop your plugin [](id=lp-6-1-dgen11-develop-your-plugin-0) 

Start creating your plugin the way you normally would. Design your application,
write code, unit test your code, have users beta test your code. In essence, do
everything you normally would do. Do all of this with the Plugin Security
Manager disabled via your plugin's `liferay-plugin-package.properties` file:  

	security-manager-enabled=false

Before the Plugin Security Manager is enabled, you must specify the resources
your plugin accesses. Let's build a list of these resources in your plugin's
PACL. 

### Build Your Plugin's PACL [](id=pacl-policy-generation-tool-liferay-portal-6-1-dev-guide-en)

Rather than tediously figuring out all of the resources your plugin accesses, on
your own, let Liferay's PACL Policy Generation tool to give you a head start.
The generation tool detects resources your plugin accesses and writes
corresponding PACL properties to a policy file. You can then merge the PACL
properties from this policy file into your plugin's
`liferay-plugin-package.properties` file. 

Here's how you generate a PACL policy for your plugin: 

1.  Make sure your Liferay Portal instance has `liferay` set as its security
    manager strategy value and that the security manager was activated during
    application server startup. 

    In your `portal-ext.properties` file, make sure Liferay Portal's security
    manager strategy is specified as follows: 

        portal.security.manager.strategy=liferay

    Your app server may require that certain startup arguments be used for
    activiting the security manager. For example, Tomcat requires that you pass
    in an option `-security` in order to activate the security manager. Check
    your app server's security manager documentation to make sure. Conveniently,
    some app servers, like Tomcat, output a terminal message, like "Using
    Security Manager", indicating that it's using the security manager. 

    Unless you already started Liferay with the security manager enabled and
    activated as described above, you must restart Liferay with these settings. 

2.  Enable the security manager to generate a security policy for your plugin by
    setting the following property in your plugin's
    `liferay-plugin-package.properties` file: 

        security-manager-enabled=generate

3.  Deploy your plugin.

    The PACL Policy Generation tool writes a PACL policy file with the following
    path: 

        [liferay.home]/pacl-policy/[servletContextName].policy

    On deploying your plugin and as you exercise your plugin's features, Liferay
    Portal's security manager performs security checks on your plugin; but
    rather than throwing errors on failed checks, the generator tool writes
    suggested rules that specify access to the resources your plugin accesses. 

    Unless you've turned off logging for the generator tool, messages like the
    ones below are logged, reporting the various authorization properties that
    the tool generated

        DEBUG [localhost-startStop-2][GeneratingPACLPolicy:230] my-pacl-portlet
        generated authorization property {key=security-manager-properties-read,
        value=log4j.configDebug}
        DEBUG [localhost-startStop-2][GeneratingPACLPolicy:230] my-pacl-portlet
        generated authorization property {key=security-manager-properties-read,
        value=line.separator}

4.  Lastly, merge the properties that the security manager wrote (i.e., your
    newly generated PACL policy file
    `[liferay.home]/pacl-policy/[servletContextName].policy`) into your plugin's
    `liferay-plugin-package.properties` file. It's just a matter of merging the
    properties that start with the "security-manager-" prefix. 

Now that your plugin has a thoroughly specified list of resources it accesses,
let's enable the security manager and do final testing of your PACL properties.
We cover enabling the security manager in the next section. 

### Test the Plugin with the Security Manager Enabled [](id=test-with-security-manager-enabled-liferay-portal-6-1-dev-guide-en)

If you want to distribute plugins, either through the Liferay Marketplace or
through your web site, you have to assume potential users will insist the
Security Manager is enabled in your plugin. For this reason, you should enable
it when testing your plugins. 

To enable the Security Manger set the following
`liferay-plugin-package.properties` property to true: 

	security-manager-enabled=true

Then, re-deploy your plugin and re-test it's functionality. The Security Manager
throws Java security exceptions, if your plugin accesses resources that are not
specified in your plugin's security policy. As you test, keep track of these
Java security exceptions, so you can authorize access to the respective
resources in the PACL properties of your `liferay-plugin-package.properties`
file. Save your changes to the file, re-deploy the plugin, and re-test. Make
sure everything works. If not, there are more rules you must declare for your
plugin. Refer to the Portal Access Control List Properties section of this
chapter to see the definitions of all the PACL properties and see example
property values. 

If you are not finding an adequate way to specify a security rule with PACL, you
can specify it in a Java Security Policy file. You see, it's almost impossible
for Liferay and PACL to be aware of every possible security implementation
check, because developers, libraries, and the Java Security API can always call
for new types of security checks. So, Liferay provides a fallback to PACL, that
lets you specify operations permissible within the context of your app's
plugins. 

In case you need it for your plugin, let's get familiar with the Java Security
Policy file. 

### Using a Java Security Policy File [](id=java-security-policy-file-liferay-portal-6-1-dev-guide-en)

If you cannot find a way to specify PACL permissions for an operation that your
plugin must access, you can specify the permission in a Java Security Policy
file. You can create policy file (`java.policy`) in your plugin's `WEB-INF`
folder. The policy file must follow Policy File syntax as described in detail at
[http://docs.oracle.com/javase/6/docs/technotes/guides/security/PolicyFiles.html#FileSyntax](http://docs.oracle.com/javase/6/docs/technotes/guides/security/PolicyFiles.html#FileSyntax).
Like the rules you define in your plugin's PACL, the additional rules you define
in your plugin's Java Policy File, `WEB-INF/java.policy`, only apply to that
plugin. Plugins aren't privy to each other's security policies. 

Importantly, the Java policy file should only be used to specify rules Liferay's
PACL property implementation does not already support. You should not specify
any rules in a Java policy file that you can specify in a PACL.

Here's a scenario that calls for using a Java Security Policy:

Java has a security implementation called
[http://docs.oracle.com/javase/6/docs/api/java/net/NetPermission.html](java.net.NetPermission).
It checks a whole bunch of networking operations, that Liferay's implementation
doesn't check. In case you want to perform one of these operations, like using a
custom Stream Handler, you can grant your plugin permission to do so in its
`WEB-INF/java.policy` Java Security Policy file. Here's one way to specify that
rule: 

    grant codeBase "file:${my-supercool-portlet}${/}-" {
        permission java.net.NetPermission "specifyStreamHandler";
    };

This `grant` entry defines permission for the plugin's code to access the
`specifyStreamHandler` target operation of the `java.net.NetPermission` class.
The `codebase` value, in this example, specifies the following:

- `file:` indicates the code resides on the server's file system. 
- `${my-supercool-portlet}` represents the context path of a plugin named "My
  Supercool Portlet". The context path is a system property Liferay generates
  for the plugin. It maps the context path name to the plugin's fully qualified
  deployment path. 
- `${/}` represents the system's path separator. 
- `-` matches files and folders, in this folder and below. 

On reading this plugin's `.jar` file, the JVM creates a codebase for it. The
codebase uses properties that Liferay sets for the plugin that say, in effect,
"If a file originates within the plugin, then this plugin can perform the
`specifyStreamHandler` operation on it". The codebase narrows the scope for the
permission. This plugin is permitted to perform the definited operation,
`specifyStreamHandler`, as long as it is done within the scope the plugin. 

How do you add more permissions to a codebase? Just define them on separate
lines in the grant entry: 

    grant codeBase "file:${my-supercool-portlet}${/}-" {
	    permission java.lang.RuntimePermission "loadLibrary.test_b";
	    permission java.net.NetPermission "specifyStreamHandler";
    };

In this example, we've granted the plugin permission to invoke native code
that's in some library (`test_b.so`). This is another type of operation which
Liferay's PACL does not support. So, it makes sense to specify permission for it
in the Java Security Policy file. 

With Liferay's PACL policy and Java Security Policy files, you can precisely
specify all of the resources your plugin needs to access! Next, let's revisit
the file path values that the PACL Policy Generation Tool wrote to your
`liferay-plugin-package.properties` file. 

### Convert PACL Absolute File Paths into Relative Paths [](id=convert-pacl-file-paths-to-relative-paths-liferay-portal-6-1-dev-guide-en)

As mentioned earlier in this chapter, we recommend using the PACL generation
tool to give you a head start on specifying your plugin's security rules. But
The generator is only aware of file paths with respect to the current system,
and therefore generates them as absolute file paths. In order to use your
security policy in production, it must use only relative file paths. So, as a
final step after testing the generated PACL, you must massage the generated file
paths into appropriate relative file paths. For example, you can specify paths
relative to your Liferay web portal directory:

    security-manager-files-read=\
        ${liferay.web.portal.dir}/WEB-INF/tld/-,\
        ${liferay.web.portal.dir}/html/themes/-

In this example, we used a dash (`-`) character at the end of the paths. We use
this as a wildcard character. Oracle defines wildcards for for use with Java
Security, and Liferay provides some too. Let's consider some helpful wildcards
you can use in PACL properties and Java Security policies. 

For files and file paths, you can leverage the following wildcard characters:

- Dash (`-`) matches everything in the current folder and below, like you might
  expect with the normal GLOB operation in UNIX. The current folder isn't
  included in the match.
- Star (`*`) matches every file (*not* folder) in the current folder. The
  current folder and subfolders are excluded from the match.

Let's say you want to match all of your theme files and folders, specify ...

this:

    security-manager-files-read=\
        ${liferay.web.portal.dir}/html/themes/-

NOT this:

    security-manager-files-read=\
        ${liferay.web.portal.dir}/html/themes/*

The star means "every file in this single directory." The dash, however, matches
everything in this folder and below. 

One more note. This: 
                                            
    ${liferay.web.portal.dir}/html/themes/-

does not include this: 

    ${liferay.web.portal.dir}/html/themes

The dash lets you read the *contents* of the folder, but not the folder itself.
Also, when defining the folder, do not include a trailing slash, otherwise
the folder itself will not be included. Below, we specify the `themes` folder
and all of the contents under it: 

    security-manager-files-read=\
        ${liferay.web.portal.dir}/html/themes,\
        ${liferay.web.portal.dir}/html/themes/-

For file path separators, you can use the `${/}` alias.

Example,

    grant codeBase "file:${my-supercool-portlet}${/}-" {
    	permission java.net.NetPermission "specifyStreamHandler";
    };

Congratulations! You now know how to specify your policy's file paths
appropriately for deployment on any server. Once you've completed testing your
plugin without getting any Java security exceptions, you can distribute it as an
app on Liferay Marketplace. You can do so with confidence, because you've
specified all of the resources it uses in the application's PACL, and possibly
its Java Security Policy, and your application satisfies Liferay Portal's
Security Manager. 

The sections that follow demonstrated how to enable the Security Manager (which
you've already done) and provide descriptions for each type of PACL property. 

## Enabling the Security Manager [](id=lp-6-1-dgen11-enabling-the-security-manager-0)

If you want to distribute plugins, either on the Liferay Marketplace or through
your web site, you have to assume users will insist the Security Manager is
enabled in your plugin. For this reason, you should enable it when testing your
plugins and on packaging it for distribution. 

It's very easy to activate the security manager. Set the following
`liferay-plugin-package.properties` property to true: 

	security-manager-enabled=true

Next, we'll look at exactly what APIs the Security Manager protects, and how you
can declare whether your application uses any of these properties. 

## Portal Access Control List (PACL) Properties [](id=lp-6-1-dgen11-portal-access-control-list-pacl-properties-0)

Liferay Portal's Plugin Security Manager checks all your plugin's API access
attempts against the security manager properties specified in your plugin's
`liferay-plugin-package.properties` file. If your plugin tries to access a
portal resource that is not specified in these properties, the Plugin Security
Manager prevents it from happening. Consider this a virtual finger waggin'. To
prevent this from happening, you have to tell the Plugin Security Manager
up-front the access your plugin needs.

The sections that follow describe the PACL properties: explaining each
property's purpose, its possible values, and the syntax to use in specifying its
value.

## AWT Security [](id=pacl-awt-security-liferay-portal-6-1-dev-guide-en)

Specify the AWT operations the plugin is permitted to access. 

*Example:*

    security-manager-awt-operations=\
        accessClipboard,\
        accessEventQueue,\
        accessSystemTray,\
        createRobot,\
        fullScreenExclusive,\
        listenToAllAWTEvents,\
        readDisplayPixels,\
        replaceKeyboardFocusManager,\
        setAppletStub,\
        setWindowAlwaysOnTop,\
        showWindowWithoutWarningBanner,\
        toolkitModality,\
        watchMousePointer

## Class Loader Security [](id=lp-6-1-dgen11-class-loader-security-0)

Specify the reference IDs of plugins for this plugin to access.

*Example:*

	security-manager-class-loader-reference-ids=\
		1_WAR_flashportlet,\
		flash-portlet

## Environment Variable Security [](id=pacl-environment-variable-security-liferay-portal-6-1-dev-guide-en)

Specify regular expression patterns used to match environment variables
that the plugin is permitted to access. 

*Example:*

    security-manager-environment-variables=\
        java.home,\
        java.vendor,\
        java.version,\
        java.vm.v.*

## Expando Bridge Security [](id=lp-6-1-dgen11-expando-bridge-0)

Specify models having Expando Bridge attributes the plugin is permitted to
access. The plugin can also access Expando Bridge attributes via the
wrapper classes of the models. 

*Example:*

	security-manager-expando-bridge=\
        com.liferay.portal.model.User

## File Security [](id=lp-6-1-dgen11-file-security-0)

The following properties address file deletion, execution, reading, writing and
replacement operations. The `*` character in a path name indicates all files in
the current directory. The `-` character in a path name indicates all files in
the current directory and in its subdirectories.

Specify files the plugin is permitted to delete.

*Example:*

	security-manager-files-delete=\
		../webapps/chat-portlet/WEB-INF/src/com/liferay/chat/temp/-

Specify files the plugin is permitted to execute.

*Example:*

	security-manager-files-execute=\
		/bin/bash,\
		C:\\WINDOWS\\system32\\ping.exe

Specify files the plugin is permitted to read.

*Example:*

	security-manager-files-read=\
		../webapps/chat-portlet/images/*,\
		../webapps/chat-portlet/WEB-INF/liferay-releng.properties,\
		../webapps/chat-portlet/WEB-INF/src/-,\
		/bin/bash,\
		C:\\WINDOWS\\system32\\ping.exe

Specify files the plugin is permitted to overwrite.

*Example:*

	security-manager-files-write=\
		../webapps/chat-portlet/images/*,\
		../webapps/chat-portlet/WEB-INF/*,\
		../webapps/chat-portlet/WEB-INF/src/com/liferay/chat/util/ChatUtil.java

## Bean Security [](id=lp-6-1-dgen11-bean-property-security-0)

Specify bean properties the plugin is permitted to acquire. 

*Example:*

    security-manager-get-bean-property=\
        com.liferay.portal.kernel.xml.SAXReaderUtil,\
        com.liferay.portal.util.PortalUtil

Specify bean properties the plugin is permitted to set. 

*Example:*

    security-manager-set-bean-property=\
        com.liferay.portal.kernel.dao.orm.PortalCustomSQLUtil

## Hook Security [](id=lp-6-1-dgen11-hook-security-0)

Set to `true` if the hook plugin is permitted use custom JSPs. By default, the
hook plugin is not permitted to use custom JSPs. 

*Example:*

	security-manager-hook-custom-jsp-dir-enabled=false

Specify entities for which the hook plugin is permitted to customize indexing.
Customizing the indexing can involve modifying the search, summary and/or
queries for these entities. 

*Example:*

	security-manager-hook-indexers=\
		com.liferay.portal.model.User

Specify which language property locales the plugin is permitted to override. 

*Example:*

This example grants the plugin permission to override the Great Britain locale
of English, the Spain locale of Spanish, and *all* Portuguese locales. 

	security-manager-hook-language-properties-locales=\
		en_GB,\
		es_ES,\
		pt

Specify which portal properties the plugin is permitted to override. 

*Example:*

	security-manager-hook-portal-properties-keys=\
		phone.number.format.impl

Specify which services the plugin is permitted to access. 

*Example:*

	security-manager-hook-services=\
		com.liferay.portlet.blogs.service.BlogsEntryLocalService

Specify whether to allow the plugin's servlet filter hooks. Setting this to
`true` gives the plugin permission to pre-process requests going to the portal's
servlets and post-process requests coming from the portal's servlets. By
default, the plugin's servlet filter hooks are not allowed. 

	security-manager-hook-servlet-filters-enabled=false

Specify struts action paths that the hook plugin is permitted to add or
override. 

*Example:*

	security-manager-hook-struts-action-paths=\
		/portal/test/pacl/success

## JNDI Security [](id=lp-6-1-dgen11-jndi-security-0)

Specify which services the plugin can look up. You can use regular expressions
to make this dynamic. 

*Example:*

Using the sample values below, the plugin can look up objects for key names
`matthew`, `mark`, `Mark`, `luke`, and `Luke`. In addition, the plugin can
look-up objects for key names containing `john` with zero or more characters
preceding and/or trailing `john`. 

	security-manager-jndi-names=\
		test-pacl-matthew,\
		test-pacl-[mM]ark|test-pacl-[lL]uke,\
		test-pacl-.*john.*

## Message Bus Security [](id=lp-6-1-dgen11-message-bus-security-0)

Specify which services the plugin is permitted to listen on via the portal's
message bus.

*Example:*

	security-manager-message-bus-listen=\
		liferay/test_pacl,\
		liferay/test_pacl_listen_success

Specify which message bus destinations the plugin is permitted to call (send
messages to) via the portal's message bus.

*Example:*

	security-manager-message-bus-send=\
		liferay/message_bus/default_response,\
		liferay/test_pacl_listen_failure,\
		liferay/test_pacl_listen_success,\
		liferay/test_pacl_send_success

## Portlet Bag Pool Security [](id=pacl-portlet-bag-pool-security-liferay-portal-6-1-dev-guide-en)

Specify regular expression patterns used to match any portlet IDs that the
plugin is permitted to access from the portlet bag pool. 

*Example:*

    security-manager-portlet-bag-pool-portlet-ids=\
        1_WAR_flashportlet,\
        .*_WAR_chatportlet

## Search Engine Security [](id=lp-6-1-dgen11-search-engine-ids-0)

Specify the IDs of search engines the plugin is permitted to access. 

*Example:*

	security-manager-search-engine-ids=\
		SYSTEM_ENGINE

## Portal Service Security [](id=lp-6-1-dgen11-portal-service-security-0)

Specify portal service classes and/or methods the plugin is permitted to access.
Use the `#` character as a delimiter between a class and its method. 

*Example:*

	security-manager-services[portal]=\
		com.liferay.portal.service.GroupLocalService,\
		com.liferay.portal.service.PortalService#getBuildNumber,\
		com.liferay.portal.service.persistence.CompanyPersistence,\
		com.liferay.portlet.blogs.service.BlogsEntryLocalService,\
		com.liferay.portlet.blogs.service.BlogsStatsUserLocalService

## Portlet Service Security [](id=lp-6-1-dgen11-portlet-service-security-0)

For each portlet the plugin accesses, replicate this property substituting
`some-portlet` in the `[` square brackets `]` with the name of the accessible
portlet. 

Specify portlet service classes and/or methods the plugin is permitted to
access. Use the `#` character as a delimiter between a class and its method. 

*Example:*

	security-manager-services[some-portlet]=\
		com.liferay.chat.service.EntryLocalService,\
		com.liferay.chat.service.StatusLocalService#getStatuses

## Socket Security [](id=lp-6-1-dgen11-socket-security-0)

Specify sockets permitted to accept connections in the plugin.

*Example:*

	security-manager-sockets-accept=\
		localhost:4320

Specify connections the plugin is permitted to make with the outside world.

*Example:*

	security-manager-sockets-connect=\
		www.cbs.com:80,\
		www.google.com:80,\
		www.google.com:443,\
		www.yahoo.com:443

Specify sockets the plugin can listen on.

*Example:*

This example specifies socket range `4316-4318`, socket `4320`, and socket
`4321`.

	security-manager-sockets-listen=\
		4316-4318,\
		4320,\
		4321

*The Star Socket Wildcard:*

The star (`*`) represent any hostname. Here are examples that demonstrate its
use: 

- `*.liferay.com` matches any host ending in `.liferay.com`, such as
`docs.liferay.com` and `issues.liferay.com`. 

- `*:*` matches every socket and every port. 

## SQL Security [](id=lp-6-1-dgen11-sql-security-0)

Specify tables in the Liferay database on which the plugin is permitted to
perform the applicable operations. These property names use the following
convention:

	security-manager-sql-tables-[operation]    
 
Specify tables on which the plugin can do any and all operations.

*Example:*

	security-manager-sql-tables-all=\
		TestPACL_Foo

Specify tables the plugin can create.

*Example:*

	security-manager-sql-tables-create=\
		TestPACL_CreateSuccess,\
		TestPACL_DropSuccess,\
		TestPACL_InsertSuccess,\
		TestPACL_ReplaceSuccess,\
		TestPACL_TruncateSuccess

Specify tables from which the plugin can delete records.

*Example:*

	security-manager-sql-tables-delete=\
		ClassName_

Specify tables the plugin can drop.

*Example:*

	security-manager-sql-tables-drop=\
		TestPACL_CreateSuccess,\
		TestPACL_DropSuccess,\
		TestPACL_InsertSuccess,\
		TestPACL_ReplaceSuccess,\
		TestPACL_TruncateSuccess

Specify tables into which the plugin can insert records.

*Example:*

	security-manager-sql-tables-insert=\
		TestPACL_InsertSuccess

Specify tables in which the plugin can replace records.

*Example:*

	security-manager-sql-tables-replace=\
		TestPACL_ReplaceSuccess

Specify tables the plugin can query.

*Example:*

	security-manager-sql-tables-select=\
		Counter,\
		TestPACL_Bar

Specify tables from which the plugin can delete all records.

*Example:*

	security-manager-sql-tables-truncate=\
		TestPACL_TruncateSuccess

Specify tables the plugin can update.

*Example:*

	security-manager-sql-tables-update=\
		ListType

## Thread Security [](id=lp-6-1-dgen11-thread-security-0)

Specify regular expression patterns used to match names of the thread pool
executor for the plugin to access.

*Example:*

	security-manager-thread-pool-executor-names=\
		liferay/test_pacl,\
		liferay/test_pacl_listen_failure,\
		liferay/test_pacl_listen_success

<!-- TODO insert section(s) explaining security with respect to core development
-->

<!--Right now summaries from both chapters (plugins security was its own
chapter previously) are left here. They should be combined and the chapter
rewqorked for flow. --> 

## Summary [](id=lp-6-1-dgen11-summary-0)

In this chapter, we've discussed the reasons for plugin security management, how
the Plugin Security Manager checks each plugin against its portal access control
list (PACL), and how to specify PACL properties for the plugins you create and
deploy. We also explained Liferay's support of the Java
Security Policy, in case you need to specify rules above and beyond what PACL
properties support. 

Now you have a better understanding of how plugin security works, and can use
Liferay Portal's Plugin Security Manager effectively to specify exactly what
services your plugin needs in order to function. Anyone running Liferay Portal
with Security Manager turned on will know you're a "law abiding" citizen,
because you've specified what services your applications need to access in order
to function.

Next, we'll show you what it takes to develop apps for publishing to
*Liferay Marketplace*.

