# Adapting to Staged Site Management

The current Guestbook app is designed to create a *Main* guestbook on startup to
avoid having an empty display when adding it to a page. Although this is a
convenient perk, it does not translate well to the staged vs. live site
paradigm. To follow best practice for production apps leveraging staging, all
content should only be added manually through the staged site. This forces
content to be reviewed without any system-generated surprises.

To ensure the auto-generated guestbook functionality is removed, and the
Guestbook app is ready for staging, complete the following steps:

1.  Add a new language key that can display when no guestbooks are available to
    display. Open the `guestbook-web` module's
    `src/main/resources/content/Language.properties` file and add the following
    language key:

        there-are-no-guestbooks-available=There are no guestbooks available.

2.  Open the `src/main/resources/META-INF/resources/guestbookwebportlet/view.jsp`
    file. Add the following code below the `guestbookId` instantiation, within
    the scriplet:

        if (guestbookId == 0) {
            %>
                <div class="alert alert-info">
                    <liferay-ui:message key="there-are-no-guestbooks-available" />
                </div>
            <%
            return;
        }

    This displays the new language key as an alert if no guestbooks are
    available to display.

3.  Open the `GuestbookPortlet` class. Within the `render` method, update this
    `if` statement

        if (guestbookId == 0) {
            guestbookId = guestbooks.get(0).getGuestbookId();
        }

    to this

        if (guestbookId == 0 && guestbooks.size() > 0) {
            guestbookId = guestbooks.get(0).getGuestbookId();
        }

    The new `guestbooks.size() > 0` parameter handles the scenario where a
    guestbook does not exist; specifically, it skips setting an ID for a
    non-existent guestbook.

Now your Guestbook app is leveraging the Staging and Export/Import frameworks!