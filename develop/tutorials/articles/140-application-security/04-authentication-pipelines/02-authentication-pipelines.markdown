# Password-Based Authentication Pipelines [](id=password-based-authentication-pipelines)

By default, once a user submits credentials, those credentials are checked
against @product@'s database, though you can also delegate authentication to an
LDAP server. To use some other system in your environment instead of or in
addition to checking credentials against the database, you can write an
`Authenticator` and insert it as a step in the authentication pipeline. 

Because the `Authenticator` is checked by the Login Portlet, you can't use this
approach if the user must be redirected to the external system or needs a token
to authenticate. In those cases, you should use an 
[Auto Login](/develop/tutorials/-/knowledge_base/7-0/auto-login) or an 
[Auth Verifier](/discover/deployment/-/knowledge_base/7-0/authentication-verifiers). 

`Authenticator`s let you do these things: 

- Log into @product@ with a user name and password maintained in an external 
  system 
- Make secondary user authentication checks 
- Perform additional processing when user authentication fails 

Read on to learn how to create an `Authenticator`. 

## Anatomy of an Authenticator [](id=anatomy-of-an-authenticator)

`Authenticator`s are implemented for various steps in the authentication 
pipeline. Here are the steps: 

1.  `auth.pipeline.pre`: Comes before default authentication to the database. In
    this step, you can skip credential validation against the database.
    Implemented by `Authenticator`. 

2.  Default (optional) authentication to the database. 

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

This example has been stripped down so you can see its structure. First, note 
the `@Component` annotation's contents: 

- `immediate = true`: sets the component to start immediately
- `key=auth.pipeline.post`: sets the `Authenticator` to run in the 
  `auth.pipeline.post` phase. To run the `auth.pipeline.pre` phase, substitute 
  `auth.pipeline.pre`. 
- `service = Authenticator.class`: implements the `Authenticator` service. All 
  `Authenticator`s must do this. 

The three methods below the annotation run based on how you've configured 
authentication: by email address (the default), by screen name, or by user ID.
All the methods throw an `AuthException` in case the `Authenticator` can't
perform its task: if the system it's authenticating against is unavailable or if
some dependency can't be found. The methods in this barebones example return
success in all cases. If you deploy its module, it has no effect. Naturally,
you'll want to provide more functionality. Next is an example that shows you how
to do that. 

## Creating an Authenticator [](id=creating-an-authenticator)

This example is an `Authenticator` that only allows users whose email addresses 
end with *@liferay.com* or *@example.com*. You can implement this using one 
module that does everything. If you think other modules might use the
functionality that validates the email addresses, you should create two modules:
one to implement the `Authenticator` and one to validate email addresses. This
example shows the two module approach. 

To create an `Authenticator`, create a module for your implementation. The most 
appropriate Blade template for this is the 
[service template](/develop/reference/-/knowledge_base/7-0/using-the-service-template). 
Once you have the module, creating the `Activator` is straightforward: 

1.  Add the `@Component` annotation to bind your `Activator` to the appropriate
    authentication pipeline phase. 

2.  Implement the `Authenticator` interface and provide the functionality you
    need. 

3.  Deploy your module. If you're using 
    [Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli), do this via 
    `blade deploy`. 

For this example, you'll do this twice: once for the email address validator
module and once for the `Authenticator` itself. The `Authenticator` project
contains the interface for the validator, and the validator project contains the
implementation. Here's what the `Authenticator` module structure looks like: 

![Figure 1: The Authenticator module contains the validator's interface and the authenticator.](../../../images/auth-pipeline-authenticator-project.png)

Since the `Authenticator` is the most relevant, examine it first: 

    package com.liferay.docs.emailaddressauthenticator;

    import java.util.Map;

    import com.liferay.docs.emailaddressauthenticator.validator.EmailAddressValidator;
    import com.liferay.portal.kernel.log.Log;
    import com.liferay.portal.kernel.log.LogFactoryUtil;
    import com.liferay.portal.kernel.security.auth.AuthException;
    import com.liferay.portal.kernel.security.auth.Authenticator;
    import com.liferay.portal.kernel.service.UserLocalService;

    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;
    import org.osgi.service.component.annotations.ReferenceCardinality;
    import org.osgi.service.component.annotations.ReferencePolicy;

    @Component(
        immediate = true,
        property = {"key=auth.pipeline.post"},
        service = Authenticator.class
    )
    public class EmailAddressAuthenticator implements Authenticator {

        @Override
        public int authenticateByEmailAddress(long companyId, String emailAddress,
                String password, Map<String, String[]> headerMap,
                Map<String, String[]> parameterMap) throws AuthException {
            
            return validateDomain(emailAddress);
        }

        @Override
        public int authenticateByScreenName(long companyId, String screenName,
                String password, Map<String, String[]> headerMap,
                Map<String, String[]> parameterMap) throws AuthException {
            
            String emailAddress = 
                _userLocalService.fetchUserByScreenName(companyId, screenName).getEmailAddress();
            
            return validateDomain(emailAddress);
        }

        @Override
        public int authenticateByUserId(long companyId, long userId,
                String password, Map<String, String[]> headerMap,
                Map<String, String[]> parameterMap) throws AuthException {
            
            String emailAddress = 
                _userLocalService.fetchUserById(userId).getEmailAddress();
            
            return validateDomain(emailAddress);
        }
        
        private int validateDomain(String emailAddress) throws AuthException {
            
            if (_emailValidator == null) {
                
                String msg = "Email address validator is unavailable, cannot authenticate user";			
                _log.error(msg);
                
                throw new AuthException(msg);
            }
            
            if (_emailValidator.isValidEmailAddress(emailAddress)) {		
                return Authenticator.SUCCESS;
            }
            return Authenticator.FAILURE;
        }
        
        @Reference
        private volatile UserLocalService _userLocalService;
        
        @Reference(
            policy = ReferencePolicy.DYNAMIC,
            cardinality = ReferenceCardinality.OPTIONAL
        )
        private volatile EmailAddressValidator _emailValidator;
        
        private static final Log _log = LogFactoryUtil.getLog(EmailAddressAuthenticator.class);
    }

This time, rather than stubs, the three authentication methods contain 
functionality. The `authenticateByEmailAddress` method directly checks the email 
address provided by the Login Portlet. The other two methods, 
`authenticateByScreenName` and `authenticateByUserId` call `UserLocalService` to
look up the user's email address before checking it. The OSGi container injects
this service because of the `@Reference` annotation. Note that the validator is
also injected in this same manner, though it's configured not to fail if the
implementation can't be found. This allows this module to start regardless of
its dependency on the validator implementation. In this case, this is safe
because the error is handled by throwing an `AuthException` and logging the
error. 

Why would you want to do it this way? To err gracefully. Because this is an 
`auth.pipeline.post` `Authenticator`, you presumably have other `Authenticator`s 
checking credentials before this one. If this one isn't working, you want to 
inform administrators with an error message rather than catastrophically failing 
and preventing users from logging in. 

The only other Java code in this module is the Interface for the validator: 

    package com.liferay.docs.emailaddressauthenticator.validator;

    import aQute.bnd.annotation.ProviderType;

    @ProviderType
    public interface EmailAddressValidator {

        public boolean isValidEmailAddress(String emailAddress);
    }

This defines a single method for checking the email address. 

Next, you'll address the validator module. 

![Figure 2: The validator project implements the Validator Interface and depends on the authenticator module. ](../../../images/auth-pipeline-validator-project.png)

This module contains only one class. It implements the Validator interface: 

    package com.liferay.docs.emailaddressvalidator.impl;

    import java.util.Arrays;
    import java.util.HashSet;
    import java.util.Set;
    import org.osgi.service.component.annotations.Component;
    import com.liferay.docs.emailaddressauthenticator.validator.EmailAddressValidator;

    @Component(
        immediate = true,
        property = {
        },
        service = EmailAddressValidator.class
    )
    public class EmailAddressValidatorImpl implements EmailAddressValidator {

        @Override
        public boolean isValidEmailAddress(String emailAddress) {

            if (_validEmailDomains.contains(
                emailAddress.substring(emailAddress.indexOf('@')))) {

                return true;
            }
            return false;
        }

        private Set<String> _validEmailDomains = 
            new HashSet<String>(Arrays.asList(new String[] {"@liferay.com", "@example.com"}));
    }

This code checks to make sure that the email address is from the *@liferay.com* 
or *@example.com* domains. The only other interesting part of this module is the 
Gradle build script, because it defines a compile-only dependency between the 
two projects. This is divided into two files: a `settings.gradle` and a 
`build.gradle`. 

The `settings.gradle` file defines the location of the project (the 
`Authenticator`) the validator depends on: 

    include ':emailAddressAuthenticator'
    project(':emailAddressAuthenticator').projectDir = new File(settingsDir, '../com.liferay.docs.emailAddressAuthenticator')

Since this project contains the interface, it must be on the classpath at 
compile time, which is when `build.gradle` is running: 

    buildscript {
        dependencies {
            classpath group: "com.liferay", name: "com.liferay.gradle.plugins", version: "3.0.23"
        }

        repositories {
            mavenLocal()

            maven {
                url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
            }
        }
    }

    apply plugin: "com.liferay.plugin"

    dependencies {
        compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.0.0"
        compileOnly group: "org.osgi", name: "org.osgi.compendium", version: "5.0.0"

        compileOnly project(":emailAddressAuthenticator")
    }

    repositories {
        mavenLocal()

        maven {
            url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
        }
    }

Note the line in the dependencies section that refers to the `Authenticator` 
project defined in `settings.gradle`. 

When these projects are deployed, the `Authenticator` you defined runs, 
enforcing logins for the two domains specified in the validator. 

If you want to examine these projects further, you can download them 
[in this ZIP file](https://dev.liferay.com/documents/10184/656312/auth-pipelines-authenticator.zip). 

## Related Topics [](id=related-topics)

[Auto Login](/develop/tutorials/-/knowledge_base/7-0/auto-login)

[Writing a Custom Login Portlet](/develop/tutorials/-/knowledge_base/7-0/writing-a-custom-login-portlet)

