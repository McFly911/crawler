package com.mcflykid.crawler.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo3 extends JpaRepository<Table3, Long>, BaseRepo<Table3> {
}
