package tdog.lib.crawler.aircraft;

import java.util.List;

import tdog.lib.crawler.service.IService;
import tdog.lib.crawler.table.BaseTable;

public interface TableCollector {
	List<? extends BaseTable> collect(IService service);
}
