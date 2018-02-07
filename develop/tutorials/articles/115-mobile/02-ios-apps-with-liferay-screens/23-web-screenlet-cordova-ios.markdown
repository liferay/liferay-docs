# Using Web Screenlet with Cordova in Your iOS App [](id=using-web-screenlet-with-cordova-in-your-ios-app)

By using 
[Cordova plugins](https://cordova.apache.org/plugins/) in 
[Web Screenlet](/develop/tutorials/-/knowledge_base/7-0/rendering-web-pages-in-your-ios-app), 
you can extend the functionality of the web page that the Screenlet renders. 
This lets you tailor that page to your app's needs. You'll get started by 
installing Cordova. 

## Installing and Configuring Cordova Automatically [](id=installing-and-configuring-cordova-automatically)

Follow these steps to automatically create an empty Android project configured 
to use Cordova. Note that you must have 
[git](https://git-scm.com/), 
[Node.js and npm](https://nodejs.org/en/), 
and 
[CocoaPods](https://cocoapods.org/) 
installed. 

1.  Install `screens-cli`: 

        npm install -g screens-cli

2.  Create the file `.plugins.screens` in the folder you want to create your 
    project in. In this file, add all the Cordova plugins you want to use in 
    your app. For example, you can add plugins from Cordova or GitHub: 

        https://github.com/apache/cordova-plugin-wkwebview-engine.git
        cordova-plugin-call-number
        cordova-plugin-camera

    Note that the `WKWebView` Engine plugin is mandatory in iOS. 

3.  In the folder containing your `.plugins.screens` file, run `screens-cli` to 
    create your project: 

        screens-cli ios <project-name>

    This creates your project in the folder `platforms/ios/<project-name>`. 

4.  Run the following in `platforms/ios/<project-name>`: 

        pod install

5.  Open the `<project-name>.xcworkspace` file with Xcode. 

## Installing and Configuring Cordova Manually [](id=installing-and-configuring-cordova-manually)

Follow these steps to install and configure Cordova: 

1.  Follow 
    [the Cordova getting started guide](https://cordova.apache.org/#getstarted) 
    to install Cordova, create a Cordova project, and add the iOS platform to 
    your Cordova project. 

2.  Install the 
    [Cordova `WKWebView` engine](https://www.npmjs.com/package/cordova-plugin-ionic-wkwebview-engine): 

        cordova plugin add cordova-plugin-wkwebview-engine

3.  Install any other Cordova plugins you want to use in your app. You can use 
    `cordova plugin` to view the currently installed plugins. 

4.  Copy the following files and folders from your Cordova project to your iOS 
    project's root folder: 

    - `platforms/ios/<your-cordova-project>/config.xml`
    - `platforms/ios/<your-cordova-project>/Plugins`
    - `platforms/ios/www`

5.  In the `config.xml` file you just copied to your iOS project's root folder, 
    add `<allow-navigationhref="*" />` below `<access origin="*" />`. 

## Using Cordova in Web Screenlet [](id=using-cordova-in-web-screenlet)

Now that you've installed and configured Cordova in your iOS project, you're 
ready to use it with Web Screenlet. Follow these steps to do so: 

1.  [Insert and configure Web Screenlet in your app](/develop/tutorials/-/knowledge_base/7-0/rendering-web-pages-in-your-ios-app). 

2.  When you set Web Screenlet's parameters via the 
    `WebScreenletConfigurationBuilder` object, call the `enableCordova()` 
    method. For example, this code adds a local JavaScript file via `addJs` and 
    then calls `enableCordova()` before loading the configuration and the 
    Screenlet: 

        let configuration = WebScreenletConfigurationBuilder(url: "url")
            .addJs(localFile: "call")
            .enableCordova()
            .load()

        webScreenlet?.configuration = configuration
        webScreenlet?.load();

That's it! Note, however, that you may also need to invoke Cordova from a 
JavaScript file, depending on what you're doing. For example, to use 
[the Cordova plugin `cordova-plugin-call-number`](https://www.npmjs.com/package/cordova-plugin-call-number) 
to call a number, then you must add a JavaScript file with the following code: 

    function callNumber() {
        //This line triggers the Cordova plugin and makes a call
        window.plugins.CallNumber.callNumber(null, function(){ alert("Calling failed.") }, "900000000", true);
    }

    setTimeout(callNumber, 3000);

If you run the app containing this code and wait three seconds, the plugin 
activates and calls the number in the JavaScript file. 

Great! Now you know how to use Web Screenlet with Cordova. 

## Related Topics [](id=related-topics)

[Rendering Web Pages in Your iOS App](/develop/tutorials/-/knowledge_base/7-0/rendering-web-pages-in-your-ios-app)

[Web Screenlet for iOS](/develop/reference/-/knowledge_base/7-0/web-screenlet-for-ios)
