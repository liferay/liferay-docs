# Using Bootstrap Styles

Since Liferay Portal 6.2, themes are based off of Bootstrap 2.3.2. When writing
common markup, such as buttons, forms, and layouts, it is recommended that you
let Bootstrap handle the styling for you.

You can view Bootstrap's Base CSS [here](http://getbootstrap.com/2.3.2/base-css.html).

You may have not been aware, but some of the web content articles you imported
with the theme are using Boostrap markup styles. If you take a look at the
markup for the Reservation form, you'll see Bootstrap's classes are used:

    <form class="form-horizontal">
        <fieldset>
          <legend>Reservation Form</legend>
          <div class="control-group">
              <label class="control-label" for="inputName">Name</label>
              <div class="controls">
                      <input type="text" id="inputName" placeholder="Enter your
                      Name here" required="required">
                  </div>
          </div>
          <div class="control-group">
              <label class="control-label" for="inputEmail">Email</label>
              <div class="controls">
                  <input type="email" id="inputEmail" placeholder="Enter your
                  E-Mail here" required="required">
              </div>
          </div>
          <div class="control-group">
              <div class="controls">
                  <button type="submit" class="btn">Submit</button>
              </div>
          </div>
        </fieldset>
    </form>

This form was created following Bootstrap's [horizontal form](http://getbootstrap.com/2.3.2/base-css.html#forms)
example. The form inputs are extended even further, using Boostrap's invalid
inputs markup. For example, by settng the `type` for the E-mail input to `email`
you are able to get validation for the field!

Bootstrap makes it easy for you to get a consistent look to your theme's UI in a
short amount of time.

A step-by-step approach to CSS allows you to handle it with ease, and keep track
of your progress. In the next portion of the Learning Path you'll create the
social media links for the Lunar Resort.