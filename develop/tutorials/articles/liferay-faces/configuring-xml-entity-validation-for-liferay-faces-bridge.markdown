# Configuring XML Entity Validation [](id=validate-faces-config-xml-entities-liferay-portal-6-2-dev-guide-en)

<!-- Explain why this is helpful. - Jim -->

Liferay Faces Bridge gives you the option of enabling or disabling XML
validation for all `faces-config.xml` file entities. By default, the validation
is disabled. 

To enable XML validation for all `faces-config.xml` file entities,
you can set the option to `true` in the `WEB-INF/web.xml` descriptor: 

    <!-- The default value of the following context-param is false. -->
    <context-param>
        <param-name>com.liferay.faces.bridge.resolveXMLEntities</param-name>
        <param-value>true</param-value>
    </context-param>

<!-- Don't end a section with a code block. -Rich -->
