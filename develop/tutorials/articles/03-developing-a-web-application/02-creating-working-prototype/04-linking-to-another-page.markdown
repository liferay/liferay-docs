# Linking to Another Page [](id=linking-to-another-page)

<div class="learn-path-step">
    <p>Developing Your First Portlet<br>Step 4 of 8</p>
</div>

In the same folder your `view.jsp` is in, create the `edit_entry.jsp` file:

1.  Right-click your project's `src/main/resources/META-INF/resources` folder
    and choose *New* &rarr; *File*.

2.  Name the file `edit_entry.jsp` and click *Finish*.

3.  Add this line to the top of the file:

        <%@ include file="init.jsp" %>

    Remember, it's a best practice to add all JSP imports and tag library
    declarations to a single file that's imported by your application's other 
    JSP files. For `edit_entry.jsp`, you need these imports to access the 
    portlet tags that create URLs and the Alloy tags that create the form. 

4.  You'll create two URLs: one to submit the form and one to go back to the 
    `view.jsp`. To create the URL to go back to `view.jsp`, add the following 
    tag below the first line you added: 

        <portlet:renderURL var="viewURL">
            <portlet:param name="mvcPath" value="/view.jsp"></portlet:param>
        </portlet:renderURL>

Next, you must create a new URL for submitting the form. Before you do, some
explanation is in order. 
