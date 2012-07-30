# What is an OpenSocial gadget?

OpenSocial is a framework designed for creation of socially themed application 
programming interfaces (APIs). OpenSocial applications, called *gadgets*, are similar to portlets because they can be added to your page and used for a multitude of tasks. However, they are very different from portlets because of the way they work and run on your site. Gadgets are characterized as simple, widely available, and easy to deploy because they can be added to your site by simply providing a URL. You can also create an OpenSocial gadget using Liferay's OpenSocial development environment. Liferay's OpenSocial implementation of the OpenSocial API is standardized. This assures that gadgets that run in it meet the OpenSocial standard; therefore these gadgets will deploy and run on any other standard OpenSocial container.

OpenSocial, considered a "Social" application, has many differences when compared to the "Standard" application that are vital to a social networking site. Standard applications have general and user specific data, whereas social applications can share data within a defined network. This variation is a huge advantage when trying to communicate important information to a large group of people.

Let's shift our focus to the internal source code of gadgets and see how these applications are built.

## Anatomy of a gadget

To fully understand what gadgets do on Liferay Portal, we must understand how it works. The source code of a gadget is written in XML, therefore, a gadget is an XML document. The root element of a gadget is `<Module>`. Within this root element, there are three sections that make up the source code:

1. Gadget Preferences (required) - defines characteristics of the gadget. This section of the source code is wrapped with `<ModulePrefs>` tags.
2. User Preferences (optional) - stores configuration values for a given user and allows them to specify settings. This section is wrapped with `<UserPref>` tags.
3. Content Section (required) - defines the content of the gadget. This section is wrapped with `<Content>` tags.

The diagram below displays the structure of a gadget and its three main sections:

![Figure 15.1: A fundamental diagram of a gadget's anatomy.](../../images/15-opensocial-22.png)

You may ask "Where is OpenSocial gadget data saved?" Both preferences and AppData are stored in Expando. Expando is a superclass for data model definitions whose properties are determined dynamically. In addition, two Expando tables are created, one to hold per-gadget preferences and another for per-user AppData.

We will now dive into the first section of the gadget structure.

### Gadget Preferences

The gadget preferences (ModulePrefs) define characteristics of your gadget such as features that it uses, how it authenticates content, and how the gadget is displayed to the user and in a gadget directory. This section of the gadget's XML document is wrapped in `<ModulePrefs>...</ModulePrefs>` tags.

Some of the attributes usable by ModulePrefs are listed below along with brief definitions of each:

-	*title*: the default wording of the title bar located on your page and, if not specified, the directory as well
-	*directory_title*: if provided, this is the title used for the gadget's directory.
-	*title_url*: enables the title bar of your gadget to act as a link to a URL
-	*description*: an optional description of your gadget, used in the gadget directory
-	*author*: name of author which is listed in your gadget's directory
-	*author_email*: email of author which is listed in your gadget's directory
-	*height*: specifies a pixel height for your gadget by default
-	*screenshot*: a screenshot of your gadget to be used on your gadget's directory. It should be exactly 280 pixels wide by natural height of your gadget at this width.
-	*scrolling*: gives your gadget a scrollbar if the content exceeds the page capacity (must be set to "true")
-	*thumbnail*: a small thumbnail used in your gadget's directory. It should be exactly 120 pixels wide and 60 pixels high for maximum clarity, otherwise it will be stretched to this size.

Within this section, you can also specify dependencies for the gadget using the `<Require>` and `<Optional>` tags. If a feature is specified with `<Require>`, it will not render if the feature is unavailable. Likewise, if the feature is tagged as `<Optional>`, the gadget will render but the feature will not be included. An important example of these tags can be demonstrated by the use of the `<UserPref>` feature. If you want to include a `<UserPref>` section, you must enable it by including `<Require feature="setprefs"/>`. This is explained in more detail within the *User Preferences* section.

Also, `<ModulePrefs>` stores other important features that will be explained in more detail in later sections, which include the PubSub and OAuth features. The PubSub feature allows for communication between gadgets and portlets on the same page by allowing them to publish and subscribe on message channels. Furthermore, the OAuth feature provides secure connections between your portal and third-party sites. Of course, there are many other features that can be defined by these tags that can be useful throughout your gadget's source code.

Lastly, here is an example of what a `<ModulePrefs>` section looks like for the Google Calendar gadget:

	<ModulePrefs 
	title="__MSG_Google_Calendar__"
	description="__MSG_Ig_Gadget_Description__" 
	author="Google"
	author_email="calendar-support+gadgets+201204131@google.com"
	author_affiliation="Google Inc." author_location="Mountain View, CA and New York, NY" 
	height="447"
	title_url="https://www.google.com/calendar/__ENV_google_apps_auth_path__render"
	screenshot="//www.gstatic.com/ig/modules/calendar/calendar_content/calendar3.cache.png"
	thumbnail="//www.gstatic.com/ig/modules/calendar/calendar_content/calendar-thm.cache.png"
	category="communication" 
	category2="tools">
	<Require feature="views"/>
	<Require feature="setprefs"/>
	<Require feature="dynamic-height"/>

The weather gadget's full source code can be viewed here: `http://gadgets.weather.com/services/gadgets/googlegadget/gadget.xml`

Overall, this section allows you to setup your gadget on a directory and establish settings and tools that you will use throughout your gadget's source code.

### User Preferences

Some gadgets are configured with more options from which you can choose. These are called user preferences (UserPrefs) and are used by app developers to store configuration values for a given user of an application. This section of the XML document is wrapped with `<UserPref>...</UserPref>` tags. Users typically set the UserPrefs and these persist across a given instance of a gadget.  When working with gadgets on your page, they are somewhat different than what other users see. As you make changes in the gadget's interface, those changes are loaded onto the database as UserPrefs. When you reload your gadget, those UserPrefs are fed back to the gadget and are rendered in the user interface. 

An example of a gadget requiring UserPrefs is a weather gadget. You would not want to know the weather of an unknown, distant land, but rather, of your current residence. Therefore, a weather gadget would need to know your location. It would also be nice to have the temperature unit, theme, and name the way you would want it. UserPrefs allows these items and many others to be customizable to your liking.

In order to set preference values from within your gadget, you will need to include the `setprefs` feature. This feature is set inside the `<ModulePrefs>...</ModulePrefs>` tags, which was previously stated in the *Gadget Preferences* section. If you look within the weather gadget's source code, we can see the `setprefs` feature is included.

	<Require feature="setprefs"/>

You can specify `<UserPref>` elements for your gadget after the `</ModulePrefs>` element and before the first `<Content>` element. You can set properties which include:

-	*name* (required) - the internal key that your UserPrefs are stored against.
-	*display_name* - how the preference is presented to the user in the edit window (must be unique)
-	*default_value* - the default value for the preference
-	*datatype* - the type of content this preference stores
-	*required* - set to true if the preference needs to be given before the gadget can display, or if it cannot be empty (default is false)

For the *datatype* property, the possible values are `bool`, `enum`, `hidden`, `list`, and `string`. The default data type is `string`. Here are some explanations and examples for each data type:

-	`bool`: Sometimes there are two options to choose from. For instance, maybe you would want an option between displaying or not displaying a graphic. The `bool` data type allows you to choose between these two options, usually denoted true or false. Also, this data type also allows you to duplicate the same gadget and enable your site to display the data side by side. Here is an example of a `<UserPref>` section using the `bool` data type for displaying an icon:

		<UserPref name="myicon" display_name="Display Icon?" default_value="true" datatype="bool" />

-	`enum`: Sometimes, you want several options for a user to choose from. Maybe you want them to select a color or number from a dropdown menu. The `enum` data type enables a dropdown to display so the user has options to choose from. For an example of this data type, an option for a list of colors is given below:

		<UserPref name="mycolor" display_name="Color" default_value="Red" datatype="enum">
			<EnumValue value="Blue"/>
			<EnumValue value="Red"/>
			<EnumValue value="Yellow">
			<EnumValue value="Green">
			<EnumValue value="Orange">
		</UserPref>

-	`hidden`: This is a non-visible string that is not user editable. This means that you have this coded into your gadget, but is not visible on the browser. For this data type, you want to set it programmatically and store persistently. This would be useful when showing a map on our previously mentioned weather gadget. The code for this data type would look like this:

		<UserPref name="showMap" default_value="true" datatype="hidden"/>

-	`list`: The `list` data type is an array of values that are dynamically supplied by the user at start-up. This would be appropriate if the gadget needed multiple types of one option. An example of this would be for our previously stated weather gadget. You may want the weather of multiple zip codes, which would require a `list` data type to fulfill. Here is an example of what this may look like:

		<UserPref name="mylist" display_name="Add Zip Codes" datatype="list" required="true"/>

-	`string`: This is the default data type and is used for information that can be easily filled into a text window. It does not require a `datatype` to be specified in the `<UserPref>...</Userpref>`. Here is a simple example:

		<UserPref name="title" display_name="Gadget Title" default_value="My Awesome Gadget" />

Lastly, we will view the `<UserPref>` section within a task manager gadget and compare to what a user would see on your site. This task manager is called *Jira*.

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
   		<EnumValue value="7" display_value="7"/>
   		<EnumValue value="8" display_value="8"/>
   		<EnumValue value="9" display_value="9"/>
   		<EnumValue value="10" display_value="10"/>
   		<EnumValue value="11" display_value="11"/>
   		<EnumValue value="12" display_value="12"/>
   		<EnumValue value="13" display_value="13"/>
   		<EnumValue value="14" display_value="14"/>
   		<EnumValue value="15" display_value="15"/>
   		<EnumValue value="16" display_value="16"/>
   		<EnumValue value="17" display_value="17"/>
   		<EnumValue value="18" display_value="18"/>
   		<EnumValue value="19" display_value="19"/>
   		<EnumValue value="20" display_value="20"/>
   		<EnumValue value="21" display_value="21"/>
   		<EnumValue value="22" display_value="22"/>
   		<EnumValue value="23" display_value="23"/>
   		<EnumValue value="24" display_value="24"/>
   		<EnumValue value="25" display_value="25"/>
   		<EnumValue value="26" display_value="26"/>
   		<EnumValue value="27" display_value="27"/>
   		<EnumValue value="28" display_value="28"/>
   		<EnumValue value="29" display_value="29"/>
   		<EnumValue value="30" display_value="30"/>
   	</UserPref>
   	<UserPref name="displayIcons" display_name="Display icons?" default_value="true" datatype="bool" /> 
   	<UserPref name="refresh" display_name="Refresh (in minutes, 0-never)" default_value="5" />
   	<UserPref name="feedusername" display_name="Username" default_value="" />
   	<UserPref name="feedpassword" display_name="Password" default_value="" />
   	<UserPref name="useTooltip" display_name="Use Tooltip" default_value="true" datatype="bool" />

In addition, here is a snapshot of what this window looks like on Liferay Portal:

![Figure 15.2: The configuration screen displaying user preferences.](../../images/15-opensocial-21.png)

UserPrefs can be displayed in many different ways and helps your gadget become more adaptable to the ever changing expectations of the user. The extensive selection of `datatype` options allows for any type of user preference.

### Content Section

Every gadget will have a `<Content>` section, which stores content displayed to the user and JavaScript that is executed to build the UI. This section provides two content types, `html` and `url`, which determines three important characteristics in your gadget:

1. Which API features are available to you, the gadget author
2. How your gadget is rendered
3. Where you deploy your gadget

We will describe how the `html` content type is used and what it offers to your gadget. It is the default content type. This is due to the fact that it is the most flexible and is most frequently used. All of the code resides in the gadget specification, which includes HTML markup, JavaScript, and the gadget XML. With this content type, some rules must be followed for it to run correctly and accomplish the tasks you want. First, it must include a `CDATA` section and all your HTML must be placed within this section. This section would look something like this:

	<Content type="html">
		<![CDATA[ "Your HTML goes here" ]]>

Second, you are not allowed to use the `<body>`, `<head>`, or `<html>` tags. Gadgets generate these on their own. Of course, there are built-in mechanisms and APIs you can use to pull URL content which adds to even more versatility. 

Next, the `url` content type will be discussed on how it works and ways it can be useful in your gadget. When using this content type, the `href=` attribute provides a URL and all other gadget specifications are ignored. The gadget assumes that all information regarding programmatic logic and the user interface specifically resides in the given URL. Therefore, you do not need any HTML or JavaScript within the gadget. This type is convenient when you just want to reference the content within the URL and nothing more. Also, this content type has better consistency in the specification regarding proxied content. Here is a sample of what the `url` content type looks like inside of the Google Calendar gadget:

	<Content view="preview" type="url" href="https://www.google.com/calendar/__ENV_google_apps_auth_path__ig"/>
	<Content view="home" type="url" href="https://www.google.com/calendar/__ENV_google_apps_auth_path__ig"/>
	<Content view="canvas" type="url" href="https://www.google.com/calendar/__ENV_google_apps_auth_path__ig"/>

Both content types offer beneficial traits and each can be used effectively, depending on your preference and needs.

Now that we're fundamentally sound on the anatomy of a gadget, let's explore what gadgets can offer us in Liferay Portal.

## Supporting third-party applications

Liferay allows gadgets to be personalized by adding third-party applications to your site. Because these applications require different credentials, users are sometimes hesitant to share their private info with other sites. However, you can provide a richer user experience by allowing a means for the user to interact with protected resources from service providers within Liferay Portal. This process is possible by implementing *OAuth*. OAuth is often characterized as a "handshake mechanism" where, instead of requiring personal information, your site redirects the user to their service provider, such as Twitter. Then, they can tell Twitter to allow your site access to their account with limited resources. This is accomplished by acquiring request and access tokens as well as a token secret to authenticate the uses of your gadget. This process is explained in detail in Liferay's User Guide: <place link here>. There are many other service providers that use this mechanism, such as Evernote, Facebook, Google, Netflix, Photobucket, Yahoo and many more.

Your gadget must include certain calls and properties to enable OAuth to run effectively. Below, the requirements for OAuth are listed along with examples from a Twitter gadget that views a user's timeline:

-	A known service provider

-	An endpoint, or URL a gadget uses to access private data through the service provider

-	An `<OAuth>...</OAuth>` section within the `<ModulePrefs>` section of your gadget that specifies services and endpoints the gadget uses. The `<OAuth>` element of the gadget XML specifies agreement between Liferay Portal and the service provider.

		<OAuth>
			<Service name="twitter"> 
				<Request url="http://twitter.com/oauth/request_token" param_location="uri-query" /> 
				<Access url="http://twitter.com/oauth/access_token" param_location="uri-query" /> 
				<Authorization url="http://twitter.com/oauth/authorize" /> 
			</Service> 
		</OAuth>

-	A call to the gadget's `makeRequest()` function with correct OAuth parameters to fetch authenticated data. For this example, when the gadget runs, it calls `gadgets.util.registerOnLoadHandler(fetchData)`, which invokes the `fetchData()` function. The `fetchData()` function calls `makeRequest()`. Then the `makeRequest()` function specifies callback parameters. When the callback is invoked, OAuth fields are returned in addition to the usual values by `makeRequest()`. Here is an explanation from within the gadget:

		// Invoke makeRequest() to fetch data from the service provider endpoint.
		// Depending on the results of makeRequest, decide which version of the UI
		// to ask showOneSection() to display. If user has approved access to his
		// or her data, display data.
		// If the user hasn't approved access yet, response.oauthApprovalUrl contains a
		// URL that includes a Google-supplied request token. This is presented in the 
		// gadget as a link that the user clicks to begin the approval process.

	Also, here is code from the gadget using the `fetchData()` function which, in turn, calls `makeRequest()`:

		function fetchData() {
			var url = "http://api.twitter.com/1/account/verify_credentials.json";
			var callback = function(response) {
				if (response.oauthApprovalUrl) {
					// Create the popup handler. The onOpen function is called when the user
					// opens the popup window. The onClose function is called when the popup
					// window is closed.
					var popup = shindig.oauth.popup({
						destination : response.oauthApprovalUrl,
						windowOptions : null,
						onOpen : function() {showOneSection('waiting');},
						onClose : function() {fetchData();}
					});

					// Use the popup handler to attach onclick handlers to UI elements.  The
					// createOpenerOnClick() function returns an onclick handler to open the
					// popup window.  The createApprovedOnClick function returns an onclick 
					// handler that will close the popup window and attempt to fetch the user's
					// data again.
					document.getElementById('personalize').onclick = popup.createOpenerOnClick();
					document.getElementById('approvalcomplete').onclick = popup.createApprovedOnClick();

					showOneSection('approval');
				} 
				else if (response.data) {              
					var screenName = response.data.screen_name;

					document.getElementById('heading').innerHTML = 'Showing last ' + tweetNum + ' tweets from ' + screenName + '\'s timeline';

					showTimeline();
				} 
				else {
					showError(response);
				}
			};

			var params = {};

			params[gadgets.io.RequestParameters.CONTENT_TYPE] = gadgets.io.ContentType.JSON;
			params[gadgets.io.RequestParameters.AUTHORIZATION] = gadgets.io.AuthorizationType.OAUTH;
			params[gadgets.io.RequestParameters.OAUTH_SERVICE_NAME] = "twitter";
			params[gadgets.io.RequestParameters.METHOD] = gadgets.io.MethodType.GET;

			gadgets.io.makeRequest(url, callback, params);
		}

-	An execution for detecting whether the user has approved access to the gadget's data. If the user cannot access the data, the gadget must provide a way to visit the service provider.

		// If user hasn't approved access to data, provide a "Personalize this gadget" link
		// that contains the oauthApprovalUrl returned from makeRequest.
		//
		// If the user has opened the popup window but hasn't yet approved access, display
		// text prompting the user to confirm that s/he approved access to data.  The user
		// may not ever need to click this link, if the gadget is able to automatically
		// detect when the user has approved access, but showing the link gives users
		// an option to fetch their data even if the automatic detection fails.
		//
		// When the user confirms access, the fetchData() function is invoked again to
		// obtain and display the user's data.
		function showOneSection(requestedSection) {
			var sections = ['approval', 'error', 'main', 'waiting'];

			for ( var i = 0; i < sections.length; ++i) {
				var section = sections[i];

				var element = document.getElementById(section);

				if (section === requestedSection) {
					element.style.display = "block";
				}
				else {
					element.style.display = "none";
				}
			}
		}

-	Code for processing the returned data

		function showTimeline() {          
			var url = "http://api.twitter.com/1/statuses/home_timeline.json" + "?count=" + tweetNum;

			var callback = function(response) {
				if (response.data) {
					populateTweetsList(response.data);

					showOneSection('main');

					gadgets.window.adjustHeight();
				} 
				else {
					showError(response);          
				}
			};

			var params = {};

			params[gadgets.io.RequestParameters.CONTENT_TYPE] = gadgets.io.ContentType.JSON;
			params[gadgets.io.RequestParameters.AUTHORIZATION] = gadgets.io.AuthorizationType.OAUTH;
			params[gadgets.io.RequestParameters.OAUTH_SERVICE_NAME] = "twitter";
			params[gadgets.io.RequestParameters.METHOD] = gadgets.io.MethodType.GET;

			gadgets.io.makeRequest(url, callback, params);
		}

OAuth gives your website freedom to incorporate popular sites, like Twitter, to enhance your site and give freedom and peace of mind to your users. The twitter gadget's source code can be viewed in its entirety at:

	http://opensocialdeju.googlecode.com/svn-history/r15/Twitter/TwitterDemo.xml

The OAuth client dramatically improves your gadget's security and gives your users peace of mind when accessing their private resources on your site.

We will now shift our focus to inter-gadget communication.

## Inter-gadget communication

Have you ever wondered how portlets communicate to each other? An even more intriguing question is how gadgets communicate with each other or with portlets. All of these questions can be answered with one word: PubSub. This is a messaging pattern where messages are sent from publishers and received from subscribers. Hence, the term PubSub means "Publish-Subscribe." PubSub was implemented to allow interaction between portlets and gadgets. Gadgets, in a sense, live in their own world because they are rendered by a separate web application called "Shindig." This is a rendering engine for OpenSocial which was created by Apache and is the reference container implementation Liferay uses. With the use of PubSub, the worlds of gadgets and portlets are able to communicate and interact with each other. Overall, PubSub is a very diverse messaging system allowing interaction between the following:

-	portlet to portlet
-	gadget to gadget
-	portlet to gadget or vice versa

Publishers do not specifically send messages to a certain subscriber but, rather, publish messages that are characterized into classes and sent via message channel. Therefore, they have no knowledge of where they are sending their messages to but just simply broadcast them over a channel. So you may ask: "How do subscribers receive messages if they are not sent to them?" Subscribers express interest in certain classes and only receive messages that are related to their expressed interest. Like publishers, they don't have knowledge of who they are subscribing to but, only know what they are subscribing for. This process makes it much easier for multiple gadgets and portlets to communicate with each other without specifically stating who they want to send/receive data from.

PubSub can be compared to a TV station and your TV. The TV station does not directly send their material to your TV but, rather, broadcasts the material over a channel. You subscribe to that channel by turning you TV channel to what you want to watch. The process TV's use to communicate with TV stations is similar to how PubSub regulates communication within your site.

PubSub also offers the ability for portlets and gadgets to publish and subscribe to multiple channels. Although this may seem like a complex process, PubSub's use of message channels creates a network for gadgets to communicate across that is easy to maintain and understand.

For a fundamental example of how PubSub works, we will import two simple portlets and convey how messages are sent and received. One acts as a publisher portlet and the other as a subscriber portlet. Follow the steps below:

1. To publish the two portlets, navigate to the Control Panel and select *OpenSocial Gadget Publisher* from under the *Portal* heading.

2. Select *Publish Gadget* and, for each portlet, enter in the URL and click *Save*

	*Sample PubSub Publisher*: http://svn.apache.org/repos/asf/shindig/trunk/content/container/sample-pubsub-2-publisher.xml

	*Sample PubSub Subscriber*: http://svn.apache.org/repos/asf/shindig/trunk/content/container/sample-pubsub-2-subscriber.xml

3. Go back to the home page and navigate to *Add* &rarr; *More...* and add your two portlets to the page

4. First, click *Publish a random number* on the publisher portlet. You will notice it publishes a number but the subscriber portlet does not receive a random number.

	![Figure 15.3: The publisher portlet in action!](../../images/15-opensocial-23.png)

5. Now, select *Subscribe* on the subscriber portlet and click *Publish a random number* again. You now see the random number is received by the subscriber.

	![Figure 15.4: The subscriber portlet in action!](../../images/15-opensocial-24.png)

6. Now select *Unsubscribe* and click *Publish a random number*. The subscriber portlet no longer receives the random number, as you would expect.

This simple example further illustrates what PubSub does. It creates a message channel to which publishers and subscribers can interact. Also, you probably noticed that while publishing and subscribing with your portlets, there was no need to refresh the page. This is because these applications use *AJAX*. This acronym stands for *Asynchronous JavaScript and XML and auto refreshes your applications. These simple processes allow PubSub to work efficiently and effectively throughout your Liferay Portal.

Next, we will complete an example for each type of gadget interaction and learn what is going on within the source code.

### Gadget to Gadget

For Gadget to gadget communication, two independent gadgets are placed on a page and configured with PubSub. These two gadgets are able to communicate with one another and provide tools that the user could, otherwise, not produce. First, we will complete a simple example for gadget to gadget communication where two gadgets work together to display an address on Google Maps. The first gadget will represent a publisher, which enables the user to input a specific address and publish. The second gadget will represent the subscriber, which receives the address and displays it on Google Maps. Follow the steps below:

1. Add the *Google Address* and *Google Map* gadgets onto your page by following the instructions from past examples. The URLs are given below:

	*Google Address*: http://opensocialdeju.googlecode.com/svn-history/r31/GoogleMaps/GoogleMapsPublisher.xml

	*Google Map*: http://opensocialdeju.googlecode.com/svn-history/r31/GoogleMaps/GoogleMapsViewer.xml

	Your gadgets should appear like the snapshot below:

	![Figure 15.5: A snapshot of the Google gadgets.](../../images/15-opensocial-25.png)

		Note: The address bar is already filled with an address. This is the set default address and can be changed within *Google Address*'s source code.

2. Input an address into the *Address* bar and then click *Update*. You should now be able to see your chosen address displayed in the *Google Map* gadget.

	![Figure 15.6: After inputing a custom address, you are able to see its physical location.](../../images/15-opensocial-26.png)

This is a simple example of two separate gadgets that communicate with each other using PubSub. We will now dive into the source code and analyze how it accomplishes this. First, we will view the *Google Map*'s XML file named *GoogleMapsViewer.xml*. The source code for this file is listed below:

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

				var map = new GMap2(_gel("map"));

				map.setCenter(new GLatLng(43, -100), 3);

				gadgets.window.adjustHeight();
			</script>
			]]>
		</Content>
	</Module>

A requirement that enables your gadget to communicate with other gadgets and use PubSub is listed below:

	<Require feature="pubsub-2">
		<Param name="topics">
			<![CDATA[ 
			<Topic title="Google Maps" name="com.liferay.opensocial.gmapsdemo" subscribe="true"/>
			]]>
		</Param>
	</Require>

Notice the `<Require feature="pubsub-2">` tag, which is mandatory for PubSub to run in your gadget. The `<Param name="topics">` section establishes the channel `com.liferay.opensocial.gmapsdemo` that it will subscribe to. This section is where you define all the channels your gadget is using for communication. Furthermore, the `subscribe="true"` line implements the role of "Subscriber" to your gadget. The code that is actually subscribing is the content:

	gadgets.HubSettings.onConnect = function(hub, suc, err) {
            gadgets.Hub.subscribe("com.liferay.opensocial.gmapsdemo", callback);

Your gadget calls `gadgets.HubSetting.onConnect` which enables the gadget to subscribe to our previously mentioned channel. All publishers and subscribers using this channel will receive each others messages. After the channel, the callback is listed which redirects you to the `function callback()` method. Finally, this method takes the received data and processes it into the map.

In summary to the breakdown of source code, subscriber gadgets need a channel and callback method to effectively receive and process data.

Next, you will analyze the publishing gadget, or *GoogleMapsPublisher.xml*. Here is the source code for this gadget:

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

The publisher source code runs similarly to your previous subscriber. It runs the same `<Require feature="pubsub-2">` tag and is using the same channel, as you would expect. Of course, the only difference is the fact that the gadget publishes to the channel, hence, the `publish="true"` statement. The executed call is listed below:

	gadgets.Hub.publish("com.liferay.opensocial.gmapsdemo", address);

It specifies our communication channel and then reads your address value that you gave in the browser. Finally, the address is published on the channel, which is then received by our subscriber. 

Overall, PubSub allows your site to run efficiently and enables, otherwise, unconnected gadgets to communicate and flourish within Liferay Portal.

###Portlet to Gadget (or vice versa)

For this section, we will continue implementing the *Google Map* gadget in our site. It would be convenient for a user to, not only implement "gadget to gadget" communications, but to also allow communications between those gadgets and their already existing portlets. For this example, we will link Liferay's *Directory* portlet with the previously mentioned *Google Map* gadget. Our goal is to create a link for an individual's address in the *Directory* portlet which will, then, send the address information to our *Google Map* gadget. Please follow the instructions below:

1. First, you will need to add the *Directory* portlet and *Google Map* gadget to your page. To do this, navigate to *Add* &rarr; *More...* and select these from the menu.

2. You will need to update the `addresses.jsp` file to create a link for mapping in our *Directory* portlet. This file can be found by navigating to the `tomcat-<version>` directory located within your Liferay Portal instance. Then, navigate to `webapps\ROOT\html\portlet\directory\user\addresses.jsp` and replace your current content with the code below:

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

3. Refresh the page and select a user with an address from your *Directory* portlet. You should now have a *Show in Google Maps* link under their address. Click this link and the location should be illustrated on the *Google Map* gadget. This should appear similarly to the snapshot below:

	![Figure 15.7: A snapshot of the *Directory* portlet and *Google Map* gadget.](../../images/15-opensocial-27.png)

You can now map addresses from the directory! The use of gadgets within your site enhances portlet use and offers a plethora of different options you can incorporate into Liferay Portal. Next, you will learn why the code changes were made to make this portlet to gadget communication possible.

As you know from explanations given earlier, the *Google Map* gadget is distinguished as a subscriber. Therefore, the *Directory* portlet needed to be transformed into a publisher role to enable communication. For this, the code listed below was inserted into the JSP file:

	<script type="text/javascript">
		function publishAddress(address) {
			Liferay.fire('gadget:com.liferay.opensocial.gmapsdemo', address);
		}
	</script>

This code involves a `publishAddress()` function which is called whenever you click on the *Show in Google Maps* link. The line below the function sends out two methods, the channel and whatever you're sending across. One thing to note is that when a portlet is sending data to a gadget, there must be a `gadget:` prefix before the channel declaration. This distinguishes who the messages are intended for when they are broadcast across a channel. Also, if you would like to broadcast the messages to portlets, you can add a similar line, but with a removed `gadget:` prefix. Notice that you did not need to change anything for your *Google Map* gadget. Since it is already subscribed to a channel, you only needed to define a publisher to send messages across that channel.

We will not switch gears and dive into Liferay's gadget editor!

## Using the gadget editor

OpenSocial gadgets are XML documents, so as part of Liferay's OpenSocial integration, a gadget editor is included. The gadget editor is a complete development environment for gadgets providing syntax highlighting, a preview function, undo/redo options, and built in tabs for working on multiple gadgets at once. You can also organize and manage gadgets through a simple file manager embedded into the portlet. This gives you the opportunity to edit and improve your XML files within Liferay Portal.

Each gadget's XML file has a wrench icon enabling you to close, rename, delete, publish, or show the URL of the file. In detail, the *Publish* button redirects you to a screen similarly found in *OpenSocial Gadget Publisher* which allows you to publish your updated gadget. This feature creates a new gadget and leaves your former gadget available as well. The *Show URL* button allows you to add the gadget "adhoc" and share with other sites. These options offer a user-friendly and easy testing station for enhancing the gadgets on your site.

For a brief exercise, you will improve the *Google Map* gadget. As you probably noticed, the gadget offered a small screen that was sometimes hard to view. By using *OpenSocial Gadget Editor*, you can edit the XML file and choose the appropriate size you desire. Follow the steps below:

1. First, you will need to copy the XML file contents into our editor. Navigate to *OpenSocial Gadget Publisher* from under the *Portal* heading and select the URL for *Google Map*

2. Copy the content onto your clipboard

3. Next, navigate to *OpenSocial Gadget Editor* from the Control Panel. Paste the contents from your clipboard onto the blank page.

4. Click the floppy disk to save. Name your gadget "GoogleMapsViewer.xml" and press the green check.

	![Figure 15.8: How to insert a gadget into Liferay's *OpenSocial Gadget Editor*.](../../images/15-opensocial-31.png)

5. Select the *Preview* tab from the toolbar and you will be presented with a preview of the current gadget. The snapshot below only displays part of the actual preview screen, but exemplifies your limited view:

	![Figure 15.9: A preview of the *Google Map* gadget.](../../images/15-opensocial-32.png)

6. Within the source code, you will need to make a slight change to the code below:

	`<div id="map" style="width:100%;height:100%"></div>`

	Please change the width parameter to `width:50%` and height parameter to `height:250%`. Then save the file.

7. Navigate back to your preview screen and click the refresh button. The gadget should appear taller with less width, as you would expect.

	![Figure 15.10: The refresh button located on the *Preview* window.](../../images/15-opensocial-34.png)

	![Figure 15.11: The *Preview* window displays your updated gadget!](../../images/15-opensocial-33.png)

8. Next, to publish the gadget for portal-wide use, select the wrench icon next to the *GoogleMapsViewer.xml* file and select *Publish*.

	![Figure 15.12: How to publish the updated gadget.](../../images/15-opensocial-35.png)

9. A *Publish Gadget* screen will open with the URL for your new gadget and a choice for your gadget's category. After choosing a category, click *Save*.

	![Figure 15.13: A snapshot of the *Publish Gadget* window.](../../images/15-opensocial-36.png)

Your new gadget is now available for portal-wide use! The gadget editor allows you to create and improve social interactions within the comfort of your own Liferay Portal. This enables you to leverage their power and popularity for your portal's content.

Now we'll demonstrate how useful gadgets are in Liferay's social API.

## Implementing gadgets in Liferay's social API

We have talked extensively about how gadgets are social applications, but we have not yet used a gadget to communicate with a user's friends. Social networks like Facebook and Twitter give the user opportunities to view and share photos with their friends. These actions are also available and expanded with OpenSocial gadgets. Using Liferay's social API, friends can publish customizable gadgets to their pages that enhance user experience. This focus of customizing a social page to interact with others is exactly what made MySpace popular. Social networking thrives on gadgets that connect users and their interests. 

For an example of a gadget used in Liferay's social API, you will publish the *Friends' Albums* gadget that enables you to view and share your friends' photos on your personal page. To begin this example, you must have a friend who has posted pictures in their profile. Follow the steps below:

1. First, view your friend's public page and view a picture they uploaded in the *Documents and Media Display* portlet. For this example, a *Blue Coupe* is listed under the *Car for Sale!!!!!!!* folder:

	![Figure 15.14: A snapshot of your friend's uploaded photo located in their *Documents and Media Display* portlet.](../../images/15-opensocial-28.png)

2. To help your friend, you decide to also post this listing on your profile as well. To accomplish this, you will need to publish the *Friends' Albums* gadget.  Navigate to the Control Panel and select *OpenSocial Gadget Publisher* located under the *Portal* heading.

3. Next, select *Publish Gadget* and enter the URL: `http://opensocialdeju.googlecode.com/svn/MediaItems/MediaItemsFriends.xml`

4. Select a category you want your gadget to reside in and then press *Save*

5. Navigate to your public page and then select *Add* &rarr; *More...* &rarr; *Friends' Albums*. This gadget should appear like the snapshot below:

	![Figure 15.15: Your friends' albums should appear within your gadget.](../../images/15-opensocial-29.png)

6. If you select *Car for Sale!!!!!!!* and *Blue Coupe*, you are presented with the photo from your friend's page.

	![Figure 15.16: You are able to see your friends photo album on your public page!](../../images/15-opensocial-30.png)

This example is a great indicator of how simple and useful gadgets can be within Liferay's social API. Liferay has a constantly improving set of social features which are nicely complimented with OpenSocial gadgets. The combination of the two offers a fun and useful social network.
