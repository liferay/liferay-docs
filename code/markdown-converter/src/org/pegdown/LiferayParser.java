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

package org.pegdown;

import com.liferay.documentation.pegdown.ast.PicWithCaptionNode;
import com.liferay.documentation.pegdown.ast.SidebarNode;

import org.parboiled.Rule;
import org.parboiled.common.ArrayBuilder;

/**
 * Provides rules for parsing pictures with captions, "side-bars", and in-line
 * images.
 *
 * @author James Hinkey
 */
public class LiferayParser extends Parser {

	public LiferayParser(Integer options) {
		super(options);
	}

	public LiferayParser(
		Integer options, ParseRunnerProvider parseRunnerProvider) {

		super(options, parseRunnerProvider);
	}

	@Override
	public Rule Block() {
		ArrayBuilder<Rule> arrayBuilder = new ArrayBuilder<Rule>();

		arrayBuilder.add(BlockQuote(), Verbatim());

		if (ext(ABBREVIATIONS)) {
			arrayBuilder.addNonNulls(Abbreviation());
		}

		arrayBuilder.add(
			PictureWithCaption(), Sidebar(), Reference(), HorizontalRule(),
			Heading(), OrderedList(), BulletList(), HtmlBlock());

		if (ext(TABLES)) {
			arrayBuilder.addNonNulls(Table());
		}

		if (ext(DEFINITIONS)) {
			arrayBuilder.addNonNulls(DefinitionList());
		}

		if (ext(FENCED_CODE_BLOCKS)) {
			arrayBuilder.addNonNulls(FencedCodeBlock());
		}

		arrayBuilder.add(Para(), Inlines());

		return Sequence(ZeroOrMore(BlankLine()), FirstOf(arrayBuilder.get()));
	}

	public Rule PictureWithCaption() {
		return NodeSequence(
			TestNot(OneOrMore(CharEntity())), '!', Label(), Spn1(), '(', Sp(),
			LinkSource(), Spn1(), FirstOf(LinkTitle(), push("")), Sp(), ')',
			TestNot(OneOrMore(Spacechar())), TestNot(OneOrMore(CharEntity())),
			drop(), push(new PicWithCaptionNode(popAsString(), popAsNode())));
	}

	public Rule Sidebar() {
		return NodeSequence(
			TestNot(OneOrMore(CharEntity())), '!', Label(), Spn1(), '(', Sp(),
			LinkSource(), Spn1(), FirstOf(LinkTitle(), push("")), Sp(), ')',
			Sp(), Para(),
			push(
				new SidebarNode(
					popAsNode(), popAsString(), popAsString(), popAsNode())));
	}

}