# Specifications [](id=specifications)

Specifications are pieces of structured product information. The data are
structured into three components: *groups*, *labels* and *values*. This
makes the data easy to display and maintain.

Values are identified by labels, which in turn are organized into groups. In
the image below, *14.4* is a value, *Voltage* is a label, and *Technical
Details* is a group.

![Figure 1: In this image, *Technical Details* is a group, *Voltage* is a label, and *14.4* is a value.](../../images/spec-group.png)

Creating a specification involves creating a group and then creating a label to
assign to it. Finally, you must create a value, which will be assigned to both
a label and a product.

## Specification Groups [](id=specification-groups)

Follow these steps to create a specification group:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Catalog* and open the
    *Specification Groups* tab. 

2.  Then click on the ![Add](../../images/icon-add.png) button and fill in
    the following fields:

**Title**: Give the group a title. Typically a group's title will indicate what
its labels have in common.

**Description**: Enter a description of the group if necessary.

**Priority**: Priority establishes the order in which specification groups will
be displayed. Smallest numbers go first.

**Key**: A key is a programmatic identifier for this specification group. This
field auto-fills; if entered manually, it needs to be unique.

Once the form is complete, click *Save*.

## Specification Labels [](id=specification-labels)

Follow these steps to create a specification label:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Catalog* and open the
    *Specification Labels* tab. 

2.  Click on the ![Add](../../images/icon-add.png) button and fill in
    the following fields:

**Title**: Give the label a title that describes the values that may be
assigned to it.

**Description**: Enter a description of the label if necessary.

**Use in faceted navigation**: Check this box to make search results filterable
for this specification.

**Specification Group**: Pick the group to which this label belongs. This is
optional, and can be overridden at the product level where specification values
are defined (see below for details).

Click *Save*. The label has been created, but assigning a value to it must
be done on the product level.

## Specification Values [](id=specification-values)

Specification values are more limited in scope than groups and labels. Groups
and labels are scoped to the catalog, which means that once they are created,
they can be assigned to any product in the catalog. Values, however, are scoped
to the product, and have no impact anywhere but on the product to which they
are assigned.

To create a specification label, you must first assign a label to a product.

1.  Go to *Site Menu* &rarr; *Catalog*, select a product, and click on the
    *Specifications* tab.

2.  Click on the ![Add](../../images/icon-add.png) button and select from the
    list of option labels that is displayed. Click *Add* to close the pop-up.
    This assigns the selected labels to the product.

Next, edit the label to give it a value.

1.  Click on the ![Options](../../images/icon-options.png) button next to the
    new label and hit *Edit*.

2.  Enter a value. For example, if your label is *Material*, then the value
    should describe what the product is made out of: *polymer*, *ceramic*, or
    *stainless steel*. 
 
3.  Set the priority and hit save.

If you want, you can also override the specification group that was selected
when the label was created. When editing the label to assign a value, you can
choose any group from the *Group* drop-down box. This change is only applied to
the current product--it has no effect on any other products which use the same
label.
