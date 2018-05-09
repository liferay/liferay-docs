# Displaying Product Pages [](id=displaying-product-pages)

Once the catalog is populated, it makes sense to start thinking about how to
display its contents to potential buyers. In most cases, this means that each
product will need a page of its own.

There are two ways to do this: you can manually create a unique page for each
product, or you can create a single default page that will be automatically
populated with the appropriate content when it is opened.

## Creating Product Pages Individually [](id=creating-product-pages-individually)

Displaying a product page is simple two-step process.

1. [Create a page][discover/portal/-/knowledge_base/7-1/creating-and-managing-pages].
2. Navigate to the catalog and select the product which you want to assign to
   the page. Click on the *Configuration* tab and then on *Product Display
   Page.* Click *Select* and then choose the page you just created.

Of course, there's no content on your page, so you can't stop there. You can get
details on content in the 
[Creating Web Content][discover/portal/-/knowledge_base/7-0/creating-web-content]
section, but before you go there you may want to consider whether creating an
individual page for every product in your catalog is really the best option. It
may make sense for some sellers, but for retailers with hundreds or thousands of
different items for sale, there's a better way.

## Creating a Default Product Page [](id=creating-a-default-product-page)

Creating a single default product display page is no more complicated than
creating an individual page--but you only need to do it once. Even better, you
don't give anything up: any individual product pages you create will override
the default page, so you can still fine-tune the look for individual products to
your heart's content. Follow these steps:

1. [Create a page][discover/portal/-/knowledge_base/7-1/creating-and-managing-pages].
2. Deploy the *Commerce Product Details* portlet to the page.

When a user clicks on a link to a product page, @commerce@ will first check to
see if there is a unique display page has been assigned to the product. If there
isn't one, then the first page with a product details portlet will be used.
