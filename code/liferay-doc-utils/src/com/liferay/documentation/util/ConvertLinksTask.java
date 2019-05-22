package com.liferay.documentation.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class ConvertLinksTask  extends Task {

	public void execute() throws BuildException {

		String docDir = _docdir;

		List<String> ceFileList = DocsUtil.getMarkdownFileList(docDir, "");
		List<String> dxpFileList = DocsUtil.getMarkdownFileList(docDir, "-dxp");

		List<String> fileList = new ArrayList<String>();
		fileList.addAll(ceFileList);
		fileList.addAll(dxpFileList);

		for (int i = 0; i < fileList.size(); i++) {
			String filename = fileList.get(i);
			File inFile = new File(filename);
			File outFile = new File(filename);
			String outFileTmp = outFile + ".tmp";
			
			try {
				LineNumberReader in =
						new LineNumberReader(new FileReader(inFile));
				BufferedWriter out =
						new BufferedWriter(new FileWriter(outFileTmp));
			
				String line;
				String findStr = "/-/knowledge_base/";
				
				while ((line = in.readLine()) != null) {
					
					// old link syntax checks
					if (line.contains("](/discover/portal/")) {

						String oldLink = getOldLink("/discover/portal/", line, findStr);
						String version = getVersion(line, findStr);
						String headerID = getHeaderID(line, findStr, version);

						String newLink = "/docs/" + version + "/user" + findStr +
								"u/" + headerID;
						
						line = line.replace(oldLink, newLink);
						System.out.println("Converted link for " + filename + ":" + in.getLineNumber());
					}
					else if (line.contains("](/discover/deployment/")) {
						
						String oldLink = getOldLink("/discover/deployment/", line, findStr);
						String version = getVersion(line, findStr);
						String headerID = getHeaderID(line, findStr, version);
						
						String newLink = "/docs/" + version + "/deploy" + findStr +
								"d/" + headerID;
						
						line = line.replace(oldLink, newLink);
						System.out.println("Converted link for " + filename + ":" + in.getLineNumber());
						
					}
					else if (line.contains("](/develop/tutorials/")) {
						
						String oldLink = getOldLink("/develop/tutorials/", line, findStr);
						String version = getVersion(line, findStr);
						String headerID = getHeaderID(line, findStr, version);
						
						String newLink = "/docs/" + version + "/tutorials" + findStr +
								"t/" + headerID;
						
						line = line.replace(oldLink, newLink);
						System.out.println("Converted link for " + filename + ":" + in.getLineNumber());
						
					}
					else if (line.contains("](/develop/reference/")) {
						
						String oldLink = getOldLink("/develop/reference/", line, findStr);
						String version = getVersion(line, findStr);
						String headerID = getHeaderID(line, findStr, version);
						
						String newLink = "/docs/" + version + "/reference" + findStr +
								"r/" + headerID;
						
						line = line.replace(oldLink, newLink);
						System.out.println("Converted link for " + filename + ":" + in.getLineNumber());
						
					}
				
				out.append(line);
				out.append("\n");
			}

				in.close();

				out.flush();
				out.close();

				// Replace original file with modified temp file

				FileUtils.copyFile(
						new File(outFileTmp),
						new File(filename));

				FileUtils.forceDelete(new File(outFileTmp));

			} catch (IOException e) {
				throw new BuildException(e.getLocalizedMessage());
			}
		}	
	}

	public void setDocdir(String docdir) {
		_docdir = docdir;
	}
	
	private static String getHeaderID(String line, String findStr, String version) {
		
		int headerStartIndex = line.indexOf(version) + 4;
		int headerEndIndex = line.indexOf(")", headerStartIndex);
		String headerID = line.substring(headerStartIndex, headerEndIndex);

		return headerID;
	}

	private static String getOldLink(String docType, String line, String findStr) {
		
		int startIndex = line.indexOf(docType);
		int endIndex = line.indexOf(")", startIndex);
		
		String oldLink = line.substring(startIndex, endIndex);

		return oldLink;
	}
	
	private static String getVersion(String line, String findStr) {
		
		int versionIndex = line.indexOf(findStr) + findStr.length();
		String version = line.substring(versionIndex, versionIndex + 3);

		return version;
	}

	private String _docdir;
}