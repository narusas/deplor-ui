package net.narusas.tools.deplor.svn;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Resource implements Comparable<Resource> {
	String			path;
	List<Change>	changes	= new ArrayList<Change>();

	public Resource(String fullPath) {
		path = fullPath;
	}

	public void addChange(Change change) {
		changes.add(change);
	}

	public void removeChange(Change change) {
		changes.remove(change);
	}

	@Override
	public int compareTo(Resource o) {
		return path.compareTo(o.path);
	}

	public boolean isConflict() {
		for (Change change : changes) {
			if (change.isBroken()) {
				return true;
			}
		}
		return changes.size() > 1;
	}

	public Change getLatestChange() {
		if (changes == null || changes.size() == 0) {
			return null;
		}
		if (changes.size() == 1) {
			return changes.get(0);
		}
		Change latest = changes.get(0);
		boolean debug = false;
		if (latest.resource.path.contains("Format")) {
			debug = true;
		}
		for (Change change : changes) {
			if (debug)
				System.out.println(change);
			if (Integer.parseInt(latest.getRevision()) < Integer.parseInt(change.getRevision())) {
				latest = change;
			}
		}
		if (debug)
			System.out.println("Lateeset:" + latest);

		return latest;
	}

}
