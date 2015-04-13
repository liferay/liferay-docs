# Sending PubSub Messages Between Gadgets and Portlets [](id=sending-pubsub-messages-between-gadgets-and-portlet)

<!-- You can test the gadget-to-gadget tutorial using the following gadgets:
https://raw.github.com/dejuknow/opensocial-gadgets/master/GoogleMaps/GoogleMapsPublisher.xml
https://raw.github.com/dejuknow/opensocial-gadgets/master/GoogleMaps/GoogleMapsViewer.xml
-->

<!-- The Portal Directory hook required for the portlet-to-gadget tutorial is
available here:
https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/opensoc/sending-pubsub-messages/portal-directory-hook
-->

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

## Communicating Between Gadgets [](id=communicating-between-gadgets)

For gadget to gadget communication, two independent gadgets are placed on a page
and configured with PubSub; these two gadgets are able to communicate with one
another and provide tools that the user could not otherwise produce. As an
example, we'll demonstrate two Google Maps gadgets that publish and subscribe to
the same channel. Using PubSub, these gadgets work together to display an
address on a map. 

<!-- Reference documentation that explains how to publish a gadget, either in
the OpenSocial learning path or additional tutorial. -Cody -->

The first gadget represents a publisher that enables the user to input a
specific address and publish the address. The second gadget represents the
subscriber who receives the address, displays the address, and locates the
address on Google Maps.

![Figure 1: Gadgets like Google's *Address* and *Map* gadgets are automatically synced to communicate with each other.](../../images/opensocial-25.png)

Once the Update button is selected in the Google Address gadget, the address is
published to a specific channel. Since the Google Map gadget is subscribed to
that same channel, it receives the address and then displays it in the map.

![Figure 2: After inputting a custom address in the Google Address gadget, you are able to see its physical location in the Google Map gadget.](../../images/opensocial-26.png)

That's all there is to it! The gadgets are communicating well with each other.
This simple tutorial demonstrated two gadgets communicating with each other
using PubSub. 

+$$$

**Note:** Gadget-to-gadget communication
using the publish-subscribe framework has been deprecated for Google gadgets:
[https://developers.google.com/gadgets/docs/pubsub](https://developers.google.com/gadgets/docs/pubsub).
However, PubSub is still the primary communication for gadgets and will be
supported with gadgets on Liferay.

$$$

We will now dive into the source code and analyze how this interaction is
accomplished. To view the full contents of the Google Map, visit the
[GoogleMapsPublisher.xml](https://raw.githubusercontent.com/dejuknow/opensocial-gadgets/master/GoogleMaps/GoogleMapsPublisher.xml)
file.

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

When the user clicks the gadget's *Update* button, the message is sent by the
publishing gadget to the topic named `com.liferay.opensocial.gmapsdemo`; PubSub
broadcasts the message received on that topic to all subscribers, such as your
Google Address gadget. Each subscriber receives and processes the message. In
the case of your Google Map gadget, the message containing an address is
processed by the gadget to show the address location on its map.

Next, we'll analyze the tutorial's *subscribing* gadget's source code specified
in the
[GoogleMapsViewer.xml](https://raw.githubusercontent.com/dejuknow/opensocial-gadgets/master/GoogleMaps/GoogleMapsViewer.xml)
file.

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

## Communicating Between Portlets and Gadgets [](id=communicating-between-portlets-and-gadgets)

For this tutorial, we'll continue exploring the *Google Map* gadget inside of
Liferay. If you have portlet applications that can take advantage of the
functionality your Maps gadgets have to offer, it would certainly be convenient
for a user to allow communications between those gadgets and your portlets.

+$$$

**Note:** The publish-subscribe
framework has been deprecated for Google gadgets:
[https://developers.google.com/gadgets/docs/pubsub](https://developers.google.com/gadgets/docs/pubsub).
However, PubSub is still a primary communication mechanism between portlets and
gadgets and will be supported on Liferay.

$$$

As a demonstration, we'll show a customized *Portal Directory* portlet that
communicates to the Google Map gadget using PubSub. For each user listed in the
Portal Directory portlet that has a specified address, a *Show in Google Maps*
link is displayed beneath the user's address. When the link is selected, the
location of the user's address is displayed in the Google Map gadget.

![Figure 3: The modified Portal Directory portlet sends the user's address to the *Google Map* gadget to display.](../../images/opensocial-27.png)

The Portal Directory portlet's `addresses.jsp` file was edited to assign the
portlet as a PubSub publisher, but what edits to the JSP file made this
possible?

+$$$

**Note:** If you'd like to edit a
Liferay core portlet, it's not recommended to edit its code directly. Always
customize Liferay core portlets using Liferay hooks.

$$$

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
they are broadcast across a channel.

Notice that you don't need to change anything for your *Google Map* gadget,
since it's already subscribed to that channel; you only needed to define the
Portal Directory portlet as a publisher to that channel.

+$$$

**Note:** If you would like to broadcast
messages to portlets, follow the same guidelines, but don't use the `gadget:`
prefix in your topic parameter value for the call to the `Liferay.fire()`
function.

$$$

Lastly, a button is needed to initiate the publishing of the address to the
topic in the Portal Directory portlet; to do this, we inserted the following
into the `addresses.jsp` file:

    <a href="javascript:void(0);" onclick='publishAddress("<%= street1 + ", " + zipCode + ", " + city %>")'>Show in Google Maps</a>

Letting your portlets communicate with gadgets enhances your portlet
applications and gives you a plethora of different ways you can enhance your 
user's experience. 

## Related Topics

[Customizing Liferay Portal with Hooks](/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)

[User Interfaces with AlloyUI](/tutorials/-/knowledge_base/6-2/alloyui)

[Localization](/tutorials/-/knowledge_base/6-2/localization)
