package com.liferay.documentation.movedclassreporter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class MovedClassReporterMain {

	private static final String PORTAL_KERNEL_JAR = "portal-kernel.jar";

	private static final String PORTAL_SERVICE_JAR = "portal-service.jar";

	private static final String USAGE = "Usage: MovedClassReporter oldLiferayDir newLiferayDir movedClassesOutputFile";

	public static void main(String[] args) {
		if (args == null || args.length < 3) {
			System.out.println(USAGE);
			return;
		}

		File oldDir = new File(args[0]);
		File newDir = new File(args[1]);
		File movedClassesOutputFile = new File(args[2]);

		if (!oldDir.isDirectory()) {
			System.out.println("oldLiferayDir is not a directory: " + args[0]);
		}

		if (!newDir.isDirectory()) {
			System.out.println("newLiferayDir is not a directory: " + args[1]);
		}

		// Create a list of all portal-kernel classes

		File newKernelSrcDir = new File(newDir + "/" + PORTAL_KERNEL_SRC);
		if (!newKernelSrcDir.exists()) {
			System.out.println("Expected folder not found: " + newKernelSrcDir.getPath());
			System.out.println(USAGE);
			return;
		}

		System.out.println("New kernel dir: " + newKernelSrcDir.getPath());

		ArrayList<File> newKernelClasses = new ArrayList<File>();

		MovedClassReporterMain.listJavaFiles(newKernelSrcDir, newKernelClasses);

		System.out.println("New kernel dir class count: " + newKernelClasses.size());


		// Create list of portal-service/portal-kernel class files

		File oldKernelSrcDir = new File(oldDir.getPath() + "/" + PORTAL_SERVICE_SRC);
		String oldJar = PORTAL_SERVICE_JAR;

		// If portal-service doesn't exist, use old portal-kernel
		if (!oldKernelSrcDir.exists()) {
			oldKernelSrcDir = new File(oldDir.getPath() + "/" + PORTAL_KERNEL_SRC);
			oldJar = PORTAL_KERNEL_JAR;

			if (!oldKernelSrcDir.exists()) {
				System.out.println("No portal-service or portal-kernel source files in oldLiferayDir: " + oldDir.getPath());
				System.out.println(USAGE);
				return;
			}
		}

		System.out.println("Old kernel dir: " + oldKernelSrcDir.getPath());

		ArrayList<File> oldKernelClasses = new ArrayList<File>();

		MovedClassReporterMain.listJavaFiles(oldKernelSrcDir, oldKernelClasses);

		System.out.println("Old kernel dir class count: " + oldKernelClasses.size());


		// Create list of service class BasicClassInfo objects

		List<BasicClassInfo> serviceBasicClassInfos = new ArrayList<BasicClassInfo>();

		for (File file : oldKernelClasses) {
			String pkg = "";
			try {
				pkg = getPackage(file);
				BasicClassInfo info = new BasicClassInfo(file, pkg);
				serviceBasicClassInfos.add(info);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Add to a new list of BasicClassInfo objects all service classes not in new kernel
		List<MovedClassInfo> classesNotInKernel = new ArrayList<MovedClassInfo>();

		for (BasicClassInfo serviceInfo: serviceBasicClassInfos) {
			String serviceFileName = serviceInfo.getName();

			boolean matched = false;
			for (File newKernelClass : newKernelClasses) {
				if (serviceFileName.equals(newKernelClass.getName())) {
					matched = true;
					break;
				}
			}

			if (!matched) {
				MovedClassInfo formerClass =
					new MovedClassInfo(serviceInfo);
				classesNotInKernel.add(formerClass);
			}
		}

		System.out.println("classesNotInKernel: " + classesNotInKernel.size());


		ArrayList<File> moduleFiles = new ArrayList<File>();

		File modulesAppsDir = new File(newDir + "/" + MODULES_APPS);
		listJavaFiles(modulesAppsDir, moduleFiles);

		File modulesCoreDir = new File(newDir + "/" + MODULES_CORE);
		listJavaFiles(modulesCoreDir, moduleFiles);

		File modulesUtilDir = new File(newDir + "/" + MODULES_UTIL);
		listJavaFiles(modulesUtilDir, moduleFiles);

		System.out.println("moduleFiles: " + moduleFiles.size());

		// Create list of module class BasicClassInfo objects
		List<BasicClassInfo> moduleBasicClassInfos = new ArrayList<BasicClassInfo>();
		for (File file : moduleFiles) {
			String pkg = "";
			try {
				pkg = getPackage(file);
				BasicClassInfo info = new BasicClassInfo(file, pkg);
				moduleBasicClassInfos.add(info);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Find the module class info for each moved class and store in MovedClassInfo objects

		List<MovedClassInfo> movedClasses = new ArrayList<MovedClassInfo>();
		List<MovedClassInfo> removedClasses = new ArrayList<MovedClassInfo>();

		final int portletPkgLen = "com.liferay.portlet.".length();

		for (MovedClassInfo classNotInKernel : classesNotInKernel) {
			final String className = classNotInKernel.getName();
			final String oldPackageName = classNotInKernel.getPackageOld();

			// Construct expected new prefix
			String newPrefix = "";
			if (oldPackageName.startsWith("com.liferay.portlet.dynamicdatalists")) {
				newPrefix = "com.liferay.dynamic.data.lists";
			} 
			else if (oldPackageName.startsWith("com.liferay.portlet.dynamicdatamapping")) {
				newPrefix = "com.liferay.dynamic.data.mapping";
			} 
			else if (oldPackageName.startsWith("com.liferay.portlet.")) {
				// construct new prefix using portlet type
				String newPrefixEnd = "";
				int end = oldPackageName.indexOf(".", portletPkgLen);
				if (end != -1) {
					newPrefixEnd = oldPackageName.substring(portletPkgLen, end);
				} else {
					newPrefixEnd = oldPackageName.substring(portletPkgLen);
				}
				newPrefix = "com.liferay." + newPrefixEnd;
			} 

			// If expecting a new prefix, match it
			boolean matched = false;
			List<BasicClassInfo> matchingClassInfos = new ArrayList<BasicClassInfo>();
			for (BasicClassInfo moduleClassInfo : moduleBasicClassInfos) {
				
				if (moduleClassInfo.getName().equals(className)) {
					matchingClassInfos.add(moduleClassInfo);
					if (!newPrefix.isEmpty()) {
						if (moduleClassInfo.getPkg().startsWith(newPrefix)) {
							matched = true;

							classNotInKernel.setPackageNew(moduleClassInfo.getPkg());
							extractModuleInfo(moduleClassInfo, classNotInKernel);

							movedClasses.add(classNotInKernel);
							break;
						}
					}
				}
			}

			if (!matched) {
				if (matchingClassInfos.isEmpty()) {
					removedClasses.add(classNotInKernel);
				}
				else if (matchingClassInfos.size() == 1) {
					// Match it with the only other class with the same name
					matched = true;

					BasicClassInfo moduleClassInfo = matchingClassInfos.get(0);
					classNotInKernel.setPackageNew(moduleClassInfo.getPkg());
					extractModuleInfo(moduleClassInfo, classNotInKernel);
					movedClasses.add(classNotInKernel);
				}
				else {
					// Find the best match
					for (BasicClassInfo moduleClassInfo : matchingClassInfos) {
						if (moduleClassInfo.getPkgEnd().equals(classNotInKernel.getBasicClassInfo().getPkgEnd())) {
							// It's a match since it has the same ending package name
							matched = true;

							classNotInKernel.setPackageNew(moduleClassInfo.getPkg());
							extractModuleInfo(moduleClassInfo, classNotInKernel);
							movedClasses.add(classNotInKernel);
						}
					}

					if (!matched) {
						removedClasses.add(classNotInKernel);
					}
				}
			}
		}

		System.out.println("movedClasses: " + movedClasses.size());
		sortByClassName(movedClasses);
		removeJavaSuffix(movedClasses);

		System.out.println("removedClasses: " + removedClasses.size());

		String oldDirName = oldDir.getName();
		String newDirName = newDir.getName();
		
		TemplateProcessor movedClassesTemplateProcessor = new TemplateProcessor();
		movedClassesTemplateProcessor.processMovedClassesTemplate(movedClasses, movedClassesOutputFile, oldJar, oldDirName, newDirName);
		System.out.println("Reported moved classes to HTML file: " + movedClassesOutputFile.getPath());

		// Write moved classes to XML

		Element root = new Element("moved-class-report");

		Element oldVersion = new Element("old-source-version");
		oldVersion.addContent(oldDir.getPath());
		root.addContent(oldVersion);

		Element newVersion = new Element("new-source-version");
		newVersion.addContent(newDir.getPath());
		root.addContent(newVersion);
		
		for (MovedClassInfo movedClass : movedClasses) {
			Element movedClassElem = new Element("moved-class");

			Element className = new Element("class-name");
			className.addContent(movedClass.getName());
			movedClassElem.addContent(className);
			
			Element oldStuff = new Element("old");

			Element oldPackage = new Element("package");
			oldPackage.addContent(movedClass.getPackageOld());
			oldStuff.addContent(oldPackage);

			movedClassElem.addContent(oldStuff);

			Element newStuff = new Element("new");
			
			Element newPackage = new Element("package");
			newPackage.addContent(movedClass.getPackageNew());
			newStuff.addContent(newPackage);

			Element newArtifactGroup = new Element("artifact-group");
			newArtifactGroup.addContent(movedClass.getGroup());
			newStuff.addContent(newArtifactGroup);
			
			Element newArtifactId = new Element("artifact-id");
			newArtifactId.addContent(movedClass.getModule());
			newStuff.addContent(newArtifactId);
			
			Element newArtifactVersion = new Element("artifact-version");
			newArtifactVersion.addContent(movedClass.getModuleVersion());
			newStuff.addContent(newArtifactVersion);
			
			movedClassElem.addContent(newStuff);
			root.addContent(movedClassElem);
		}
		
		Document doc = new Document();
		doc.setRootElement(root);

		String movedClassesXMLFilePath;
		int dotIndex = movedClassesOutputFile.getPath().indexOf(".htm");
		if (dotIndex != -1) {
			movedClassesXMLFilePath = movedClassesOutputFile.getPath().substring(0, dotIndex) + ".xml";
		}
		else {
			movedClassesXMLFilePath = movedClassesOutputFile.getPath() + ".xml";
		}
		
		File movedClassesXMLFile = new File(movedClassesXMLFilePath);

		XMLOutputter outter = new XMLOutputter();
		outter.setFormat(Format.getPrettyFormat());
		try {
			outter.output(doc, new FileWriter(movedClassesXMLFile));
			System.out.println("Reported moved classes to XML file: " + movedClassesXMLFile.getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void extractModuleInfo(BasicClassInfo classInfo, MovedClassInfo formerClass) {
		String bundleName = "";
		String bundleVersion = "";
		File moduleFile = classInfo.getFile();
		File currentDir = moduleFile.getParentFile();
		while (currentDir != null) {
			if (currentDir.getName().equals("src")) {
				File moduleDir = currentDir.getParentFile();

				FilenameFilter filter = new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						if (name.equals("bnd.bnd")) {
							return true;
						}
						return false;
					}
				};
				File[] bndFiles = moduleDir.listFiles(filter);
				if ((bndFiles != null) && bndFiles.length > 0) {
					File bndFile = bndFiles[0];
					
					try {

						LineNumberReader in =
								new LineNumberReader(new FileReader(bndFile));
						String line;
						while ((line = in.readLine()) != null) {
							if (line.startsWith("Bundle-SymbolicName:")) {
								String[] tokens = line.split(" ");
								if (tokens.length > 1) {
									bundleName = tokens[1].trim();
								}
							}
							else if (line.startsWith("Bundle-Version:")) {
								String[] tokens = line.split(" ");
								if (tokens.length > 1) {
									bundleVersion = tokens[1].trim();
								}
							}

							if (!bundleName.isEmpty() && !bundleVersion.isEmpty()) {
								break;
							}
						}
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

					if (bundleName.isEmpty()) {
						System.out.println("ERROR: Could not fill bundle info for " + bndFile.getPath());
					}
				}
				else {
					System.out.println("Couldn't find a parent folder with a bnd.bnd file.");
				}
				
				break;
			}
			currentDir = currentDir.getParentFile();
		}

		formerClass.setGroup(GROUP_COM_LIFERAY);
		formerClass.setModule(bundleName);
		formerClass.setModuleVersion(bundleVersion);
	}

	private static void sortByClassName(
			List<MovedClassInfo> formerPortalServiceClasses) {
		formerPortalServiceClasses.sort(new Comparator<MovedClassInfo>() {

			@Override
			public int compare(MovedClassInfo classA,
					MovedClassInfo classB) {

				return classA.getName().compareTo(classB.getName());
			}

		}
		);
	}

	private static void sortByPackageName(
			List<MovedClassInfo> formerPortalServiceClasses) {
		formerPortalServiceClasses.sort(new Comparator<MovedClassInfo>() {

			@Override
			public int compare(MovedClassInfo classA,
					MovedClassInfo classB) {

				int rval = classA.getPackageOld().compareTo(classB.getPackageOld());

				if (rval == 0) {
					rval = classA.getName().compareTo(classB.getName());
				}

				return rval;
			}

		}
		);
	}

	private static void removeJavaSuffix(
			List<MovedClassInfo> formerPortalServiceClasses) {
		for (MovedClassInfo movedClass : formerPortalServiceClasses) {
			String name = movedClass.getName();
			int x = name.indexOf(".java");
			if (x != -1) {
				name = name.substring(0, x);
				movedClass.setName(name);
			}
		}
	}

	private static String getPackage(File serviceClass) throws Exception {
		String pkgStr = "package";
		int pkgStrLen = pkgStr.length();
		String pkg = "";

		try {
			LineNumberReader in =
					new LineNumberReader(new FileReader(serviceClass));
			String line;
			while ((line = in.readLine()) != null) {
				if (line.startsWith("package")) {
					String str = line.substring(pkgStrLen);
					str = str.trim();
					int semiIndex = str.indexOf(';');
					if (semiIndex > 0) {
						str = str.substring(0, semiIndex);
					}

					pkg = str;
					break;
				}
			}
			in.close();
		} catch (IOException e) {
			throw new Exception(e.getLocalizedMessage());
		}

		return pkg;
	}

	private static void listBndFiles(String directoryName, ArrayList<File> files) {
	    File directory = new File(directoryName);

	    // get all the files from a directory
	    File[] fList = directory.listFiles();
	    if (fList == null) {
	    	return;
	    }

	    for (File file : fList) {
	        if (file.isFile()) {
	        	if (file.getName().equals("bnd.bnd")) {
	        		files.add(file);
	        	}
	        } else if (file.isDirectory()) {
	        	listBndFiles(file.getAbsolutePath(), files);
	        }
	    }
	}

	private static void listJavaFiles(File directory, ArrayList<File> files) {

	    // get all the files from a directory
	    File[] fList = directory.listFiles();
	    if (fList == null) {
	    	return;
	    }

	    for (File file : fList) {
	        if (file.isFile()) {
	        	if (file.getName().endsWith(".java")) {
	        		files.add(file);
	        	}
	        } else if (file.isDirectory()) {
	            listJavaFiles(file, files);
	        }
	    }
	}

	public static final String GROUP_COM_LIFERAY = "com.liferay";

	public static final String MODULES_APPS = "modules/apps";

	public static final String MODULES_CORE = "modules/core";

	public static final String MODULES_UTIL = "modules/util";

	public static final String PORTAL_KERNEL_SRC = "portal-kernel/src";

	public static final String PORTAL_SERVICE_SRC = "portal-service/src";

	public static final String SRC_MAIN_JAVA = "src/main/java";

}
