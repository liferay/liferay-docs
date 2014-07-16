# Configuring Bridge Request Scope Behavior for JSF Portlets

For this tutorial, you'll step through requirements, drawbacks, assumptions, and
behaviors for configuring the Bridge Request Scope. 

One of the key requirements in creating a JSF portlet bridge is managing JSF
request-scoped data within the portlet lifecycle. This is normally referred to
as the *Bridge Request Scope* by JSR 329. The lifespan of the Bridge Request
Scope works like this: 

1. `ActionRequest`/`EventRequest`: `BridgeRequestScope` begins.

2. `RenderRequest`: `BridgeRequestScope` is preserved.

3. Subsequent `RenderRequest`: `BridgeRequestScope` is reused.

4. Subsequent `ActionRequest`/`EventRequest`: `BridgeRequestScope` ends, and a
   new `BridgeRequestScope` begins.

5. If the session expires or is invalidated, then similar to the
   `PortletSession` scope, all `BridgeRequestScope` instances associated with
   the session are made available for garbage collection by the JVM.

The main use-case for having the `BridgeRequestScope` preserved in Step 2
(above) is for *re-rendering* portlets. consider the following example to help
illustrate this use-case. 

Suppose two or more JSF portlets are placed on a portal page (Portlets X and
Y), and those portlets are *not* using `f:ajax` for form submission. In such a
case, if the user were to submit a form (via full `ActionRequest` postback) in
Portlet X, and then submit a form in Portlet Y, then Portlet X should be
re-rendered with its previously submitted form data. 

With the advent of JSF 2.x and Ajax, there were four drawbacks for continuing
to support this use-case as the default behavior: 

-   Request-scoped data is basically semi-session-scoped in nature, because the
    `BridgeRequestScope` is preserved (even though the user might *never* click
    the Submit button again). 
-   `BridgeRequestScope` can't be stored in the `PortletSession` because the
    data is request-scoped in nature, and the data stored in the scope isn't
    guaranteed to be `Serializable` for replication. Therefore, it doesn't
    really work well in a clustered deployment. 
-   The developer might have to specify the
    `javax.portlet.faces.MAX_MANAGED_REQUEST_SCOPES` `<init-param>` in the
    `WEB-INF/web.xml` descriptor in order to tune the memory settings on the
    server. 

As result, Liferay Faces Bridge was designed for JSF 2.x, and keeps Ajax in
mind. The Liferay Faces Bridge makes the following assumptions: 

-   Developers are not primarily concerned about the *re-rendering of
    portlets* use-case mentioned above. 
-   Developers don't want any of the drawbacks mentioned above. 
-   Developers are making heavy use of the `f:ajax` tag and submitting
    forms via Ajax with their modern-day portlets. 
-   Developers want to do as little configuration as possible and don't
    want to be forced to add anything to the `WEB-INF/web.xml` descriptor. 

Consequently, the default behavior of Liferay Faces Bridge is to cause the
`BridgeRequestScope` to end at the end of the `RenderRequest`. 

If you prefer the standard behavior over Liferay Faces Bridge's default
behavior, then you can place the following option in your portlet's
`WEB-INF/web.xml` descriptor: 

    <!--
    The default value of the following context-param is false, meaning that
    Liferay Faces Bridge will cause the BridgeRequestScope to end after the
    RENDER_PHASE of the portlet lifecycle. Setting the value to true will cause
    Liferay Faces Bridge to cause the BridgeRequestScope to last until the next
    ACTION_PHASE or EVENT_PHASE of the portlet lifecycle.
    -->
    <context-param>
        <param-name>com.liferay.faces.bridge.bridgeRequestScopePreserved</param-name>
        <param-value>true</param-value>
    </context-param>

    <!--
    The default value of the following context-param is 100. It defines the
    maximum number of BridgeRequestScope instances to keep in memory on the
    server if the bridgeRequestScopePreserved option is true. 
    -->
    <context-param>
        <param-name>javax.portlet.faces.MAX_MANAGED_REQUEST_SCOPES</param-name>
        <param-value>2000</param-value>
    </context-param>

Alternatively, the `com.liferay.faces.bridge.bridgeRequestScopePreserved` value
can be specified on a portlet-by-portlet basis in the `WEB-INF/portlet.xml`
descriptor. 

Now you know the two options for Bridge Request Scope behavior. By considering
the assumptions and drawbacks that were outlined in this tutorial, you should be
able to make an educated decision about how you'd like to implement the Bridge
Request Scope behavior. 
