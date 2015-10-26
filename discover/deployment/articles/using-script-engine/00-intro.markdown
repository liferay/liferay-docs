# Using Liferay's Script Engine

Liferay provides a robust script engine that can be used to execute scripts in
Beanshell, JavaScript, Groovy, Python, and Ruby. Liferay's script engine was
originally developed to allow developers to write portlets in languages other
than Java such as PHP, Groovy, Ruby, and Python. Since then, the script engine
has been extended to do much more. For example, when Liferay's workflow
framework was introduced, the script engine was leveraged to support the
execution of scripts from within a workflow module. Liferay now provides a
script console in the Server Administration area of the Control Panel. The
script console provides an easy way for system administrators to execute scripts
to perform repetitive user maintenance operations, bulk invocations of Liferay's
API, or even system level operations.

The tutorials in this section explain how to use Liferay's script engine and
script console. They cover the following topics: 

- Invoking Liferay's API from a script

- Running scripts from the script console

- Using the script engine with workflow

- Leveraging custom Java tools in the script engine

Invoking Liferay's API is probably the most common task for which you'll want to
run a script. If you have any familiarity with Liferay's API, this will be very
easy for you. To access Liferay's scripting console, navigate to the Control
Panel, click on *Server Administration* under the System heading, then click on
the *Script* tab.
