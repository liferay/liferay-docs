# Installing Blade CLI [](id=installing-blade-cli)

The best way to obtain Blade CLI is to download its JAR file and install it
using the [Java Package Manager](http://jpm4j.org) (JPM). Blade CLI is
packaged using [JPM](http://jpm4j.org/#!/md/install). Make sure to install JPM,
since it's the best way to download, install, and update your Blade CLI
environment. The exact JPM installation and update syntax depends on your
operating system, and will be covered in greater detail next.

## Installing Blade CLI Using JPM [](id=installing-blade-cli-using-jpm)

Follow the steps outlined below to install Blade CLI to your local machine.

1.  After you have installed JPM, download the `com.liferay.blade.cli.jar` file:
    [https://liferay-test-01.ci.cloudbees.com/job/liferay-blade-cli/lastSuccessfulBuild/artifact/com.liferay.blade.cli/generated/com.liferay.blade.cli.jar](https://liferay-test-01.ci.cloudbees.com/job/liferay-blade-cli/lastSuccessfulBuild/artifact/com.liferay.blade.cli/generated/com.liferay.blade.cli.jar).

2.  Next, install the downloaded JAR:

        (sudo) jpm install -fl [Downloads Directory]/com.liferay.blade.cli.jar

3.  Configure with http(s) proxy
	
	This is an unnecessary step. But if you want to configure your http(s) proxy working with blade cli , you can follow the steps bellow:

	- for linux and mac user:
		
			(sudo) jpm install -fl --jvmargs "-Dhttp(s).proxyHost=[your proxy host] -Dhttp(s).proxyPort=[your proxy port]" [Downloads Directory]/com.liferay.blade.cli.jar
	
	- for windows user:
	
		There is a bug preventing jvmargs passing into jpm. So just install blade the same way in step 2. Then goto your jpm installation path (maybe `{user.home}/.jpm/windows/bin`) and open `blade.ini` file and add the the following lines to the end of the file.
		
			vmarg.1=-Dhttp(s).proxyHost=[your proxy host]
			vmarg.2=-Dhttp(s).proxyPort=[your proxy port]

	After configuring proxy, every time you run blade command, it will take your proxy. 

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

    jpm install -f https://liferay-test-01.ci.cloudbees.com/job/liferay-blade-cli/lastSuccessfulBuild/artifact/com.liferay.blade.cli/generated/com.liferay.blade.cli.jar

$$$

You've successfully downloaded and installed Blade CLI using JPM and verified
your installation using a basic `blade` command. Blade CLI offers many `create`
templates to help build @product@ 7.0 applications, and also offers various ways
to deploy those apps and interact with your Liferay server. Be sure to explore
more Blade CLI tutorials to learn how.
