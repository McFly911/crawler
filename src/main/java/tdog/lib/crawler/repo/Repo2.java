package tdog.lib.crawler.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdog.lib.crawler.table.Table2;

@Repository
public interface Repo2 extends JpaRepository<Table2, Long>, BaseRepo<Table2> {
}
