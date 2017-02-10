# Shield Adapter Settings [](id=shield-adapter-settings)

The following is a complete list of the settings you can configure with
[Liferay's Shield adapter](/discover/deployment/-/knowledge_base/7-0/shield).

`password=liferay`
:The String value used for the password part of the authentication token.

`requiresAuthentication=true`
:Set this boolean to `false` to connect to an Elasticsearch cluster not secured
by Shield (in other words, don't use authentication or encryption). 

`requiresSSL=true`
:Set this boolean to `false` to connect to an Elasticsearch cluster that's
secured by Shield but doesn't have SSL configured (in other words, use
authentication but not encryption).

`sslKeystoreKeyPassword=`
:When `requiresSSL=true`, set this String to the value of your Elasticsearch's
`shield.ssl.keystore.key_password` property. See
[here](https://www.elastic.co/guide/en/shield/2.2/ssl-tls.html#enable-ssl) for
more information.

`sslKeystorePassword=liferay`
:When `requiresSSL=true`, set this String to the value of your Elasticsearch's
`shield.ssl.keystore.password` property. See
[here](https://www.elastic.co/guide/en/shield/2.2/ssl-tls.html#enable-ssl) for
more information.

`sslKeystorePath=/path/to/keystore.jks`
:When `requiresSSL=true`, set this String to the value for your Elasticsearch's
`shield.ssl.keystore.path` property. See
[here](https://www.elastic.co/guide/en/shield/2.2/ssl-tls.html#enable-ssl) for
more information.

`username=liferay`
:The String value to use for the username part of the authentication token.
