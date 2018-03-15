# Building Pages: The Toolbox

Even though there are many different types of pages that you can create in 
Liferay, when you're planning out how to build a specific page or element, you have two core options, Applications and Fragments.

**Applications:** whether it's Web Content, Forms, Blogs, or Wikis most of your 
options for creating and displaying content are application based. You create 
the content and display it on the page with the corresponding application.

**Fragments:** using Fragments, you can create reusable, customizable page
fragments built with HTML, CSS, and JavaScript. Any number of Fragments can 
then be combined to build a page.

Most of the content you create for Liferay will be created through Liferay's various applications, whether through Web Content Management or through one of Liferay's Collaboration suite of applications. Collaboration is primarily focused on user created content, while Web Content and Fragments are going to be entirely made up of content specifically created and curated for your site.

If your goal is dynamic publishing or creating text heavy content, Web Content is going to be your primary tool. Combining the Web Content Editor with the publishing capabilities of the Asset Publisher and other tools, like tags and categories, you have a powerful suite for creating compelling content and delivering it to your users.

If your goal is to create interactive elements or to move beautiful designs from Photoshop onto the web page, then Fragments are the tool for you. Fragments give the web developer complete control over what is displayed. There is no abstraction, no "What You See Is What You Get," just HTML, CSS, JavaScript, and whatever art and assets you have available. 

In previous versions of Liferay, much of the functionality of Fragments could be realized through Web Content's Structures and Templates, which allowed you to define data types, and then populate them using a templating language like Freemarker or Velocity. Structures and Templates are still useful for many use cases where you need to define complicated data patterns or displays, but they're not the best way to provide developers with a means to simply create code web pages. In @product_ver@ rather than require developers to use the added complexity of a multipart, structure, template, and content, you can just enter the code in our new editor.

The Fragment editor provides simultaneous editing of HTML, CSS, and JavaScript with automated previews.

