# Service Builder Application Demonstrating Actionable Dynamic Query

## What does this sample do when deployed?

The Service Builder ADQ portlet functions similarly to the basic service builder portlet. It allows the user to perform CRUD (create, read, update, delete) operations on service builder entities. The distinctive feature of the Service Builder ADQ portlet is that it also allows the user to perform a mass update on all the existing service builder entities.

## What API(s) and / or code components does this sample highlight?

This sample demonstrates the use of Liferay's actionable dynamic query API. Specifically, this sample demonstrates how to create an ADQ, add criteria to an ADQ, specify an action for the ADQ, and execute the ADQ.

## How does this sample leverage the API(s) and / or code component?

To see the ADQ portlet in action, the user should first add it to a page and add several entities. Then the user should click on the *Mass Update* update and invoke the update. When the user clicks the *Submit* button to invoke the update, an action request is sent to the `JSPPortlet` with a `cmd` request parameter. When the `JSPPortlet`'s `processAction` method processes the request, the value of the `cmd` parameter is parsed and then portlet's `massUpdate` method is invoked. The `massUpdate` method, in turn, invokes the `massUpdate` method defined in `BarLocalServiceImpl`. This is where the sample leverages the actionable dynamic query API:

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

This sample demonstrates how to create an ADQ, specify criteria for an ADQ, specify an action for an ADQ, and how to execute an ADQ.
