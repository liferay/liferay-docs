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

package com.liferay.documentation.pegdown.ast;

import java.util.List;

import org.pegdown.ast.Node;
import org.pegdown.ast.SuperNode;
import org.pegdown.ast.TextNode;
import org.pegdown.ast.Visitor;

/**
 * Represents a "side-bar" image with associated text. A "side-bar" is currently
 * set apart from the surrounding text of a document by a horizontal rule above
 * and below the image and its associated text. The image is typically an icon
 * (e.g. a pen tip for a tip side-bar) that denotes the type of the "side-bar".
 *
 * @author James Hinkey
 */
public class SidebarNode extends SuperNode {

	public SidebarNode(Node captionNode, String title, String src, Node node) {
		super(captionNode);

		_alt = _getText(node);
		_src = src;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public String getAlt() {
		return _alt;
	}

	public String getSrc() {
		return _src;
	}

	private String _getText(Node node) {
		if (node == null) {
			return _BLANK;
		}

		List<Node> nodes = node.getChildren();

		if ((nodes != null) && !nodes.isEmpty() &&
			(nodes.get(0) instanceof TextNode)) {

			TextNode textNode = (TextNode)nodes.get(0);

			return textNode.getText();
		}

		return _BLANK;
	}

	private static final String _BLANK = "";

	private String _alt;
	private String _src;

}