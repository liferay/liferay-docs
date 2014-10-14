# OpenSocial gadget basics [](id=opensocial-gadget-basics)

An OpenSocial Gadget is specified in an XML document that has two parts. The
first part of the document specifies *meta-data* declaring gadget dependencies,
defining characteristics about the gadget, and specifying user preferences. The
meta-data can be broken up into *module preferences* and *user preferences*.
The second part of the document contains the gadget *content*, defining the
gadget's user interface and business logic. We'll spend time going over all
these facets of gadget XML.

![Figure 13.1: An OpenSocial gadget's XML consists of elements specifying gadget preferences, user preferences, and content.](../../images/opensocial-22.png)

Below is an example OpenSocial gadget XML file for a map gadget:

	<?xml version="1.0" encoding="UTF-8" ?>
	<Module>
		<ModulePrefs title="Location Map" height="300"
		    author="Cody Hoag" 
			author_email="cody.hoag@liferay.com" /> 
		<UserPref name="lat" display_name="Latitude" required="true" /> 
		<UserPref name="lng" display_name="Longitude" required="true" /> 
		<Content type="html">
		<![CDATA[ 
			<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false" type="text/javascript">
			</script>
			<div id="map" style="width: 100%; height: 100%;"></div>
			<script type="text/javascript">
			
			var includeMarker = false;
			var latlng;
			var userPrefs = new gadgets.Prefs();
            var latLocation = userPrefs.getFloat('lat');
            var lngLocation = userPrefs.getFloat('lng');
			
			if (latLocation == "" && lngLocation == "") {
			    latlng = new google.maps.LatLng(33.997547,-117.814305);
			}
			else {
			    latlng = new google.maps.LatLng(latLocation,lngLocation);
			    includeMarker=true;
			}
			
			function initialize() {
                var mapOptions = {
                    center: latlng,
                    mapTypeId: google.maps.MapTypeId.ROADMAP,
                    zoom: 7
                }
                map = new google.maps.Map(document.getElementById('map'), mapOptions);
                
                if (includeMarker == true) {
                    var marker = new google.maps.Marker({
                        map: map,
                        position: latlng
                    });
                }
            }

            google.maps.event.addDomListener(window, 'load', initialize);

            </script>
        ]]> 
		</Content>
	</Module>

For the official documentation on Gadget anatomy, see Google's [Anatomy of a
Gadget](https://developers.google.com/gadgets/docs/basic#Anatomy). For
convenience, however, let's consider gadget *meta-data* and *content* here to
highlight their important aspects.

## Gadget meta-data [](id=gadget-meta-data)

Gadget meta-data specifies characteristics of the overall gadget and specifies
controls for the user to customize the gadget. Here is a simple breakdown of
these two types of meta-data:

**Gadget Preferences (required):** Define characteristics of the gadget. They are
specified as attributes of and nested elements in the gadget's `<ModulePrefs/>`
element.

**User Preferences (optional):** Store user configuration values and allow users
to give input to the application. They are specified in `<UserPref/>` elements.

Now, let's dive deeper into gadget preferences.

### Gadget Preferences (ModulePrefs) [](id=gadget-preferences-moduleprefs)

The gadget preferences (ModulePrefs) define characteristics of your gadget,
including features that it uses, how it authenticates content, how it is
displayed to the user, and how it is displayed in a gadget directory. The
preferences are wrapped in `<ModulePrefs>...</ModulePrefs>` tags.

Within the ModulePrefs, you specify the gadget's dependencies with `<Require/>`
tags and optional features with `<Optional/>` tags. If you specify a feature in
a `<Require/>` tag, the gadget only renders itself if the feature is available.
If you specify a feature in an `<Optional/>` tag, the gadget renders despite
feature availability.

ModulePrefs can also be used to configure important features such as PubSub and
OAuth. The PubSub feature allows for communication between gadgets on the same
page: they can publish and subscribe on message channels. Liferay also enables
gadgets to communicate with portlets using the same mechanism. The OAuth
feature provides secure connections between your portal and third-party sites.
These are just a couple of useful features that can be defined in the
ModulePrefs. You'll learn more about them later in this chapter.

Overall, this `<ModulePrefs>` element allows you to register your gadget on a
gadget directory and establish settings and tools to use in your gadget's
business logic. For complete details on ModulePrefs, see Google's [Moduleprefs
Elements and
Attributes](https://developers.google.com/gadgets/docs/xml_reference#Moduleprefs_Ref)
reference.

Next, let's consider gadget user preferences.

### User Preferences (UserPrefs) [](id=user-preferences-userprefs)

When a gadget is configured to fit a specific page, it can really enhance the
user's experience. OpenSocial gadgets are capable of storing user preferences,
allowing gadgets to present information and options tailored for individual
pages. The title *UserPrefs* can also be referred to as application
preferences, since the preferences being changed only apply to the single
gadget and not to each user that uses it. A gadget's user preferences are
specified in `<UserPref>...</UserPref>` tags in the gadget's XML. The owner of
a page can adjust permissions to allow certain users to change the user
preferences. However, every time the UserPrefs are modified, every user that
views the gadget has a modified gadget. As a user enters information and
adjusts controls via a gadget's user interface, those changes are persisted in
the database for that gadget instance. Each time the gadget is reloaded, the
UserPrefs are extracted from the database, fed back to the gadget, and rendered
in the user interface.

Map gadgets, for example, are prime candidates for UserPrefs. When users first
bring up map gadgets, they are typically interested in their current location or
a location of interest--not some random distant land. Therefore, it makes sense
for a map gadget to take the user's location of interest as input. UserPrefs
facilitate taking in this information, storing it, and processing it to present
gadget user interfaces customized to the user. Here are the UserPrefs for our
current map gadget:
 
	<UserPref name="lat" display_name="Latitude" required="true" />
	<UserPref name="lng" display_name="Longitude" required="true" />

Notice the `lat` and `lng` UserPrefs of the map gadget. These user preferences
take in the user's latitude and longitude preferences, stores them, and displays
them on the mapping interface.

Lastly, we'll look at what a user sees in the gadget's user interface when
setting the *Latitude* and *Longitude* user preferences. 

<!--
Here is a snapshot of
what this window looks like on Liferay Portal:

!incorrect[Figure 13.2: Here, the map gadget's user preference is made available for user input.](../../images/opensocial-21.png)
-->

<!-- Need to update snapshot above with updated Map gadget. I'm unable to do it
at the present time because the Gadget Editor doesn't currently work for 6.1.
-->

UserPrefs can be displayed in many different ways and help your gadget become
more adaptable to your users' ever changing expectations. And, there is an
extensive variety of UserPref data types available to properly personalize your
gadgets. For complete details on UserPrefs see Google's [User
preferences](https://developers.google.com/gadgets/docs/xml_reference#Userprefs_Ref)
reference.

## Gadget Content [](id=gadget-content)

Within the `<Content>...</Content>` tags of your gadget, you define your
gadget's user interface and business logic. The content can be implemented in
HTML or linked via a URL. HTML allows you to use JavaScript and gives you
plenty of flexibility. But if you simply want to leverage content found at a
particular URL, use the URL content type.

To help you decide which content type is best for you, see Google's [Choosing a
Content
Type](https://developers.google.com/gadgets/docs/fundamentals#Content_Type)
documentation. But for convenience, let's consider basic aspects of these
content types here.

### HTML content type [](id=html-content-type)

HTML is the default content type for OpenSocial gadgets. Because HTML is so
flexible and commonly used for gadgets, you may find yourself using it often.

Note that all HTML content must be specified in a `CDATA` section within your
gadget's content. The following simple content example demonstrates proper
`CDATA` specification:

	<Content type="html">
		<![CDATA[ "Your HTML goes here" ]]>
	</Content>

Also, it is forbidden to use `<body>`, `<head>`, or `<html>` tags in your HTML
content, as these tags conflict with the ones automatically generated by the
container for your gadget. 

### URL content type [](id=url-content-type)

This content type is convenient when you just want to reference content within
an existing URL, and nothing more. When using this content type, specify the URL
as your `href` attribute value. The gadget assumes all programming and user
interface logic resides in your specified URL. Therefore, when using the `url`
content type, you do not need any HTML or JavaScript. Note that the URL content
type has better consistency in the specification than the HTML content type
regarding proxied content.

Here is a sample of what the URL content type looks like for an example gadget:

	<Content type="url" href="http://www/cgi-bin/example/gadgets/mystats.cgi" />

Both HTML and URL content types offer beneficial traits and can be used
effectively. The content type you use for your gadgets depends on your needs and
preferences.

Now that you're fundamentally sound on the gadget basics, let's explore what
gadgets offer you in Liferay Portal.
