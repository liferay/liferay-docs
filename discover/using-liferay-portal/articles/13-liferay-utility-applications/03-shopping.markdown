# Shopping [](id=shopping)

Would your organization like to make some money selling promotional items? Are
you an artist looking to share your work with the world? Perhaps your company
produces a publication that customers want to purchase? If you have something of
value the visitors of your site want or need, then Liferay's Shopping
application can help you get these items to your customers with a secure
transaction.

The Shopping portlet uses PayPal and allows you to choose the credit cards
your store accepts. You can organize your inventory with categories and
subcategories. A search function helps users find items quickly. Users
place items in a shopping cart, allowing them to purchase multiple items at
once. There is also an email notification system to alert customers when their
transactions are processed.

Before we start printing money, let's first create an online store.

## Setting up shop

To begin setting up a store, place the Shopping application on a page in your
site. Like the Message Boards portlet, the Shopping portlet takes up a lot of
space. It's best, therefore, to dedicate an entire page to the application. The
Shopping portlet is available from the *Add* &rarr; *Applications* menu on the
left side panel under Shopping.

![Figure 13.11: Start setting up the store by entering items and categories in the shopping portlet.](../../images/shopping-add-portlet.png)

The shopping portlet has four tabs across the top:

**Categories:** shows the categories of items in your store. For example, if
you're selling music, you might have categories for various genres, such as pop,
rock, metal, hip hop, and the like. The portlet defaults to this view.

**Cart:** shows the items the user has selected to purchase from your store. It
displays the order subtotal, the shipping cost, and a field for entering a
coupon code. There are buttons for updating the cart, emptying the cart, and
checking out.

**Orders:** displays a list of all previous orders placed, containing options to
search for orders by the order number, status, first name, last name and/or
email address.

**Coupons:** lets you define coupon codes to offer discounts to your customers.
You can enter the coupon code, discount type, and whether it is active or not.
Search looks for a particular coupon offer while Add Coupon opens a new form to
key in the coupon data. Delete removes a coupon.

Below the tabs are breadcrumbs for navigating between the categories and
subcategories you create. In fact, this would be a good time to start creating
some categories.

### Creating Categories

It's not difficult to create categories. Simply click the *Add Category* button
to display the Category form. In this form enter the *Name*, *Description*, and
set the *Permissions* for the category. That's all there is to it.

![Figure 13.12: In this figure there are three subcategories for the `Aromatherapy` category. The first subcategory has three items, the second has two, and the third is empty.](../../images/shopping-categories.png)

When you click *Save*, your new category is listed in the portlet, along with
the number of subcategories and the number of items that are in the category.
You can edit the category, set permissions for it or delete it using the
*Actions* button.

![Figure 13.13: Breadcrumbs are an important navigational tool in the shopping portlet.](../../images/shopping-category-breadcrumbs.png)

Each category can have unlimited subcategories and you can add subcategories to
any category. Notice as you add categories and subcategories, navigational
breadcrumbs appear in the portlet. Use these to move through the store
inventory.

### Creating Items

When you select a category, you'll see its items appear. You create items the
same way you create categories. Use the *Add Item* button to open the new item
form. Enter data for the SKU number, name, description, and item properties. You
can select checkboxes to specify whether the item requires shipping and
whether it is a featured item. Enter the stock quantity to record how many
items are available and set the appropriate permissions.

The Fields area is where you add additional fields to set specific
characteristics for the item. These can include things like sizes and colors.
The additional fields appear in the item form as pull-down menus.

The Prices area is for all data pertaining to the item's cost, minimum, and
maximum quantities, quantity discounts, taxes, and shipping costs.

The Images area lets you add photos to the item form. You can add a link to the
photo or upload the file locally. Choose from three sizes of images. You must
select the appropriate check box for the image you want to display. When you're
finished creating a new item, click *Save*.

![Figure 13.14: The image in this figure is the medium sized option.](../../images/shopping-item-image2.png)

As products are added, they are listed in the Items section of the portlet. If
the item you just created needs to go into one of your new categories or
subcategories, you can assign it to the category by editing the item. Choose the
*Select* button (next to the *Remove* button), and this displays a dialog box
listing all the shop categories. Choose the desired category from the list to
relocate the item to its new location. Notice how the breadcrumbs reflect this
change in the item form. For a tutorial on categories, breadcrumbs, and item
relocation, visit the *Moving a Link* section in this chapter.

You can make changes to any item through *Actions* &rarr; *Edit*. Finding an
item is easy, using the *Search* function.

That's how you create an item for the store. Now let's examine some of the
shopping portlet's configuration options.

## Configuration

By selecting the *gear* icon in the top right of the portlet, you can manage
the configuration options of the shopping application. In the Setup view, there
are tabs for Payment Settings, Shipping Calculation, Insurance Calculation, and
Emails.

### Payment Settings

The payment settings section is where you configure all the functions related to
transactions for your store.

**PayPal Email Address:** is the address of your store's PayPal account which is
used for payment processing.

Note that PayPal can be disabled by entering a blank PayPal address in the
field. Credit cards can likewise be disabled. Payments to the store are not
required when these settings are disabled. The credit card function does not
process payments; it instead stores the card information with the order so you
can process the transaction separately. 

**Credit Cards:** sets the type of credit cards your store accepts.

The Current column holds the cards your store takes. The Available column holds
cards not accepted by your shop. These can be moved easily from one column to
another by selecting a card and clicking one of the arrow buttons. The arrows
below the Current window allow you to choose the order credit cards are
displayed on the form.

**Currency:** sets the appropriate currency your shop accepts.

**Tax State:** sets the applicable state where your business is responsible for
paying taxes.

**Tax Rate:** sets the percentage of taxes your store is responsible for paying.
This rate is added as a sales tax charge to orders.

**Minimum Order:** sets the minimum amount required for a sale.

### Shipping and Insurance Calculation

Both the Shipping and Insurance forms have identical options.

**Formula:** sets the equation for determining shipping and insurance costs.
They're calculated on either a flat rate based on the total purchase amount or
on a percentage of the total amount spent.

**Values:** sets the shipping and insurance fees based on a range of figures
that the total order amount falls under.

### Emails

This form sets the addresses for customer email notifications. Use the list of
term definitions below to customize the correspondence with your customers.

![Figure 13.15: Shopping emails can be configured in a myriad of ways to suit your needs.](../../images/shopping-confirmation-email-form.png)

**Emails From:** sets the email address from which order and shipping
notifications are sent.  

**Confirmation Email:** Use this form to customize the email message customers
receive when an order is received. 

**Shipping Email:** Use this form to customize the email message customers
receive when an order has been shipped.

So far we have added the shopping portlet to your site, created categories and
items for your store, set up payment options, and configured customer
communication options. These are the basics required to get your store up and
running. Now let's review the buying process.

## Using the shopping cart

Logged in users are given a shopping cart to store the items they wish to buy.
Customers can manage items and their quantities directly from the cart, allowing
them to purchase a single product or multiple products at once. Customers can
also key in coupon codes to take advantage of any discounts your store has to
offer. Products can be placed in the cart from any category or subcategory. The
cart's appearance can be customized to reflect the overall design of your store.

When buyers select an item, they see the item's description displaying all of
its relevant information. The figure below is typical of what an item's
description might look like.

![Figure 13.16: Your product's SKU number is listed above the image.](../../images/shopping-item.png)

Below the product description is the Availability field indicating whether the
item is in stock. There are also two buttons for managing the shopping
experience:

**Add to Shopping Cart:** places the item in your cart for checkout.

**Next:** lets you to scroll through all the items in the category, giving you
the option to add to the cart as you go.

After adding an item to the cart, click Back (blue arrow) to return to the
product description and continue shopping by navigating the category breadcrumbs
at the top of the form. You can also continue shopping by scrolling through a
category, item by item, using the *Previous* and *Next* buttons at the bottom of
the product description.

Each time you add an item to the cart, a running tally of the cart's contents is
kept. Quantities for each item are controlled using drop-down menus.  The order
subtotal and shipping costs appear above a field where coupon codes can be 
entered. When you have finished adding products to the cart, you have three
options:

**Update Cart:** lets you change the quantity of an item being purchased.
If a minimum number of items has been set in the item description, the field
under the Quantity column shows that number by default. You can adjust
the exact number of items you want with the drop-down menus in the cart.

**Empty Cart:** lets you clear the contents of the cart to either start
shopping again or to stop shopping.

**Checkout:** sends you to a new form to verify the billing address, shipping
address, and the credit card information. You can also add comments about the
order if necessary.

![Figure 13.17: The shopping cart gives a preview of the items you'd like to buy.](../../images/shopping-cart-order.png)

When you're ready to checkout, click the *Checkout* button at the bottom of the
screen. When all the data has been entered correctly, click *Continue* to see
the order summary. After reviewing the summary, click *Finished* and you are
given confirmation the order has been placed, along with the order number. Use
this number to search for the order history and keep track of its status.

### Customizing the shopping cart with a hook

If you think the shopping cart looks a little basic for your purposes, you can
customize it by using a hook. To learn more about changing the appearance of the
shopping cart, consult the [*Liferay Developer's
Guide*](http://www.liferay.com/documentation/liferay-portal/6.2/development) or
see section 8.3 in [*Liferay in Action*](http://manning.com/sezov). 

Now your online store is set up, you have inventory, you have a payment system,
and you have sales rolling in. All is good. Some day there will be customers
with questions about their orders. Let's go over the orders next.

## Managing Orders

On the Orders tab, there are fields for finding specific orders. Search for
orders using the order number, order status, first or last name on the order, or
by the email address associated with the account. For more information on
searching in Liferay Portal, see the Faceted Search section in chapter 6 of this
guide.

Below the search fields is the orders list. Orders can be deleted or edited
using the *Actions* button. When you select an order from the Orders tab, or if 
you edit an order, you see a summary of the order details along with some 
options across the bottom.

**Invoice:** creates a printer-friendly copy of the order that can be sent to a
customer.

**Resend Confirmation Email:** lets you notify the customer that the order
has been received and is being processed.

**Send Shipping Email:** notifies the customer that the order is *en route*. You
can also include a tracking number with this email to allow the customer to
follow the delivery process.

**Delete:** removes the order from the system.

**Cancel:** closes the Edit view and returns the user to the main orders view. 

You can also add comments about the order and subscribe to the comments to get
any updates on the order.

## Managing Coupons

The Coupons view of the Shopping application lets you provide coupon codes for
special sale events or other discounts. You can determine the type of discount
to apply and whether it is currently active. You can search for coupons and
create new coupons from this form.

![Figure 13.18: Create a coupon code automatically when you select the Autogenerate Code box.](../../images/shopping-coupon.png)

To add a coupon, enter the coupon code in the Code field. If no code is
specified, you can create one automatically by selecting the *Autogenerate Code*
checkbox. After entering the coupon's name and description, you can set the
coupon's start and expiration dates. Additional options let you activate the
coupon and set it to never expire.

![Figure 13.19: Customize your coupon parameters under Discounts and Limits.](../../images/shopping-coupon-discount-limits.png)

Under the Discount section, you can set the minimum order amount required for
the discount, the discount amount, and the discount type. Types can be based on
a percentage, a fixed amount, free shipping, or a tax free sale. The Limits
section lets you set coupon restrictions based on a list of categories and/or
SKU numbers.

### Integrating the Amazon Rankings portlet

If your store sells books, you can use Liferay's Amazon Rankings application to
display them alongside the main shopping portlet. Both of these are found in the
Shopping category under *Add* &rarr; *Applications* in the left menu. The Amazon
Rankings application lets you highlight the books in your store's inventory
outside of the typical category structure. Books are arranged in ascending order
according to Amazon's Best Sellers Rank. Book cover images displayed in the
portlet come from the images in the product's description.

#### Setting up your Amazon Web Services account

To use Amazon rankings, you must first setup an Amazon Associates Program
account. This gives you the *associate ID tag* you need to enter in your
`portal-ext.properties` file. Then you need to join the Amazon Product Advertising
API group. This yields the *access key id* and the *secret access key* that
also must go into your `portal-ext.properties` file.

Amazon License Keys are available here:

[https://aws-portal.amazon.com/gp/aws/developer/registration/index.html/](https://aws-portal.amazon.com/gp/aws/developer/registration/index.html/)

Add the following lines to your `portal-ext.properties` file and populate the
values for the associate ID tag, access key ID, and secret access key. Ensure
there are no spaces between the `=` sign and the property values.

	amazon.access.key.id=
	amazon.associate.tag=
	amazon.secret.access.key=

Note that these keys are provided by Amazon for personal use only. Please
consult Amazon at [http://www.amazon.com](http://www.amazon.com) for more
information.

To obtain the `amazon.associate.tag`, visit
[https://affiliate-program.amazon.com/gp/associates/apply/main.html](https://affiliate-program.amazon.com/gp/associates/apply/main.html)
and apply, if necessary. Your associate tag is the *Tracking ID* listed in the
upper left corner of the [Associates
Central](https://affiliate-program.amazon.com/gp/associates/network/main.html)
page.

+$$$

**Tip:** Make sure to create your affiliate
program before generating an access key; otherwise, the access key will not be
linked to your affiliate program. 

$$$

If your Amazon Web Services key is set improperly, you can't add books to your
Shopping portlet.

#### Setting up the Amazon Rankings portlet

After setting up your Amazon Web Services account, choose the books to display
in your store. Select *Configuration* from the Amazon Rankings portlet in the
upper right corner. Go to the *Setup* tab and enter the International Standard
Book Numbers (ISBNs) in the textbox, separated by spaces. The portlet accepts
10-digit ISBNs rejecting ISBNs that letters.

![Figure 13.20: Separate ISBNs with single spaces.](../../images/shopping-amazon-rankings-config.png)

When you are finished setting up the rankings, books appear in the portlet
similar to the example below. Clicking on the book's cover image opens the
book's Amazon page.

![Figure 13.21: Using the Amazon Rankings application can be a nice addition to your store.](../../images/shopping-and-amazon-rankings.png)

Now that you have a good grasp on Liferay's Shopping and Amazon Rankings
applications, let's learn how to generate reports in Liferay.
