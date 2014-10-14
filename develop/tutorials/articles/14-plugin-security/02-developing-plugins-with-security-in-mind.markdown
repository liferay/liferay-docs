# Developing Plugins with security in mind [](id=developing-plugins-with-security-in-mind)

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

## Consider Common Security Pitfalls [](id=consider-common-security-pitfalls)

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

## Develop your plugin [](id=develop-your-plugin)

Start creating your plugin the way you normally would. Design your application,
write code, unit test your code, have users beta test your code. In essence, do
everything you normally would do. Do all of this with the Plugin Security
Manager disabled via your plugin's `liferay-plugin-package.properties` file:  

	security-manager-enabled=false

Before the Plugin Security Manager is enabled, you must specify the resources
your plugin accesses. Let's build a list of these resources in your plugin's
PACL. 

## Build Your Plugin's PACL [](id=build-your-plugins-pacl)

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

## Test the Plugin with the Security Manager Enabled [](id=test-the-plugin-with-the-security-manager-enabled)

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

## Using a Java Security Policy File [](id=using-a-java-security-policy-file)

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

## Convert PACL Absolute File Paths into Relative Paths [](id=convert-pacl-absolute-file-paths-into-relative-paths)

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
