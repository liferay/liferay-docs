# Workflow

*[This is an introductory story, feel free to skip the italics to get to the
details]*

*Rupert fit in well with the rest of ThisCorp's employees. He dressed in
business casual attire (except on Blue Jeans day, which happened once per
quarter) arrived at work on time, and liked to accompany his fellow employees
to the tavern for drinks on Friday at 5:30 p.m. Rupert reeeeaaaaallllyyy liked
this Friday tradition. One slow week in January, Rupert rounded up a group of
fellow-minded colleagues to join him for drinks on Thursday. He just couldn't
wait until Friday this week. Unfortunately, Rupert's enthusiasm led him to
drink far too many libations, and his discretion was still compromised when he
arrived at work on Friday (on time, wearing fresh khakis and a brand new polo
shirt). He went about his  usual Friday tasks (Rupert wrote a blog post on
ThisCorp's blog page each Friday) in a bit of a stupor. After lunch time he
felt quite recovered and was surprised when his supervisor, Rufelia Sacker,
stormed into his office.*

*"Rupert, we need to talk", Rufelia said in a calm voice that belied her
anger.*

*"Sure, Rufelia. Did you see my blog post on our plans to merge with
OtherCorp?" Probed Rupert.*

*"Yes, Rupert. That's why I'm here."*

*At this point Rupert was very concerned. Because of his morning stupor, he
couldn't remember the details of his blog post, so he simply said, "Okay".*

*Rufelia handed him a printed version of the thiscorp.com Blog page, with his
blog post featured at the top. "I printed us both a copy of your post so we can
look at it together. I printed our OtherCorp Guide to Official Web
Communications memo as well. Did you ever get that memo, Rupert?"*

*"Yeah, I have the memo right here", Rupert replied, as he gently patted a
stack of papers on his desk.*

*Rufelia ignored him and slid the familiar pink folder onto his desk. "Let's
look at your post now."*

*Rupert took the printout and read it in horror. Rufelia was saying something,
but all Rupert could think about now was the state of his resume.*

![Figure 1: ThisCorp should have enabled workflow for Blog posts.](../../images/thiscorp-blog.png)

Workflow is important. Applications accepting user created content usually need
to support an approval process so that low quality, objectionable, or otherwise
undesirable data is not inadvertently published. 

Workflow-enabling your app's entities is so easy, you could do it in your sleep
(but don't try this). You need to implement a workflow handler class for each
entity, and then update the service layer to keep track of the workflow
*status*. After that, update the view layer so it displays only approved
entities wherever that's appropriate. These steps are shown in the first part of
this tutorial.

In addition to enabling your entities for workflow, you can develop new workflow
definitions, using the graphical Kaleo Designer, which is bundled with Liferay
EE in versions 6.2 and later. The later sections of this tutorial show this
process.
