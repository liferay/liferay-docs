package com.liferay.documentation.movedclassreporter;

public class MovedClassInfo {
	public MovedClassInfo(BasicClassInfo basicClassInfo) {
		this.basicClassInfo = basicClassInfo;
		this.name = basicClassInfo.getName();
		this.packageOld = basicClassInfo.getPkg();
	}

	public BasicClassInfo getBasicClassInfo() {
		return this.basicClassInfo;
	}

	public String getGroup( ) {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
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

	public String getModuleVersion() {
		return moduleVersion;
	}

	public void setModuleVersion(String moduleVersion) {
		this.moduleVersion = moduleVersion;
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

	BasicClassInfo basicClassInfo;
	private String name;
	private String packageOld;
	private String packageNew;
	private String module;
	private String moduleVersion;
	private String group;

}
