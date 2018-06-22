# Adding Logic to MVC Commands [](id=adding-logic-to-mvc-commands)

You can completely override MVC commands, or any OSGi service for that matter,
but *adding logic* to the commands is the better option. Discarding necessary
logic is bad. Conversely any logic you copy from the original might not work in
new versions of the portlet. Adding custom logic while continuing to invoke
the original logic decouples the custom class from the original implementation.
Keeping the new logic separate form the original logic keeps the code clean,
maintainable, and easy to understand.

Here are the steps for adding logic to MVC commands.

1.  [Implement the interface](#implement-the-interface)
2.  [Publish as a component](#publish-as-a-component)
3.  [Refer to the original implementation](#refer-to-the-original-implementation)
4.  [Add the logic, and call the original](#add-the-logic)

## Step 1: Implement the interface [](id=#implement-the-interface)

Implement the respective MVC Command interface either directly or by extending
an existing base class that implements it. Extending a base class for the
interface relieves you from implementing logic that should typically be a part
of most command implementations. For example, to add logic to the Blogs
portlet's `EditEntryMVCActionCommand`, you would extend base class
`BaseMVCActionCommand`.

    public class CustomBlogsMVCActionCommand extends BaseMVCActionCommand

Check the MVC command interfaces for existing base classes:

- [`MVCActionCommand`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCActionCommand.html)
- [`MVCRenderCommand`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCRenderCommand.html)
- [`MVCResourceCommand`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCResourceCommand.html)

Next make your class a service component.

## Step 2: Publish as a component [](id=publish-as-a-component)

The Declarative Services `@Component` annotation facilitates customizing MVC
commands. All the customization options require publishing your MVC command
class as a component. For example, this `@Component` annotation declares an
`MVCActionCommand` service. 

    @Component(
        immediate = true,
        property = { 
            "javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN, 
            "mvc.command.name=/blogs/edit_entry",
            "service.ranking:Integer=100" 
        }, 
        service = MVCActionCommand.class
    )
    public class CustomBlogsMVCActionCommand extends BaseMVCActionCommand {
        ...
    } 

It publishes `CustomBlogsMVCActionCommand` as a service component for the
`MVCActionCommand` class. Upon resolving, it's activated immediately because `immediate =
true`. The component is invoked in the Blogs Admin portlet by the command URL
`/blogs/edit_entry`. Its service ranking of `100` prioritizes it ahead of the
original service component, whose ranking is `0`. 

Here's what you need to specify in an `@Component`
annotation for your custom MVC command:

-   `javax.portlet.name`: for each portlet you want the customization to affect.
    JSPs in these portlets can invoke the MVC command via applicable command URL
    tags. You can specify the same portlets as the original MVC command or a
    subset of those portlets. 

-   `mvc.command.name`: this property declares the command URL that maps to this
    custom MVC command component.

-   `service.ranking:Integer`: set this property to a higher integer than the 
    original service implementation's ranking. The ranking tells the OSGi
    runtime which service to use, in cases where multiple components register
    the same service, with the same properties. The higher the integer you
    specify here, the more weight your component carries. Liferay's service
    implementations typically have a `0` ranking. 

-   `service`: this attribute specifies the service (interface) to override .

-   `immediate`: set this attribute to `true` to activate your component 
    immediately  upon resolution.

You can refer back to this list as you add `@Component` annotations to your
custom MVC commands.

Next reference the original implementation. 

## Step 3: Refer to the original implementation [](id=refer-to-the-original-implementation)

Use a field annotated with `@Reference` to fetch a reference to the original MVC
command component. If there are no additional customizations on the original
component, this reference will be for the original MVC command type. For
example, this field references the original MVC command component
`EditEntryMVCActionCommand`.

    @Reference(
        target = "(component.name=com.liferay.blogs.web.internal.portlet.action.EditEntryMVCActionCommand)")
    protected MVCActionCommand mvcActionCommand;

Here's how to add the reference:

1.  Declare the field as the MVC command interface type that it is. For
    example, the `mvcActionCommand` field is type `MVCActionCommand`. 

2.  Add the `@Reference` annotation. 

3.  In the annotation, define a `target` attribute that filters on
    a `component.name` equal to the default service implementation class's
    fully qualified name. 

When your custom component resolves, the OSGi runtime assigns the targeted
service to your field. It's time to add your custom logic. 

## Step 4: Add the logic [](id=add-the-logic)

Adding the logic involves overriding the primary method of the base class
you're extending or the interface you're implementing. In your method override,
add your new logic AND then invoke the original implementation. For example,
the following method overrides `BaseMVCActionCommand`'s method
`doProcessAction`.

    @Override
    protected void doProcessAction(
    	ActionRequest actionRequest, ActionResponse actionResponse)
    throws Exception {
        // Add custom logic here 
        ...
        
        // Call the original service implementation 
        mvcActionCommand.processAction(actionRequest, actionResponse);
    }

The method above defines custom logic and then invokes the original service it
referenced in the previous step. 

If you use this approach, your extension will continue to work with new versions
of the original portlet, because no coupling exists between the original portlet
logic and your customization. The command implementation class can change. Make
sure to keep your reference updated to the name of the current implementation
class. 

Congratulations on adding logic to your existing MVC command.
