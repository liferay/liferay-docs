### Consider Common Security Pitfalls [](id=consider-common-security-pitfalls-liferay-portal-6-2-dev-guide-11-en)

As you develop your plugin, you need to anticipate your plugin's actions in
light of Liferay's secured environment. The security manager leverages the Java
SE Security Architecture. So understanding Java SE Security and learning the few
requirements that Liferay's security manager adds on top of it will benefit you
as you develop plugins. The extensive Java SE Security Architecture
documentation is available for you to read at
[http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html](http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html).
But we'll highlight a couple common mistakes developers make that violate
Liferay's secured environment: 

- Invoking a method, directly or indirectly, without considering whether the
  method can throw a security exception.
- Using external libraries or external frameworks that access classloaders
  outside of your plugin.  

You wouldn't intentionally make these kinds of mistakes, but you'd be surprised
at how easily you can make them if you're not being careful enough. We'll
consider scenarios that illustrate both of these mistakes and explain how to
avoid making them in your plugin. Let's consider security exceptions first. 

When you're running on Liferay Portal with the security manager enabled for your
plugin, you must only access authorized resources. If you invoke a method
declared as throwing a security exception (i.e., `java.lang.SecurityException`)
and you're not authorized to access the resources the method uses, the method
throws the security exception and the Security Manger stops your plugin dead in
its tracks. Security exceptions are unchecked, meaning that the compiler doesn't
require your code to handle them. But since methods that throw security
exceptions are declared as throwing them, you should check their signatures
while you're designing your plugin. If the methods your plugin uses throw
security exceptions, handle them appropriately with try/catch blocks. Keep in
mind that you not only need to handle security exceptions of methods your plugin
invokes *directly*, but you also need to handle the security exceptions of the
underlying methods your plugin invokes *indirectly*. 

For example, you may be using a file utility that calls `java.io.File`'s
`canRead` method. Since the `canRead` method can throw a `SecurityException`,
your plugin will violate security if it invokes the utility on a file that
you're not authorized to access. So, be aware of all security exceptions thrown
by methods your plugin calls directly and indirectly.

Operations involving reflection, and similar activities, typically can throw
security exceptions. The Java SE Security documentation explains how to deal
with them. In many cases, you can declare your plugin's permissions to avoid
running into these exceptions. We'll go over your plugin's permissions and
security policies later in this chapter. 

The second common mistake you should avoid is allowing your plugin to bring up
classloaders unintentionally, via other frameworks or libraries. Consider the
following Spring configuration from a plugin:

    <bean id="userServiceBeanFactory"
        class="com.liferay.portal.service.UserLocalServiceUtil"
        factory-method="getService"
    />

It declares a factory bean that calls a method on a Liferay class. This seems
reasonable, right? Unfortunately, Spring tries to grab the classloader for the
factory class. Since the factory class does not belong to the plugin, the
security manager balks at the plugin's attempt to access the classloader for the
factory class. The security manager forbids applications from accessing arbitrary
classloaders because the classloaders can add, access, and modify classes that
your plugin is unauthorized to access. Using Spring in this manner violates the
secured environment.

How do you get around this issue? You could simply invoke the method directly
like this: 

    UserLocalServiceUtil.getService()

But if you insist on using a Spring factory bean, you can do the following:

1. Write a class *inside* your plugin to act as a factory. Your factory class
   should declare a class that wraps the type of instance your factory returns.
   Your factory should also implement a method that returns the instance,
   wrapped in the class you declared. 

2. Configure a Spring factory bean that uses your plugin's factory class. 
    
Here's what your plugin's new factory class could look like:

        package test;
    
        // Add imports here ...
    
        public class FactoryUtil {
    
            public static UserLocalService getUserLocalService() {
                TestUserLocalServiceWrapper localServiceWrapper =
                    new TestUserLocalServiceWrapper(
                        UserLocalServiceUtil.getService());
    
                return localServiceWrapper;
            }
    
            private static class TestUserLocalServiceWrapper
                extends UserLocalServiceWrapper {
     
                public TestUserLocalServiceWrapper(
                    UserLocalService userLocalService) {

                    super(userLocalService);
                }
    
            }
    
        }

The code above declares a factory class named `FactoryUtil` that resides in a
package named `test`. The factory declares an inner class named
`TestUserLocalServiceWrapper` that extends Liferay's `UserLocalServiceWrapper`
class. Note, `UserLocalServiceWrapper` in turn wraps `UserLocalService`--the
class you want the factory to return. Lastly, the `getUserLocalService()` method
uses the original factory method, `UserLocalServiceUtil.getService()`, to get
the `UserLocalService` instance. This instance is wrapped up in your factory's
`TestUserLocalServiceWrapper` class. In your plugin, you've implemented a
factory class to access the instances you want. That wasn't so difficult, was
it? 

Your new Spring factory bean would look like the following configuration:

    <bean id="userServiceBeanFactory" 
        class="test.FactoryUtil"
        factory-method="getUserLocalService"
    />

Great! Now you know a couple alternatives to using the troublesome Spring
factory bean configuration that was accessing a classloader that didn't belong
to your plugin. 

With regards to both of the use cases we've illustrated, the main point we're
emphasizing is that you must be aware of the how the libraries you use behave
with respect to your secured environment. The better you understand Java SE
Security and Liferay's Plugin Security Management, the easier it will be for you
to write security-aware plugins. Keeping this in mind, you can proceed
confidently creating your plugin. 
