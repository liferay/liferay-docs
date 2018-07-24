# Auditing Users [](id=auditing-users)

You've just finished lunch and are ready to get back to work. You have a site in
Liferay you use to manage your project, and before you left, you were about to
create a folder in your Documents and Media library for sharing some
requirements documentation. Sitting down at your desk, you navigate to the
repository and attempt to create the folder. 

*You do not have permission to perform this action*, Liferay helpfully tells
you. 

"*What?*" you blurt in surprise. "This is *my* project!" 

"Ah, you too?" asks a co-worker helpfully from over the cube wall. "I lost
access to a wiki I was updating just a few minutes ago. I was about to enter a
support ticket for it." 

"Forget the ticket. Let's go see the admin now," you say. 

And off you go, two floors down, to the far end of the building where, as you
approach, you can already hear stress in the admin's voice as he tries to
reassure someone on the phone. 

"Yes, Mr. Jones. Yes, I'll fix it." (*Jones? The president of the company?* goes
through your mind.) "I'll get on it right away, Mr. Jones. It was just a
mistake; I'll fix it. Thank you, Mr. Jones," and he hangs up the phone. 

"Problems?" you ask the admin, whose name is Harry. He does look rather
harried. 

"Yeah, Tom," he says. "Somebody changed a bunch of permissions in @product@--it
wasn't me. I'm assuming you and Dick are here because of the same problem?" 

"Yup," you say. "I lost access to a document repository folder." 

"And I lost access to a wiki," Dick says helpfully. 

"It was probably due to some site membership change. Let's take a look at the
audit portlet in the control panel and see what happened." 

Sometimes you just need to know what users are doing on your site and exactly
who is the one doing it. If you're a DXP subscriber, you can find out easily
using the Audit application. In combination with some settings in
`portal-ext.properties`, the Audit application enables you to see all the
activity that occurs in your @product@. Using this, you can quickly find out
what changes were made and by whom. If you've delegated permission granting to
any group of people, this is an essential feature you're likely to use. 

We'll come back to Tom, Dick and Harry's story later. For now, let's look at
how to configure and use Liferay's Audit application so you can do the same
thing Harry's about to do. 
