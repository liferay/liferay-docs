# Auto Login [](id=auto-login)

While @product@ supports a wide variety of 
[authentication mechanisms](/discover/deployment/-/knowledge_base/7-0/liferay-portal-security), 
you may use a home-grown system or some other product to authenticate users. To 
do so, you can write an Auto Login component to support your authentication 
system. 

Auto Login components can check if the request contains something (a cookie, an
attribute) that can be associated with a user in any way. If the component can
make that association, it can authenticate that user. 

## Creating an Auto Login Component [](id=creating-an-auto-login-component)

Create a 
[Declarative Services component](/develop/tutorials/-/knowledge_base/7-0/creating-modules-with-liferay-ide#creating-component-classes). 
The component should implement the 
`com.liferay.portal.kernel.security.auto.login.AutoLogin` interface. Here's an 
example template: 

    import com.liferay.portal.kernel.security.auto.login.AutoLogin;

    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;

    import org.osgi.service.component.annotations.Component;

    @Component(immediate = true)
    public class MyAutoLogin implements Autologin {

        public String[] handleException(
                HttpServletRequest request, HttpServletResponse response,
                Exception e)
            throws AutoLoginException {

            /* This method is no longer used in the interface and can be 
          left empty */

        }

        public String[] login(
                HttpServletRequest request, HttpServletResponse response)
            throws AutoLoginException {

            /* Your Code Goes Here */

        }

    }

As you can see, you have access to the `HttpServletRequest` and the 
`HttpServletResponse` objects. If your sign-on solution places anything here 
that identifies a user such as a cookie, an attribute, or a parameter, you can
retrieve it and take whatever action you need to retrieve the user information 
and authenticate that user. 

For example, say that there's a request attribute that contains the encrypted 
value of a user key. This can only be there if the user has authenticated with
a third party system that knew the value of the user key, encrypted it, and
added it as a request attribute. You could write code that reads the value,
decrypts it using the same pre-shared key, and uses the value to look up and
authenticate the user. 

The `login` method is where this all happens. This method must return a `String` 
array with three items in this order: 

- The user ID
- The user password
- A boolean flag that's `true` if the password is encrypted and `false` if it's
  not (`Boolean.TRUE.toString()` or `Boolean.FALSE.toString()`). 

Sending redirects is an optional `AutoLogin` feature. Since `AutoLogin`s are 
part of the servlet filter chain, you have two options. Both are implemented by 
setting attributes in the request. Here are the attributes: 

- `AutoLogin.AUTO_LOGIN_REDIRECT`: This key causes `AutoLoginFilter` to stop the
  filter chain's execution and redirect immediately to the location specified
  in the attribute's value. 

- `AutoLogin.AUTO_LOGIN_REDIRECT_AND_CONTINUE`: This key causes
  `AutoLoginFilter` to set the redirect and continue executing the remaining
  filters in the chain. 

Auto Login components are useful ways of providing an authentication mechanism 
to a system that @product@ doesn't yet support. You can write them fairly 
quickly to provide the integration you need. 

## Related Topics [](id=related-topics)

[Password-Based Authentication Pipelines](/develop/tutorials/-/knowledge_base/7-0/password-based-authentication-pipelines)

[Writing a Custom Login Portlet](/develop/tutorials/-/knowledge_base/7-0/writing-a-custom-login-portlet)
