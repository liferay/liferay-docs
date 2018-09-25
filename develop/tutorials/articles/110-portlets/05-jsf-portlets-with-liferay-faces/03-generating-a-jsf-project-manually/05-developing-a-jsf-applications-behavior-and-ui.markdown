# Developing a JSF Application's Behavior and UI [](id=developing-a-jsf-applications-behavior-and-ui)

Your current JSF application satisfies the requirements for portlet descriptors
and WAR-style structure, but it doesn't do anything yet. You'll learn how to
develop a JSF application's back-end and give it a simple UI next.

The first thing to do is create a Java class for your module. Your JSF portlet's
behavior is defined here. In the case of the Hello User portlet, you should
provide Java methods that can get/set a name and facilitate the submission
process.

1.  Create a unique package name in the module's `src/main/java` folder and
    create a new public Java class named `ExampleBacking.java` in that package.
    For example, the class's folder structure could be
    `src/main/java/com/liferay/example/ExampleBacking.java`. Make sure the class
    is annotated with
    [@RequestScoped](http://docs.oracle.com/javaee/7/api/javax/faces/bean/RequestScoped.html)
    and
    [@ManagedBean](http://docs.oracle.com/javaee/7/api/javax/faces/bean/ManagedBean.html):

        @RequestScoped
        @ManagedBean
        public class ExampleBacking {

    Managed beans are Java beans that are managed by the JSF framework. Managed
    beans annotated with `@RequestScoped` are usually responsible for handling
    actions and listeners. JSF *manages* these beans by creating and removing
    the bean object from the server. Visit the linked annotations above for more
    details.

2.  Add the following methods and field to your `ExampleBacking.java` class:

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void submit(ActionEvent actionEvent) {
            FacesContextHelperUtil.addGlobalSuccessInfoMessage();
        }

        private String name;

    You've provided a getter and setter method for the private `name` field.
    You've also provided a `submit(...)` method, which is called when the
    *Submit* button is selected. A success info message is displayed once the
    method is invoked.

    You've defined your Hello User portlet's Java behavior; now create its UI!

3.  Create a `view.xhtml` file in the `webapp/WEB-INF/views` folder. Add the
    following logic to that file:

        <?xml version="1.0"?>

        <f:view
            xmlns="http://www.w3.org/1999/xhtml"
            xmlns:f="http://java.sun.com/jsf/core"
            xmlns:h="http://java.sun.com/jsf/html"
        >
            <h:head>
                <h:outputStylesheet library="css" name="main.css" />
            </h:head>
            <h:form>
                <h:messages globalOnly="true" />
                <h:outputLabel value="#{i18n['enter-your-name']}" />
                <h:inputText value="#{exampleBacking.name}" />
                <h:commandButton actionListener="#{exampleBacking.submit}" value="#{i18n['submit']}">
                    <f:ajax execute="@form" render="@form" />
                </h:commandButton>
                <br />
                <h:outputText value="Hello #{exampleBacking.name}" />
            </h:form>
        </f:view>

    The first thing to notice is the `main.css` file you created is specified
    here, which makes your portlet's heading typeface bold. Next, your form is
    defined within the `<h:form>` tags. The form asks the user to enter his or
    her name, and then sets that value to the `name` field in your Java class.
    The form uses the `<h:commandButton>` tag to execute the Submit button and
    render the form after submission.

    Notice the `i18n` object call for the `enter-your-name` and `submit`
    properties. The `enter-your-name` property was set in the `i18n.properties`
    file you specified, but what about the `submit` property? This was not
    defined in your portlet's `i18n.properties` file, so how does your portlet
    know to use the string *Submit* for your button? If you recall, the `i18n`
    object can also access messages in @product@'s `Language.properties` file.
    This is where the `submit` language key comes from.

    Finally, the `<h:outputText>` tag prints the submitted name on the page,
    prefixed with *Hello*.

Awesome! Your Hello User JSF application is complete! Deploy your WAR to
@product@. Remember, when your WAR-style portlet is deployed, it's converted to
a WAB via the WAB Generator. Visit the 
[Using the WAB Generator](/develop/tutorials/-/knowledge_base/7-1/using-the-wab-generator)
tutorial for more information on this process and your portlet's resulting
folder structure.

![Figure 1: After submitting the user's name, it's displayed with a greeting.](../../../../images/hello-user-jsf-portlet.png)

You can view the finished version of the Hello User JSF application by
downloading its
[ZIP file](https://dev.liferay.com/documents/10184/1608802/hello-user-jsf-portlet.zip/61e42092-4487-976d-f0f0-841fba0fba33?version=1.0&download=true).