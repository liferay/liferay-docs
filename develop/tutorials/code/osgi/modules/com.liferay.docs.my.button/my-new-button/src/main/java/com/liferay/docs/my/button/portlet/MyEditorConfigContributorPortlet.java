package com.liferay.docs.my.button.portlet;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
import com.liferay.portal.kernel.editor.configuration.EditorConfigContributor;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.Map;
import java.util.Objects;
import com.liferay.portal.kernel.util.Validator;
/**
 * @author liferay
 */
 @Component(
         immediate = true,
         property = {
           "editor.name=alloyeditor",
           "service.ranking:Integer=100"
         },
         service = EditorConfigContributor.class
 )
public class MyEditorConfigContributorPortlet extends BaseEditorConfigContributor {

	@Override
	public void populateConfigJSONObject(
					JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
					ThemeDisplay themeDisplay,
					RequestBackedPortletURLFactory requestBackedPortletURLFactory) {

					JSONObject toolbarsJSONObject = jsonObject.getJSONObject("toolbars");

					if (toolbarsJSONObject == null) {
									toolbarsJSONObject = JSONFactoryUtil.createJSONObject();
					}

          String extraPlugins = jsonObject.getString("extraPlugins");

					if (Validator.isNotNull(extraPlugins)) {
					  extraPlugins = extraPlugins + ",ae_uibridge,ae_autolink,ae_buttonbridge,ae_menubridge,ae_panelmenubuttonbridge,ae_placeholder,ae_richcombobridge,clipboard";
					}
					else {
					  extraPlugins = "ae_uibridge,ae_autolink,ae_buttonbridge,ae_menubridge,ae_panelmenubuttonbridge,ae_placeholder,ae_richcombobridge,clipboard";
					}

					jsonObject.put("extraPlugins", extraPlugins);

					JSONObject addJSONObject = toolbarsJSONObject.getJSONObject(
									"add");

					if (addJSONObject == null) {
									addJSONObject = JSONFactoryUtil.createJSONObject();
					}

          JSONArray addbuttons = addJSONObject.getJSONArray("buttons");
          
          addbuttons.put("camera");

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
													JSONArray Selectionbuttons = selection.getJSONArray("buttons");

                          Selectionbuttons.put("Cut");
													Selectionbuttons.put("Copy");
													Selectionbuttons.put("Paste");
									}
					}

          stylesJSONObject.put("selections", selectionsJSONArray);

					addJSONObject.put("buttons", addbuttons);

					toolbarsJSONObject.put("add", addJSONObject);

					jsonObject.put("toolbars", toolbarsJSONObject);
	}

}