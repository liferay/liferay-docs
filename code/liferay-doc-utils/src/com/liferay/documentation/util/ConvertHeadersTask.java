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

public class ConvertHeadersTask extends Task {

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
				String headerPrefix = " [](id=";
				String newHeaderIdPrefix = "header-id: ";
				while ((line = in.readLine()) != null) {
					
					if (line.startsWith(newHeaderIdPrefix)) {
						out.append(line);
						out.append("\n");
						continue;
					}
					
					if (line.startsWith("#") && !line.startsWith("##") &&
							line.contains(headerPrefix)) {
						
						int idStartIndex = line.indexOf(headerPrefix);
						int idEndIndex = line.indexOf(")", idStartIndex);
						
						String id = line.substring(idStartIndex + headerPrefix.length(), idEndIndex);

						line = line.substring(0, idStartIndex);
						
						out.append("---\n");
						out.append(newHeaderIdPrefix + id + "\n");
						out.append("---\n\n");
						
						System.out.println("Converted header(s) for " + filename);
					}
					else if (line.startsWith("##") && line.contains(headerPrefix)) {
						int idStartIndex = line.indexOf(" [](id=");
						line = line.substring(0, idStartIndex);
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

	private String _docdir;
}
