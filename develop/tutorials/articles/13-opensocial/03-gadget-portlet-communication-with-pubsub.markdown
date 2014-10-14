# Gadget/Portlet Communication with PubSub [](id=gadget-portlet-communication-with-pubsub)

Have you ever wanted your gadgets to communicate with each other or with
portlets? You can do so with *PubSub*. It is a messaging pattern in which
publishers send messages to topics and subscribers receive the messages on those
topics. Hence, the term PubSub is short for "Publish and Subscribe." PubSub is
implemented in Liferay to facilitate interaction between gadgets and interaction
between gadgets and portlets. PubSub is a diverse messaging system that allows
messages to be sent in the following manner:

- gadget to gadget
- portlet to gadget or vice versa
- portlet to portlet

With the use of PubSub, the worlds of gadgets and portlets blend together,
facilitating their ability to communicate and interact with each other.

Publishers don't send messages to subscribers directly; instead, they publish
messages that are characterized into classes and sent across message channels
(topics). Therefore, the publishers have no knowledge of *whom* they are
sending their messages to; they simply broadcast the messages over a channel.
So you may ask: "How do subscribers receive messages if the messages are not
sent directly to them?" Subscribers express interest by subscribing to certain
channels. Once subscribed, they receive messages sent to those channels. Like
publishers, they have no knowledge of *who* sends the messages, they only know
channels and receive messages coming in on those channels. This process makes
it much easier for multiple gadgets and portlets to communicate with each
other, without specifically stating with whom they are communicating. 

PubSub can be compared to a TV station and your TV. The TV station does not
directly send their material to your TV, but instead broadcasts the material
over a channel. You subscribe to that channel by tuning your TV to that channel
to watch what's on it. This process of broadcasting and tuning into TV programs
is similar to publishing and subscribing to messages sent via PubSub.

PubSub also offers the ability for portlets and gadgets to publish to and
subscribe to multiple channels. Although this may seem like a complex process,
PubSub's use of message channels creates a network for gadgets to communicate
across, and the network is easy to maintain and understand.

Let's try out PubSub for ourselves to explore how PubSub works. In this
fundamental example, we'll import two simple gadgets and send messages from one
to the other. One acts as a publisher and the other as a subscriber. Follow the
steps below:

1.  Navigate to the Control Panel and select *OpenSocial Gadget Publisher* from
    under the *Portal* heading.

2.  Select *Publish Gadget* and, for each gadget, enter the URL and click *Save*. 

    *Sample PubSub Publisher URL*:
    `http://svn.apache.org/repos/asf/shindig/trunk/content/container/sample-pubsub-2-publisher.xml`

    *Sample PubSub Subscriber URL*:
    `http://svn.apache.org/repos/asf/shindig/trunk/content/container/sample-pubsub-2-subscriber.xml`
	
3.  Go back to a page on your site, navigate to *Add* &rarr; *More...*, and add
    your new gadgets to the page

4.  Click *Publish a random number* on the PubSub Publisher gadget.  Notice that
    it publishes a number; but the PubSub Subscriber gadget does not receive the
    number.

    ![Figure 13.2: The subscriber cannot receive any messages from the publisher without being subscribed to the channel.](../../images/opensocial-23.png)

5.  Select *Subscribe* on the subscriber gadget.

6.  Click *Publish a random number* again from the publisher gadget. You now see
    the random number received by the subscriber.

    ![Figure 13.3: When the subscriber is subscribed to the publisher's channel, the subscriber is able to receive messages.](../../images/opensocial-24.png)

7.  Select *Unsubscribe* on the subscriber gadget.

8.  Click *Publish a random number*.

As you would expect, the subscriber portlet no longer receives the random
number.

This simple example illustrates what PubSub does. It opens up a message channel
through which publishers and subscribers can interact. You probably also
noticed that while publishing and subscribing with your gadgets, there was no
need to refresh the page. This is because the gadgets use *AJAX (Asynchronous
JavaScript and XML)*. This technology refreshes your applications
automatically, allowing PubSub to work efficiently and effectively throughout
your Liferay Portal.

I bet you are wondering how to implement PubSub messaging in your gadgets and
portlets. We'll dive into the code next. In fact, we'll complete exercises
demonstrating gadget to gadget interaction and portlet to gadget interaction.

## Gadget to Gadget [](id=gadget-to-gadget)

For gadget to gadget communication, two independent gadgets are placed on a page
and configured with PubSub. These two gadgets are able to communicate with one
another and provide tools that the user could not otherwise produce. We will
complete a simple example for gadget to gadget communication where two gadgets
work together to display an address on Google Maps. The first gadget represents
a publisher that enables the user to input a specific address and publish the
address. The second gadget represents the subscriber who receives the address, 
displays the address, and locates the address on Google Maps. Follow the steps
below:

1.  Publish the *Google Address* and *Google Map* gadgets, as we had done
    previously with other gadgets. The URLs are given below:

    *Google Address URL*:
    `https://raw.github.com/dejuknow/opensocial-gadgets/master/GoogleMaps/GoogleMapsPublisher.xml`

    *Google Map URL*:
    `https://raw.github.com/dejuknow/opensocial-gadgets/master/GoogleMaps/GoogleMapsViewer.xml`

2.  Add both gadgets to a page.

    ![Figure 13.4: Gadgets are easy to publish and some gadgets, like Google's *Address* and *Map* gadgets, are automatically synced to communicate with each other.](../../images/opensocial-25.png)

    Note: The address bar is already filled with an address. This default
    address is specified in the *Google Address* gadget's source code. We will
    edit this setting using Liferay's gadget editor later in the chapter.

3.  Input an address into the *Address* bar and then click *Update*. You should
    now be able to see that address location displayed in the *Google Map*
    gadget.

    ![Figure 13.5: After inputting a custom address in the *Google Address* gadget, you are able to see its physical location in the *Google Map* gadget.](../../images/opensocial-26.png)

Congratulations! Your gadgets are communicating well with each other. This
simple example demonstrates two gadgets communicating with each other using
PubSub. We will now dive into the source code and analyze how this interaction
is accomplished.

First, we'll look at the contents of the *Google Maps* XML file
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
						<input type="text" id="address" name="address" size="40" value="1400 Montefino Ave, Diamond Bar, CA 91765">
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

The following excerpt from *Google Maps* XML file enables the gadget to use
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
the *topics* parameter, you define all the topics your gadget uses for
communication. Furthermore, the `publish="true"` attribute specifies the
gadget's role as a *publisher* to the topic.

In the JavaScript of the gadget's *content*, the gadget publishes to the topic
with the following invocation:

	gadgets.Hub.publish("com.liferay.opensocial.gmapsdemo", address);

When the user clicks the gadget's *Publish* button, the message is sent by the
publishing gadget to the topic named `com.liferay.opensocial.gmapsdemo`. PubSub
broadcasts the message received on that topic to all subscribers, such as your
*Google Address* gadget. Each subscriber receives and processes the message. In
the case of your *Google Maps* gadget, the message, containing an address, is
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
topic, hence the attribute setting `subscribe="true"`.

The following JavaScript from the gadget's *content* registers a callback on the
`com.liferay.opensocial.gmapsdemo` topic:

	gadgets.HubSettings.onConnect = function(hub, suc, err) {
            gadgets.Hub.subscribe("com.liferay.opensocial.gmapsdemo", callback);

The `gadgets.HubSettings.onConnect` function is called by the OpenSocial
container once the gadget connects to the PubSub messaging hub. In our example, 
the gadget subscribes to the previously mentioned topic. All subscribers to
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

## Communicating Between Portlets and Gadgets [](id=communicating-between-portlets-and-gadgets)

For this section, we will continue implementing the *Google Map* gadget on your
site. If you have portlet applications that can take advantage of the
functionality your Maps gadgets have to offer, it would certainly be convenient
for a user to allow communications between those gadgets and your portlets.

As a demonstration, we will send messages from a *Directory* portlet to the
*Google Map* gadget. For each user listed in the *Directory* portlet, we will
create a *Show in Google Maps* link that, when selected, displays the location
of the user's address in the *Google Map* gadget.

This portlet is much like Liferay's *Directory* portlet. The first thing we need
to do is edit the portlet's `address.jsp` file to configure a Google Maps link. 

As you know from explanations given earlier, the *Google Map* gadget is
distinguished as a subscriber. Therefore, the *Directory* portlet needs to take
on a publisher role to enable communication. To enable the *Directory* portlet
to publish to the topic on which the *Google Map* gadget is subscribed, we
insert the following JavaScript into the `address.jsp` file:

	<script type="text/javascript">
		function publishAddress(address) {
			Liferay.fire('gadget:com.liferay.opensocial.gmapsdemo', address);
		}
	</script>

This code involves a `publishAddress()` function that's called whenever you
click on the *Show in Google Maps* link. The function invokes the
`Liferay.fire()` function, passing in the name of the channel receiving the
message and the user's address as the message. One thing to note is that when a
portlet sends data to a gadget, there must be a `gadget:` prefix before the
channel declaration. This distinguishes who the messages are intended for when
they are broadcast across a channel. Notice that you don't need to change anything
for your *Google Map* gadget, since it's already subscribed to that channel. You
only needed to define the *Directory* portlet as a publisher to that channel.

+$$$

**Note:** If you would like to broadcast messages to portlets, follow the same
guidelines, but don't use the `gadget:` prefix in your topic parameter value for
the call to the `Liferay.fire()` function.

$$$

After editing the JSP, you can add the *Directory* Portlet and *Google Map*
gadget to a Liferay page and test it out. Here is a snapshot of what the
interaction would look like:

![Figure 13.6: Your modified *Directory* portlet sends a user address to the *Google Map* gadget to display the address location in its map.](../../images/opensocial-27.png)

Letting your portlets communicate with gadgets enhances your portlet
applications and gives you a plethora of different ways you can enhance your
users' experience. 

We will now switch gears and dive into Liferay's gadget editor!
