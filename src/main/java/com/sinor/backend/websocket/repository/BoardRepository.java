package com.sinor.backend.websocket.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends CrudRepository<BoardRepository, Long> {
}
