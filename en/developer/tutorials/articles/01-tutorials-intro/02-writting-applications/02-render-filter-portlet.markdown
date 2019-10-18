---
header-id: render-filter-portlet
---

# Application - GenericPortlet + RenderFilter + DS 

[TOC levels=1-4]


In this tutorial we are going to learn how to use a RenderFilter to modify the response data before the Portlet is actually rendered to the client.

Portlet filters are similar to servlet filters in a web application. 
Portlet filters allow to transform the content of portlet requests and responses transparently, or execute some code before, after or around any the different portlet request processing phases. 

Multiple filters can be linked to a portlet. A filter chain will be created to keep an ordered list of all of them. 

The filters can also intercept and block the execution of the portlet phase (usually by throwing an exception or by not calling the next element in the filter chain)

In this code example you will see how to create and link two different filters linked to the render phase of a portlet. In the example we will have a portlet which renders a list of persons, showing thier names and email addresses. But we don't want to expose their email addresses directly, so we will use a `RenderFilter` to hide some parts of the email.

We will also use a second filter to monitor and keep some stats on the number of times that the portlet has been called and the time it took to render.

## 1. Create the project

The first thing you need to do is create a new mvc portlet. You can do this using [blade CLI](https://portal.liferay.dev/docs/7-1/tutorials/-/knowledge_base/t/blade-cli) tool:

```
 $ blade create -v 7.1 [-b maven] -t mvc-portlet -p com.liferay.code.samples.portal.modules.applications.portlets.render_filter -c MembersListPortlet render-filter-portlet
 ```

 Alternatively you can [create the module using Liferay Dev Studio](https://portal.liferay.dev/docs/7-1/tutorials/-/knowledge_base/t/creating-modules-with-liferay-ide) or [create the project with IntelliJ IDEA](https://portal.liferay.dev/docs/7-1/tutorials/-/knowledge_base/t/creating-projects-with-intellij-idea)

 ## 2. Review the dependencies

 Check that the dependencies and configuration in `pom.xml` or `build.gradle` (depending on the build tool you are using) are pointing to right libraries and versions according to your liferay-portal version.

 In this code example, a BOM with the following coordinates `com.liferay.portal:release.portal.bom:7.1.3` is configured to make sure the right versions of the dependencies are used.

## 3. The OSGi bundle descriptor

The `bnd.bnd` contains the information required to the OSGi container to deploy the project as a module. In the example, `bnd.bnd` imports the `common.bnd`descriptor containing the common properties and then adds any specific property depending on the build environment used.

## 4. The portlet and view rendered.

The `MembersListPortlet` is a simple MVC portlet that uses a `view.jsp` to render the list of Persons.

The view also renders a button to load the list of users. When the button is clicked, the `UserListMVCActionCommand`is invoked to load a list of users.

> _You can check the **Portlet using an ActionCommand** code sample to leanr how `MVCActionCommand` works_

## 5. A RenderFilter to hide email adresses

The `EncodingPersonEmailsRenderFilter` *implements* an `RenderFilter` and it's configured, through the `@Component.property` to be linked to the portlet defined in the previous step.

This configuration means that Liferay will create an unique instance of the filter and will add it to the FilterChain associated with portlet identified by `"javax.portlet.name=" + MembersListPortletKeys.MEMBERSLIST_PORTLET_NAME`

The `RenderFilter` interface forces to implement, among others, a `doFilter` method in which we can add whatever code we need to modify, transfor or intercept the request. The access to request an response objects is provided via parameters to the method.

A reference to the `FilterChain` is also provided as an argument to the method. Whenever the we want to continue with the invocation of the next filter (or the portlet phase execution if the filter is the last on the chain) our code should invoke `chain.doFilter(request, response)`

## 6. A Second Render filter to keep Render Stats

There is a second filter in this example which is keeping some stats on the number of times that the portlet has been rendered and how much total time has been spent in render phase.

The code for this second filter can be found on the `MembersListStatsRenderFilter`

The goal of this second filter is to show how both filters are automatically picked and added to the FilterChain that will be executed around the Render of the portlet.

## 7. Specifying the order in which the filters are applied
For each portlet, a `FilterChain` is created and the filters that should be applied to the portlet.

The `FilterChain` will keep the filters ordered by the  value configured in `"service.ranking:Integer=n"` property. The highest number means the higher precedence.

> According to the Portlet 3.0 Specification the Filters in the filter chain are placed in the same order they are defined in the `portlet.xml` descriptor file. 
>
> The equivalent of the order in which is defined in a file in the case of OSGi would be the the OSGi service ranking. 

 
## 8. Build and deploy.

Once you have created the components described above, you can build the project and copy the .jar file inside the `osgi/modules` located within your root liferay installation.

For example, using [blade CLI](https://portal.liferay.dev/docs/7-1/tutorials/-/knowledge_base/t/blade-cli) tool:

```
$ blade gw deploy
```

After you copy the .jar file to `osgi/modules` you should see that the module has started

```
[Refresh Thread: Equinox Container: d98f6888-c84c-42ee-8b4f-6c9cb1d13c5b][BundleStartStopLogger:39] STARTED com.liferay.code.samples.portal.modules.applications.portlets.render_filter_1.0.0 [1022]
```

Now, if you add the `MemberList` portlet to a page and hit the `Load Users` button you should see the list of members with their email addresses partially hidden, thanks to the action of the Filter.

```
Sievert Shayne

Sievert.Sha...@...mple.com

Vida Jonas

Vida.Jo...@...mple.com

Nikola Septima

Nikola.Sept...@...mple.com

Ericka Merav

Ericka.Me...@...mple.com

Kennet Brandr

Kennet.Bra...@...mple.com
```

You should also see the statistics (depending on the log level you have configured) on your logs:

```
Portlet com_liferay_code_samples_portal_modules_applications_portlets_render_filter_MembersListPortlet rendered in 7791 ms
Portlet com_liferay_code_samples_portal_modules_applications_portlets_render_filter_MembersListPortlet rendered 2 times with an average 356135 ms render time
```

You may also check the [code samples](/learn/code-samples) related with this tutorials.




