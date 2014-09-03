package com.liferay.documentation.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

public class GitCompareTask extends Task {

	public void execute() throws BuildException {

		try {			
			Repository repo = openGitRepository();
			AbstractTreeIterator masterTreeParser = gitTreeParser(repo, "refs/heads/master");
			AbstractTreeIterator importTreeParser = gitTreeParser(repo, "refs/heads/test");

			try {
				List<DiffEntry> diff = new Git(repo).diff().setOldTree(masterTreeParser).setNewTree(importTreeParser).call();

				for (DiffEntry entry : diff) {
					System.out.println("Entry: " + entry);
				}

				repo.close();

			} catch (GitAPIException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}


		File dir = new File("../" + _docdir);

	}

	public void setDocdir(String docdir) {
		_docdir = docdir;
	}

	private static Repository openGitRepository() throws IOException {
		FileRepositoryBuilder repoBuilder = new FileRepositoryBuilder();
		Repository repo = repoBuilder.readEnvironment().findGitDir().build();

		return repo;
	}

	private static AbstractTreeIterator gitTreeParser(Repository repo, String ref)
			throws IOException {

		Ref gitHead = repo.getRef(ref);
		RevWalk revWalk = new RevWalk(repo);
		RevCommit gitCommit = revWalk.parseCommit(gitHead.getObjectId());
		RevTree gitTree = revWalk.parseTree(gitCommit.getTree().getId());
		CanonicalTreeParser masterTreeParser = new CanonicalTreeParser();
		ObjectReader masterReader = repo.newObjectReader();

		try {
			masterTreeParser.reset(masterReader, gitTree.getId());
		}
		finally {
			masterReader.release();
		}
		revWalk.dispose();
		return masterTreeParser;
	}

	private String _docdir;
}
