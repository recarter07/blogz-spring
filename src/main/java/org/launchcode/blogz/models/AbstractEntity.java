package org.launchcode.blogz.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class AbstractEntity {   // so named b/c annotations are also classes, and there is an Entity annotation already

	private int uid;
	
	@Id                // primary key
    @GeneratedValue    // Hibernate will generate this value for me
    @NotNull
    @Column(name = "uid", unique = true)
	public int getUid() {
		return this.uid;
	}
	
	protected void setUid(int uid) {
        this.uid = uid;
    }
	
}
