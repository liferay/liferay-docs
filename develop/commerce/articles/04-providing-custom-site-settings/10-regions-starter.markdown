# Adding Regions to @commerce@'s Country List

@commerce@ stores a country list that is used wherever a user is prompted to
select a country (typically to enter an address). @commerce@ also stores lists of
regions (states or provinces) for each country, but out of the box, only the
region lists for Italy and the USA are populated. Administrators can populate
these lists manually, (see
[Countries](web/emporio/documentation/-/knowledge_base/1-0/countries))
but a developer can populate them automatically by implementing the
`CommerceRegionsStarter` interface.

Follow these steps:

1.  Create a new module and add dependencies on `commerce-api` and
   `commerce-product-api` to its `build.gradle` file.

2.  Implement the `com.liferay.commerce.starter.CommerceRegionsStarter` interface.

3.  Implement your logic in the interface's `start` method.

First, add dependencies to `build.gradle`. It should look like this:

    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"

    dependencies {
        compileOnly group: "com.liferay.commerce", name: "com.liferay.commerce.product.api", version: "2.0.0-SNAPSHOT"
        compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "3.5.0"
        compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
        compileOnly group: "com.liferay.commerce", name: "com.liferay.commerce.api", version: "2.0.0"
    }

Then create a component to implement the interface. In
the example below, the class `CanadaCommerceRegionsStarter` contains the the
constant `CANADA_NUMERIC_ISO_CODE`. This constant must be set to the country's
3-digit ISO code and provides the value for the `commerce.region.starter.key`
property. This connects the region starter with the correct country in
@commerce@'s country list.


    package com.liferay.commerce.sample;

    import com.liferay.commerce.model.CommerceCountry;
    import com.liferay.commerce.service.CommerceCountryLocalService;
    import com.liferay.commerce.service.CommerceRegionLocalService;
    import com.liferay.commerce.starter.CommerceRegionsStarter;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.service.ServiceContext;

    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;

    @Component(
        immediate = true,
        property = "commerce.region.starter.key=" + CanadaCommerceRegionsStarter.CANADA_NUMERIC_ISO_CODE,
        service = CommerceRegionsStarter.class
    )
    public class CanadaCommerceRegionsStarter implements CommerceRegionsStarter {

        public static final int CANADA_NUMERIC_ISO_CODE = 124;

        public CommerceCountry getCommerceCountry(long groupId)
            throws PortalException {

            return _commerceCountryLocalService.fetchCommerceCountry(
                groupId, CANADA_NUMERIC_ISO_CODE);
        }

Finally, you can add your own logic to the interface's `start` method. The
example below adds four regions to Canada's region list. Besides the name of each
region, the method includes a postal code for each region, and a number
determining the order in which it will be listed.

    @Override
        public void start(ServiceContext serviceContext) throws Exception {
            CommerceCountry commerceCountry = getCommerceCountry(
                serviceContext.getScopeGroupId());

            if (commerceCountry == null) {
                return;
            }

            _commerceRegionLocalService.addCommerceRegion(
                    commerceCountry.getCommerceCountryId(),"Ontario" , "ON", 1,
                    true, serviceContext);
            _commerceRegionLocalService.addCommerceRegion(
                    commerceCountry.getCommerceCountryId(),"Manitoba" , "MB", 2,
                    true, serviceContext);
            _commerceRegionLocalService.addCommerceRegion(
                    commerceCountry.getCommerceCountryId(),"British Colombia" , "BC", 3,
                    true, serviceContext);
            _commerceRegionLocalService.addCommerceRegion(
                    commerceCountry.getCommerceCountryId(),"Yukon" , "YT", 4,
                    true, serviceContext);
        }

        @Reference
        private CommerceCountryLocalService _commerceCountryLocalService;

        @Reference
        private CommerceRegionLocalService _commerceRegionLocalService;
    }

Of course, listing each region in the method is only an example and far from an
ideal solution---admins can accomplish the same thing manually without tying up
a developer's time. For a production use case, you're likely to want your
`start` method to retrieve regions from a database, json web service, or some
other resource.
