# Socket Security [](id=socket-security)

Specify sockets permitted to accept connections in the plugin.

*Example:*

	security-manager-sockets-accept=\
		localhost:4320

Specify connections the plugin is permitted to make with the outside world.

*Example:*

	security-manager-sockets-connect=\
		www.cbs.com:80,\
		www.google.com:80,\
		www.google.com:443,\
		www.yahoo.com:443

Specify sockets the plugin can listen on.

*Example:*

This example specifies socket range `4316-4318`, socket `4320`, and socket
`4321`.

	security-manager-sockets-listen=\
		4316-4318,\
		4320,\
		4321

*The Star Socket Wildcard:*

The star (`*`) represent any hostname. Here are examples that demonstrate its
use: 

- `*.liferay.com` matches any host ending in `.liferay.com`, such as
`docs.liferay.com` and `issues.liferay.com`. 

- `*:*` matches every socket and every port. 
