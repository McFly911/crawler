package com.mcflykid.crawler.jpa;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public final class Table3 extends BaseTable {
	public Table3() {
	}

	public Table3(Long id) {
		super(id);
	}

	private static final long serialVersionUID = -5159849380792940867L;
}
