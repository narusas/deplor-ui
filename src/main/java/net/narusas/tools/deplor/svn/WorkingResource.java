package net.narusas.tools.deplor.svn;

public class WorkingResource {
	Resource				resource;

	String					status;

	private final String	path;

	private final String	revision;

	private final String	owner;

	public WorkingResource(String status, String path, String revision, String owner) {
		this.status = status;
		this.path = path;
		this.revision = revision;
		this.owner = owner;
	}

	public WorkingResource(String status, Resource resource) {
		this(status, resource.getPath(), resource.getLatestChange().getRevision(), resource.getLatestChange().getOwner().getName());
		this.resource = resource;
	}

	public String getComment() {
		return resource.getChanges().get(0).getComment();
	}

	public String getStatus() {
		return status;
	}

	public String getPath() {
		return path;
	}

	public String getFullPath(String branch) {
		String fullPath = branch + "/" + path;
		if (fullPath.contains("trunk") == false) {
			fullPath = "branches/" + fullPath;
		}

		return "/" + fullPath;
	}

	public long getRevision() {
		if (revision == null) {
			return -1L;
		}
		return Long.parseLong(revision);
	}

	public String getOwner() {
		return owner;
	}

	public void setStatus(String string) {
		status = string;
	}

}
