package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.response.WorkScheduleAttendanceResponse;
import com.example.SmartRestaurant.dto.response.WorkScheduleResponse;
import com.example.SmartRestaurant.entity.WorkScheduleEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WorkScheduleMapper {
    ShiftMapper shiftMapper;
    EmploymentMapper employmentMapper;

    AttendanceMapper attendanceMapper;


    public WorkScheduleResponse toResponse(WorkScheduleEntity workSchedule) {
        return WorkScheduleResponse.builder()
                .id(workSchedule.getId())
                .date(workSchedule.getDate())
                .shift(shiftMapper.toResponse(workSchedule.getShift()))
                .employmentResponse(employmentMapper.toResponse(workSchedule.getEmployment()))
                .build();
    }

    public WorkScheduleAttendanceResponse toAttendancResponse(WorkScheduleEntity workSchedule) {
        return WorkScheduleAttendanceResponse.builder()
                .id(workSchedule.getId())
                .date(workSchedule.getDate())
                .shift(shiftMapper.toResponse(workSchedule.getShift()))
                .employment(employmentMapper.toResponse(workSchedule.getEmployment()))
                .attendances(workSchedule.getAttendances()
                        .stream()
                        .map(x -> attendanceMapper.toResponse(x))
                        .collect(Collectors.toList()))
                .build();
    }
}
