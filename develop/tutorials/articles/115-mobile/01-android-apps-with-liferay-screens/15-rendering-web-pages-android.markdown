# Rendering Web Pages in Your Android App

The 
[Rendering Web Content](/develop/tutorials/-/knowledge_base/7-0/rendering-web-content-in-your-android-app) 
tutorial shows you how to display 
[web content](/discover/portal/-/knowledge_base/7-0/creating-web-content) 
from a @product@ site in your Android app. But what if you don't want to display 
only content? What if you want to display an entire page? And what if you want 
to customize that page? No problem! 
[Web Screenlet](/develop/reference/-/knowledge_base/7-0/web-screenlet-for-android) 
lets you display any web page. What's more, Web Screenlet lets you customize the 
web page through injection of local and remote JavaScript and CSS files. When 
combined with @product@'s server-side customization features (like 
[Application Display Templates](/discover/portal/-/knowledge_base/7-0/styling-apps-with-application-display-templates), 
for example), Web Screenlet gives you almost limitless possibilities for 
displaying web pages in your Android apps. 

In this tutorial, you'll learn how to use Web Screenlet to display web pages in 
your Android app. 

## Inserting Web Screenlet in Your App

Inserting Web Screenlet in your app is the same as 
[inserting any Screenlet in your app](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-android-apps): 

1.  Insert the Screenlet's XML in the layout of the activity or fragment you 
    want to use the Screenlet in. Also be sure to set any attributes that you 
    need. For a list of Web Screenlet's available attributes, see 
    [the Attributes section](/develop/reference/-/knowledge_base/7-0/web-screenlet-for-android#attributes) 
    of the Web Screenlet reference doc. 

    For example, here's Web Screenlet's XML with the Screenlet's `layoutId` and 
    `autoLoad` attributes set to `web_default` and `false`, respectively: 

        <com.liferay.mobile.screens.web.WebScreenlet
            android:id="@+id/web_screenlet"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layoutId="@layout/web_default"
            app:autoLoad="false"
            /> 

    Note that `web_default` specifies the Screenlet's Default View, which is 
    part of the Default View Set. 

2.  If you use a View that is part of a View Set, like Web Screenlet's Default 
    View, the theme of the app or activity must inherit the theme that sets the 
    View Set's styles. For the Default View Set, this is `default_theme`. For 
    example, to set the app's theme to inherit from `default_theme`, open  `res/values/styles.xml` and set the base app theme's parent to 
    `default_theme`. In this example, the base app theme is `AppTheme`: 

        <style name="AppTheme" parent="default_theme">
            ...

Next, you'll implement Web Screenlet's listener. 

## Implementing Web Screenlet's Listener

To use any Screenlet in an activity or fragment, you must also implement the 
Screenlet's listener in that activity or fragment's class. Web Screenlet's 
listener is `WebListener`. Follow these steps to implement `WebListener` in the 
class of the activity or fragment you want Web Screenlet to appear in: 

1.  Change the class declaration to implement `WebListener`: 

        public class YourActivity extends AppCompatActivity implements WebListener {...

    You must also import `com.liferay.mobile.screens.web.WebListener`.

2.  Implement `WebListener`'s `onPageLoaded` method. Since this method is called 
    when the Screenlet loads the page correctly, how you implement it depends on 
    what (if anything) you want to happen at that time. For example, this 
    `onPageLoaded` implementation displays a toast message indicating success: 

        @Override
        public void onPageLoaded(String url) {
            Toast.makeText(this, "Page load successful!", Toast.LENGTH_SHORT).show();
        }

3.  Implement `WebListener`'s `onScriptMessageHandler` method. This method is 
    called when the `WebView` in the Screenlet sends a message. The `namespace` 
    parameter is the source namespace key, and `body` is the source namespace 
    body. For example, this `onScriptMessageHandler` implementation parses data 
    from the source namespace body if it matches a specific namespace, and then 
    starts a new activity with that data via an intent: 

        @Override
        public void onScriptMessageHandler(String namespace, String body) {
            if ("gallery".equals(namespace)) {
                String[] allImgSrc = body.split(",");
                int imgSrcPosition = Integer.parseInt(allImgSrc[allImgSrc.length - 1]);

                Intent intent = new Intent(getApplicationContext(), DetailMediaGalleryActivity.class);
                intent.putExtra("allImgSrc", allImgSrc);
                intent.putExtra("imgSrcPosition", imgSrcPosition);
                startActivity(intent);
            }
        }

4.  Implement the `error` method. This method is called when an error occurs in 
    the process. The `e` argument contains the exception, and the `userAction` 
    argument distinguishes the specific action in which the error occurred. You 
    can use these arguments to log the error, show the user an error message, or 
    take any other action that you want to occur. For example, this `error` 
    implementation displays a toast message containing the exception's message: 

        @Override
        public void error(Exception e, String userAction) {
            Toast.makeText(this, "Bad things happened: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

5.  Now you must get a reference to the Screenlet and set the activity or 
    fragment class as its listener. To do so, add the following code to the end 
    of the `onCreate` method: 

        WebScreenlet screenlet = (WebScreenlet) findViewById(R.id.web_screenlet);
        screenlet.setListener(this);

    Note that the `findViewById` references the `android:id` value set in the 
    Screenlet's XML. 

Next, you'll use the same Web Screenlet reference to set the Screenlet's 
parameters. 

## Setting Web Screenlet's Parameters

Web Screenlet has `WebScreenletConfiguration` and 
`WebScreenletConfiguration.Builder` objects that you can use together to supply 
the parameters that the Screenlet needs to work. These parameters include the 
URL of the page to load, and the location of any JavaScript or CSS files that 
you want to use to customize the page. You'll set most of these parameters via 
`WebScreenletConfiguration.Builder`'s methods. 

+$$$

**Note:** For a full list of `WebScreenletConfiguration.Builder`'s methods, and 
a description of each, see the table in 
[the Configuration section](/develop/reference/-/knowledge_base/7-0/web-screenlet-for-android#configuration) 
of Web Screenlet's reference doc. 

$$$

To use `WebScreenletConfiguration` and `WebScreenletConfiguration.Builder` to 
set the parameters you need for Web Screenlet, follow these steps in the 
`onCreate` method of an activity that uses Web Screenlet: 
