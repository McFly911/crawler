package com.mcfly911.mcraw.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo1 extends JpaRepository<Table1, Long>, BaseRepo<Table1> {
}
