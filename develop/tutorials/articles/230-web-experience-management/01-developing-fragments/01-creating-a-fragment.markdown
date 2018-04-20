# Creating a Fragment [](id=creating-a-fragment)

The first example is simple. If you aren't sure about the basics of Fragments
and Collections, you should read our User Documentation [link] first so you know
what you're getting yourself into.

1.  Go to *Site Administration* and for your selected site click *Content*
    &rarr; *Fragments*.
 
2.  Create a new Collection named *Developing Fragments*.

3.  Inside of the new Collection, create a new Fragment named *Basic Fragment*.

You're now on the Fragment editing page. There are four panes on this screen.
You enter HTML in the top left pane, CSS in the top right, JavaScript in the
bottom left, and preview the results in the bottom right.

You can look at the three editing panes as if each were writing to a separate 
file. Everything in the HTML pane goes to `index.html`, the CSS pane goes to 
`index.css`, and the JavaScript one goes to `index.js`. The preview pane renders
everything as it will look on the page. Here's how this works:

1.  Add the following code inside the `div` in the HTML pane:

          <h1>
              This text is styled, so it will look pretty!
          </h1>

2.  Add the following code to the CSS pane inside the fragment block:

          width: 100%;
          background-color: white;
          text-align: center;
          padding: 2em 0;
          font-size: 28px;

3.  Add this code below it and replace the `#####` with the auto-generated
    number provided for the fragment 

        .fragment_##### h1 {
          font-weight: 100;
          width: calc(100% - 1em);
          max-width: 60ch;
          margin: 0 auto;
          font-size: 28px;
         }

3. Click *Publish* to save your work and make it available to add to a content
   page.

As you work, you can observe the preview in the preview pane.

![Figure 1: The Fragment editor with HTML and CSS code and a live preview.](../../../images/fragment-editor-basic.png)

From here, the Fragment can be used to create a Content Page. To see this 
process in action, see the Creating Content Pages [link] tutorial. Next, you'll
explore some Fragment-specific tags. 
