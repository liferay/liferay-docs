# Summary [](id=summary-0)

You've covered a lot of ground learning Liferay Portlet development. You created
a portlet project, studied its anatomy, and created the "My Greeting Portlet".
You understood the Action phase and Render phase, and have have passed
information between them in a portlet. You've enhanced a portlet with multiple
actions and have mapped a friendly URL to it. Lastly, you've found how easy it
is to start localizing your portlets. You're really on a roll! 

Now that you know how to create portlets, you'll need to consider a few things,
such as persisting your objects to a database, maintaining separatation between
your persistence layer, business logic, and presentation layer, and allowing for
flexible implementations. Lastly, you'll want the ability to publish your
portlet's operations as services. So how do you address all of this? Hibernate
probably comes to mind for persisting your data model, and Spring probably comes
to mind with regards to supporting implementation flexibility. Sounds
complicated, right? No need to worry! Liferay's Service Builder helps you build
portlet services while hiding the complexities of using Spring and Hibernate
under the hood. We'll cover Service Builder next. 
