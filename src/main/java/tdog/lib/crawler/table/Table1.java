package tdog.lib.crawler.table;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public final class Table1 extends BaseTable {
	public Table1() {
	}

	public Table1(Long id) {
		super(id);
	}

	private static final long serialVersionUID = 428091085677864600L;
}
