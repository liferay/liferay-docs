## Step 1: Defining a Custom Finder Method [](id=step-1-defining-a-custom-finder-method)

To define any custom query, either by specifying custom SQL or by defining a
dynamic query, you need a finder class. Create a `[Entity]FinderImpl` class in
the generated `[package path].service.persistence.impl` package of your service
module's `src/main/java` folder. Recall that you specify the package path
in `service.xml`. Here's an example:

    <service-builder package-path="com.liferay.docs.guestbook">
        ...
    </service-builder>

Then define a `findBy-` finder method in the class you created. Make sure to
add any required arguments to your finder method's method signature.

For example, consider a fictitious Guestbook application. In this application,
there are two entities: guestbooks and entries. Each entry belongs to a
guestbook so the entry entity has a `guestbookId` field as a foreign key.
Suppose you need to create a custom finder to search for guestbook entries by
the entry name and the guestbook name. In this case, you'd add a custom finder
method to `GuestbookFinderImpl` and name it something like
`findByEntryNameGuestbookName`. The full method signature would appear as
`findByEntryNameGuestbookName(String entryName, String guestbookName)`.

Once you've created a finder method with the appropriate method signature in
your finder class, run Service Builder to generate the appropriate interface in
the `[package path].service.persistence` package in the `service` folders of
your API and service modules.

For example, after adding `findByEntryNameGuestbookName(String entryName, String
guestbookName)` to `GuestbookFinderImpl` and running Service Builder, the
interface `com.liferay.docs.guestbook.service.persistence.GuestbookFinder` is
generated.

Once the finder interface has been generated, make sure that the finder class
implements the interface. For example, the class declaration should look like
this:

    public class GuestbookFinderImpl extends BasePersistenceImpl<Guestbook> implements GuestbookFinder

Your next step is to actually define your query in your custom finder method
using the Dynamic Query API.
