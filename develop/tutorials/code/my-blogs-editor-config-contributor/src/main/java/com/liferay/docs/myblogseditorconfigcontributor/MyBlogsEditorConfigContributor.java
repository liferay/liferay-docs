package com.liferay.docs.myblogseditorconfigcontributor;

import javax.portlet.Portlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
import com.liferay.portal.kernel.editor.configuration.EditorConfigContributor;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author liferay
 */
 @Component(
 	immediate = true,
 	property = {
 		"editor.config.key=contentEditor",
 		"editor.name=alloyeditor",
 		"editor.name=ckeditor",
 		"javax.portlet.name=com_liferay_blogs_web_portlet_BlogsPortlet",
 		"javax.portlet.name=com_liferay_blogs_web_portlet_BlogsAdminPortlet",
 		"service.ranking:Integer=100"
 	},
 	service = EditorConfigContributor.class
 )
 public class MyBlogsEditorConfigContributor
   extends BaseEditorConfigContributor {
 
   @Override
   public void populateConfigJSONObject(
     JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
     ThemeDisplay themeDisplay,
     RequestBackedPortletURLFactory requestBackedPortletURLFactory) {
			 
			String extraPlugins = jsonObject.getString("extraPlugins");
			 
			 if (Validator.isNotNull(extraPlugins)) {
				  extraPlugins = extraPlugins + ",ae_uibridge,ae_autolink,ae_buttonbridge,ae_menubridge,ae_panelmenubuttonbridge,ae_placeholder,ae_richcombobridge,font"; 
				}
				else {
				  extraPlugins = "ae_uibridge,ae_autolink,ae_buttonbridge,ae_menubridge,ae_panelmenubuttonbridge,ae_placeholder,ae_richcombobridge,font";
				}

				jsonObject.put("extraPlugins", extraPlugins);
				
				JSONObject toolbarsJSONObject = jsonObject.getJSONObject("toolbars");

				if (toolbarsJSONObject == null) {
				 toolbarsJSONObject = JSONFactoryUtil.createJSONObject();
				}

				JSONObject stylesJSONObject = toolbarsJSONObject.getJSONObject(
				 "styles");

				if (stylesJSONObject == null) {
				 stylesJSONObject = JSONFactoryUtil.createJSONObject();
				}

				JSONArray selectionsJSONArray = stylesJSONObject.getJSONArray(
				 "selections");

				for (int i = 0; i < selectionsJSONArray.length(); i++) {
				 JSONObject selection = selectionsJSONArray.getJSONObject(i);

						if (Objects.equals(selection.get("name"), "text")) {
						 JSONArray buttons = selection.getJSONArray("buttons");

						 buttons.put("Font");
						 buttons.put("FontSize");
						}
 
				 }
		 
				stylesJSONObject.put("selections", selectionsJSONArray);

				toolbarsJSONObject.put("styles", stylesJSONObject);

				jsonObject.put("toolbars", toolbarsJSONObject);
   }
 }