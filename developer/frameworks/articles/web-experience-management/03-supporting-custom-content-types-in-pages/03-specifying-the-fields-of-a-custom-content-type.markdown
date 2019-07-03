---
header-id: specifying-the-fields-of-a-custom-content-type
---

# Specifying the Fields of a Custom Content Type

[TOC levels=1-4]

Now that your custom content type is selectable for a Display Page Template, you
must specify the fields you want the user to map to the fragment's editable
fields in the Display Page Template. To do this, implement the
[`InfoDisplayContributorField`](@app-ref@/info/2.0.0/javadocs/com/liferay/info/display/contributor/field/InfoDisplayContributorField.html)
interface.

Follow the steps below to create a user name field for the User content type:

1.  Inside your custom model project, add a class named
    `UserNameInfoDisplayContributorField`.

2.  Implement the `InfoDisplayContributorField` interface and pass the `User`
    model as the type parameter. Then add the `@Component` annotation:

    ```java
    @Component(
        property = "model.class.name=com.liferay.portal.kernel.model.User",
        service = InfoDisplayContributorField.class
    )
    public class UserNameInfoDisplayContributorField
        implements InfoDisplayContributorField<User> {
    }
    ```

    The `@Component` annotation declares the class as an info display
    contributor field in the OSGi service registry. You also set the property
    `model.class.name`, which associates the content type you wish to configure
    with this service.

3.  Implement the methods.

    ```java
    @Override
    public String getKey() {
        return "userName";
    }

    @Override
    public String getLabel(Locale locale) {
        return "User Name";
    }

    @Override
    public InfoDisplayContributorFieldType getType() {
        return InfoDisplayContributorFieldType.TEXT;
    }

    @Override
    public String getValue(User user, Locale locale) {
        return user.getFullName();
    }
    ```

    The above methods

    - set the content type field key to `username`.
    - set the field label to `User Name`.
    - set the field type to text.
    - set the field value to the user's full name.

4.  Now you must override the `getInfoDisplayFields` method in your
    `*DisplayContributor` class, so the mappable fields are displayed. Open the
    `UserInfoDisplayContributor` class and add the following method:

    ```java
    @Override
    public Set<InfoDisplayField> getInfoDisplayFields(
            long classTypeId, Locale locale)
        throws PortalException {

        Set<InfoDisplayField> infoDisplayFields = new LinkedHashSet<>();

        List<InfoDisplayContributorField> infoDisplayContributorFields =
            _infoDisplayContributorFieldTracker.getInfoDisplayContributorFields(
            getClassName());

        for (InfoDisplayContributorField infoDisplayContributorField :
        infoDisplayContributorFields) {

            InfoDisplayContributorFieldType infoDisplayContributorFieldType =
                infoDisplayContributorField.getType();

            infoDisplayFields.add(
                new InfoDisplayField(
                infoDisplayContributorField.getKey(),
                infoDisplayContributorField.getLabel(locale),
                infoDisplayContributorFieldType.getValue()));
        }

        return infoDisplayFields;
    }

    @Reference
    private InfoDisplayContributorFieldTracker _infoDisplayContributorFieldTracker;
    ```

    This method references your new `*InfoDisplayContributorField` class to
    specify your content type's fields.

![Figure 1: After creating the `*InfoDisplayContributorField` class, your custom content type has a new field to map.](../../../images/content-type-custom-fields.png)

Awesome! You've mapped the content type's fields to the editable fields of the
provided fragments. Next, you'll provide the friendly URLs for the Display Page
Template.
