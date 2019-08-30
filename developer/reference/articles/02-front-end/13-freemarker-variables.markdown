---
header-id: freemarker-variable-reference-guide
---

# Freemarker Variable Reference Guide

[TOC levels=1-4]

By default, FreeMarker templates have access to several variables defined in 
[`init.ftl`](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/frontend-theme/frontend-theme-unstyled/src/main/resources/META-INF/resources/_unstyled/templates/init.ftl) 
that you can use in your [themes](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction) 
to access several theme objects, settings, and resources. Several of these 
variables are listed below for reference:

**Common Variables**

| Variable | Description |
| --- | --- |
| `theme_display` | Returns the `themeDisplay` Java Object and all its methods |
| `portlet_display` | Returns the `portletDisplay` Java Object and all its methods |
| `layoutSet` | Returns the page set |
| `theme_timestamp` | Prints the date in the current locale with the given format |
| `theme_settings` | Retrieves theme settings. See [configurable theme settings](/docs/7-2/frameworks/-/knowledge_base/f/making-configurable-theme-settings) for more information. |
| `root_css_class` | Returns the root CSS class which indicates the direction of the page (`ltr` (left-to-right) by default)  |
| `css_class` | Returns a string of the current classes applied to the body of the page |
| `page_group` | Retrieves the page group |
| `css_folder` | Returns the path to the theme's `css` folder |
| `images_folder` | Returns the path to the theme's `images` folder |
| `javascript_folder` | Returns the path to the theme's `javascript` folder |
| `templates_folder` | Returns the path to the theme's `templates` folder |
| `full_css_path` | Returns the full path, which includes the servlet context, to the theme's `css` |
| `full_templates_path` | returns the full path, which includes the servlet context, to the theme's `templates` |
| `css_main_file` | Returns the path to `main.css` |
| `js_main_file` | Returns the path to `main.js` |
| `company_id` | Returns the company ID |
| `company_name` | Returns the company name |
| `company_logo` | Returns the company logo's URL |
| `company_logo_height` | Returns the company logo's height |
| `company_logo_width` | Returns the company logo's width |
| `company_url` | Returns the URL of the home page for the company |
| `time_zone` | Returns the time zone for the current user |
| `is_login_redirect_required` | Returns whether a login redirect is required for the user |
| `is_signed_in` | Returns whether the user is signed in |
| `group_id` | Returns the group ID for the current user |
| `time_zone` | Returns the time zone for the current user |
| `is_default_user` | Returns if the user has a default role |
| `is_female` | Returns if the current user is Female |
| `is_male` | Returns if the current user is Male |
| `is_setup_complete` | Returns whether the user has configured their profile |
| `language` | Returns the native language for the current user |
| `language_id` | Returns the ID of the current locale |
| `user_birthday` | Returns the current user's birthday |
| `user_comments` | Returns comments from the user's profile |
| `user_email_address` | Returns the user's email address |
| `user_first_name` | Returns the user's first name |
| `user_greeting` | Returns the user's greeting |
| `user_id` | Returns the ID of the current user |
| `user_last_login_ip` | Returns the IP address that the user last logged in from |
| `user_last_name` | Returns the last name of the current user |
| `user_login_ip` | Returns the current user's current IP address |
| `user_middle_name` | Returns the user's middle name |
| `user_name` | Returns the current user's username |
| `w3c_language_id` | Returns the W3C language code of the current language |

**URLs**

| Variable | Description |
| --- | --- |
| `show_control_panel` | Returns whether the current user has permission to view the Control Panel |
| `control_panel_text` | Returns the "control-panel" language key in the current user's locale, if they have permission to view the Control Panel |
| `control_panel_url` | Returns the URL to the Control Panel, if the current user has permission to view the Control Panel |
| `show_home` | Returns whether the current user is on a page |
| `home_text` | Returns the "home" language key in the current user's locale |
| `home_url` | Returns the URL to the home page |
| `show_my_account` | Returns whether the current user's account icon is visible |
| `my_account_text` | Returns the "my-account" language key in the current user's locale, if the user's account icon is visible |
| `my_account_url` | Returns the URL to the user's Account Settings page if the user's account icon is visible |
| `show_sign_in` | Returns whether the sign in link is visible |
| `sign_in_text` | Returns the "sign-in" language key in the current user's locale, if they are signed out |
| `sign_in_url` | Returns the sign in URL, if the current user is signed out |
| `show_sign_out` | Returns whether the sign out link is visible |
| `sign_out_text` | Returns the "sign-out" language key in the current user's locale, if they are signed in |
| `sign_out_url` | Returns the sign out URL, if the current user is signed in |

**Page**

| Variable | Description |
| --- | --- |
| `the_title` | Returns the current page's title |
| `selectable` | Returns whether the current page is selectable |
| `is_maximized` | Returns whether the page is maximized |
| `page` | Returns the current page (layout) |
| `is_first_child` | Returns whether the current page is the first child page in the navigation |
| `is_first_parent` | Returns whether the current page is the first parent page in the navigation |
| `is_portlet_page` | Returns whether the current page is a widget page (portlet) |
| `site_name` | Returns the site's name |
| `is_guest_group` | Returns whether the current page group is for guests |
| `site_type` | Returns the type of the current site: site, company  site, organization site, or user site |
| `site_default_url` | Returns the default URL for the site |
| `layout_friendly_url` | Returns the friendly URL of the current page |
| `portlet_id` | Returns the portlet ID for the specified portlet |

**Logo**

| Variable | Description |
| --- | --- |
| `logo_css_class` | Returns a string of the current classes applied to the logo. |
| `use_company_logo` | Returns whether the logo is displayed |
| `site_logo_height` | Returns the logo's height |
| `site_logo_width` | Returns the logo's width |
| `show_site_name_supported` | Returns whether the logo is configured to show the site name. The value is `true` if `show_site_name_default` is true. |
| `show_site_name_default` | Returns whether the Show Site Name Default theme setting is enabled |
| `show_site_name` | Returns whether the `showSiteName` property for the current pageset is enabled |
| `logo_description` | Returns the Site's name or nothing if `show_site_name` is enabled. It is used for alternate text for the logo by default. |

**Navigation**

| Variable | Description |
| --- | --- |
| `has_navigation` | Returns whether navigation exists (i.e. at least one page exists) |
| `nav_items` | Returns the current pages as list |
| `nav_css_class` | Returns a string of the current classes applied to the page's navigation |

**My Sites**

| Variable | Description |
| --- | --- |
| `show_my_sites` | Returns whether the current user has a My Sites page |
| `show_my_places` | Returns whether the current user has a My Sites page |
| `my_sites_text` | Returns the "my-sites" language key in the current user's locale |
| `my_places_text` | Returns whether the current user has a My Sites page |

**Includes**

| Variable | Description |
| --- | --- |
| `dir_include` | Returns "/html"  |
| `body_bottom_include` | Returns "${dir_include}/common/themes/body_bottom.jsp" |
| `body_top_include` | Returns "${dir_include}/common/themes/body_top.jsp" |
| `bottom_include` | Returns "${dir_include}/common/themes/bottom.jsp" |
| `top_head_include` | Returns "${dir_include}/common/themes/top_head.jsp" |
| `top_messages_include` | Returns "${dir_include}/common/themes/top_messages.jsp" |

**Date**

| Variable | Description |
| --- | --- |
| `date` | Gives access to the `dateUtil` Java Object and all its methods |
| `current_time` | Returns the current time |
| `the_year` | Returns the current year |
