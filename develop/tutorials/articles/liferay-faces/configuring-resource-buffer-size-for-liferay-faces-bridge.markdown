# Configuring Resource Buffer Size [](id=configure-resource-buffer-size-liferay-portal-6-2-dev-guide-04-en)

Liferay Faces Bridge lets you set the size of the buffer used to load resources
into memory as file contents are copied to the response. The default value of
this option is `1024` (1KB). 

<!-- Is the code below from a web.xml? - Jim -->

    <!-- The default value of the following context-param is 1024. -->
    <context-param>
        <param-name>com.liferay.faces.bridge.resourceBufferSize</param-name>
        <param-value>4096</param-value>
    </context-param>

Alternatively, you can specify the `com.liferay.faces.bridge.resourceBufferSize`
value on a portlet-by-portlet basis in the `WEB-INF/portlet.xml` descriptor. 
