package com.liferay.documentation.movedclassreporter;

public class FormerPortalServiceClass {

	public FormerPortalServiceClass(String name, String packageOld) {
		this.name = name;
		this.packageOld = packageOld;
	}

	public String getPackageNew() {
		return packageNew;
	}

	public void setPackageNew(String packageNew) {
		this.packageNew = packageNew;
	}

	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackageOld() {
		return packageOld;
	}

	private String name;
	private String packageOld;
	private String packageNew;
	private String module;
}
