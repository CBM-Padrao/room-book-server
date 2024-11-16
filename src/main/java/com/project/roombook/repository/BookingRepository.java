package com.project.roombook.repository;

import com.project.roombook.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {
    boolean existsByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            Long roomId, Date endTime, Date startTime);

    boolean existsByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndIdNot(
            Long roomId, Date endTime, Date startTime, Long id);

    List<Booking> findByIsDeletedFalse();

    Optional<Booking> findByIdAndIsDeletedFalse(Long id);
}
