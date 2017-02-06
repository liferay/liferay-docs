# Installing Blade CLI [](id=installing-blade-cli)

The best way to obtain Blade CLI is to download its JAR file and install it
using the [Java Package Manager](http://jpm4j.org) (JPM). Blade CLI is
packaged using [JPM](http://jpm4j.org/#!/md/install). Make sure to install JPM,
since it's the best way to download, install, and update your Blade CLI
environment.

## Installing Blade CLI Using JPM [](id=installing-blade-cli-using-jpm)

After you've installed JPM, install Blade CLI using the following command: 

    (sudo) jpm install https://releases.liferay.com/tools/blade-cli/2.0.1.201612161126/plugins/com.liferay.blade.cli_2.0.1.201612161126.jar

<!-- Above URL should be updated to a permanent "latest" URL, once available.
-Cody -->

To check that Blade CLI is installed, make sure that the `blade` executable is
available on your system path. Test its usage by executing `blade` in your
terminal window. If Blade CLI is installed correctly, you'll see the Blade CLI
options printed in your terminal.

## Updating Your Blade CLI Installation [](id=verifying-your-blade-cli-installation)

If your Blade CLI version is outdated, you can run the following command to
automatically download and install the latest version of Blade CLI:

    jpm install -f https://releases.liferay.com/tools/blade-cli/2.0.1.201612161126/plugins/com.liferay.blade.cli_2.0.1.201612161126.jar

<!--
    blade update

For Windows users, the `blade update` command does not work because Windows
cannot update a file that is currently in use. To bypass this issue, you can use
JPM to update your version of Blade CLI:

    jpm install -f com.liferay.blade.cli
-->

Blade CLI is updated frequently, so it's recommended to update your Blade CLI
environment for new features. You can check your current installed version by
running `blade version`.

You've successfully learned how to install and update Blade CLI. Blade CLI
offers many `create` templates to help build @product-ver@ applications, and
also offers various ways to deploy those apps and interact with your Liferay
server. Be sure to explore more Blade CLI tutorials to learn how.
