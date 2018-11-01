# Creating New Tax Methods [](id=creating-new-tax-methods)

Out of the box, @commerce@ offers two methods for calculating
[taxes](/web/emporio/documentation/-/knowledge_base/1-0/taxes).
Since these options may not meet every user's needs, @commerce@ exposes an
extension point, `CommerceTaxEngine`, to make it easy to use a custom tax
solution.

This tutorial describes how to implement a custom tax calculation that
administrators can activate from *Commerce* &rarr; *Settings* &rarr; *Taxes*.
For the sake of simplicity, the example below imposes a flat 10% tax rate on
every product. Of course, your real-world solution should be more
sophisticated---@commerce@ doesn't require customization to charge a flat tax.

Follow these steps:

1.  Create a new module and add dependencies on `commerce.api` and
    `commerce.product.api`.

2.  Create a new component implementing the
    `com.liferay.commerce.tax.CommerceTaxEngine` interface.

First, add dependencies to `build.gradle`. The build script should look like
this:

    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"

    dependencies {
        compileOnly group: "com.liferay.commerce", name: "com.liferay.commerce.product.api", version: "2.0.0"
        compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "3.5.0"
        compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
        compileOnly group: "com.liferay.commerce", name: "com.liferay.commerce.api", version: "2.0.0"
        compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
    }

Next, create your component class to implement the interface.


    @Component(immediate = true, property = "commerce.tax.engine.key=sample-tax", service = CommerceTaxEngine.class)
    public class TaxationMethod implements CommerceTaxEngine {

        public static final String KEY = "sample-tax";

The class's `KEY` constant identifies the tax calculation and---when mapped to your
module's `language.properties` file---provides its label in the UI. (See
[Localizing Your Application](/develop/tutorials/-/knowledge_base/7-1/localizing-your-application)
for more information on language properties.)

The business logic of your tax method is contained in the `getCommerceTaxValue`
method:

    @Override public CommerceTaxValue
    getCommerceTaxValue(CommerceTaxCalculateRequest commerceTaxCalculateRequest)
    throws CommerceTaxEngineException {

		CommerceTaxValue commerceTaxValue = null;

		BigDecimal rate = BigDecimal.TEN;

		BigDecimal amount = commerceTaxCalculateRequest.getPrice();

		BigDecimal taxValue = rate;

		if (commerceTaxCalculateRequest.isPercentage()) {
			taxValue = amount.multiply(rate);

			taxValue = taxValue.divide(_ONE_HUNDRED);
		}

		commerceTaxValue = new CommerceTaxValue(KEY, KEY, taxValue);

		return commerceTaxValue;
	}

`GetCommerceTaxValue` takes a `CommerceTaxCalculateRequest` object as
a parameter. The object provides the following variables which can be used to
calculate tax:

`_commerceAddressId` stores an identifier for the buyer's billing address.

`_price` indicates the purchase price of the product to be taxed.

`_percentage` indicates whether the tax should be calculated as a percentage of
the purchase price.

`_taxCategoryId` stores an identifier for the product's tax category.

`_userId` stores the buyer's user ID.

The above example calls `CommerceTaxCalculateRequest`'s `getPrice` method to
retrieve the value of the `_price` variable, calculates 10%, and assigns the
result to the `taxValue` variable. It then returns a `CommerceTaxValue` object,
passing in `taxValue` as one of its parameters. This particular method requires
the constant `_ONE_HUNDRED`, which is declared outside the method:

        private static final BigDecimal _ONE_HUNDRED = BigDecimal.valueOf(100);
    }

The `CommerceTaxEngine` interface requires two additional methods.
`getDescription` retrieves a language key containing a description of your
method that administrators will see in the UI:

	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = _getResourceBundle(locale);

		return LanguageUtil.get(resourceBundle, "sample-tax-description");
	}

Meanwhile the `getName` method retrieves the language key assigned to your `KEY`
constant. This language key should provide the name of your tax method---again,
for administrators' benefit:

	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = _getResourceBundle(locale);

        return LanguageUtil.get(resourceBundle, KEY);

![The *Name* and *Description* fields are set by the `getName` and `getDescription` methods.](../../images/taxengine-name-description.png)

The `_getResourceBundle` method is not required by the `CommerceTaxEngine`
interface, but supports `getName` and `getDescription` by providing the language
bundle appropriate to the user's location:

	private ResourceBundle _getResourceBundle(Locale locale) {
		return ResourceBundleUtil.getBundle("content.Language", locale, getClass());
	}

Once you've written your `getCommerceTaxValue` method and put your language keys
in place, your new tax method is ready to go.
