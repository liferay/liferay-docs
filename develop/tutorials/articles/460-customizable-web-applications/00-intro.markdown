# Customizable Web Applications [](id=customizable-web-applications)

One of the great strengths of @product@ is the sheer number of out of the box
applications. This gives it great flexibility in what it can do, which is why
@product@ is used to run many different kinds of websites around the world.
For example, if you want users to add Blog posts, you can configure the Blogs
portlet to handle those requests. 

But really, that's technology from the last decade. What if you could define a
particular function that users might want to perform and let @product@
choose an available installed app to perform that function? That way, if users
want to Blog, and you've installed your own custom-developed app for blogging
instead of Liferay's, the @product@ instance can just use yours instead? 

With @product@ 7, you can do just that. You can request an app based on an
entity and action type. Processing the entity type and action, Liferay uses an
available portlet that can handle the request. This increases the flexibility
and modularity of using portlets in @product@.

