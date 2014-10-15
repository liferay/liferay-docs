# What is Liferay? [](id=what-is-liferay)

If you're reading this, we're going to make a wild guess and say that you're
probably looking for some software to help you run your web site. Creating a
dynamic web site that's more than brochureware is complicated these days. You
have to think about all kinds of things like user registration, security,
community-building, collaboration, and of course your own, unique functionality
and the design of your site. 

To create all this, you can pursue several different strategies. You can decide
to build the whole thing yourself from scratch. The reason usually given for
pursuing this strategy is that you have the most freedom, since you can write
whatever you want. A closer inspection, however, reveals that instead of
freedom, you become a slave, writing all kinds of code that's not core to your
web site. You have to write code for user registration, login, security, and
session management; code for standards support, such as Facebook login, OpenID,
OpenSocial, or RSS feeds; and code for general features, such as comments, tags,
and ratings. You also take the most risk: you and/or your development team are
responsible for every bug, glitch, user interface issue, or security hole--and
the consequences for these--that can be found in your site. This strategy also
requires the most *time* to implement, because you're building everything from
scratch. When considering this option, therefore, you really need to think hard
about whether you and your development team have the time and the expertise to
handle building everything from the ground up. 

Another option is to scrounge the web for software that has some of the features
you want, and then "glue" it together into one integrated site. For example,
every site has some kind of content, so you might pick a content management
system. But you want users to interact in some way, so you also grab forum
software. Prominent users might get a blog, so you'll need software to handle
that. And, of course, don't forget that you have to write your own
application(s). You're attempting to build a super app out of a collection of
disparate, but best-of-breed individual applications. You've collected the
applications, and now your job is to integrate all of these together so that
users only have to log in once, the forum software can power comments on your
content, and blogs are published in a nice, customizable feed on the home page.
As you work on this, you find that gluing all these applications together isn't
as easy as you thought. You might get all of it working, but then you have a
different problem down the road: a maintenance nightmare. All of these software
packages will be updated by their development teams separately, on different
schedules, to fix bugs, add features, and plug security holes. Now that you've
customized them all, every upgrade of every software package you've chosen
becomes an exercise in re-implementing your "glue" code to make it all work. 

You could also try using something like a blog that has a lot of functionality
that can kind of be used as a content management system. But then, of course,
things don't work exactly as you'd like them to, and features you need are
missing. You either have to build those features and integrate them in, or do
without them. 

One final option, if you have a budget, is to buy something that mostly fits
what you want to do, with the intention of customizing it to fit your needs. The
goal here is to get something up and running as quickly as possible, by taking
advantage of someone's product and support. The devil here, though, is in the
details: it's the customization--particularly with products that are not open
source--that can be most difficult. License agreements need to be negotiated to
get you access to non-documented, internal APIs, and many products are simply
not designed to be customized to the extent that you might want to have them
customized. It can take more time to customize than you though, and you usually
run into some limitation that keeps you from building exactly what you need.
Alternatively, you may wind up having to pay expensive consultants to get the
result that you want, and after that, you've paid more than you initially
thought you would, and you have a complicated situation when it comes to
upgrading your system. 

As you can see, all of these options have pros and cons to them. There are times
when building from scratch makes sense, and there are times when integration or
purchasing a solution make sense. But what if you could have all of these
strategies combined? What if there was a product that gave you the freedom to
create whatever you want, had tons of reusable functionality, was designed for
customization, and was a single, unified product with a clear upgrade path? 

There is. This solution gives you all the freedom of creating your site from the
ground up, all the benefits of existing, integrated, robust applications, and a
development framework that makes your application a first class citizen. It's
called Liferay Portal. 
