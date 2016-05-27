##New Liferay Component Class Wizard

In the latest `IDE 3.0.0`, we added a *New Liferay Component* wizard. Users can choose the component class they want to use and then the wizard could help you add this 
class file to one module project directly.

First, you should create a `Module project`. Then go to *File*> &rarr; *New* &rarr; *Liferay Component Class*.

In this wizard, the `Project name` is the Module project you just created. Both `Package name` and `Component Class Name` have the default name, the names also can define by yourself.
The default `Component Class Template` is Portlet.

![component_class_wizard](../../../images/component_class_1.png)

There are sixteen templates in the `Component Class Template` list:

- `Auth Failure`: the processing of varify login failure
- `Auth Max Failure`: the maximum number of login failure
- `Authenticator`: authentication processing
- `Friendly URL Mapper`: processing about Friendly URL
- `GOGO Command`: create your own GOGO command
- `Indexer Post Processor`: create new Indexer Post Processor
- `Login Pre Action`: login pre actoin
- `Model Listener`: set model listener
- `Poller Processor`: create new poller processor
- `Portlet`: create new portlet class file
- `Portlet Action Command`: create new portlet action command
- `Portlet Filter`: create new portlet filter
- `Rest`: call and wrap inner service in the way of Rest
- `Service Wrapper`: create new service wrapper
- `Struts in Action`: create new strutus action
- `Struts Portlet Action`: create new struts portlet action

##Fix the dependencies error

When you select `Authenticator`, `Portlet Action Command`, `Rest` and `Service Wrapper` these four component class, can’t find dependencies error will occur in project. Then you should fix it by doing the following steps:

**Authenticator**<br/>

- Open build.gradle file in module project.
- Check whether the following dependencies exist or not:<br/>
`compile ‘com.liferay.portal:com.liferay.portal.kernel:2.0.0’`<br/>
`compile ‘org.osgi:org.osgi.service.component.annotations:1.3.0’`<br/>
`compile ‘org.apache.shiro:shiro-core:1.1.0’`(This is only for projects that use Shiro, so we don’t need them to add shiro-core. It’s just that a blade sample has shiro core.)

If missing anyone of these, please add them to the end of dependencies block.<br/>

- Save, right-click your project, then click *Gradle* &rarr; *Refresh Gradle Project*.

**Portlet Action Command**

- Open build.gradle file in module project.
- Check whether the following dependencies exist or not<br/>
`compile ‘javax.portlet:portlet-api:2.0’`<br/>
`compile ‘javax.servlet:javax.servlet-api:3.0.1’`<br/>
`compile ‘org.osgi:org.osgi.service.component.annotations:1.3.0’`<br/>
`compile ‘com.liferay.portal:com.liferay.portal.kernel:2.0.0’`<br/>
`compile ‘com.liferay.portal:com.liferay.util.bridges:2.0.0’`<br/>
`compile ‘com.liferay.portal:com.liferay.util.taglib:2.0.0’`

If missing anyone of these, please add them to the end of dependencies block.

- Save, right-click your project,then click *Gradle* &rarr; *Refresh Gradle Project*.
- Open the XXXPortlet, change the `XXXPortlet implements FreeMarkerPortlet` 
To `XXXPortlet extends FreeMarkerPortlet`

**Rest**<br/>
Fix steps are as same as Authenticator, but the dependencies should be: `compile ‘javax.ws.rs:javax.ws.rs-api:2.0.1’`

**Service Wrapper**<br/>
Fix steps are as same as *Authenticator*, but the corresponding dependencies are in liferay nexus repo:
[Compile Dependency errors](https://repository.liferay.com/nexus/content/groups/public/com/liferay)

Like: BookmarksEntryLocalServiceWrapper
`com.liferay:com.liferay.bookmarks.api:2.0.0`

- Save, right-click your project,then click *Gradle* &rarr; *Refresh Gradle Project*.

