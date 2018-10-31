# Creating New Shipping Methods

@commerce@ includes three shipping methods out of the box: *fixed*- and
*variable*-rate methods give you a wide range of options for calculating
shipping costs in-house, while the FedEx method (subscribers only) provides an
option for you to integrate your system with the multinational courier of the
same name. Administrators can create any number of shipping options for buyers
to select, but all of them will will rely on one of these methods to calculate
costs.

![Figure 1: Buyers can choose from among various shipping options at checkout.](../../../images/shipping-options.png)

![Figure 2: But every option relies on a cost calculation provided by a shipping method at *Site Menu* &rarr; *Commerce* &rarr; *Settings* &rarr; *Shipping
Methods*.](../../../images/shipping-methods.png)

Shipping options are created when the `getCommerceShippingOptions` method from
the `CommerceShippingEngine` interface is called from an appropriate context.

Since the provided shipping methods may not meet every user's needs---suppose
you want to integrate with a shipping company other than FedEx, for
example---commerce exposes an extension point to allow new methods to be
developed. This involves implementing the `CommerceShippingEngine` interface and
writing your own logic for the `getCommerceShippingOptions` method. This creates
a new method that administrators can select from *Site Menu* &rarr; *Commerce*
&rarr; *Settings* &rarr; *Shipping Methods*.

The following instructions cover the creation of new shipping methods.
Business logic to integrate with any particular shipping company (UPS, DHL,
etc.) is outside the scope of this tutorial.

Follow these steps:

1.  Create a new module and include dependencies on
    `com.liferay.commerce.product.api` and `com.liferay.commerce.api` in its `build.gradle`
    file.

2.  Create a component implementing the
    `com.liferay.commerce.model.CommerceShippingEngine` interface.

First, add dependencies to `build.gradle`. The build script should look like
this<!--in DXP GA1, Commerce 1.0.0-->

    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"

    dependencies {
        compileOnly group: "com.liferay.commerce", name: "com.liferay.commerce.product.api", version: "1.1.0"
        compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "3.5.0"
        compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
        compileOnly group: "com.liferay.commerce", name: "com.liferay.commerce.api", version: "1.1.0"
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

The KEY constant in this example provides a unique identifier for this shipping method.

The `getCommerceShippingOptionLabel` method returns a localized value for the
shipping method's name, and is called when during the checkout process when a
buyer is prompted to select a shipping option.

    @Override
    public String getCommerceShippingOptionLabel(String name, Locale locale) {
        ResourceBundle resourceBundle = _getResourceBundle(locale);

        return ResourceBundleUtil.getString(resourceBundle, name);
    }

The main business logic of the shipping method is handled by the
`getCommerceShippingOptions` method:


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
