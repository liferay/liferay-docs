Liferay.Service.register("Liferay.Service.Event", "com.nosester.portlet.eventlisting.service", "event-listing-portlet");

Liferay.Service.registerClass(
	Liferay.Service.Event, "Event",
	{
		addEvent: true,
		delete: true,
		deleteEvent: true,
		update: true,
		updateEvent: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Event, "Location",
	{
		addLocation: true,
		delete: true,
		deleteLocation: true,
		update: true,
		updateLocation: true
	}
);