# OAuth [](id=oauth)

![EE Only Feature](../../images/ee-feature-web.png)

Liferay's OAuth utility authorizes third-party applications to interact with a
user's resources. It's available as an app from Liferay Marketplace. Let's say
you're hosting Liferay Portal and have users and customers coming to your web
site. You want them to have access to a third party resource, like Twitter, and
be able to access their accounts from your site. In the past, they would have to
provide their Twitter user names and passwords, but not if you use OAuth.
For this reason, a popular characterization for the OAuth client is the "valet key for
your web services."

OAuth is a handshake mechanism where, instead of asking for personal
information, Liferay redirects users to a service provider like Twitter, where
they can tell Twitter to allow Liferay limited access to their accounts. This
example is similar to our earlier "valet key" characterization. You wouldn't
want a valet driver opening your glove box, storage spaces, hood, and other
personal compartments in your vehicle. You would only want the valet to
access what is necessary to park your car. OAuth is based on this same
idea: it gives a site just enough information to do what it needs and nothing
more. This assures users that their personal information is safe, but gives
them freedom to take advantage of valuable resources they typically use from the
service provider's site.

## Registering OAuth Applications [](id=registering-oauth-applications)

The first thing you'll need to do is register an application for OAuth's
services. To access the OAuth Admin page, navigate to the Control Panel and,
under the *Users* heading, select *OAuth Admin*. Then select *Add* to create a
new OAuth application in the OAuth registry. You'll be given the following
options:

**Application Name:** the display name for your application

**Description:** the short description that is attached to your application

**Website URL:** your application's URL

**Callback URI:** the URI where users are redirected after authentication is
complete

**Access Level:** select the *Read* or *Write* access level. For the *Read*
access level, the user can only view the application's contents, but not modify
them. The *Write* access level gives the user permission to access and modify
the application's contents.

After you're finished registering the OAuth app, click *Actions* &rarr; *View*.
You'll notice Liferay generated two Application Credentials: the *Consumer Key*
and *Consumer Secret*. The consumer key is a value used by the application to
identify itself to the service provider. Likewise, the consumer secret is a
value the application uses to establish ownership of the consumer key.

![Figure 14.37: You'll need the application credentials to implement OAuth in your application.](../../images/oauth-app-credentials.png)

Take note of your application credentials; you'll need them when configuring
your application with OAuth. To learn how to configure an application with
OAuth, visit the
[Authorizing Access to Services with OAuth](/develop/tutorials/-/knowledge_base/6-2/authorizing-access-to-services-with-oauth).
tutorial. Once you have your application configured to use OAuth, visit the next
section to begin authorizing requests via OAuth. 

## Authorizing Requests via OAuth [](id=authorizing-requests-via-oauth)

Once you have your application configured to use OAuth, you can place your
application on a page and test out the process. Here is a basic synopsis of
what's happening during the authorization process.

The app you registered in the previous section and configured with the consumer
key and secret in the Developer's Guide is characterized as a service provider.
The service provider uses OAuth to allow users access to its protected
resources. These protected resources are data controlled by the service
provider, which can only be accessed by the application through authentication.
By configuring an application in Liferay to use OAuth, you're keeping all
private information you have between a third-party service provider and the
portal separate. Essentially, OAuth bridges the connection between the portal
and third-party services without the user sharing any protected resources
between them. 

Once your OAuth application is placed on a portal page, you'll be asked to
grant or deny the third-party service provider limited access to your portal.

![Figure 14.38: You can grant or deny the service provider access to your.](../../images/oauth-authorize.png)

Congratulations! You've successfully installed your OAuth app and authorized
access between a service provider and Liferay Portal!
