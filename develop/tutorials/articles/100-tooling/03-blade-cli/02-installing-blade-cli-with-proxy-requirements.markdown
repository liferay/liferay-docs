# Installing Blade CLI with Proxy Requirements [](id=setting-blade-cli-proxy-requirments)

If you have proxy server requirements and want to use Blade CLI, you must
configure your http(s) proxy for it using JPM. Before beginning, make sure
you've installed JPM and Blade CLI using a Liferay Workspace installer. Read the
[Installing Blade CLI](/develop/tutorials/-/knowledge_base/7-1/installing-blade-cli)
tutorial for more details.

Once Blade CLI and JPM are installed, execute the following command to configure
your proxy requirements for Blade CLI:

    jpm command --jvmargs "-Dhttp(s).proxyHost=[your proxy host] -Dhttp(s).proxyPort=[your proxy port]" jpm

Excellent! You've configured Blade CLI with your proxy settings using JPM.
