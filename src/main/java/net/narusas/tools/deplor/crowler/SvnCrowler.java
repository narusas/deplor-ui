package net.narusas.tools.deplor.crowler;

import java.util.List;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.tmatesoft.svn.core.SVNException;

import net.narusas.tools.deplor.domain.model.Branch;
import net.narusas.tools.deplor.domain.model.Repository;
import net.narusas.tools.deplor.domain.repository.RepoRepository;
import net.narusas.tools.deplor.svn.Svn;

@Data
public class SvnCrowler {

	private Svn				svn;
	private Repository		repo;

	@Autowired
	private RepoRepository	repoRepository;

	public SvnCrowler(Svn svn) {
		this.svn = svn;
	}

	public SvnCrowler(Repository repo) {
		this(new Svn(repo.getUrl(), repo.getWorkerId(), repo.getWorkerPassword()));
		this.repo = repo;
	}

	public void start(int startRevision, int endRevision) {

	}

	public long headRevision() {
		return svn.headRevision();
	}

	public List<String> listBranches() {
		try {
			return svn.listBranches();
		} catch (SVNException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void processBranches() {
		List<String> list = listBranches();
		List<Branch> oldBranches = repo.getBranches();
		for (String branchName : list) {
			if (isAlreadExist(branchName, oldBranches)) {
				continue;
			}
			Branch newBranch = new Branch();
			newBranch.setName(branchName);
			newBranch.setRepository(repo);
			newBranch.setLastRevision(0L);
			oldBranches.add(newBranch);
		}
		repoRepository.saveAndFlush(repo);
	}

	boolean isAlreadExist(String branchName, List<Branch> oldBranches) {
		for (Branch branch : oldBranches) {
			if (branchName.equals(branch.getName())) {
				return true;
			}
		}
		return false;
	}
}
