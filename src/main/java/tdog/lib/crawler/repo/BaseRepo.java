package tdog.lib.crawler.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;

/**
 * Do not use LIKE, I repeat :v
 * 
 * @author user
 *
 * @param <T>
 */
public interface BaseRepo<T> {

	public List<T> findAllByLog1(String log);
	public List<T> findAllByLog1(String log, Pageable pageAble);
	public List<T> findAllByLog2(String log);
	public List<T> findAllByLog2(String log, Pageable pageAble);
	public List<T> findAllByLog3(String log);
	public List<T> findAllByLog3(String log, Pageable pageAble);
	public List<T> findAllByLog4(String log);
	public List<T> findAllByLog4(String log, Pageable pageAble);
	public List<T> findAllByLog5(String log);
	public List<T> findAllByLog5(String log, Pageable pageAble);
}
