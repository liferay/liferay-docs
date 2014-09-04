package com.liferay.documentation.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

public class GitCompare {

	public static void main(String[] args) throws GitAPIException, IOException  {
		if (args == null || args.length < 3) {
			throw new IllegalArgumentException("Requires 2 arguments: markdownFile htmlFile");
		}

		String importBranch = args[0];
		String docdir = args[1];
		String purposedir = args[2];
		
		Repository repo = openGitRepository();
		AbstractTreeIterator masterTreeParser = gitTreeParser(repo, "refs/heads/master");
		AbstractTreeIterator importTreeParser = gitTreeParser(repo, "refs/heads/" + importBranch);

		List<DiffEntry> diff = new Git(repo).diff().setOldTree(masterTreeParser).setNewTree(importTreeParser).call();

		PrintWriter writer = new PrintWriter("git-modified-list.txt", "UTF-8");

		for (DiffEntry entry : diff) {
			String stringEntry = entry.toString();

			if (stringEntry.contains(purposedir + "/" + docdir)) {
				writer.println(stringEntry);
			}
		}

		writer.close();
		repo.close();

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
}
