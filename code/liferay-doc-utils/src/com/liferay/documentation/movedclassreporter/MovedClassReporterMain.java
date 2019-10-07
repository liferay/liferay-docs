package com.liferay.documentation.movedclassreporter;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class MovedClassReporterMain {

	public static void main(String[] args) {
		if (args == null || args.length < 3) {
			System.out.println("Usage: MovedClassReporter oldDir newDir movedClassesOutputFile [removedClassesOutputFile]");
			return;
		}

		File oldDir = new File(args[0]);
		File newDir = new File(args[1]);
		File movedClassesOutputFile = new File(args[2]);

		File removedClassesOutputFile = null;
		if (args.length > 3) {
			removedClassesOutputFile = new File(args[3]);
		}

		if (!oldDir.isDirectory()) {
			System.out.println("oldDir is not a directory: " + args[0]);
		}

		if (!newDir.isDirectory()) {
			System.out.println("newDir is not a directory: " + args[1]);
		}

		// Create a list of all portal-kernel classes

		File kernelSrcDir = new File(newDir + "/" + PORTAL_KERNEL_SRC);
		System.out.println("kernelSrcDir: " + kernelSrcDir.getPath());

		ArrayList<File> kernelClasses = new ArrayList<File>();

		MovedClassReporterMain.listJavaFiles(kernelSrcDir, kernelClasses);

		System.out.println(PORTAL_KERNEL_SRC + " class count: " + kernelClasses.size());


		// Create list of portal-service class files

		File serviceSrcDir = new File(oldDir.getPath() + "/" + PORTAL_SERVICE_SRC);
		System.out.println("serviceSrcDir: " + serviceSrcDir.getPath());

		ArrayList<File> serviceClasses = new ArrayList<File>();

		MovedClassReporterMain.listJavaFiles(serviceSrcDir, serviceClasses);

		System.out.println(PORTAL_SERVICE_SRC + " class count: " + serviceClasses.size());


		// Create list of service class BasicClassInfo objects

		List<BasicClassInfo> serviceBasicClassInfos = new ArrayList<BasicClassInfo>();

		for (File file : serviceClasses) {
			String pkg = "";
			try {
				pkg = getPackage(file);
				BasicClassInfo info = new BasicClassInfo(file, pkg);
				serviceBasicClassInfos.add(info);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Add to a new list of BasicClassInfo objects all service classes not in kernel
		List<FormerPortalServiceClass> classesNotInKernel = new ArrayList<FormerPortalServiceClass>();

		for (BasicClassInfo serviceInfo: serviceBasicClassInfos) {
			String serviceFileName = serviceInfo.getName();

			boolean matched = false;
			for (File kernelClass : kernelClasses) {
				if (serviceFileName.equals(kernelClass.getName())) {
					matched = true;
					break;
				}
			}

			if (!matched) {
				FormerPortalServiceClass formerClass =
					new FormerPortalServiceClass(serviceInfo);
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

		// Find the module class info for each moved class and store in FormerPortalServiceClass objects

		List<FormerPortalServiceClass> movedClasses = new ArrayList<FormerPortalServiceClass>();
		List<FormerPortalServiceClass> removedClasses = new ArrayList<FormerPortalServiceClass>();

		final int portletPkgLen = "com.liferay.portlet.".length();

		for (FormerPortalServiceClass classNotInKernel : classesNotInKernel) {
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
							classNotInKernel.setModule(getModule(moduleClassInfo));

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
					classNotInKernel.setModule(getModule(moduleClassInfo));
					movedClasses.add(classNotInKernel);
				}
				else {
					// Find the best match
					for (BasicClassInfo moduleClassInfo : matchingClassInfos) {
						if (moduleClassInfo.getPkgEnd().equals(classNotInKernel.getBasicClassInfo().getPkgEnd())) {
							// It's a match since it has the same ending package name
							matched = true;

							classNotInKernel.setPackageNew(moduleClassInfo.getPkg());
							classNotInKernel.setModule(getModule(moduleClassInfo));
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
		sortByPackageName(removedClasses);
		removeJavaSuffix(removedClasses);

		String oldDirName = oldDir.getName();
		String newDirName = newDir.getName();
		
		TemplateProcessor movedClassesTemplateProcessor = new TemplateProcessor();
		movedClassesTemplateProcessor.processMovedClassesTemplate(movedClasses,  movedClassesOutputFile, oldDirName, newDirName);
		System.out.println("Reported moved classes to file: " + movedClassesOutputFile.getPath());

		if (removedClassesOutputFile != null) {
			TemplateProcessor removedClassesTemplateProcessor = new TemplateProcessor();
			removedClassesTemplateProcessor.processRemovedClassesTemplate(removedClasses, removedClassesOutputFile, oldDirName, newDirName);
			System.out.println("Reported removed classes to file: " + removedClassesOutputFile.getPath());

		}
	}

	private static String getModule(BasicClassInfo classInfo) {
		String bundleName = "";
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

							if (!bundleName.isEmpty()) {
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
		
		return bundleName;
	}

	private static void sortByClassName(
			List<FormerPortalServiceClass> formerPortalServiceClasses) {
		formerPortalServiceClasses.sort(new Comparator<FormerPortalServiceClass>() {

			@Override
			public int compare(FormerPortalServiceClass classA,
					FormerPortalServiceClass classB) {

				return classA.getName().compareTo(classB.getName());
			}

		}
		);
	}

	private static void sortByPackageName(
			List<FormerPortalServiceClass> formerPortalServiceClasses) {
		formerPortalServiceClasses.sort(new Comparator<FormerPortalServiceClass>() {

			@Override
			public int compare(FormerPortalServiceClass classA,
					FormerPortalServiceClass classB) {

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
			List<FormerPortalServiceClass> formerPortalServiceClasses) {
		for (FormerPortalServiceClass movedClass : formerPortalServiceClasses) {
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


	public static final String MODULES_APPS = "modules/apps";

	public static final String MODULES_CORE = "modules/core";

	public static final String MODULES_UTIL = "modules/util";

	public static final String PORTAL_KERNEL_SRC = "portal-kernel/src";

	public static final String PORTAL_SERVICE_SRC = "portal-service/src";

	public static final String SRC_MAIN_JAVA = "src/main/java";

}
