<%@ page import="java.util.List" %>
<%@ page import="com.hospital.model.Doctor" %>
<%@ page import="com.hospital.model.Patient" %>
<%@ page import="com.hospital.model.Appointment" %>
<%@ page import="com.hospital.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User currentUser = (User) session.getAttribute("loggedInUser");
    List<Doctor> doctors = (List<Doctor>) request.getAttribute("doctors");
    List<Patient> patients = (List<Patient>) request.getAttribute("patients");
    List<Appointment> appointments = (List<Appointment>) request.getAttribute("appointments");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/app.css">
</head>
<body>
<div class="page-shell">
    <div class="topbar">
        <div>
            <p class="eyebrow">Admin Panel</p>
            <h1>Welcome, <%= currentUser.getDisplayName() %></h1>
        </div>
        <div class="topbar-actions">
            <a href="<%= request.getContextPath() %>/dashboard" class="secondary-btn">Dashboard</a>
            <a href="<%= request.getContextPath() %>/logout" class="secondary-btn">Logout</a>
        </div>
    </div>

    <div class="stats-grid">
        <div class="stat-card">
            <span>Total Doctors</span>
            <strong><%= doctors.size() %></strong>
        </div>
        <div class="stat-card">
            <span>Total Patients</span>
            <strong><%= patients.size() %></strong>
        </div>
        <div class="stat-card">
            <span>Total Appointments</span>
            <strong><%= appointments.size() %></strong>
        </div>
    </div>

    <div class="content-grid">
        <section class="panel">
            <h2>Add Doctor</h2>
            <form method="post" action="<%= request.getContextPath() %>/admin/dashboard" class="stack">
                <input type="hidden" name="entityType" value="doctor">
                <label>Doctor Name</label>
                <input type="text" name="name" required>
                <label>Specialization</label>
                <input type="text" name="specialization" required>
                <label>Availability</label>
                <input type="text" name="availability" placeholder="Mon-Fri 10:00-16:00" required>
                <button type="submit">Save Doctor</button>
            </form>
        </section>

        <section class="panel">
            <h2>Add Patient</h2>
            <form method="post" action="<%= request.getContextPath() %>/admin/dashboard" class="stack">
                <input type="hidden" name="entityType" value="patient">
                <label>Patient Name</label>
                <input type="text" name="name" required>
                <label>Age</label>
                <input type="number" name="age" min="0" required>
                <label>Gender</label>
                <input type="text" name="gender" required>
                <label>Contact</label>
                <input type="text" name="contact" required>
                <button type="submit">Save Patient</button>
            </form>
        </section>
    </div>

    <div class="content-grid">
        <section class="panel">
            <div class="section-title">
                <h2>Doctors</h2>
            </div>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Specialization</th>
                    <th>Availability</th>
                </tr>
                </thead>
                <tbody>
                <% for (Doctor doctor : doctors) { %>
                <tr>
                    <td><%= doctor.getId() %></td>
                    <td><%= doctor.getName() %></td>
                    <td><%= doctor.getSpecialization() %></td>
                    <td><%= doctor.getAvailability() %></td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </section>

        <section class="panel">
            <div class="section-title">
                <h2>Patients</h2>
            </div>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Gender</th>
                    <th>Contact</th>
                </tr>
                </thead>
                <tbody>
                <% for (Patient patient : patients) { %>
                <tr>
                    <td><%= patient.getId() %></td>
                    <td><%= patient.getName() %></td>
                    <td><%= patient.getAge() %></td>
                    <td><%= patient.getGender() %></td>
                    <td><%= patient.getContact() %></td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </section>
    </div>
</div>
</body>
</html>
