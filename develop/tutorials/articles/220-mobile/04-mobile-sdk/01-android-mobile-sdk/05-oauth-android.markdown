# Using OAuth 2 in the Android Mobile SDK [](id=using-oauth-2-in-the-android-mobile-sdk)

You can use 
[OAuth 2](https://oauth.net/2/) 
to authenticate with the following 
[OAuth 2 grant types](https://oauth.net/2/grant-types/): 

-   [**Authorization Code (PKCE for native apps):**](https://oauth.net/2/grant-types/authorization-code/) 
    Redirects users to a page in their mobile browser where they enter their 
    credentials. Following login, the browser redirects users back to the mobile 
    app. User credentials can't be compromised via the app because it never 
	accesses them---it uses a token that can be revoked. This is also useful if
	users don't want to enter their credentials in the app. For example, users
	may not want to enter their Twitter credentials directly in a 3rd-party
	Twitter app, preferring instead to authenticate via Twitter's official
	site. Note that the site you redirect to for authentication must have OAuth
	2 implemented. 

-   [**Resource Owner Password:**](https://oauth.net/2/grant-types/password/) 
    Users authenticate by entering their credentials directly in the app. 

-   [**Client Credentials:**](https://oauth.net/2/grant-types/client-credentials/)
    Authenticates without requiring user interaction. This is useful when the 
    app needs to access its own resources, not those of a specific user. 

This tutorial shows you how to use these grant types with the Mobile SDK. Note 
that before getting started, you may want to see 
[@product@'s OAuth 2.0 documentation](/discover/deployment/-/knowledge_base/7-1/oauth-2-0) 
for instructions on registering an OAuth 2.0 application in the portal. 

## Authorization Code (PKCE) [](id=authorization-code-pkce)

To authenticate via the Authorization Code grant type, you must call this 
`OAuth2SignIn` method: 

    OAuth2SignIn.signInWithRedirect(Activity activity, Session session, String clientId, 
        List<String> scopes, Uri redirectUri, CustomTabsIntent customTabsIntent)

Here are descriptions of this method's parameters: 

-   `activity`: The activity to use to present the mobile browser.
-   `session`: The session that you want to authenticate. Its `server` property 
    must be set. 
-   `clientId`: The ID of the portal's OAuth 2 application that you want to use. 
    To find this value, navigate to that application in the portal's OAuth 2 
    Admin portlet. 
-   `scopes`: The portal permissions to request. You can define a set of 
    permissions associated with an OAuth 2 application in the portal's OAuth 2 
    Admin portlet. Use this property to request a subset of those permissions. 
-   `redirectUri`: The URI that the user is redirected to after successful 
    login in the mobile browser. You must configure this URI in the portal via 
    the OAuth 2 Admin portlet, and associate the URI with the Android app. 
-   `customTabsIntent`: The object used to customize the appearance of the 
    mobile browser window shown for authentication. 

This `signInWithRedirect` method opens the mobile browser to initiate 
authentication. You must also configure the redirect URI in your Android app, 
which sends the user back to the Android app when authentication completes. 

Here's an example of this workflow: 

1.  Configure the redirect URI in the portal via the OAuth2 Administration 
    portlet. In the portal, navigate to *Control Panel* &rarr; *Configuration* 
    &rarr; *OAuth2 Administration* and select or 
    [create](/discover/deployment/-/knowledge_base/7-1/oauth-2-0#creating-an-application) 
    the OAuth 2 application you want to use. Then enter the redirect URI in the 
    *Callback URIs* field. The redirect URI in this example is 
    `my-app://my-app`: 

    ![Figure 1: Enter the redirect URI in the portal's OAuth 2 application you want to use.](../../../../images/mobile-oauth2-redirect-url.png)

2.  In your Android app, register your redirect URI by editing the 
    `AndroidManifest.xml` file. Add the following code to this file: 

        <activity
        android:name="com.liferay.mobile.android.auth.oauth2.OAuth2RedirectActivity"
            tools:node="replace">
            <intent-filter>
                <action android:name="android.intent.action.VIEW"/>
                <category android:name="android.intent.category.DEFAULT"/>
                <category android:name="android.intent.category.BROWSABLE"/>

                <data android:scheme="<your-scheme>"/>
            </intent-filter>
        </activity>

    If you don't want to use a custom scheme and you need to use HTTPS, add this 
    instead: 

        <activity
            android:name="com.liferay.mobile.android.auth.oauth2.OAuth2RedirectActivity"
            tools:node="replace">
            <intent-filter>
                <action android:name="android.intent.action.VIEW"/>
                <category android:name="android.intent.category.DEFAULT"/>
                <category android:name="android.intent.category.BROWSABLE"/>
                <data android:scheme="https"
                      android:host="your.custom.domain"
                      android:path="/oauth2redirect"/>
            </intent-filter>
        </activity>

3.  In the activity in which you want to perform the authentication, call 
    `OAuth2SignIn.signInWithRedirect`:

        private void doLogin() {
            Session session = new SessionImpl("http://my-server.com");
            OAuth2SignIn.signInWithRedirect(this, session, "54321", new ArrayList<>(), 
            Uri.parse("my-app://my-app"), null);
        }

    This opens the mobile browser with the login page.

4.  In the same activity, you must override the method `onActivityResult` to 
    receive the authentication's result. In this method, you do this by calling 
    the method `OAuth2SignIn.resumeAuthorizationFlowWithIntent`: 

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
            super.onActivityResult(requestCode, resultCode, intent);

            if (requestCode == OAuth2SignIn.REDIRECT_REQUEST_CODE) {
                Session session = new SessionImpl("http://my-server.com");
                OAuth2SignIn.resumeAuthorizationFlowWithIntent(this, session, intent,
                new SessionCallback() {
                    @Override
                    public void onSuccess(Session session) {
                        // Login success
                    }

                    @Override
                    public void onFailure(Exception e) {
                        // Login error
                    }
                );
            }
        }

## Resource Owner Password [](id=resource-owner-password)

Authenticating via the Resource Owner Password grant type is similar to
authenticating via the PKCE grant type, except you don't need to configure
a redirect URL. You instead handle the user's credentials directly in your
Android app via a different `OAuth2SignIn` method: 

    public static Session signInWithUsernameAndPassword(String username, String password, Session session,
        String clientId, String clientSecret, List<String> scopes, SessionCallback callback)

Compared to the `OAuth2SignIn.signInWithRedirect ` method used for the PKCE 
grant type, this one requires the user's credentials instead of a redirect URI. 
It also requires the OAuth 2 application's client secret from the portal, and a 
callback. 

Here are descriptions of this method's parameters: 

-   `username`: The user's username. 
-   `password`: The user's password.
-   `session`: The session that you want to authenticate. Its `server` property 
    must be set. 
-   `clientId`: The ID of the portal's OAuth 2 application that you want to use. 
    To find this value, navigate to that application in the portal's OAuth 2 
    Admin portlet. 
-   `clientSecret`: The client secret of the same OAuth 2 application in the 
    portal. 
-   `scopes`: The portal permissions to request. You can define a set of 
    permissions associated with an OAuth 2 application in the portal's OAuth 2 
    Admin portlet. Use this property to request a subset of those permissions. 
-   `callback`: A `SessionCallback` object containing the authentication's 
    result. If authentication succeeds, you receive a non-null session 
    containing the authentication; otherwise you receive an error. 

+$$$

**Note:** You can call the `OAuth2SignIn.signInWithUsernameAndPassword` method 
without a callback by passing `null` in place of the callback. This causes the 
request to execute synchronously. If you provide a callback, the request is 
executed asynchronously in another thread and the callback receives the 
response. 

$$$

Here's an example of calling the `OAuth2SignIn.signInWithUsernameAndPassword` 
method for the Resource Owner Password grant type. After creating the session, 
the method is called with a `SessionCallback` created as an anonymous inner 
class: 

    Session session = new SessionImpl("http://my-server.com");

    OAuth2SignIn.signInWithUsernameAndPassword("username", "password", session, "12345", "12345", 
        new ArrayList<>(), new SessionCallback() {

        @Override
        public void onSuccess(Session session) {
            // Login correct
        }

        @Override
        public void onFailure(Exception e) {
            // Login error
        }
    });

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

To authenticate with the Client Credentials grant type, you must call the 
`OAuth2SignIn.clientCredentialsSignIn` method. Note that this method lacks 
arguments for user credentials or redirect URIs: 

    public static Session clientCredentialsSignIn(Session session, String clientId, String clientSecret,
        List<String> scopes, SessionCallback callback)

Here are descriptions of this method's parameters: 

-   `sesssion`: The session that you want to authenticate. Its `server` property 
    must be set. 
-   `clientId`: The ID of the portal's OAuth 2 application that you want to use. 
    To find this value, navigate to that application in the portal's OAuth 2 
    Admin portlet. 
-   `clientSecret`: The client secret of the same OAuth 2 application in the 
    portal. 
-   `scopes`: The portal permissions to request. You can define a set of 
    permissions associated with an OAuth 2 application in the portal's OAuth 2 
    Admin portlet. Use this property to request a subset of those permissions. 
-   `callback`: A `SessionCallback` object containing the authentication's 
    result. If authentication succeeds, you receive a non-null session 
    containing the authentication; otherwise you receive an error. 

+$$$

**Note:** You can call the `OAuth2SignIn.clientCredentialsSignIn` method without 
a callback by passing `null` in place of the callback. This causes the request 
to execute synchronously. If you provide a callback, the request is executed 
asynchronously in another thread and the callback receives the response. 

$$$

Here's an example of calling the `OAuth2SignIn.clientCredentialsSignIn` method 
for the Resource Owner Password grant type. After creating the session, the 
method is called with a `SessionCallback` created as an anonymous inner class: 

    Session session = new SessionImpl("http://my-server.com");

    OAuth2SignIn.clientCredentialsSignIn(session, "12345", "12345", new ArrayList<>(), 
        new SessionCallback() {

        @Override
        public void onSuccess(Session session) {
            // Login correct
        }

        @Override
        public void onFailure(Exception e) {
            // Login error
        }
    });

## Related Topics [](id=related-topics)

[Using OAuth 2 in Liferay Screens for Android](/develop/tutorials/-/knowledge_base/7-1/using-oauth-2-in-liferay-screens-for-android)

[OAuth 2.0](/discover/deployment/-/knowledge_base/7-1/oauth-2-0)
