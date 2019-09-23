---
header-id: displaying-messages-and-errors
---

# Displaying Messages and Errors

[TOC levels=1-4]

When users interact with your application, they perform tasks it defines, like 
saving or editing things. The Guestbook application is no different. Your 
application should provide feedback on these operations so users can know 
they completed. Up to now, you've been placing this information in logs 
that only administrators can access. Wouldn't it be better to show users these 
messages? 

![Figure 1: You can use Liferay's APIs to display helpful messages.](../../../images/guestbook-status-message.png)

That's exactly what you'll do next, in three steps: 

1.  Create language keys for your messages.
2.  Add the error messages to your action methods.
3.  Report those error messages in your JSPs.

If you get stuck, [source code](https://github.com/liferay/liferay-docs/tree/master/en/developer/tutorials/code/guestbook/06-messages/com-liferay-docs-guestbook)
for this step is provided. 

Ready to get started? 

<a class="go-link btn btn-primary" href="/docs/7-2/tutorials/-/knowledge_base/t/creating-language-keys">Let's Go!<span class="icon-circle-arrow-right"></span></a>
