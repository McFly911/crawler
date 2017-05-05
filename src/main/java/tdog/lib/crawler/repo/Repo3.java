package tdog.lib.crawler.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdog.lib.crawler.table.Table3;

@Repository
public interface Repo3 extends JpaRepository<Table3, Long>, BaseRepo<Table3> {
}
