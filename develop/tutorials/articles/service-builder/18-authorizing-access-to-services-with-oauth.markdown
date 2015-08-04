# Authorizing Access to Services with OAuth [](id=authorizing-access-to-services-with-oauth)

Suppose you wanted users to authenticate to your Liferay Portal plugin from a
provider, like Twitter. You might think that you'd need to store the user's
credentials (e.g., the user's Twitter account name and password), so you could
pass them along with requests to the service provider and log them in. But this
opens up a can of worms. The third party is a moving target: what happens when
they modify their site, and your slick authenticator stops working?
Additionally, you might receive some criticism from your users for asking them
to give their Twitter credentials to you so you can use them to log in. Sounds
like a hassle, right? This is where OAuth comes into play, taking a approach
that is safe and simple. 

OAuth delegates user authentication to the service provider. An OAuth-enabled
plugin uses a token to prove it is authorized to access the user's third-party
profile data and invoke authorized services. By implementing OAuth in your
plugin, you get the best of both worlds--access to an outside service provider,
and your users' trust that the plugin won't have access to their protected
resources. 

In addition, Liferay Portal instances can act as OAuth service providers: you
can provide a means for your users to use their portal credentials to access
other services that have OAuth configured. We refer to such portals as
*Liferay Service Portals*. The OAuth framework lets Liferay Service Portal
administrators specify well-defined service authorizations. Once authorized,
the users can invoke the services via OAuth clients, such as the OAuth-enabled
plugin that you'll learn about in this section. 

+$$$

**Note:** To learn more about the OAuth framework, Liferay OAuth app,
registering your OAuth app, or activating it from a portal page, please refer to
the [OAuth](/discover/portal/-/knowledge_base/6-2/oauth)
documentation. 

$$$

To access portal services using OAuth, you'll need to create a client that uses
an OAuth cycle implementation, along with a user interface to lead your users
through the cycle. In this section, you'll see  an example of a portlet accessing
JSON Web Services from a remote portal. Let's get started by first selecting and
implementing services of an OAuth Client library. 

## Selecting an OAuth Client Library [](id=selecting-an-oauth-client-library)

In order for your portlet to use OAuth, it must have a reference to OAuth
standards for authorization. You can offer your portlet an OAuth client library
by specifying a single JAR file. In this example, Scribe is chosen as the
OAuth library because it's available in Liferay Portal and can be easily
included in a plugin. To use the Scribe OAuth client library, open your plugin's
`liferay-plugin-package.properties` file and insert the `scribe.jar` file as a
portal dependency jar:

    portal-dependency-jars=\
       scribe.jar

That's all you have to do! Your portlet now has access to Scribe's OAuth
library. Next, you'll implement Scribe's OAuth service interface.

## Configuring OAuth's Service Implementation [](id=configuring-oauths-service-implementation)

Now that your portlet can access an OAuth client library, you need to
implement the OAuth services in your portlet. The following code demonstrates
implementing a Scribe OAuth service API:

    import org.scribe.builder.api.DefaultApi10a;
    ...

    public class OAuthAPIImpl extends DefaultApi10a {

        @Override
        protected String getAccessTokenEndpoint() {
            if (Validator.isNull(_accessTokenEndpoint)) {
                _accessTokenEndpoint = OAuthUtil.buildURL(
                    "oauth-portal-host", 80, "http",
                    PortletPropsValues.OSB_LCS_PORTLET_OAUTH_ACCESS_TOKEN_URI);
            }

            return _accessTokenEndpoint;
        }

        @Override
        protected String getRequestTokenEndpoint() {
            if (Validator.isNull(_requestTokenEndpoint)) {
                _requestTokenEndpoint = OAuthUtil.buildURL(
                    "oauth-portal-host", 80, "http",
                    PortletPropsValues.OSB_LCS_PORTLET_OAUTH_REQUEST_TOKEN_URI);
            }

            return _requestTokenEndpoint;
        }

        private String _accessTokenEndpoint;
        private String _requestTokenEndpoint;

    }

In this code snippet, the portlet provides the service platform's OAuth URLs to
Scribe to acquire the access token and request token from the service
provider. A *request token* is a value the portlet uses to obtain user
authorization. It is exchanged for an *access token*. The access token is a
value the portlet uses to gain access to protected resources on behalf of the
user. The exchange of a request token for an access token replaces the need for
supplying the user's service provider credentials. 

In addition to the tokens, you'll also need to provide the callback URL so that
the service platform can redirect the user's browser back to your portlet, once
authentication and authorization is complete. The callback URL can be provided
in an authorization request as a parameter, or it can be specified when
registering your application through Liferay's OAuth Admin menu. Keep in mind
that a callback URL provided via an authorization parameter overrides the
callback setting specified in the OAuth Admin menu. You can specify the callback
URL as an authorization parameter in your portlet's `portlet.properties` file.
You'll see this process later. Here's a code snippet that uses the callback URL
and request token in acquiring the OAuth Service:

    public class OAuthUtil {

        public static String buildURL(
            String hostName, int port, String protocol, String uri) {
            ...
        }

        public static Token extractAccessToken(
            Token requestToken, String oAuthVerifier) {

            Verifier verifier = new Verifier(oAuthVerifier);

            OAuthService oAuthService = getOAuthService();

            return oAuthService.getAccessToken(requestToken, verifier);
        }

        public static String getAuthorizeURL(
            String callbackURL, Token requestToken) {

            if (Validator.isNull(_authorizeRequestURL)) {
                authorizeRequestURL = buildURL(
                "oauth-portal-host", 80, "http",
                PortletPropsValues.OSB_LCS_PORTLET_OAUTH_AUTHORIZE_URI);

                if (Validator.isNotNull(callbackURL)) {
                    authorizeRequestURL = HttpUtil.addParameter(
                        authorizeRequestURL, "oauth_callback",
                        callbackURL);
                }
            }

            _authorizeRequestURL.replace("{0}", requestToken.getToken());
        }

        public static OAuthService getOAuthService() {
            if (_oAuthService == null) {
                ServiceBuilder oAuthServiceBuilder = new ServiceBuilder();

                oAuthServiceBuilder.apiKey(
                    PortletPropsValues.OSB_LCS_PORTLET_OAUTH_CONSUMER_KEY);
                oAuthServiceBuilder.apiSecret(
                    PortletPropsValues.OSB_LCS_PORTLET_OAUTH_CONSUMER_SECRET);
                oAuthServiceBuilder.provider(OAuthAPIImpl.class);

                _oAuthService = oAuthServiceBuilder.build();
            }

            return _oAuthService;
        }

        public static Token getRequestToken() {
            OAuthService oAuthService = getOAuthService();

            return oAuthService.getRequestToken();
        }

        private static String _authorizeRequestURL;
        private static OAuthService _oAuthService;

    }

Besides authorizing the callback URL, you're also implementing methods to acquire
the OAuth service, submit the request to that service, and obtain tokens from
the service. By doing this, you provide OAuth services to your portlet.
You're not quite done yet; you still need to provide information about the OAuth
platform you're accessing. 

First, you need to specify the OAuth protocol context paths for your URLs. In
the case of using Liferay Portal as a service platform, the default paths for
the OAuth portlet are specified in the `auth.public.paths` portal property found
in the [Authentication Pipeline](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Authentication%20Pipeline)
section of Portal's `portal.properties` file. The URLs specified here do not
require authentication to access.

    auth.public.paths=\
        /portal/oauth/access_token,\
        /portal/oauth/authorize,\
        /portal/oauth/request_token

You'll need to transfer these OAuth related constants to your portlet's
`portlet.properties` file. Here's an example code snippet of what these
property settings look like:

    oauth.access.token.uri=/c/portal/oauth/access_token
    oauth.authorize.uri=/c/portal/oauth/authorize?oauth_token={0}
    oauth.consumer.key=42c56e22-d5a2-4003-86f4-cbc34b6de3e3
    oauth.consumer.secret=793195c2936a85649042b24ed843a036
    oauth.request.token.uri=/c/portal/oauth/request_token

Great! Now your OAuth services are implemented and OAuth constants are
specified. Your portlet can now take part in the OAuth authorization process!
You'll just need to set up a simple user interface to start the OAuth cycle.
Let's do this next!

## Creating a User Interface for Authentication [](id=creating-a-user-interface-for-authentication)

Your portlet's user interface must initiate the OAuth cycle the first time it
accesses the OAuth platform for each specific user. Your portlet must
render the OAuth authorization UI automatically when the portlet does not
possess the access token and access secret. The JSP code snippet below initiates
the OAuth authorization process:

    <portlet:actionurl name="setupOAuth" var="setupOAuthURL">
    <%
    Token requestToken = OAuthUtil.getRequestToken();

    portletSession.setAttribute(Token.class.getName(), requestToken);
    %>
    <div class="button-container"%>
        <a class="lcs-portal-link" href="<%= OAuthUtil.getAuthorizeURL(setupOAuthURL, requestToken) %>"><liferay-ui:message key="authorize-access"/>
        </a>
    </div>

On successfully getting authorization from the service provider, the OAuth
platform redirects the user back to the callback URL, which in this case is a URL
for the `setupOAuth` portlet action. This action method uses the request token
to get the access token. It stores the token secret and the token itself. Here's
a snippet of the portlet action method:

    public void setupOAuth(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws Exception {

        PortletSession portletSession = actionRequest.getPortletSession();

        Token requestToken = (Token)portletSession.getAttribute(
            Token.class.getName());

        String oAuthVerifier = ParamUtil.getString(
            actionRequest, "oauth_verifier");

        Token token = OAuthUtil.extractAccessToken(requestToken, oAuthVerifier);

        // store token.getSecret() and token.getToken()
        ...
    }

The figure below shows the OAuth authorization user interface.

![Figure 1: When your portlet is granted access to the service provider, it acquires the access token and access secret.](../../images/oauth-application-authorize.png)

On completing initial OAuth authorization via the UI and on the user revisiting
the portlet instance thereafter, the portlet should render its normal UI. 
Once your portlet is granted access, the OAuth platform redirects the user back
to the callback URL you specified during the portlet's registration.

Once you have the access token and access secret stored, your portlet can use
them to access services such as JSON web services. Here's a simple code example
for this scenario:

    Token token = new Token(getAccessToken(), getAccessSecret());

    String requestURL = OAuthUtil.buildURL(
        "oauth-portal-host", 80, "http",
        "/api/secure/jsonws/context.service/method/parms");

    OAuthRequest oAuthRequest = new OAuthRequest(Verb.POST, requestURL);

    OAuthService oAuthService = OAuthUtil.getOAuthService();

    oAuthService.signRequest(token, oAuthRequest);

    Response response = oAuthRequest.send();

    if (response.getCode() == HttpServletResponse.SC_UNAUTHORIZED) {
        String value = response.getHeader("WWW-Authenticate");

        throw new CredentialException(value);
    }

    if (response.getCode() == HttpServletResponse.SC_OK) {
        // do something with results from response.getBody();
    }

That's it! You've implemented an OAuth client library, created a service
implementation, and developed a user interface to present the OAuth cycle. Of
course, this example and its code snippets are not compatible for all use cases,
but they demonstrate configuring an OAuth-ready application for Liferay Portal. 

## Related Topics [](id=related-topics)

[OAuth](/discover/portal/-/knowledge_base/6-2/oauth)

