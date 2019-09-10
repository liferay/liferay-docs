---
header-id: installing-new-extensions-for-blade-cli
---

# Installing New Extensions for Blade CLI

[TOC levels=1-4]

After you've created a new extension for Blade CLI, you must install it so it's
available for use. You can learn how to create
[custom commands](/docs/7-2/reference/-/knowledge_base/r/creating-custom-commands-for-blade-cli)
and
[custom project templates](/docs/7-2/reference/-/knowledge_base/r/creating-custom-project-templates-for-blade-cli)
in their respective articles.

When Blade CLI starts, it looks in the user's `${user.home}/.blade/extensions`
folder for JAR files. All JAR files are searched to see if they contain
valid Blade extensions. You'll learn how to install new extensions next.

## Installing a New Extension

To install an extension, you must move the extension JAR to the user's
`${user.home}/.blade/extensions` folder. You can do this automatically from
Blade CLI by running

```bash
blade extension install /path/to/my_extension.JAR
```

You can verify that the extension is available by running the following
commands, depending on extension type:

**Custom Command:**

```bash
blade help
```

**Custom Project Template:**

```bash
blade create -l
```

Great! You've installed a new extension!

## Uninstalling an Extension

You can uninstall a Blade extension by running this:

```bash
blade extension uninstall EXTENSION_NAME.jar
```

This removes the extension JAR from the user's `${user.home}/.blade/extensions`
folder.
