-- 1. Insert into ADDRESS
INSERT INTO address (id, city, address_line1, address_line2, postal_code)
VALUES
    (1, 'Wrocław', 'Czysta 4', '', '11-234'),
    (2, 'Warszawa', 'Fabryczna 55', 'Home', '50-131'),
    (3, 'Gdańsk', 'Antoniego Słonimskiego 11', '', '22-222');

-- 2. Insert into DOCTOR
INSERT INTO doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
VALUES
    (1, 'Blaze', 'Bronka', '123456789', 'blazejbronka@gmail.com', 'DOC001', 'HANDS', 1),
    (2, 'Konrad', 'Jaszczyk', '987654321', '10minutesemail@gmail.com', 'DOC002', 'FEET', 2);

-- 3. Insert into PATIENT
INSERT INTO patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id)
VALUES
    (1, 'Ania', 'Blabla', '555666777', 'whoknows@gmail.com', 'PAT001', '1985-10-10', 3),
    (2, 'Justyna', 'Yapyap', '444555666', 'whoknowsher@gmail.com', 'PAT002', '1990-05-15', 2);

-- 4. Insert into VISIT
INSERT INTO visit (id, description, time, doctor_id, patient_id)
VALUES
    (1, 'Routine check-up', '2024-12-06 10:00:00', 1, 1),
    (2, 'Neurology consultation', '2024-12-07 14:30:00', 2, 2);

-- 5. Insert into MEDICAL_TREATMENT
INSERT INTO medical_treatment (id, description, type, visit_id)
VALUES
    (1, 'Blood test', 'LAB', 1),
    (2, 'CT scan', 'IMAGING', 2),
    (3, 'Cardiology consultation', 'CONSULTATION', 1);
