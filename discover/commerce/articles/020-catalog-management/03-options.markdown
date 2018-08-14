# Options [](id=options)

An *option* is a product feature that a buyer can select or specify. An option
could prompt a buyer to choose between T-shirts of different colors, building
materials of different dimensions, or engines of different displacements. 

To create an option, you must first create an option template and then a set of
option values. You must also 
[apply the template to a product](/web/liferay-emporio/documentation/-/knowledge_base/1-0/adding-options-to-products).

**An Option Template** describes the kind of variation that the option defines.
If the buyer should be prompted to choose between product variants of different
sizes, then the template would be defined as *Size*.

**An Option Value** refers to a specific selection a buyer can choose. For
example, an option template called *Size* can be assigned the option values
*Small*, *Medium*, and *Large*.

+$$$

**Note**: not every option requires option values. Values allow buyers to choose
from among preset variants. Values are not required for options that are
open-ended: for instance, the buyer can be prompted to enter custom text to be
printed or engraved on the product, or asked to provide schematics of a custom
product for fabrication or 3D-printing.

You can select whether an option template requires values when you set its field
type (see below).

$$$

An option template can be applied to any product in the catalog. This makes it
easy to apply frequently-used options to a large number of products. Once
applied, however, the link between template and product is severed: template
changes don't affect the product, and product changes don't affect the template.
This allows each product's options to be customized, and it ensures that those
customizations aren't unintentionally changed by future template changes.

## Creating an Option Template [](id=creating-an-option-template)

Follow these steps to create a new option template:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Catalog* and click on the
    *Options* tab. This displays a list of existing templates on the left of the
    page and the *Option Template Detail* form on the right.

2.  Then click ![Add](../../images/icon-add.png), fill in the following fields
    from the Option Template Detail form, and click *Save*.

**Title**: Give the template a name, such as *size* or *color*.

**Description**: This text can be displayed on the product page.

**Field Type**: Select from the following different ways to display
options to customers:

- *Select from List:* Displays option values in a drop-down box.

- *Single Selection:* Displays option values with a radio button next to each.
  Since radio buttons cannot be deselected, only use this field type for options where
  a selection is required.

- *Date:* Prompts buyers to select a specific date---applicable for services,
deliveries, and the like. It does not take option values.

- *Boolean Question:* Displays a checkbox next to the option template. It does
not does not take option values. It is useful where an option is a simple
binary, such as "gift-wrap".

- *Multiple Selection:* Displays a checkbox next to each option value. This
 allows more than one value to be selected for a single template---useful for offering
a choice among add-ons or accessories.

- *Numeric:* Displays a field where a number can be entered, for
entering custom dimensions or the like. It does not take option values.

**Use in faceted navigation**: enable to allow search results to be filtered for
this option.

**Required**: forces customers to make a selection before purchasing products
that use this option.

**SKU Contributor:** a SKU contributor can be assigned a unique price and other
attributes for each variant via
[SKUs](/web/liferay-emporio/documentation/-/knowledge_base/1-0/SKUs). If
enabled, the option must have an SKU in order to be sold.

**Key**: A unique identifier for referring to this option programmatically. As
this field is filled automatically, most users can safely leave it alone.

Once an option has been created and saved, it is displayed in the list on the
left side of the screen.

## Creating Option Values [](id=creating-option-values)

Depending on the Field Type you specified when you created your template, you
may need to create values for your option. 

Follow these steps:

1.  Go to the catalog and select the *Options* tab. Find the template
    you want to create values for and click on the *values* button next to its
    title.

2.  Click on the ![Add](../../images/icon-add.png) button and fill in the
    following fields in the *Option Value Detail* form.

3.  Click *Save*. Repeat steps 2 and 3 to create additional values.

**Title:** Give the value a name.

**Priority:** Values are displayed on a page in order of priority. Lower
numbers come first.

**Key:** A key is a unique identifier for referring to this value
programmatically. This field is filled automatically, so most users can
safely leave it alone.

These values apply to every product where the template is applied. You can
override these values for individual products when you 
[apply the template to a product](/web/liferay-emporio/documentation/-/knowledge_base/1-0/adding-options-to-products).
