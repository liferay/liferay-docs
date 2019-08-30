---
header-id: creating-custom-commands-for-blade-cli
---

# Creating Custom Commands for Blade CLI

[TOC levels=1-4]

To create a custom command for Blade CLI, follow these steps:

| **Note:** This article creates a Gradle-based command project. These steps can
| be completed for a Maven-based project too.

1.  [Create a generic OSGi module](/docs/7-2/reference/-/knowledge_base/r/creating-a-project).

2.  You'll leverage [JCommander](http://jcommander.org/) and the Blade CLI API
    to create your custom command. Add these dependencies in your build file.
    For example, a `build.gradle` file's `dependencies` block looks like this:

    ```groovy
    dependencies {
        compileOnly group: "com.beust", name: "jcommander", version: "1.72"
        compileOnly group: "com.liferay.blade", name: "com.liferay.blade.cli", version: "latest.release"
    }
    ```

3.  Build a Command class by extending the
    [`BaseCommand`](https://github.com/liferay/liferay-blade-cli/blob/master/cli/src/main/java/com/liferay/blade/cli/command/BaseCommand.java)
    class:

    ```java
    import com.liferay.blade.cli.command.BaseCommand;

    public class Hello extends BaseCommand<HelloArgs> {

        @Override
        public void execute() throws Exception {
            HelloArgs helloArgs = getArgs();

            getBladeCLI().out("Hello " + helloArgs.getName());
        }

        @Override
        public Class<HelloArgs> getArgsClass() {
            return HelloArgs.class;
        }

    }
    ```

    This registers your new command with Blade. You must define the `execute()`
    command for all classes extending `BaseCommand`. The `BaseCommand` class
    expects an arguments class as its parameter. You'll create this next.

4.  Create a class that holds your command's arguments:

    ```java
    import com.beust.jcommander.Parameter;
    import com.beust.jcommander.Parameters;

    import com.liferay.blade.cli.command.BaseArgs;

    @Parameters(commandDescription = "Executes a hello command", commandNames = "hello")
    public class HelloArgs extends BaseArgs {

        public String getName() {
            return _name;
        }

        @Parameter(description = "The name to say hello to", names = "--name", required = true)
        private String _name;

    }
    ```

    This class extends the
    [`BaseArgs`](https://github.com/liferay/liferay-blade-cli/blob/master/cli/src/main/java/com/liferay/blade/cli/command/BaseArgs.java)
    class. Notice that the class declaration has the `@Parameters` JCommander
    annotation. This sets your command's description and name. The `@Parameter`
    annotation applied to the private string `_name` sets how the command's
    parameter is called and whether it's required.

5.  Since Blade looks for custom commands using the
    `com.liferay.blade.cli.command.BaseCommand` service interface, you must use
    a standard JRE service loader mechanism to finish registering your new
    command with Blade CLI.

    Create a file named `com.liferay.blade.cli.command.BaseCommand` in the
    `src/main/resources/META-INF/services/` folder. This class should list all
    of your custom commands' fully qualified class names:

    ```
    com.liferay.extensions.sample.command.Hello
    ```

    | **Note:** Java's Service Loader Interface (SPI) is used to load the
    | fully qualified classes in the `META-INF/services` folder. You can learn
    | more about SPIs
    | [here](https://docs.oracle.com/javase/7/docs/api/java/util/ServiceLoader.html).

6.  Generate the extension's JAR file (e.g., `gradlew build`).

Awesome! You've created a custom command! You can deploy multiple custom
commands in a single JAR, so you can continue adding custom command projects to
this module, if desired. See the
[Installing New Extensions](/docs/7-2/reference/-/knowledge_base/r/installing-new-extensions-for-blade-cli)
article to install the command (JAR) to Blade CLI.

You can examine a working custom command project
[here](https://github.com/liferay/liferay-blade-cli/blob/master/extensions/sample-command).
