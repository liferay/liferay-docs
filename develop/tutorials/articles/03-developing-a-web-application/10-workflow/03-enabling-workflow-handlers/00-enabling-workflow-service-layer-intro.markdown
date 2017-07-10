# Enabling Workflow at the Service Layer 

In the Learning Path on assets, you learned that asset enabled entities are
added to the `AssetEntry` table. There's no special table for workflow entities,
but there are some additional database columns in the entity table (e.g.,
`GB_Entry`) that allow you to keep track of workflow status. The necessary
fields include `status`, `statusByUserName`, `statusByUserId`, and `statusDate`.
Add the columns to the database by entering the following `<column>` tags into
`docroot/WEB-INF/service.xml`, in the Audit Fields section of both entities:

    <column name="status" type="int" />
    <column name="statusByUserId" type="long" />
    <column name="statusByUserName" type="String" />
    <column name="statusDate" type="Date" />

Once you run Service Builder, the database tables for both entities contain the
proper fields for workflow. However, if you add an `Entry`, you'll see that the
new fields are not populated. The local service implementation classes need
some modifications to put values in these fields. 

