package com.mcfly911.mcraw.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo4 extends JpaRepository<Table4, Long>, BaseRepo<Table4> {
}
