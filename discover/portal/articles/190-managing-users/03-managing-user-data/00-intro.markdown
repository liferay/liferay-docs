# Managing User Data [](id=managing-user-data)

Enforcement of the General Data Protection Regulation (GDPR) began May 25,
2018. Companies processing the personal data of EU residents must adopt
appropriate measures to protect their user data. A wealth of available resources
discuss the implications of the regulation and the steps companies must take to
ensure compliance.

To assist in complying with GDPR, new User Management functionality was added to
make these tasks easier:

[Erase and/or anonymize data associated with a User](/discover/deployment/-/knowledge_base/7-1/sanitizing-user-data)
: Administrative Users go through a step by step process, choosing to erase
certain pieces of data and anonymize others.

[Export a User's personal data](/discover/deployment/-/knowledge_base/7-1/exporting-user-data)
: Export ZIP files containing the data associated with a User.

These features can be seen as a tool that gets your enterprise closer to meeting
two of GDPR's technically challenging requirements, the _right to data
portability_ and the _right to be forgotten_. 

ADD A NOTE ABOUT NOT GUARANTEEING THAT THIS SATISFIES GDPR, IT'S UP TO THE
COMPANY TO DETERMINE WHETHER THIS IS SATISFACTORY

## Anonymizing Data [](id=anonymizing-data)

Anonymizing User Associated Data (UAD) is all about identity swapping. A User
being anonymized must have their identifiers (for example, User ID and their
User Name) removed from content they've interacted with. However, portal content
usually requires htis information for the applications to work properly.
Therefore, the User's identifiers must be replaced by something, or someone.
Meet the new User, *Anonymous Anonymous*. This deactivated User is dedicated to
be the User whose identifiers are assigned to anonymized content. 

![Figure 1: Anonymized content is presented with the User Anonymous Anonymous's identifying information.](../../../images/users-anonymized-content.png)

Just anonymizing the User's identification fields might not be enough. There can
be personal data within the content of an application, for example, that must be
manually edited to remove identifying details. For example, perhaps a User named
Ziltoid complains about The Lunar Resort's coffee in a Message Boards Message
(for example, a comment on a Blogs Entry) and in it refers to himself as the
_Supreme Leader of Ziltoidea 9_. Anyone searching the internet for _Ziltoidea 9_
will quickly realize that the post was written by 
[Ziltoid the Omniscient](https://en.wikipedia.org/wiki/Ziltoid_the_Omniscient).

![Figure 2: Even though this Message Boards Message (a comment on a blog post in this case) is anonymized, it should be edited to remove User Associated Data from the content of the message.](../../../images/users-partial-anonymization.png)


