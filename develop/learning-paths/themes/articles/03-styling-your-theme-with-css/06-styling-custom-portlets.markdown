# Styling Custom Portlets

The Guestbook portlet is looking pretty plain at the moment. It's time to change
that. It can be styled just like any other element on the page; you just need to
know the existing classes to override.

1. Sign in as an Admin and click the *Make Your Reservation!* link at the bottom
   of the page.

2. Click the *gear icon* on the Guestbook portlet and select *Look and Feel*.

3. Select the *Advanced Styling* tab at the top of the dialog window.

4. At the top of the section you'll see a box highlighted in blue that reads
   *Your current portlet information is as follows*

    ![Figure 1: You can view a custom portlet's class in the *Look and Feel* configuration menu.](../../images/current-portlet-info.png)

Now you can see that the class for the Guestbook portlet is
`.guestbook-portlet`. It follows an easy pattern: `.project-name`. You could
write all of your CSS for the Guestbook portlet here in the *Advanced Styling*
section, but to keep things organized, you should place the styling in
`custom.css` with the rest of the styles.

1. Open `custom.css` and add the following code above the closing `}` for the
   `#content` styling:

        /*---------Guestbook Portlet Styles------------*/
        .guestbook-portlet {
            background-color: #DF5946;
            border: 2px solid #50A2F6;
            border-radius: 25px;
            color: #FFF;
            max-width: 100%;
            padding: 30px;
        }

    This gets you most of the way there, but the styling could use some more
    work. The background matches the color of the theme's navigation and Footer,
    but the text is unreadble in the first entry in the table. If you haven't
    added any entries to the guestbook, you won't see the table elements. Go
    ahead and add a couple entries to the *Main* guestbook if you haven't
    already, and save them. You should now see the table for the entries. As you
    can see the red background is a bit jarring with the second entry link. In
    order to make the needed changes to the rest of the guestbook styles, you'll
    need to know the elements or classes.

    Most browsers allow you to inspect the elements of the page to view the
    HTML. Using the built-in browser tools, you can retrieve the rest of the
    class and element names.

2. Right click on the table and select *Inspect Element*.

    A new area will appear showing the HTML for the page. If you take a closer
    look, you'll see that the table is a `table` element with a `th` element for
    the column names and `td` elements for each of the columns in the rows. Now
    you can add the styles to update the table.

3. Add the following code below the `color: #FFF;` style and save the file:

        .table {
            th {
                background-color: #50A2F6;
            }

            td {
                background-color: #FFF;
                color: #000;
            }
        }

        .portlet-title-text {
            display: block;
            font-size: 1.3em;
        }

        .aui-search-bar .field {
            border-radius: 25px;
            margin-top: 10px;
        }

Some additional styling has been added for the Guestbook portlet's title and
search bar using the classes found in the browser's inspector. With the updates
to the styling, the Guestbook portlet should look similar to the figure below:

![Figure 1: The Guestbook portlet's styles now complement the theme.](../../images/guestbook-portlet-fin.png)

In the next section you'll learn how you can use Boostrap markup and styles to
give your theme's UI a consistent look.