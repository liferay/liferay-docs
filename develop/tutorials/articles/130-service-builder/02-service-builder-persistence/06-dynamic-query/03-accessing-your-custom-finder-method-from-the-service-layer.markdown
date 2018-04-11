## Step 3: Accessing Your Custom Finder Method from the Service Layer [](id=step-3-accessing-your-custom-finder-method-from-the-service-layer)

So far, you've created a `-FinderImpl` class, defined a custom `findBy-` finder
method in that class, and implemented the custom finder method using Dynamic
Query. Now how do you call your custom finder method from the service layer?

When you ran Service Builder after defining your custom finder method, the
`-Finder` interface was generated (e.g., `GuestbookFinder`). Your portlet class,
however, should not call the `-Finder` interface: only a local or remote service
implementation (i.e., `-LocalServiceImpl` or `-ServiceImpl`) in your service
module should invoke the `-Finder` class. This encourages a proper separation of
concerns: the portlet classes in your application's web module invoke the
business logic of the services published from your application's service module.
The services, in turn, access the data model using the persistence layer's
finder classes.

+$$$

**Note:** In previous versions of Liferay Portal, your finder methods were
accessible via `-FinderUtil` utility classes. Finder methods are now injected
into your app's local services, removing the need to call finder utilities.

$$$

So you'll add a method in the `-LocalServiceImpl` class that invokes the finder
method implementation via the `-Finder` class. Then you'll rebuild your
application's service layer so that the portlet classes and JSPs in your web
module can access the services.

For example, for the Guestbook application, you'd add the following method to
the `EntryLocalServiceImpl` class:

    public List<Entry> findByEntryNameGuestbookName(String entryName,
        String guestbookName) throws SystemException {

        return entryFinder.findByEntryNameGuestbookName(String entryName,
            String guestbookName);
    }

After you've added your `findBy-` method to your `-LocalServiceImpl` class, run
Service Builder to generate the interface and make the finder method available
in the `EntryLocalService` class.

Now you can indirectly call the finder method from your portlet class or from a
JSP by calling `EntryLocalService.findByEntryNameGuestbookName(...)`!

Congratulations on following the three step process of developing a dynamic
query in a custom finder and exposing it as a service for your portlet!
