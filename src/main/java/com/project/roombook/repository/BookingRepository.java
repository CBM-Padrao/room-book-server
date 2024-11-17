package com.project.roombook.repository;

import com.project.roombook.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {
    boolean existsByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndIsDeletedFalse(
            Long roomId, LocalDateTime endTime, LocalDateTime startTime);

    boolean existsByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndIdNotAndIsDeletedFalse(
            Long roomId, LocalDateTime endTime, LocalDateTime startTime, Long id);

    List<Booking> findByIsDeletedFalse();

    Optional<Booking> findByIdAndIsDeletedFalse(Long id);
    List<Booking> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}
