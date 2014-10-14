# File Security [](id=file-security)

The following properties address file deletion, execution, reading, writing and
replacement operations. The `*` character in a path name indicates all files in
the current directory. The `-` character in a path name indicates all files in
the current directory and in its subdirectories.

Specify files the plugin is permitted to delete.

*Example:*

	security-manager-files-delete=\
		../webapps/chat-portlet/WEB-INF/src/com/liferay/chat/temp/-

Specify files the plugin is permitted to execute.

*Example:*

	security-manager-files-execute=\
		/bin/bash,\
		C:\\WINDOWS\\system32\\ping.exe

Specify files the plugin is permitted to read.

*Example:*

	security-manager-files-read=\
		../webapps/chat-portlet/images/*,\
		../webapps/chat-portlet/WEB-INF/liferay-releng.properties,\
		../webapps/chat-portlet/WEB-INF/src/-,\
		/bin/bash,\
		C:\\WINDOWS\\system32\\ping.exe

Specify files the plugin is permitted to overwrite.

*Example:*

	security-manager-files-write=\
		../webapps/chat-portlet/images/*,\
		../webapps/chat-portlet/WEB-INF/*,\
		../webapps/chat-portlet/WEB-INF/src/com/liferay/chat/util/ChatUtil.java
