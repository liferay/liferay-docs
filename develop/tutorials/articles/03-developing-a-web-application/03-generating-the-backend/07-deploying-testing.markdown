# Deploying and testing the application

Now that you created your services, and set them all up, you'll need to deploy
your `guestbook-service` and `guestbook-api` modules to get your complete 
integrated application working.

1. Drag and drop the `guestbook-api` module onto the server.
2. Drag and drop the `guestbook-service` module onto the server.
3. Wait for the everything to finish deploying.
4. Head over to your Liferay instance at `localhost:8080` in your browser to
    test your updated application.

Now you'll want to test your finished application.

1. In your application, click on *Add Guestbook*.
2. Enter a name for your Guestbook and click *Submit*.
3. Verify that a guestbook appears.
4. Now click *Add Entry*.
5. Enter a *Name*, *Message*, and *Email Address*.
6. Click *Submit*.
7. Verify that your entry now appears.

![Figure x: A new Guestbook and Entry created.](../../../images/guestbook-entry-test.png)

# Wrapping up the application

You've done everything you need to create a working web application with 
Liferay. In some cases, you could deploy it now and get working, but there are
a lot of other important pieces to creating a full-featured application integrated with Liferay. Stay tuned to learn how to get your application
properly organized in Liferay's application hierarchy, and how to create
administrative applications for the Control Panel.