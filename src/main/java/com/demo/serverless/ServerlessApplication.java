package com.demo.serverless;

import com.demo.serverless.domain.ports.AppointmentRepository;
import com.demo.serverless.domain.ports.DentistRepository;
import com.demo.serverless.domain.ports.PatientRepository;
import com.demo.serverless.infrastructure.adapters.InMemoryAppointmentRepository;
import com.demo.serverless.infrastructure.adapters.InMemoryDentistRepository;
import com.demo.serverless.infrastructure.adapters.InMemoryPatientRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class ServerlessApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerlessApplication.class, args);
    }

    @Bean
    @Profile({"local", "dev"})
    public PatientRepository patientRepository() {
        return new InMemoryPatientRepository();
    }

    @Bean
    @Profile({"local", "dev"})
    public DentistRepository dentistRepository() {
        return new InMemoryDentistRepository();
    }

    @Bean
    @Profile({"local", "dev"})
    public AppointmentRepository appointmentRepository() {
        return new InMemoryAppointmentRepository();
    }
} 