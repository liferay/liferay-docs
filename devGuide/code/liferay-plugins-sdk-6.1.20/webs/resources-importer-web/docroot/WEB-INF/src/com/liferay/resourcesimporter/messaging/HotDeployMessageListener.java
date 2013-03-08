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

package com.liferay.resourcesimporter.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.resourcesimporter.util.FileSystemImporter;
import com.liferay.resourcesimporter.util.Importer;
import com.liferay.resourcesimporter.util.ImporterException;
import com.liferay.resourcesimporter.util.LARImporter;
import com.liferay.resourcesimporter.util.ResourceImporter;

import java.io.IOException;

import java.net.URL;
import java.net.URLConnection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;

/**
 * @author Ryan Park
 * @author Raymond Augé
 */
public class HotDeployMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");

		if (!command.equals("deploy")) {
			return;
		}

		String servletContextName = message.getString("servletContextName");

		ServletContext servletContext = ServletContextPool.get(
			servletContextName);

		URL resourcesDirURL = servletContext.getResource(_RESOURCES_DIR);

		if (resourcesDirURL == null) {
			return;
		}

		Properties pluginPackageProperties = getPluginPackageProperties(
			servletContext);

		String targetClassName = pluginPackageProperties.getProperty(
			"resources-importer-target-class-name",
			LayoutSetPrototype.class.getName());

		Set<String> resourcePaths = servletContext.getResourcePaths(
			_RESOURCES_DIR);
		URL larURL = servletContext.getResource(
			_RESOURCES_DIR.concat("archive.lar"));

		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (Company company : companies) {
			long companyId = CompanyThreadLocal.getCompanyId();

			try {
				CompanyThreadLocal.setCompanyId(company.getCompanyId());

				Importer importer = null;

				if (larURL != null) {
					LARImporter larImporter = getLARImporter();

					URLConnection urlConnection = larURL.openConnection();

					larImporter.setLARInputStream(
						urlConnection.getInputStream());

					importer = larImporter;
				}
				else if ((resourcePaths != null) && !resourcePaths.isEmpty()) {
					importer = getResourceImporter();

					importer.setResourcesDir(_RESOURCES_DIR);
				}
				else {
					String resourcesDir = pluginPackageProperties.getProperty(
						"resources-importer-external-dir");

					if (Validator.isNotNull(resourcesDir)) {
						importer = getFileSystemImporter();

						importer.setResourcesDir(resourcesDir);
					}
				}

				if (importer == null) {
					throw new ImporterException("No valid importer found");
				}

				importer.setCompanyId(company.getCompanyId());
				importer.setServletContext(servletContext);
				importer.setServletContextName(servletContextName);
				importer.setTargetClassName(targetClassName);

				String targetValue = pluginPackageProperties.getProperty(
					"resources-importer-target-value");

				if (Validator.isNull(targetValue)) {
					targetValue = TextFormatter.format(
						servletContextName, TextFormatter.J);
				}

				importer.setTargetValue(targetValue);

				importer.afterPropertiesSet();

				boolean developerModeEnabled = GetterUtil.getBoolean(
					pluginPackageProperties.getProperty(
						"resources-importer-developer-mode-enabled"));

				if (!developerModeEnabled && importer.isExisting()) {
					if (_log.isInfoEnabled()) {
						_log.info(
							"Group or layout set prototype already exists " +
								"for company " + company.getWebId());
					}

					continue;
				}

				long startTime = 0;

				if (_log.isInfoEnabled()) {
					startTime = System.currentTimeMillis();
				}

				importer.importResources();

				if (_log.isInfoEnabled()) {
					long endTime = System.currentTimeMillis() - startTime;

					_log.info(
						"Importing resources from " + servletContextName +
							" to group " + importer.getGroupId() + " takes " +
								endTime + " ms");
				}

				Message newMessage = new Message();

				newMessage.put("companyId", company.getCompanyId());
				newMessage.put("servletContextName", servletContextName);
				newMessage.put("targetClassName", targetClassName);
				newMessage.put("targetClassPK", importer.getTargetClassPK());

				if (message.getResponseId() != null) {
					Map<String, Object> responseMap =
						new HashMap<String, Object>();

					responseMap.put("groupId", importer.getTargetClassPK());

					newMessage.setPayload(responseMap);

					newMessage.setResponseId(message.getResponseId());
				}

				MessageBusUtil.sendMessage(
					"liferay/resources_importer", newMessage);
			}
			catch (ImporterException ie) {
				Message newMessage = new Message();

				newMessage.put("companyId", company.getCompanyId());
				newMessage.put("error", ie.getMessage());
				newMessage.put("servletContextName", servletContextName);
				newMessage.put("targetClassName", targetClassName);
				newMessage.put("targetClassPK", 0);

				MessageBusUtil.sendMessage(
					"liferay/resources_importer", newMessage);
			}
			finally {
				CompanyThreadLocal.setCompanyId(companyId);
			}
		}
	}

	protected FileSystemImporter getFileSystemImporter() {
		return new FileSystemImporter();
	}

	protected LARImporter getLARImporter() {
		return new LARImporter();
	}

	protected Properties getPluginPackageProperties(
		ServletContext servletContext) {

		Properties properties = new Properties();

		try {
			String propertiesString = StringUtil.read(
				servletContext.getResourceAsStream(
					"/WEB-INF/liferay-plugin-package.properties"));

			if (propertiesString != null) {
				String contextPath = servletContext.getRealPath(
					StringPool.SLASH);

				contextPath = StringUtil.replace(
					contextPath, StringPool.BACK_SLASH, StringPool.SLASH);

				propertiesString = propertiesString.replace(
					"${context.path}", contextPath);

				PropertiesUtil.load(properties, propertiesString);
			}
		}
		catch (IOException e) {
			_log.error(e, e);
		}

		return properties;
	}

	protected ResourceImporter getResourceImporter() {
		return new ResourceImporter();
	}

	private static final String _RESOURCES_DIR =
		"/WEB-INF/classes/resources-importer/";

	private static Log _log = LogFactoryUtil.getLog(
		HotDeployMessageListener.class);

}