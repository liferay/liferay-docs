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

package com.liferay.docs.guestbook.service.persistence.impl.constants;

import com.liferay.petra.string.StringBundler;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Liferay
 * @generated
 */
@Component(immediate = true, service = {})
public class GBPersistenceConstants {

	public static final String BUNDLE_SYMBOLIC_NAME =
		"com.liferay.docs.guestbook.service";

	public static final String ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER =
		"(origin.bundle.symbolic.name=" + BUNDLE_SYMBOLIC_NAME + ")";

	@Activate
	protected void activate(BundleContext bundleContext) {
		Bundle bundle = bundleContext.getBundle();

		if (!BUNDLE_SYMBOLIC_NAME.equals(bundle.getSymbolicName())) {
			throw new IllegalStateException(
				StringBundler.concat(
					"Incorrect ", Constants.BUNDLE_SYMBOLICNAME, " for bundle ",
					bundle.getSymbolicName()));
		}
	}

}