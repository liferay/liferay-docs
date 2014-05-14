# Sending PubSub Messages Between Gadgets and Portlets

Have you ever wanted your gadgets to communicate with each other or with
portlets? You can do so with *PubSub*. PubSub is implemented in Liferay to
facilitate interaction between gadgets and interaction between gadgets and
portlets. PubSub is a diverse messaging system that allows messages to be sent
in the following manner:

- gadget to gadget
- portlet to gadget or vice versa
- portlet to portlet

With the use of PubSub, the worlds of gadgets and portlets blend together,
facilitating their ability to communicate and interact with each other.

We'll run through a tutorial for gadget to gadget communication, and then show
an example for using gadget to portlet communication; both will integrate Google
Maps into Liferay Portal. After we've run through the exercises, we'll briefly
analyze specific code fragments that made the PubSub messaging possible. Let's
get started!

## Communicating Between Gadgets

For gadget to gadget communication, two independent gadgets are placed on a page
and configured with PubSub; these two gadgets are able to communicate with one
another and provide tools that the user could not otherwise produce. We'll
complete a simple tutorial for gadget to gadget communication where two gadgets
work together to display an address on Google Maps. The first gadget represents
a publisher that enables the user to input a specific address and publish the
address. The second gadget represents the subscriber who receives the address, 
displays the address, and locates the address on Google Maps. Follow the steps
below:

1. Publish the *Google Address* and *Google Map* gadgets, as we had done
   previously with other gadgets. The URLs are given below:

    *Google Address URL*:
    `https://raw.github.com/dejuknow/opensocial-gadgets/master/GoogleMaps/GoogleMapsPublisher.xml`

    *Google Map URL*:
    `https://raw.github.com/dejuknow/opensocial-gadgets/master/GoogleMaps/GoogleMapsViewer.xml`

2. Add both gadgets to a page.

    ![Figure 1: Gadgets are easy to publish and some gadgets, like Google's *Address* and *Map* gadgets, are automatically synced to communicate with each other.](../../images/opensocial-25.png)

    Note: The address bar is already filled with an address. This default
    address is specified in the *Google Address* gadget's source code. We will
    edit this setting using Liferay's gadget editor later in the chapter.

3. Input an address into the *Address* bar and then click *Update*. You should
   now be able to see that address location displayed in the Google Map gadget.

    ![Figure 2: After inputting a custom address in the *Google Address* gadget, you are able to see its physical location in the *Google Map* gadget.](../../images/opensocial-26.png)

Congratulations! Your gadgets are communicating well with each other. This
simple tutorial demonstrates two gadgets communicating with each other using
PubSub.

---

![Note](../../images/tip-pen-paper.png) **Note:** Gadget-to-gadget communication
using the publish-subscribe framework has been deprecated for Google gadgets:
[https://developers.google.com/gadgets/docs/pubsub](https://developers.google.com/gadgets/docs/pubsub).
However, PubSub is still the primary communication for gadgets and will be
supported with gadgets on Liferay.

---

We will now dive into the source code and analyze how this interaction is
accomplished. First, we'll look at the contents of the Google Map XML file
*GoogleMapsPublisher.xml*:

	<?xml version="1.0" encoding="UTF-8" ?>

	<Module>
		<ModulePrefs title="Google Address">
			<Require feature="pubsub-2">
				<Param name="topics">
					<![CDATA[
					<Topic
					    title="Google Map"
					    name="com.liferay.opensocial.gmapsdemo" publish="true"
					/>
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
						<input
						    type="text"
						    id="address"
						    name="address"
						    size="40"
						    value="1400 Montefino Ave, Diamond Bar, CA 91765"
						>
					</td>
					<td>
						<input
						    type="button"
						    value="Update"
						    onclick="updateLoc()"
						>
					</td>
				</tr>
			</table>

			<script type="text/javascript">
				function updateLoc() {
					var address = document.getElementById("address").value;
					gadgets.Hub.publish(
					    "com.liferay.opensocial.gmapsdemo", address);
				}

				gadgets.window.adjustHeight();
			</script>
			]]>
		</Content>
	</Module>

The following excerpt from the Google Map XML file enables the gadget to use
PubSub and specifies the channel (topic) to which the gadget publishes:

	<Require feature="pubsub-2">
		<Param name="topics">
			<![CDATA[ 
			<Topic
			    title="Google Map"
			    name="com.liferay.opensocial.gmapsdemo"
			    publish="true"
			/>
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
publishing gadget to the topic named `com.liferay.opensocial.gmapsdemo`; PubSub
broadcasts the message received on that topic to all subscribers, such as your
Google Address gadget. Each subscriber receives and processes the message. In
the case of your Google Map gadget, the message containing an address is
processed by the gadget to show the address location on its map.

Next, we'll analyze the tutorial's *subscribing* gadget's source code specified
in  *GoogleMapsViewer.xml*:

	<?xml version="1.0" encoding="UTF-8" ?>

	<Module>
		<ModulePrefs title="Google Map">
			<Require feature="pubsub-2">
				<Param name="topics">
					<![CDATA[
					<Topic
					    title="Google Map"
					    name="com.liferay.opensocial.gmapsdemo"
					    subscribe="true"
					/>
					]]>
				</Param>
			</Require>
			<Require feature="dynamic-height" />
		</ModulePrefs>

		<Content type="html">
			<![CDATA[
			<script
				src="https://maps.googleapis.com/maps/api/js?sensor=false"
			    type="text/javascript">
			</script>

			<div id="map" style="width:100%;height:100%"></div>

			<script type="text/javascript">
				gadgets.HubSettings.onConnect = function(hub, suc, err) {
					gadgets.Hub.subscribe("com.liferay.opensocial.gmapsdemo", callback);
				}

				function callback(topic, data, subscriberData) {
					geocoder.geocode( { 'address': data }, showAddress);
				}

				function showAddress(results, status) {
					if (status == google.maps.GeocoderStatus.OK) {
						while(overlays[0]) {
							overlays.pop().setMap(null);
						}

						map.setCenter(results[0].geometry.location);

						var marker = new google.maps.Marker(
							{
								map: map,
								position: results[0].geometry.location
							}
						);

						overlays.push(marker);
					}
					else {
						alert('Failed to locate address. Reason: ' + status);
					}

					map.setZoom(12);
				}

				var overlays = [];

				var geocoder = new google.maps.Geocoder();

				var mapOptions = {
					center: new google.maps.LatLng(43, -100),
					zoom: 3,
					mapTypeId: google.maps.MapTypeId.ROADMAP
				};

				var map = new google.maps.Map(document.getElementById("map"), mapOptions);

				gadgets.window.adjustHeight();
			</script>
			]]>
		</Content>
	</Module>

The subscriber source code is similar to that of the publisher: it mandates the
`pubsub-2` feature for the gadget and specifies 
`com.liferay.opensocial.gmapsdemo` as one of its topics, as you would expect; of
course, the only difference is the fact that this gadget subscribes to the
topic, hence the attribute setting `subscribe="true"`.

The following JavaScript from the gadget's *content* registers a callback on the
`com.liferay.opensocial.gmapsdemo` topic:

	gadgets.HubSettings.onConnect = function(hub, suc, err) {
            gadgets.Hub.subscribe("com.liferay.opensocial.gmapsdemo", callback);
        }

The `gadgets.HubSettings.onConnect` function is called by the OpenSocial
container, once the gadget connects to the PubSub messaging hub. In our example, 
the gadget subscribes to the previously mentioned topic; all subscribers to
this topic receive messages sent to it.

When a message is received, the gadget's `callback()` function is executed. In
this example the callback method sends the received message (the address sent
by the publisher), calls `google.maps.Geocoder.geocode()` to get the
locations, and finally processes and displays the locations on the map.

In summary, subscriber gadgets need to specify a topic and register a callback
function on that topic to handle the messages they receive.

As you can see, PubSub allows your site to run efficiently and enables otherwise
unconnected gadgets to communicate and flourish within Liferay Portal.

Gadgets are not limited to only communicating with other gadgets. In the next
section, we will demonstrate the capabilities of communication between portlets
and gadgets.

## Communicating Between Portlets and Gadgets

For this tutorial, we'll continue implementing the *Google Map* gadget on your
site. If you have portlet applications that can take advantage of the
functionality your Maps gadgets have to offer, it would certainly be convenient
for a user to allow communications between those gadgets and your portlets.

---

![Note](../../images/tip-pen-paper.png) **Note:** The publish-subscribe
framework has been deprecated for Google gadgets:
[https://developers.google.com/gadgets/docs/pubsub](https://developers.google.com/gadgets/docs/pubsub).
However, PubSub is still a primary communication mechanism between portlets and
gadgets and will be supported on Liferay.

---

As a demonstration, we'll send messages from the *Portal Directory* portlet to
the Google Map gadget. For each user listed in the Portal Directory portlet,
we'll create a *Show in Google Maps* link that, when selected, displays the
location of the user's address in the Google Map gadget.

1. Click your user's name in the dockbar and select *My Account*. Then, select
   *Addresses* from the right menu, enter an address, and click *Save*.

2. Navigate back to a site page and select *Add* &rarr; *Applications* &rarr; 
   *Community* and add the Portal Directory portlet to the page. Also, make sure 
   the Google Map gadget from the previous tutorial is positioned near the 
   Portal Directory portlet.

3. In the Portal Directory portlet, make sure you're in the Users tab and select
   the user you added an address for. Notice the address is displayed, but
   there's no way to map that address to our Google Map gadget.

4. To enable PubSub for the Portal Directory portlet, you'll need to edit its
   `addresses.jsp` file to configure it as a PubSub publisher. The portlet will
   need to publish the address to the channel for which the Google Map is
   subscribed. Since the Portal Directory portlet is a Liferay core portlet,
   we'll edit it using a hook. The `portal-directory-hook` is available in the
   <https://github.com/liferay/liferay-docs> GitHub repository
   [here](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/opensoc/sending-pubsub-messages-between-gadgets-and-portlets/portal-directory-hook).
   You'll need to clone the repository if you haven't already done so. Then copy
   the `portal-directory-hook` folder into the `hooks` folder of your Liferay
   Plugins SDK.

    <!-- Will probably need to edit Github URL to match new directory structure,
    once finished. -Cody -->
    
    <!-- Once learning path/tutorials for hooks are complete, we should
    reference how to create a hook and why it's important to do so when editing
    Liferay core portlets. -Cody -->

5. Deploy the Portal Directory hook into your portal instance. Then refresh the
   page. The *Show in Google Maps* link is available beneath the user's address.

6. Click the *Show in Google Maps* link.

![Figure 3: The modified Portal Directory portlet sends the user's address to the *Google Map* gadget to display.](../../images/opensocial-27.png)

Great! Now you have portlet to gadget PubSub communication working successfully,
but what did the hook edit in the `addresses.jsp` to make this possible?

As you know from explanations given earlier, the *Google Map* gadget is
distinguished as a subscriber. Therefore, the Portal Directory portlet needed to
take on the publisher role to enable communication. To enable the Portal
Directory portlet to publish to the topic on which the *Google Map* gadget is
subscribed, we inserted the following JavaScript into the `addresses.jsp` file:

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
channel declaration; this distinguishes who the messages are intended for when
they are broadcast across a channel. Notice that you don't need to change
anything for your *Google Map* gadget, since it's already subscribed to that
channel; you only needed to define the Portal Directory portlet as a publisher
to that channel.

---

![Note](../../images/tip-pen-paper.png) **Note:** If you would like to broadcast
messages to portlets, follow the same guidelines, but don't use the `gadget:`
prefix in your topic parameter value for the call to the `Liferay.fire()`
function.

---

Lastly, we needed a button that would initiate the publishing of the address to
the topic in the Portal Directory portlet; to do this, we inserted the following
into the `addresses.jsp` file:

    <br />
    <a href="javascript:void(0);" onclick='publishAddress("<%= street1 + ", " + zipCode + ", " + city %>")'>Show in Google Maps</a>

If you'd like to study the updated `addresses.jsp` file for additional
information, you can view it in the Portal Directory hook's
`docroot/META-INF/custom_jsps/html/portlet/directory/user` directory.

Letting your portlets communicate with gadgets enhances your portlet
applications and gives you a plethora of different ways you can enhance your
user's experience. 
