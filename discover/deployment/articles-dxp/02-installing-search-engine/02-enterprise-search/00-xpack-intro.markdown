# Installing X-Pack [](id=installing-x-pack)

X-Pack is an 
[Elasticsearch extension](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/setup-xpack.html)
for securing and monitoring Elasticsearch clusters. If you use Elasticsearch,
you should secure it with X-Pack. The security features of X-Pack include
authenticating access to the Elasticsearch cluster's data and encrypting
Elasticsearch's internal and external communications. These are necessary
security features for most production systems. A Liferay Enterprise Search
Premium subscription gets you access to two X-Pack Connectors for @product@:
monitoring and security. A Liferay Enterprise Search Standard subscription gets
you the monitoring integration. Contact
[Liferay's Sales department for more information](https://www.liferay.com/contact-us#contact-sales).

Here's an overview of using X-Pack to secure the data indexed in Elasticsearch:

1.  Get an Enterprise Search subscription.

2.  [Install X-Pack into Elasticsearch](https://www.elastic.co/guide/en/x-pack/6.1/installing-xpack.html) 
    and configure it to require authentication and 
    [encryption](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/configuring-tls.html#configuring-tls).

3.  Download and install the 
[Liferay Connector to X-Pack Security](https://web.liferay.com/group/customer/dxp/downloads/enterprise-search) 
[Elastic Stack 6.x]. 

4.  Configure the X-Pack connector with the proper credentials and encryption
    information.

5.  Restart Elasticsearch. These steps require a full cluster restart.

Following these instructions gives you a working installation of Elasticsearch
communicating freely with @product@. Read Elastic's documentation to learn about
additional configuration options, features, and the architecture of
[X-Pack](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/configuring-security.html). 

## Installing X-Pack [](id=installing-x-pack)

1.  To 
    [install X-Pack](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/installing-xpack-es.html)
    and automatically grant it the required permissions (recommended), run

        bin/elasticsearch-plugin install x-pack --batch

    on each Elasticsearch node. The `--batch` option bypasses
    installation prompts for granting permissions to X-Pack. 

    You'll see log output detailing the permissions granted, finishing with
    `Installed x-pack`:

        -> Downloading x-pack from elastic
        [=================================================] 100%   
        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        @     WARNING: plugin requires additional permissions     @
        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        * java.io.FilePermission \\.\pipe\* read,write
        * java.lang.RuntimePermission accessClassInPackage.com.sun.activation.registries
        * java.lang.RuntimePermission getClassLoader
        * java.lang.RuntimePermission setContextClassLoader
        * java.lang.RuntimePermission setFactory
        * java.net.SocketPermission * connect,accept,resolve
        * java.security.SecurityPermission createPolicy.JavaPolicy
        * java.security.SecurityPermission getPolicy
        * java.security.SecurityPermission putProviderProperty.BC
        * java.security.SecurityPermission setPolicy
        * java.util.PropertyPermission * read,write
        See http://docs.oracle.com/javase/8/docs/technotes/guides/security/permissions.html
        for descriptions of what these permissions allow and the associated risks.
        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        @        WARNING: plugin forks a native controller        @
        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        This plugin launches a native controller that is not subject to the Java
        security manager nor to system call filters.
        Elasticsearch keystore is required by plugin [x-pack], creating...
        -> Installed x-pack

    See more about the permissions X-Pack needs
    [here](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/installing-xpack-es.html). 

2.  Make sure Elasticsearch allows the automatic creation of indexes. If
    you're unsure, check `elasticsearch.yml` for this property:

        action.auto_create_index: false

    This property is `true` by default, so if you don't see it in
    `elasticsearch.yml`, there's nothing to worry about. See [Elastic's
    documentation](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/docs-index_.html#index-creation) 
    for more information on automatic index creation.

3.  Restart Elasticsearch. Make sure @product@ is shut down during this step.

Now configure security and/or monitoring, depending on your needs.
