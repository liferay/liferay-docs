# Configuring XML Entity Validation with Liferay Faces Bridge

<!-- Explain why this is helpful. - Jim -->

Liferay Faces Bridge gives you the option of enabling or disabling XML
validation for all `faces-config.xml` file entities. This tutorial will show you
how! 

By default, the validation is disabled. To enable XML validation for all
`faces-config.xml` file entities, you can set the option to `true` in the
`WEB-INF/web.xml` descriptor: 

    <context-param>
        <param-name>com.liferay.faces.bridge.resolveXMLEntities</param-name>
        <param-value>true</param-value>
    </context-param>

Excellent! You now know how to enable XML entity validation using Liferay Faces
Bridge. 

## Related Topics

<!-- Add once JSF tutorials are finished. -Cody -->
