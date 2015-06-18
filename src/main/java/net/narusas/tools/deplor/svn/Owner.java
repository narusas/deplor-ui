package net.narusas.tools.deplor.svn;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Owner implements Comparable<Owner> {
	String		name;
	Set<Change>	changes	= new HashSet<Change>();

	public Owner(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Change> getChanges() {
		return changes;
	}

	public void addChange(Change change) {
		changes.add(change);
	}

	@Override
	public int compareTo(Owner o) {
		return name.compareTo(o.name);
	}

	@Override
	public String toString() {
		return "Owner [name=" + name + "]";
	}

}
