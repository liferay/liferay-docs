package com.liferay.documentation.movedclassreporter;

public class BasicClassInfo {

	public BasicClassInfo(String name, String pkg) {
		this.name = name;
		this.pkg = pkg;
	}

	public String getName() {
		return name;
	}

	public String getPkg() {
		return pkg;
	}

	private String name;
	private String pkg;
}
