# Creating Layout Templates Manually [](id=creating-layout-templates-manually)

If you've checked the [Layout Templates with the Themes Generator](creating-layout-templates-with-the-themes-generator-0) tutorial, you should know by now what Layout Templates are and how to generate them automatically.

In this tutorial you'll learn how to manually create or modify a Layout Template.

## Basic Layout Template [](id=basic-layout-template)

Below you can see an example of a basic Layout Template.

```html
<div class="my-liferay-layout" id="main-content" role="main">
	<div class="portlet-layout row">
		<div class="col-md-4 portlet-column portlet-column-first" id="column-1">
			$processor.processColumn("column-1", "portlet-column-content portlet-column-content-first")
		</div>
		<div class="col-md-8 portlet-column portlet-column-last" id="column-2">
			$processor.processColumn("column-2", "portlet-column-content portlet-column-content-last")
		</div>
	</div>
	<div class="portlet-layout row">
		<div class="col-md-12 portlet-column portlet-column-only" id="column-3">
			$processor.processColumn("column-3", "portlet-column-content portlet-column-content-only")
		</div>
	</div>
	<div class="portlet-layout row">
		<div class="col-md-4 portlet-column portlet-column-first" id="column-4">
			$processor.processColumn("column-4", "portlet-column-content portlet-column-content-first")
		</div>
		<div class="col-md-4 portlet-column" id="column-5">
			$processor.processColumn("column-5", "portlet-column-content")
		</div>
		<div class="col-md-4 portlet-column portlet-column-last" id="column-6">
			$processor.processColumn("column-6", "portlet-column-content portlet-column-content-last")
		</div>
	</div>
</div>
```

## Column Elements and Classes [](id=column-elements-and-classes)

Let's take a close look at the first column of the previous example and the various elements it consists of.

```html
<div class="col-md-4 portlet-column portlet-column-first" id="column-1">
	$processor.processColumn("column-1", "portlet-column-content portlet-column-content-first")
</div>
```

### Column Container [](id=column-container)

- `column-1`

	Unique identifier for the column that matches ID passed to `$processor.processColumn`.

- `col-md-4`

	This class comes from Bootstrap's grid system and determines two things, the percentage based width of the element, and the media query breakpoint for when this element expands to 100% width. 12 is the maximum amount, so `col-md-4` indicates `4/12` width, or `33.33%`.

- `portlet-column portlet-column-first`

	All column containers should have the `portlet-column` class. For rows with more than one column, the first column will have `portlet-column-first` and the last will have `portlet-column-last`. For rows with only one column, the column will have the `portlet-column-only` class.

### $processor.processColumn

- `column-1`

	Unique identifier, should match ID of the parent `div`.

- `portlet-column-content portlet-column-content-first`

	Additional classes added to the content element, classes match the parent `div`'s classes with `-content` appended.

## Modifying Template Breakpoints [](id=modifying-template-breakpoints)

When looking at the example template you'll notice this Bootstrap grid class used on every column.

```
col-md-{size}
```

The different sizes available are `xs`, `sm`, `md`, and `lg`. The medium size is used by default, but the others can be used in layout templates as well.

For example, setting the column classes to `col-lg-{size}` means the columns would expand to 100% width at a larger screen width than `col-md-{size}`.

These classes can also be mixed to achieve more advanced layouts.

```
<div class="portlet-layout row">
	<div class="col-md-4 col-sm-6 portlet-column portlet-column-first" id="column-1">
		$processor.processColumn("column-1", "portlet-column-content portlet-column-content-first")
	</div>
	<div class="col-md-8 col-sm-6 portlet-column portlet-column-last" id="column-2">
		$processor.processColumn("column-2", "portlet-column-content portlet-column-content-last")
	</div>
</div>
```

In this example row, on medium sized view ports `column-1` will be 33.33% width and `column-2` will be 66.66% width, but on small sized view ports both `column-1` and `column-2` will be 50% width.
