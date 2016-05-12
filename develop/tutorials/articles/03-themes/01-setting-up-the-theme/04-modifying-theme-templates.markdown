# Modifying Theme Templates [](id=modifying-theme-templates)

In this section, you'll add some touches to `portal_normal.ftl` and
`navigation.ftl` to match the finished mock up for the Lunar Resort.

1.  Open `portal_normal.ftl` and find the `<body class="${css_class}">` and 
    replace it with the following code:
   
        <body class="${css_class} dockbar-split">
 
    Your theme now has the modern dockbar that's in Liferay's Classic theme.

2.  Find the following code and remove it from `portal_normal.ftl`:

        <#if !is_signed_in>
            <a href="${sign_in_url}" data-redirect="${is_login_redirect_
            required?string}" id="sign-in" rel="nofollow">${sign_in_text}</a>
        </#if>
 
     The default *Sign In* link has been removed, but you'll replace it in the 
     next step.
 
3.  Find the `<@liferay.dockbar />` code and remove the `<#if is_signed_in>` 
    statement and the closing `</#if>` tag that wraps it.

    The updated code now displays the Dockbar whether you are logged in or not.
    If you're logged out, the Dockbar is simplified, and shows a *Sign In* link.
    This will come in handy later in the Learning Path when the theme is made
    responsive.
 
4.  Insert the following code just above the closing `</div>` tag for the content
    `<div>`:
   
        <#if the_title != "Reservation">
            <div id="reserveBtnWrapper">
                <span id="reserveBtn">
                <a href="/web/guest/reservation">Make Your Reservation!</a>
                </span>
            </div>
        </#if>
 
     This adds a link to make a reservation for the Lunar Resort on every page, 
     except the *Reservation* page. At the moment you only have the default 
     *Welcome* page so you may be scratching your head. Don't worry, you'll add 
     the remaining pages later, using the Resources Importer. If you try to make
     a reservation using the link, you'll be greeted with an error, but this is
     to be expected at this point. 

5.  Save the file.

    Next, it's time to update your navigation.
 
6.  Open `navigation.ftl` and replace the contents with the following code:

        <nav class="${nav_css_class}" id="navigation" role="navigation">
            <ul aria-label="<@liferay.language key="site-pages" />" 
            role="menubar">
                <#list nav_items as nav_item>
                    <#assign nav_item_attr_has_popup = "" />
                    <#assign nav_item_attr_selected = "" />
                    <#assign nav_item_css_class = "lfr-nav-item" />
                    <#assign nav_item_caret = "" />
                    <#assign nav_item_link_css_class = "" />

                    <#if nav_item.isSelected()>
                        <#assign nav_item_attr_selected = "aria-selected='true'" 
                        />
                        <#assign nav_item_css_class = "${nav_item_css_class} 
                        selected active" />
                    </#if>
                    <#if nav_item.hasChildren()>
                        <#assign nav_item_attr_has_popup = 
                        "aria-haspopup='true'" />
                        <#assign nav_item_caret = "<b class='icon-caret-down'>
                        </b>" />
                        <#assign nav_item_css_class = "${nav_item_css_class} 
                        dropdown" />
                        <#assign nav_item_link_css_class = "dropdown-toggle" />
                    </#if>

                    <li ${nav_item_attr_selected} class="${nav_item_css_class}" 
                    id="layout_${nav_item.getLayoutId()}" role="presentation">
                        <a aria-labelledby="layout_${nav_item.getLayoutId()}" 
                        ${nav_item_attr_has_popup} 
                        class="${nav_item_link_css_class}" 
                        href="${nav_item.getURL()}" ${nav_item.getTarget()} 
                        role="menuitem"><span>${nav_item.icon()} 
                        ${nav_item.getName()} ${nav_item_caret}</span></a>

                        <#if nav_item.hasChildren()>
                            <ul class="dropdown-menu child-menu" role="menu">
                                <#list nav_item.getChildren() as nav_child>
                                    <#assign nav_child_attr_selected = "" />
                                    <#assign nav_child_css_class = 
                                    "lfr-nav-item" />

                                        <#if nav_item.isSelected()>
                                            <#assign nav_child_attr_selected 
                                            = "aria-selected='true'" />
                                            <#assign nav_child_css_class = 
                                            "selected" />
                                        </#if>

                                        <li class="${nav_child_css_class}" 
                                        id="layout_${nav_child.getLayoutId()}" 
                                        ${nav_child_attr_selected} 
                                        role="presentation">
                                            <a aria-labelledby=
                                            "layout_${nav_child.getLayoutId()}" 
                                            href="${nav_child.getURL()}" 
                                            ${nav_child.getTarget()} 
                                            role="menuitem">
                                            ${nav_child.getName()}</a>
                                        </li>
                                </#list>
                            </ul>
                        </#if>
                    </li>
                </#list>
            </ul>
        </nav>
 
    The updated code is a duplicate of the old code with some additions. The
    main change is the updated child menu classes: child menu items are now
    supported. Additionally, a class called `.active` highlights the current
    page. 

7.  Save the file.

You have the templates nicely wrapped up, so you can move on to deploying 
the theme next.

1.  Once your server is started, select the theme from the Package Explorer, 
    drag it down, and drop it on top of the server to deploy your theme.

2.  Once your theme has been deployed, log in to the Portal and goto *Admin* &rarr;
    *Site Administration* &rarr; *Pages*.

3.  Make sure *Public Pages* is selected and scroll down to the *Available
    Themes* heading.

4.  Select the radio button for the *Lunar Resort* theme and click *Save*.

    The theme is now applied! Before you look at it though, you need to 
    take care of a few more housekeeping items.
 
5.  Select *logo* in the menu on the right and uncheck *Show Site Name*.

6.  Click Browse and select the *lunar-resort-logo.png* in the `_diffs/images`
    directory of the *Lunar Resort Theme*.
 
7.  Click *Save*.

8.  Once you've received the success message, navigate back to the *Welcome* 
    page.

You'll now see your new theme is in place. It should look similar to the figure 
below:

![Figure 1: Liferay's *_styled* base theme is a good starting point.](../../images/theme-creation-02.png)

The theme appears broken at the moment, but this is to be expected. In the 
sections that follow, you'll fix this. This shows you the starting point the 
base theme provides. 

You've successfully installed and applied your new theme! In the next section 
you'll learn how to import resources for your theme.
