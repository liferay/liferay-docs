# Service Builder Application Demonstrating Actionable Dynamic Query [](id=service-builder-application-demonstrating-actionable-dynamic-query)

## What does this sample do when deployed? [](id=what-does-this-sample-do-when-deployed)

This sample is similar to the
[`basic` Service Builder sample](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/apps/service-builder/basic),
which lets you perform CRUD (create, read, update, delete) operations on service
builder entities. The distinctive feature of the Service Builder Actionable
Dynamic Query (ADQ) sample is that it also lets you perform a mass update on all
existing service builder entities.

![Figure 1: This sample provides options to add entities and perform a mass update.](../../../../images/adq-sample.png)

To see the ADQ Service Builder sample in action, complete the following steps:

1.  Add the sample to a page by navigating to *Add*
    (![Add](../../../../images/icon-control-menu-add.png)) &rarr; *Widgets* &rarr;
    *Sample* and dragging it to the page.

2.  Select the app's *Add* button and add an entity. Do this several times to
    create multiple entities.

3.  Click the *Mass Update* button and click *Save* to invoke the update.

    ![Figure 2: Clicking the *Save* button executes the mass update.](../../../../images/adq-sample-mass-update.png)

    After invoking the update, each entity's `field3` value (whose value is less
    than 100) is incremented.

You've leveraged the actionable dynamic query API in your sample!

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample demonstrates @product@'s actionable dynamic query API. Specifically,
it demonstrates how to create an ADQ, add criteria to an ADQ, specify an action
for the ADQ, and execute the ADQ.

## How does this sample leverage the API(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

An action request is sent to the `JSPPortlet` with a `cmd` request parameter.
When the `JSPPortlet`'s `processAction` method processes the request, the value
of the `cmd` parameter is parsed and then the portlet's `massUpdate` method is
invoked. The `massUpdate` method, in turn, invokes the `massUpdate` method
defined in the `adq-service` module's `BarLocalServiceImpl`. This is where the
sample leverages the actionable dynamic query API:

    public void massUpdate() {
        ActionableDynamicQuery adq = getActionableDynamicQuery();

        adq.setAddCriteriaMethod(
            new ActionableDynamicQuery.AddCriteriaMethod() {

                @Override
                public void addCriteria(DynamicQuery dynamicQuery) {
                    dynamicQuery.add(RestrictionsFactoryUtil.lt("field3", 100));
                }

            });

        adq.setPerformActionMethod(
            new ActionableDynamicQuery.PerformActionMethod<Bar>() {

                @Override
                public void performAction(Bar bar) {
                    int field3 = bar.getField3();

                    field3++;
                    bar.setField3(field3);

                    updateBar(bar);
                }

            });

        try {
            adq.performActions();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

For more information on the actionable dynamic query API, visit its dedicated
[tutorial](/develop/tutorials/-/knowledge_base/7-0/dynamic-query#actionable-dynamic-queries).