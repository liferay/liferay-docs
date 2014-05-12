# Understanding PubSub Communication

Publish and Subscribe (PubSub) is a messaging pattern in which publishers send
messages to topics and subscribers receive the messages in those topics.
Publishers don't send messages to subscribers directly; instead, they publish
messages that are characterized into classes and sent across message channels
(topics). When the subscribers are subscribed to the publishers unique channel,
they receive the publisher's messages. PubSub is implemented in Liferay to
facilitate interaction between gadgets, interaction between gadgets and
portlets, and interaction between portlets.

In this tutorial, we'll demonstrate two simple gadgets communicating to each
other using PubSub. At the end of this tutorial, you'll have visual proof that
the two gadgets communicated to each other using PubSub:

![Figure 1: When the Subscriber gadget is subscribed to the publisher's unique channel (topic), the subscriber receives all the publisher's messages.](../../images/opensocial-24.png)

Let's begin the tutorial, and prove that PubSub works in Liferay Portal.

## Sending Messages Between Publishers and Subscribers

Let's try out PubSub for ourselves to explore how PubSub works. In this
fundamental tutorial, we'll import two simple gadgets and send messages from one
to the other. One acts as a publisher and the other as a subscriber. Follow the
steps below:

1. In a Liferay Portal instance, navigate to the Control Panel and select
   *OpenSocial Gadget Publisher* from under the *Apps* heading.

2. Select *Publish Gadget* and, for each gadget, enter the URL and click
   *Save*. 

    *Sample PubSub Publisher URL*:
    `http://svn.apache.org/repos/asf/shindig/trunk/content/gadgets/sample-pubsub-2-publisher.xml`

    *Sample PubSub Subscriber URL*:
    `http://svn.apache.org/repos/asf/shindig/trunk/content/gadgets/sample-pubsub-2-subscriber.xml`
	
3. Go back to a page on your site, navigate to *Add* &rarr; *Applications* &rarr; 
   *Gadgets*, and add your new gadgets to the page.

4. Click *Publish a random number* on the PubSub Publisher gadget.  Notice that
   it publishes a number, but the PubSub Subscriber gadget does not receive the
   number.

    ![Figure 2: The subscriber cannot receive any messages from the publisher without being subscribed to the channel.](../../images/opensocial-23.png)

5. Select *Subscribe* on the subscriber gadget.

6. Click *Publish a random number* again from the publisher gadget: you now see
   the random number received by the subscriber.

    ![Figure 3: When the subscriber is subscribed to the publisher's channel, the subscriber is able to receive messages.](../../images/opensocial-24.png)

7. Select *Unsubscribe* on the subscriber gadget.

8. Click *Publish a random number*.

As you would expect, the subscriber portlet no longer receives the random
number.

You probably also noticed that while publishing and subscribing with your
gadgets, there was no need to refresh the page; this is because the gadgets use
*AJAX (Asynchronous JavaScript and XML)*; this technology refreshes your
applications automatically, allowing PubSub to work efficiently and effectively
throughout your Liferay Portal.

This simple example illustrates what PubSub does: It opens up a message channel
through which publishers and subscribers can interact. 
