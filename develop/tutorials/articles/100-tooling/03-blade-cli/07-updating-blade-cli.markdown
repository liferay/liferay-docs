# Updating Blade CLI [](id=updating-blade-cli)

If your Blade CLI version is outdated, you can run the following command to
automatically download and install the latest version of Blade CLI:

    blade update

For Windows users, the `blade update` command does not work because Windows
cannot update a file that is currently in use. To bypass this issue, you can use
JPM to update your version of Blade CLI:

    jpm install -f https://releases.liferay.com/tools/blade-cli/latest/blade.jar

Blade CLI is updated frequently, so it's recommended to update your Blade CLI
environment for new features. You can check the released versions of Blade CLI
at [https://releases.liferay.com/tools/blade-cli/](https://releases.liferay.com/tools/blade-cli/).
You can check your current installed version by running `blade version`. 

Awesome! You've successfully learned how to update Blade CLI.
