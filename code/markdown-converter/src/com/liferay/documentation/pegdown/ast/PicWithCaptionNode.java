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
 * Represents picture with an associated caption.
 *
 * @author James Hinkey
 */
public class PicWithCaptionNode extends SuperNode {

	public PicWithCaptionNode(String src, Node node) {
		super(node);

		_src = src;

		List<Node> nodes = node.getChildren();

		if ((nodes != null) && !nodes.isEmpty() &&
			(nodes.get(0) instanceof TextNode)) {

			TextNode textNode = (TextNode)nodes.get(0);

			_alt = textNode.getText();
		}
		else {
			_alt = _BLANK;
		}
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

	private static final String _BLANK = "";

	private final String _alt;
	private final String _src;

}