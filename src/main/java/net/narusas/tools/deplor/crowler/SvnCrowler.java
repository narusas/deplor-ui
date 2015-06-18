package net.narusas.tools.deplor.crowler;

import net.narusas.tools.deplor.domain.model.Repository;
import net.narusas.tools.deplor.svn.Svn;

public class SvnCrowler {

	private Svn	svn;

	public SvnCrowler(Svn svn) {
		this.svn = svn;
	}

	public SvnCrowler(Repository repo) {
		this(new Svn(repo.getUrl(), repo.getWorkerId(), repo.getWorkerPassword()));
	}

	public void start(int startRevision, int endRevision) {

	}

	public long headRevision() {
		return svn.headRevision();
	}

}
