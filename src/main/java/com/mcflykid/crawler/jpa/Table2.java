package com.mcflykid.crawler.jpa;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public final class Table2 extends BaseTable {
	public Table2() {
	}

	public Table2(Long id) {
		super(id);
	}
	
	private static final long serialVersionUID = 1044253419370607334L;	
}
