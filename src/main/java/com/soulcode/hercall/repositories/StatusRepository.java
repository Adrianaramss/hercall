package com.soulcode.hercall.repositories;

import com.soulcode.hercall.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
