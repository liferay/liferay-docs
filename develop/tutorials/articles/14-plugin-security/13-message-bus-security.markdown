# Message Bus Security [](id=message-bus-security)

Specify which services the plugin is permitted to listen on via the portal's
message bus.

*Example:*

	security-manager-message-bus-listen=\
		liferay/test_pacl,\
		liferay/test_pacl_listen_success

Specify which message bus destinations the plugin is permitted to call (send
messages to) via the portal's message bus.

*Example:*

	security-manager-message-bus-send=\
		liferay/message_bus/default_response,\
		liferay/test_pacl_listen_failure,\
		liferay/test_pacl_listen_success,\
		liferay/test_pacl_send_success
