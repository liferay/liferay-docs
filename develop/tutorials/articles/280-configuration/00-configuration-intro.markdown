# Configuration [](id=configuration)

Many applications must be configurable, whether by end users or administrators.
A viable configuration solution must support use cases ranging from setting a
location for a weather display to more complex cases like settings for a mail or
time sheet application. 

The Portlet standard's portlet preferences API can be used for portlet
configuration, but it is intended for storing user preferences. This limits its
usefulness for enabling administrator configuration, and of course limits it for
use with portlets. Application developers have instead tended to create ad hoc
configuration methods on an as-needed basis. 

There's a full-featured configuration API that's easy to use, and it's not
limited to portlets. Any class that needs to accept configuration values set in
the UI can do so through the configuration API. It's used throughout @product@'s
applications. We like it, and we think you'll like it too. 

The following tutorials show you how to use it. 
