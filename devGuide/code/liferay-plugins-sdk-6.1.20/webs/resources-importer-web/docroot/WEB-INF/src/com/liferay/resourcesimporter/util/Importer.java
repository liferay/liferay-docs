/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.resourcesimporter.util;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public interface Importer {

	public void afterPropertiesSet() throws Exception;

	public long getGroupId();

	public long getTargetClassPK();

	public void importResources() throws Exception;

	public boolean isExisting();

	public void setCompanyId(long companyId);

	public void setResourcesDir(String resourcesDir);

	public void setServletContext(ServletContext servletContext);

	public void setServletContextName(String servletContextName);

	public void setTargetClassName(String className);

	public void setTargetValue(String targetValue);

}