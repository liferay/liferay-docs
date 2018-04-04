# Catalog Options

An option is any feature of a product that can be selected by the buyer. An
option consists of an **option template**, which specifies the type of thing to
be selected (such as *color* or *size*), and a set of **option values**, which
are the actual selections from which a buyer will choose (such as *red*,
*yellow*, *small* or *large*).

## Option Templates

When an option template is created, it becomes available to the whole catalog,
but must be deployed to individual products before it can be used. To create
a new option, go to *Site Menu* &rarr; *Commerce* &rarr; *Catalog* and click on
the *Option Templates* tab. Then click the *Add* button
(![Add](../../../images/icon-add.png)) at the top of the screen and fill in the
fields on the right.

**Title**: Give the template a name, such as *size* or *color*.

**Description**: This text can be displayed on the product page.

**Option Field Type**: Select from a number of different ways to display options
to customers (drop-down menu, radio buttons, etc).

**Use in faceted navigation**: Check this box to allow search results to be
filtered for this option.

**Required**: Selecting this box forces customers to make a selection before
adding items that deploy this option to their carts.

**Key**: A unique identifier for referring to this option programmatically. As
this field is filled automatically, most users can safely leave it alone.

Once an option has been created and saved, it is displayed in the list on the
left side of the screen. But it cannot be seen by customers unless it has values
assigned to it and has been deployed to a product.

## Option Values

There are two ways to assign values to an option template. One is from the
Options Template tab in *Site Menu* &rarr; *Commerce* &rarr; *Catalog*:

![Figure 1: Click on the *Values* button to enter values for each option.](../../../images/options.png) 

Click on the *Values* button and when the pop-up box appears, click on the *Add*
button at the top of the *Option Values* section. Then in the *Option Value
Detail* section, fill in the *Title* and *Priority* fields and hit *Save*.

![Figure 2: For example, the option *Color* would likely be assigned values with titles such as *Black*, *White*, and *Brown*--or, for more adventurous users, *Azure*, *Fuchsia*, and *Sable*.](../../../images/option-values.png) 

The contents of the *Priority* field simply establish the order in which the
option values will be displayed on the product page (lowest number comes first).
The *Key* field, again, is auto-filled so most users can leave it alone.

The method of assigning values to options given on this page is Catalog-wide.
Once you've assigned a set of values to an option, you've created a template for
every product that subsequently deploys that option. If you're running a shoe
store, it may suit you to have a *Size* option that imposes the values 1, 2, 3,
etc. on all of your products. But what if you also want to sell T-shirts, which
would need *Size* values of small, medium and large? For that use case, you'll
want to override your values by assigning new ones to certain products
[individually](/discover/portal/-/knowledge_base/7-1/product-options).
