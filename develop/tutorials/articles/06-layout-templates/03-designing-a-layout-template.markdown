# Designing a layout template [](id=designing-a-layout-template)

Initially, the layout template's generated TPL files are empty, a fresh canvas
on which you design layout templates. If this seems overwhelming, don't worry.
With the handy palette in Developer Studio's *Layout Template Editor*,
designing templates becomes fun! Let's try it:

1.  Open your layout template's TPL file in Developer Studio's *Layout Template
    Editor* to see it displayed in *Visual (Experimental)* mode. 

2.  Click the triangle in the upper left corner of the editor to view the
    Palette. Drag rows, columns, and even existing templates onto your
    canvas. 

3.  Start designing your new layout template by dragging an *existing* layout
    template into the editor. Then modify it by adding, deleting, and adjusting
    elements as you like. 

4.  After adding rows and columns, drag their edges to achieve the desired
    dimensions. Your layout isn't etched in stone. You can make changes to your
    layout template whenever you need by using *Visual (Experimental)* mode in
    Developer Studio. 

    ![Figure 6.3: Visually adjusted template](../../images/layout-template-tpl-visual-almost.png)

5.  To fine tune your design's dimensions, switch to *Source* view and edit
    your TPL files as necessary. 

![Figure 6.4: Source view of the template](../../images/layout-template-tpl-src-almost.png)

For example, if you want each second-row column of the above template to be 25%
of the page width, adjust that row's first column (`column-2`) from 20% to 25%. 

Here's how the original 20% column width definition looks:

	<div class="aui-w20 portlet-column portlet-column-first" id="column-2">
		$processor.processColumn("column-2", "portlet-column-content portlet-column-content-first")
	</div>

Here's the column width definition modified to 25%:

	<div class="aui-w25 portlet-column portlet-column-first" id="column-2">
		$processor.processColumn("column-2", "portlet-column-content portlet-column-content-first")
	</div>

It's hard to see, but we changed the column's class `aui-w20` value to
`aui-w25`. 

Just like that, the rows and columns of the *Columns 1 4 1* layout template are
arranged and sized evenly. 

Now that we've generated some positive Feng Shui through the design of our
layout, let's increase our control over the layout by embedding portlets. 
