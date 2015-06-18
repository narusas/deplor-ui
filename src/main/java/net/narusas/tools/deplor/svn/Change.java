package net.narusas.tools.deplor.svn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Change {
	Resource	resource;
	Owner		owner;
	String		revision;
	String		comment;
	boolean		toDelete;

	public Change(Resource resource2, Owner owner, String revision2, String comment, boolean toDelete) {
		this.resource = resource2;
		this.resource.addChange(this);

		this.owner = owner;
		this.revision = revision2;
		this.comment = comment;
		this.toDelete = toDelete;
		this.owner.addChange(this);
	}
}
