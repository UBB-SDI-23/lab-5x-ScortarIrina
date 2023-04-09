package com.example.dogsdatabase.repository;

import com.example.dogsdatabase.model.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
}
