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

public class ConvertSidebarsTask extends Task {

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

				String line;
				boolean sidebarsExist = false;
				
				List<Integer> sidebarStartIndexes = new ArrayList<Integer>();
				List<Integer> sidebarEndIndexes = new ArrayList<Integer>();
				List<String> sidebarSpacing = new ArrayList<String>();
				
				while ((line = in.readLine()) != null) {

					if (line.contains(sidebarPrefix)) {
						sidebarStartIndexes.add(in.getLineNumber());
						sidebarsExist = true;
						
						String sidebarWhitespace = line.substring(0, line.indexOf(sidebarPrefix));
						sidebarSpacing.add(sidebarWhitespace);
					}
					
					if (line.contains(sidebarSuffix) && !line.contains("+")) {
						sidebarEndIndexes.add(in.getLineNumber());
					}
				}
				in.close();
				
				if (sidebarsExist) {
					boolean sidebar = false;
					int j = 0;

					LineNumberReader in2 =
							new LineNumberReader(new FileReader(inFile));

					BufferedWriter out =
							new BufferedWriter(new FileWriter(outFileTmp));

					while ((line = in2.readLine()) != null) {

						int lineNumber = in2.getLineNumber();
						int sidebarStartIndex = sidebarStartIndexes.get(j);
						int sidebarEndIndex = sidebarEndIndexes.get(j);

						if (lineNumber == sidebarStartIndex) {
							sidebar = true;
							continue;
						}
						else if (lineNumber == (sidebarStartIndex + 1)) {
							continue;
						}
						else if (lineNumber == (sidebarEndIndex - 1)) {
							continue;
						}
						else if (lineNumber == sidebarEndIndex) {
							sidebar = false;
							boolean moreSidebars = true;

							try {
								sidebarStartIndexes.get(j+1);
							} catch (IndexOutOfBoundsException e) {
								moreSidebars = false;
							}

							if (moreSidebars) {
								j++;
							}
							continue;
						}

						if (sidebar && (lineNumber > sidebarStartIndexes.get(j) + 1) &&
								(lineNumber < sidebarEndIndexes.get(j))) {
							out.append(sidebarSpacing.get(j) + "| " + line.trim());
							out.append("\n");
							continue;
						}

						out.append(line);
						out.append("\n");
					}


					in2.close();

					out.flush();
					out.close();

					// Replace original file with modified temp file

					FileUtils.copyFile(
							new File(outFileTmp),
							new File(filename));

					FileUtils.forceDelete(new File(outFileTmp));
				}
			} catch (IOException e) {
				throw new BuildException(e.getLocalizedMessage());
			}
		}
		
	}

	public void setDocdir(String docdir) {
		_docdir = docdir;
	}

	private static String sidebarPrefix = "+$$$";
	private static String sidebarSuffix = "$$$";
	
	private String _docdir;
}
