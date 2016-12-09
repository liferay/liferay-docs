# Authentication Pipelines 

The sign-in experience for @product@ can be completely customized. If you need
to support an authentication mechanism that @product@ doesn't yet support, you
can do that. If you don't like the user interface for signing in, you can
replace it with your own. If you need accept credentials from a third party
system that @product@ doesn't yet support, you can do that as well. 

This set of tutorials guides you through these customizations. You'll discover
three kinds of customizations: 

- Auto Login: the easiest of the three, this lets you use credentials provided
    in the HTTP header from another system to authenticate to @product@. 

- Authentication Pipelines: if you need to check credentials against other
    systems instead of or in addition to @product@'s database, you can create a
    pipeline. 

- Custom Login Portlet: if you want to change the user's sign-in experience
    completely, you can implement your own Login portlet. 

Read on to discover how to customize your users' sign-in experience. 

