package com.liferay.documentation.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class AppendLangHeader  extends Task {
	
	@Override
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
				
				Reader reader = new InputStreamReader(new FileInputStream(inFile), "ISO_8859_1");
				BufferedReader br = new BufferedReader(reader);
				LineNumberReader in = new LineNumberReader(br);

				BufferedWriter out = Files.newBufferedWriter(Paths.get(outFileTmp), StandardCharsets.ISO_8859_1);

				String line;
				
				while ((line = in.readLine()) != null) {

					if (in.getLineNumber() == 2) {
						out.append(line + lang);
					}
					else {
						out.append(line);
					}
					out.append("\n");
				}
				in.close();
				
				out.flush();
				out.close();
				
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
			private String lang = "ja";
}
