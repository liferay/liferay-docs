# Creating New Shipping Methods

@commerce@ includes three shipping methods out of the box: *fixed*- and
*variable*-rate methods give you a wide range of options for calculating
shipping costs in-house, while the FedEx method (subscribers only) provides an
option for you to integrate your system with the multinational courier of the
same name. Since the provided shipping methods may not meet every user's
needs---suppose you want to integrate with a shipping company other than FedEx,
for example---commerce exposes an extension point to allow new methods to be
developed.

The following instructions cover the creation of new shipping methods.
Business logic to integrate with any particular shipping company (UPS, DHL,
etc.) is outside the scope of this tutorial.

## Shipping Options and Shipping Methods

Before we dive in, it's important to understand the difference between a
*shipping option* and a *shipping method*:

A **Shipping Option** determines how an order will be shipped and is selected
by buyers at the time of checkout.

![Figure 1: Buyers choose from among various shipping options at checkout.](../../images/shipping-options.png)

A **Shipping Method** provides a process for calculating the cost of a given
shipping option. Shipping methods are selected by administrators when they
create the options to present to buyers.

![Figure 2: Every shipping option relies on a cost calculation provided by one of these shipping methods---or by a custom method that you create.](../../images/shipping-methods.png)

Administrators can create any number of shipping options for buyers to select,
but all of them will will rely on a shipping method to calculate costs.

## Creating a Custom Method

Creating a new shipping method involves implementing the
`CommerceShippingEngine` interface and writing your own logic for the
`getCommerceShippingOptions` method.

Follow these steps:

1.  Create a new module and include dependencies on
    `com.liferay.commerce.product.api` and `com.liferay.commerce.api` in its `build.gradle`
    file.

2.  Create a component implementing the
    `com.liferay.commerce.model.CommerceShippingEngine` interface.

First, add dependencies to `build.gradle`. The build script should look like
this:

    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"

    dependencies {
        compileOnly group: "com.liferay.commerce", name: "com.liferay.commerce.product.api", version: "2.0.0"
        compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "3.5.0"
        compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
        compileOnly group: "com.liferay.commerce", name: "com.liferay.commerce.api", version: "3.0.0"
        compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
    }

Next, create your component class:

    @Component(
        immediate = true,
        property = "commerce.shipping.engine.key=" + SampleCommerceShippingEngine.KEY,
        service = CommerceShippingEngine.class
    )
    public class SampleCommerceShippingEngine implements CommerceShippingEngine {

        public static final String KEY = "sample";

The KEY constant in this example provides a unique identifier for this shipping
method.

The `getCommerceShippingOptionLabel` method returns a localized value for the
shipping method's name, and is called when during the checkout process when
a buyer is prompted to select a shipping option.

    @Override
    public String getCommerceShippingOptionLabel(String name, Locale locale) {
        ResourceBundle resourceBundle = _getResourceBundle(locale);

        return ResourceBundleUtil.getString(resourceBundle, name);
    }

The main business logic of the shipping method is handled by the
`getCommerceShippingOptions` method. A new shipping *option* is created when
this method is called from an appropriate context.

    @Override
        public List<CommerceShippingOption> getCommerceShippingOptions(
                CommerceContext commerceContext, CommerceOrder commerceOrder,
                Locale locale)
            throws CommerceShippingEngineException {

            List<CommerceShippingOption> commerceShippingOptions =
                new ArrayList<>();


            commerceShippingOptions.add(new CommerceShippingOption("sample-option", "sample-option", BigDecimal.TEN));
            
                   return commerceShippingOptions;
        }

This method calculates the shipping cost and passes it in as a parameter of
a `CommerceShippingOptions` object. In this case, the method specifies a fixed
cost of 10. The method also passes in a name, `"sample-option"`, that
identifies the option in the checkout UI when an administrator activates the
method.

The component's final methods provide additional labeling information:

    @Override
        public String getDescription(Locale locale) {
            ResourceBundle resourceBundle = _getResourceBundle(locale);

            return LanguageUtil.get(resourceBundle, "sample-shipping-description");
        }

        @Override
        public String getName(Locale locale) {
            ResourceBundle resourceBundle = _getResourceBundle(locale);

            return LanguageUtil.get(resourceBundle, "sample-rate");
        }


        private ResourceBundle _getResourceBundle(Locale locale) {
            return ResourceBundleUtil.getBundle(
                "content.Language", locale, getClass());
        }


    }

The `getDescription` method populates the *Description* field of the shipping
method's  admin screen, while `getName` fills the *name* field on the same
screen. The `_getResourceBundle` method provides the appropriate language
bundle to the previous two methods.
