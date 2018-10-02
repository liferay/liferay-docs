# Using OAuth 2 in the iOS Mobile SDK [](id=using-oauth-2-in-the-ios-mobile-sdk)

The Liferay Mobile SDK for iOS lets you use 
[OAuth 2](https://oauth.net/2/) 
to authenticate to the portal. You can use the following 
[OAuth 2 grant types](https://oauth.net/2/grant-types/): 

-   [**Authorization Code (PKCE for native apps):**](https://oauth.net/2/grant-types/authorization-code/) 
    Redirects users to a page in their mobile browser where they enter their 
    credentials. Following login, the browser redirects users back to the mobile 
    app. User credentials can't be compromised via the app because it never 
    accesses them--it uses a token that can be easily revoked. This is also 
    useful if users don't want to enter their credentials in the app. For 
    example, users may not want to enter their Twitter credentials directly in a 
    3rd-party Twitter app, preferring instead to authenticate via Twitter's 
    official site. Note that the site you redirect to for authentication must 
    have OAuth 2 implemented. 

-   [**Resource Owner Password:**](https://oauth.net/2/grant-types/password/) 
    Users authenticate by entering their credentials directly in the app. 

-   [**Client Credentials:**](https://oauth.net/2/grant-types/client-credentials/)
    Authenticates without requiring user interaction. This is useful when the 
    app needs to access its own resources, not those of a specific user. 

This tutorial shows you how to use these grant types with the Mobile SDK. 

## Authorization Code (PKCE) [](id=authorization-code-pkce)

To authenticate via the Authorization Code grant type, you must call the 
following `LROAuth2SignIn` method: 

    LROAuth2SignIn.signIn(withRedirectURL: URL, session: LRSession, clientId: String, 
        scopes: [String], callback: (LRSession?, Error?) -> Void) -> LROAuth2AuthorizationFlow

Here are descriptions of this method's parameters: 

-   `withRedirectURL`: The URL that the user is redirected to after successful 
    login in the mobile browser. You must configure this URL in the portal via 
    the OAuth 2 Admin portlet, and 
    [associate the URL with the iOS app](https://developer.apple.com/documentation/uikit/core_app/communicating_with_other_apps_using_custom_urls). 
-   `session`: The session that you want to authenticate. Its `server` property 
    must be set. 
-   `clientId`: The ID of the portal's OAuth 2 application that you want to use. 
    To find this value, navigate to that application in the portal's OAuth 2 
    Admin portlet. 
-   `scopes`: The portal permissions to request. You can define a set of 
    permissions associated with an OAuth 2 application in the portal's OAuth 2 
    Admin portlet. Use this property to request a subset of those permissions. 
    <!-- Why doesn't the example app use this property? -->
-   `callback`: A function called with the authentication's result. If 
    authentication succeeds, you receive a non-null session containing the 
    authentication; otherwise you receive an error. 

This `LROAuth2SignIn.signIn` method returns an `LROAuth2AuthorizationFlow` 
object that represents an ongoing authentication request. You must save this as 
an `AppDelegate` property and then call the `LROAuth2AuthorizationFlow` method 
`resumeAuthorizationFlowWithURL` in the `application(_:open:options:)` method. 

Here's an example of this workflow: 

1.  Configure the redirect URL in the portal via the OAuth 2 Admin portlet. In 
    the portal, navigate to *Control Panel* &rarr; *OAuth 2 Admin* and select 
    the OAuth 2 application you want to use. Then enter the redirect URL in the 
    *Callback URIs* field. The redirect URL in this example is 
    `my-app://my-app`. 

    ![Figure 1: Enter the redirect URL in the portal's OAuth 2 application you want to use.](../../../../images/mobile-oauth2-redirect-url.png)

2.  In your iOS app, register your redirect URL via the *Info* tab in your 
    project settings. For instructions on this, see the section *Register Your 
    URL Scheme* in 
    [Apple's documentation on using custom URLs](https://developer.apple.com/documentation/uikit/core_app/communicating_with_other_apps_using_custom_urls). 

    ![Figure 2: Register the redirect URL in your iOS app.](../../../../images/ios-register-url.png)

3.  In your `AppDelegate`, create a `LROAuth2AuthorizationFlow` property. You'll 
    set this property later when you call the `LROAuth2SignIn.signIn` method: 

        var authorizationFlow: LROAuth2AuthorizationFlow?

4.  In the view controller in which you'll call `LROAuth2SignIn.signIn`, define 
    the callback that runs with the authentication's result. This callback can 
    perform any action you need. In this example, if the authentication succeeds 
    the callback prints a success message and calls a sample method that tests 
    the session's user credentials; otherwise it prints an error message: 

        let oauth2Callback: (LRSession?, Error?) -> Void = { session, error in
            if let session = session {
                print("Login successful")
                testCredentials(session: session)
            }
            else {
                print(error!)
            }
        }

5.  In the same view controller, call the `LROAuth2SignIn.signIn` method with 
    the above parameters. Set the resulting `LROAuth2AuthorizationFlow` to the 
    `AppDelegate` property you created in step 3. This example does this in a 
    `loginWithRedirect()` method: 

        func loginWithRedirect() {
            let session = LRSession(server: "http://my-server.com")
            let redirectUrl = URL(string: "my-app://my-app")!
            let clientIdRedirect = "54321"

            let authorizationFlow = LROAuth2SignIn.signIn(withRedirectURL: redirectUrl,
                session: session, clientId: clientIdRedirect, scopes: [], callback: oauth2Callback)

            (UIApplication.shared.delegate as! AppDelegate).authorizationFlow = authorizationFlow
        }

6.  In your `AppDelegate`'s `application(_:open:options:)` method, call the 
    `LROAuth2AuthorizationFlow` method `resumeAuthorizationFlow` with the URL. 
    For more information on the `application(_:open:options:)` method, see the 
    section *Handle Incoming URLs* in 
    [Apple's documentation on using custom URLs](https://developer.apple.com/documentation/uikit/core_app/communicating_with_other_apps_using_custom_urls): 

        @UIApplicationMain
        class AppDelegate: UIResponder, UIApplicationDelegate {

            ...

            var authorizationFlow: LROAuth2AuthorizationFlow?

            func application(_ app: UIApplication, open url: URL,
                options: [UIApplicationOpenURLOptionsKey : Any] = [:]) -> Bool {

                if let authorizationFlow = authorizationFlow {
                    return authorizationFlow.resumeAuthorizationFlow(with: url)
                }
            }

            ...

        }

## Resource Owner Password [](id=resource-owner-password)

Authenticating via the Resource Owner Password grant type is similar to doing so 
for the PKCE grant type, except you don't need to configure a redirect URL. You 
instead handle the user's credentials directly in your iOS app via a slightly 
different `LROAuth2SignIn.signIn` method: 

    LROAuth2SignIn.signIn(withUsername: String, password: String, session: LRSession, clientId: String, 
        clientSecret: String, scopes: [String], callback: (LRSession?, Error?) -> Void) -> LRSession?

Compared to the `LROAuth2SignIn.signIn` method used for the PKCE grant type, 
this one requires the user's credentials instead of a redirect URL. It also 
requires the OAuth 2 application's client secret from the portal. 

Here are descriptions of this method's parameters: 

-   `withUsername`: The user's username. 
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
    <!-- Why doesn't the example app use this property? -->
-   `callback`: A function called with the authentication's result. If 
    authentication succeeds, you receive a non-null session containing the 
    authentication; otherwise you receive an error. 

+$$$

**Note:** You can call the `LROAuth2SignIn.signIn` method without a callback by 
passing `nil` as the `callback` argument. This causes the request to execute 
synchronously. If you provide a callback, the request is executed asynchronously 
in another thread and the callback receives the response. 

$$$

Follow these steps to call the `LROAuth2SignIn.signIn` method for the Resource 
Owner Password grant type: 

1.  If you want to provide a callback, define it in the view controller in which 
    you'll call `LROAuth2SignIn.signIn`. This callback can perform any action 
    you need. In this example, if the authentication succeeds the callback 
    prints a success message and calls a sample method that tests the session's 
    user credentials; otherwise it prints an error message: 

        let oauth2Callback: (LRSession?, Error?) -> Void = { session, error in
            if let session = session {
                print("Login successful")
                testCredentials(session: session)
            }
            else {
                print(error!)
            }
        }

2.  In the same view controller, call the `LROAuth2SignIn.signIn` method with 
    the above parameters. This example does this in a 
    `loginWithUsernameAndPassword()` method: 

        func loginWithUsernameAndPassword() {
            if password.isEmpty {
                fatalError("you have to enter the password")
            }

            let session = LRSession(server: "http://my-server.com")
            let clientId = "12345"
            let clientSecret = "12345"

            _ = try? LROAuth2SignIn.signIn(withUsername: "test@liferay.com", password: password,
                        session: session, clientId: clientId, clientSecret: clientSecret, scopes: [], 
                        callback: oauth2Callback)
        }

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
`LROAuth2SignIn.signIn` method that lacks arguments for user credentials or 
redirect URLs: 

    LROAuth2SignIn.clientCredentialsSignIn(with: LRSession, clientId: String, 
        clientSecret: String, scopes: [String], callback: (LRSession?, Error?) -> Void)

Here are descriptions of this method's parameters: 

-   `with`: The session that you want to authenticate. Its `server` property 
    must be set. 
-   `clientId`: The ID of the portal's OAuth 2 application that you want to use. 
    To find this value, navigate to that application in the portal's OAuth 2 
    Admin portlet. 
-   `clientSecret`: The client secret of the same OAuth 2 application in the 
    portal. 
-   `scopes`: The portal permissions to request. You can define a set of 
    permissions associated with an OAuth 2 application in the portal's OAuth 2 
    Admin portlet. Use this property to request a subset of those permissions. 
    <!-- Why doesn't the example app use this property? -->
-   `callback`: A function called with the authentication's result. If 
    authentication succeeds, you receive a non-null session containing the 
    authentication; otherwise you receive an error. 

+$$$

**Note:** You can call the `LROAuth2SignIn.signIn` method without a callback by 
passing `nil` as the `callback` argument. This causes the request to execute 
synchronously. If you provide a callback, the request is executed asynchronously 
in another thread and the callback receives the response. 

$$$

Follow these steps to call the `LROAuth2SignIn.signIn` method for the Client 
Credentials grant type: 

1.  If you want to provide a callback, define it in the view controller in which 
    you'll call `LROAuth2SignIn.signIn`. This callback can perform any action 
    you need. In this example, if the authentication succeeds the callback 
    prints a success message and calls a sample method that tests the session's 
    user credentials; otherwise it prints an error message: 

        let oauth2Callback: (LRSession?, Error?) -> Void = { session, error in
            if let session = session {
                print("Login successful")
                testCredentials(session: session)
            }
            else {
                print(error!)
            }
        }

2.  In the same view controller, call the `LROAuth2SignIn.signIn` method with 
    the above parameters. This example does this in a 
    `loginWithClientCredentials()` method: 

        func loginWithClientCredentials() {

            let session = LRSession(server: "http://my-server.com")
            let clientId = "12345"
            let clientSecret = "12345"

            _ = try? LROAuth2SignIn.clientCredentialsSignIn(with: session, clientId: clientId, 
                        clientSecret: clientSecret, scopes: [], callback: oauth2Callback)
        }

## Related Topics [](id=related-topics)

[Using OAuth 2 in Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/7-1/using-oauth-2-in-liferay-screens-for-ios)
