# Using the Bootstrap 3 Lexicon CSS Compatibility Layer [](id=using-the-bootstrap-3-lexicon-css-compatibility-layer)

By default, @product@ includes Bootstrap 4 out-of-the-box. Bootstrap 4 has been 
completely rewritten and therefore includes some 
[notable changes](https://getbootstrap.com/docs/4.1/migration/) 
and 
[compatibility updates](http://getbootstrap.com/docs/4.1/getting-started/browsers-devices/) 
that may be cause for concern if your theme uses Bootstrap 3 or Lexicon CSS. Not 
to worry though. To ensure that your upgrade runs smoothly, @product@ includes a 
compatibility layer so you can use Bootstrap 3 markup and Lexicon CSS markup 
alongside the new Bootstrap 4 and Clay CSS. If your theme extends the 
[Styled base theme](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/frontend-theme/frontend-theme-styled), 
this compatibility layer is included by default. 

+$$$

**Note:** This compatibility layer is meant as a short-term solution to ensure 
that your Bootstrap 3 and Lexicon CSS components aren't broken while you update 
your theme to use 
[Bootstrap 4](https://getbootstrap.com/docs/4.0/migration/) 
and 
[Clay CSS](https://ui-migration.liferay.com/docs/lexicon/add_menu.html).

$$$
 
Follow these guidelines to update your markup:

1.  See how your theme looks with the compatibility layer enabled 
    (it's enabled by default).

2.  Individually disable the component(s) in the compatibility layer that you 
    don't need. These are listed in the 
    [`css/compat/_variables.scss`](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/frontend-theme/frontend-theme-styled/src/main/resources/META-INF/resources/_styled/css/compat/_variables.scss) 
    file. For convenience, the components are listed below:
    
        // Compatibility layer components config

        $compat-alerts: true !default;
        $compat-basic_search: true !default;
        $compat-breadcrumbs: true !default;
        $compat-button_groups: true !default;
        $compat-buttons: true !default;
        $compat-cards: true !default;
        $compat-component_animations: true !default;
        $compat-dropdowns: true !default;
        $compat-figures: true !default;
        $compat-form_validation: true !default;
        $compat-forms: true !default;
        $compat-grid: true !default;
        $compat-icons: true !default;
        $compat-labels: true !default;
        $compat-liferay: true !default;
        $compat-list_groups: true !default;
        $compat-management_bar: true !default;
        $compat-modals: true !default;
        $compat-nav_tabs: true !default;
        $compat-navbar: true !default;
        $compat-navs: true !default;
        $compat-pager: true !default;
        $compat-pagination: true !default;
        $compat-panels: true !default;
        $compat-progress_bars: true !default;
        $compat-responsive_utilities: true !default;
        $compat-sidebar: true !default;
        $compat-simple_flexbox_grid: true !default;
        $compat-stickers: true !default;
        $compat-tables: true !default;
        $compat-toggle_card: true !default;
        $compat-toggle_switch: true !default;
        $compat-toolbar: true !default;
        $compat-user_icons: true !default;
        $compat-utilities: true !default;

    To disable a component, add the component you want to remove compatibility 
    for to `/src/css/_clay_custom.scss` (create this file if it doesn't exist) 
    and set its value to `false`. The example below removes compatibility for 
    alerts and cards:
    
        $compat-alerts: false !default;
        $compat-cards: false !default;

    +$$$

    **Note:** Some @product@ components haven't been migrated to Bootstrap 4. 
    Disabling certain components might cause portions of the UI to break. 
    Therefore, after upgrading your markup, we recommend that you re-enable any 
    components you disable. Proceed with caution. 

    $$$

3.  Update your markup to Bootstrap 4 and Clay CSS until you're satisfied with 
    the result.

4.  Re-enable any components you disabled in the compatibility layer by 
    removing any components you set to false in `/src/css/_clay_custom.scss`. 
    This ensures that @product@'s UI isn't broken.

Now you know how to use the Bootstrap 3 and Lexicon CSS compatibility layer to 
provide a smooth transition during your theme upgrade. 
    
## Related Topics [](id=related-topics)

[Updating CSS Code](/develop/tutorials/-/knowledge_base/7-1/updating-css-code)

[Updating Project Metadata](/develop/tutorials/-/knowledge_base/7-1/updating-project-metadata)
