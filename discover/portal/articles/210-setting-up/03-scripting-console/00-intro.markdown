# Using Liferay's Script Engine [](id=using-liferays-script-engine)

@product@ provides a robust script engine that can be used to execute scripts. 
The script engine is used on the backend to perform tasks like running workflow 
scripts, and you can use the script console in the Server Administration area 
of the Control Panel to execute scripts to perform repetitive user 
maintenance operations, bulk invocations of Liferay's API, or even system level 
operations.

The tutorials in this section explain how to use Liferay's script engine and
script console. They cover the following topics: 

- Invoking Liferay's API from a script

- Running scripts from the script console

- Using the script engine with workflow

- Leveraging custom Java tools in the script engine

Invoking Liferay's API is probably the most common task for which you'll want to
run a script. If you have any familiarity with Liferay's API, this will be very
easy for you. To access Liferay's scripting console:

1.  Navigate to the Control Panel.

2.  Click on *Server Administration* under the System heading.

3.  Click on the *Script* tab.

**Important:** Liferay's script engine supports the following languages:
Beanshell, JavaScript, Groovy, Python, and Ruby. In previous versions of
Liferay, these languages were all available by default. Beginning with Liferay
7, only Groovy is available by default. The other languages are still supported;
you just have to install the appropriate modules. These are the available
modules:

- `portal-scripting-groovy` (installed by default)
- `portal-scripting-beanshell` (available from Liferay Marketplace)
- `portal-scripting-javascript` (available from Liferay Marketplace)
- `portal-scripting-python` (available from Liferay Marketplace)
- `portal-scripting-ruby` (available from Liferay Marketplace)

All of these modules are freely available on
[Liferay Marketplace](www.liferay.com/marketplace).
