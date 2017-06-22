package com.mcfly911.mcraw.jpa;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public final class Table4 extends BaseTable {
	public Table4() {
	}

	public Table4(Long id) {
		super(id);
	}

	private static final long serialVersionUID = 5471392241828100516L;
}
