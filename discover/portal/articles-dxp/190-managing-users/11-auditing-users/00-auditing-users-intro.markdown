# Auditing Users [](id=auditing-users)

You've just finished lunch and are ready to get back to work. You have a Site
you use to manage your project and before you left, you were about to create a
folder in your Documents and Media library for sharing some requirements
documentation. Sitting down at your desk, you navigate to the repository and
attempt to create the folder.

*You do not have permission to perform this action*, @product@ helpfully tells
you. 

"*What?*" you blurt in surprise. "This is *my* project!" 

"Ah, you too?" asks a co-worker from over the cube wall. "I lost access to a 
wiki I was updating just a few minutes ago. I was about to enter a support 
ticket for it." 

"Forget the ticket. Let's go see the admin now," you say. 

And off you go, two floors down and to the far end of the building where, as you
approach, you can already hear stress in the admin's voice as he tries to 
reassure someone on the phone. 

"Yes, Mr. Jones. Yes, I'll fix it." (*Jones? The president of the company?* goes
through your mind.) "I'll get on it right away, Mr. Jones. It was just a 
mistake; I'll fix it. Thank you, Mr. Jones," and he hangs up the phone. 

"Problems?" you ask the admin, whose name is Harry. He does look rather harried. 

"Yeah, Tom," he says. "Somebody changed a bunch of permissions---it wasn't me. 
I'm assuming you and Dick are here because of the same problem?" 

"Yup," you say. "I lost access to a document repository folder." 

"And I lost access to a wiki," Dick says. 

"It was probably due to some Site membership change. Let's take a look at the
Audit app in the Control Panel and see what happened." 

Sometimes you need to know what users are doing and exactly who is doing it. If
you're a DXP subscriber, you can find this out with the Audit app. In
combination with some settings in `portal-ext.properties`, the Audit app shows
you all the activity that occurs on your server. You can quickly find out
what changes were made and by whom. If you've delegated permission granting to
any group of people, this is an essential feature you're likely to use.

