---
header-id: creating-a-blade-profile
---

# Creating a Blade Profile

[TOC levels=1-4]

Suppose you want to customize the normal Blade development workflow. Normally,
Liferay developers who use Blade CLI run a series of Blade commands that all
make sense in the *default* Liferay Workspace. What if the workspace, however,
should support a containerized environment (e.g., Docker) or some other
specialized environment? The commands used in the development workflow must
complete the workflow slightly different.

To customize Blade CLI's development workflow, create a Blade *profile*. Blade
profiles let you *override* existing Blade commands or add *new* commands. For
example, `blade init` for a profile `myprofile` would override the
default `init` command to do something before/after the normal `init` command.
The command `blade server start`, in the case of a profile, would override the
`ServerStartCommand` in Blade and contribute its own way to start the Liferay
server (e.g., in a container of some sort).

Commands like `blade deploy` or `blade watch` would likely mean something
completely different in the context of a profile, so they would need to be
overridden too.

## Creating a New Profile

To create a new Blade profile, follow these steps:

1.  [Create a generic OSGi module](/docs/7-2/reference/-/knowledge_base/r/creating-a-project).

2.  In the profile module's `src/main/java/PACKAGE_NAME` folder, create the
    command and arguments classes extending
    [`BaseCommand`](https://github.com/liferay/liferay-blade-cli/blob/master/cli/src/main/java/com/liferay/blade/cli/command/BaseCommand.java)
    and
    [`BaseArgs`](https://github.com/liferay/liferay-blade-cli/blob/master/cli/src/main/java/com/liferay/blade/cli/command/BaseArgs.java),
    respectively, as described in the
    [Creating Custom Commands](/docs/7-2/reference/-/knowledge_base/r/creating-custom-commands-for-blade-cli)
    article. These classes register your command and arguments to Blade CLI.

3.  To override a default command, create the command class extending
    [`BaseCommand`](https://github.com/liferay/liferay-blade-cli/blob/master/cli/src/main/java/com/liferay/blade/cli/command/BaseCommand.java)
    as described in the
    [Creating Custom Commands](/docs/7-2/reference/-/knowledge_base/r/creating-custom-commands-for-blade-cli)
    article. When specifying your arguments class, instead of extending
    [`BaseArgs`](https://github.com/liferay/liferay-blade-cli/blob/master/cli/src/main/java/com/liferay/blade/cli/command/BaseArgs.java),
    extend the arguments class defined for the command to override.
    
    Then, set the `@Parameters` annotation's `commandNames` argument to the
    command to override. For example, if you intend to override the default
    `deploy` command, your arguments class declaration would look like this:

    ```java
    @Parameters(commandDescription = "Overriden Deploy Command", commandNames = "deploy")
    public class OverriddenArgs extends DeployArgs {

    }
    ```

    You can search for the default arguments classes
    [here](https://github.com/liferay/liferay-blade-cli/tree/master/cli/src/main/java/com/liferay/blade/cli/command).

4.  To associate a command to your new profile, set the
    [`BladeProfile`](https://github.com/liferay/liferay-blade-cli/blob/master/cli/src/main/java/com/liferay/blade/cli/command/BladeProfile.java)
    annotation in your command class:

    ```java
    @BladeProfile("myprofile")
    public class NewCommand extends BaseCommand<NewArgs> {

    }
    ```

    The annotation's parameter should specify the profile you want to associate
    the command with (e.g., `myprofile`).

Excellent! You've created a new Blade profile and learned how to add new
commands or override default commands by leveraging the profile. You can
reference the
[sample profile project](https://github.com/liferay/liferay-blade-cli/tree/master/extensions/sample-profile)
to examine a new command and overridden command's setup in a custom profile.

Next, you'll learn how to set your new profile for use in a Liferay Workspace.

## Setting a Profile

To set your new Blade profile in a Liferay Workspace open the
`${workspaceDir}/.blade.properties` file and set the `profile.name` property to
your profile name:

```properties
profile.name=myprofile
```

This specifies which Blade profile is active and uses the commands with that
defined profile. The default setting is `gradle`. You can also set this property
to `maven` out-of-the-box, which is applied for Maven-based workspaces.

You can specify the Blade profile for a workspace when initializing it too. This
is done by passing the profile name as an argument when creating the workspace:

```bash
blade init -p <profile-name> <workspace-name>
```

For example, if you execute the following command:

```bash
blade init -p myprofile my-new-custom-workspace
```

Your `my-new-custom-workspace` will have the following properties set in its
`${workspaceDir}/.blade.properties` file:

```properties
liferay.version.default=7.2
profile.name=myprofile
```

Awesome! You've set your new Blade profile!
