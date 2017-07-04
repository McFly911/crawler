package com.mcflykid.crawler.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo2 extends JpaRepository<Table2, Long>, BaseRepo<Table2> {
}
