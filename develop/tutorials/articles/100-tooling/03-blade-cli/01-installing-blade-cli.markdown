# Installing Blade CLI [](id=installing-blade-cli)

The best way to obtain Blade CLI is to download its JAR file and install it
using the [Java Package Manager](http://jpm4j.org) (JPM). Blade CLI is
packaged using [JPM](http://jpm4j.org/#!/md/install). Make sure to install JPM,
since it's the best way to download, install, and update your Blade CLI
environment. The exact JPM installation and update syntax depends on your
operating system, and will be covered in greater detail next.

## Installing Blade CLI Using JPM [](id=installing-blade-cli-using-jpm)

After you have installed JPM, install the Blade CLI using the following command: 

        (sudo) jpm install com.liferay.blade.cli

Now that Blade CLI is installed on your machine, you'll learn how to verify and
update your installation.

## Verifying Your Blade CLI Installation [](id=verifying-your-blade-cli-installation)

To check that Blade CLI is installed, make sure that the `blade` executable is
available on your system path. Test its usage by entering `blade version` into a
terminal. If Blade CLI is installed correctly, you'll see the current version
for the installed tools:

If your version is outdated, you can run `blade update` to automatically
download and install the latest version of Blade CLI. Blade CLI is updated
frequently, so it's recommended to update your Blade CLI environment for new
features.

+$$$

**Note:** For Windows users, the `blade update` command does not work. This is
because Windows cannot update a file that is currently in use. To bypass this
issue, you can use JPM to update your version of Blade CLI:

    jpm install -f com.liferay.blade.cli

$$$

You've successfully downloaded and installed Blade CLI using JPM and verified
your installation using a basic `blade` command. Blade CLI offers many `create`
templates to help build @product@ 7.0 applications, and also offers various ways
to deploy those apps and interact with your Liferay server. Be sure to explore
more Blade CLI tutorials to learn how.
