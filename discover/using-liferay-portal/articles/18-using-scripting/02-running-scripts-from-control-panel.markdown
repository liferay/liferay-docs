# Running Scripts from the Control Panel [](id=running-scripts-from-the-control-panel)

To see a very simple example of the script console in action, log into the
portal as an administrator and navigate to the control panel &rarr; Server
Administration &rarr; Script. Change the script type to Groovy and modify the
current code to look like the following: 

    number = com.liferay.portal.service.UserLocalServiceUtil.getUsersCount(); 
    out.println(number); 

Click the execute button and check the console or the log for your output. 

Let's implement a more realistic example. We'll retrieve some user information
from the database, make some changes and then update the database with our
changes. Our company has updated the terms of use and requires that everyone be
presented with the updated terms of use on the next log in. When users agree to
the terms of use, a boolean attribute called `agreedToTermsOfUse` is set in
their user records. As long as the boolean is `true`, Liferay will not present
the user with the terms of use. However, if we set this flag to `false` for
everyone, all users will have to agree to it again to use the site. 

We'll again use Groovy, so ensure the script type is set to Groovy and execute
the following code to check the status of the `agreedToTermsOfUse` attribute:

	import com.liferay.portal.service.UserLocalServiceUtil

	userCount = UserLocalServiceUtil.getUsersCount()
	users = UserLocalServiceUtil.getUsers(0, userCount)

	for (user in users) {
		println("User Name: " + user.getFullName() + " -- " + user.getAgreedToTermsOfUse())
	}
	
Now we'll actually update each user in the system to set his or her
`agreedToTermsOfUse` attribute to false. We'll be sure to skip the default user
as the default user is not required to agree to the Terms of Use. We'll also
skip the admin user that's currently logged in and running the script. If you're
logged in as somoene other than test@liferay.com, be sure to update the
following script before running it. 
	
	import com.liferay.portal.service.UserLocalServiceUtil

	userCount = UserLocalServiceUtil.getUsersCount()
	users = UserLocalServiceUtil.getUsers(0, userCount)

	for (user in users){
	
		if(!user.isDefaultUser() && 
			!user.getEmailAddress().equalsIgnoreCase("test@liferay.com")) {
			
				user.setAgreedToTermsOfUse(false)
				UserLocalServiceUtil.updateUser(user)
		
		}
		
	}
	
To verify the script has updated the records, run the first script again and you
should see all users (except the default user and your ID) have been updated. 

That's all that's needed to run scripts and to access the Liferay service layer.
There are, however, some things to keep in mind when working with the script
console: 

* There is no undo

* There is no preview

* When using Local Services, no permissions checking is enforced

* Scripts are executed synchronously, so be careful with scripts that might take
  a long time to execute. 

For these reasons, you want to use the script console with care, and test run
your scripts on non-production systems before you run them on production. 

Of course, the script engine has uses beyond the script console. One of the main
uses of it is in designing workflows. 
