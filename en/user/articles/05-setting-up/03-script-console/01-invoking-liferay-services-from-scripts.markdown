---
header-id: invoking-liferay-services-from-scripts
---

# Invoking Liferay Services From Scripts

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/system-administration/using-the-script-engine/invoking-liferay-services-from-scripts.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

Many scripting scenarios require invoking Liferay services.
[Liferay `*ServiceUtil` classes](https://docs.liferay.com/ce/portal/7.2-latest/javadocs/portal-kernel/)
are the fastest and most convenient way to invoke Liferay
services in the
[script console](/docs/7-2/user/-/knowledge_base/u/running-scripts-from-the-script-console).
You can use Groovy to invoke Liferay services the same way you would use Java.
Groovy's syntax facilitates writing concise, elegant scripts.

This first example illustrates correct syntax for interacting with Liferay
services. It uses `UserLocalServiceUtil` to retrieve a list of users and print
their names to Liferay's log. To do this, you could deploy a module with Java
code like this:

```groovy
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import java.util.List;

...

int userCount = UserLocalServiceUtil.getUsersCount();
List<User> users = UserLocalServiceUtil.getUsers(0, userCount);

for (User user:users) {
    System.out.println("User Name: " + user.getFullName());
}

...
```

Or you could use Groovy---based on Java---and do the whole thing right from the
[script console](/docs/7-2/user/-/knowledge_base/u/running-scripts-from-the-script-console)
with the same code:

```groovy
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import java.util.List;

int userCount = UserLocalServiceUtil.getUsersCount();
List<User> users = UserLocalServiceUtil.getUsers(0, userCount);

for (User user:users) {
    System.out.println("User Name: " + user.getFullName());
}

You can even make the code somewhat Groovier:

import com.liferay.portal.kernel.service.UserLocalServiceUtil

userCount = UserLocalServiceUtil.getUsersCount()
users = UserLocalServiceUtil.getUsers(0, userCount)
for (user in users){
    System.out.println("User Name: " + user.getFullName())
}
```

Groovy scripts that invoke Liferay services are easy to write and execute in the
script console.

How to run scripts is next.

## Related Topics

[Running Scripts From the Script Console](/docs/7-2/user/-/knowledge_base/u/running-scripts-from-the-script-console)

[Leveraging the Script Engine in Workflow](/docs/7-2/user/-/knowledge_base/u/leveraging-the-script-engine-in-workflow)

[Script Examples](/docs/7-2/user/-/knowledge_base/u/script-examples)
