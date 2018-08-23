# Defining a Custom Finder Method [](id=defining-a-custom-finder-method)

Dynamic queries belong in finder methods. You implement them and then make them
available through an interface. This tutorial demonstrates defining the finder
method in an implementation class, generating its interface and tying the
implementation to the interface. 

An example of this is a Guestbook application with two entities: guestbook and
entry. Each entry belongs to a guestbook so the entry entity has
a `guestbookId` field as a foreign key. If you need a finder to search for
guestbook entries by entry name and guestbook name, you'd add a finder method
to `GuestbookFinderImpl` and name it `findByEntryNameGuestbookName`. The full
method signature would be `findByEntryNameGuestbookName(String entryName,
String guestbookName)`. The steps are below. 

1.  Create a `[Entity]FinderImpl` class in the `[package 
    path].service.persistence.impl` package of your service module's
    `src/main/java` folder. Recall that you specify the `[package path]` in your
    `service.xml` file. Here's an example:

        <service-builder package-path="com.liferay.docs.guestbook">
            ...
        </service-builder>

2.  Define a `findBy*` finder method in the class you created. Make sure to add 
    any required arguments to your finder method signature.

4.  Run Service Builder to generate the appropriate interface in the `[package 
    path].service.persistence` package in the `service` folder of your API and
    service modules.

    For example, after adding `findByEntryNameGuestbookName(String entryName,
    String guestbookName)` to `GuestbookFinderImpl` and running Service Builder,
    the interface
    `com.liferay.docs.guestbook.service.persistence.GuestbookFinder` is
    generated.

5.  Make sure that the finder class implements the interface. For example, the 
    class declaration should look like this:

        public class GuestbookFinderImpl extends BasePersistenceImpl<Guestbook> implements GuestbookFinder

Your next step is to implement the query in your finder method using the
Dynamic Query API.
