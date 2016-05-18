# Writing a Liferay MVC Application [](id=writing-a-liferay-mvc-application)

A popular way to develop Liferay applications is to use the 
[Model View Controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) 
(MVC) pattern. If you're a battle-tested developer, you're likely familiar with 
MVC. To make things simpler, Liferay has developed its own MVC framework. You 
might now be thinking, "Simpler? How does yet another MVC implementation make 
things simpler? I'm tired of redundant frameworks!" Liferay's MVC framework, 
however, provides many time-saving benefits that streamline Liferay development. 
Unlike many Java MVC implementations, it's a lightweight framework. It also 
hides part of the complexity of portlets and makes common operations easier. 
What's more, Liferay's own developers use it to develop portlets. This means 
there are many robust examples to reference when you need to design or 
troubleshoot your applications. Liferay also provides tooling that makes 
creating Liferay MVC applications a snap. You'll quickly find that learning and 
using Liferay MVC saves you time over trying to implement your application with 
a different MVC implementation. 

Here, you'll use Liferay MVC to develop a Liferay portlet from start to finish. 
This includes installing Liferay's development tools, generating your 
persistence layer, integrating with Liferay features like search and indexing, 
generating remote services, and much more. 
