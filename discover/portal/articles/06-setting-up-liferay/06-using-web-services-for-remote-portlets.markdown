# Using Web Services for Remote Portlets (WSRP) [](id=using-web-services-for-remote-portlets-wsrp)

The Web Services for Remote Portlets (WSRP) specification defines a web service
interface for accessing and interacting with presentation-oriented web services
in the form of portlets. What are presentation-oriented web services? These are
web services that send user interfaces over the wire, rather than raw data like
JSON objects or SOAP data envelopes. If an application is written as a portlet,
this is an easy way to expose that application to end users on a completely
different system, rather than sending just the data and having to craft an
application to present that data. WSRP's presentation-oriented web services 
allow portals to display remote portlets inside their pages, as if locally
deployed, without requiring any additional programming by developers.

Here are the two main components for WSRP:

*Producer:* A web service that exposes one or more portlets and is described
using a Web Services Description Language (WSDL) document.

*Consumer:* A web service client that receives the data from the Producer and
presents it to the user in a portlet window. 

Below, you'll see how the components interact with each other. So without
further ado, let's explore WSRP in @product@!

## WSRP with Liferay [](id=wsrp-with-liferay)

@product@ provides a deployable WSRP portlet that supports the 1.0 and 2.0
specifications. The portlet is available from Liferay Marketplace as a CE or EE
app. Once you've downloaded and installed the WSRP app, you have instant access
to the portlet by navigating to the Control Panel and, under *Apps*, selecting
*WSRP Admin*.

![Figure 1: Once deployed, you can find the WSRP Admin portlet in the Control Panel.](../../images/wsrp-admin-control-panel.png)

@product@ can be used as a WSRP producer or consumer. As a producer, it
hosts portlets that are consumed by other portal servers (Liferay or
non-Liferay) acting as WSRP consumers. The image below illustrates WSRP
producers and consumers and how they interact.

![Figure 2: Portlets can interact with other portlets located on a different portal server using WSRP.](../../images/wsrp-illustration.png)

As mentioned in the previous chapter, there are two main components of the WSRP 
process: producers and consumers. Let's go through the basic process of how 
producers and consumers work together to bring the end user a remote portlet. 
First, the consumer portal server establishes a connection with its producer
portal server counterpart. This connection is made possible by giving the
consumer the producer portlet's URL. The consumer then uses the URL to
discover the producer's portlet and establish a connection. After the connection
is made, the consumer acquires the producer's information and creates a consumer
proxy portlet. The proxy portlet acts as an intermediary, relaying requests to
and from the end user and the producer portlet.

For example, you can compare the proxy portlet to a TV satellite box. If you
want to change the channel on your TV, you (end user) send the channel number
you desire to the TV's satellite box (consumer's proxy portlet) via your TV's
remote. When the satellite box receives the request to change the channel, it
relays the request to a TV satellite (producer's portlet) which then sends the
channel information back to the satellite box. Then, the satellite box displays
the new channel to you on your TV. In this simple example, you're not directly
requesting the TV satellite to change the channel, but rather, you're
communicating with the satellite box, which acts as an intermediary between you
and the satellite. This example directly relates to using WSRP with Liferay.
Although the end users are sending requests to the consumer portlet, they're not
receiving feedback from the consumer portlet itself, but rather its producer
portlet located remotely.

Now that you know a little bit about the WSRP process, let's begin configuring
WSRP on @product@. For this demonstration, you'll assume you have two 
portal servers.

+$$$

**Tip**: If you're following along with this example and don't have an 
additional portal server, you can download another instance of @product@
and have it running at the same time as your current Liferay instance to 
simulate an additional portal server. Remember, typical use cases have WSRP 
producers and consumers linked on differing portal servers. To run two portal 
instances locally at the same time, you'll need to change one of your portal's 
server configurations. Navigate to one of your portal's 
`tomcat-[VERSION]\conf\server.xml` and change the `port=` designations to 
different values (e.g., change `8080` to `18080`). Also, you can specify the new 
port number for your browser launcher URL by adding 
`browser.launcher.url=http://localhost:18080` to your portal's 
`portal-ext.properties` file. 

$$$

To create a producer, go to the *Producers* tab and click *Add Producer*. Give
your producer a name and choose the appropriate version of WSRP to use. Liferay
displays a list of available portlets your producer can use. For demonstration
purposes, select the Hello World portlet and click the *Save* button. The portal
generates a WSDL document to define your producer. To view the WSDL document,
click the URL link provided. 

![Figure 3: You can view the WSDL document for your producer by clicking the provided URL.](../../images/wsrp-producer-url.png)

Now that you've created a producer, let's create a consumer on your second 
portal server. 

On your consumer portal server, navigate to the Consumers tab and select the
*Add Consumer* button. Give it a name and add the producer's WSDL URL in the
*URL* field. There are also additional fields:

*Forward Cookies:* Allows the WSRP consumer to forward specific cookies from
the user's browser session to the WSRP producer.

*Forward Headers:* Allows the WSRP consumer to forward specific HTTP headers
from the user's browser session to the WSRP producer.

*Markup Character Sets:* Markup character encodings supported for the consumer
are shown in a comma delimited list. UTF-8 is assumed and will be added
automatically as a supported encoding.

Leave these additional fields blank for our demonstration. Lastly, you need to
define the portlets that the end-user can use from this consumer. To do this, go
to *Actions* &rarr; *Manage Portlets* for your consumer. Add the remote portlets
that you've configured for your producer portal server. In this case, select the
*Hello World* remote portlet and give the new portlet an arbitrary name. Now end
users can "consume" or use the remote portlet just like any local portlet in the
portal.

Next, you'll learn how to create custom remote portlets.

## Creating Custom Remote Portlets [](id=creating-custom-remote-portlets)

With the demand for dynamic portlets by end users, sometimes a finite,
pre-selected list of remote portlets isn't enough. Because of this, @product@
allows you to make custom developed portlets remotely accessible for WSRP.

To enable your custom portlet for WSRP, you'll need to add the
`<remoteable>true</remoteable>` tag in your portlet's
`docroot/WEB-INF/liferay-portlet.xml` file:

    <liferay-portlet-app>
        <portlet>
            <portlet-name>RemoteSamplePortlet</portlet-name>
            <remoteable>true</remoteable>
    ...
        </portlet>
    ...
    </liferay-portlet-app>

After editing your portlet's `liferay-portlet.xml` file, your custom portlet
will appear in the list of portlets available when creating a WSRP producer.
Congratulations! Now you can share all your portlets to end users using WSRP!

Next, you'll learn how to remotely access Liferay services.
