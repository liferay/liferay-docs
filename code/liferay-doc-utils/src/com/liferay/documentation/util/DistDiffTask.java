package com.liferay.documentation.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class DistDiffTask extends Task {

	@Override
	public void execute() throws BuildException {

		File dir = new File("../" + _docdir);
		if (!dir.exists()) {
			throw new BuildException("docdir " + dir.getAbsolutePath() +
					" could not be found");
		}

		String absDir = dir.getAbsolutePath();
		absDir = absDir.replace("\\", "/");
		List<String> diffs = null;

		File modifiedList = new File(absDir + "/git-modified-list.txt");

		try {
			diffs = FileUtils.readLines(modifiedList, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

		String txtPath = _purposedir + "/" + _docdir;	
		
		List<File> allFiles = new ArrayList<File>();
		
		for (String diff : diffs) {
			if (diff.startsWith("DiffEntry")) {
				int x = diff.indexOf(txtPath);
				int y = diff.indexOf("]", x);
				int z = absDir.indexOf(txtPath);
				String endModifiedFileDir = diff.substring(x, y);
				String begModifiedFileDir = absDir.substring(0, z);

				File file = new File(begModifiedFileDir + endModifiedFileDir);
				allFiles.add(file);			
			}
		}

		System.out.println(allFiles);
	}

	public void setDocdir(String docdir) {
		_docdir = docdir;
	}

	public void setPurposedir(String purposedir) {
		_purposedir = purposedir;
	}

	private String _docdir;
	private String _purposedir;
}