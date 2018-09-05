# Configurable Applications [](id=configurable-applications)

Many applications must be configurable, whether by end users or administrators.
A viable configuration solution must support use cases ranging from setting a
location for a weather display to more complex cases like settings for a mail or
time sheet application. 

The Portlet standard's portlet preferences API can be used for portlet
configuration, but it's intended for storing user preferences. This limits its
usefulness for enabling administrator configuration; plus it can only be used
with portlets. Instead, application developers tend to create ad hoc
configuration methods. But this isn't necessary. 

There's a full-featured configuration API that's easy to use, and it's not
limited to portlets. Any class can use the configuration API to set
configuration values in the UI. It's used throughout @product@'s applications.
We like it, and we think you'll like it too. 

The following tutorials show you how to use it. 
