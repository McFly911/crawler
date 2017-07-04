package com.mcflykid.crawler.jpa;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public final class Table5 extends BaseTable {
	public Table5() {
	}

	public Table5(Long id) {
		super(id);
	}

	private static final long serialVersionUID = 5471392241828100516L;
}
