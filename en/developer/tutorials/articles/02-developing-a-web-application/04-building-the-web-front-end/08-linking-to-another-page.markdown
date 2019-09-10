---
header-id: linking-to-another-page
---

# Linking to Another Page

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Building the Web Front-End</p><p>Step 7 of 11</p>
</div>

In the same folder your `view.jsp` is in, create the `edit_entry.jsp` file:

1.  Right-click your project's `src/main/resources/META-INF/resources/guestbook`
    folder and choose *New* &rarr; *File*.

2.  Name the file `edit_entry.jsp` and click *Finish*.

3.  Add this line to the top of the file:

    ```markup
    <%@ include file="../init.jsp" %>
    ```

    Remember, it's a best practice to add all JSP imports and tag library
    declarations to a single file that's imported by your application's other 
    JSP files. For `edit_entry.jsp`, you need these imports to access the 
    portlet tags that create URLs and the Alloy tags that create the form. 

4.  Next, you need a scriptlet that helps determine the function the user
    accessed. You named this JSP `edit_entry.jsp` because it's used both for
    adding and editing. Add this scriptlet to add logic for determining which
    function the user wants: 

    ```markup
    <% 

    long entryId = ParamUtil.getLong(renderRequest, "entryId");

    GuestbookEntry entry = null;
    if (entryId > 0) {
      entry = GuestbookEntryLocalServiceUtil.getGuestbookEntry(entryId);
    }

    long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");

    %>
    ```

    If an `entryId` greater than `0` is found in the request, editing
    a `GuestbookEntry` is assumed. Otherwise, the user is adding. 

5.  You'll create two URLs: one in the next step to submit the form and one in
    this step to go back to `view.jsp`. To create the URL to go back to
    `view.jsp`, add the following tag below the first line you added: 

    ```markup
    <portlet:renderURL var="viewURL">
        <portlet:param name="mvcPath" value="/guestbook/view.jsp"></portlet:param>
    </portlet:renderURL>
    ```

Next, you must create a new URL for submitting the form. This is a different
kind of URL, for it triggers a portlet action.
