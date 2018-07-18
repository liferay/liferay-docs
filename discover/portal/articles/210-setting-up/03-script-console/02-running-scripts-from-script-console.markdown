# Running Scripts From the Script Console [](id=running-scripts-from-the-script-console)

To see a very simple example of the script console in action, do the following:

1.  Log into the portal as an administrator

2.  Navigate to the *Control Panel* &rarr; *Server Administration* area of the 
    Control Panel.

3.  Click on *Script*. This is @product@'s script console.

4.  Replace the code in the script console with the following: 

        number = com.liferay.portal.kernel.service.UserLocalServiceUtil.getUsersCount(); 
        out.println(number); 

5.  Click the *Execute* button and check the script console or the log for the
    output.

Next, consider a less simplistic example that retrieves some user information
from the database, makes changes, and then saves the changes in the database.
Suppose that your company has updated the 
[terms of use](/discover/portal/-/knowledge_base/7-1/terms-of-use)
and wants each user to be presented with the updated terms of use whenever they
next log in. When users agree to the terms of use, a boolean attribute called
`agreedToTermsOfUse` is set in their user records. As long as the value of this
variable is `true`, the user won't be presented with the terms of use when they
log in.  However, if you set this flag to `false` for each user, each user must
agree to the terms of use again before they can log in. 

1.  Enter the following code into the *Script* console:

        import com.liferay.portal.kernel.service.UserLocalServiceUtil

        userCount = UserLocalServiceUtil.getUsersCount()
        users = UserLocalServiceUtil.getUsers(0, userCount)

        for (user in users) { println("User Name: " + user.getFullName() + " -- " +
        user.getAgreedToTermsOfUse()) }


    This code to checks the status of the `agreedToTermsOfUse` user attribute, 
    and prints the value for each user. 

2.  Now replace that with this script:
    
        import com.liferay.portal.kernel.service.UserLocalServiceUtil

        userCount = UserLocalServiceUtil.getUsersCount()
        users = UserLocalServiceUtil.getUsers(0, userCount)

        for (user in users){
    
            if(!user.isDefaultUser() && 
                !user.getEmailAddress().equalsIgnoreCase("test@liferay.com")) {
            
                    user.setAgreedToTermsOfUse(false)
                    UserLocalServiceUtil.updateUser(user)
        
            }
        
        }

    This updates each user in the system to set his or her `agreedToTermsOfUse` 
    attribute to `false`. Your script will make sure to skip the default user 
    as well as the default admin user that's currently logged in and running 
    the script. If you're logged in as someone other than test@liferay.com, 
    update the script with your email address.

3.  Click *Execute*.
 
4.  To verify the script has updated the records, run the first script again. 

    You should see that all users (except the default user and your user) have been updated. 

That's all that's needed to run scripts and to access the Liferay service layer.

Keep these things in mind when working with the script console: 

- There is no undo.
- There is no preview.
- When invoking local services, no permissions checking is enforced.
- Scripts are executed synchronously. Be careful with scripts that might take a
  long time to execute. 

For these reasons, you should use the script console cautiously. It's best to
test run your scripts on non-production systems before running them on
production.
