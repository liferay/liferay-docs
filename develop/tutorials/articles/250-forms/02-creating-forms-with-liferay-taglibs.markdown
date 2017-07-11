# Creating Forms with Liferay's Taglibs [](id=creating-forms-with-liferays-taglibs)

This tutorial demonstrates how to:

- Create a form using @product-ver@'s taglibs in your application's JSPs

To begin, take a look at the Portlet Configuration application's
[add_configuration_template.jsp](https://github.com/liferay/liferay-portal/blob/2960360870ae69360861a720136e082a06c5548f/modules/apps/web-experience/portlet-configuration/portlet-configuration-web/src/main/resources/META-INF/resources/add_configuration_template.jsp)
form for example:

    <aui:form action="<%= updateArchivedSetupURL %>"
    cssClass="container-fluid-1280" method="post" name="fm">
            <aui:input name="redirect" type="hidden" value="<%= redirect %>" />
            <aui:input name="portletResource" type="hidden"
            value="<%= portletResource %>" />

            <aui:fieldset-group markupView="lexicon">
                    <aui:fieldset>

                            <%
                            String name = StringPool.BLANK;
                            boolean useCustomTitle =
                            GetterUtil.getBoolean(portletPreferences.getValue
                            ("portletSetupUseCustomTitle", null));
                            if (useCustomTitle) {
                                    name = PortletConfigurationUtil.getPortletTitle
                                    (portletPreferences, LocaleUtil.toLanguageId
                                    (themeDisplay.getSiteDefaultLocale()));
                            }
                            %>

                            <aui:input name="name" placeholder="name"
                            required="<%= true %>" type="text" value="<%= name
                            %>">
                                    <aui:validator name="maxLength">75
                                    </aui:validator>
                            </aui:input>
                    </aui:fieldset>
            </aui:fieldset-group>

            <aui:button-row>
                    <aui:button type="submit" />

                    <aui:button href="<%= redirect %>" type="cancel" />
            </aui:button-row>
    </aui:form>

As you can see, a standard form uses quite a few taglibs. If you take a closer
look, you'll notice that all of the taglibs are prefixed with `aui`. `aui`
stands for [AlloyUI](http://alloyui.com/), a JavaScript framework that uses
Bootstrap to allow you to create UI components, easily and effectively. In
order to use these AUI tags, you will need to have the AUI taglib declaration
imported into your JSP. You'll take care of this next.

## Adding the Taglib Declaration [](id=adding-the-taglib-declaration)

The first thing you'll need to do is make sure you have the
`<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>` declaration is in
your JSP. This can be added to the main view of your app, `view.jsp` for example,
or it can be added to a separate JSP file, such as `init.jsp`, and imported into
the JSP in which you want to show the form. For example, the Portlet
Configuration application includes the taglib declarations in its `init.jsp`,
and then imports the `init.jsp` into its `view.jsp`, using the following line:

    <%@ include file="/init.jsp"%>

Once the AUI taglib declaration is imported, you can move onto creating the form
next.

## Creating the Form [](id=creating-the-form)

A form's design is determined by the input needed from the user. To that end,
there are multiple design possibilities. The examples covered in the sections
that follow are one possible design, and highlight some of the available
attributes for the tags. The steps are laid out in a natural order, however you
can jump to any section you wish.

For a full list of the available attributes for the form tags covered, checkout
the API docs for the [AUI Tags](@platform-ref@/7.0-latest/taglibs/util-taglib/aui/tld-summary.html).

Go ahead and get started by adding the form tag next.

### Adding the Form Wrapper [](id=adding-the-form-wrapper)

Start off by adding a `<aui:form>` tag to your jsp. Make sure to add a closing
`<aui:form/>` tag, to wrap your form. This acts as a wrapper for your form and
offers some additional styling and custom portlet namespacing for you to use. If
you are familiar with HTML `<form>` tags, `<aui:form>` tags are configured the
same way. For instance, the Bloggs Aggregator app has the following
configuration:

    <aui:form action="<%= configurationActionURL %>" method="post" name="fm">

The `action` attribute specifies where to send the form data when the form is
submitted. The `method` attribute defines the method to use to send the form
data(in most cases this will be post). Finally, the `name` attribute specifies
the name for the form, as well as the ID for the component instance for the
form. It's important to note that, by default, `aui:form` places the portlet
namespace in front of the `name` and `id` attributes. This is also the default
behavior for the `aui:input` tag as well.

You can find a full list of the available attributes for the `<aui:form>`
tag in the [AUI Form Taglib Docs](@platform-ref@/7.0-latest/taglibs/util-taglib/aui/form.html).

Now that your form element is created, you can add your fieldsets next.

### Adding the Fieldset Groups and Fieldsets [](id=adding-the-fieldset-groups-and-fieldsets)

The next main element is the `<aui:fieldset-group>` tag. This tag creates a
`<div>` to group fieldset elements of the form. Looking at the Portlet
Configuration example, you can see the following pattern:

    <aui:fieldset-group markupView="lexicon">

It's important to note the addition of the `markupView="lexicon"` attribute.
This ensures that the lexicon HTML markup and CSS styles are used to render
the element, rather than the standard markup.

Add the `<aui:fieldset-group markupView="lexicon">` tag, just below the
`<aui:form>` tag you just added. And make sure to add the closing
`</aui:fieldset-group>` tag just before the closing `</aui:form>` tag. Your form
should look something like this at this point:

    <aui:form action="<%= ActionURL %>" method="post" name="fm">
        <aui:fieldset-group markupView="lexicon">

        </aui:fieldset-group>
    </aui:form>

Now that your fieldset group is added, you can add your fieldset next. The
`<aui:fieldset>` tag creates a `<div>` to group related form elements and offers
some additional styling attributes as well.

Use the following pattern to add the `<aui:fieldset>` tag to your form:

    <aui:fieldset >

    </aui:fieldset>

Alternatively, if you have multiple fieldsets, you can update your tags to be
collapsible, using the following pattern:

    <aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" >

    </aui:fieldset>

You can find a full list of the available attributes for the `<aui:fieldset>`
tag in the [AUI Fieldset Taglib Docs](@platform-ref@/7.0-latest/taglibs/util-taglib/aui/fieldset.html).

Next, you can add your input fields.

### Adding the Input Fields [](id=adding-the-input-fields)

Each fieldset is used to group similar form elements together. In this section,
you'll take a look at the different kinds of input fields you can use in your
form.

AUI input fields use the following pattern:

    <aui:input label="label" name="name" type="type" />

The `label` attribute sets the label for the input field. The `name` attribute
sets the name for the field. Finally, the `type` attribute sets the type of
input to use for the field.

The `type` attribute supports the following types:

- file: Adds a file browser.
- text: The default value if no `type` is specified. Adds a text input field.
- hidden: Adds a hidden text field.
- assetCategories: Adds a `liferay-ui:asset-categories-selector` component.
- assetTags: Adds a `liferay-ui:asset-tags-selector` component.
- textarea: Adds a textarea box.
- timeZone: Adds a `liferay-ui:input-time-zone` component.
- password: Adds a password input field.
- checkbox: Adds a checkbox.
- radio: Adds a radio button.
- color: Adds a HTML color picker.
- editor: Adds a `liferay-ui:input-editor` component.
- email: Adds an email input field.
- number: Adds a number selector.
- range: Adds a range slider.
- resource: Adds a `liferay-ui:input-resource` component.
- toggle-card: Adds a [Toggle Card](http://liferay.github.io/lexicon/content/toggles/#toggleCard)
- toggle-switch: Adds a [Toggle Switch](http://liferay.github.io/lexicon/content/toggles/#toggle-switch)
- url: Adds a URL input field.

For a full list of all the attributes available for the `<aui:input>` tag, check
out the [AUI Input Taglib Docs](@platform-ref@/7.0-latest/taglibs/util-taglib/aui/input.html).

Once you've added all of your input fields, you can move onto the form's buttons
next.

### Adding the Button Rows and Buttons [](id=adding-the-button-rows-and-buttons)

Taking a look at the portlet configuration application example once again, you
can see that form buttons follow the pattern below:

    <aui:button-row>
            <aui:button type="submit" />

            <aui:button href="<%= redirect %>" type="cancel" />
    </aui:button-row>

The `<aui:button-row>` tags acts as a wrapper for the form's buttons, and offers
some additional styling through the `cssClass` attribute.

The `<aui:button>` tag is a standard input button, with some additional
attributes. It supports the `button`, `submit`(the default type), `cancel`, and
`reset` types for the `type` attribute. Note that you if you wish to emphasize
the button as a primary action, you can add the `primary="true"` attribute to
your button:

    <aui:button
        cssClass="btn-lg"
        id="submit"
        label="save"
        primary="<%= true %>"
        type="submit"
    />

For a full list of the attributes available for the `<aui:button>` tag, check
out the [AUI Button Taglib Docs](@platform-ref@/7.0-latest/taglibs/util-taglib/aui/button.html).

Your form is complete!

## Related Topics [](id=related-topics)

[Portlet Decorators](/develop/tutorials/-/knowledge_base/7-0/portlet-decorators)

[Basic Forms](/discover/portal/-/knowledge_base/7-0/basic-forms)

[Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)
