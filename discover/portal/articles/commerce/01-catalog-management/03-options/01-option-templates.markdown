# Option Templates 

To create an option and apply it to a product, you must first create an option
template. Once created, and option template can be assigned to any product in the catalog.

## Creating an Option Template

Follow these steps to create a new option template:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Catalog* and click on the
    *Option Templates* tab. This displays a list of existing templates on the
    left of the page and the *Option Template Detail* form on the right.

2.  Then click the ![Add](../../../images/icon-add.png) button at the top of the
    screen and fill in the following fields in the form:

**Title**: Give the template a name, such as *size* or *color*.

**Description**: This text can be displayed on the product page.

**Field Type**: Select from the following different ways to display
options to customers:

-*Select:* This general-purpose field type displays option values in a drop-down
box.

-*Radio:* This field type displays option values with a radio button next to
each. Since radio buttons cannot be deselected, this field type should be be
used for options where a selection is not required.

-*Date:* This field type prompts buyers to select a specific date--applicable
for services, deliveries, and the like. It does not take option values.

-*Checkbox:* This type displays a checkbox next to the option template. It does
not does not take option values. It is useful where an option is a simple
binary, such as "gift-wrap".

-*checkbox_multiple:* This type displays a checkbox next to each option value.
This allows more than one value to be selected for a single template--useful for
offering a choice among add-ons or accessories.

-*Numeric:* This type displays a field where a number can be entered, for
entering custom dimensions or the like. It does not take option values.

-*Text:* This field type allows the buyer to enter custom text. It can be used
to enter text that will be printed (or engraved, embroidered, etc.) on the
product, or as an open-ended way for the buyer to provide other information to
the seller. It does not take option values.

-*Paragraph:* The paragraph field type works exactly like *text*, but provides
a larger field.

**Use in faceted navigation**: Check this box to allow search results to be
filtered for this option.

**Required**: Selecting this box forces customers to make a selection before
purchasing products that deploy this option.

**SKU Contributor:** An option that is an SKU contributor can be assigned
a unique price and other attributes for each variant via
[SKUs](discover/portal/-/knowledge_base/7-1/SKUs). If check, the option must
have an SKU in order to be sold.

**Key**: A unique identifier for referring to this option programmatically. As
this field is filled automatically, most users can safely leave it alone.

3.  Click *Save*.

Once an option has been created and saved, it is displayed in the list on the
left side of the screen. But it cannot be used until it has been assigned to
a product.

## Assigning an Option Template to a Product

To assign an option template to a product, follow these steps:

1.  Go to the catalog, open a product and click on the *Options* tab.

2.  Click on the ![Add](../../../images/icon-add.png) button and select option
    templates from the pop-up. Click *Add* to close to pop-up.

## Editing an option template at the product scope

Once an option template has been assigned to a product, you can make changes to
it that only affect that product--other products that assign the same template
will not be affected. Follow these steps:

1.  Go to the catalog, select the product in question, and open the *Options*
    tab. If you just finished assigning a template to the product in question,
    then you are already on this page.

2.  On the left side of the page is a list of the option templates this product
    deploys. Click on the one you want to edit.

3.  Make changes to the *Option Detail* form on the right side of the page and
    then hit *save*. The form contains most of the information that was entered
    when the template was created, but includes a *Priority* field. Priority
    indicates where on a page the option will be displayed--lower numbers come
    first.

