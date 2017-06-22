package com.mcfly911.mcraw;

import java.util.List;

import com.mcfly911.mcraw.jpa.BaseTable;
import com.mcfly911.mcraw.jpa.IService;

public interface ScraperInputCollector {
	List<? extends BaseTable> collect(IService service);
}
