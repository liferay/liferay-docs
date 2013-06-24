Liferay.Service.register("Liferay.Service.Event", "com.nosester.portlet.eventlisting.service", "event-listing-portlet");

Liferay.Service.registerClass(
	Liferay.Service.Event, "Event",
	{
		addEvent: true,
		update: true,
		delete: true,
		deleteEvent: true
	}
);