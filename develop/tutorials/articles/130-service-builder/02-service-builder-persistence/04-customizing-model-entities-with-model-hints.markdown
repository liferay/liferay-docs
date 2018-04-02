# Customizing Model Entities With Model Hints [](id=customizing-model-entities-with-model-hints)

If you've already used Service Builder to define your model entities and have
implemented business logic for creating and modifying those entities, you might
have some ideas for helping users to submit valid model entity data. For
example, suppose you're working on a calendar app where users can select a date
for a calendar event. How can you specify that only future dates are
selectable? Easy! Use portlet model hints! Liferay Service Builder's model hints 
provide a single place in your application where you can specify entity data 
restrictions. Model hints are specified in a single file called 
`portlet-model-hints.xml` in your project. If your project is comprised of an
API module, a service module, and a web module, as is common for @product-ver@
applications, `portlet-model-hints.xml` should go in the service module. For
example, in Liferay's Bookmarks application, the `portlet-model-hints.xml` file
goes in the `bookmarks-service/src/main/resources/META-INF/` folder. Model
hints are referred to as such because they suggest how entities should be 
presented to users. Model hints let you to configure how the AlloyUI tag 
library, `aui`, shows model fields. As Liferay displays form fields in your 
application, it first checks the model hints you specified and customizes the
form's input fields based on these hints. Model hints can also be used to 
specify the size of the database columns used to store the entities and to 
specify other entity details. 

+$$$

**Note:** Service Builder generates a number of XML configuration files in your
service module's `src/main/resources/META-INF` folder. Service Builder uses
most of these files to manage Spring and Hibernate configurations. Don't modify
the Spring or Hibernate configuration files; your changes will be overwritten
the next time Service Builder runs. However, you can safely edit the
`portlet-model-hints.xml` file.

$$$

As an example, consider 
[the Bookmarks app's model hints file](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/bookmarks/bookmarks-service/src/main/resources/META-INF/portlet-model-hints.xml):

    <?xml version="1.0"?>

    <model-hints>
        <model name="com.liferay.bookmarks.model.BookmarksEntry">
            <field name="uuid" type="String" />
            <field name="entryId" type="long" />
            <field name="groupId" type="long" />
            <field name="companyId" type="long" />
            <field name="userId" type="long" />
            <field name="userName" type="String" />
            <field name="createDate" type="Date" />
            <field name="modifiedDate" type="Date" />
            <field name="resourceBlockId" type="long" />
            <field name="folderId" type="long" />
            <field name="treePath" type="String">
                <hint name="max-length">4000</hint>
            </field>
            <field name="name" type="String">
                <hint name="max-length">255</hint>
            </field>
            <field name="url" type="String">
                <hint-collection name="URL" />
                <validator name="required" />
                <validator name="url" />
            </field>
            <field name="description" type="String">
                <hint-collection name="TEXTAREA" />
            </field>
            <field name="visits" type="int" />
            <field name="priority" type="int">
                <hint name="display-width">20</hint>
            </field>
            <field name="lastPublishDate" type="Date" />
            <field name="status" type="int" />
            <field name="statusByUserId" type="long" />
            <field name="statusByUserName" type="String" />
            <field name="statusDate" type="Date" />
        </model>
        <model name="com.liferay.bookmarks.model.BookmarksFolder">
            ...
        </model>
    </model-hints>

The root-level element is `model-hints`. All the model entities are represented
by `model` sub-elements of the `model-hints` element. Each `model` element must
have a `name` attribute specifying the fully-qualified model class name. Each
model has `field` elements representing its model entity's columns. Lastly, each
`field` element must have a name and a type. Each `field` element's names and
types correspond to the names and types specified for each entity's columns in
the service module's `service.xml` file. Service Builder generates all these 
elements for you, based on `service.xml`. 

To add hints to a field, add a `hint` tag inside its `field` tag. For example,
you can add a `display-width hint` to specify the pixel width used to display 
the field. The default pixel width is 350. To show a `String` field with 50 
pixels, you could nest a `hint` element named `display-width` and give it a 
value of `50`. 

To see the effect of a hint on a field, you must run Service Builder again and 
redeploy your project. Note that changing `display-width` doesn't limit the 
number of characters a user can enter into the `name` field; it only controls 
the field's width in the AlloyUI input form. 

To configure the maximum size of a model field's database column (i.e., the
maximum number of characters that can be saved for the field), use the 
`max-length` hint. The default `max-length` value is 75 characters. If you
want the `name` field to persist up to 100 characters, add a `max-length` hint 
to that field: 

    <field name="name" type="String">
        <hint name="display-width">50</hint>
        <hint name="max-length">100</hint>
    </field>

Remember to run Service Builder and redeploy your project after updating the 
`portlet-model-hints.xml` file. 

So far, you've seen a few different hints. The following table describes the
portlet model hints available for use.

**Model Hint Values and Descriptions**

Name                | Value Type | Description | Default
:-----------------: | :--------: | :---------- | :-----:
`auto-escape`       | boolean | sets whether text values should be escaped via `HtmlUtil.escape` | true
`autoSize`          | boolean | displays the field in a for scrollable text area | false
`day-nullable`      | boolean | allows the day to be null in a date field | false
`default-value`     | String  | sets the default value of the form field rendered using the aui taglib | (empty String)
`display-height`    | integer | sets the display height of the form field rendered using the aui taglib | 15
`display-width`     | integer | sets the display width of the form field rendered using the aui taglib | 350
`editor`            | boolean | sets whether to provide an editor for the input | false
`max-length`        | integer | sets the maximum column size for SQL file generation | 75
`month-nullable`    | boolean | allows the month to be null in a date field | false
`secret`            | boolean | sets whether hide the characters input by the user | false
`show-time`         | boolean | sets whether to show include time along with the date | true
`upper-case`        | boolean | converts all characters to upper case | false
`year-nullable`     | boolean | allows the year to be null in a date field | false
`year-range-delta`  | integer | specifies the number of years to display from today's date in a date field rendered with the aui taglib | 5
`year-range-future` | boolean | sets whether to include future dates | true
`year-range-past`   | boolean | sets whether to include past dates | true
---

<!--
I wanted to get a complete list of model hints used in portal. I found the
ModelHints interface and then searched for where (*.java, *.js, *.jsp) it was
referenced. It led me to portal-web/docroot/html/taglib/ui/input_field/page.jsp.
I found some more hints and added them to the table above.

I'm not sure what the `check-tab` hint does. Would be worth asking the UI team.

- Jim -->

Note that Liferay has 
[its own `portal-model-hints.xml` file](https://github.com/liferay/liferay-portal/blob/master/portal-impl/src/META-INF/portal-model-hints.xml). 
This file contains many hint examples, so you can reference it when customizing 
your `portlet-model-hints.xml` file. 

You can use the `default-hints` element to define a list of hints to apply to 
every field of a model. For example, adding the following element inside a
model element applies a `display-width` of 300 to each field: 

    <default-hints>
        <hint name="display-width">300</hint>
    </default-hints>

You can define `hint-collection` elements inside the `model-hints` root-level
element to define a list of hints to apply together. A hint collection must have 
a name. For example, Liferay's `portal-model-hints.xml` defines the following 
hint collections:

    <hint-collection name="CLOB">
        <hint name="max-length">2000000</hint>
    </hint-collection>
    <hint-collection name="EDITOR">
        <hint name="editor">true</hint>
        <hint name="max-length">2000000</hint>
    </hint-collection>
    <hint-collection name="SEARCHABLE-DATE">
        <hint name="month-nullable">true</hint>
        <hint name="day-nullable">true</hint>
        <hint name="year-nullable">true</hint>
        <hint name="show-time">false</hint>
    </hint-collection>
    <hint-collection name="TEXTAREA">
        <hint name="display-height">105</hint>
        <hint name="display-width">500</hint>
        <hint name="max-length">4000</hint>
    </hint-collection>
    <hint-collection name="URL">
        <hint name="max-length">4000</hint>
    </hint-collection>

You can apply a hint collection to a model field by referencing the hint
collection's name. For example, if you define a `SEARCHABLE-DATE` collection
like the one above in your `model-hints` element, you can apply it to your 
model's date field by using a `hint-collection` element that references the 
collection by its name:

    <field name="date" type="Date">
        <hint-collection name="SEARCHABLE-DATE" />
    </field>

As always, remember to run Service Builder and redeploy your project after
updating your `portlet-model-hints.xml` file.

For example, suppose you want to use a couple of model hints in your project. 
Start by providing users with an editor for filling in their comment fields. To 
apply the same hint to multiple entities, define it as a hint collection. Then 
reference the hint collection in each entity. 

To define a hint collection, add a `hint-collection` element inside the
`model-hints` root element in your `portlet-model-hints.xml` file. For example:

	<hint-collection name="COMMENT-TEXTAREA">
		<hint name="display-height">105</hint>
		<hint name="display-width">500</hint>
		<hint name="max-length">4000</hint>
	</hint-collection>

To reference a hint collection for a specific field, add the `hint-collection`
element inside the field's `field` element:

	<field name="comment" type="String">
		<hint-collection name="COMMENT-TEXTAREA" />
	</field>

After defining hint collections and adding hint collection references, rebuild
your services using Service Builder, redeploy your project, and check that the 
hints defined in your hint collection have taken effect. 

Nice work! You've learned the art of persuasion through Liferay's model hints. 
Now you can not only influence how your model's input fields are displayed, but 
you can also can set its database table column sizes. You can organize hints, 
insert individual hints directly into your fields, apply a set of default hints 
to all of a model's fields, or define collections of hints to apply at either of 
those scopes. You've picked up the "hints" on how Liferay model hints specify
how to display app data! 
