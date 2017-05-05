package tdog.lib.crawler.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdog.lib.crawler.table.Table6;

@Repository
public interface Repo6 extends JpaRepository<Table6, Long>, BaseRepo<Table6> {
}
