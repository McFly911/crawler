package tdog.lib.crawler.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdog.lib.crawler.table.Table4;

@Repository
public interface Repo4 extends JpaRepository<Table4, Long>, BaseRepo<Table4> {
}
