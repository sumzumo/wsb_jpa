package com.jpacourse.rest;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
@RestController
@RequestMapping("/api/patients")
public class PatientController
{
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientTO> getPatientById(@PathVariable Long id) {
        PatientTO patient = patientService.getPatientById(id);
        if (patient == null) {
            throw new EntityNotFoundException(id);
        }
        return ResponseEntity.ok(patient);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PatientTO> createOrUpdatePatient(@RequestBody PatientTO patientTO) {
        PatientTO savedPatient = patientService.saveOrUpdatePatient(patientTO);
        return ResponseEntity.ok(savedPatient);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{id}/visits")
    public ResponseEntity<Void> addVisit(@PathVariable Long id, @RequestParam Long doctorId, @RequestParam String description, @RequestParam String time) {
        LocalDateTime visitTime = LocalDateTime.parse(time);
        patientService.addVisit(id, doctorId, description, visitTime);
        return ResponseEntity.ok().build();
    }
}