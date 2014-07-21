# Configuring the Portlet Container Abilities [](id=configure-portlet-container-abilities-liferay-portal-6-2-dev-guide-en)

Liferay Faces Bridge can be run in a variety of portlet containers (Liferay,
Pluto, etc.) and is aware of some of the abilities (or limitations) of these
containers. Liferay Faces Bridge enables you to configure the abilities of the
portlet container in the `WEB-INF/web.xml` descriptor. 

    <!--
    The default value of the following context-param depends on which portlet
    container the bridge is running in. The value determines whether or not the
    bridge resource handler will attempt to set the status code of downloaded
    resources to values like HttpServletResponse.SC_NOT_MODIFIED.
    -->
    <context-param>
        <param-name>com.liferay.faces.bridge.containerAbleToSetHttpStatusCode</param-name>
        <param-value>true</param-value>
    </context-param>

By configuring portlet container capabilities, you can take advantage of your
portlet container's specific strengths while using Liferay Faces Bridge. 
