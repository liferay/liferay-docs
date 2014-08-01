## Understanding Plugin Security Management [](id=understanding-plugin-security-management-liferay-portal-6-2-dev-guide-11-en)

We all wish cyberspace was free of malicious software and unwanted bugs. Since
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
  however, was written by an outside firm, and you must assure that the plugins
  will not tamper with your proprietary files. 

These are just a few scenarios that may ring true for you. When you're
responsible for keeping your system running well 24x7, you can't be too cautious
in protecting your portal, system, and network.

When the Plugin Security Manager is enabled for your plugin, it checks your
plugin's *Portal Access Control List (PACL)*. This list describes what APIs the
plugin accesses, so people deploying the plugin can review what it does without
seeing its source code. If the plugin tries to access anything that's not on
this list, the plugin's request is stopped dead in its tracks and the security
manager logs information on the plugin's attempt to access the unauthorized APIs
or resources. 

Access to APIs and resources is authorized by means of property values specified
in the plugin's `liferay-plugin-package.properties` file. This file must be
specified in your plugin's `WEB-INF` directory. These security management
properties are collectively known as the plugin's PACL. 

As you develop plugins for Liferay Marketplace or for distribution within your
organization, you'll need to set the security management properties
appropriately. Before we dive into the intricacies of these properties, let's
consider a plugin development approach that involves designing an app for the
security manager from the ground up. 

## Developing Plugins with Security in Mind [](id=developing-plugins-with-security-in-mind-liferay-portal-6-2-dev-guide-11-en)

At the start of plugin development, you may not have a clear picture of all the
aspects of the portal you'll need to access, and that's fine. In fact, we
suggest you go ahead and develop your plugin first and address your plugin's
Portal Access Control List (PACL) later. But, as you develop your plugin there
are some common security pitfalls, highlighted in the next section, that you'll
want to avoid. After you develop your plugin you'll dig whole-heartedly
into security management by generating and fine-tuning your plugin's PACL. Don't
worry, we'll guide you through the entire process. 

If you're developing a plugin as part of a free app, writing the plugin's PACL
and enabling the security manager for the plugin are optional, and you can skip
the remainder of this chapter. Otherwise, read on. 

Here is the suggested approach for developing secure plugins: 

- Consider common security pitfalls.
- Develop your plugin. 
- Build your plugin's PACL using Liferay's PACL Policy Generation tool. 
- Test your plugin thoroughly, with the security manager enabled. 
- Add to your plugin's security policy, as needed.
- Convert your policy's absolute file paths to relative paths. 

Let's go over each part of this approach. 

## Enabling the Security Manager [](id=enabling-the-security-manager-liferay-portal-6-2-dev-guide-11-en)

If you want to distribute plugins, either on the Liferay Marketplace or through
your web site, you have to assume users will insist the Security Manager is
enabled in your plugin. For this reason, you should enable it when testing your
plugins and on packaging it for distribution. 

It's very easy to activate the security manager. Set the following
`liferay-plugin-package.properties` property to true: 

    security-manager-enabled=true

Next, we'll explain the purpose of the PACL properties, show you some of the
wildcards you can use for particular property values, and refer you to the file
containing the PACL property definitions. 
