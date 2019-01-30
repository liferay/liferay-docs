# Watching a Deployed Project with Blade CLI

Blade CLI offers a way to *watch* a deployed project, which compiles and
redeploys a project when changes are detected. There are two ways to do this:

- `blade watch`
- `blade deploy -w`

The `blade watch` command is the fastest way to develop and test module changes,
because the `watch` command does not rebuild your project every time
a change is detected. When running `blade watch`, your project is not copied to
Portal, but rather, is installed into the runtime as a reference. This means
that the Portal does not make a cached copy of the project. This allows the
Portal to see changes that are made to your project's files immediately. When
you cancel the `watch` task, your module is uninstalled automatically.

+$$$

**Note:** The `blade watch` command is available for Liferay Workspace versions
1.10.9+ (i.e., the `com.liferay.gradle.plugins.workspace` dependency). Maven
projects cannot leverage the `watch` feature at this time.

$$$

The `blade deploy -w` command works similarly to `blade watch`, except it
manually recompiles and deploys your project every time a change is detected.
This causes slower update times, but does preserve your deployed project in
Portal when it's shut down.

Cool! You your development and testing just got more efficient with Blade CLI's
watch capabilities.