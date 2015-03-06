package com.liferay.documentation.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
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

		String docdir = args[0];
		String purposedir = args[1];
		String olderBranch = args[2];
		
		Repository repo = openGitRepository();
		String importBranch = repo.getBranch();
		AbstractTreeIterator oldTreeParser = gitTreeParser(repo, "refs/heads/" + olderBranch);
		AbstractTreeIterator importTreeParser = gitTreeParser(repo, "refs/heads/" + importBranch);

		List<DiffEntry> diff = new Git(repo).diff().setOldTree(oldTreeParser).setNewTree(importTreeParser).call();

		System.out.println("Creating ../git-modified-list.txt file");
		PrintWriter writer = new PrintWriter("git-modified-list.txt", "UTF-8");
		addTimeStamp("Comparing your " + importBranch + " branch to your older branch "
				+ olderBranch + " .\nGenerated on", writer);
		System.out.println("Comparing your " + importBranch + " branch to your older branch "+ olderBranch);

		boolean newDiff = false;
		String baseFilePath;
		
		if (System.getProperty("user.dir").contains(purposedir + "\\learning-paths") ||
				System.getProperty("user.dir").contains(purposedir + "/learning-paths")) {
			baseFilePath = purposedir + "/learning-paths/" + docdir;
		}
		
		else {
			baseFilePath = purposedir + "/" + docdir + "/";
		}

		for (DiffEntry entry : diff) {
			String stringEntry = entry.toString();

			if (stringEntry.contains(baseFilePath)) {
				writer.println(stringEntry);
				System.out.println(stringEntry);
				newDiff = true;
			}
		}
		
		if (!newDiff) {
			System.out.println("There are no additions/modifications in ../" + baseFilePath);
			writer.println("There are no new additions/modifications to report");
		}

		writer.close();
		repo.close();
	}

	private static void addTimeStamp(String message, PrintWriter writer) {

	    Calendar calendar = Calendar.getInstance();
	    writer.printf("%s %tc\n\n", message, calendar);
	  }

	private static AbstractTreeIterator gitTreeParser(Repository repo, String ref)
			throws IOException {

		Ref gitHead = repo.getRef(ref);
		RevWalk revWalk = new RevWalk(repo);
		RevCommit gitCommit = revWalk.parseCommit(gitHead.getObjectId());
		RevTree gitTree = revWalk.parseTree(gitCommit.getTree().getId());
		CanonicalTreeParser treeParser = new CanonicalTreeParser();
		ObjectReader newObjectReader = repo.newObjectReader();

		try {
			treeParser.reset(newObjectReader, gitTree.getId());
		}
		finally {
			newObjectReader.release();
		}
		revWalk.dispose();
		return treeParser;
	}

	private static Repository openGitRepository() throws IOException {

		FileRepositoryBuilder repoBuilder = new FileRepositoryBuilder();
		Repository repo = repoBuilder.readEnvironment().findGitDir().build();

		return repo;
	}
}
