# Setting up a Storefront [](id=setting-up-a-storefront)

Once the catalog is populated, you need a way to display its contents to buyers,
along with an interface for placing orders. If you choose not to use a Site
Initializer such as Beryl, you must set this up manually. 

Here are the components you need:

- Product landing pages

- Product category landing pages

- A shopping cart

- A checkout page

- A search page

- A compare products page

The product and product category landing pages are described in 
[Displaying Product Pages](/web/liferay-emporio/documentation/-/knowledge_base/1-0/displaying-product-pages)
and [Displaying Category
Pages](/web/liferay-emporio/documentation/-/knowledge_base/1-0/displaying-category-pages).

## Shopping Cart [](id=shopping-cart)

The product detail widget (see 
[Displaying Product Pages](/web/liferay-emporio/documentation/-/knowledge_base/1-0/displaying-product-pages))
lets users add products to the cart, but it doesn't make the cart visible.
A cart widget does that. 

There are three separate cart widgets, each with its own uses. They can be found
at the *Add Menu* &rarr; *Widgets* &rarr; *Commerce*.

The *Commerce Cart Content* widget displays the cart's current contents. It
includes controls to change the quantity of the products it holds, or to delete
them entirely.

The *Commerce Cart Content Total* widget displays the total price of all items
in the cart, including a checkout button. It doesn't list the cart's contents.

The *Commerce Cart Content Mini* displays the cart's contents and includes
a checkout button, but has no controls to edit its content. Instead, it displays
an *Edit Cart* button that links to the *Commerce Cart Content* widget. Because
of this link, the Content Mini widget can't function without a page containing
a Cart Content widget.

Follow these steps to set up a cart:

1.  [Create a page](/discover/portal/-/knowledge_base/7-1/creating-pages).

2.  Go to *Add Menu* &rarr; *Widgets* &rarr; *Commerce* and add *Commerce
    Cart Content* and *Commerce Cart Content Total* widgets to the page. This
    is your main shopping cart page.

3.  On other suitable pages, such as product and category pages, add the more
    compact *Commerce Cart Content Mini*, which provides quick reference to the
    cart as well as a link to the main cart page.

## Checkout [](id=checkout)

The purchase process is handled by a *Commerce Checkout* widget. Follow
these steps:

1.  [Create a page](/discover/portal/-/knowledge_base/7-1/creating-pages).

2.  Go to the *Add Menu* &rarr; *Widgets* &rarr; *Commerce* and add a *Commerce
    Checkout* Widget to the page.

Without any further configuration, the *Checkout* widget features a four-step
process: users are prompted to enter a shipping address, a billing
address, an order summary, and finally an order confirmation. When you
configure 
[Shipping Methods](/web/liferay-emporio/documentation/-/knowledge_base/1-0/shipping-methods)
and 
[Payment Methods](/web/liferay-emporio/documentation/-/knowledge_base/1-0/payment-methods),
additional steps are added automatically to the checkout portlet's process,
prompting users to select one of each.

## Search [](id=search)

The Commerce *Search Results* widget displays a product list resulting from
search. There are two ways to populate search results with products: by clicking
on a link in a *Commerce Category Navigation* widget, or by entering search
terms in a *Search Bar* widget.

+$$$

**Note:** the Commerce search results widget (found in *Add Menu* &rarr;
*Widgets* &rarr; *Commerce*) is not the same as the regular search results
widget (in *Add Menu* &rarr; *Widgets* &rarr; *Search*). The regular widget
searches all indexed material---web content, blogs, documents, etc.---while the
commerce version only searches products in the catalog.

$$$

### Category Navigation [](id=category-navigation)

Category navigation always searches by category. When a user clicks on
a link in the navigation widget, the appropriate category page is displayed. If
there is a search results portlet on that page, it shows the products assigned
to that category. See 
[Displaying Category Pages](/web/liferay-emporio/documentation/-/knowledge_base/1-0/displaying-category-pages)
for details.

### The Search Bar [](id=the-search-bar)

In contrast, a search bar takes entered search terms and displays appropriate
products in the results widget. See 
[Configuring Search](/discover/portal/-/knowledge_base/7-1/configuring-search-pages) for
details on how to set up the search bar. Keep in mind that those instructions
assume the use of the standard search results widget. To search the catalog for
products, you must use the Commerce search results widget instead, but the
process is otherwise the same.

### Search Facets [](id=search-facets)

Regardless of whether users click on links to categories or type keywords into
a search bar, they need a way to filter their results. Search facet widgets
make this possible.

The two facet widgets that filter the catalog are *Specification Option Facets*
and *Option Facets*, both of which are in *Add Menu* &rarr; *Widgets* &rarr;
*Commerce*. Place either widget onto a page that also contains a search results
portlet to filter results by
[specification](/web/liferay-emporio/documentation/-/knowledge_base/1-0/specifications)
or [option](/web/liferay-emporio/documentation/-/knowledge_base/1-0/options),
respectively.

## Compare Products [](id=compare-products)

The product comparison feature displays the details of multiple products on
a single page. Follow these steps to enable this feature:

1.  [Create a page](/discover/portal/-/knowledge_base/7-1/creating-pages).

2.  Go to *Add Menu* &rarr; *Widgets* &rarr; *Commerce* and add a *Compare
    Products* widget to the page.

3.  Go to your 
    [product page](/web/liferay-emporio/documentation/-/knowledge_base/1-0/displaying-product-pages) 
    and add a *Compare Products Mini* widget to the page.

When a user clicks on the *Compare* box in the *Product Detail* widget, the
product is saved to compare and displayed in the *Compare Products Mini* widget.
When a users clicks on the *Compare* button in the mini widget, the page with
the *Compare Products* widget appears, showing all products that have been
selected for comparison.
