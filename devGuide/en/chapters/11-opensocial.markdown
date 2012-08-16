# Creating and Integrating with OpenSocial Gadgets

OpenSocial is a public specification for creating web applications using standard technologies like HTML, CSS, and Javascript. It was originally developed by Google, Myspace and others to standardize common social networking API's but has evolved into a general platform for building web applications. Whereas "standard" applications work with data on a per-user basis, "social" applications share data within well defined networks, facilitating communication of information between groups of users. OpenSocial applications, called *gadgets*, are similar to portlets because they can be added to your portal's pages and used for all kinds of tasks. Gadgets are characterized as being simple, widely available, and easy to deploy.

In this chapter, we'll cover the following topics:

- OpenSocial gadget basics.

- Using PubSub to allow gadgets to communicate with each other and with portlets.

- Liferay's gadget editing environment.

<!-- - Leveraging Liferay's Social API in your gadgets. -->

Let's first look at the structure of an OpenSocial gadget and consider the concepts involved with OpenSocial gadgets.

## OpenSocial gadget basics

An OpenSocial Gadget is specified in an XML document. The first part of the document specifies *meta-data* declaring gadget dependencies, defining characteristics about the gadget, and specifying user preferences. The meta-data can be broken up into *module preferences* and *user preferences*. The second part of the document contains the gadget *content*, defining the gadget's user interface and business logic. We'll spend time going over all these facets of gadget XML.

![Figure 11.1: An OpenSocial gadget's XML consists of elements specifying gadget preferences, user preferences, and content.](../../images/opensocial-22.png)

<!-- Adjust colors as they may be misleading? Looks like a traffic signal ... -->

Below is a sample of OpenSocial gadget XML from a weather gadget authored by The Weather Channel. The complete weather gadget is found at   [`http://gadgets.weather.com/services/gadgets/googlegadget/gadget.xml`](http://gadgets.weather.com/services/gadgets/googlegadget/gadget.xml).

	<Module>
		<ModulePrefs 
		author="The Weather Channel"
		author_email="googlegadget@talk2.weather.com"
		title="The Weather Channel" scrolling="false"
		height="390"
		thumbnail="http://i.imwx.com/web/services/gadgets/images/thumbnail.png"
		screenshot="http://i.imwx.com/web/services/gadgets/images/logo.jpg"
		description="The Weather Channel Google Gadget provides local weather forecasts based upon city or US Zip Code">
			<Require feature="setprefs"/>
		</ModulePrefs>
		<UserPref name="locId" default_value="" datatype="hidden"/>
		<UserPref name="lat" default_value="" datatype="hidden"/>
		<UserPref name="lng" default_value="" datatype="hidden"/>
		<UserPref name="showMap" default_value="true" datatype="hidden"/>
		<UserPref name="locName" default_value="" datatype="hidden"/>
		<UserPref name="installId" default_value="-7" datatype="hidden"/>
		<UserPref name="tempUnit" default_value="f" datatype="hidden"/>
		<UserPref name="skinName" default_value="" datatype="hidden"/>
		<UserPref name="skinId" default_value="" datatype="hidden"/>
		<UserPref name="ftl" default_value="true" datatype="hidden"/>
		<UserPref name="date" default_value="-7" datatype="hidden"/>
		<Content type="html">
			...
		</Content>
	</Module>

For the official documentation on Gadget anatomy, see Google's [Anatomy of a Gadget](https://developers.google.com/gadgets/docs/basic#Anatomy). But, for convenience, let's consider gadget *meta-data* and *content* here to highlight their important aspects.

### Gadget meta-data

Gadget meta-data specifies characteristics of the overall gadget and specifies controls for the user to customize the gadget. Here is a simple breakdown of these two types of meta-data:

*Gadget Preferences (required)*: Define characteristics of the gadget. They are specified as attributes of and nested elements in the gadget's `<ModulePrefs/>` element.

*User Preferences (optional)*: Store user configuration values and allow users to give input to the application. They are specified in `<UserPref/>` elements.

Now, let's dive deeper into gadget preferences.

#### Gadget Preferences (ModulePrefs)

The gadget preferences (ModulePrefs) define characteristics of your gadget, including features that it uses, how it authenticates content, how it is displayed to the user, and how it is displayed in a gadget directory. The preferences are wrapped in `<ModulePrefs>...</ModulePrefs>` tags.

Within the ModulePrefs, you specify the gadget's dependencies with `<Require/>` tags and optional features with `<Optional/>` tags. If you specify a feature in a `<Require/>` tag, the gadget itself only renders if the feature is available. If you specify a feature in an `<Optional/>` tag, the gadget renders despite feature availability.

ModulePrefs can also be used to configure important features such as PubSub and OAuth. The PubSub feature allows for communication between gadgets on the same page, by allowing them to publish and subscribe on message channels. Liferay also enables gadgets to communicate with portlets using the same mechanism. The OAuth feature provides secure connections between your portal and third-party sites. These are just a couple of useful features that can be defined in the ModulePrefs. You'll learn more about them later in this chapter.

---

 ![](../../images/tip-pen-paper.png)**Note:** To enable use of `<UserPref>` elements in your gadget XML, the `setprefs` feature must be included, by specifying `<Require feature="setprefs"/>` within your `<ModulePrefs>...<ModulePrefs/>` tags.

---

Overall, this `<ModulePrefs>` element allows you to setup your gadget on a gadget directory and establish settings and tools to use in your gadget's business logic. For complete details on ModulePrefs, see Google's [Moduleprefs Elements and Attributes](https://developers.google.com/gadgets/docs/xml_reference#Moduleprefs_Ref) reference.

Next, let's consider gadget user preferences.

#### User Preferences (UserPrefs)

What can really enhance a user's experience is when a gadget is personalized to fit the user. OpenSocial gadgets are capable of storing user preferences, allowing gadgets to present information and options tailored to individual users. A gadget's user preferences are specified in `<UserPref>...</UserPref>` tags in the gadget's XML. As a user enters information and adjusts controls via a gadget's user interface, those changes are persisted in the database for that gadget instance. Each time the gadget is reloaded, the *UserPrefs* are extracted from the database, fed back to the gadget, and rendered in the user interface.

Weather gadgets, for example, are prime candidates for UserPrefs. When users first bring up weather gadgets, they are typically interested in the weather at their current location or their residence -- not the weather of some random distant land. Therefore, it makes sense for a weather gadget to take the user's location or residence as input. It is also good to present the user's name and use a temperature unit familiar to the user. Lastly, users often like to hand-select a stylish theme for their gadget. UserPrefs facilitate taking in this information, storing it, and processing it to present gadget user interfaces customized to the user.

In order to set preference values from within your gadget, you must include the `setprefs` feature inside the `<ModulePrefs>...</ModulePrefs>` tags, which we discussed in the *Gadget Preferences (ModulePrefs)* section. Look at the weather gadget's XML to see the `setprefs` feature included as a required feature:

	<Module>
		<ModulePrefs
		author="The Weather Channel" ... >
			<Require feature="setprefs"/>
		</ModulePrefs>
		<UserPref name="locId" default_value="" datatype="hidden"/>
		<UserPref name="lat" default_value="" datatype="hidden"/>
		<UserPref name="lng" default_value="" datatype="hidden"/>
		<UserPref name="showMap" default_value="true" datatype="hidden"/>
		<UserPref name="locName" default_value="" datatype="hidden"/>
		...
		<Content type="html">
			...
		</Content>
	</Module>

Also, notice the following user preferences of this weather gadget:

- `locId` and `locName` are used to store the ID and name of the user's location.

- `lat` and `lng` are used to store the coordinates of the user's location.

- `showMap` flags whether to display the user's location on the map.

Lastly, let's take a look at the UserPrefs for a task manager gadget and compare it to what a user would see in the gadget's user interface. This task manager is called *Jira*.

	<UserPref name="version" datatype="hidden" default_value="1.2" />
	<UserPref name="title" display_name="Gadget Title" default_value="JIRA Issues" /> 
	<UserPref name="feedurl" display_name="JIRA XML URL" default_value="http://issues.liferay.com/sr/jira.issueviews:searchrequest-xml/temp/SearchRequest.xml?jqlQuery=project+%3D+LPS+AND+component+%3D+OpenSocial+AND+status+in+%28Open%2C+%22In+Progress%22%2C+Reopened%2C+%22In+Review%22%2C+%22Manual+Testing%22%2C+%22Automated+Testing%22%29" required="true"/>
	<UserPref name="maxFeedItems" display_name="Items Total" datatype="enum" default_value="10" required="true">
		<EnumValue value="1" display_value="1"/>
		<EnumValue value="2" display_value="2"/>
		<EnumValue value="3" display_value="3"/>
		<EnumValue value="4" display_value="4"/>
		<EnumValue value="5" display_value="5"/>
		<EnumValue value="6" display_value="6"/>
		...
		<EnumValue value="30" display_value="30"/>
	</UserPref>
	<UserPref name="displayIcons" display_name="Display icons?" default_value="true" datatype="bool" />
	<UserPref name="refresh" display_name="Refresh (in minutes, 0-never)" default_value="5" />
	<UserPref name="feedusername" display_name="Username" default_value="" />
	<UserPref name="feedpassword" display_name="Password" default_value="" />
	<UserPref name="useTooltip" display_name="Use Tooltip" default_value="true" datatype="bool" />

Here is a snapshot of what this window looks like on Liferay Portal:

![Figure 11.2: Here, the Jira gadget's user preferences are made available for user input.](../../images/opensocial-21.png)

<!-- TODO use a gadget other than the Jira gadget -->

UserPrefs can be displayed in many different ways and help your gadget become more adaptable to your users' ever changing expectations. And, there is an extensive variety of UserPref data types available to properly personalize your gadgets. For complete details on UserPrefs see Google's [User preferences](https://developers.google.com/gadgets/docs/xml_reference#Userprefs_Ref) reference.

### Gadget Content

Within the `<Content>...</Content>` tags of your gadget, you define your gadget's user interface and business logic. The content can be implemented in HTML or linked in as a URL. HTML allows you to use Javascript and gives you plenty of flexibility. But, if you simply want to leverage content found at a particular URL, use the URL content type.

To help you decide which content type is best for you, see Google's [Choosing a Content Type](https://developers.google.com/gadgets/docs/fundamentals#Content_Type) documentation. But for convenience, let's consider basic aspects of these content types here.

#### HTML content type

HTML is the default content type for OpenSocial gadgets. Because HTML is so flexible and commonly used for gadgets, you may find yourself using it often.

Note, all HTML content must be specified in a `CDATA` section within your gadget's content. The following simple content example demonstrates proper `CDATA` specification:

	<Content type="html">
		<![CDATA[ "Your HTML goes here" ]]>
	</Content>

Also, it is forbidden to use `<body>`, `<head>`, or `<html>` tags in your HTML content, as these tags conflict with the ones automatically generated by the container for your gadget. 

#### URL content type 

This content type is convenient when you just want to reference content within an existing URL, and nothing more. When using this content type, specify the URL as your `href` attribute value. The gadget assumes all information regarding programmatic logic and user interface resides in your specified URL. Therefore, when using the `url` content type, you do not need any HTML or JavaScript. Note, the URL content type has better consistency in the specification than the HTML content type regarding proxied content.

Here is a sample of what the URL content type looks like inside the Google Calendar gadget:

	<Content view="preview" type="url"
	href="https://www.google.com/calendar/__ENV_google_apps_auth_path__ig"/>
	<Content view="home" type="url"
	href="https://www.google.com/calendar/__ENV_google_apps_auth_path__ig"/>
	<Content view="canvas" type="url"
	href="https://www.google.com/calendar/__ENV_google_apps_auth_path__ig"/>

The complete Google Calendar gadget can be found at [`http://www.google.com/ig/modules/calendar3.xml`](http://www.google.com/ig/modules/calendar3.xml).

<!-- TODO consider using a gadget other than Google Calendar; a gadget that works outside of iGoogle -->

Both HTML and URL content types offer beneficial traits and can be used effectively. The content type you use for your gadgets depends on your needs and preferences.

Now that you're fundamentally sound on the gadget basics, let's explore what gadgets offer you in Liferay Portal.

## Accessing third-party applications from your gadget

Is there data on sites like Evernote, Facebook, Google, Netflix, Photobucket, Twitter, or Yahoo you'd like to access in your OpenSocial gadgets? Perhaps you'd like to provide a gadget for portal users to add movies to their Netflix queue or for users to display their Photobucket pictures within gadgets in Liferay Portal. You may be concerned users would be required to share their third-party application credentials with portal in order to use the applications. Good news! *OAuth* technology resolves the issue.

You can think of OAuth as a "handshake mechanism" where, instead of requiring exchange of personal information, your site redirects portal users directly to the service provider (e.g. Netflix, Photobucket, etc). Users simply approve the gadget's access to their resources on the external web applications. It's just that easy!

Read the [Gadget personalization](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/opensocial-integration) section of the *Social Networking* chapter in *Using Liferay Portal 6.1* for instructions on configuring and using OAuth enabled gadgets. To learn how to write OAuth enabled gadgets, see Google's [Writing OAuth Gadgets](https://developers.google.com/gadgets/docs/oauth) reference.

Let's now shift our focus to gadget/portlet communication with PubSub.

## Gadget/Portlet communication with PubSub

Have you ever wanted your gadgets to communicate with each other or with portlets? You can do so with *PubSub*. It is a messaging pattern in which publishers send messages to topics and subscribers receive the messages on those topics. Hence, the term PubSub is short for "Publish and Subscribe." PubSub is implemented in Liferay to facilitate interaction between gadgets and interaction between gadgets and portlets. PubSub is a very diverse messaging system that allows messages to be sent in the following manner:

-	gadget to gadget
-	portlet to gadget or vice versa
-	portlet to portlet

With the use of PubSub, the worlds of gadgets and portlets blend together facilitating their ability to communicate and interact with each other.

Publishers do not send messages to subscribers directly, but rather, they publish messages that are characterized into classes and sent across message channels (topics). Therefore, the publishers have no knowledge of *whom* they are sending their messages to; they simply broadcast the messages over a channel. So you may ask: "How do subscribers receive messages if the messages are not sent directly to them?" Subscribers express interest by subscribing to certain channels and receive messages sent to those channels. Like publishers, they have no knowledge of *who* sends the messages, they only know channels and receive messages coming in on those channels. This process makes it much easier for multiple gadgets and portlets to communicate with each other, without specifically stating who they want to send/receive data from.

PubSub can be compared to a TV station and your TV. The TV station does not directly send their material to your TV but, rather, broadcasts the material over a channel. You subscribe to that channel by tuning your TV to that channel to watch what's on it. This process of broadcasting and tuning into TV programs is similar to publishing and subscribing to messages sent via PubSub.

PubSub also offers the ability for portlets and gadgets to publish and subscribe to multiple channels. Although this may seem like a complex process, PubSub's use of message channels creates a network for gadgets to communicate across; and the network is easy to maintain and understand.

Let's try out PubSub for ourselves to explore how PubSub works. In this  fundamental example, we'll import two simple gadgets and send messages from one to the other. One acts as a publisher and the other as a subscriber. Follow the steps below:

1. Navigate to the Control Panel and select *OpenSocial Gadget Publisher* from under the *Portal* heading.

2. Select *Publish Gadget* and, for each gadget, enter the URL and click *Save*

	*Sample PubSub Publisher URL*: `http://svn.apache.org/repos/asf/shindig/trunk/content/container/sample-pubsub-2-publisher.xml`

	*Sample PubSub Subscriber URL*: `http://svn.apache.org/repos/asf/shindig/trunk/content/container/sample-pubsub-2-subscriber.xml`
	
3. Go back to a page on your site, navigate to *Add* &rarr; *More...*, and add your new gadgets to the page

4. Click *Publish a random number* on the PubSub Publisher gadget. You will notice it publishes a number; but the PubSub Subscriber gadget does not receive the number.

	![Figure 11.3: The subscriber cannot receive any messages from the publisher without being subscribed to the channel.](../../images/opensocial-23.png)

5. Select *Subscribe* on the subscriber gadget.

6. Click *Publish a random number* again from the publisher gadget. You now see the random number received by the subscriber.

	![Figure 11.4: When the subscriber is subscribed to the publisher's channel, the subscriber is able to receive messages.](../../images/opensocial-24.png)

7. Select *Unsubscribe* on the subscriber gadget.

8. Click *Publish a random number*.

	As you would expect, the subscriber portlet no longer receives the random number.

This simple example illustrates what PubSub does. It opens up a message channel through which publishers and subscribers can interact. Also, you probably noticed, while publishing and subscribing with your gadgets, there was no need to refresh the page. This is because the gadgets use *AJAX (Asynchronous JavaScript and XML)*. This technology refreshes your applications automatically, allowing PubSub to work efficiently and effectively throughout your Liferay Portal.

I bet you are wondering how to implement PubSub messaging in your gadgets and portlets. We'll dive into the code next. In fact, we'll complete exercises demonstrating gadget to gadget interaction and portlet to gadget interaction.

### Gadget to Gadget

For gadget to gadget communication, two independent gadgets are placed on a page and configured with PubSub. These two gadgets are able to communicate with one another and provide tools that the user could not otherwise produce. We will complete a simple example for gadget to gadget communication where two gadgets work together to display an address on Google Maps. The first gadget represents a publisher, which enables the user to input a specific address and publish the address. The second gadget represents the subscriber, which receives the address , displays the address, and locates the address on Google Maps. Follow the steps below:

1. Publish the *Google Address* and *Google Map* gadgets, as we had done previously with other gadgets. The URLs are given below:

	*Google Address URL*: `http://opensocialdeju.googlecode.com/svn-history/r31/GoogleMaps/GoogleMapsPublisher.xml`

	*Google Map URL*: `http://opensocialdeju.googlecode.com/svn-history/r31/GoogleMaps/GoogleMapsViewer.xml`

<!-- TODO rehost these xml's on liferay -->

2. Add both gadgets to a page.

	Your gadgets should appear like the snapshot below:

	![Figure 11.5: Gadgets are easy to publish and some gadgets, like Google's *Address* and *Map* gadgets, are automatically synced to communicate with each other.](../../images/opensocial-25.png)

	Note: The address bar is already filled with an address. This default address is specified in the *Google Address* gadget's source code. You can change the default address if you like.

3. Input an address into the *Address* bar and then click *Update*. You should now be able to see that address location displayed in the *Google Map* gadget.

	![Figure 11.6: After inputting a custom address in the *Google Address* gadget, you are able to see its physical location in the *Google Map* gadget.](../../images/opensocial-26.png)

Congratulations! Your gadgets are communicating well with each other. This simple example demonstrates two gadgets communicating with each other using PubSub. We will now dive into the source code and analyze how this interaction is accomplished.

First, we'll look at the contents of *Google Map*'s XML file  *GoogleMapsViewer.xml*:

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

The following excerpt from *Google Map*'s XML file enables the gadget to use PubSub and specifies the channel (topic) to which the gadget subscribes:

	<Require feature="pubsub-2">
		<Param name="topics">
			<![CDATA[ 
			<Topic title="Google Maps" name="com.liferay.opensocial.gmapsdemo" subscribe="true"/>
			]]>
		</Param>
	</Require>

Notice the opening `<Require feature="pubsub-2">`, mandating the `pubsub-2` feature for the gadget. The `<Param name="topics">` section establishes the topic `com.liferay.opensocial.gmapsdemo` that the gadget subscribes to. Within the *topics* parameter you define all the topics your gadget uses for communication. Furthermore, the `subscribe="true"` attribute specifies the gadget's role as a *subscriber* to the topic.

The following Javascript from the gadget's *content* registers a callback on the `com.liferay.opensocial.gmapsdemo` topic:

	gadgets.HubSettings.onConnect = function(hub, suc, err) {
            gadgets.Hub.subscribe("com.liferay.opensocial.gmapsdemo", callback);

The `gadgets.HubSettings.onConnect` function will be called by the OpenSocial container once the gadget connects to the PubSub messaging hub. In our example the gadget will subscribe to the previously mentioned topic. All subscribers to this topic receive messages sent to it.

When a message is received, the gadget's `callback()` function is executed. In this example, the callback method sends the received message (the address sent by the publisher) and calls `GClientGeocoder.getLocations()` to get the locations. And finally, the locations are processed and displayed on the map.

In summary, subscriber gadgets need to specify a topic and register a callback function on that topic to handle the messages they receive.

Next, we'll analyze the example's *publishing* gadget's source code specified in  *GoogleMapsPublisher.xml*:

<!-- TODO reverse the order here. Put the publisher first then the subscriber. -->

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

The publisher source code is similar to that of the subscriber. It mandates the `pubsub-2` feature for the gadget and specifies `com.liferay.opensocial.gmapsdemo` as one of its topics, as you would expect. Of course, the only difference is the fact that this gadget publishes to the topic, hence, the attribute setting `publish="true"`.

In the Javascript of the gadget's *content*, the gadget publishes to the topic with the following invocation:

	gadgets.Hub.publish("com.liferay.opensocial.gmapsdemo", address);

When the user clicks the gadget's *Publish* button, the message is sent by the publishing gadget to the topic named `com.liferay.opensocial.gmapsdemo`. PubSub broadcasts the message received on that topic to all subscribers, such as your *Google Address* gadget. Each subscriber receives and processes the message. In the case of your *Google Map* gadget, the message, containing an address, is processed by the gadget to show the address location on its map.

As you can see, PubSub allows your site to run efficiently and enables otherwise unconnected gadgets to communicate and flourish within Liferay Portal.

Gadgets are not limited to only communicating with other gadgets. In the next section, we will demonstrate the capabilities of communication between portlets and gadgets.

### Communicating between portlets and gadgets

For this section, we will continue implementing the *Google Map* gadget in your site. It would be convenient for a user to, not only implement "gadget to gadget" communications, but to also allow communications between those gadgets and your portlets.

As an exercise, we will send messages from Liferay's *Directory* portlet to the *Google Map* gadget. For each user listed in the *Directory* portlet, we will create a link that, when selected, will display the location of the user's address in the *Google Map* gadget. Please follow the instructions below:

1. Add the *Directory* portlet and *Google Map* gadgets to your page.

2. Backup the `addresses.jsp` file found in `webapps\ROOT\html\portlet\directory\user\addresses.jsp`, copying it to a location outside of your Liferay installation.

3. Replace the content of your installation's `addresses.jsp` with the following code:

		<%@ include file="/html/portlet/directory/init.jsp" %>

		<%
		List<Organization> organizations = (List<Organization>)request.getAttribute("user.organizations");

		String className = (String)request.getAttribute("addresses.className");
		long classPK = (Long)request.getAttribute("addresses.classPK");

		List<Address> personalAddresses = Collections.emptyList();
		List<Address> organizationAddresses = new ArrayList<Address>();

		if (classPK > 0) {
			personalAddresses = AddressServiceUtil.getAddresses(className, classPK);
		}

		for (int i = 0; i < organizations.size(); i++) {
			try {
				organizationAddresses.addAll(AddressServiceUtil.getAddresses(Organization.class.getName(), organizations.get(i).getOrganizationId()));
			}
			catch (Exception e) {
			}
		}
		%>

		<c:if test="<%= !personalAddresses.isEmpty() || !organizationAddresses.isEmpty() %>">
			<h3><liferay-ui:message key="address" /></h3>

			<c:if test="<%= !organizationAddresses.isEmpty() %>">
				<div>
					<h4><liferay-ui:message key="organization-address" /></h4>

					<ul class="property-list">

					<%
					for (Address address: organizationAddresses) {
						String street1 = address.getStreet1();
						String street2 = address.getStreet2();
						String street3 = address.getStreet3();

						String city = address.getCity();
						String region = address.getRegion().getName();
						String zipCode = address.getZip();

						String mailingName = LanguageUtil.get(pageContext, address.getType().getName());
					%>

						<li class="<%= address.isPrimary() ? "primary" : "" %>">
							<em class="mailing-name"><%= mailingName %></em>

							<c:if test="<%= Validator.isNotNull(street1) %>">
						<%= HtmlUtil.escape(street1) %><br />
						</c:if>

						<c:if test="<%= Validator.isNotNull(street2) %>">
						<%= HtmlUtil.escape(street2) %><br />
						</c:if>

						<c:if test="<%= Validator.isNotNull(street3) %>">
						<%= HtmlUtil.escape(street3) %><br />
						</c:if>

						<c:if test="<%= Validator.isNotNull(city) %>">
						<%= HtmlUtil.escape(city) %>
						</c:if>

						<c:if test="<%= Validator.isNotNull(region) %>">
						<%= region %>,
						</c:if>

						<c:if test="<%= Validator.isNotNull(zipCode) %>">
						<%= zipCode %>,
						</c:if>

						<c:if test="<%= address.isMailing() %>">(<liferay-ui:message key="mailing" />)</c:if>
					</li>

				<%
				}
				%>

				</ul>
			</div>
		</c:if>

		<c:if test="<%= !personalAddresses.isEmpty() %>">
			<div>
				<h4><liferay-ui:message key="personal-address" /></h4>

				<ul class="property-list">

				<%
				for (Address address: personalAddresses) {
					String street1 = address.getStreet1();
					String street2 = address.getStreet2();
					String street3 = address.getStreet3();

					String city = address.getCity();
					String region = address.getRegion().getName();
					String zipCode = address.getZip();

					String mailingName = LanguageUtil.get(pageContext, address.getType().getName());

					String addressFormatted = street1 + " " + city + ", " + region + " " + zipCode;
				%>

					<li class="<%= address.isPrimary() ? "primary" : "" %>">
						<em class="mailing-name"><%= mailingName %></em>
						<c:if test="<%= Validator.isNotNull(street1) %>">
						<%= HtmlUtil.escape(street1) %><br />
						</c:if>

						<c:if test="<%= Validator.isNotNull(street2) %>">
						<%= HtmlUtil.escape(street2) %><br />
						</c:if>

						<c:if test="<%= Validator.isNotNull(street3) %>">
						<%= HtmlUtil.escape(street3) %><br />
						</c:if>

						<c:if test="<%= Validator.isNotNull(city) %>">
						<%= HtmlUtil.escape(city) %>
						</c:if>

						<c:if test="<%= Validator.isNotNull(region) %>">
						<%= region %>
						</c:if>

						<c:if test="<%= Validator.isNotNull(zipCode) %>">
						<%= zipCode %>,
						</c:if>

						<c:if test="<%= address.isMailing() %>">(<liferay-ui:message key="mailing" />)</c:if>

						<br />

						<a href='#' onClick='publishAddress("<%=addressFormatted %>");'>Show in Google Maps</a>
					</li>

				<%
				}
				%>

				</ul>
			</div>
		</c:if>
		</c:if>

		<script type="text/javascript">
			function publishAddress(address) {
				Liferay.fire('gadget:com.liferay.opensocial.gmapsdemo', address);
			}
		</script>

3. Refresh your page and select a user with an address from your *Directory* portlet. You should now have a *Show in Google Maps* link under their address. 

4. Click the *Show in Google Maps* link. The address location should be displayed in the *Google Map* gadget.

	![Figure 11.7: Your modified *Directory* portlet sends a user address to the *Google Map* gadget to display the address location in its map.](../../images/opensocial-27.png)

	Your portal users can now map addresses straight from the *Directory* portlet!

Next, you will learn why the code changes were made to make this portlet to gadget communication possible.

As you know from explanations given earlier, the *Google Map* gadget is distinguished as a subscriber. Therefore, the *Directory* portlet needed to take on a publisher role to enable communication. To enable the *Directory* portlet to publish to the topic on which the *Google Map* gadget was subscribed, we inserted the following Javascript into the JSP file:

	<script type="text/javascript">
		function publishAddress(address) {
			Liferay.fire('gadget:com.liferay.opensocial.gmapsdemo', address);
		}
	</script>

This code involves a `publishAddress()` function which is called whenever you click on the *Show in Google Maps* link. The function invokes the `Liferay.fire()` function passing in the name of the topic receiving the message and the user's address as the message. One thing to note is that when a portlet is sending data to a gadget, there must be a `gadget:` prefix before the topic declaration. This distinguishes who the messages are intended for when they are broadcast across a topic. Notice that you did not need to change anything for your *Google Map* gadget, since it was already subscribed to that topic. You only needed to define the *Directory* portlet as a publisher to that topic.

---

![](../../images/tip-pen-paper.png)**Note:** If you would like to broadcast messages to portlets, follow the same guidelines, but do not use the `gadget:` prefix in your topic parameter value for the call to the `Liferay.fire()` function.

---

Communicating between gadgets and portlets within your site can enhance your portlets and offers a plethora of different options you can incorporate in your portal.

We will now switch gears and dive into Liferay's gadget editor!

## Using the gadget editor

As part of Liferay's OpenSocial integration, the *OpenSocial Gadget Editor* is included with Liferay Portal. The gadget editor is a complete development environment for gadgets providing syntax highlighting, preview functionality, undo/redo options, and built in tabs for working on multiple gadgets at the same time. You can also organize and manage gadgets easily using the editor's file manager. All of this gives you the convenience of creating and improving your gadgets right from within Liferay Portal.

Within the editor, each gadget's XML file has a wrench icon allowing you to close, rename, delete, or publish the gadget, or to simply show the gadget's URL. The *Publish* button redirects you to a screen, similar to the *OpenSocial Gadget Publisher*, allowing you to publish your gadget. Gadgets published through the editor will be stored in the site's Document and Media Library. The *Show URL* button allows you to add the gadget "adhoc" and share with other sites. These options offer a user-friendly and easy to use testing station for enhancing the gadgets on your sites.

![Figure 11.8: The wrench icon can be easily found to the right of your XML file.](../../images/opensocial-39.png)

For a brief exercise, we will improve the *Google Map* gadget using the gadget editor. As you have probably noticed, the gadget offers a small screen that is sometimes hard to view. By using the *OpenSocial Gadget Editor*, you can edit the XML file and specify a more desirable size for the gadget's UI.

1. You will need to copy the gadget XML contents into the gadget editor. Navigate to *OpenSocial Gadget Publisher* from under the *Portal* heading and select the URL for *Google Map*.

2. Copy the XML content onto your clipboard.

3. Navigate to the *OpenSocial Gadget Editor* from the Control Panel and paste your clipboard contents into the gadget editor.

4. Click the floppy disk button to save your new gadget XML, naming your gadget "GoogleMapsViewer.xml". Press the green check button to save the file.

	![Figure 11.9: It is easy to insert gadget content into Liferay's *OpenSocial Gadget Editor* and save it as an OpenSocial gadget.](../../images/opensocial-31.png)

5. Select the *Preview* tab from the toolbar and you will be presented with a preview of your gadget. The snapshot below only displays part of the actual preview screen, but it illustrates the gadgets current limited viewing space.

	![Figure 11.10: This snapshot displays our limited view and dire need for an updated gadget.](../../images/opensocial-32.png)

6. Locate the following element in the gadget XML:

		<div id="map" style="width:100%;height:100%"></div>

	Then, change the `width` value to `50%` and the `height` value to `250%`.

		<div id="map" style="width:50%;height:250%"></div>
	
	Save the file.

7. Select the editor's *Preview* mode and click the refresh button.

	![Figure 11.11: The refresh button is easy to find in the upper right corner of the editor's *Preview* pane.](../../images/opensocial-34.png)

	As you would expect, the gadget now appears taller with less width, giving a more appropriate viewing space.

	![Figure 11.12: Your updated gadget is much easier to view, thanks to the editing you've done in Liferay's gadget editor.](../../images/opensocial-33.png)

8. Publish your gadget for portal-wide use by selecting the wrench icon next to the *GoogleMapsViewer.xml* file and clicking *Publish*.

	![Figure 11.13: You can publish your OpenSocial gadgets directly from Liferay's *OpenSocial Gadget Editor*.](../../images/opensocial-35.png)

	A *Publish Gadget* screen opens up, showing your gadget's URL and categories to select for your gadget.

	![Figure 11.14: A *Publish Gadget* window displays your gadget's URL and a host of categories for you to consider for your gadget.](../../images/opensocial-36.png)

9. Choose a category and click *Save*.

Your new gadget is now available for portal-wide use!

The *OpenSocial Gadget Editor* allows you to create and improve gadgets within the comfort of your own Liferay Portal instance.

You can facilitate the social interactions on your sites and increase your portal content's popularity across your social network by leveraging the power of OpenSocial gadgets.

<!-- 
Now, we'll demonstrate the usefulness of Liferay's social API.

## Implementing gadgets in Liferay's social API

TODO This was from another section. See if it can be used in this section.
In a sense, gadgets live in their own world because they are rendered by a separate web application called "Shindig." This is a rendering engine for OpenSocial which was created by Apache and is the reference container implementation Liferay uses.

We have talked extensively about how gadgets are social applications, but we have not yet used a gadget to communicate with a user's friends. Social networks like Facebook and Twitter give the user opportunities to view and share photos with their friends. These actions are also available and expanded with OpenSocial gadgets. Using Liferay's social API, friends can publish customizable gadgets to their pages that enhance user experience. This focus of customizing a social page to interact with others is exactly what made MySpace popular. Social networking thrives on gadgets that connect users and their interests. 

For an example of a gadget used in Liferay's social API, you will publish the *Friends' Albums* gadget that enables you to view and share your friends' photos on your personal page. To begin this example, you must have a friend who has posted pictures in their profile. Follow the steps below:

1. View your friend's public page and view a picture they uploaded in the *Documents and Media Display* portlet. For this example, a *Blue Coupe* is listed under the *Car for Sale!!!!!!!* folder:

	![Figure 11.15: A snapshot of your friend's uploaded photo located in their *Documents and Media Display* portlet.](../../images/opensocial-28.png)

2. To help your friend, you decide to also post this listing on your profile as well. To accomplish this, you will need to publish the *Friends' Albums* gadget.  Navigate to the Control Panel and select *OpenSocial Gadget Publisher* located under the *Portal* heading.

3. Select *Publish Gadget* and enter the URL: `http://opensocialdeju.googlecode.com/svn/MediaItems/MediaItemsFriends.xml`

4. Select a category you want your gadget to reside in and then press *Save*

5. Navigate to your public page and then select *Add* &rarr; *More...* &rarr; *Friends' Albums*. This gadget should appear like the snapshot below:

	![Figure 11.16: Your friends' albums appear within your gadget.](../../images/opensocial-29.png)

6. If you select *Car for Sale!!!!!!!* and *Blue Coupe*, you are presented with the photo from your friend's page.

	![Figure 11.17: You are able to see your friends photo album on your public page!](../../images/opensocial-30.png)

This example is a great indicator of how simple and useful gadgets can be within Liferay's social API. Liferay has a constantly improving set of social features which are nicely complimented with OpenSocial gadgets. The combination of the two offers a fun and useful social network.

-->

## Summary

OpenSocial gadgets offer a plethora of new features to Liferay that present new opportunities for your portal customization. You've learned the anatomy of a gadget, how to access third-party applications from a gadget, and gadget communication with PubSub. Also, you learned that Liferay's gadget editor makes editing and customizing your gadgets easier than ever. Take a deep breath and give your self a pat on the back, you're now a trained gadget guru!

<!-- TODO will need to add in social API content into conclusion -->

Next, we'll explore the seemingly mysterious world of plugin security management.

