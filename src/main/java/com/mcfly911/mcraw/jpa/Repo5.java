package com.mcfly911.mcraw.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo5 extends JpaRepository<Table5, Long>, BaseRepo<Table5> {
}
