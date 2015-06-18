package net.narusas.tools.deplor.svn;

import java.util.Set;
import java.util.TreeSet;


public class ChangeSet {
	Set<Owner>		owners		= new TreeSet<Owner>();
	Set<Resource>	resources	= new TreeSet<Resource>();

	public void addOwner(Owner owner) {
		owners.add(owner);
	}

	public void removeOwner(Owner owner) {
		owners.remove(owner);
	}

	public Owner findOwner(String name) {
		for (Owner owner : owners) {
			if (name.equals(owner.getName())) {
				return owner;
			}
		}
		Owner owner = new Owner(name);
		addOwner(owner);
		return owner;
	}

	public void addResource(Resource resource) {
		resources.add(resource);
	}

	public void removeResource(Resource resource) {
		resources.remove(resource);
	}

	public Resource findResource(String path) {
		for (Resource resource : resources) {
			if (resource.getPath().equals(path)) {
				return resource;
			}
		}
		Resource resource = new Resource(path);
		addResource(resource);
		return resource;
	}

	public Set<Owner> getOwners() {
		return owners;
	}

	public void setOwners(Set<Owner> owners) {
		this.owners = owners;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

}
