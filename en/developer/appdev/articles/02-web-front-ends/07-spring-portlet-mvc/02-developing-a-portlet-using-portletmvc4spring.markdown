---
header-id: developing-a-portlet-using-portletmvc4spring
---

# Developing a Portlet Using PortletMVC4Spring

[TOC levels=1-4]

PortletMVC4Spring compliments the Spring Web framework and MVC design pattern by
providing annotations that map portlet requests to Controller classes and
methods. Here you'll develop and deploy a portlet application that uses
PortletMVC4Spring, Spring, and JSP/JSPX templates. 

![Figure 1: The archetype's sample portlet prints a greeting (e.g., *Hello, Joe Bloggs*) on submitting a first and last name.](../../../images/portletmvc4Spring-developing.png)

Follow these steps to create and deploy your portlet application: 

1.  Create a PortletMVC4Spring project. See
    [PortletMVC4Spring Project Anatomy](/docs/7-2/reference/-/knowledge_base/r/portletmvc4spring-project-anatomy)
    for the project structure and commands for generating PortletMVC4Spring
    projects. Here's the Maven command for generating the JSP/JSPX project.
    This article lists sample code from the generated project. 

    ```bash
    mvn archetype:generate \
    -DarchetypeGroupId=com.liferay.portletmvc4spring.archetype \
    -DarchetypeArtifactId=com.liferay.portletmvc4spring.archetype.form.jsp.portlet \
    -DarchetypeVersion=5.1.0 \ 
    -DgroupId=com.mycompany \ 
    -DartifactId=com.mycompany.my.form.jsp.portlet
    ```

2.  In your project's Gradle build file or Maven POM, add your app's
    dependencies. Here are the PortletMVC4Spring development dependencies:
    
    **Gradle:**

    ```groovy
    compile group: 'com.liferay.portletmvc4spring', name: 'com.liferay.portletmvc4spring.framework', version: '5.1.0'
    compile group: 'com.liferay.portletmvc4spring', name: 'com.liferay.portletmvc4spring.security', version: '5.1.0'
    providedCompile group: 'javax.portlet', name: 'portlet-api', version: '3.0.0'
    ```

    **Maven:**

    ```xml
    <dependency>
        <groupId>com.liferay.portletmvc4spring</groupId>
        <artifactId>com.liferay.portletmvc4spring.framework</artifactId>
        <version>5.1.0</version>
    </dependency>
    <dependency>
        <groupId>com.liferay.portletmvc4spring</groupId>
        <artifactId>com.liferay.portletmvc4spring.security</artifactId>
        <version>5.1.0</version>
    </dependency>
    <dependency>
        <groupId>javax.portlet</groupId>
        <artifactId>portlet-api</artifactId>
        <version>3.0.0</version>
        <scope>provided</scope>
    </dependency>
    ```

    At this point you can develop your Model, View, or Controller components, in
    any order. 

3.  Create your Model class(es) in a package for models (e.g.,
    `java/[my-package-path]/dto`). The sample Model class is `User`. 

    ```java 
    public class User implements Serializable {

    	private static final long serialVersionUID = 1234273427623725552L;

    	@NotBlank
    	private String firstName;

    	@NotBlank
    	private String lastName;

    	public String getFirstName() {
    		return firstName;
    	}

    	public void setFirstName(String firstName) {
    		this.firstName = firstName;
    	}

    	public String getLastName() {
    		return lastName;
    	}

    	public void setLastName(String lastName) {
    		this.lastName = lastName;
    	}
    }
    ```

4.  Create your View using a Spring Web-supported template type. If you didn't 
    generate your project using the archetype mentioned above, specify a View
    resolver for template type in your
    `spring-context/portlet-application-context.xml` portlet application
    context. (See
    [PortletMVC4Spring Configuration Files](/docs/7-2/reference/-/knowledge_base/r/portletmvc4spring-configuration-files)
    for details). 

    The sample `user.jspx` template renders a form for submitting a user's first
    and last name. 
    
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
    		  xmlns:portlet="http://xmlns.jcp.org/portlet_3_0"
    		  xmlns:spring="http://www.springframework.org/tags"
    		  xmlns:form="http://www.springframework.org/tags/form"
    		  version="2.1">
    	<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />
    	<portlet:defineObjects/>
    	<link href="${contextPath}/resources/css/main.css" rel="stylesheet" type="text/css"/>
    	<portlet:actionURL var="mainFormActionURL"/>
    	<form:form id="${namespace}mainForm" action="${mainFormActionURL}" class="user-form" method="post" modelAttribute="user">
    		<p class="caption">
    			<spring:message code="personal-information" />
    		</p>
    		<fieldset>
    			<div class="form-group">
    				<form:label for="${namespace}firstName" path="firstName">
    					<spring:message code="first-name" />
    				</form:label>
    				<form:input id="${namespace}firstName" cssClass="form-control" path="firstName"/>
    				<form:errors path="firstName" cssClass="portlet-msg-error"/>
    			</div>
    			<div class="form-group">
    				<form:label for="${namespace}lastName" path="lastName">
    					<spring:message code="last-name" />
    				</form:label>
    				<form:input id="${namespace}lastName" cssClass="form-control" path="lastName"/>
    				<form:errors path="lastName" cssClass="portlet-msg-error"/>
    			</div>
    		</fieldset>
    		<hr />
    		<spring:message code="submit" var="submit" />
    		<input class="btn btn-primary" value="${submit}" type="submit"/>
    	</form:form>
    </jsp:root>
    ``` 

    To invoke actions in your Controller, associate action URLs with your
    templates. The sample template associates the action URL variable
    `mainFormActionURL` with its form element. 
    
    ```xml 
    <portlet:actionURL var="mainFormActionURL"/>
    <form:form id="${namespace}mainForm" action="${mainFormActionURL}" class="user-form" method="post" modelAttribute="user">
        ...
    ```

    A `<form:form/>` element's `modelAttribute` attribute targets an application
    Model. The sample template targets the application's `user` Model. 

5.  Style your portlet by adding CSS to a stylesheet (e.g.,
    `webapp/resources/css/main.css`) and linking your template to it. 

    ```xml 
    <link href="${contextPath}/resources/css/main.css" rel="stylesheet" type="text/css"/>
    ```

6.  Define your portlet's messages in a properties file (e.g.,
    `src/main/resources/content/[portlet].properties`). The sample `user.jspx`
    template references some of these properties: 

    ```properties 
    first-name=First Name
    greetings=Greetings, {0} {1}!
    javax.portlet.display-name=com.mycompany.my.form.jsp.portlet
    javax.portlet.keywords=com.mycompany.my.form.jsp.portlet
    javax.portlet.short-title=com.mycompany.my.form.jsp.portlet
    javax.portlet.title=com.mycompany.my.form.jsp.portlet
    last-name=Last Name
    personal-information=Personal Information
    submit=Submit
    todays-date-is=Today''s date is {0}
    ```

7.  Create a Controller class to handle portlet requests. Here's an example:

    ```java 
    @Controller
    @RequestMapping("VIEW")
    public class MyController {
        ...
    }
    ```

    The
    [`@Controller`](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/stereotype/Controller.html)
    annotation applies the Spring Controller component stereotype. The Spring
    Framework scans Controller classes for Controller annotations. 

    The
    [`@RequestMapping("VIEW")`](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestMapping.html)
    annotation marks the class's public methods as request handler methods for
    the portlet's
    [VIEW mode](/docs/7-2/frameworks/-/knowledge_base/f/portlets). 

8.  In your Controller, apply `@RenderMapping` annotations to methods for 
    handling portlet render requests. Import the annotation
    `com.liferay.portletmvc4spring.bind.annotation.RenderMapping` and make sure
    each handler method returns a string that matches the name of a template to
    render. Here are the sample's render request handler methods:

    ```java
    @RenderMapping
	public String prepareView() {
		return "user";
	}

	@RenderMapping(params = "javax.portlet.action=success")
	public String showGreeting(ModelMap modelMap) {

		DateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy G");

		Calendar todayCalendar = Calendar.getInstance();

		modelMap.put("todaysDate", dateFormat.format(todayCalendar.getTime()));

		return "greeting";
	}
    ```

    The `@RenderMapping` annotation causes the `prepareView` method above to be
    invoked if no other handler methods match the request. `prepareView` renders
    the `user` template (i.e., `user.jspx`). 

    The `@RenderMapping(params = "javax.portlet.action=success")` annotation
    causes the `showGreeting` method to be invoked if the render request has the
    parameter setting `javax.portlet.action=success`. `showGreeting` renders the
    `greeting` template (i.e., `greeting.jspx`). 

6.  In your Controller, apply `@ActionMapping` annotations to your portlet 
    action request handling methods. Import the annotation
    `com.liferay.portletmvc4spring.bind.annotation.ActionMapping`. 

    The sample Controller's action handler method below is annotated with
    `@ActionMapping`, making it the default action handler if no other action
    handlers match the request. Since this portlet only has one action handler,
    the `submitApplicant` method handles all of the portlet's action requests. 

    ```java 
    @ActionMapping
    public void submitApplicant(@ModelAttribute("user") User user, BindingResult bindingResult, ModelMap modelMap,
                                Locale locale, ActionResponse actionResponse, SessionStatus sessionStatus) {

        localValidatorFactoryBean.validate(user, bindingResult);

        if (!bindingResult.hasErrors()) {

            if (logger.isDebugEnabled()) {
                logger.debug("firstName=" + user.getFirstName());
                logger.debug("lastName=" + user.getLastName());
            }

            MutableRenderParameters mutableRenderParameters = actionResponse.getRenderParameters();

            mutableRenderParameters.setValue("javax.portlet.action", "success");

            sessionStatus.setComplete();
        }
    }
    ```

    The [`@ModelAttribute`](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/bind/annotation/ModelAttribute.html)
    annotation in method parameter `@ModelAttribute("user") User user`
    associates the View's `user` Model (comprising a first name and last name)
    to the `User` object passed to this method. 

    Note, the `submitApplicant` method sets the `javax.portlet.action` render
    parameter to `success`---the previous render handler method `showGreeting`
    matches this request parameter. 

7.  Configure any additional resources and beans in the project's 
    descriptors and Spring context files respectively. (See
    [PortletMVC4Spring Configuration Files](/docs/7-2/reference/-/knowledge_base/r/portletmvc4spring-configuration-files)
    for details).

8.  Build the project WAR using Gradle or Maven. 

9.  Deploy the WAR by copying it to your `[Liferay-Home]/deploy` folder. 

@product@ logs the deployment and the portlet is now available in the @product@
UI. Find your portlet by selecting the *Add* icon
(![Add](../../../images/icon-add-app.png))
and navigating to *Widgets* and the category you specified to the Liferay Bundle
Generator (*Sample* is the default category). 

Congratulations! You created and deployed a PortletMVC4Spring Portlet. 

## Related Topics 

[PortletMVC4Spring Project Anatomy](/docs/7-2/reference/-/knowledge_base/r/portletmvc4spring-project-anatomy)

[PortletMVC4Spring Annotations](/docs/7-2/reference/-/knowledge_base/r/portletmvc4spring-annotations)

[PortletMVC4Spring Configuration Files](/docs/7-2/reference/-/knowledge_base/r/portletmvc4spring-configuration-files)

[Migrating to PortletMVC4Spring](/docs/7-2/appdev/-/knowledge_base/a/migrating-to-portletmvc4spring)
