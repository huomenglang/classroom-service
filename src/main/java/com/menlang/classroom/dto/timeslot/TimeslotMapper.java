package com.menlang.classroom.dto.timeslot;

import com.menlang.classroom.model.entities.Timeslot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class TimeslotMapper {
   public abstract Timeslot toEntity(TimeslotRequest request);

   public abstract TimeslotResponse toResponse(Timeslot timeSlot);

   @Mapping(target = "id", ignore = true)
   @Mapping(target = "createdAt", ignore = true)
   @Mapping(target = "createdBy", ignore = true)
   @Mapping(target = "updatedAt", ignore = true)
   @Mapping(target = "updatedBy", ignore = true)
   public abstract void updateTimeSlotDto(TimeslotRequest request, @MappingTarget Timeslot timeslot);

   public abstract List<TimeslotResponse> toResponses(List<Timeslot> timeSlots);
}
