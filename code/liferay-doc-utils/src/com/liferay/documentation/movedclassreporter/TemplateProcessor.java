package com.liferay.documentation.movedclassreporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TemplateProcessor {

	public void processMovedClassesTemplate(List<FormerPortalServiceClass> movedClasses, File article, String oldZipName, String newZipName) {
		// Write report using FreeMarker template
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		try {
			cfg.setDefaultEncoding("UTF-8");

			ClassTemplateLoader ctl = new ClassTemplateLoader(getClass(), TPL_ROOT);
			
			cfg.setTemplateLoader(ctl);

			Map root = new HashMap();

			root.put("oldSrcZip", oldZipName);
			root.put("newSrcZip", newZipName);
			root.put("movedClasses", movedClasses);
			
			Template temp = cfg.getTemplate("moved-classes.ftl");

			FileWriter out = new FileWriter(article);
			temp.process(root, out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void processRemovedClassesTemplate(List<FormerPortalServiceClass> removedClasses, File article, String oldZipName, String newZipName) {
		// Write report using FreeMarker template
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		try {
			cfg.setDefaultEncoding("UTF-8");

			ClassTemplateLoader ctl = new ClassTemplateLoader(getClass(), TPL_ROOT);
			
			cfg.setTemplateLoader(ctl);

			Map root = new HashMap();

			root.put("oldSrcZip", oldZipName);
			root.put("newSrcZip", newZipName);
			root.put("removedClasses", removedClasses);
			
			Template temp = cfg.getTemplate("removed-classes.ftl");

			FileWriter out = new FileWriter(article);
			temp.process(root, out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static final String TPL_ROOT =
		"/com/liferay/documentation/movedclassreporter/dependencies/";
}
