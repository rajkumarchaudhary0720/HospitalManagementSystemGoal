package com.hospital.store;

import com.hospital.model.Appointment;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import com.hospital.model.Role;
import com.hospital.model.User;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class HospitalStore {
    private static final HospitalStore INSTANCE = new HospitalStore();

    private final Map<String, User> users = new LinkedHashMap<>();
    private final List<Doctor> doctors = new ArrayList<>();
    private final List<Patient> patients = new ArrayList<>();
    private final List<Appointment> appointments = new ArrayList<>();

    private final AtomicInteger doctorSequence = new AtomicInteger(100);
    private final AtomicInteger patientSequence = new AtomicInteger(500);
    private final AtomicInteger appointmentSequence = new AtomicInteger(900);

    private HospitalStore() {
        seedData();
    }

    public static HospitalStore getInstance() {
        return INSTANCE;
    }

    private void seedData() {
        users.put("admin", new User("admin", "admin123", "System Admin", Role.ADMIN));
        users.put("reception", new User("reception", "recep123", "Front Desk", Role.RECEPTIONIST));

        doctors.add(new Doctor(doctorSequence.incrementAndGet(), "Dr. Priya Sharma", "Cardiology", "Mon-Fri 10:00-16:00"));
        doctors.add(new Doctor(doctorSequence.incrementAndGet(), "Dr. Arjun Mehta", "Orthopedics", "Mon-Sat 09:00-13:00"));

        patients.add(new Patient(patientSequence.incrementAndGet(), "Riya Kapoor", 29, "Female", "9876543210"));
        patients.add(new Patient(patientSequence.incrementAndGet(), "Mohit Verma", 41, "Male", "9123456780"));

        appointments.add(new Appointment(
                appointmentSequence.incrementAndGet(),
                patients.get(0).getId(),
                doctors.get(0).getId(),
                "2026-03-28T11:30",
                "Routine heart check-up",
                "Scheduled"
        ));
    }

    public User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public synchronized void addDoctor(String name, String specialization, String availability) {
        doctors.add(new Doctor(doctorSequence.incrementAndGet(), name, specialization, availability));
    }

    public synchronized void addPatient(String name, int age, String gender, String contact) {
        patients.add(new Patient(patientSequence.incrementAndGet(), name, age, gender, contact));
    }

    public synchronized void addAppointment(int patientId, int doctorId, String appointmentDate, String notes) {
        appointments.add(new Appointment(
                appointmentSequence.incrementAndGet(),
                patientId,
                doctorId,
                appointmentDate,
                notes,
                "Scheduled"
        ));
    }

    public List<Doctor> getDoctors() {
        return new ArrayList<>(doctors);
    }

    public List<Patient> getPatients() {
        return new ArrayList<>(patients);
    }

    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }

    public Doctor findDoctorById(int id) {
        return doctors.stream().filter(doctor -> doctor.getId() == id).findFirst().orElse(null);
    }

    public Patient findPatientById(int id) {
        return patients.stream().filter(patient -> patient.getId() == id).findFirst().orElse(null);
    }
}
