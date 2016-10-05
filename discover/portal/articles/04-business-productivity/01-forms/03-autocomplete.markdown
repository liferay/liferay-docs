#Autocompleting Text Fields

It's been scientifically proven that internet users are lazy (not you or me, for
sure--other internet users). So citizens of *Bosnia and Herzegovina* probably
won't apply to The Lunar Resort if you make them type the entire title of their
country in the employment application. This is doubly true if they're filling
out the form on their mobile device. 

To guide user input you can use a select field with a data provider, but
sometimes the possible field entries are not able to be encompassed by a data
provider. If your data provider doesn't include Bosnia and Herzevogina, for
example, users from that country won't be able to enter anything into the field.
An alternative to this is using a text field with the *autocomplete* feature so
they can begin typing their country's name, and select it from a list if it
appears. Using a text field with autocomplete gives you the flexibility of a
text field (accepting any response that meets your [validation
criteria](/discover/portal/-/knowledge_base/7-0/creating-advanced-forms#validating-text-fields))
while giving users common choices to select from.

## Configuring Autocomplete

Before configuring autocomplete for your text fields, [create a form and add a
text field](/discover/portal/-/knowledge_base/7-0/basic-forms). Now you're ready
to configure autocomplete for the field:

1. In the field configuration sidebar, click the *Properties* tab.
2. Click *Autocomplete*.
3. Now configure a data provider.
    -  You can set up a data provider from a [REST service](/discover/portal/-/knowledge_base/7-0/creating-advanced-forms#populating-select-fields-with-rest-data-providers)
    -  Alternatively, manually enter the options a user should see when they
        start typing in the text field.
4. Once your form is saved and published, you're done.

![Figure x: You can configure a manual data provider to specify the options
users can select from.](../../../images/forms-manual-data-provider.png)

Once a user begins entering text into the field, a dropwdown list of options
appears. As they continue to type, the list is filtered alphabetically to refine
the set of options. For example, the imaginary user from Bosnia and Herzegovina
begins reluctantly typing their country of origin by entering a *b*. They're
delighted to see a dropdown list with a bunch of countries beginning with the
letter *b* appear for their selection convenience. If they continue typing and
enter *o*, the list is refined and they now only see a few options (Bolivia,
Bosnia and Herzegovina, and Botswana). If they continue typing and enter *s*
they'll now only see one option, Bosnia and Herzegovina. Selecting it from the
dropdown list after typing the first three letters is much easier than typing
the remaining 17 letters.

What will the Forms team think of next? Configuring wireless extrasensory
connections to the Forms application would be nice. Then users could just think
their form field entries into existence. Stay tuned.





Autocomplete is a feature that helps user to search for and select a value
through a list of options, but the user is not enforced to do so.  Once a user
starts typing a text, a filtered list of options is presented and then a value
can be picked up.

Autocomplete is available only for Text fields and a data provider must be
assigned to such kind of field in order to work properly. A data provider can be
selected from a previously configured service or by manually editing a
predefined list of options.  

Notice that the autocomplete feature is only an aid to enhance usability but not
a way to compel a user to select one of the values available in the attached
data provider. A Select field should be used in place to add this kind of
behavior as the user has no other option then select a value.

The figure presented below shows how autocomplete is configured for a Text field
in the Form Builder view. In order to configure autocomplete the form
administrator must choose between a manual or a previously registered data
provider. If the form administrator chooses a manual data provider then he/she
needs to enter all available options, including a text that is shown when final
user starts typing and a corresponding value that is saved into the database
once that item is selected.

![](images/image03.png)

Below it is presented a picture that shows a user manually defining a data
provider to be used as a source to autocomplete.

![](images/image02.png)

The other option to configure autocomplete implies choosing a previously
configured data provider as it is presented in the figure below. A data provider
can be configured accessing a menu available on the top-right corner of the form
list page.

![](images/image01.png)

When the form administrator finish the configuration of a form, the form can be
saved and published on page so that form is available to final users. The form
executes on this shared page on a mode called User View.  An example of
autocomplete feature is shown in action in the figure below.
