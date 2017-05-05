package tdog.lib.crawler.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdog.lib.crawler.table.Table1;

@Repository
public interface Repo1 extends JpaRepository<Table1, Long>, BaseRepo<Table1> {
}
