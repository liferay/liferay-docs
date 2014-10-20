# Building and Testing Your Plugin's PACL [](id=building-and-testing-your-plugins-pacl)

Liferay's Plugin Security Manager requires that a plugin specify in advance the 
portal resources that it intends to access. If a plugin tries to access 
something it hasn't told the Security Manager about, the Security Manager puts a 
stop to it. A plugin's Portal Access Control List (PACL) is its way of telling 
the Security Manager what it intends to access. 

This tutorial shows you how to build and test your plugin's PACL through the 
following steps:

- Develop Your Plugin
- Build Your Plugin's PACL
- Test Your Plugin with the Security Manager Enabled
- Use a Java Security Policy File, if Necessary
- Convert PACL Absolute File Paths into Relative Paths

Lastly, this tutorial explains some of the subtle details about the PACL
properties. 

Now go ahead and get started--you don't want to run afoul of the Security 
Manager!

## Develop Your Plugin [](id=develop-your-plugin)

Start by creating your plugin the way you normally do. Design your application,
write the code, unit test your code, and have users beta test your app. In 
essence, do everything you normally do. Do all of this with the Plugin Security
Manager disabled via your plugin's `liferay-plugin-package.properties` file:

    security-manager-enabled=false

Before the Plugin Security Manager is enabled, you must specify the resources
your plugin accesses. You do this by building a list of these resources in your 
plugin's PACL. 

## Build Your Plugin's PACL [](id=build-your-plugins-pacl)

Rather than tediously figuring out all of the resources your plugin accesses on
your own, let Liferay's PACL Policy Generation tool to give you a head start!
The generation tool detects the resources your plugin accesses and writes the 
corresponding PACL properties to a policy file. You can then merge the PACL
properties from this policy file into your plugin's 
`liferay-plugin-package.properties` file. 
<!-- Change the link in step 1 to the dev site user guide once it's back up. -->

Here's how to generate a PACL policy for your plugin: 

1.  Make sure your Liferay Portal instance has `liferay` set as its security
    manager strategy value and that the security manager was activated during
    application server startup. 
    
    To set your portal's security manager strategy value to `liferay`, simply 
    specify the following in your `portal-ext.properties` file:

        portal.security.manager.strategy=liferay

    Your app server may require certain startup arguments to be used for
    activiting the security manager. Check the PACL and security manager
    instructions for your app server in the
    [Installation and Setup](/discover/portal/-/knowledge_base/6-2/installation-and-setup)
    chapter of *Using Liferay Portal*. Some app servers, like Tomcat, output
    a terminal message like "Using Security Manager", indicating that it's
    using the security manager. 

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

    Liferay Portal's Security Manager performs security checks on your plugin at 
    deployment time and as you exercise your plugin's features. Rather than 
    throwing errors on failed checks, the generator tool writes suggested rules 
    that specify access to the resources your plugin accesses. 

    Unless you've turned off logging for the generator tool, messages like the
    ones below are logged, reporting the various authorization properties that
    the tool generated:

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

+$$$

**Note:** There is a known issue
 [LPS-41716](https://issues.liferay.com/browse/LPS-41716) in which Liferay may
 need to be restarted after deploying your plugin, in order for the security
 manager to detect and write out the complete set of policies for a plugin. If
 you are using your plugin with the "security-manager-" generated properties the
 first time and notice security violations, then you may need to turn on policy
 generation one more time and restart Liferay. This gives the security manager
 another opportunity to detect additional properties to satisfy your security
 policy. If you are still seeing security violations on deployment, you'll need
 to address them per instructions that follow in this tutorial. Here are the
 work-around steps: Remove the previously generated
 `[servletContextName].policy` file, set `security-manager-enabled=generate` in
 your `liferay-plugin-package.properties` file, restart Liferay, redeploy your
 plugin, and merge any new properties from the newly generated
 `[servletContextName].policy` file into your
 `liferay-plugin-package.properties` file. 
 
$$$

Now that you've thoroughly specified the resources your plugin accesses, you can 
enable the security manager and do final testing of your PACL properties. 

## Test Your Plugin with the Security Manager Enabled [](id=test-your-plugin-with-the-security-manager-enabled)

If you want to distribute plugins, either through the Liferay Marketplace or 
your web site, you have to assume potential users will insist that the Security 
Manager be enabled in your plugin. For this reason, you should enable it when 
testing your plugins. 

To enable the Security Manger set the following 
`liferay-plugin-package.properties` property to true: 

    security-manager-enabled=true

Then re-deploy your plugin and re-test its functionality. The Security Manager 
throws Java security exceptions if your plugin accesses resources that are not
specified in your plugin's security policy. As you test, keep track of these
Java security exceptions so you can authorize access to the respective resources 
in the PACL properties of your `liferay-plugin-package.properties` file. Save 
your changes to the file, re-deploy the plugin, and re-test. Make sure 
everything works. If it doesn't, there are more rules you must declare for your 
plugin. For additional details, refer to the online definition of the Portal 
Access Control List Properties for the [liferay-plugin-package.properties](http://docs.liferay.com/portal/6.2/propertiesdoc/liferay-plugin-package_6_2_0.properties.html)
file and refer to the PACL properties section at the end of this tutorial. 

If you're not finding an adequate way to specify a security rule with PACL, you
can specify it in a Java Security Policy file. It's almost impossible for
Liferay and PACL to be aware of every possible security implementation check,
because developers, libraries, and the Java Security API can always call for new
types of security checks. Liferay, therefore, provides a Java Security Policy
file as fallback to PACL . The policy file lets you specify operations
permissible within the context of your app's plugins. 

In case you need it for your plugin, go ahead and get familiar with the Java 
Security Policy file. 

## Use a Java Security Policy File, if Necessary [](id=use-a-java-security-policy-file-if-necessary)

If you can't find a way to specify PACL permissions for an operation that your
plugin must access, you can specify the permission in a Java Security Policy
file. You can create the policy file (`java.policy`) in your plugin's `WEB-INF`
folder. The policy file must follow Policy File syntax as described in detail at
[http://docs.oracle.com/javase/7/docs/technotes/guides/security/PolicyFiles.html#FileSyntax](http://docs.oracle.com/javase/7/docs/technotes/guides/security/PolicyFiles.html#FileSyntax).
Like the rules you define in your plugin's PACL, the additional rules you define
in your plugin's Java Policy File, `WEB-INF/java.policy`, only apply to that
plugin. Plugins aren't privy to each other's security policies. 

Importantly, the Java policy file should only be used to specify rules Liferay's
PACL property implementation does not already support. You should not specify
any rules in a Java policy file that you can specify in a PACL.

Here's a scenario that calls for using a Java Security Policy:

Java has a security implementation called
[http://docs.oracle.com/javase/7/docs/api/java/net/NetPermission.html](java.net.NetPermission).
It checks a whole bunch of networking operations that Liferay's implementation
doesn't check. In case you want to perform one of these operations, like using a
custom Stream Handler, you can grant your plugin permission to do so in its
`WEB-INF/java.policy` Java Security Policy file. Here's one way to specify that
rule: 

    grant codeBase "file:${my-supercool-portlet}${/}-" {
        permission java.net.NetPermission "specifyStreamHandler";
    };

This `grant` entry defines permission for the plugin's code to access the
`specifyStreamHandler` target operation of the `java.net.NetPermission` class.
The `codeBase` value, in this example, specifies the following:

- `file:` indicates the code resides on the server's file system. 
- `${my-supercool-portlet}` represents the context path of a plugin named "My
  Supercool Portlet". The context path is a system property Liferay generates
  for the plugin. It maps the context path name to the plugin's fully qualified
  deployment path. 
- `${/}` represents the system's path separator. 
- `-` matches files and folders, in this folder and below. 

On reading this plugin's `.jar` file, the JVM creates a codebase for it. The
codebase uses properties that Liferay sets for the plugin that in effect say,
"If a file originates within the plugin, then this plugin can perform the
`specifyStreamHandler` operation on it." The codebase narrows the scope for the
permission. This plugin is permitted to perform the defined operation 
`specifyStreamHandler`, as long as it is done within the scope the plugin. 

How do you add more permissions to a codebase? Just define them on separate
lines in the grant entry: 

    grant codeBase "file:${my-supercool-portlet}${/}-" {
        permission java.lang.RuntimePermission "loadLibrary.test_b";
        permission java.net.NetPermission "specifyStreamHandler";
    };

In this example, the plugin is granted permission to invoke native code that's
in some library (`test_b.so`). This is another type of operation that Liferay's
PACL does not support. It, therefore, makes sense to specify permission for it
in the Java Security Policy file. 

With Liferay's PACL policy and Java Security Policy files, you can precisely
specify all of the resources your plugin needs to access! Now it's time to 
revisit the file path values that the PACL Policy Generation Tool wrote to your
`liferay-plugin-package.properties` file. 

## Convert PACL Absolute File Paths into Relative Paths [](id=convert-pacl-absolute-file-paths-into-relative-paths)

As mentioned earlier in this tutorial, using the PACL generation tool to give
you a head start on specifying your plugin's security rules is recommended. The
generator, however, is only aware of file paths with respect to the current
system. The file paths it genereates are absolute paths. To use your security
policy in production, the policy should use only relative file paths. As a final
step after testing the generated PACL, you must massage the generated file paths
into the appropriate relative file paths. For example, you can specify paths
relative to your Liferay web portal directory:

    security-manager-files-read=\
        ${liferay.web.portal.dir}/WEB-INF/tld/-,\
        ${liferay.web.portal.dir}/html/themes/-

This example uses a dash (`-`) character at the end of the paths. It's used as 
a wildcard character. Oracle defines wildcards to use with Java Security, 
and Liferay provides some too. You can leverage the following wildcard 
characters for files and file paths:

- Dash (`-`) matches everything in the current directory and below, like you 
  might expect with the normal GLOB operation in UNIX. The current directory is
  excluded from the match. 
- Star (`*`) matches every file (*not* directory) in the current directory. The
  current directory and subdirectories are excluded from the match.

For example, if you want to match all of your theme files and directories, 
specify...

this:

    security-manager-files-read=\
        ${liferay.web.portal.dir}/html/themes/-

NOT this:

    security-manager-files-read=\
        ${liferay.web.portal.dir}/html/themes/*

The star means "every file in this single directory." The dash, however, matches
everything in this directory and below. 

One more note. This: 

    ${liferay.web.portal.dir}/html/themes/-

does not include this: 

    ${liferay.web.portal.dir}/html/themes

The dash lets you read the *contents* of the directory, but not the directory 
itself. Also, when defining the directory don't include a trailing 
slash--otherwise the directory itself won't be included. For example, this 
specifies the `themes` directory and all its contents: 

    security-manager-files-read=\
        ${liferay.web.portal.dir}/html/themes,\
        ${liferay.web.portal.dir}/html/themes/-

For file path separators, you can use the `${/}` alias.

For example:

    grant codeBase "file:${my-supercool-portlet}${/}-" {
        permission java.net.NetPermission "specifyStreamHandler";
    };

Congratulations! You now know how to specify your policy's file paths
appropriately for deployment on any server. Once you've completed testing your
plugin without getting any Java security exceptions, you can distribute it as an
app on Liferay Marketplace. You can do so with confidence because you've
specified all of the resources it uses in the application's PACL, and possibly
its Java Security Policy. Your application satisfies Liferay Portal's Security
Manager. 

The next section provides some additional details regarding PACL properties. 

## Portal Access Control List (PACL) Details [](id=portal-access-control-list-pacl-details)

Liferay Portal's Plugin Security Manager checks all your plugin's API access
attempts against the Security Manager properties specified in your plugin's
`liferay-plugin-package.properties` file. If your plugin tries to access a
portal resource that is not specified in these properties, the Plugin Security
Manager prevents it from happening. Consider this a virtual finger waggin'. To
prevent this from happening, you have to tell the Plugin Security Manager
up-front the access your plugin needs.

The online definitions for the PACL properties can be found at
[http://docs.liferay.com/portal/6.2/propertiesdoc/liferay-plugin-package_6_2_0.properties.html](http://docs.liferay.com/portal/6.2/propertiesdoc/liferay-plugin-package_6_2_0.properties.html).
If you have the Liferay Portal source code, you can find the
`liferay-plugin-package_6_2_0.properties` file in the
`liferay-portal/definitions` folder. 

Some of the properties accept wildcard characters that have special meaning. The 
following properties address file deletion, execution, reading, writing, and
replacement operations.

- The `*` character in a path name indicates all files in the current directory.
- The `-` character in a path name indicates all files in the current directory 
  and in its subdirectories.

Here's an example that uses the `-` character to specify that the plugin is
permitted to delete files in the
`../webapps/chat-portlet/WEB-INF/src/com/liferay/chat/temp` directory and its
subdirectories:

    security-manager-files-delete=\
        ../webapps/chat-portlet/WEB-INF/src/com/liferay/chat/temp/-

Note that you can use relative paths in the file security properties. 

You can also use a mix of UNIX/Linux and Windows style paths, as demonstrated 
here: 

    security-manager-files-execute=\
        /bin/bash,\
        C:\\WINDOWS\\system32\\ping.exe

The following example uses the `*` character to specify that the plugin reads 
files in the `../webapps/chat-portlet/images` and
`../webapps/chat-portlet/WEB-INF/*` directories, but not in their 
subdirectories:

    security-manager-files-write=\
        ../webapps/chat-portlet/images/*,\
        ../webapps/chat-portlet/WEB-INF/*,\
        ../webapps/chat-portlet/WEB-INF/src/com/liferay/chat/util/ChatUtil.java

For socket security properties, the `*` character represents any hostname. For
example, `*.liferay.com` matches any host ending in `.liferay.com`, such as
`docs.liferay.com` and `issues.liferay.com`. In addition, `*:*` matches every 
socket and every port. 

<!-- TODO insert section(s) explaining security with respect to core development
-->

Awesome! Now you know how to build and test your plugin's PACL. You also know 
how to use a Java Security Policy file in cases where the PACL just isn't 
cutting it. Armed with this knowledge, you can confidently build, test, and 
deploy plugins that don't end up on the wrong side of Liferay's Plugin Security 
Manager.

## Related Topics [](id=related-topics)

[Setting Permissions](/develop/learning-paths/-/knowledge_base/6-2/setting-permissions)

[Developing with the Plugins SDK](/develop/tutorials/-/knowledge_base/6-2/plugins-sdk)

[Developing Plugins with Liferay IDE](/develop/tutorials/-/knowledge_base/6-2/liferay-ide)

[Developing with Maven](/develop/tutorials/-/knowledge_base/6-2/maven)

[MVC Portlets](/develop/tutorials/-/knowledge_base/6-2/developing-jsp-portlets-using-liferay-mvc)

[Liferay Faces](/develop/tutorials/-/knowledge_base/6-2/liferay-faces-jsf-portlets)
