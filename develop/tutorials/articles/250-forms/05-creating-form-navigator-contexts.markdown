# Creating Form Navigator Contexts [](id=creating-form-navigator-contexts)

Form Navigator System Settings let you specify what categories and sections are 
visible in your forms. You can learn how to set Form Navigator 
System Settings in [Configuring Form Navigator Forms](/discover/portal/-/knowledge_base/7-0/configuring-form-navigator-forms). 
Form Navigator configurations let you specify an optional context 
which defines the circumstances for which the configuration is applied. The
following Form Navigator contexts are available by default:

- `add`: Denotes that the form is viewed when new content is being created. 
  For example, you could use the `add` context to specify the visible form
  sections when someone creates a new site.

- `update`: Denotes that the form is viewed when content is being edited. 
  For example, you could use the `update` context to specify the visible form
  sections when someone edits a site.

Although the default contexts cover most use cases, you may want to provide 
additional contexts for users. For example, you may want a custom configuration
for a form when it's viewed by an administrator. This tutorial covers how to
create additional contexts for Form Navigators. 

Your first step is to manage the dependencies.

## Adding the Form Navigator Dependency [](id=adding-the-form-navigator-dependency)

Open your module's `build.gradle` file and add the following dependency:

    dependencies {
        compileOnly group: "com.liferay.portal", name: 
        "com.liferay.frontend.taglib.form.navigator", version: "1.0.2"
    }

Now that you have the Form Navigator taglib dependency added, you can create the 
component class. 

## Implementing the Context Provider Class [](id=implementing-the-context-provider-class)

Follow these steps to create a Form Navigator Context:

1.  Create a component class that implements the `FormNavigatorContextProvider` 
    service: 
    
        @Component(
        	service = FormNavigatorContextProvider.class
        )

2.  Set the `FormNavigatorContextConstants.FORM_NAVIGATOR_ID` property to the 
    form associated with the context. The example configuration below 
    specifies the Users form, using a constant provided by the 
    [`com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorConstants` class](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/taglib/ui/FormNavigatorConstants.html):

        @Component(
        	property = FormNavigatorContextConstants.FORM_NAVIGATOR_ID + "=" + 
          FormNavigatorConstants.FORM_NAVIGATOR_ID_USERS,
        	service = FormNavigatorContextProvider.class
        )
 
3.  Implement the [`FormNavigatorContextProvider` interface](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/foundation/frontend-taglib/frontend-taglib-form-navigator/src/main/java/com/liferay/frontend/taglib/form/navigator/context/FormNavigatorContextProvider.java), 
    specifying the Form Navigator's model bean class as a generic type. The 
    example below sets the Users form's `User` model bean class as the generic 
    type:

        public class UsersFormNavigatorContextProvider
        	implements FormNavigatorContextProvider<User> {
            ...
          }

    You can determine the model bean class from the name of the ID's constant in
    [`FormNavigatorConstants`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/taglib/ui/FormNavigatorConstants.html).
    The word(s) right after `FORM_NAVIGATOR_ID_` in the constant's name hints at
    the class type. If you can access the Form Navigator's JSP source code, you
    can find the model bean from the `form-navigator` tag's `formModelBean`
    attribute value. You can locate the constant in the 
    [Liferay Portal repository](https://github.com/liferay/liferay-portal)
    using the following pattern: `id="<%= FormNavigatorConstants.[CONSTANT]`.

    For example, if the form navigator's ID is `FORM_NAVIGATOR_ID_USERS`, then
    you would search for `id="<%=
    FormNavigatorConstants.FORM_NAVIGATOR_ID_USERS`; Make sure that the
    `form-navigator` tag's `formModelBean` attribute's value isn't a reference
    to another class. For example, the web content form navigator's
    `formModelBean` attribute value is `article`, but upon further inspection,
    it's clear that `article` is a 
    [reference variable](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/web-experience/journal/journal-web/src/main/resources/META-INF/resources/edit_article.jsp#L29) 
    to the true model bean class: `JournalArticle`. 

4.  Override the `*ContextProvider`'s `getContext()` method. This method also
    takes the Form Navigator's model bean class as a generic type and defines
    the logic for the new context. The example configuration below creates a new
    context called `my.account` for the Users form:
    
        @Override
        public String getContext(User selectedUser) {
        	if (PortletKeys.MY_ACCOUNT.equals(_getPortletName())) {
        		return "my.account";
        	}

        	if (selectedUser == null) {
        		return FormNavigatorContextConstants.CONTEXT_ADD;
        	}

        	return FormNavigatorContextConstants.CONTEXT_UPDATE;
        }
 
    If you are viewing the Users form from the 
    `com_liferay_my_account_web_portlet_MyAccountPortlet` portlet, the 
    `my.account` context is returned. You also need to specify under what 
    circumstances the `add` and `update` contexts are returned. In the example
    above, if the user doesn't exist (i.e. you are creating a new user), the
    `add` context is returned. Otherwise it defaults to the `update` context
    (edit view).

The new context is ready to use in your Form Navigator configurations.
 
A full `*ContextProvider` example class is provided next.

## Context Provider Example class [](id=context-provider-example-class)

Below is an example configuration for the 
[`com.liferay.users.admin.web.servlet.taglib.ui.UsersFormNavigatorContextProvider` class](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/foundation/users-admin/users-admin-web/src/main/java/com/liferay/users/admin/web/servlet/taglib/ui/UsersFormNavigatorContextProvider.java):

    package com.liferay.users.admin.web.servlet.taglib.ui;

    import com.liferay.frontend.taglib.form.navigator.constants.FormNavigatorContextConstants;
    import com.liferay.frontend.taglib.form.navigator.context.FormNavigatorContextProvider;
    import com.liferay.portal.kernel.model.User;
    import com.liferay.portal.kernel.service.ServiceContext;
    import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
    import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorConstants;
    import com.liferay.portal.kernel.theme.PortletDisplay;
    import com.liferay.portal.kernel.theme.ThemeDisplay;
    import com.liferay.portal.kernel.util.PortletKeys;

    import org.osgi.service.component.annotations.Component;

    /**
     * @author Alejandro Tardín
     */
    @Component(
    	property = FormNavigatorContextConstants.FORM_NAVIGATOR_ID + "=" + FormNavigatorConstants.FORM_NAVIGATOR_ID_USERS,
    	service = FormNavigatorContextProvider.class
    )
    public class UsersFormNavigatorContextProvider
    	implements FormNavigatorContextProvider<User> {

    	@Override
    	public String getContext(User selectedUser) {
    		if (PortletKeys.MY_ACCOUNT.equals(_getPortletName())) {
    			return "my.account";
    		}

    		if (selectedUser == null) {
    			return FormNavigatorContextConstants.CONTEXT_ADD;
    		}

    		return FormNavigatorContextConstants.CONTEXT_UPDATE;
    	}

    	private String _getPortletName() {
    		ServiceContext serviceContext =
    			ServiceContextThreadLocal.getServiceContext();

    		ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();

    		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

    		return portletDisplay.getPortletName();
    	}

    }
    
Now you know how to create a Form Navigator context!

## Related Topics [](id=related-topics)

[Configuring Form Navigator Forms](/discover/portal/-/knowledge_base/7-0/configuring-form-navigator-forms)

[Form Navigator Extensions](/develop/tutorials/-/knowledge_base/7-0/form-navigator)
