# Specifications [](id=specifications)

Specifications are pieces of structured product information. The data are
structured into three components: *groups*, *labels* and *values*. This
structure makes the data easy to display and maintain.

The structure of a specification is best explained with an example. In the image
below, *Technical Details* is a group, *Voltage* is a label, and *14.4* is
a value.

![Figure 1: In this image, *Technical Details* is a group, *Voltage* is a label, and *14.4* is a value.](../../images/spec-group.png)

Creating a specification involves creating a group, creating a label to assign
to it, and finally creating a value and assigning it to the label.

## Specification Groups [](id=specification-groups)

Follow these steps to create a specification group:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Catalog* and open the
    *Specification Groups* tab. 

2.  Then click on the ![Add](../../images/icon-add.png) button and fill in
    the following fields:

**Title**: Give the group a title. Typically a group's title will indicate what
it is that it's labels have in common.

**Description**: Enter a description of the group if necessary.

**Priority**: Priority establishes the order in which specification groups will
be displayed. Smallest numbers go first.

**Key**: A key is a programmatic identifier for this specification group. This
field auto-fills; if entered manually, it needs to be unique.

Once the form is complete, click *Save*. The next section explains how to
assign specifications to these categories.

## Specification Labels [](id=specification-labels)

Follow these steps to create a specification label:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Catalog* and open the
    *Specification Labels* tab. 

2.  Then click on the ![Add](../../images/icon-add.png) button and fill in
    the following fields:

**Title**: Give the label a title that describes the values that may be
assigned to it.

**Description**: Enter a description of the label if necessary.

**Use in faceted navigation**: Check this box to make search results filterable
for this specification.

**Specification Group**: Pick the group to which this label belongs. This is
optional, and can be overridden at the product level where specification values
are defined (see below for details).

3.  Click *Save*. The label has been created, but assigning a value to it must
    be done on the product level.

## Specification Values [](id=specification-values)

Specification values are more limited in scope than groups and labels. Groups
and labels are scoped to the catalog, which means that once they are created,
they can be deployed to any product in the catalog. Values, however, are scoped
to the product, and have no impact anywhere but on the product to which they
are assigned.

### Creating a Specification Value [](id=creating-a-specification-value)

Follow these steps:

1.  Go to *Site Menu* &rarr; *Catalog*, select a product, and click on the
    *Specifications* tab.

2.  Click on the ![Add](../../images/icon-add.png) button and select from the list of option labels that is displayed. You can choose more than one.


### Editing a Specification Value [](id=editing-a-specification-value)
To apply your specifications, go to your catalog and select a product. Then open
the *Specifications* tab, click on the ![Add](../../images/icon-add.png)
button and select any number of specification labels from the list, and click
*Add* to close the pop-up.

Now your specification labels have been deployed to the product. To assign
values to the labels, click on each individually, enter a value and choose
whether to override the specification group by making a selection from the
drop-down menu.
