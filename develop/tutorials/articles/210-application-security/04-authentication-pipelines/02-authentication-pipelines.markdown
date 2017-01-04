# Password-Based Authentication Pipelines 

By default, once a user submits credentials to @product@, those credentials are
checked against @product@'s database, though you can also delegate
authentication to an LDAP server. But what if there's some other system in your
environment that should check users' credentials? What if you want to do this
instead of or in addition to checking them against @product@'s database? You
can do exactly this, by writing an `Authenticator` and inserting it as a step in
@product@'s authentication pipeline. 

Because the `Authenticator` is checked by the Login Portlet, you cannot use this
approach if the user must be redirected to the external system or needs a token
to authenticate. In those cases, you should use an 
[Auto Login](/develop/tutorials/-/knowledge_base/7-0/auto-login) or an 
[Auth Verifier](/discover/deployment/-/knowledge_base/7-0/authentication-verifiers). 

`Authenticator`s let you do these things: 

- Log into @product@ using a user name and password maintained in an external
    system
- Make secondary user authentication checks
- Perform additional processing when user authentication fails

Read on to learn how to create an `Authenticator`. 

## Creating an Authenticator

`Authenticator`s are implemented for various steps in the authentication
pipeline. Here are the steps: 

1.  `auth.pipeline.pre`: Comes before default authentication to the @product@
    database. In this step, you can instruct @product@ to skip credential
    validation against the @product@ database. Implemented by `Authenticator`. 

2.  Default (optional) authentication to the @product@ database. 

3.  `auth.pipeline.post`: Further (secondary, tertiary) authentication checks.
    Implemented by `Authenticator`. 

4.  `auth.failure`: Perform additional processing after authentication fails.
    Implemented by `AuthFailure`. 

To create an `Authenticator`, create a module and add a component that
implements the interface: 


    @Component(
        immediate = true, property = {"key=auth.pipeline.post"},
        service = Authenticator.class
    )
    public class MyCustomAuth implements Authenticator {

        public int authenticateByEmailAddress(
                long companyId, String emailAddress, String password,
                Map<String, String[]> headerMap, Map<String, String[]> parameterMap)
            throws AuthException {

    return Authenticator.SUCCESS;
    }

        public int authenticateByScreenName(
                long companyId, String screenName, String password,
                Map<String, String[]> headerMap, Map<String, String[]> parameterMap)
            throws AuthException {

    return Authenticator.SUCCESS;
        }

        public int authenticateByUserId(
                long companyId, long userId, String password,
                Map<String, String[]> headerMap, Map<String, String[]> parameterMap)
            throws AuthException {
            
    return Authenticator.SUCCESS;
        }
    }


