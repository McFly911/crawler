package tdog.lib.crawler.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdog.lib.crawler.table.Table5;

@Repository
public interface Repo5 extends JpaRepository<Table5, Long>, BaseRepo<Table5> {
}
