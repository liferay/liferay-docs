# Installing Blade CLI with Proxy Requirements [](id=installing-blade-cli-with-proxy-requirements)

If you have proxy server requirements and want to use Blade CLI, you must
configure your http(s) proxy for it using JPM. Follow the instructions below to
learn how.

1.  Install JPM and Blade CLI using the Liferay Project SDK installer. Read the
    [Installing Blade CLI](/developer/reference/-/knowledge_base/7-2/installing-blade-cli)
    tutorial for more details.

2.  Execute the following command to configure your proxy requirements for Blade
    CLI:

        jpm command --jvmargs "-Dhttp(s).proxyHost=[your proxy host] -Dhttp(s).proxyPort=[your proxy port]" jpm

Excellent! You've configured Blade CLI with your proxy settings using JPM.
