---
header-id: creating-a-blade-profile
---

# Creating a Blade Profile

[TOC levels=1-4]

There are two steps to follow when adding a new Blade profile:

- [Creating a new profile](#creating-a-new-profile)
- [Setting the profile in Liferay Workspace](#setting-a-profile)

You'll learn how to create a profile first.

## Creating a New Profile

To create a new Blade profile, follow these steps:

1.  [Create a generic OSGi module](/docs/7-2/reference/-/knowledge_base/r/creating-a-project).

2.  To create a new command, create the command and arguments classes extending
    [`BaseCommand`](https://github.com/liferay/liferay-blade-cli/blob/master/cli/src/main/java/com/liferay/blade/cli/command/BaseCommand.java)
    and
    [`BaseArgs`](https://github.com/liferay/liferay-blade-cli/blob/master/cli/src/main/java/com/liferay/blade/cli/command/BaseArgs.java),
    respectively, as described in the
    [Creating Custom Commands](/docs/7-2/reference/-/knowledge_base/r/creating-custom-commands-for-blade-cli)
    article. These classes should reside in the profile module's
    `src/main/java/PACKAGE_NAME` folder. These classes register your command and
    arguments to Blade CLI.

3.  To override a default command, follow the same steps outlined
    [here](/docs/7-2/reference/-/knowledge_base/r/creating-custom-commands-for-blade-cli):

    - Create a command class
    - Create an arguments class
    - Define your commands' fully qualified class names for the service loader

    Instead of extending the
    [`BaseCommand`](https://github.com/liferay/liferay-blade-cli/blob/master/cli/src/main/java/com/liferay/blade/cli/command/BaseCommand.java)
    and
    [`BaseArgs`](https://github.com/liferay/liferay-blade-cli/blob/master/cli/src/main/java/com/liferay/blade/cli/command/BaseArgs.java),
    classes, however, extend the command/arguments classes defined for the
    command you intend to override. Make sure to also set the `@Parameters`
    annotation's `commandNames` argument to the command to override.

    For example, if you intend to override the default `deploy` command, your
    arguments class declaration would look like this:

    ```java
    @Parameters(commandDescription = "Overridden Deploy Command", commandNames = "deploy")
    public class OverriddenArgs extends DeployArgs {

    }
    ```

    The corresponding command class override would look like this:

    ```java
    public class OverriddenCommand extends BaseCommand<OverriddenArgs> {

        @Override
        public void execute() throws Exception {
		        OverriddenArgs args = getArgs();

            getBladeCLI().out("OverriddenCommand says " + args.isWatch());
        }

        @Override
        public Class<OverriddenArgs> getArgsClass() {
            return OverriddenArgs.class;
        }
    }
    ```

    You can search for the default command/arguments classes
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
commands or override default commands by leveraging the profile.

| **Note:** Command classes spanning multiple JARs can use/share the same
| profile name. For example, if you want to extend the internal (provided)
| `maven` profile extension with new commands, you can do it externally the same
| way you'd create a new profile.

You can reference the
[sample profile project](https://github.com/liferay/liferay-blade-cli/tree/master/extensions/sample-profile)
to examine a new command and overridden command's setup in a custom profile.

Next, you'll learn how to set your new profile for use in a Liferay Workspace.

## Setting a Profile

To set your new Blade profile in a Liferay Workspace, open the
`${workspaceDir}/.blade.properties` file and set the `profile.name` property to
your profile name:

```properties
profile.name=myprofile
```

This specifies which Blade profile is active and uses its defined commands. The
default setting is `gradle`. You can also set this property to `maven`
out-of-the-box, which is applied for Maven-based workspaces. You can only set
one profile for a workspace.

You can specify the Blade profile for a workspace when initializing it too. This
is done by passing the profile name as an argument when creating the workspace:

```bash
blade init -P [profile-name] [workspace-name]
```

For example, if you execute the following command:

```bash
blade init -P myprofile my-new-custom-workspace
```

Your `my-new-custom-workspace` has the following properties set in its
`${workspaceDir}/.blade.properties` file:

```properties
liferay.version.default=7.2
profile.name=myprofile
```

| **Note:** The `-P` profile parameter can be used for any command to specify
| the profile to use for that command. This is helpful if you want to run a
| command not associated with the workspace's current profile.

Awesome! You've set your new Blade profile!
