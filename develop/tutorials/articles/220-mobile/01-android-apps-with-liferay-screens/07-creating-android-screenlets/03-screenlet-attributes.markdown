# Defining Screenlet Attributes in Your App [](id=defining-screenlet-attributes-in-your-app)

Before creating the Screenlet class, you should define its attributes. These are 
the attributes the app developer can set when inserting the Screenlet's XML in 
an activity or fragment layout. For example, to use 
[Login Screenlet](/develop/reference/-/knowledge_base/7-0/loginscreenlet-for-android), 
the app developer could insert the following Login Screenlet XML in an activity 
or fragment layout: 

    <com.liferay.mobile.screens.auth.login.LoginScreenlet
        android:id="@+id/login_screenlet"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:basicAuthMethod="email"
        app:layoutId="@layout/login_default"
        />

The app developer can set the `liferay` attributes `basicAuthMethod` and 
`layoutId` to set Login Screenlet's authentication method and View, 
respectively. The Screenlet class reads these settings to enable the appropriate 
functionality. 

When creating a Screenlet, you can define the attributes you want to make 
available to app developers. You do this in an XML file inside your Android 
project's `res/values` directory. For example, Add Bookmark Screenlet's 
attributes are defined in the 
[Screenlet's `bookmark_attrs.xml` file](https://github.com/liferay/liferay-screens/blob/master/android/samples/addbookmarkscreenlet/src/main/res/values/bookmark_attrs.xml): 

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <declare-styleable name="AddBookmarkScreenlet">
            <attr name="layoutId"/>
            <attr name="folderId"/>
            <attr name="defaultTitle" format="string"/>
        </declare-styleable>
    </resources>

This defines the attributes `layoutId`, `folderId`, and `defaultTitle`. Add 
Bookmark Screenlet's Screenlet class adds functionality to these attributes. 
Here's a brief description of what each does: 

- `layoutId`: Sets the View that displays the Screenlet. This functions the same 
as the `layoutId` attribute in 
[Liferay's existing Screenlets](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android). 

- `folderId`: Sets the folder ID in the Bookmarks portlet where the Screenlet 
adds bookmarks. 

- `defaultTitle`: Sets each Bookmark's default title. 

Now that you've defined your Screenlet's attributes, you're ready to create the 
Screenlet class.
