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
text field](/discover/portal/-/knowledge_base/7-0/basic-forms). If you want the
autocomplete options to be populated by a REST data provider, [configure
one](/discover/portal/-/knowledge_base/7-0/creating-advanced-forms#populating-select-fields-with-rest-data-providers)
before creating your form. Now you're ready to configure autocomplete for the
field:

1. In the field configuration sidebar, click the *Properties* tab.
2. Click *Autocomplete*.
3. Now select a data provider or create one manually.
    -  You can set up a data provider from a [REST service](/discover/portal/-/knowledge_base/7-0/creating-advanced-forms#populating-select-fields-with-rest-data-providers).
    -  Alternatively, manually enter the options a user should see when they
        start typing in the text field.
4. Once your form is saved and published, you're done.

![Figure x: You can configure a manual data provider to specify the options
users can select from.](../../../images/forms-manual-data-provider.png)

Once a user begins entering text into the field, a dropwdown list of options
appears. As they enter additional text, the list is refined to include only
options that contain the text currently entered. For example, the imaginary user
from Bosnia and Herzegovina begins reluctantly typing their country of origin by
entering a *b*. They're delighted to see a dropdown list with a bunch of
countries containing the letter *b* appear for their selection convenience.  If
they continue typing and enter *o*, the list is refined and they now only see
options that have *bo* in their name (for example *Bo*livia,
*Bo*snia-Herzegovina, and Cam*bo*dia, among others). If they continue typing and
enter *s* they'll now only see one option, Bosnia-Herzegovina. Selecting it
from the dropdown list after typing the first three letters is much easier than
typing the remaining 14 letters.

![Figure x: When typing in a field with autocomplete, users are presented a list of selections form the configured data provider. The displayed results are filtered to include only selections containing the text entered by the user.](../../../images/forms-autocomplete-filtering.png)

What will the Forms team think of next? Configuring telepathic connections to
the Forms application would be nice. Then users could just think their form
field entries into existence. Stay tuned.

