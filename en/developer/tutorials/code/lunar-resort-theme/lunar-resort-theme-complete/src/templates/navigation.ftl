<div class="mx-1 mx-sm-3 order-md-1 lunar-user">
	<@liferay.user_personal_bar />
</div>

<div class="collapse navbar-collapse" id="lunarNav">
<nav class="${nav_css_class}" id="navigation" role="navigation">
	<h1 class="hide-accessible"><@liferay.language key="navigation" /></h1>

	<ul aria-label="<@liferay.language key="site-pages" />" class="navbar-nav mr-auto" role="menubar">
		<#list nav_items as nav_item>
			<#assign
				nav_item_attr_has_popup = ""
				nav_item_css_class = "nav-item"
				nav_item_layout = nav_item.getLayout()
				nav_item_caret = ""
			/>

			<#if nav_item.isSelected()>
				<#assign
					nav_item_attr_has_popup = "aria-haspopup='true'"
					nav_item_css_class = "${nav_item_css_class} selected"
				/>
			</#if>
			
			<#if nav_item.hasChildren()>
				<#assign
					nav_item_css_class = "${nav_item_css_class} dropdown"
					nav_item_caret = '<svg class="lexicon-icon"><use xlink:href="${images_folder}/lexicon/icons.svg#caret-bottom" /></svg>'
				/>
			</#if>

			<li class="${nav_item_css_class}" id="layout_${nav_item.getLayoutId()}" role="presentation">
				<a aria-labelledby="layout_${nav_item.getLayoutId()}" class="nav-link" ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem"><span><@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()}</span> ${nav_item_caret}</a>

				<#if nav_item.hasChildren()>
				
					<ul class="child-menu dropdown-menu" role="menu">
						<#list nav_item.getChildren() as nav_child>
							<#assign
								nav_child_css_class = "nav-item"
							/>

							<#if nav_item.isSelected()>
								<#assign
									nav_child_css_class = "nav-item selected"
								/>
							</#if>

							<li class="${nav_child_css_class}" id="layout_${nav_child.getLayoutId()}" role="presentation">
								<a aria-labelledby="layout_${nav_child.getLayoutId()}" class="nav-link" href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem">${nav_child.getName()}</a>
							</li>
						</#list>
					</ul>
				</#if>
			</li>
		</#list>
	</ul>
</nav>

<a aria-controls="book-now" class="btn text-white btn-orange order-md-2">
	<p class="book-now-text mb-0">Book Now</p>
</a>
</div>