# Setting Blade CLI Proxy Requirments [](id=setting-blade-cli-proxy-requirments)

If you have proxy server requirements and want to configure your http(s) proxy
to work with the Blade CLI, follow the instructions below.

Once you've installed [JPM](http://jpm4j.org/#!/md/install), you must configure
your http(s) proxy to work with JPM before installing Blade CLI. Run the
following command to modify JPM's executable so you can install Blade CLI from
Liferay's release server through a proxy:

    (sudo) jpm command --jvmargs "-Dhttp(s).proxyHost=[your proxy host] -Dhttp(s).proxyPort=[your proxy port]" jpm

Now you can install Blade CLI and set its proxy settings using JPM.

For Mac and Linux users, run the following command:
		
    (sudo) jpm install -f --jvmargs "-Dhttp(s).proxyHost=[your proxy host] -Dhttp(s).proxyPort=[your proxy port]" https://releases.liferay.com/tools/blade-cli/2.0.1.201612161126/plugins/com.liferay.blade.cli_2.0.1.201612161126.jar

<!-- Above URL should be updated to a permanent "latest" URL, once available.
-Cody -->

Windows users may encounter a bug preventing JVM arguments from passing into
JPM. To work around this,
[install Blade CLI](/develop/tutorials/-/knowledge_base/7-0/installing-blade-cli)
the same way that was instructed for non-proxy users. Then go to your JPM
installation path (e.g., `~/.jpm/windows/bin`) and open the `blade.ini`. Add the
following lines to the end of the file.

    vmarg.1=-Dhttp(s).proxyHost=[your proxy host]
    vmarg.2=-Dhttp(s).proxyPort=[your proxy port]

<!--+$$$

**Note:** When executing `blade update`, your Blade CLI's proxy settings are
sometimes reset. Be sure to verify your proxy settings after every Blade CLI
update.

$$$-->

Now that Blade CLI's proxy settings are configured, you'll learn how to update
your installation.
