package com.mcfly911.mcraw.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo6 extends JpaRepository<Table6, Long>, BaseRepo<Table6> {
}
