# Using OAuth 2 in Liferay Screens for Android [](id=using-oauth-2-in-liferay-screens-for-android)

You can use 
[OAuth 2](https://oauth.net/2/) 
to authenticate using 
[Login Screenlet](/develop/reference/-/knowledge_base/7-1/loginscreenlet-for-android)
with the following 
[OAuth 2 grant types](https://oauth.net/2/grant-types/): 

-   [**Authorization Code (PKCE for native apps):**](https://oauth.net/2/grant-types/authorization-code/) 
    Redirects users to a page in their mobile browser where they enter their 
    credentials. Following login, the browser redirects users back to the mobile 
    app. User credentials can't be compromised via the app because it never
    accesses them---it uses a revocable token. This is also useful if users
    don't want to enter their credentials in the app. For example, users may not
    want to enter their Twitter credentials directly in a 3rd-party Twitter app,
    preferring instead to authenticate via Twitter's official site. Note that
    the site you redirect to for authentication must have OAuth 2 implemented. 

-   [**Resource Owner Password:**](https://oauth.net/2/grant-types/password/) 
    Users authenticate by entering their credentials directly in the app. 

-   [**Client Credentials:**](https://oauth.net/2/grant-types/client-credentials/)
    Authenticates without requiring user interaction. This is useful when the 
    app needs to access its own resources, not those of a specific user. 

This tutorial shows you how to use these grant types with Login Screenlet. Note 
that before getting started, you may want to see 
[@product@'s OAuth 2.0 documentation](/discover/deployment/-/knowledge_base/7-1/oauth-2-0) 
for instructions on registering an OAuth 2.0 application in the portal. 

## Authorization Code (PKCE) [](id=authorization-code-pkce)

Follow these steps to use the Authorization Code grant type with Login 
Screenlet: 

1.  Configure the URL where the mobile browser redirects after the user 
    authenticates. To do this, follow the first two steps in the 
    [Mobile SDK's Authorization Code instructions](/develop/tutorials/-/knowledge_base/7-1/using-oauth-2-in-the-android-mobile-sdk#authorization-code-pkce). 
    Note that you must configure this URL in both the portal and your Android 
    app. 

2.  Set Login Screenlet's `loginMode` attribute to `oauth2Redirect`. There are
    two ways to do this: 

    -   In code, as the Login Screenlet instance's `authenticationType` 
        variable. You must set this variable via Login Screenlet's 
        `setAuthenticationType` method, using the `AuthenticationType` enum 
        constant `OAUTH2REDIRECT`:

			loginScreenlet.setAuthenticationType(AuthenticationType.OAUTH2REDIRECT); 

    -   When inserting Login Screenlet's XML, set the `loginMode` attribute to 
        `oauth2Redirect`. 

3.  In Login Screenlet's XML, set Login Screenlet's `oauth2ClientId` attribute 
    to the ID of the portal's OAuth 2 application that you want to use. To find 
    this value, navigate to that application in the portal's OAuth 2 Admin 
    portlet. 

4.  In Login Screenlet's XML, set Login Screenlet's `oauth2Redirect` attribute 
    to the URL you configured in step 1. 

    Here's an example of Login Screenlet's XML with the attributes from the 
    preceding steps:

        <com.liferay.mobile.screens.auth.login.LoginScreenlet
            android:id="@+id/login_screenlet"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:loginMode="oauth2Redirect"
            app:oauth2Redirect="my-app://my-app"
            app:oauth2ClientId="54321"
            app:credentialsStorage="shared_preferences"
        />

5.  In your activity that uses Login Screenlet, you must override the 
    `onActivityResult` method to implement the redirect you configured in the 
    first step. You do this by calling Login Screenlet's 
    `resumeOAuth2RedirectFlow` method: 

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
            super.onActivityResult(requestCode, resultCode, intent);

            if (requestCode == OAuth2SignIn.REDIRECT_REQUEST_CODE) {
                loginScreenlet.resumeOAuth2RedirectFlow(intent);
            }
        }

## Resource Owner Password [](id=resource-owner-password)

Follow these steps to use the Resource Owner Password grant type with Login 
Screenlet: 

1.  Set Login Screenlet's `loginMode` attribute to `oauth2UsernameAndPassword`.
    There are two ways to do this: 

    -   In code, as the Login Screenlet instance's `authenticationType` 
        variable. You must set this variable via Login Screenlet's 
        `setAuthenticationType` method, using the `AuthenticationType` enum 
        constant `OAUTH2USERNAMEANDPASSWORD`:

            loginScreenlet.setAuthenticationType(AuthenticationType.OAUTH2USERNAMEANDPASSWORD);

    -   When inserting Login Screenlet's XML, set the `loginMode` attribute to 
        `oauth2UsernameAndPassword`.

2.  In Login Screenlet's XML, set Login Screenlet's `oauth2ClientId` attribute 
    to the ID of the OAuth 2 application that you want to use. To find this
    value, navigate to that application in the OAuth 2 Admin portlet. 

3.  In Login Screenlet's XML, set Login Screenlet's `oauth2ClientSecret` 
    attribute to the same OAuth 2 application's client secret. 

    Here's an example of Login Screenlet's XML with the attributes from the 
    preceding steps: 

        <com.liferay.mobile.screens.auth.login.LoginScreenlet
            android:id="@+id/login_screenlet"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:loginMode="oauth2UsernameAndPassword"
            app:oauth2ClientId="54321"
            app:oauth2ClientSecret="12345"
            app:basicAuthMethod="email"
            app:credentialsStorage="shared_preferences"
        />

## Client Credentials [](id=client-credentials)

The OAuth 2 Client Credentials grant type authenticates without requiring user 
interaction. This is useful when the app needs to access its own resources, not 
those of a specific user. 

+$$$

**Warning:** The Client Credentials grant type poses a security risk to the 
portal. To authenticate without user credentials, the mobile app must contain 
the OAuth 2 application's client ID and client secret. Anyone who can access 
those values via the mobile app can also authenticate without user credentials. 

$$$

Follow these steps to use the Client Credentials grant type in your Android app: 

1.  Follow the 
    [Android Mobile SDK instructions](/develop/tutorials/-/knowledge_base/7-1/using-oauth-2-in-the-android-mobile-sdk#client-credentials) 
    for using the Client Credentials grant type. 

2.  The session object contains a valid authentication object. Pass the session 
    as an argument to the `SessionContext` method `createOAuth2Session`: 

        SessionContext.createOAuth2Session(session);

    This initializes the Screens `SessionContext` object, authenticating any 
    Screenlets that you use in the Android app. 

## Related Topics [](id=related-topics)

[Using OAuth 2 in the Android Mobile SDK](/develop/tutorials/-/knowledge_base/7-1/using-oauth-2-in-the-android-mobile-sdk)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/7-1/using-screenlets-in-android-apps)

[OAuth 2.0](/discover/deployment/-/knowledge_base/7-1/oauth-2-0)
