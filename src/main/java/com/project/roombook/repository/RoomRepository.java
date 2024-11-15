package com.project.roombook.repository;

import com.project.roombook.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByIsDeletedFalse();

    Optional<Room> findByIdAndIsDeletedFalse(Long id);
}
