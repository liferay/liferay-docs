## Applying Clay Design Patterns [](id=applying-clay-design-patterns-to-6.2-themes)

@product-ver@ uses 
[Clay](https://liferay.github.io/clay/), 
a web implementation of Liferay's 
[Lexicon Experience Language](https://lexicondesign.io/). 
The Lexicon Experience Language provides styling guidelines and best practices 
for application UIs. Clay's CSS, HTML, and JavaScript components enable 
developers to build fully-realized UIs quickly and effectively. This section 
demonstrates how to apply Lexicon to your HTML markup. For example, this is the 
Liferay Portal 6.2 Lunar Resort's reservation form:

    <p>
    Thanks for choosing to stay at the Liferay Lunar Resort! Please fill out the
    form below to book your stay. We know you have a choice in where to stay on
    the
    Moon... oh wait no you don't. Thanks for picking us anyways. We'll see you
    soon on the Moon!
    </p>

    <form class="form-horizontal">
            <fieldset>
              <legend>Reservation Form</legend>
              <div class="control-group">
                  <label class="control-label" for="inputName">Name</label>
                  <div class="controls">
                          <input type="text" id="inputName"
                          placeholder="Enter your Name here" required="required">
                      </div>
              </div>
              <div class="control-group">
                  <label class="control-label" for="inputEmail">Email</label>
                  <div class="controls">
                      <input type="email" id="inputEmail"
                      placeholder="Enter your E-Mail here" required="required">
                  </div>
              </div>
              <div class="control-group">
                  <div class="controls">
                      <button type="submit" class="btn">Submit</button>
                  </div>
              </div>
            </fieldset>
    </form>

    <p style="padding-bottom:25px;">
    Thanks again for booking with Liferay. When you book with Liferay, you
    remember your stay. Please take a moment to fill out our guestbook below.
    </p>

The HTML code above uses Bootstrap 2's markup and CSS classes. Here's the Lunar 
Resort form's updated Clay markup:

    <?xml version="1.0"?>

    <root available-locales="en_US" default-locale="en_US">
            <dynamic-element name="content" type="text_area"
            index-type="keyword" index="0">
                    <dynamic-content language-id="en_US">
                            <![CDATA[
                                    <p>Thanks for choosing to stay at the
                                    Liferay Lunar Resort! Please fill out the
                                    form below to book your stay. We know you
                                    have a choice in where to stay on the Moon...
                                    oh wait no you don't. Thanks for picking us
                                    anyways. We'll see you soon on the Moon!</p>
    <form role="form-horizontal">
            <fieldset>
              <legend>Reservation Form</legend>
              <div class="form-group">
                  <label for="inputName">Name</label>
                  <input type="text" id="inputName" class="form-control"
                  placeholder="Enter your Name here" required="required">
              </div>
              <div class="form-group">
                  <label for="inputEmail">Email</label>
                  <input type="email" id="inputEmail" class="form-control"
                  placeholder="Enter your E-Mail here" required="required">
              </div>
              <div class="form-group">
                      <button type="submit" class="btn btn-primary">Submit
                      </button>
              </div>
            </fieldset>
    </form>

    <p style="padding-bottom:25px;">Thanks again for booking with Liferay. When
    you book with Liferay, you remember your stay. Please take a moment to fill
    out our guestbook below.</p>
                            ]]>
                    </dynamic-content>
            </dynamic-element>
    </root>

The Clay updates applied to the form are as follows:

-   The `control-group` classes were updated to `form-group` classes.
-   The `control-label` classes were removed from the `label` elements.
-   The `<div class=""controls>` elements were removed.
-   The `form-control` class was added to each `input` element.
-   To emphasize the form's submit button, the `btn-primary` class was added.

You can apply similar Clay patterns to your theme's HTML files. 

You've updated your theme to @product-ver@! You can deploy it from your theme 
project. Now your users can continue enjoying the visual styles you've created 
in your upgraded themes. 

## Related Topics [](id=related-topics)

[Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-1/creating-themes)

[Upgrading to @product-ver@] (/discover/deployment/-/knowledge_base/7-1/upgrading-to-liferay-71)
