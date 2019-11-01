package com.liferay.documentation.movedclassreporter;

import java.io.File;

public class BasicClassInfo {

	public BasicClassInfo(File file, String pkg) {
		this.file = file;
		this.name = file.getName();
		this.pkg = pkg;

		this.pkgEnd = pkg;
		int lastDot = pkg.lastIndexOf(".");
		if (lastDot != -1) {
			this.pkgEnd = pkg.substring(lastDot);
		}
	}

	public File getFile() {
		return file;
	}

	public String getName() {
		return name;
	}

	public String getPkg() {
		return pkg;
	}

	public String getPkgEnd() {
		return pkgEnd;
	}

	private File file;
	private String name;
	private String pkg;
	private String pkgEnd;
}
