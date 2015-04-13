# Invoking Services Using Skinny JSON Provider [](id=invoking-services-using-skinny-json-provider)

**Important:** This is a "Labs" application. Any app designated as "Labs" is
experimental in features and is not supported by Liferay. This status may change
without notice. Labs apps allow us to accelerate the availability of useful and
cutting-edge features, but these apps are not yet hardened like standard Liferay
EE apps. Please download and use Labs apps at your own discretion. 

It's the classic story of a superhero trying to maintain his secret
identity as a mild-mannered reporter. He's trying to make a living while
maintaining a low profile. Enter the overbearing boss, with furled brow and a
cigar clenched in his teeth. 

"Hey you!" barks the boss, "I want coverage on the hoopla going on in the
lower-east side." 

"Uh, yes sir. Right away sir," says the hero as he fuddles around for his pen
and notepad. 

"And you better shoot straight this time. No nonsense. I want the skinny on
this one!" exclaims the boss. 

The hero grabs his coat and clumsily knocks into the corner of his desk as he
makes for the office door. Before walking out, he pauses in the doorway to
look at his boss and says, "Sir; I won't let you down." 

"Well? What are you waiting for? Get moving!" hollers the boss. 

The hero cowers away quickly in a hurried mess. 

Maybe you've been asked to get "the skinny" on Liferay Portal data. Your gruff
boss demands it or you're personally anxious to get the data to use in a slick
mobile app you're developing. Either way, you want information from the portal
and you want it now. The *Skinny JSON Provider* app is your ticket to getting
skinny data. 

The Skinny JSON Provider offers web services for retrieving web content
articles and DDLs, but returns them in a simplified, "skinny" JSON fashion. As a
convenience, the Skinny JSON provider performs additional parsing and
processing, to return the bare essentials of each article and DDL. Each web
content article representation is comprised of its associated web content
structure fields. Each DDL record representation is comprised of its data
definition fields. The returned values are light-weight and easy to read,
making them ideal to use in browsers and mobile applications. 

The Skinny JSON Provider app is available through the Liferay Marketplace.
You'll find it categorized as a Utility app. To learn how to install it, you can
read the section
[Downloading and Installing Apps](/discover/portal/-/knowledge_base/6-2/downloading-and-installing-apps).

The app adds two new web service APIs that you can configure like other Liferay
web services. Here is the context and the class name that you must specify to
access them:

- *Plugin Context Name:* `skinny-web`
- *Service Class Name:* `skinny`

To retrieve web content articles, use the `get-skinny-journal-articles` service.
To retrieve Dynamic Data Lists, use the `get-skinny-ddl-records` service. Note
that both services include "skinny" in their names. Both services are accessible
from Liferay's JSON Web Service Console. 

![Figure 1: You can access Skinny JSON web services from JavaScript, cURL, URLs in your browser, and from Liferay's trusty JSON Web Service API browser interface.](../../images/jsonws-api-services-01.png)

Before invoking the `get-skinny-journal-articles` and `get-skinny-ddl-records`
services, you should understand how to use the Skinny JSON Provider anonymously 
and how to use it in a Cross-Site Request Forgery (CSRF) protected environment. 

## Accessing the Services Anonymously and in CSRF Protected Environments [](id=accessing-the-services-anonymously-and-in-csrf-protected-environments)

Retrieving data through an anonymous request to a web service is a typical use
case for Skinny JSON Provider. You can configure Liferay to allow anonymous
access to the Skinny JSON Provider. The configuration varies depending on the
version of Liferay you are using. 

For Liferay 6.2 and later, the app is preconfigured to allow anonymous access by
default. The underlying implementation uses the `@AccessControlled` annotation
to accomplish this. Note that this preconfiguration can only be changed by
modifying the plugin's source code and recompiling the plugin. 

For Liferay 6.1, you must add the value `getSkinny*` as a public JSON web
service method entry in your `portal-ext.properties` file to enable anonymous
access to the services. 

    jsonws.web.service.public.methods=getSkinny*

Even if you've made authentication required for the Skinny Provider methods, all
of Liferay's JSON web services require you to use a `p_auth` token for CSRF
protection in environments that pass sensitive per-user data, like browser
cookies. If your application is running in such an environment (e.g., JavaScript
in a browser) and you're calling Liferay's JSON web services, you should never
disable CSRF checking. 

The table below shows various ways that you can specify a p_auth token. 

**Specifying a p_auth Token:**

Invocation Type        | Parameter and Value
---------------------- | -------------------
JavaScript JSON object | `p_auth: 'value'`
curl                   | `-d pauth='value'`
URL                    | `p_auth/value`

You can substitute `value` with the value of your p_auth token. 

If your app is running in a simpler environment like that of a custom mobile
app, and you're okay with foregoing the risks involved with disabling CSRF
protection, you can disable that protection. The mechanism for disabling CSRF
protection varies depending on the version of Liferay you are using. 

For Liferay 6.2 and later, you can disable CSRF protection for services that you
believe don't need checking. To disable CSRF protection for the Skinny
Provider's services, you can open access to the app's plugin context and service
class by adding their values to the list of `auth.token.ignore.origins` values
in your `portal-ext.properties`. Here's a `auth.token.ignore.origins` property
setting with these two values: 

    auth.token.ignore.origins=\
        com.liferay.portal.jsonwebservice.JSONWebServiceServiceAction:/skinny-web.skinny/,\
        com.liferay.portal.jsonwebservice.JSONWebServiceServiceAction:/skinny/

For Liferay 6.1, the only way to disable CSRF checking and disable the `p_auth`
token requirement is by globally disabling them. You can globally disable them
by specifying the following setting in your `portal-ext.properties`: 

    json.service.auth.token.enabled=false

Note that we do not recommend that you disable CSRF checking. For further
information about Cross-Site Request Forgery, please see [the Wikipedia article](http://en.wikipedia.org/wiki/Cross-site_request_forgery) 
on the topic. 

Now that you've configured appropriate access for your Skinny JSON Provider app,
you can call its services!

## Retrieving Skinny Entities [](id=retrieving-skinny-entities)

It's the moment you've been waiting for: getting skinny streamlined
representations of web content articles and dynamic data lists. In the
following sections, you'll learn how to invoke their services and also
how to find values to populate the service parameters. And you'll see what the
returned JSON objects look like. If you're itching to access DDLs, then you
might want to skip ahead to the section
[Retrieving Skinny Dynamic Data Lists](#retrieving-skinny-dynamic-data-lists).
The next section shows you how to access skinny web content articles. 

### Retrieving Skinny Web Content Articles [](id=retrieving-skinny-web-content-articles)

The `get-skinny-journal-articles` service is designed to retrieve all web
content articles that are based on a given web content structure, in a given
site or organization. To retrieve skinny web content articles, you must specify
values for the following parameters: 

- `companyId` - The ID of the company in which the articles exist.
- `groupName` - The human-readable name of the group (site or organization) in
  which the articles exist.
- `ddmStructureId` - The dynamic data mapping structure ID of the web content
  structure on which the desired articles are based.
- `locale` (optional) - The name of the locale for which you want localized
  articles (e.g., `en_US`, `es_ES`, `fr`, etc.). If you omit this argument, the
  default locale of the server is used. If you specify a locale and no such
  localization exists for one of the articles, the article's default locale is
  used.

To demonstrate using the web service, imagine a web content structure that has
the following fields:

- `title` - A Text field with a title.
- `image` - A Documents and Media Library field for referencing an image file.
- `date` - A Date field (Date fields for web content structures are supported in
  Liferay Portal 6.2 and later).
- `toggle` - a Boolean field.

Say you have many articles based on this structure, and you wish to
retrieve them through the `get-skinny-journal-articles` service. Here is an
example JavaScript invocation to retrieve the articles:

    Liferay.Service(
      '/skinny-web.skinny/get-skinny-journal-articles',
      {
        companyId: 10157,
        groupName: 'Guest',
        ddmStructureId: '15521',
        locale: 'en_US'
      },
      function(obj) {
        console.log(obj);
      }
    );

You could make the same service invocation by specifying the following URL in
your browser: 

    http://localhost:8080/api/jsonws/skinny-web.skinny/get-skinny-journal-articles/company-id/10157/group-name/Guest/ddm-structure-id/15521/locale/en_US

Note that the URL above specifies the DDM record ID parameter
`ddm-structure-id`, which has a slightly different name than the JavaScript
parameter `ddmStructureId`. Also, when invoking a service via a URL, make sure
to URL-encode any parameters that contain special characters. 

Now that you've seen a couple example invocations, you're might be wondering how
to get the different ID values to pass in to the service. For starters, you'll
need your portal's company ID. Here are steps you can follow to find that ID
value: 

1.  Sign in to your portal.
2.  To view your user account, click on your profile picture and select *My
    Account*.
3.  Note the numeric value of your *User ID*.
4.  In your browser's address bar, specify the URL to your portal's JSON web
    services. Here's an example URL for services on port `8080` of a portal
    running on a local host.

        http://localhost:8080/api/jsonws/

5.  With the slash character (`/`) selected as the *Context Path*, search for
    service `get-user-roles` and select the link to the service under *Role*.
6.  Specify your user ID in the *userId* field and click on *Invoke*. 
7.  From the *Result* text area, copy the value returned next to the
    `companyId`. 

Note your portal's company ID so that you can specify it when invoking Skinny
Provider's services. As mentioned previously, you'll need to know your web
content article DDM Structure ID too. You'll learn how to get that next. 

On adding or editing a web content article, you can select a structure for the
article. The structure selection screen displays a listing of web content
structures. Each web content structure has an ID that you can give to the Skinny
Provider web service as a DDM structure ID value.

![Figure 2: On adding or configuring a structure for web content, the structure's ID is displayed. You can specify the ID as the value for the DDM structure ID in querying for the articles associated with the structure.](../../images/web-content-structure-listing.png)

Now that you know your portal's company ID and your DDM structure's ID, you can
invoke Skinny Provider's `get-skinny-journal-articles` service. 

+$$$

**Note:** there is a known issue
[LRDCOM-11877](https://issues.liferay.com/browse/LRDCOM-11877) in which invoking
services, such as Skinny Provider's services, via the JSON Web Services console
erroneously returns empty result sets. 

$$$

The `get-skinny-journal-articles` service returns a `JSONArray` object
containing zero or more articles. The following example return value shows a
`JSONArray` object that includes web content articles:

    [
        {
            "dynamicElements": {
                "title": "Liferay Portal Welcome",
                "toggle": "true",
                "image": "/documents/10184/0/welcome_tools/9921a6ff-2a83-414e-b629-d174ea7f2a0c?t=1385161659939",
                "date": "1393909200000"
            }
        },
        {
            "dynamicElements": {
                "title": "It's Super",
                "toggle": "",
                "image": "/documents/10184/0/welcome_cube/f9e23e2c-122d-4b7e-bf67-29d33abc1ec1?t=1385161661151",
                "date": "1394596800000"
            }
        },
        {
            "dynamicElements": {
                "title": "And Amazing",
                "toggle": "true",
                "image": "/documents/10184/0/welcome_community/ef976219-16c8-481b-a482-e3dac3291dcf?t=1385161662900",
                "date": "1395374400000"
            }
        }
    ]

Each web content article representation is comprised of its associated web
content structure fields. The service returns an empty array (`[]`) if no
matching articles are found. 

If you think getting web content articles through Skinny Provider is easy,
you'll find it a snap to get Dynamic Data List records. 

### Retrieving Skinny Dynamic Data Lists [](id=retrieving-skinny-dynamic-data-lists)

The `get-skinny-ddl-records` service retrieves all records found in a specified
Dynamic Data List (DDL). Each DDL is globally recognized by its unique ID--no
company ID or group name is required to locate them. And since DDLs don't
support localization, no locale argument is needed. 

Dynamic Data List records can be retrieved using the DDL's record set ID. You
can find the ID from within the Dynamic Data Lists app. Each list is assigned an
ID that you can supply to the Skinny Provider web service as the DDL record set
ID value. 

![Figure 3: On adding or configuring lists in Liferay's the Dynamic Data List app, each list's ID is displayed. You can specify this ID as the value for the DDL record set ID in querying the list from the Skinny web service.](../../images/ddl-data-definition-listing.png)

Now that you know how to get a DDL record set ID, you can specify it in calling
the `get-skinny-ddl-records` service. 

For example, imagine that you have a simple DDL based on the following data
definition:

- `title` - A Text field with a title.
- `image` - A Documents and Media Library field for referencing images.
- `date` - A Date field.
- `toggle` - a Boolean field.

If the DDL's record set ID is `15002`, for example, you can retrieve its records
by invoking JavaScript code like this:

    Liferay.Service(
      '/skinny-web.skinny/get-skinny-ddl-records',
      {
        ddlRecordSetId: 15002
      },
      function(obj) {
        console.log(obj);
      }
    );

In JavaScript, you pass the DDL record set ID via parameter `ddlRecordSetId`.

If you want to retrieve DDL records using a URL, you can specify a URL like this
one:

    http://localhost:8081/api/jsonws/skinny-web.skinny/get-skinny-ddl-records/ddl-record-set-id/15002

Make sure to specify your DDL record set ID as the value for the URL parameter
`ddl-record-set-id`. 

The Skinny Provider's `get-skinny-ddl-records` service returns a `JSONArray`
object containing zero or more dynamic data list records. The following example
shows a `JSONArray` object that includes several dynamic data list records:

    [
        {
            "dynamicElements": {
                "title": "Amazing Liferay",
                "toggle": "true",
                "image": "{\"groupId\":\"10184\",\"uuid\":\"012c2d55-b99f-4c85-92c9-7381c3693044\",\"version\":\"1.0\"}",
                "date": "2014-03-14"
            }
        },
        {
            "dynamicElements": {
                "title": "Can Do Much",
                "toggle": "false",
                "image": "{\"groupId\":\"10184\",\"uuid\":\"f9e23e2c-122d-4b7e-bf67-29d33abc1ec1\",\"version\":\"1.0\"}",
                "date": "2011-03-04"
            }
        },
        {
            "dynamicElements": {
                "title": "For You!",
                "toggle": "true",
                "image": "{\"groupId\":\"10184\",\"uuid\":\"ef976219-16c8-481b-a482-e3dac3291dcf\",\"version\":\"1.0\"}",
                "date": "2013-03-04"
            }
        }
    ]

Each DDL record representation is comprised of its data definition fields. The
service returns an empty array (`[]`) if the DDL has no records. 

Each value returned by the service is a JSON object that you can parse (e.g., by
invoking JavaScript's `JSON.parse()` method on it). Each object includes the
group ID and UUID of the target image. You can use the group ID and UUID to
construct a URL of the format below to access the image.

    http://localhost:8081/documents/[groupId]/[uuid]

As a recap, the Skinny JSON Provider app helps you retrieve lightweight
"skinny" representations of dynamic data list records and web content articles.
What a great way to access these core Liferay entities! 

## Related Topics [](id=related-topics)

[Creating iOS Apps that Use Liferay](/develop/tutorials/-/knowledge_base/6-2/creating-ios-apps-that-use-liferay)

[Creating Android Apps that Use Liferay](/develop/tutorials/-/knowledge_base/6-2/creating-android-apps-that-use-liferay)

[Writing a Data-Driven Application](/develop/learning-paths/-/knowledge_base/6-2/writing-a-data-driven-application)
