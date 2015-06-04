## Portal Configuration of JSON Web Services [](id=portal-configuration-of-json-web-services)

JSON web services are enabled on Liferay Portal by default. If you need to
disable them, specify this portal property setting: 

    json.web.service.enabled=false

Next, let's look at strict HTTP methods. 

### Strict HTTP Methods [](id=strict-http-methods)

All JSON web services are mapped to either GET or POST HTTP methods. If a
service method name starts with `get`, `is` or `has`, the service is assumed to
be read-only and is bound to the GET method; otherwise it's bound to POST. 

By default, Liferay Portal doesn't check HTTP methods when invoking a service
call; it works in *non-strict http method* mode, where services may be invoked
using any HTTP method. If you need the strict mode, you can set it with this
portal property: 

    jsonws.web.service.strict.http.method=true

When using strict mode, you must use the correct HTTP methods in calling service
methods. 

When strict HTTP mode is enabled, you still might have need to disable HTTP
methods. We'll show you how next. 

### Disabling HTTP Methods [](id=disabling-http-methods)

When strict HTTP method mode is enabled, you can filter web service access based
on HTTP methods used by the services. For example, you can set the portal JSON
web services to work in read-only mode by disabling HTTP methods other than GET.
For example: 

    jsonws.web.service.invalid.http.methods=DELETE,POST,PUT

Now all requests that use HTTP methods from the list above are ignored.

Next, we'll show you how to restrict public access to exposed JSON APIs. 

### Controlling Public Access [](id=controlling-public-access)

Each service method knows if it can be executed by unauthenticated users and
if a user has adequate permission for the chosen action. Most of the portal's
read-only methods are open to public access.

If you're concerned about security, you can further restrict public access to
exposed JSON APIs by explicitly stating which methods are *public* (i.e.,
accessible to unauthenticated users). Use the following property to specify your
public methods: 

    jsonws.web.service.public.methods=*

The property supports wildcards, so if you specify `get*,has*,is*` on the right
hand side of the `=` symbol, all read-only JSON methods will be publicly
accessible. All other JSON methods will be secured. To disable access to *all*
exposed methods, you can leave the right side of the `=` symbol empty; to enable
access to all exposed methods, specify `*`. 

## Related Topics

[Registering JSON Web Services](develop/tutorials/-/knowledge_base/6-2/registering-json-web-services)

[Invoking JSON Web Services](develop/tutorials/-/knowledge_base/6-2/invoking-json-web-services)

[JSON Web Services Invoker](develop/tutorials/-/knowledge_base/6-2/json-web-services-invoker)

[JSON Web Services Invocation Examples](develop/tutorials/-/knowledge_base/6-2/json-web-services-invocation-examples)
