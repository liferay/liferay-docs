# Adding Resource WebKeys

Every web developer sticks objects into the request or the session in one area
of code and then retrieves them in another. Sometimes, depending on the
complexity of the application, a developer can get confused by the names of the
objects that he or she is placing there. For this reason, you can create an
object that holds constants that contain the names of these objects, so you
never forget them and are always consistent in using them. The convention in
Liferay applications is to call this object `WebKeys`. You already did a similar
thing with `PortletKeys`

1.  Create a new package called `com.liferay.docs.guestbook.util`. 

2.  Create a class in this package called `WebKeys.java`. It should implement
    the `com.liferay.portal.kernel.util.WebKeys` interface. 

3.  Create two final `String`s for your Guestbook and Guestbook Entry entities.
    When finished, your class should look like this: 

        package com.liferay.docs.guestbook.util;

        public class WebKeys implements com.liferay.portal.kernel.util.WebKeys {
            
            public static final String GUESTBOOK = "GUESTBOOK";

            public static final String GUESTBOOK_ENTRY = "GUESTBOOK_ENTRY";

        }

Excellent! You've now created a consistent way to refer to guestbook and
guestbook entry objects that you put in the request as attributes. You won't be
using these variables just yet, but we will as we continue. 

