# Supporting Workflow at the Service Layer 

When you [asset enabled the Guestbook
Application](/develop/tutorials/-/knowledge_base/7-0/assets-integrating-with-liferays-framework),
the Guestbook's entities were added to the `AssetEntry` table. There's no
special table for workflow entities, but there are four database columns in the
entity table (e.g., `GB_Entry`) that allow you to keep track of workflow status
(they're already added; celebrate!). The necessary fields include `status`,
`statusByUserName`, `statusByUserId`, and `statusDate`. The columns are defined
in the `service.xml` file of the `guestbook-service` module.

    <column name="status" type="int" />
    <column name="statusByUserId" type="long" />
    <column name="statusByUserName" type="String" />
    <column name="statusDate" type="Date" />

The `status` field, (defaults to `0`,which evaluates to *approved*) tells you
what the current status of the entity is. The other status fields are used
to store the ID and name of the user (`statusByUserId` and `statusByUserName`)
who last caused an update to the status of the entity, and when it occurred
(`statusDate`).

Although the status columns are in the Guestbook Application's entity tables,
they're not being set anywhere. Update the local service implementation's `add`
methods to set them, and while you're there, send the entity to the workflow
framework. You'll also write a method to update the status fields when the
entity returns from the workflow framework, and add some getters that take
workflow status as a parameter. That sounds like a lot of work, but thanks to
Service Builder it only involves working in three files: `service.xml`,
`GuestbookLocalServiceImpl`, and `EntryLocalServiceImpl`.

