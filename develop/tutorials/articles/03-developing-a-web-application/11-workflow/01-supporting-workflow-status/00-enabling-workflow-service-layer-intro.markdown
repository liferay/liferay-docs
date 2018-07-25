# Supporting Workflow at the Service Layer [](id=supporting-workflow-at-the-service-layer)

When you 
[asset enabled the Guestbook Application](/develop/tutorials/-/knowledge_base/7-0/assets-integrating-with-liferays-framework),
you added four database columns in the Guestbook entities (e.g., `GB_Entry`)
that keep track of workflow status (they're already added; celebrate!). The
necessary fields are `status`, `statusByUserName`, `statusByUserId`, and
`statusDate`. The columns are defined in the `guestbook-service` module's
`service.xml` file.

    <column name="status" type="int" />
    <column name="statusByUserId" type="long" />
    <column name="statusByUserName" type="String" />
    <column name="statusDate" type="Date" />

The `status` field tells you the current status of the entity (it defaults to
`0`, which evaluates to *approved*). The other status fields store the date of
the last change (`statusDate`) along with the ID and name of the user
(`statusByUserId` and `statusByUserName`) who made the update.

Although the status columns are in the Guestbook application's entity tables,
you must update the local service implementation's `add` methods to set them,
and while you're there, send the entity to the workflow framework. You'll also
write a method to update the status fields when the entity returns from the
workflow framework, along with getters that take workflow status as a parameter.
That sounds like a lot of work, but thanks to Service Builder, you must change
only three files: `service.xml`, `GuestbookLocalServiceImpl`, and
`EntryLocalServiceImpl`.

<a class="go-link btn btn-primary" href="/develop/tutorials/-/knowledge_base/7-0/setting-the-guestbook-status">Let's Go!<span class="icon-circle-arrow-right"></span></a>
