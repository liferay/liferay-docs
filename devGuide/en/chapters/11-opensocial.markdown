
# Creating and Integrating with OpenSocial Gadgets

OpenSocial is a public specification for creating web applications using
standard technologies like HTML, CSS, and Javascript. It was originally
developed by Google, Myspace and others to standardize common social networking
API's but has evolved into a general platform for building web applications.
Whereas "standard" applications work with data on a per-user basis, "social"
applications share data within well defined networks, facilitating communication
of information between groups of users. OpenSocial applications, called
*gadgets*, are similar to portlets because they can be added to your portal's
pages and used for all kinds of tasks. Gadgets are characterized as being
simple, widely available, and easy to deploy.

In this chapter, we'll cover the following topics:

- OpenSocial gadget basics.

- Using PubSub to allow gadgets to communicate with each other and with
  portlets.

- Liferay's gadget editing environment.

Let's first look at the structure of an OpenSocial gadget and consider the
concepts involved with OpenSocial gadgets.

## OpenSocial gadget basics

An OpenSocial Gadget is specified in an XML document. The first part of the
document specifies *meta-data* declaring gadget dependencies, defining
characteristics about the gadget, and specifying user preferences. The meta-data
can be broken up into *module preferences* and *user preferences*. The second
part of the document contains the gadget *content*, defining the gadget's user
interface and business logic. We'll spend time going over all these facets of
gadget XML.

![Figure 11.1: An OpenSocial gadget's XML consists of elements specifying gadget
preferences, user preferences, and content.](../../images/opensocial-22.png)

Below is a sample of OpenSocial gadget XML from a weather gadget authored by
LabPixies:

	<Module>
		<ModulePrefs
		height="230"
		author="LabPixies"
		author_email="labpixie.gadgets+weather+201101101@gmail.com"
		description="__MSG_description__"
		thumbnail="http://www.labpixies.com/campaigns/weather/images/thumbnail.jpg"
		screenshot="http://www.labpixies.com/campaigns/weather/images/screenshot.png"
		title="__MSG_gTitle__" 
		title_url="http://www.labpixies.com">
		<Locale messages="http://www.labpixies.com/campaigns/weather/i20/all_all.xml"/>
		<Locale lang="en" country="UK" messages="http://www.labpixies.com/campaigns/weather/i20/en_gb_all.xml"/>
		<Locale lang="en" country="AU" messages="http://www.labpixies.com/campaigns/weather/i20/en_au_all.xml"/>
		...
			<Require feature="dynamic-height"/>
			<Require feature="setprefs"/>
			<Require feature="views"/>
		</ModulePrefs>
		<UserPref name="degree_unit_type" datatype="hidden" default_value="0"/>
		<UserPref name="first_load" datatype="hidden" default_value="1"/>
		<UserPref name="locations" datatype="hidden" default_value="__MSG_dflt_location__"/>
		<UserPref name="zip_code" datatype="hidden" default_value="0"/>
		<UserPref name="city_code" datatype="hidden" default_value="0"/>
		<UserPref name="disable_os" datatype="hidden" default_value="0"/>
		<UserPref name="tabs" datatype="hidden" default_value="{"news":1,"l0":1}"/>
		<Content type="html" view="home">
			...
		</Content>
	</Module>

To view the weather gadget in its entirety, visit
[http://www.labpixies.com/campaigns/weather/weather.xml](http://www.labpixies.com/campaigns/weather/weather.xml).

For the official documentation on Gadget anatomy, see Google's [Anatomy of a
Gadget](https://developers.google.com/gadgets/docs/basic#Anatomy). But, for
convenience, let's consider gadget *meta-data* and *content* here to highlight
their important aspects.

### Gadget meta-data

Gadget meta-data specifies characteristics of the overall gadget and specifies
controls for the user to customize the gadget. Here is a simple breakdown of
these two types of meta-data:

*Gadget Preferences (required)*: Define characteristics of the gadget. They are
specified as attributes of and nested elements in the gadget's `<ModulePrefs/>`
element.

*User Preferences (optional)*: Store user configuration values and allow users
to give input to the application. They are specified in `<UserPref/>` elements.

Now, let's dive deeper into gadget preferences.

#### Gadget Preferences (ModulePrefs)

The gadget preferences (ModulePrefs) define characteristics of your gadget,
including features that it uses, how it authenticates content, how it is
displayed to the user, and how it is displayed in a gadget directory. The
preferences are wrapped in `<ModulePrefs>...</ModulePrefs>` tags.

Within the ModulePrefs, you specify the gadget's dependencies with `<Require/>`
tags and optional features with `<Optional/>` tags. If you specify a feature in
a `<Require/>` tag, the gadget itself only renders if the feature is available.
If you specify a feature in an `<Optional/>` tag, the gadget renders despite
feature availability.

ModulePrefs can also be used to configure important features such as PubSub and
OAuth. The PubSub feature allows for communication between gadgets on the same
page, by allowing them to publish and subscribe on message channels. Liferay
also enables gadgets to communicate with portlets using the same mechanism. The
OAuth feature provides secure connections between your portal and third-party
sites. These are just a couple of useful features that can be defined in the
ModulePrefs. You'll learn more about them later in this chapter.

---

 ![](../../images/tip-pen-paper.png)**Note:** To enable use of `<UserPref>`
elements in your gadget XML, the `setprefs` feature must be included, by
specifying `<Require feature="setprefs"/>` within your
`<ModulePrefs>...<ModulePrefs/>` tags.

---

Overall, this `<ModulePrefs>` element allows you to setup your gadget on a
gadget directory and establish settings and tools to use in your gadget's
business logic. For complete details on ModulePrefs, see Google's [Moduleprefs
Elements and
Attributes](https://developers.google.com/gadgets/docs/xml_reference#Moduleprefs_Ref)
reference.

Next, let's consider gadget user preferences.

#### User Preferences (UserPrefs)

What can really enhance a user's experience is when a gadget is configured to
fit a specific page. OpenSocial gadgets are capable of storing user preferences,
allowing gadgets to present information and options tailored for individual
pages. The title *UserPrefs* can also be referred to as application preferences,
due to the fact that the preferences being changed only apply to the single
gadget and not to each user that utilizes it. A gadget's user preferences are
specified in `<UserPref>...</UserPref>` tags in the gadget's XML. The owner of a
page can adjust permissions to allow certain users to change the user
preferences. However, every time the UserPrefs are modified, every user that
views the gadget will also have a modified gadget. As a user enters information
and adjusts controls via a gadget's user interface, those changes are persisted
in the database for that gadget instance. Each time the gadget is reloaded, the
UserPrefs are extracted from the database, fed back to the gadget, and rendered
in the user interface.

Weather gadgets, for example, are prime candidates for UserPrefs. When users
first bring up weather gadgets, they are typically interested in the weather at
their current location or their residence -- not the weather of some random
distant land. Therefore, it makes sense for a weather gadget to take the user's
location or residence as input. It is also beneficial to use a temperature unit
familiar to the user. Lastly, users often like to hand-select a stylish theme
for their gadget. UserPrefs facilitate taking in this information, storing it,
and processing it to present gadget user interfaces customized to the user.

In order to set preference values from within your gadget, you must include the
`setprefs` feature inside the `<ModulePrefs>...</ModulePrefs>` tags, which we
discussed in the *Gadget Preferences (ModulePrefs)* section. Look at the weather
gadget's XML to see the `setprefs` feature included as a required feature:

	<Module>
	...
		<ModulePrefs height="230" author="LabPixies" ... >
		...
			<Require feature="setprefs"/>
		</ModulePrefs>
		<UserPref name="degree_unit_type" datatype="hidden" default_value="0"/>
		<UserPref name="first_load" datatype="hidden" default_value="1"/>
		<UserPref name="locations" datatype="hidden" default_value="__MSG_dflt_location__"/>
		<UserPref name="zip_code" datatype="hidden" default_value="0"/>
		<UserPref name="city_code" datatype="hidden" default_value="0"/>
		<UserPref name="disable_os" datatype="hidden" default_value="0"/>
		<UserPref name="tabs" datatype="hidden" default_value="{"news":1,"l0":1}"/>
		<Content type="html" view="home">
			...
		</Content>
	</Module>

Also, notice the following user preferences of this weather gadget:

- `degree_unit_type` is used to store the user's preference for degree unit
  type.

- `zip_code` and `city_code` are stored when user specifies their zipcode or
  city.

- `locations` displays locations that most closely relate to your zipcode or
  city specification.

Lastly, let's take a look at the UserPrefs for the Google Reader gadget and
compare it to what a user would see in the gadget's user interface. The Google
Reader gadget helps you find and keep track of interesting stuff on the web.

	<UserPref name="displayStreamId" default_value="user/-/state/com.google/reading-list" datatype="hidden"/>
	<UserPref name="itemCount" display_name="Item count: " datatype="enum" default_value="5">
		<EnumValue value="1"/>
		<EnumValue value="2"/>
		<EnumValue value="3"/>
		...
		<EnumValue value="10"/>
	</UserPref>
	<UserPref name="ranking" display_name="Sorting: " datatype="enum" default_value="d">
		<EnumValue value="d" display_value="newest first"/>
		<EnumValue value="o" display_value="oldest first"/>
	</UserPref>
	<UserPref name="readItemsVisible" display_name="Show read items: " datatype="bool" default_value="false"/>
	<UserPref name="linkTarget" display_name="Items open: " datatype="enum" default_value="bubble">
		<EnumValue value="bubble" display_value="as bubbles"/>
		<EnumValue value="new" display_value="in a new window"/>
		<EnumValue value="same" display_value="in the same window"/>
	</UserPref>

Here is a snapshot of what this window looks like on Liferay Portal:

![Figure 11.2: Here, the Google Reader gadget's user preferences are made
available for user input.](../../images/opensocial-21.png)

UserPrefs can be displayed in many different ways and help your gadget become
more adaptable to your users' ever changing expectations. And, there is an
extensive variety of UserPref data types available to properly personalize your
gadgets. For complete details on UserPrefs see Google's [User
preferences](https://developers.google.com/gadgets/docs/xml_reference#Userprefs_Ref)
reference.

### Gadget Content

Within the `<Content>...</Content>` tags of your gadget, you define your
gadget's user interface and business logic. The content can be implemented in
HTML or linked in as a URL. HTML allows you to use Javascript and gives you
plenty of flexibility. But, if you simply want to leverage content found at a
particular URL, use the URL content type.

To help you decide which content type is best for you, see Google's [Choosing a
Content
Type](https://developers.google.com/gadgets/docs/fundamentals#Content_Type)
documentation. But for convenience, let's consider basic aspects of these
content types here.

#### HTML content type

HTML is the default content type for OpenSocial gadgets. Because HTML is so
flexible and commonly used for gadgets, you may find yourself using it often.

Note, all HTML content must be specified in a `CDATA` section within your
gadget's content. The following simple content example demonstrates proper
`CDATA` specification:

	<Content type="html">
		<![CDATA[ "Your HTML goes here" ]]>
	</Content>

Also, it is forbidden to use `<body>`, `<head>`, or `<html>` tags in your HTML
content, as these tags conflict with the ones automatically generated by the
container for your gadget. 

#### URL content type 

This content type is convenient when you just want to reference content within
an existing URL, and nothing more. When using this content type, specify the URL
as your `href` attribute value. The gadget assumes all information regarding
programmatic logic and user interface resides in your specified URL. Therefore,
when using the `url` content type, you do not need any HTML or JavaScript. Note,
the URL content type has better consistency in the specification than the HTML
content type regarding proxied content.

Here is a sample of what the URL content type looks like inside a Sudoku gadget:

	<Content type="url" href="http://www.counttonine.com/displayGoogleAjaxGame.html"/>

Both HTML and URL content types offer beneficial traits and can be used
effectively. The content type you use for your gadgets depends on your needs and
preferences.

Now that you're fundamentally sound on the gadget basics, let's explore what
gadgets offer you in Liferay Portal.

## Accessing third-party applications from your gadget

Is there data on sites like Evernote, Facebook, Google, Netflix, Photobucket,
Twitter, or Yahoo you'd like to access in your OpenSocial gadgets? Perhaps you'd
like to provide a gadget for portal users to add movies to their Netflix queue
or for users to display their Photobucket pictures within gadgets in Liferay
Portal. You may be concerned users would be required to share their third-party
application credentials with portal in order to use the applications. Good news!
*OAuth* technology resolves the issue.

You can think of OAuth as a "handshake mechanism" where, instead of requiring
exchange of personal information, your site redirects portal users directly to
the service provider (e.g. Netflix, Photobucket, etc). Users simply approve the
gadget's access to their resources on the external web applications. It's just
that easy!

Read the [Gadget
personalization](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/opensocial-integration)
section of the *Social Networking* chapter in *Using Liferay Portal 6.1* for
instructions on configuring and using OAuth enabled gadgets. To learn how to
write OAuth enabled gadgets, see Google's [Writing OAuth
Gadgets](https://developers.google.com/gadgets/docs/oauth) reference.

Let's now shift our focus to gadget/portlet communication with PubSub.

## Gadget/Portlet communication with PubSub

Have you ever wanted your gadgets to communicate with each other or with
portlets? You can do so with *PubSub*. It is a messaging pattern in which
publishers send messages to topics and subscribers receive the messages on those
topics. Hence, the term PubSub is short for "Publish and Subscribe." PubSub is
implemented in Liferay to facilitate interaction between gadgets and interaction
between gadgets and portlets. PubSub is a very diverse messaging system that
allows messages to be sent in the following manner:

-	gadget to gadget
-	portlet to gadget or vice versa
-	portlet to portlet

With the use of PubSub, the worlds of gadgets and portlets blend together
facilitating their ability to communicate and interact with each other.

Publishers do not send messages to subscribers directly, but rather, they
publish messages that are characterized into classes and sent across message
channels (topics). Therefore, the publishers have no knowledge of *whom* they
are sending their messages to; they simply broadcast the messages over a
channel. So you may ask: "How do subscribers receive messages if the messages
are not sent directly to them?" Subscribers express interest by subscribing to
certain channels and receive messages sent to those channels. Like publishers,
they have no knowledge of *who* sends the messages, they only know channels and
receive messages coming in on those channels. This process makes it much easier
for multiple gadgets and portlets to communicate with each other, without
specifically stating who they want to send/receive data from.

PubSub can be compared to a TV station and your TV. The TV station does not
directly send their material to your TV but, rather, broadcasts the material
over a channel. You subscribe to that channel by tuning your TV to that channel
to watch what's on it. This process of broadcasting and tuning into TV programs
is similar to publishing and subscribing to messages sent via PubSub.

PubSub also offers the ability for portlets and gadgets to publish and subscribe
to multiple channels. Although this may seem like a complex process, PubSub's
use of message channels creates a network for gadgets to communicate across; and
the network is easy to maintain and understand.

Let's try out PubSub for ourselves to explore how PubSub works. In this
fundamental example, we'll import two simple gadgets and send messages from one
to the other. One acts as a publisher and the other as a subscriber. Follow the
steps below:

1. Navigate to the Control Panel and select *OpenSocial Gadget Publisher* from
   under the *Portal* heading.

2. Select *Publish Gadget* and, for each gadget, enter the URL and click *Save*

	*Sample PubSub Publisher URL*:
`http://svn.apache.org/repos/asf/shindig/trunk/content/container/sample-pubsub-2-publisher.xml`

	*Sample PubSub Subscriber URL*:
`http://svn.apache.org/repos/asf/shindig/trunk/content/container/sample-pubsub-2-subscriber.xml`
	
3. Go back to a page on your site, navigate to *Add* &rarr; *More...*, and add
   your new gadgets to the page

4. Click *Publish a random number* on the PubSub Publisher gadget. You will
   notice it publishes a number; but the PubSub Subscriber gadget does not
   receive the number.

![Figure 11.3: The subscriber cannot receive any messages from the publisher
without being subscribed to the channel.](../../images/opensocial-23.png)

5. Select *Subscribe* on the subscriber gadget.

6. Click *Publish a random number* again from the publisher gadget. You now see the random number received by the subscriber.

![Figure 11.4: When the subscriber is subscribed to the publisher's channel, the
subscriber is able to receive messages.](../../images/opensocial-24.png)

7. Select *Unsubscribe* on the subscriber gadget.

8. Click *Publish a random number*.

As you would expect, the subscriber portlet no longer receives the random
number.

This simple example illustrates what PubSub does. It opens up a message channel
through which publishers and subscribers can interact. Also, you probably
noticed, while publishing and subscribing with your gadgets, there was no need
to refresh the page. This is because the gadgets use *AJAX (Asynchronous
JavaScript and XML)*. This technology refreshes your applications automatically,
allowing PubSub to work efficiently and effectively throughout your Liferay
Portal.

I bet you are wondering how to implement PubSub messaging in your gadgets and
portlets. We'll dive into the code next. In fact, we'll complete exercises
demonstrating gadget to gadget interaction and portlet to gadget interaction.

### Gadget to Gadget

For gadget to gadget communication, two independent gadgets are placed on a page
and configured with PubSub. These two gadgets are able to communicate with one
another and provide tools that the user could not otherwise produce. We will
complete a simple example for gadget to gadget communication where two gadgets
work together to display an address on Google Maps. The first gadget represents
a publisher, which enables the user to input a specific address and publish the
address. The second gadget represents the subscriber, which receives the address
, displays the address, and locates the address on Google Maps. Follow the steps
below:

1. Publish the *Google Address* and *Google Map* gadgets, as we had done
   previously with other gadgets. The URLs are given below:

	*Google Address URL*:
`http://opensocialdeju.googlecode.com/svn-history/r31/GoogleMaps/GoogleMapsPublisher.xml`

	*Google Map URL*:
`http://opensocialdeju.googlecode.com/svn-history/r31/GoogleMaps/GoogleMapsViewer.xml`

<!-- TODO rehost these xml's on liferay -->

2. Add both gadgets to a page.

	Your gadgets should appear like the snapshot below:

![Figure 11.5: Gadgets are easy to publish and some gadgets, like Google's
*Address* and *Map* gadgets, are automatically synced to communicate with each
other.](../../images/opensocial-25.png)

Note: The address bar is already filled with an address. This default address is
specified in the *Google Address* gadget's source code. We will edit this
setting using Liferay's gadget editor later in the chapter.

3. Input an address into the *Address* bar and then click *Update*. You should
   now be able to see that address location displayed in the *Google Map*
   gadget.

![Figure 11.6: After inputting a custom address in the *Google Address* gadget,
you are able to see its physical location in the *Google Map*
gadget.](../../images/opensocial-26.png)

Congratulations! Your gadgets are communicating well with each other. This
simple example demonstrates two gadgets communicating with each other using
PubSub. We will now dive into the source code and analyze how this interaction
is accomplished.

First, we'll look at the contents of *Google Map*'s XML file
*GoogleMapsPublisher.xml*:

	<?xml version="1.0" encoding="UTF-8" ?> 

	<Module>
		<ModulePrefs title="Google Address">
			<Require feature="pubsub-2">
				<Param name="topics">
					<![CDATA[ 
					<Topic title="Google Maps" name="com.liferay.opensocial.gmapsdemo" publish="true"/>
					]]>
				</Param>
			</Require>
			<Require feature="dynamic-height" />
		</ModulePrefs> 

		<Content type="html">
			<![CDATA[    
			<table>
				<tr>
					<td>Address:</td>
					<td>
						<input type="text" id="address" name="address" size="40" value="1220 Brea Canyon Rd, Walnut, CA 91789">
					</td>
					<td>
						<input type="button" value="Update" onclick="updateLoc()">
					</td>
				</tr>
			</table>

			<script type="text/javascript">
				function updateLoc() {
					var address = _gel("address").value;
					gadgets.Hub.publish("com.liferay.opensocial.gmapsdemo", address);
				}

				gadgets.window.adjustHeight();
			</script>
			]]>
		</Content>
	</Module>

The following excerpt from *Google Map*'s XML file enables the gadget to use
PubSub and specifies the channel (topic) to which the gadget publishes:

	<Require feature="pubsub-2">
		<Param name="topics">
			<![CDATA[ 
			<Topic title="Google Maps" name="com.liferay.opensocial.gmapsdemo" publish="true"/>
			]]>
		</Param>
	</Require>

Notice the opening `<Require feature="pubsub-2">`, mandating the `pubsub-2`
feature for the gadget. The `<Param name="topics">` section establishes the
topic `com.liferay.opensocial.gmapsdemo` that the gadget publishes to. Within
the *topics* parameter you define all the topics your gadget uses for
communication. Furthermore, the `publish="true"` attribute specifies the
gadget's role as a *publisher* to the topic.

In the Javascript of the gadget's *content*, the gadget publishes to the topic
with the following invocation:

	gadgets.Hub.publish("com.liferay.opensocial.gmapsdemo", address);

When the user clicks the gadget's *Publish* button, the message is sent by the
publishing gadget to the topic named `com.liferay.opensocial.gmapsdemo`. PubSub
broadcasts the message received on that topic to all subscribers, such as your
*Google Address* gadget. Each subscriber receives and processes the message. In
the case of your *Google Map* gadget, the message, containing an address, is
processed by the gadget to show the address location on its map.

Next, we'll analyze the example's *subscribing* gadget's source code specified
in  *GoogleMapsViewer.xml*:

<?xml version="1.0" encoding="UTF-8" ?> 

	<Module>
		<ModulePrefs title="Google Map">
			<Require feature="pubsub-2">
				<Param name="topics">
					<![CDATA[ 
					<Topic title="Google Maps" name="com.liferay.opensocial.gmapsdemo" subscribe="true"/>
					]]>
				</Param>
			</Require>
			<Require feature="dynamic-height" />
		</ModulePrefs>

		<Content type="html">
			<![CDATA[ 
			<script src="http://maps.google.com/maps?file=api&amp;v=2.x" type="text/javascript"></script>

			<div id="map" style="width:100%;height:100%"></div>

			<script type="text/javascript">
				var geocoder = new GClientGeocoder();

				gadgets.HubSettings.onConnect = function(hub, suc, err) {
					gadgets.Hub.subscribe("com.liferay.opensocial.gmapsdemo", callback);
				}

				function callback(topic, data, subscriberData) {			
					geocoder.getLocations(data, showAddress);
				}

				function showAddress(response) {
					map.clearOverlays();
					place = response.Placemark[0];
					point = newGLatLng(place.Point.coordinates[1],place.Point.coordinates[0]);
					marker = new GMarker(point);
					map.addOverlay(marker);
					marker.openInfoWindowHtml(place.address);
					map.setZoom(12);
					map.panTo(point, 13);
				}

				var map = new GMap2(document.getElementById("map"));

				map.setCenter(new GLatLng(43, -100), 3);

				gadgets.window.adjustHeight();
			</script>
			]]>
		</Content>
	</Module>

The subscriber source code is similar to that of the publisher. It mandates the
`pubsub-2` feature for the gadget and specifies
`com.liferay.opensocial.gmapsdemo` as one of its topics, as you would expect. Of
course, the only difference is the fact that this gadget subscribes to the
topic, hence, the attribute setting `subscribe="true"`.

The following Javascript from the gadget's *content* registers a callback on the
`com.liferay.opensocial.gmapsdemo` topic:

	gadgets.HubSettings.onConnect = function(hub, suc, err) {
            gadgets.Hub.subscribe("com.liferay.opensocial.gmapsdemo", callback);

The `gadgets.HubSettings.onConnect` function will be called by the OpenSocial
container once the gadget connects to the PubSub messaging hub. In our example
the gadget will subscribe to the previously mentioned topic. All subscribers to
this topic receive messages sent to it.

When a message is received, the gadget's `callback()` function is executed. In
this example, the callback method sends the received message (the address sent
by the publisher) and calls `GClientGeocoder.getLocations()` to get the
locations. And finally, the locations are processed and displayed on the map.

In summary, subscriber gadgets need to specify a topic and register a callback
function on that topic to handle the messages they receive.

As you can see, PubSub allows your site to run efficiently and enables otherwise
unconnected gadgets to communicate and flourish within Liferay Portal.

Gadgets are not limited to only communicating with other gadgets. In the next
section, we will demonstrate the capabilities of communication between portlets
and gadgets.

### Communicating between portlets and gadgets

For this section, we will continue implementing the *Google Map* gadget in your
site. It would be convenient for a user to, not only implement "gadget to
gadget" communications, but to also allow communications between those gadgets
and your portlets.

As a demonstration, we will send messages from a *Directory* portlet to the
*Google Map* gadget. For each user listed in the *Directory* portlet, we will
create a *Show in Google Maps* link that, when selected, will display the
location of the user's address in the *Google Map* gadget.

We will be editing a portlet much like Liferay's *Directory* portlet. The only
step we need to take is editing the portlet's `address.jsp` file to configure a
Google Maps link. 

As you know from explanations given earlier, the *Google Map* gadget is
distinguished as a subscriber. Therefore, the *Directory* portlet needs to take
on a publisher role to enable communication. To enable the *Directory* portlet
to publish to the topic on which the *Google Map* gadget is subscribed, we
insert the following Javascript into the `address.jsp` file:

	<script type="text/javascript">
		function publishAddress(address) {
			Liferay.fire('gadget:com.liferay.opensocial.gmapsdemo', address);
		}
	</script>

This code involves a `publishAddress()` function which is called whenever you
click on the *Show in Google Maps* link. The function invokes the
`Liferay.fire()` function passing in the name of the topic receiving the message
and the user's address as the message. One thing to note is that when a portlet
is sending data to a gadget, there must be a `gadget:` prefix before the topic
declaration. This distinguishes who the messages are intended for when they are
broadcast across a topic. Notice that you don't need to change anything for your
*Google Map* gadget, since its already subscribed to that topic. You only needed
to define the *Directory* portlet as a publisher to that topic.

---

![](../../images/tip-pen-paper.png)**Note:** If you would like to broadcast
messages to portlets, follow the same guidelines, but do not use the `gadget:`
prefix in your topic parameter value for the call to the `Liferay.fire()`
function.

---

After editing the JSP, you can add the *Directory* Portlet and *Google Map*
gadget to a Liferay page and test it out. Here is a snapshot of what the
interaction would look like:

![Figure 11.7: Your modified *Directory* portlet sends a user address to the
*Google Map* gadget to display the address location in its
map.](../../images/opensocial-27.png)

Communicating between gadgets and portlets within your site can enhance your
portlets and offers a plethora of different options you can incorporate in your
portal.

We will now switch gears and dive into Liferay's gadget editor!

## Using the gadget editor

As part of Liferay's OpenSocial integration, the *OpenSocial Gadget Editor* is
included with Liferay Portal. The gadget editor is a complete development
environment for gadgets providing syntax highlighting, preview functionality,
undo/redo options, and built in tabs for working on multiple gadgets at the same
time. You can also organize and manage gadgets easily using the editor's file
manager. All of this gives you the convenience of creating and improving your
gadgets right from within Liferay Portal.

Within the editor, each gadget's XML file has a wrench icon allowing you to
close, rename, delete, or publish the gadget, or to simply show the gadget's
URL. The *Publish* button redirects you to a screen, similar to the *OpenSocial
Gadget Publisher*, allowing you to publish your gadget. Gadgets published
through the editor will be stored in the site's Document and Media Library. The
*Show URL* button allows you to add the gadget "adhoc" and share with other
sites. These options offer a user-friendly and easy to use testing station for
enhancing the gadgets on your sites.

![Figure 11.8: The wrench icon can be easily found to the right of your XML
file.](../../images/opensocial-39.png)

---

![](../../images/tip-pen-paper.png)**Note:** When you publish a new gadget,
remember that your Liferay Portal is the new host when specifying the gadget's
URL.

---

For a brief exercise, we will improve the *Google Address* gadget using the
gadget editor. As we referenced earlier, the *Google Address* gadget
automatically displays the address *1220 Brea Canyon Rd, Walnut, CA 91789* in
its text window. By using the *OpenSocial Gadget Editor*, you can edit the XML
file and specify a customized address or remove the default address entirely
from the gadget's UI. For our example, we will remove the default address
entirely from our gadget's text window.

1. You will need to copy the gadget XML contents into the gadget editor.
   Navigate to *OpenSocial Gadget Publisher* from under the *Portal* heading and
   select the URL for *Google Address*.

2. Copy the XML content onto your clipboard.

3. Navigate to the *OpenSocial Gadget Editor* from the Control Panel and paste
   your clipboard contents into the gadget editor.

4. Click the floppy disk button to save your new gadget XML, naming your gadget
   "GoogleMapsPublisher.xml". Press the green check button to save the file.

![Figure 11.9: It is easy to insert gadget content into Liferay's *OpenSocial
Gadget Editor* and save it as an OpenSocial
gadget.](../../images/opensocial-31.png)

---

![](../../images/tip-pen-paper.png)**Note:** Liferay will not allow you to
publish your new gadget without attaching `.xml` to the end of your file name. 

---

5. Select the *Preview* tab from the toolbar and you will be presented with a
   preview of your gadget.

![Figure 11.10: The *Preview* tab displays what your gadget would look like if
it was added to a Liferay page.](../../images/opensocial-32.png)

6. Locate the following element in the gadget XML:

		<input type="text" id="address" name="address" size="40" value="1220 Brea Canyon Rd, Walnut, CA 91789">

	Then, remove the `value="1220 Brea Canyon Rd, Walnut, CA 91789"` setting and save the file.

7. Select the editor's *Preview* mode and click the refresh button.

![Figure 11.11: The refresh button is easy to find in the upper right corner of
the editor's *Preview* pane.](../../images/opensocial-34.png)

As you would expect, the gadget's default address is now blank.

![Figure 11.12: Your updated gadget now has a blank text window, thanks to the
editing you've done in Liferay's gadget editor.](../../images/opensocial-33.png)

8. Publish your gadget for portal-wide use by selecting the wrench icon next to
   the *GoogleMapsPublisher.xml* file and clicking *Publish*.

![Figure 11.13: You can publish your OpenSocial gadgets directly from Liferay's
*OpenSocial Gadget Editor*.](../../images/opensocial-35.png)

A *Publish Gadget* screen opens up, showing your gadget's URL and categories to
select for your gadget.

![Figure 11.14: A *Publish Gadget* window displays your gadget's URL and a host
of categories for you to consider for your
gadget.](../../images/opensocial-36.png)

9. Choose a category and click *Save*.

Your new gadget is now available for portal-wide use!

The *OpenSocial Gadget Editor* allows you to create and improve gadgets within
the comfort of your own Liferay Portal instance.

You can facilitate the social interactions on your sites and increase your
portal content's popularity across your social network by leveraging the power
of OpenSocial gadgets.

## Summary

OpenSocial gadgets offer a plethora of new features to Liferay that present new
opportunities for your portal customization. You've learned the anatomy of a
gadget, how to access third-party applications from a gadget, and gadget
communication with PubSub. Also, you learned that Liferay's gadget editor makes
editing and customizing your gadgets easier than ever. Take a deep breath and
give your self a pat on the back, you're now a trained gadget guru!

Next, we'll explore the seemingly mysterious world of plugin security management.
