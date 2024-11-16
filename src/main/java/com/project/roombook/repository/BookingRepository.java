package com.project.roombook.repository;

import com.project.roombook.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            Long roomId, Date endTime, Date startTime);
}
