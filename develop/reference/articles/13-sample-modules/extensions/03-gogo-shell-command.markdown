# Gogo Shell Command [](id=gogo-shell-command)

The Gogo Shell Command sample demonstrates adding a custom command to Liferay
Portal's Gogo shell environment. All Liferay Portal installations have a Gogo
shell environment, which lets system administrators interact with Liferay
Portal's module framework on a local server machine.

This example adds a new custom Gogo shell command called `usercount` under the
`blade` scope. It prints out the number of registered users on your Liferay
Portal installation.

To test this sample, follow the instructions below:

1.  Start a Liferay Portal installation.
2.  Using a command line tool, connect to your local Gogo shell. For example,
    you can do this by executing `telnet localhost 11311`.
3.  Run `help` to view all the available commands. The sample Gogo shell command
    is listed.

    ![Figure 1: The sample Gogo shell command is listed with all the available commands.](../../../images/gogo-shell-1.png)

4.  Execute `usercount` to execute the new custom command. The number of users
    on your running Liferay Portal installation is printed.

    ![Figure 2: The outcome of executing the `usercount` command.](../../../images/gogo-shell-2.png)

To add this new Gogo shell command, you must implement the logic in a Java
class with the following two properties:

- `osgi.command.function`: the command's name, which must match the method name
   in the registered service implementation.
- `osgi.command.scope`: the general scope or namespace for the command.

These properties are set in your class's `@Component` annotation like this:

    @Component(
        property = {"osgi.command.function=usercount", "osgi.command.scope=blade"},
        service = Object.class
    )

The logic for the `usercount` command is specified in the method with the same
name:

    public void usercount() {
        System.out.println(
            "# of users: " + getUserLocalService().getUsersCount());
    }

This method uses *Declarative Services* to get a reference for the
`UserLocalService` to invoke the `getUsersCount` method. This lets you find the
number of users currently in the system.

For more information on using the Gogo shell, see the
[Using the Felix Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)
tutorial on Liferay's Developer Network.

## Where Is This Sample? [](id=where-is-this-sample)

There are three different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/extensions/gogo)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-workspace/extensions/gogo)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/master/maven/extensions/gogo)
