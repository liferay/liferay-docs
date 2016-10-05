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

