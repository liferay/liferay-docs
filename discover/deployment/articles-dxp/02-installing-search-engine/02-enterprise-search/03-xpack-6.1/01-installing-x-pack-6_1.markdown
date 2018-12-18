# Installing X-Pack

Here are the X-Pack installation steps:

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
