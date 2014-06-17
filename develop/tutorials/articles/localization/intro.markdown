# Localization [](id=localization-lp-6-2-develop-tutorial)

If your portlets target an international audience, you can localize your
portlets' user interfaces. To localize a portlet, you need to create language
properties files, also called resource bundles, for each language you wish to
support. You can translate language properties manually or use a web service to
translate them for you. Conveniently, all of the translated messages used by
Liferay Portal are also accessible to plugin projects. To localize messages in
addition to portal's localized messages, you must create language keys in one or
more resource bundles within your plugin project. When planning your portlet's
localization, you should consider the following questions.

Are there messages that Portal uses that you'd like to use in your portlets?
Does your plugin contain multiple portlets? If so, do any of its portlets need
to be available for administrative purposes in the Control Panel? If any of its
portlets need to be in the Control Panel, you should create separate resource
bundles for each of these portlets. Otherwise, your portlets should share the
same resource bundle so that you can leverage Liferay's language building
capabilities from Liferay IDE and the Plugins SDK. You'll learn how to
localize your portlets in all of these scenarios.

## Next Steps [](id=next-steps-lp-6-2-develop-tutorial-l18n-0)

A good way to start learning about app localization is by learning how to
leverage the messages that Liferay Portal has already localized in its core set
of language keys. The following tutorial explains how to do this: 

[Using Liferay's Language Keys](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/using-liferays-language-keys-lp-6-2-develop-tutorial)

As you develop site administration portlets, you should learn how to localize
them be reading this tutorial:

[Localizing Site Administration Portlets](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/localizing-site-administration-portlets-lp-6-2-develop-tutorial)

You'll find that it's helpful to use the same language keys throughout your
app's portlets. Learn how to do so by following this tutorial:

[Sharing Language Keys Between Your Portlets](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/sharing-language-keys-between-your-portlets-lp-6-2-develop-tutorial)

Lastly, you'll want to propagate your master language properties to the language
properties files for each of the languages you're supporting. And you can get a
leg up on doing the translations by using automation. Check out this
tutorial to learn how to generate the properties files and automate initial
translations: 

[Generating Language Properties File and Automated Translations](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/generating-language-properties-file-and-automated-t-lp-6-2-develop-tutorial)
