# Handling Low Inventories

On a product's
[*Configuration*](/web/emporio/documentation/-/knowledge_base/1-0/configuration)
page, site administrators have the option to set a *Minimum Stock Quantity*, as
well as to select an action---a *Low Stock Activity*---to be taken
automatically if stocks fall to that level. Out of the box, only one
action---*Set as Unpublished* is a available. This tutorial explains how to add
additional custom options.

Follow these steps:

1. Create a new module and add dependencies on `com.liferay.commerce.api` and
   `com.liferay.commerce.product.api` to its build.gradle file.

2. Create a new class implementing the `CommerceLowStockActivity` interface.

3.  Implement your own logic and add language keys.


First, add the dependences to your module's `build.gradle` file, which should
look like this:


    dependencies { 
    ...
	compileOnly group: "com.liferay.commerce", name: "com.liferay.commerce.api", version: "1.0.0"
    compileOnly group: "com.liferay.commerce", name: "com.liferay.commerce.product.api", version: "1.0.0"
    ...
	}

Then implement the interface. @commerce@'s default low stock activity is handled
by `CommerceLowStockActivityImpl`, which looks like this:

    @Component(
        immediate = true,
        property = {
            "commerce.low.stock.activity.key=" + CommerceLowStockActivityImpl.KEY,
            "commerce.low.stock.activity.priority:Integer=10"
        },
        service = CommerceLowStockActivity.class
    )
    public class CommerceLowStockActivityImpl implements CommerceLowStockActivity {

        public static final String KEY = "default";

While your custom code will have a different class name, it will implement the
same interface. You will also need to change the `KEY` string, which must be
unique.

The `CommerceLowStockActivity` interface requires three methods. The `execute`
method contains the class's core functionality, and is where your custom code
should go. The example below, from the default activity, checks to see whether
the products is published and switches it to unpublished if it is.

	@Override
	public void execute(CommerceWarehouseItem commerceWarehouseItem)
		throws PortalException {

		CPInstance cpInstance = commerceWarehouseItem.getCPInstance();

		if (cpInstance.isPublished()) {
			cpInstance.setPublished(false);

			_cpInstanceLocalService.updateCPInstance(cpInstance);
		}
	}

After the class's core functionality is complete, you need the `getKey` method
to return the class's `KEY` identifier:

	@Override
	public String getKey() {
		return KEY;
	}

Finally, the `getLabel` method returns the label that appears in the UI. In the
example below, the method returns a language key that will display the label in
the appropriate translation. See 
[Language Keys](/develop/tutorials/-/knowledge_base/7-1/localizing-your-application)
for more detail.

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "set-as-unpublished");
	}
