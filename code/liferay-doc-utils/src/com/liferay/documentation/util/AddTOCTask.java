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

public class AddTOCTask extends Task {

	@Override
	public void execute() throws BuildException {

		String docDir = _docdir;
		String productType = _productType;

		List<String> dirTypes = new ArrayList<String>();
		dirTypes.add("");

		if (productType.equals("dxp")) {
			dirTypes.add("-dxp");
		}

		for (String dirType : dirTypes) {

			List<String> fileList = DocsUtil.getMarkdownFileList(docDir, dirType);
			List<String> fileListNoTOC = new ArrayList<String>();

			try {

				for (int i = 0; i < fileList.size(); i++) {
					String filename = fileList.get(i);
					File inFile = new File(filename);

					LineNumberReader in =
							new LineNumberReader(new FileReader(inFile));

					String line;
					boolean tocExists = false;
					//Integer tocLineNum = null;
					int tocLineNum = -2;

					while ((line = in.readLine()) != null) {

						if (line.equals(tocSyntax)) {

							tocExists = true;
							tocLineNum = in.getLineNumber();
						}
						else if (line.contains(tocSyntax)) {
							in.close();
							throw new BuildException("Filename: " + filename + ":" +
									in.getLineNumber() + " TOC syntax should not be accompanied by " +
									"any additional text! Also verify there is no whitespace " +
									"with the TOC syntax.");
						}
						else if (in.getLineNumber() == (tocLineNum + 1) && tocExists) {
							if (!line.equals("")) {
								in.close();
								throw new BuildException("Filename: " + filename + ":" +
										in.getLineNumber() +  "The line following the TOC syntax should " +
										"be blank.");
							}
						}
					}

					if (!tocExists) {
						fileListNoTOC.add(filename);
					}

					in.close();
				}
				for (int j = 0; j < fileListNoTOC.size(); j++) {
					String filenameNoTOC = fileListNoTOC.get(j);
					File inFile2 = new File(filenameNoTOC);
					File outFile = new File(filenameNoTOC);
					String outFileTmp = outFile + ".tmp";

					LineNumberReader in2 =
							new LineNumberReader(new FileReader(inFile2));

					BufferedWriter out =
							new BufferedWriter(new FileWriter(outFileTmp));

					String line2;
					while ((line2 = in2.readLine()) != null) {

						if (line2.startsWith("#") && !line2.startsWith("##")) {
							out.append(line2);
							out.append("\n\n");
							out.append(tocSyntax);
							out.append("\n");
							continue;
						}
						else {
							out.append(line2);
							out.append("\n");
						}

					}


					in2.close();

					out.flush();
					out.close();

					// Replace original file with modified temp file

					FileUtils.copyFile(
							new File(outFileTmp),
							new File(filenameNoTOC));

					FileUtils.forceDelete(new File(outFileTmp));
				}

			} catch (IOException e) {
						throw new BuildException(e.getLocalizedMessage());
					}
			}
		}
	
	private static void getFilesWithNoTOC(String duplicateFile, String duplicateFile2) 
			{
		
	}

	public void setDocdir(String docdir) {
		_docdir = docdir;
	}

	public void setProductType(String productType) {
		_productType = productType;
	}

	private static String tocSyntax = "[TOC levels=1-4]";

	private String _docdir;
	private String _productType;
}
