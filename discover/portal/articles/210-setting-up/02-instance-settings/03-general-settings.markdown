# General Settings[](id=general-settings)
 
General virtual instance settings are configured at *Control Panel* &rarr;
*Configuration* &rarr; *Instance Settings*. The General settings are at the top of
the first tab, Configuration.

The General section has three headings: Main Configuration, Navigation, and
Additional Information. Under Main Configuration,

- Set the name of the entity responsible for running the virtual instance.

- Set the mail domain.

- Set the virtual host.

- Set the URLs to a CDN configured to serve static resources.

Under the Navigation heading, set a home page for your virtual instance as well
as default landing and logout pages. To set these pages, use the part of the
page's address that follows your domain. For example, to set the default landing
page to be `http://localhost:8080/web/guest/login`, use `/web/guest/login`. Use
the variable `${liferay:screenName}` as part of the address. This comes in handy
if you want to redirect Users to their personal pages upon login. For example,
the User `marvin` has this default URL to his personal page: 

    http://localhost:8080/user/marvin

To make sure he's directed there on login, place this in the Default Landing
Page field:

    /user/${liferay:screenName}

These URLs can also be set at the system scope, in a `portal-ext.properties`
file:

    default.landing.page.path=
    default.logout.page.path=
    company.default.home.url=

All virtual instances share the values specified in the properties file. Changes
made in Instance Settings override the values set in the properties file.

For more information, see the [Portal Properties documentation](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html).

Under the Additional Information heading, you can specify a Legal name, ID, 
company type, SIC code, ticker symbol, industry and industry type.
