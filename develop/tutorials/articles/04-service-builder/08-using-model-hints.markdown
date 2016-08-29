# Using Model Hints [](id=using-model-hints)

Now that you've created your model entities and implemented your business logic
to create and modify those entities, you probably have some ideas for helping
users input valid model entity data. For example, in the Event Listing project
you've been working on throughout this chapter, you want users to create social
events for the future, not for the past. And it would be nice to give users a
nice text editor to fill in their descriptions. Wouldn't it be great to specify
these customizations from a single place in your portal project? Good news!
Service Builder lets you specify this information as *model hints* in a single
file called `portlet-model-hints.xml` in your project's
`docroot/WEB-INF/src/META-INF` folder. Liferay calls them *model hints* because
they suggest how entities should be presented to users and can also specify the
size of database columns used to store the entities. 

<!-- Commenting this out for now. It's good information to know, but I found it
distracting in the first paragraph and thought it was more important to focus on
introducing model hints. Perhaps this can be moved somewhere else in this
section or the chapter. - Jim

Service Builder generates a
number of XML configuration files in your project's
`docroot/WEB-INF/src/META-INF` folder. Service Builder uses most of these files
to manage Spring and Hibernate configurations. Don't modify the Spring or
Hibernate configuration files; your changes will be overwritten the next time
Service Builder runs. However, you can safely edit the
`portlet-model-hints.xml` file. 
-->

Model hints let you to configure how the AlloyUI tag library, `aui`, shows
model fields. As Liferay Portal displays form fields in your application, it
first checks the model hints you specified and customizes the form's input
fields based on these hints. For example, if you want to limit users to
selecting dates in the future, you'd set a `year-range-past` hint to `false` for
that field in your
`portlet-model-hints.xml` file. It would look like the following tag:

    <field name="date" type="Date">
        <hint name="year-range-past">false</hint>
    </field>

Let's look at the model hints file that Service Builder generated for the Event
Listing portlet. Examine your project's
`docroot/WEB-INF/src/META-INF/portlet-model-hints.xml` file. If you've been
following along in the previous sections, Service Builder created the
`portlet-model-hints.xml` file with the following contents:

    <?xml version="1.0"?>

    <model-hints>
        <model name="com.nosester.portlet.eventlisting.model.Event">
            <field name="eventId" type="long" />
            <field name="companyId" type="long" />
            <field name="groupId" type="long" />
            <field name="userId" type="long" />
            <field name="createDate" type="long" />
            <field name="modifiedDate" type="long" />
            <field name="name" type="String" />
            <field name="description" type="String" />
            <field name="date" type="Date />
            <field name="locationId" type="long" />
        </model>
        <model name="com.nosester.portlet.eventlisting.model.Location">
            <field name="locationId" type="long" />
            <field name="companyId" type="long" />
            <field name="groupId" type="long" />
            <field name="userId" type="long" />
            <field name="createDate" type="long" />
            <field name="modifiedDate" type="long" />
            <field name="name" type="String" />
            <field name="description" type="String" />
            <field name="streetAddress" type="String" />
            <field name="city" type="String" />
            <field name="stateOrProvince" type="String" />
            <field name="country" type="String" />
        </model>
    </model-hints>

<!-- Should the above model elements include the uuid String field? - Jim -->

The root-level element is `model-hints`. In this are all your model entities
represented by `model` elements. Each `model` element must have a `name`
attribute specifying the fully-qualified model class name. Each model has
`field` elements representing its model entity's columns. Lastly, each
`field` element must have a name and a type. Each `field` element's names and
types correspond to the names and types specified for each entity's columns in
your project's `service.xml` file. Service Builder generates all these elements
for you, based on `service.xml` file. 

To add hints to a field, add a `hint` tag inside its `field` tag. For example,
you can add a `display-width hint` to specify the pixel width that should be
used when displaying the field. The default pixel width is 350. To show a
String field with 50 pixels, we could nest a `hint` element named
`display-width` and give it a value of `50` for 50 pixels. Here's an example of
using the `display-width` hint in a `field` element: 

    <field name="name" type="String">
        <hint name="display-width">50</hint>
    </field>

In order to see the effect of a hint on a field, you must run Service Builder
again and redeploy your portlet project. Changing the `display-width` doesn't
actually limit the number of characters that can be entered into the `name`
field; it's just a way to control the width of the field in the AlloyUI input
form. 

To configure the maximum size of a model field's database column (i.e., the
maximum number of characters that can be saved for the field), use the
`max-length` hint. The default `max-length` value is 75 characters. If you
wanted the `name` field to persist up to 100 characters, you'd add a
`max-length` hint to that field: 

    <field name="name" type="String">
        <hint name="display-width">50</hint>
        <hint name="max-length">100</hint>
    </field>

Remember to run Service Builder and redeploy your portlet project after updating
the `portlet-model-hints.xml` file. 

So, we've mentioned a few different hints. It's about time we listed the portlet
hints available to you. The following table describes the portlet model hints.

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
`show-time`         | boolean | sets whether to show inlcude time along with the date | true
`upper-case`        | boolean | converts all characters to upper case | false
`year-nullable`     | boolean | allows the year to be null in a date field | false
`year-range-delta`  | integer | specifies the number of years to display from today's date in a date field rendered with the aui taglib | 5
`year-range-future` | boolean | sets whether to include future dates | true
`year-range-past`   | boolean | sets whether to include past dates | true

<!-- 
Do NOT add the following hints as they are NOT a part of 6.1:
    autoSize
    editor

TODO - Add the following hints exclusive to 6.1:
    default-year-delta
    default-month-delta
    default-day-delta

- Jim -->

<!--
I wanted to get a complete list of model hints used in portal. I found the
ModelHints interface and then searched for where (*.java, *.js, *.jsp) it was
referenced. It led me to portal-web/docroot/html/taglib/ui/input_field/page.jsp.
I found some more hints and added them to the table above.

I'm not sure what the `check-tab` hint does. Would be worth asking the UI team.

- Jim -->

Liferay Portal has its own model hints XML configuration file called
`portal-model-hints.xml` which is in Liferay's `portal-impl/classes/META-INF`
folder. Liferay's model hints configuration file contains many hint examples,
so you can reference it when customizing your `portlet-model-hints.xml` file. 

You can use the `default-hints` element to define a list of hints to be applied
to every field of a model. For example, adding the following element inside a
model element applies a `display-width` of 300 to each field: 

    <default-hints>
        <hint name="display-width">300</hint>
    </default-hints>

You can define `hint-collection` elements inside the `model-hints` root-level
element to define a list of hints to be applied together. A hint collection
must have a name. For example, Liferay's `portal-model-hints.xml` defines the
following hint collections:

    <hint-collection name="CLOB">
        <hint name="max-length">2000000</hint>
    </hint-collection>
    <hint-collection name="URL">
        <hint name="max-length">4000</hint>
    </hint-collection>
    <hint-collection name="TEXTAREA">
        <hint name="display-height">105</hint>
        <hint name="display-width">500</hint>
        <hint name="max-length">4000</hint>
    </hint-collection>
    <hint-collection name="SEARCHABLE-DATE">
        <hint name="month-nullable">true</hint>
        <hint name="day-nullable">true</hint>
        <hint name="year-nullable">true</hint>
        <hint name="show-time">false</hint>
    </hint-collection>

You can apply a hint collection to a model field by referencing the hint
collection's name. For example, if you define a `SEARCHABLE-DATE` collection
like the one above in your `model-hints` element, you can apply it to your
Event model's date field by using a `hint-collection` element that references
the collection by its name:

    <field name="date" type="Date">
        <hint-collection name="SEARCHABLE-DATE" />
    </field>

As always, remember to run Service Builder and redeploy your project after
updating your `portlet-model-hints.xml` file.

Now you can use a couple of model hints in the Event Listing portlet and
Location Listing Portlet. Start by giving users an editor for filling
in their description fields. Since you want to apply the same hint to both the
event and location entities, define it as a hint collection. Then you can
reference the hint collection in them. 

Define the following hint collection just below the `model-hints` root element
in the `portlet-model-hints.xml` file:

	<hint-collection name="DESCRIPTION-TEXTAREA">
		<hint name="editor">true</hint>
		<hint name="max-length">250</hint>
	</hint-collection>

Then replace the event and location description fields' entities with a
reference to the hint collection, as demonstrated below:

	<field name="description" type="String">
		<hint-collection name="DESCRIPTION-TEXTAREA" />
	</field>

The last hint is one that makes sure the user has no option to select a year
from the past. Replace the event entity's date field with the following date
field as specified below:

	<field name="date" type="Date">
		<hint name="year-range-past">false</hint>
	</field>

Great! Now rebuild your service using Service Builder, redeploy your portlet
project, and add or edit an event using the portlet. The following figure shows
the portlet displaying the input fields as we specified.
	
![Figure 4.9: Customizing string input fields to use spacious text areas and customizing date fields to filter-out past years are just a couple examples of the many things you can do with Liferay model hints.](../../images/service-builder-edit-event.png)

Well, you've learned the art of persuasion through Liferay's model hints. Now,
not only can you influence how your model's input fields are displayed but also
can set its database table column sizes. You can organize hints. Insert
individual hints directly into your fields, apply a set of default hints to all
of a model's fields, or define collections of hints to apply at either of those
scopes. Looks like you've picked up on the "hints" on how Liferay model hints
help portlet data! 

Next, let's find out how to implement a remote service. 
