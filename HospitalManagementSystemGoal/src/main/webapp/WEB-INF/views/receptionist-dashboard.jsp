<%@ page import="java.util.List" %>
<%@ page import="com.hospital.model.Doctor" %>
<%@ page import="com.hospital.model.Patient" %>
<%@ page import="com.hospital.model.User" %>
<%@ page import="com.hospital.servlet.ReceptionistDashboardServlet.AppointmentView" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User currentUser = (User) session.getAttribute("loggedInUser");
    List<Doctor> doctors = (List<Doctor>) request.getAttribute("doctors");
    List<Patient> patients = (List<Patient>) request.getAttribute("patients");
    List<AppointmentView> appointmentViews = (List<AppointmentView>) request.getAttribute("appointmentViews");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Receptionist Dashboard</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/app.css">
</head>
<body>
<div class="page-shell">
    <div class="topbar">
        <div>
            <p class="eyebrow">Reception Desk</p>
            <h1>Welcome, <%= currentUser.getDisplayName() %></h1>
        </div>
        <div class="topbar-actions">
            <a href="<%= request.getContextPath() %>/dashboard" class="secondary-btn">Dashboard</a>
            <a href="<%= request.getContextPath() %>/logout" class="secondary-btn">Logout</a>
        </div>
    </div>

    <div class="content-grid">
        <section class="panel">
            <h2>Book Appointment</h2>
            <form method="post" action="<%= request.getContextPath() %>/receptionist/dashboard" class="stack">
                <label>Patient</label>
                <select name="patientId" required>
                    <% for (Patient patient : patients) { %>
                    <option value="<%= patient.getId() %>"><%= patient.getName() %> (<%= patient.getContact() %>)</option>
                    <% } %>
                </select>

                <label>Doctor</label>
                <select name="doctorId" required>
                    <% for (Doctor doctor : doctors) { %>
                    <option value="<%= doctor.getId() %>"><%= doctor.getName() %> - <%= doctor.getSpecialization() %></option>
                    <% } %>
                </select>

                <label>Appointment Date &amp; Time</label>
                <input type="datetime-local" name="appointmentDate" required>

                <label>Notes</label>
                <textarea name="notes" rows="4" placeholder="Reason for visit"></textarea>

                <button type="submit">Create Appointment</button>
            </form>
        </section>

        <section class="panel">
            <div class="section-title">
                <h2>Doctor Availability</h2>
            </div>
            <table>
                <thead>
                <tr>
                    <th>Doctor</th>
                    <th>Specialization</th>
                    <th>Availability</th>
                </tr>
                </thead>
                <tbody>
                <% for (Doctor doctor : doctors) { %>
                <tr>
                    <td><%= doctor.getName() %></td>
                    <td><%= doctor.getSpecialization() %></td>
                    <td><%= doctor.getAvailability() %></td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </section>
    </div>

    <section class="panel">
        <div class="section-title">
            <h2>Appointment Queue</h2>
        </div>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Patient</th>
                <th>Doctor</th>
                <th>Date</th>
                <th>Status</th>
                <th>Notes</th>
            </tr>
            </thead>
            <tbody>
            <% for (AppointmentView appointment : appointmentViews) { %>
            <tr>
                <td><%= appointment.getId() %></td>
                <td><%= appointment.getPatientName() %></td>
                <td><%= appointment.getDoctorName() %></td>
                <td><%= appointment.getAppointmentDate() %></td>
                <td><span class="badge"><%= appointment.getStatus() %></span></td>
                <td><%= appointment.getNotes() %></td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </section>
</div>
</body>
</html>
