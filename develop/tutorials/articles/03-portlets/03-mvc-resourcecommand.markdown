# MVC Resource Command

When using Liferay's MVC framework, you can create resource URLs in your JSPs to 
retrieve images, XML, or any other kind of resource from a Liferay instance. The 
resource URL then invokes the corresponding MVC resource command class that 
processes the resource request and response. 

First, use the `<portlet:resourceURL>` tag to create the resource URL in a JSP. 
For example, the Login Portlet's 
[`create_account.jsp` file](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/login/login-web/src/main/resources/META-INF/resources/create_account.jsp) 
defines the following resource URL for retrieving a CAPTCHA image during account 
creation: 

    <portlet:resourceURL id="/login/captcha" var="captchaURL" />


