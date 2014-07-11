# Understanding CDI in JSF Annotations

For this tutorial, you'll explore CDI annotations and, then, a helpful table is
provided defining equivalence between JSF and CDI annotations. Each CDI
annotation provides a description and link that provides helpful documentation. 

First, when developing portlets with CDI, it's possible to annotate Java classes
as CDI managed beans with
[`@Named`](http://docs.oracle.com/javaee/6/api/javax/inject/Named.html) with the
following scopes: 

| CDI Annotation | Description |
|----------------|-------------|
| [`@ApplicationScoped`](http://docs.oracle.com/javaee/6/api/javax/enterprise/context/ApplicationScoped.html) | An `@ApplicationScoped` managed bean exists for the entire lifetime of the portlet application. |
| [`@ConversationScoped`](http://docs.oracle.com/javaee/6/api/javax/enterprise/context/ConversationScoped.html) | A `@ConversationScoped` managed bean is created when `Conversation.begin()` is called and is scheduled for garbage collection when `Conversation.end()` is called. |
| [`@FlowScoped`](https://javaserverfaces.java.net/nonav/docs/2.2/javadocs/javax/faces/flow/FlowScoped.html) | A `@FlowScoped` managed bean is created when a JSF 2.2 Flow begins and scheduled for garbage collection when a JSF 2.2 Flow completes. |
| [`@RequestScoped`](http://docs.oracle.com/javaee/6/api/javax/enterprise/context/RequestScoped.html) | A `@RequestScoped` managed bean exists during an `ActionRequest`, `RenderRequest`, or `ResourceRequest`. Beans that are created during the `ActionRequest` do not survive into the `RenderRequest`. |
| [`@SessionScoped`](http://docs.oracle.com/javaee/6/api/javax/enterprise/context/SessionScoped.html) | A `@SessionScoped` managed bean exists for the duration of the user session. |

In addition to CDI scope annotations, it's important to understand JSF 2
annotations and their equivalence to CDI annotations. 

| JSF Annotation | Equivalent CDI Annotation |
|----------------|---------------------------|
| `javax.faces.ManagedBean` | [`javax.inject.Named`](http://docs.oracle.com/javaee/6/api/javax/inject/Named.html) |
| `javax.faces.ApplicationScoped` | [`javax.enterprise.context.ApplicationScoped`](http://docs.oracle.com/javaee/6/api/javax/enterprise/context/ApplicationScoped.html) |
| `javax.faces.RequestScoped` | No such equivalent, since [`javax.enterprise.context.RequestScoped`](http://docs.oracle.com/javaee/6/api/javax/enterprise/context/RequestScoped.html) does not span portlet lifecycle phases. 
| `javax.faces.SessionScoped` | [`javax.enterprise.context.SessionScoped`](http://docs.oracle.com/javaee/6/api/javax/enterprise/context/SessionScoped.html) |
| `javax.faces.ManagedProperty` (corresponding setter method required) | [`javax.inject.Inject`](http://docs.oracle.com/javaee/6/api/javax/inject/Inject.html) (corresponding setter method not required) |

You should now have a keen understanding of CDI and JSF annotations.

<!-- For more information on using CDI for JSF portlets, you can visit the []()
and []() tutorials. -->

<!-- Add links to other CDI tutorials, when they're available. -Cody -->
