# Authentication Pipelines [](id=authentication-pipelines)

The authentication process in @product@ is a pipeline through which users can be
validated by one or several systems. @product@'s flexibility and extensibility
makes it possible for you to make it authenticate users to anything you wish, 
rather than be limited by what it supports out of the box. 

Here's how authentication works under most circumstances: 

1.  Users provide their credentials to the Login Portlet to begin an
    authenticated session in a browser. 

2.  Alternatively, credentials are provided to @product@'s API endpoints, where
    they are sent in an HTTP BASIC Auth header. 

3.  Alternatively, credentials can be provided by another system. These are
    managed by `AutoLogin` components. 

4.  Credentials are checked by default against the database, but they can be
    delegated to other systems instead of or in addition to it. This is called
    an *Authentication Pipeline*. You can add `Authenticator`s to the pipeline 
    to support any system. 

5.  You can also customize @product@'s Login Portlet to support whatever user
    interface any of these systems need. This gives you full flexibility over
    the entire authentication process. 

You can also support an authentication mechanism and/or accept credentials from 
a system that @product@ doesn't yet support. If you don't like the user 
interface for signing in, you can replace it with your own. 

This set of tutorials guides you through these customizations. You'll discover
three kinds of customizations: 

- Auto Login: the easiest of the three, this lets you authenticate to @product@ 
    using credentials provided in the HTTP header from another system. 

- Authentication Pipelines: if you need to check credentials against other
    systems instead of or in addition to @product@'s database, you can create a
    pipeline. 

- Custom Login Portlet: if you want to change the user's sign-in experience
    completely, you can implement your own Login portlet. 

Read on to discover how to customize your users' sign-in experience. 

