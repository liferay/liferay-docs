# Writing a Custom Login Portlet [](id=writing-a-custom-login-portlet)

If you need to customize your users' authentication experience completely, you
can write your own Login Portlet. The mechanics of this on the macro level are
no different from writing any other portlet, so if you need to familiarize
yourself with that, please see the 
[portlets section of tutorials](/develop/tutorials/-/knowledge_base/7-0/portlets). 

This tutorial shows only the relevant parts of a 
[Liferay MVC
Portlet](/develop/tutorials/-/knowledge_base/7-0/liferay-mvc-portlet) that
authenticates the user. You'll learn how to call the 
[authentication pipeline](/develop/tutorials/-/knowledge_base/7-0/authentication-pipelines) 
and then redirect the user to a location of your choice. 

## Authenticating to @product@ [](id=authenticating-to-product)

You can use the example project 
[in this ZIP file](https://dev.liferay.com/documents/10184/656312/MyCustomLoginPortlet.zip) 
as a starting point for your own. When using the example project please use the following 
portal property ```session.timeout.auto.extend.offset=45```. This property will help you
avoid a known issue while learning how to work with a custom login portlet.

It has only one view, which is used for logging in or showing the user who is
already logged in: 

    <%@ include file="/init.jsp" %>

    <p>
        <b><liferay-ui:message key="myloginportlet_MyLogin.caption"/></b>
    </p>

    <c:choose>
        <c:when test="<%= themeDisplay.isSignedIn() %>">

            <%
            String signedInAs = HtmlUtil.escape(user.getFullName());

            if (themeDisplay.isShowMyAccountIcon() && (themeDisplay.getURLMyAccount() != null)) {
                String myAccountURL = String.valueOf(themeDisplay.getURLMyAccount());

                signedInAs = "<a class=\"signed-in\" href=\"" + HtmlUtil.escape(myAccountURL) + "\">" + signedInAs + "</a>";
            }
            %>

            <liferay-ui:message arguments="<%= signedInAs %>" key="you-are-signed-in-as-x" translateArguments="<%= false %>" />
        </c:when>
        <c:otherwise>
        
            <%
            String redirect = ParamUtil.getString(request, "redirect");
            %>
        
            <portlet:actionURL name="/login/login" var="loginURL">
                <portlet:param name="mvcRenderCommandName" value="/login/login" />
            </portlet:actionURL>
            
            <aui:form action="<%= loginURL %>" autocomplete='on' cssClass="sign-in-form" method="post" name="loginForm">
            
                <aui:input name="saveLastPath" type="hidden" value="<%= false %>" />
                <aui:input name="redirect" type="hidden" value="<%= redirect %>" />
                        
                <aui:input autoFocus="true" cssClass="clearable" label="email-address" name="login" showRequiredLabel="<%= false %>" type="text" value="">
                    <aui:validator name="required" />
                </aui:input>

                <aui:input name="password" showRequiredLabel="<%= false %>" type="password">
                    <aui:validator name="required" />
                </aui:input>
                
                <aui:button-row>
                    <aui:button cssClass="btn-lg" type="submit" value="sign-in" />
                </aui:button-row>
                    
            </aui:form>
        </c:otherwise>
    </c:choose>

Note that in the form, authentication by email address (the default setting) is
hard-coded, as this is an example project. The current page is sent as a hidden
field on the form so the portlet can redirect the user to it, but you can of
course set this to any value you want. 

The portlet handles all processing of this form using a single 
[Action Command](/develop/tutorials/-/knowledge_base/7-0/mvc-action-command) 
(imports left out for brevity): 

    @Component(
        property = {
            "javax.portlet.name=MyLoginPortlet",
            "mvc.command.name=/login/login"
        },
        service = MVCActionCommand.class
    )
    public class MyLoginMVCActionCommand extends BaseMVCActionCommand {

        @Override
        protected void doProcessAction(ActionRequest actionRequest,
                ActionResponse actionResponse) throws Exception {

            ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
                WebKeys.THEME_DISPLAY);
            
            HttpServletRequest request = PortalUtil.getOriginalServletRequest(
                PortalUtil.getHttpServletRequest(actionRequest));
            
            HttpServletResponse response = PortalUtil.getHttpServletResponse(
                actionResponse);

            String login = ParamUtil.getString(actionRequest, "login");
            String password = actionRequest.getParameter("password");
            boolean rememberMe = ParamUtil.getBoolean(actionRequest, "rememberMe");
            String authType = CompanyConstants.AUTH_TYPE_EA;
            
            AuthenticatedSessionManagerUtil.login(
                request, response, login, password, rememberMe, authType);
            
            actionResponse.sendRedirect(themeDisplay.getPathMain());
        }

    }

The only tricky/unusual code here is the need to grab the `HttpServletRequest` 
and the `HttpServletResponse`. This is necessary to call @product@'s API for 
authentication. At the end of the Action Command, the portlet sends a redirect 
that sends the user to the same page. You can of course make this any page you 
want. 

Implementing your own login portlet gives you complete control over the
authentication process. 

## Portal property changes needed when working with the example project

As noted above when using the example project please make sure that 
```session.timeout.auto.extend.offset=45``` has been set within the 
portal-ext.properties file for Liferay bundle that will be hosting the 
example project. The declared 45 seconds above are a starting point and
any optimization of the property will need to be determined as the 
environment is utilized.

This is needed due to a change implemented by LPS-68543 which sets 
```session.timeout.auto.extend.offset=0```as the default within the Liferay
bundle. When this property is set to 0 an issue can occur when using this 
example project where the browser will execute an "extend_session" call which
will force any users who attempt to login to have to log in twice in order to 
access the bundle.


## Related Topics [](id=related-topics)

[Password-Based Authentication Pipelines](/develop/tutorials/-/knowledge_base/7-0/password-based-authentication-pipelines)

[Auto Login](/develop/tutorials/-/knowledge_base/7-0/auto-login)
