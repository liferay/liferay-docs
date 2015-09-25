# Creating Language Keys for Your Theme Settings [](id=creating-language-keys-for-your-theme-settings)

To create custom language keys, you must create a hook plugin to modify
`language_en.properties`. Follow the steps below to create the hook:

1.  Open IDE/Developer Studio and goto *File* &rarr; *New* &rarr; *Liferay
    Plugin Project*.

2.  Set the *Project name* as `language-key-hook` and *Display name* as 
    `Language Key Hook`.

3.  Set the proper SDK and runtime, select *Hook* for the *Plugin type*, and
    click *Finish*.

The foundation for the hook is created. Next you need to configure the hook. In
this case, you'll modify the English US language properties, but the same
process can be used to hook into any existing language properties file.

1.  Right-click the language key hook you just created in the package explorer
    on the left and select *New* &rarr; *Liferay Hook Configuration*.

2.  With `language-key-hook` set as the *Hook plugin project* select
    *Language properties* for the *hook type* and *click* *Next*.

3.  Leave the default *Content folder* and click *Add*.

4.  Enter *Language_en.properties* for the property file, click *OK* and 
    *Finish*.

5.  The file should open for you. If it doesn't, open the `docroot/WEB-INF/src`
    folder of the `language-key-hook` and open the `Language_en.properties` file in
    the `content` package.

6.  Add the following language key to the file and Save it:

        show-page-title = Show Page Title

7.  Deploy the `language-key-hook` to the server and wait for the changes to
    publish.

8.  Once the changes have published, click the *Edit* button and open the *Look
    and Feel* drop-down panel. The `show-page-title` theme setting is now using the
    language key you just created!

Excellent! The theme settings now all have language keys! In the next portion of 
the Learning Path you'll expand your CSS further by making the Lunar Resort 
theme responsive to mobile devices.
