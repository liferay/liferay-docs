---
header-id: integrating-the-back-end
---

# Integrating the Back-end

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Building the Web Front-End</p><p>Step 4 of 11</p>
</div>

To use your Service Builder-generated back-end in your front-end, you must
tell your front-end project that the back-end exists and where to find it. 

For the web module to access the generated services, you must make it aware of
the API and service modules: 

1.  Open `guestbook-web`'s `build.gradle` file and add these dependencies:

    ```groovy
    compileOnly project(":modules:guestbook:guestbook-api")
    compileOnly project(":modules:guestbook:guestbook-service")
    ```

2.  Save the file. Right-click on the `guestbook-web` project and select
    *Gradle* &rarr; *Refresh Gradle Project*. 

3.  Now you must add *references* to the Service Builder services you need. To 
    do this, add them as class variables with `@Reference` annotations on their 
    setter methods. Open `GuestbookPortlet` and add these references to the 
    bottom of the file: 

    ```java
	@Reference
	private GuestbookEntryLocalService _guestbookEntryLocalService;
	
	@Reference
	private GuestbookLocalService _guestbookLocalService;
    ```

    Note that it's Liferay's code style to add class variables this way. The
    `@Reference` annotation causes Liferay's OSGi container to
    inject references to your generated services so you can use them. 

4.  Press Ctrl-Shift-O to add the import you need: 

    - `org.osgi.service.component.annotations.Reference`

Now you're ready to begin building your front-end. 

