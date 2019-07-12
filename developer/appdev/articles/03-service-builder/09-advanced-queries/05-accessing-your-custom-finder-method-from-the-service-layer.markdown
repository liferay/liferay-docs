---
header-id: accessing-your-custom-finder-method-from-the-service-layer
---

# Accessing Your Custom Finder Method from the Service Layer

[TOC levels=1-4]

So far, you've created a `*FinderImpl` class, defined a `findBy*` finder method
in that class, and implemented the finder method using Dynamic Query or custom
SQL. Now how do you call your finder method from the service layer?

When you ran Service Builder (if you haven't run it yet; run it now), the
`*Finder` interface was generated (e.g., `GuestbookFinder`). For proper
separation of concerns, only a local or remote service implementation (i.e.,
`*LocalServiceImpl` or `*ServiceImpl`) in your service module should invoke the
`*Finder` class. The portlet classes in your application's web module invoke the
business logic of the services published from your application's service module.
The services, in turn, access the data model using the persistence layer's
finder classes.

| **Note:** In previous versions of @product@, your finder methods were
| accessible via `*FinderUtil` utility classes. Finder methods are now injected
| into your app's local services, removing the need to call finder utilities.

You'll add a method in the `*LocalServiceImpl` class that invokes the finder
method implementation via the `*Finder` class. Then you'll rebuild your
application's service layer so that the portlet classes and JSPs in your web
module can access the services.

For example, for the Guestbook application, you'd add the following method to
the `GuestbookEntryLocalServiceImpl` class:

    public List<GuestbookEntry> findByEntryNameGuestbookName(String entryName,
        String guestbookName) throws SystemException {

        return entryFinder.findByEntryNameGuestbookName(String entryName,
            String guestbookName);
    }

After you've added your `findBy*` method to your `*LocalServiceImpl` class, run
Service Builder to generate the interface and make the finder method available
in the `EntryLocalService` class.

Now you can indirectly call the finder method from your portlet class or from a
JSP by calling `_entryLocalService.findByEntryNameGuestbookName(...)`!

Congratulations on following the process of developing custom queries in
a custom finder and exposing it as a service for your portlet!

## Related Topics

[Service Builder Web Services](/docs/7-2/appdev/-/knowledge_base/a/service-builder-web-services)

[Creating Local Service](/docs/7-2/appdev/-/knowledge_base/a/creating-local-services)

[Invoking Local Services](/docs/7-2/appdev/-/knowledge_base/a/invoking-local-services)
