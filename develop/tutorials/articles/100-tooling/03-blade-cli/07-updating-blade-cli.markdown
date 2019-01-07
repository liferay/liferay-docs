# Updating Blade CLI [](id=updating-blade-cli)

If your Blade CLI version is outdated, you can run the following command to
automatically download and install the latest version of Blade CLI:

    blade update

+$$$

**Note:** For Windows users on Blade CLI 3.3.0 and older, the `blade update`
command does not work because Windows cannot update a file that is currently in
use. To bypass this issue, use JPM to update your version of Blade CLI:
    
    jpm install -f https://releases.liferay.com/tools/blade-cli/latest/blade.jar

The `blade update` command for Windows users on Blade CLI 3.4.1+ works as
expected.

$$$

Blade CLI is updated frequently, so it's recommended to update your Blade CLI
environment for new features. You can check the released versions of Blade CLI
on Nexus by inspecting the
[com.liferay.blade.cli](https://repository-cdn.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/blade/com.liferay.blade.cli/).
artifact. You can check your current installed version by running `blade
version`. When running `blade version`, you are notified if there's a newer
Blade CLI version available.

+$$$

**Note:** If you run `blade version` after updating, but don't see the
expected version installed, you may have two separate Blade CLI installations on
your machine. This is typically caused if you installed an earlier version of
Blade CLI, and then used the
[Liferay Project SDK installer](/develop/tutorials/-/knowledge_base/7-1/installing-blade-cli)
(at any time prior) to update the older Blade CLI instance. This is not
recommended. Doing this installs Blade CLI in the global and user home folder of
your machine. The latest Blade CLI update process installs to your user home
folder, so you must delete the legacy Blade files in your global folder, if
present. To do this, navigate to your `GLOBAL_FOLDER/JPM4J` folder and delete

- `/bin/blade`
- `/commands/blade`

The newest Blade CLI installation in your user home folder is now recognized and
available.

$$$

Although Blade CLI is frequently released, if you want bleeding edge features
not yet available, you can install the latest snapshot version:

    blade update -s

This pulls the latest snapshot version of Blade CLI and installs it to your
local machine. Running `blade version` after installing a snapshot displays
output similar to this:

    blade version 3.3.1.SNAPSHOT201811301746

Be careful; snapshot versions are unstable and should only be used for
experimental purposes.

Awesome! You've successfully learned how to update Blade CLI.
