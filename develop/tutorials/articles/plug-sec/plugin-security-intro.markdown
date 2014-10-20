# Plugin Security and PACL [](id=plugin-security-and-pacl)

Everyone wishes that cyberspace were free of malicious software and unwanted 
bugs. Since it isn't, you need to guard yourself and your portal from these 
evils. Liferay Portal's Plugin Security Manager to the rescue! It's like a 
super-hero in a cape and tights, except, well, it's not.

In its quest for peace within your portal, the Plugin Security Manager pledges
to:

- Protect your portal and host system from unwanted side affects and malicious
  software introduced by plugins.
- Control plugin access to your portal, host system, and network by requiring
  that plugins specify ahead of time the portal resources they intend to access.

Still not sold on the need to use Liferay's Plugin Security Manager? The 
scenarios in the following section might change your mind.

## Understanding the Need for Plugin Security [](id=understanding-the-need-for-plugin-security)

Consider the following scenarios:

- A flashy new plugin has arrived on Liferay Marketplace and you want to give it
  a whirl. But naturally, you want to know the parts of your system it will
  access.
- A colleague finds an interesting plugin after scouring the web for something
  that can help streamline processes at your workplace. Of course, you don't
  know whether you can truly trust the plugin creator--this plugin was found
  outside the Liferay Marketplace. If the plugin isn't open source, you have no
  way of knowing if it does anything nefarious.
- Upper management requests your corporate branch and other branches use a
  standard set of plugins on your portal instances. However, this set of plugins
  was written by an outside firm. It's your job to ensure that the plugins won't 
  tamper with your company's proprietary files. 

These are just a few situations that may ring true for you. When you're 
responsible for keeping your system running well 24x7, you can't be too cautious
in protecting your portal, system, and network.

## Controlling Portal Access [](id=controlling-portal-access)

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
organization, you need to set the security management properties appropriately. 
Before you dive into the intricacies of these properties, you should first 
consider a plugin development approach that involves designing an app for the 
security manager from the ground up. 

## Developing Plugins with Security in Mind [](id=developing-plugins-with-security-in-mind)

When you start developing your plugin, you may not have a clear picture of all 
the aspects of the portal you need to access, and that's fine. In fact, it's 
recommended that you go ahead and develop your plugin first and address your 
plugin's Portal Access Control List (PACL) later. However, as you develop your 
plugin there are some common security pitfalls that you should avoid. After you 
develop your plugin you'll dig whole-heartedly into security management by 
generating and fine-tuning your plugin's PACL. The tutorials in this section 
describe the entire process. 

If you're developing a plugin as part of a free app, writing the plugin's PACL
and enabling the security manager for the plugin are optional.

Here is the suggested process for developing secure plugins: 

- Consider common security pitfalls.
- Develop your plugin. 
- Build your plugin's PACL using Liferay's PACL Policy Generation tool. 
- Test your plugin thoroughly, with the security manager enabled. 
- Add to your plugin's security policy, as needed.
- Convert your policy's absolute file paths to relative paths. 

A key step in this process is turning on the Security Manager. This is described 
next.

## Enabling the Security Manager [](id=enabling-the-security-manager)

If you want to distribute plugins, either on the Liferay Marketplace or through
your web site, you have to assume that users will insist the Security Manager is 
enabled in your plugin. For this reason, you should enable it when testing your 
plugins and packaging them for distribution. 

It's very easy to activate the security manager. Set the following
property to true in your plugin's `liferay-plugin-package.properties` file. 

    security-manager-enabled=true

Great! Now you know the basics of how Liferay plugin security works.

## Related Topics [](id=related-topics)

[Setting Permissions](/develop/learning-paths/-/knowledge_base/6-2/setting-permissions)

[Developing with the Plugins SDK](/develop/tutorials/-/knowledge_base/6-2/plugins-sdk)

[Developing Plugins with Liferay IDE](/develop/tutorials/-/knowledge_base/6-2/liferay-ide)

[Developing with Maven](/develop/tutorials/-/knowledge_base/6-2/maven)

[MVC Portlets](/develop/tutorials/-/knowledge_base/6-2/developing-jsp-portlets-using-liferay-mvc)

[Liferay Faces](/develop/tutorials/-/knowledge_base/6-2/liferay-faces-jsf-portlets)

