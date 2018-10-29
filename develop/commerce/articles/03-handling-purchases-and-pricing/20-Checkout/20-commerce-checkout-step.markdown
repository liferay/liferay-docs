# Custom Checkout Step

@commerce@'s checkout process involves several steps. Out of the box, the first
few steps are used to collect information (billing address, shipping address,
etc.) from the buyer, while the final steps are used to provide information
(order summary, order confirmation) to the buyer. This tutorial explains how to
add custom steps to the checkout process.

Adding new a new checkout step involves implementing the `CommerceCheckoutStep`
interface. The implementation will render a JSP file at a selected point in
checkout process. Writing the JSP and any supporting code to provide the step's
business logic is not covered in this tutorial.

Follow these steps:

1.  Create a new module and include dependencies on
    `com.liferay.commerce.product.api` and `com.liferay.commerce.checkout.web`.

2.  Specify a `Web-ContextPath` in your `bnd.bnd` file.

3.  Create a new component implementing `CommerceCheckoutStep`.

4.  Provide a JSP for your component to render.

First, add dependencies to `build.gradle`. The build script should look like
this:

    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"

    dependencies {
        compileOnly group: "com.liferay.commerce", name: "com.liferay.commerce.product.api", version: "2.0.0-SNAPSHOT"
        compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "3.5.0"
        compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
        compileOnly group: "com.liferay.commerce", name: "com.liferay.commerce.checkout.web", version: "1.0.2"
        compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
        compileOnly group: "com.liferay", name: "com.liferay.frontend.taglib", version: "3.0.3"
        compileOnly group: "javax.portlet", name: "portlet-api", version: "3.0.0"
    }

Next, specify an appropriate `Web-ContextPath` in the module's `bnd.bnd` file.
For this example, the file looks like this:

    Bundle-Name: liferay-commerce-sample
    Bundle-SymbolicName: com.liferay.commerce.sample
    Bundle-Version: 1.0.0
    Web-ContextPath: /commerce-sample

Then create your component:

    @Component(
        immediate = true,
        property = {
            "commerce.checkout.step.name=" + DemoCommerceCheckoutStep.NAME,
            "commerce.checkout.step.order:Integer=" + (Integer.MAX_VALUE - 101)
        },
        service = CommerceCheckoutStep.class
    )
    public class DemoCommerceCheckoutStep implements CommerceCheckoutStep {

        public static final String NAME = "demo";

        @Override
        public String getName() {
            return NAME;
        }

The `getName` method returns the `NAME` constant, which stores the component's
unique identifier. The constant's value is also used to label the step in the
UI, and can be localized when tied to an entry in a `Language.properties` file.

The step's position in the checkout process is determined by the
`commerce.checkout.step.order:Integer` property.

![Figure 1: New checkout steps can be interspersed between default steps.](../../../images/checkoutsteps.png)

Out of the box, the `commerce.checkout.step.order:Integer` properties for the
first four steps---*Billing Address*, *Shipping Address*, *Shipping Method*, and
*Payment Method*---are 10, 20, 30, and 40, respectively. *Order Summary* and
*Order Confirmation*, which are intended to be the final two steps, have
`order:Integer` properties of `Integer.MAX_VALUE - 100` and
`Integer.MAX_VALUE`,respectively. In the example above, the value of
`Integer.MAX_VALUE
- 101` will put the demo step in fifth position, right before the Order Summary.

![Figure 2: An `order:Integer` value of 41 would have the same effect.](../../../images/newcheckoutstep.png)

Next, include the `processAction` and `render` methods:

    @Override
    public void processAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws Exception {
    }

    @Override
    public void render(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse)
        throws Exception {

        _jspRenderer.renderJSP(
            _servletContext, httpServletRequest, httpServletResponse,
            "/checkout_step/view.jsp");
    }

The `processAction` method is called whenever the buyer clicks the *Continue*
button, and processes new input in checkout fields.

![Figure 3: The `processAction` method will save Liferay's address when the user clicks *Continue*.](../../../images/continuecheckoutstep.png)

Meanwhile the `render` method renders `view.jsp`, which is located in the
modules' `src/main/resourcer/checkout_step` folder.

The `CommerceCheckoutStep` interface requires a few more methods:

    @Override
    public String getLabel(Locale locale) {
      ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
         "content.Language", locale, getClass());

      return LanguageUtil.get(resourceBundle, getName());
    }

    @Override
    public boolean isActive(
         HttpServletRequest httpServletRequest,
         HttpServletResponse httpServletResponse)
      throws Exception {

      return true;
    }

    @Override
    public boolean isOrder() {
      return false;
    }

    @Override
    public boolean isSennaDisabled() {
      return false;
    }

The `getLabel` method retrieves an appropriate language bundle and then passes
in the `getName` method to find a localized version of the step's label.
Meanwhile the `isActive` method indicates that the checkout step is active in
the current context.

Include the `isVisible` and `showControls` methods:

    @Override
    public boolean isVisible(
         HttpServletRequest httpServletRequest,
         HttpServletResponse httpServletResponse)
      throws Exception {

      return isActive(httpServletRequest, httpServletResponse);
    }

    @Override
    public boolean showControls(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse) {

      return true;
    }

The `isVisible` method indicates that the step should be listed in the checkout
widget's step summary (seen in Figure 2 above), while `showControls` indicates that
the custom step should display the *Continue* and *Previous* buttons.

Finally, include these reference annotations:

        @Reference
        private JSPRenderer _jspRenderer;

        @Reference(target = "(osgi.web.symbolicname=com.liferay.commerce.sample)")
        private ServletContext _servletContext;

    }

Note that the second reference annotation's `osgi.web.symbolicname`---in this case
`com.liferay.commerce.sample`---must match the `Bundle-SymbolicName` in your
module's `bnd.bnd`.

Your component is now ready to render your new checkout step. Completing the
task is a matter of writing your JSP and whatever back-end code is required
to support it.
