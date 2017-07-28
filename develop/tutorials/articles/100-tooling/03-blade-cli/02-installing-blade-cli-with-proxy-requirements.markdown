# Installing Blade CLI with Proxy Requirements [](id=setting-blade-cli-proxy-requirments)

If you have proxy server requirements and want to configure your http(s) proxy
so you can install Blade CLI, follow the instructions corresponding to your
operating system.

## Windows [](id=windows)

Follow the steps below to install Blade CLI on Windows using your proxy server
requirements.

1.  Install JPM for Windows using the
    [Windows JPM Installer](http://jpm4j.org/#!/md/windows). Download the
    installer using a browser that is already configured with your proxy
    settings.

2.  You must configure your http(s) proxy to work with JPM before installing
    Blade CLI. Run the following command to modify JPM's executable so you can
    install Blade CLI from Liferay's release server through a proxy:

        jpm command --jvmargs "-Dhttp(s).proxyHost=[your proxy host] -Dhttp(s).proxyPort=[your proxy port]" jpm

3.  Run the following JPM command to install Blade CLI:

        jpm install -f https://releases.liferay.com/tools/blade-cli/latest/blade.jar

Excellent! You've configured JPM with your proxy settings and installed Blade
CLI on Windows!

## MacOS and Linux [](id=macos-and-linux)

Follow the steps below to install Blade CLI on MacOS/Linux using your proxy
server requirements.

1.  Switch to sudo shell:

        sudo sh

2.  Set up your proxy settings for curl:

        export https_proxy=[your proxy host]:[your proxy port]

    For example,

        export https_proxy=http://172.16.207.1:9090

3.  Execute the appropriate shell script to install JPM:

        curl https://raw.githubusercontent.com/liferay/liferay-blade-cli/master/installers/local_jpm_only | su

4.  You must configure your http(s) proxy to work with JPM before installing
    Blade CLI. Run the following command to modify JPM's executable so you can
    install Blade CLI from Liferay's release server through a proxy:

        jpm command --jvmargs "-Dhttp(s).proxyHost=[your proxy host] -Dhttp(s).proxyPort=[your proxy port]" jpm

5.  Run the following JPM command to install Blade CLI:

        jpm install -f https://releases.liferay.com/tools/blade-cli/latest/blade.jar

Excellent! You've configured JPM with your proxy settings and installed Blade
CLI on MacOS/Linux!

+$$$

**Note:** When executing `blade update`, your Blade CLI's proxy settings are
sometimes reset. Be sure to verify your proxy settings after every Blade CLI
update.

$$$
