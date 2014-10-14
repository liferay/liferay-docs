# Plugin Security Management [](id=plugin-security-management)

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
