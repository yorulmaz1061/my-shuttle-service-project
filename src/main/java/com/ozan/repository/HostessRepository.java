package com.ozan.repository;

import com.ozan.entity.Hostess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostessRepository extends JpaRepository<Hostess, Long> {
}
