---
header-id: applying-clay-design-patterns-to-6-2-themes
---

# Applying Clay Design Patterns

[TOC levels=1-4]

@product-ver@ uses [Clay](https://clayui.com/), a web implementation of 
Liferay's [Lexicon Experience Language](https://lexicondesign.io/). The Lexicon 
Experience Language provides styling guidelines and best practices for 
application UIs. Clay's CSS, HTML, and JavaScript components enable developers 
to build fully-realized UIs quickly and effectively. @product@'s 
[compatibility layer](/docs/7-2/tutorials/-/knowledge_base/t/using-the-bootstrap-3-lexicon-css-compatibility-layer) 
let's you use [Lexicon CSS](https://lexiconcss.wedeploy.io/) markup alongside 
[Clay CSS](https://clayui.com/). This section demonstrates how to apply Clay to 
the Lunar Resort's form. 

Follow these steps:

1.  Replace the `control-group` classes with `form-group` classes:

2.  Remove the `control-label` classes from the `label` elements:

3.  Remove `<div class="controls">` elements.

4.  Add the `form-control` class to each `input` element.

5.  Add the `btn-primary` class to your submit buttons to emphasize them.

The Lunar Resort's original form and updated form are shown below:

Original form markup:

```html
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
```

Updated form markup:

```html
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
```

The Lunar Resort theme is updated for @product-ver@! 
