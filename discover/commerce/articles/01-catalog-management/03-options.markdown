# Options [](id=options)

**An option** is a product feature that <!--a buyer selects or specifies--> is
selected or specified by the buyer. An option could prompt a buyer to choose
between engines of different displacements, building materials of different
dimensions, or T-shirts of different colors.

To create an option, you must first create an option template and then a set of
option values. You must also 
[assign the template to a product](/web/liferay-emporio/documentation/-/knowledge_base/7-1/assigning-options-to-products).

**An Option Template** describes the kind of variation that the option defines.
If the buyer should be prompted to choose between product variants of different
sizes, then the template would be defined as *Size*.

**An Option Value** refers to a specific selection a buyer might choose. For
example, an option template called *Size* might be assigned the option values
*Small*, *Medium*, and *Large*.

+$$$

Note: not every option requires option values. Values must be defined in order
to prompt buyers to choose from among preset variants. Values are not required
for options that are more open-ended: for instance, the buyer might be prompted
to enter custom text to be printed or engraved on the product, or asked to
provide schematics of a custom product for fabrication or 3D-print

You can select whether an option template requires values when you set its field
type (see below).

$$$

**Scope:** When an option template is created, its default scope is the catalog.
Every product it's assigned to inherits the template's configuration. However,
when you deploy a template to an individual product, you can make changes to the
template that are scoped only to that product---other products in the catalog are
not affected.

Option values can be scoped either to an option template or to a product. Values
that are scoped to a template apply to all products that deploy that template.
Values scoped to a product apply only to that product, but can override any
template-scoped values.

+$$$

Note: changes at the catalog scope are not propagated to the product scope. This
means that if you edit an option template, it does not affect any products to
which that template has already been assigned. This ensures that products that
override the template are not unintentionally tampered with by future template
changes.

$$$

## Creating an Option Template [](id=creating-an-option-template)

Follow these steps to create a new option template:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Catalog* and click on the
    *Option Templates* tab. This displays a list of existing templates on the
    left of the page and the *Option Template Detail* form on the right.

2.  Then click the ![Add](../../images/icon-add.png) button at the top of the
    screen, fill in the following fields from the Option Template Detail form,
    and click *Save*.

**Title**: Give the template a name, such as *size* or *color*.

**Description**: This text can be displayed on the product page.

**Field Type**: Select from the following different ways to display
options to customers:

-*Select:* displays option values in a drop-down box.

-*Radio:* displays option values with a radio button next to each. Since radio
buttons cannot be deselected, this field type should be used for options where
a selection is not required.

-*Date:* prompts buyers to select a specific date---applicable for services,
deliveries, and the like. It does not take option values.

-*Checkbox:* displays a checkbox next to the option template. It does
not does not take option values. It is useful where an option is a simple
binary, such as "gift-wrap".

-*checkbox_multiple:* displays a checkbox next to each option value. This allows
more than one value to be selected for a single template---useful for offering
a choice among add-ons or accessories.

-*Numeric:* displays a field where a number can be entered, for
entering custom dimensions or the like. It does not take option values.

-*Text:* allows the buyer to enter custom text. It can be used to enter text to
be printed (or engraved, embroidered, etc.) on the product, or as an open-ended
way for the buyer to provide other information to the seller. It does not take
option values.

-*Paragraph:* works exactly like *text*, but provides a larger field.

**Use in faceted navigation**: Check this box to allow search results to be
filtered for this option.

**Required**: forces customers to make a selection before purchasing products
that deploy this option.

**SKU Contributor:** an SKU contributor can be assigned a unique price and other
attributes for each variant via
[SKUs](/web/liferay-emporio/documentation/-/knowledge_base/7-1/SKUs). If
checked, the option must have an SKU in order to be sold.

**Key**: A unique identifier for referring to this option programmatically. As
this field is filled automatically, most users can safely leave it alone.

Once an option has been created and saved, it is displayed in the list on the
left side of the screen.

## Creating Option Values at the Catalog Scope [](id=creating-option-values-at-the-catalog-scope)

Depending on what Field Type you specified when you created your template, you
may need to create values for your option. 

Follow these steps:

1.  Go to the catalog and select the *Option Templates* tab. Find the template
    you want to create values for and click on the *values* button next to its
    title.

2.  Click on the ![Add](../../images/icon-add.png) button and fill in the
    following fields in the *Option Value Detail* form:

    **Title:** Give the value a name.

    **Priority:** Values are displayed on a page in order of priority. Lower
    numbers come first.

    **Key:** A key is a unique identifier for referring to this value
    programmatically. This field is filled automatically, so most users can
    safely leave it alone.

3.  Click *Save*. Repeat steps 2 and 3 to create additional values.

Keep in mind that values scoped to the catalog apply to every product assigned
to your template. You can override these values for individual products when
you [assign the template to
a product](/web/liferay-emporio/documentation/-/knowledge_base/7-1/assigning-options-to-products).
