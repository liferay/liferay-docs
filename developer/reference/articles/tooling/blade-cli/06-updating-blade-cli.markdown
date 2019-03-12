---
header-id: updating-blade-cli
---

# Updating Blade CLI

Blade CLI is updated frequently, so it's recommended to update your Blade CLI
environment for new features. You can check the released versions of Blade CLI
on Nexus by inspecting the
[`com.liferay.blade.cli`](https://repository-cdn.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/blade/com.liferay.blade.cli/).
artifact. You can check your current installed version by running `blade
version`.

To update your Blade CLI installation to the latest stable version, run

    blade update

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
