package com.liferay.documentation.movedclassreporter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


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
				BasicClassInfo info = new BasicClassInfo(file.getName(), pkg);
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
					new FormerPortalServiceClass(serviceInfo.getName(), serviceInfo.getPkg());
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

		// Find the module class info for each moved class and store in FormerPortalServiceClass objects

		List<FormerPortalServiceClass> movedClasses = new ArrayList<FormerPortalServiceClass>();
		List<FormerPortalServiceClass> removedClasses = new ArrayList<FormerPortalServiceClass>();

		for (FormerPortalServiceClass classNotInKernel : classesNotInKernel) {
			String className = classNotInKernel.getName();

			boolean foundMatch = false;
			for (File moduleFile : moduleFiles) {
				if (className.equals(moduleFile.getName())) {
					foundMatch = true;

					try {
						String pkg = MovedClassReporterMain.getPackage(moduleFile);
						classNotInKernel.setPackageNew(pkg);

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

									processModuleInfo(bndFile, classNotInKernel);
								}
								else {
									System.out.println("Couldn't find a parent folder with a bnd.bnd file.");
								}
								
								break;
							}
							currentDir = currentDir.getParentFile();
						}

						movedClasses.add(classNotInKernel);
					} catch (Exception e) {
						e.printStackTrace();
					}

					break;
				}
			}

			if (!foundMatch) {
				removedClasses.add(classNotInKernel);
			}
		}

		System.out.println("movedClasses: " + movedClasses.size());
		sortByClassName(movedClasses);
		removeJavaSuffix(movedClasses);

		System.out.println("removedClasses: " + removedClasses.size());
		sortByPackageName(removedClasses);
		removeJavaSuffix(removedClasses);

		String oldZipName = oldDir.getName() + ".zip";
		String newZipName = newDir.getName() + ".zip";
		
		TemplateProcessor movedClassesTemplateProcessor = new TemplateProcessor();
		movedClassesTemplateProcessor.processMovedClassesTemplate(movedClasses,  movedClassesOutputFile, oldZipName, newZipName);
		System.out.println("Reported moved classes to file: " + movedClassesOutputFile.getPath());

		if (removedClassesOutputFile != null) {
			TemplateProcessor removedClassesTemplateProcessor = new TemplateProcessor();
			removedClassesTemplateProcessor.processRemovedClassesTemplate(removedClasses, removedClassesOutputFile, oldZipName, newZipName);
			System.out.println("Reported removed classes to file: " + removedClassesOutputFile.getPath());

		}
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
			name = name.substring(0, x);
			movedClass.setName(name);
		}
	}

	private static void processModuleInfo(File bndFile, FormerPortalServiceClass formerClass) {
		String bundleName = "";
		String bundleVersion = "";
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

						// Decrement the bundle version if it's last version number is greater than zero
						int x = bundleVersion.lastIndexOf(".");
						if (x > 0 && (x + 1) < bundleVersion.length()) {

							String microVersion = bundleVersion.substring(x + 1);

							try {
								int i = Integer.parseInt(microVersion);
								StringBuilder sb = new StringBuilder();
								sb.append(bundleVersion.substring(0, x));
								sb.append(".");

								if (i > 0) {
									i = i - 1;
								}

								sb.append(i);

								bundleVersion = sb.toString();
							} catch (NumberFormatException e) {
								e.printStackTrace();
							}
						}
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

		if (!bundleName.isEmpty() && !bundleVersion.isEmpty()) {
			formerClass.setModule(bundleName);
			formerClass.setVersion(bundleVersion);
		}
		else {
			System.out.println("ERROR: Could not fill bundle info for " + bndFile.getPath());
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

