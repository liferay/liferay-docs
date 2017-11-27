# Using Web Screenlet with Cordova in Your Android App [](id=using-web-screenlet-with-cordova-in-your-android-app)

+$$$

**Note:** This documentation is currently in beta. 

$$$

By using 
[Cordova plugins](https://cordova.apache.org/plugins/) in 
[Web Screenlet](/develop/tutorials/-/knowledge_base/7-0/rendering-web-pages-in-your-android-app), 
you can extend the functionality of the web page that the Screenlet renders. 
This lets you tailor that page to your apps needs. 

You'll get started by installing and configuring Cordova. There are two ways to 
do this: automatically, or manually. The automatic method is covered first. 

## Installing and Configuring Cordova Automatically [](id=installing-and-configuring-cordova-automatically)

Follow these steps to automatically create an empty Android project configured 
to use Cordova with npm: 

1.  Download `screens-cli` by cloning it via git: 

        git clone https://github.com/victorg1991/screens-cli

2.  Run `npm install`. 

3.  Update the file named `.plugins.screens` and insert all the plugins you want 
    to use in your app. You can add from Cordova or from GitHub, for example: 

        https://github.com/apache/cordova-plugin-wkwebview-engine.git
        cordova-plugin-call-number
        cordova-plugin-camera

4.  Use Node.js to create the project: 

        node index.js android <project-name>

## Installing and Configuring Cordova Manually [](id=installing-and-configuring-cordova-manually)

If you want to install and configure Cordova manually, follow these steps: 

1.  Follow 
    [the Cordova getting started guide](https://cordova.apache.org/#getstarted) 
    to install Cordova, create a Cordova project, and add the Android platform 
    to your Cordova project. 

2.  Install any Cordova plugins you want to use in your app. For example, this 
    command adds 
    [the Cordova plugin `cordova-plugin-call-number`](https://www.npmjs.com/package/cordova-plugin-call-number): 

        cordova plugin add cordova-plugin-call-number

    You can use `cordova plugin` to view the currently installed plugins. 

3.  Copy the following files and folders from your Cordova project to your 
    Android project's root folder: 

    - `/platforms/android/<_project-name_>/config.xml`
    - `/platforms/android/assets/www`

    You should also review other files like `AndroidManifest.xml`, resource 
    files, and so on. Some plugins add permissions or styles in such files that 
    you may need to copy for those plugins to work correctly in your Android 
    app. 

## Using Cordova in Web Screenlet [](id=using-cordova-in-web-screenlet)

Now that you've installed and configured Cordova in your Android project, you're 
ready to use it with Web Screenlet. Follow these steps to do so: 

1.  Insert and configure Web Screenlet in your app, as described in 
    [the tutorial on using Web Screenlet](/develop/tutorials/-/knowledge_base/7-0/rendering-web-pages-in-your-android-app). 

2.  When you set Web Screenlet's parameters via the 
    `WebScreenletConfiguration.Builder` object, you must enable Cordova by 
    calling the `enableCordova` method with a `CordovaLifeCycleObserver` object 
    as an argument. `CordovaLifeCycleObserver` is a simple class that informs 
    Cordova about the activity lifecycle. Creating such an object is also 
    simple--just use its no-argument constructor. 

    For example, this code creates a `CordovaLifeCycleObserver` object that it 
    then uses with `enableCordova` when setting Web Screenlet's parameters: 

        CordovaLifeCycleObserver observer = new CordovaLifeCycleObserver();

        WebScreenletConfiguration configuration =
                new WebScreenletConfiguration
                        .Builder("/")
                        .addLocalJs("call.js")
                        .enableCordova(observer)
                        .load();

        webScreenlet.setWebScreenletConfiguration(configuration);
        webScreenlet.load();

3.  Next, you must override the following `Activity` methods and call the 
    corresponding method in the observer:

        @Override
        protected void onStart() {
            super.onStart();
            observer.onStart();
        }

        @Override
        protected void onStop() {
            super.onStop();

            observer.onStop();
        }

        @Override
        public void onPause() {
            super.onPause();

            observer.onPause();
        }

        @Override
        public void onResume() {
            super.onResume();

            observer.onResume();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();

            observer.onDestroy();
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);

            observer.onSaveInstanceState(outState);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            observer.onActivityResult(requestCode, resultCode, data);
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, 
            @NonNull int[] grantResults) {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);

                observer.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);

            observer.onConfigurationChanged(newConfig);
        }

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

[Rendering Web Pages in Your Android App](/develop/tutorials/-/knowledge_base/7-0/rendering-web-pages-in-your-android-app)

[Web Screenlet for Android](/develop/reference/-/knowledge_base/7-0/web-screenlet-for-android)
