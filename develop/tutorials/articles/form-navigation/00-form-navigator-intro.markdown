# Form Navigators

Liferay Portal provides the taglib `form-navigator` that is useful to organize a
big form in categories and sections that can be easily browsed and filled by the
user.

Since Liferay 7.0, Liferay offers a mechanism to dynamically add new sections to
form navigator taglibs via OSGi modules. Developers can take advantage of this
mechanism to create their own OSGi plugins and add new categories or sections in
those form navigators.

The taglib has an attribute `id` that indentifies that particular instance of
form-navigator taglib. This attributed is used by the sections and categories to
identify in which form navigator they will be included.

## Adding new categories to existing form navigators

Developers who want to include a new category in a existing form navigator will
need to implement `FormNavigatorCategory` interface. Developers will need to
identify in which form navigator id the category is to be included by
implementing method `getFormNavigatorId`. That method will return an identifier
that will match with the `id` attribute of a taglib. Values of existing form
navigator ids used in the portal can be found in the class
`FormNavigatorConstants.java`.

These implementations needs to be registered in the OSGi registry so the form
navigator taglib can retrieve them. The order in which the categories will be
displayed is based on the `service.ranking` OSGi property. The higher the
service ranking is, the first the category will appear.

## Adding new sections to existing form navigators

Developers who want to include a new section in a existing form navigator will
need to implement `FormNavigatorEntry` interface. As it also happens with form
navigator categories, developers will need to identify in which form navigator
the new section will be included (`getFormNavigatorId` method ) but they will
also need to specify under which category (`getCategoryKey` method). Values of
existing form navigator category keys used in the portal can be found in the
class `FormNavigatorConstants.java`.

These implementations needs to be registered in the OSGi registry so the form
navigator taglib can retrieve them. The order in which the sections will be
displayed is based on the `service.ranking` OSGi property. The higher the
service ranking is, the first the entry will appear under certain category.