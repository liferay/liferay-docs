# Installing X-Pack [](id=installing-x-pack-0)

Here are the X-Pack installation steps:

1.  To 
    [install X-Pack](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/installing-xpack-es.html)
    and automatically grant it the required permissions (recommended), run this
    command on each Elasticsearch node:

        bin/elasticsearch-plugin install x-pack --batch

    The `--batch` option bypasses installation prompts for granting permissions
    to X-Pack. 

    The log output details the permissions granted and finishes with
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

2.  Make sure Elasticsearch allows automatic index creation by verifying this 
    property in your `elasticsearch.yml` file:

        action.auto_create_index: false

    This property is `true` by default. 
    [Elastic's documentation](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/docs-index_.html#index-creation) 
    describes more automatic index creation details.

3.  Shutdown @product@ and restart Elasticsearch. 

Now configure security and/or monitoring, depending on your needs.
