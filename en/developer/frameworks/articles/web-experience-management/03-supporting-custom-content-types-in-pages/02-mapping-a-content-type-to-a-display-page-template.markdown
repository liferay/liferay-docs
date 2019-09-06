---
header-id: mapping-a-content-type-to-a-page
---

# Mapping a Content Type to a Page

[TOC levels=1-4]

You must allow the mapping of your custom content type to the page type. To do
this, implement the
[`InfoDisplayContributor`](@app-ref@/info/2.0.0/javadocs/com/liferay/info/display/contributor/InfoDisplayContributor.html)
interface. Follow the steps below to complete this for the custom User content
type.

1.  Inside your custom model project, create a new Java package and add a class
    named `UserInfoDisplayContributor`.

2.  Implement the `InfoDisplayContributor` interface and pass the `User` model
    as the type parameter. Then add the `@Component` annotation:

    ```java
    @Component(immediate = true, service = InfoDisplayContributor.class)
    public class UserInfoDisplayContributor
        implements InfoDisplayContributor<User> {
    }
    ```

    The `@Component` annotation registers the class as an info display
    contributor in the OSGi service registry. Set the `service` property to the
    interface you're implementing.

3.  Implement the methods. For the example User content type, three methods are
    crucial to mapping its model to the Display Page Template framework:

    ```java
    @Override
    public String getClassName() {
        return User.class.getName();
    }

    @Override
    public String getInfoURLSeparator() {
        return "/user/";
    }

    @Override
    public String getLabel(Locale locale) {
        return "Users";
    }
    ```

    - The class name is used to link the Display Page Template to the User
      model.
    - The URL separator is used to generate friendly URLs for the Display Page
      Template.
    - The label is the display name for the new content type.

![Figure 1: After creating the `*InfoDisplayContributor` class, you can create Display Page Templates and map them to your custom model.](../../../images/custom-model-selectable.png)

Great! You've mapped your custom content type to the Display Page Template
framework. Next, you'll provide your content type's fields.
