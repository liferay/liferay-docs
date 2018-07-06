# User Facet [](id=user-facet)

The User Facet narrows search results down to those created by a certain User.

![Figure 1: Each User with matching content is a facet term.](../../../images/search-user-facet.png)

The User Facet contains several configuration options:

![Figure 2: The User Facet is configurable.](../../../images/search-user-facet-config.png)

**User Parameter Name**
: Set the URL parameter name for the Facet. The default is *user*. Searching for
*lunar resort* and clicking on a User Facet produces the URL

    http://localhost:8080/web/guest/search?q=lunar resort&user=38716

**Max Terms**
: Set the maximum number of facet terms to display, regardless of how
many matching terms are found for the facet.

**Frequency Threshold**
: Set the minimum frequency required for terms to appear in the result list. For
example, if the frequency threshold of a facet is set to `3`, a term with two
matching results doesn't appear in the facet.

**Display Frequencies**
: Choose whether or not to display the term frequencies.
