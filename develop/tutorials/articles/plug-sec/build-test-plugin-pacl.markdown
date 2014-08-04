### Develop Your Plugin [](id=develop-your-plugin-for-security-liferay-portal-6-2-dev-guide-en)

Start creating your plugin the way you normally would. Design your application,
write code, unit test your code, and have users beta test your app. In essence,
do everything you would normally do. Do all of this with the Plugin Security
Manager disabled via your plugin's `liferay-plugin-package.properties` file:

    security-manager-enabled=false

Before the Plugin Security Manager is enabled, you must specify the resources
your plugin accesses. Let's build a list of these resources in your plugin's
PACL. 

### Build Your Plugin's PACL [](id=build-your-plugins-pacl-liferay-portal-6-2-dev-guide-11-en)

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
    activiting the security manager. Check the PACL and security manager
    instructions for your app server in the [Installation and
    Setup](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/installation-and-setup-liferay-portal-6-2-user-guide-15-en)
    chapter of *Using Liferay Portal 6.2*. Some app servers, like Tomcat, output
    a terminal message, like "Using Security Manager", indicating that it's
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

----

![Note](../../images/tip-pen-paper.png) **Note:** There is a known issue
 [LPS-41716](https://issues.liferay.com/browse/LPS-41716) in which Liferay may
 need to be restarted after deploying your plugin, in order for the security
 manager to detect and write out the complete set of policies for a plugin. If
 you are using your plugin with the "security-manager-" generated properties the
 first time and notice security violations, then you may need to turn on policy
 generation one more time and restart Liferay. This gives the security manager
 another opportunity to detect additional properties to satisfy your security
 policy. If you are still seeing security violations on deployment, you'll need
 to address them per instructions that follow in this chapter. Here are the
 work-around steps: Remove the previously generated
 `[servletContextName].policy` file, set `security-manager-enabled=generate` in
 your `liferay-plugin-package.properties` file, restart Liferay, redeploy your
 plugin, and merge any new properties from the newly generated
 `[servletContextName].policy` file into your
 `liferay-plugin-package.properties` file. 
 
----

Now that you've thoroughly specified the resources your plugin accesses, let's
enable the security manager and do final testing of your PACL properties. 

### Test the Plugin with the Security Manager Enabled [](id=test-the-plugin-with-security-manager-liferay-portal-6-2-dev-guide-en)

If you want to distribute plugins, either through the Liferay Marketplace or
through your web site, you have to assume potential users will insist the
Security Manager be enabled in your plugin. For this reason, you should enable
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
plugin. Refer to the online definition of the Portal Access Control List
Properties for the `liferay-plugin-package.properties` file at 
[http://docs.liferay.com/portal/6.2/propertiesdoc/liferay-plugin-package_6_2_0.properties.html](http://docs.liferay.com/portal/6.2/propertiesdoc/liferay-plugin-package_6_2_0.properties.html)
and in the PACL properties section of this chapter for additional details. 

If you are not finding an adequate way to specify a security rule with PACL, you
can specify it in a Java Security Policy file. It's almost impossible for
Liferay and PACL to be aware of every possible security implementation check,
because developers, libraries, and the Java Security API can always call for new
types of security checks. So, Liferay provides a fallback to PACL, that lets you
specify operations permissible within the context of your app's plugins. 

In case you need it for your plugin, let's get familiar with the Java Security
Policy file. 

### Using a Java Security Policy File [](id=using-a-java-security-policy-file-liferay-portal-6-2-dev-guide-11-en)

If you cannot find a way to specify PACL permissions for an operation that your
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

### Convert PACL Absolute File Paths into Relative Paths [](id=convert-pacl-file-paths-to-relative-paths-liferay-portal-6-2-dev-guide-en)

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

## Portal Access Control List (PACL) Properties [](id=portal-access-control-list-pacl-properties-liferay-portal-6-2-dev-guide-en)

Liferay Portal's Plugin Security Manager checks all your plugin's API access
attempts against the security manager properties specified in your plugin's
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

Some of the properties accept wildcard characters that have special meaning.
Let's investigate the wildcard characters you can use in your plugin's file
security properties. 

The following properties address file deletion, execution, reading, writing and
replacement operations. The `*` character in a path name indicates all files in
the current directory. The `-` character in a path name indicates all files in
the current directory and in its subdirectories.

Here's an example that uses the `-` character to specify that the plugin is
permitted to delete files in the
`../webapps/chat-portlet/WEB-INF/src/com/liferay/chat/temp` directory and its
subdirectories. 

    security-manager-files-delete=\
        ../webapps/chat-portlet/WEB-INF/src/com/liferay/chat/temp/-

Note, you can use a relative paths in the file security
properties. 

You can use a mix of UNIX/Linux style paths and Windows style paths as
demonstrated in the example below: 

    security-manager-files-execute=\
        /bin/bash,\
        C:\\WINDOWS\\system32\\ping.exe

And the following example uses the `*` character to specify that the plugin is
reads files in the `../webapps/chat-portlet/images` and
`../webapps/chat-portlet/WEB-INF/*` directories, but not their subdirectories:

    security-manager-files-write=\
        ../webapps/chat-portlet/images/*,\
        ../webapps/chat-portlet/WEB-INF/*,\
        ../webapps/chat-portlet/WEB-INF/src/com/liferay/chat/util/ChatUtil.java

For socket security properties the `*` character represents any hostname. For
example, `*.liferay.com` matches any host ending in `.liferay.com`, such as
`docs.liferay.com` and `issues.liferay.com`. And `*:*` matches every socket and
every port. 

<!-- TODO insert section(s) explaining security with respect to core development
-->
