package net.narusas.tools.deplor.svn;

import org.tmatesoft.svn.core.SVNException;

public class MalformedSvnURLException extends RuntimeException {

	private static final long	serialVersionUID	= -1328456941776504128L;

	public MalformedSvnURLException() {
	}

	public MalformedSvnURLException(SVNException e) {
		super(e);
	}

}
