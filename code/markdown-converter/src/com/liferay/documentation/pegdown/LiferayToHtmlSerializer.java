/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.documentation.pegdown;

import com.liferay.documentation.pegdown.ast.PicWithCaptionNode;
import com.liferay.documentation.pegdown.ast.SidebarNode;

import org.pegdown.LinkRenderer;
import org.pegdown.ToHtmlSerializer;
import org.pegdown.ast.SuperNode;

/**
 * Provides a visitor implementation for printing HTML for pictures with
 * captions, "side-bars", and in-line images.
 *
 * @author James Hinkey
 */
public class LiferayToHtmlSerializer extends ToHtmlSerializer {

	public LiferayToHtmlSerializer(LinkRenderer linkRenderer) {
		super(linkRenderer);
	}

	public void visit(PicWithCaptionNode picWithCaptionNode) {
		printPicWithCaptionNode(picWithCaptionNode);
	}

	public void visit(SidebarNode sidebarNode) {
		printSidebarNode(sidebarNode);
	}

	@Override
	public void visit(SuperNode superNode) {
		if (superNode instanceof PicWithCaptionNode) {
			visit((PicWithCaptionNode)superNode);
		}
		else if (superNode instanceof SidebarNode) {
			visit((SidebarNode)superNode);
		}
		else {
			visitChildren(superNode);
		}
	}

	protected void printPicWithCaptionNode(PicWithCaptionNode node) {
		printer.print("<p><img src=\"");
		printer.print(node.getSrc());
		printer.print("\" alt=\"");
		printer.print(node.getAlt());
		printer.print("\"/><p class=\"caption\">");

		visitChildren(node);

		printer.print("</p></p>");
	}

	protected void printSidebarNode(SidebarNode node) {
		printer.print("<p><img src=\"");
		printer.print(node.getSrc());
		printer.print("\" alt=\"");
		printer.print(node.getAlt());
		printer.print("\"/>");

		visitChildren(node);

		printer.print("</p>");
	}

}