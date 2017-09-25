# Enabling Workflow at the Service Layer 

When you [asset enabled the Guestbook
Application](/develop/tutorials/-/knowledge_base/7-0/assets-integrating-with-liferays-framework),
the Guestbook's entities were added to the `AssetEntry` table. There's no
special table for workflow entities, but there are four database columns in the
entity table (e.g., `GB_Entry`) that allow you to keep track of workflow status
( they're already added; celebrate!) . The necessary fields include `status`,
`statusByUserName`, `statusByUserId`, and `statusDate`. The columns are defined
in the `service.xml` file of the `guestbook-service` module.

    <column name="status" type="int" />

- The `status` column, set to `0` by default (which evaluates to *approved*) tells you what the current status of the entity is. 

        <column name="statusByUserId" type="long" />
        <column name="statusByUserName" type="String" />
        <column name="statusDate" type="Date" />

- These fields store the ID and name of the user (`statusByUserId` and
    `statusByUserName`) who last caused an update to the status of the entity,
    and when it occurred (`statusDate`).

Although you have the status columns in the Guestbook Application's entity
tables, they're not being set anywhere. Update the local service implementation
classes to put values in these fields. 

