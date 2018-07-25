# Running Scripts From the Script Console [](id=running-scripts-from-the-script-console)

The script console provides a single view for executing Groovy scripts on @product@ and printing their output. The console has predefined variables that facilitate printing output and working with portlets and @product@ users. Here you'll learn these things:

- [How to execute a script in the script console](#running-the-sample-script) 

- [The predefined variables available in the script console](#predefined-variables)

- [Tips for running scripts in the script console](#tips)

+$$$

**Important:** The script console is for system operations and maintenance and 
not for end users. Limit script console access to portal administrators.

$$$

Start with running the script console's sample script. 

## Running the Sample Script [](id=running-the-sample-script)

Here's how to run the sample script in the script console:

1.  Sign in to @product@ as an administrator.

2.  In the *Product Menu*, navigate to *Control Panel* &rarr; *Configuration*
    &rarr; *Server Administration*. 

3.  Click on *Script*. This is the script console. The default sample script
    prints the User count to the console output. 

        // ### Groovy Sample ###

        number = com.liferay.portal.kernel.service.UserLocalServiceUtil.getUsersCount();

        out.println(number);

4.  Click *Execute* and check the script console *Output* for the User count.

![Figure 1: The script console's sample Groovy script prints the User count to the console's *Output* section.](../../../images/groovy-script-sample.png)

The Groovy sample invokes Liferay service utility `UserLocalServiceUtil` to get
the user count. Then it uses the `out` (a built-in `PrintWriter`) to output the
count to the script console. 

## Predefined Variables [](id=predefined-variables)

Here are the predefined variables available to scripts executed in the script
console:

- `out` (`java.io.PrintWriter`)
- `actionRequest` (`javax.portlet.ActionRequest`)
- `actionResponse` (`javax.portlet.ActionReponse`)
- `portletConfig` (`javax.portlet.PortletConfig`)
- `portletContext` (`javax.portlet.PortletContext`)
- `preferences` (`javax.portlet.PortletPreferences`)
- `userInfo` (`java.util.Map<String, String>`)

This script demonstrates using the `actionRequest` variable to get the portal
instance's `Company`:

    import com.liferay.portal.kernel.util.*

    company = PortalUtil.getCompany(actionRequest)
    out.println("Current Company:${company.getName()}\n")

    out.println("User Info:")
    userInfo.each { 
            k,v -> out.println("${k}:${v}") 
    }

![Figure 1: Here's an example of invoking a Groovy script that uses the predefined `out`, `actionRequest`, and `userInfo` variables to print information about the company and current user.](../../../images/groovy-script-current-user-info.png)

Note that if you use `System.out.println`, for example, your output is printed
to Liferay's log file. If you use `out.println` instead (using the predefined
variable), your output is printed to the script console.

## Tips [](id=tips)

Keep these things in mind when using the script console: 

- There is no undo.
- There is no preview.
- Permissions checking is not enforced for local services.
- Scripts are executed synchronously. Avoid executing scripts that might take a
  long time. 

For these reasons, use the script console cautiously. Test your scripts on
non-production systems before running them on production. 

Of course, Liferay's script engine can be used outside of the script console.
Next, you'll learn how workflows leverage Liferay's script engine.
