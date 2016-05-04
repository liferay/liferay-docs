# Configuring JSON Web Services [](id=portal-configuration-of-json-web-services)

JSON web services are enabled in Liferay by default. If you need to disable 
them, specify this portal property setting in a `portal-ext.properties` file: 

    json.web.service.enabled=false

This tutorial presents other such properties that you can use to fine-tune 
exactly how JSON web services work in your Liferay instance. You can find these, 
and other properties, in 
[the portal properties reference documentation](http://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html). 
As with the preceding property, you should set portal properties in a 
`portal-ext.properties` file. 

First, you'll learn about setting whether JSON web services are discoverable via 
the API page. 

## Discoverability [](id=discoverability)

By default, JSON web services are discoverable via the API page at 
`http://[address]:[port]/api/jsonws`. To disable this, set the following 
property: 

    jsonws.web.service.api.discoverable=false

Next, you'll learn how to disable HTTP methods. 

## Disabling HTTP Methods [](id=disabling-http-methods)

When strict HTTP method mode is enabled, you can filter web service access based
on HTTP methods used by the services. For example, you can set your Liferay 
instance's JSON web services to work in read-only mode by disabling HTTP methods 
other than `GET`. For example: 

    jsonws.web.service.invalid.http.methods=DELETE,POST,PUT

With this setting, all requests that use `DELETE`, `POST`, or `PUT` HTTP methods 
are ignored. 

Next, you'll learn how to restrict public access to exposed JSON APIs. 

## Strict HTTP Methods [](id=strict-http-methods)

All JSON web services are mapped to either `GET` or `POST` HTTP methods. If a
service method name starts with `get`, `is` or `has`, the service is assumed to
be read-only and is bound to the `GET` method. Otherwise, it's bound to `POST`. 

By default, Liferay doesn't check `HTTP` methods when invoking a service
call; it works in non-strict http method mode, where services may be invoked
using any HTTP method. If you need the strict mode, you can set it as follows: 

    jsonws.web.service.strict.http.method=true

When using strict mode, you must use the correct HTTP methods to calll service 
methods. When strict HTTP mode is enabled, you still might need to disable HTTP
methods. You'll learn how next. 

## Controlling Public Access [](id=controlling-public-access)

Each service method knows whether a given user has permission to invoke the 
chosen action. If you're concerned about security, you can restrict access
to exposed JSON APIs by explicitly permitting or restricting certain JSON web
service paths. 

The property `jsonws.web.service.paths.includes` denotes patterns for JSON web
service action paths that are allowed. Set a blank pattern to allow any service
action path. 

The property `jsonws.web.service.paths.excludes` denotes patterns for JSON web 
service action paths that aren't allowed even if they match one of the patterns 
set in `jsonws.web.service.paths.includes`. 

Note that these properties support wildcards. For example, if you set 
`jsonws.web.service.paths.includes=get*,has*,is*`, Liferay makes all read-only 
JSON methods publicly accessible. All other JSON methods are secured. To disable 
access to all exposed methods, you can leave the right side of the `=` symbol 
empty. To enable access to all exposed methods, specify `*`. Remember that if a 
path matches both the `jsonws.web.service.paths.includes` and 
`jsonws.web.service.paths.excludes` properties, the 
`jsonws.web.service.paths.excludes` property takes precedence. 

## Related Topics [](id=related-topics)

[Registering JSON Web Services](/develop/tutorials/-/knowledge_base/7-0/registering-json-web-services)

[Creating Remote Services](/develop/tutorials/-/knowledge_base/7-0/creating-remote-services)

[Invoking Remote Services](/develop/tutorials/-/knowledge_base/7-0/invoking-remote-services)
