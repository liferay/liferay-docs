# Extending the Lunar Resort Theme with a Themelet

So far, you've created the theme for the Lunar Resort and created a color scheme 
variant to apply during the Lunar Eclipse, when special discounts are available. 
There's one more thing that you can customize to be more user-friendly. By 
default, when the user clicks the *sign-in* link they are redirected to a new 
page to sign in, which is a bit jarring. Instead, you can borrow the behavior of 
the Classic theme and display the sign in form in a modal window on the same 
page when a user clicks the *sign-in* link. The Admin has just sent an email 
notifying you that the Lunar Resort is going to make another site for the new 
Lunar Restaurant. She would like to use the sign in modal in all future themes, 
including the new Lunar Restaurant's theme. You could manually add the sign in 
modal to each theme, but since the same code is going to be used in all future 
themes, it would be better to create a Themelet. A themelet is a modular code 
package (JS and/or CSS) that can be installed into multiple themes and removed 
just as easily if it's no longer needed. 

Follow these steps to create the themelet for the Lunar Resort:

1.  Navigate to the Lunar Resort project's root folder and run the command below 
    to create the themelet:
    
    ```bash
    yo liferay-theme:themelet
    ```
    
2.  Enter *Lunar Sign In Themelet* for the name and accept the defaults.

3.  Navigate to the themelet's root folder and add a `/src/js/main.js` file.

4.  Add the snippet below to `main.js` to display the sign in form in a modal:

    ```JavaScript
    (function() {
    	AUI().ready('liferay-sign-in-modal', function(A) {
    		var signIn = A.one('.sign-in > a');

    		if (signIn && signIn.getData('redirect') !== 'true') {
    			signIn.plug(Liferay.SignInModal);
    		}
    	});
    })();
    ```

5.  Globally install the themelet so you can use it in your themes. Run the 
    command below from the themelet's root folder to globally install the Lunar 
    Sign In Themelet: 

    ```bash
    npm link
    ```

6.  With the themelet globally installed, navigate to the Lunar Resort theme's 
    root folder and run the `gulp extend` command.

7.  Enter 2 to select Themelet, and then enter 1 to select globally installed 
    npm modules.

8.  Select the *lunar-sign-in-themelet*, press spacebar, and hit Enter to 
    install the themelet.

9.  Re-deploy the theme to apply the themelet.

Now you have a reusable sign-in modal that we can install across all our themes!

![Figure 1: The themelet displays the sign in form in a modal window.](dfd)