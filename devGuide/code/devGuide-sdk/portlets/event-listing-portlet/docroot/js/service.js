Liferay.Service.register("Liferay.Service.Event", "com.nosester.portlet.eventlisting.service", "event-listing-portlet");

Liferay.Service.registerClass(
	Liferay.Service.Event, "Event",
	{
		addEvent: true,
		update: true,
		updateEvent: true,
		delete: true,
		deleteEvent: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Event, "Location",
	{
		addLocation: true,
		update: true,
		updateLocation: true,
		delete: true,
		deleteLocation: true
	}
);