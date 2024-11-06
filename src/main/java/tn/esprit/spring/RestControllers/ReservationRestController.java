package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Reservation;
import tn.esprit.spring.DAO.Entities.Universite;
import tn.esprit.spring.Services.Reservation.IReservationService;
import tn.esprit.spring.Services.Universite.IUniversiteService;

import java.time.LocalDate;
import java.util.List;

@Rversion: '3.8'

        services:
        database:
        image: mysql:5.7
        container_name: mysql_db
        environment:
        MYSQL_ROOT_PASSWORD: root_password # Change this to a secure password
        MYSQL_DATABASE: foyer
        MYSQL_USER: root
        MYSQL_PASSWORD: root_password
        ports:
        - "3306:3306"
        volumes:
        - db_data:/var/lib/mysql

        app:
        image: openjdk:17-jdk-alpine
        container_name: spring_app
        environment:
        SPRING_DATASOURCE_URL: jdbc:mysql://database:3306/foyer?createDatabaseIfNotExist=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
        SPRING_DATASOURCE_USERNAME: root
        SPRING_DATASOURCE_PASSWORD: root_password
        SPRING_JPA_HIBERNATE_DDL_AUTO: update
        SERVER_PORT: 8081
        SERVER_SERVLET_CONTEXT_PATH: /Foyer
        depends_on:
        - database
        ports:
        - "8081:8081"
        volumes:
        - ./target:/app
        working_dir: /app
        command: ["java", "-jar", "Foyer-0.0.1-SNAPSHOT.jar"]

        volumes:
        db_data:
        estController
@RequestMapping("reservation")
@AllArgsConstructor
public class ReservationRestController {
    IReservationService service;

    @PostMapping("addOrUpdate")
    Reservation addOrUpdate(@RequestBody Reservation r) {
        return service.addOrUpdate(r);
    }

    @GetMapping("findAll")
    List<Reservation> findAll() {
        return service.findAll();
    }

    @GetMapping("findById")
    Reservation findById(@RequestParam String id) {
        return service.findById(id);
    }

    @DeleteMapping("delete")
    void delete(@RequestBody Reservation r) {
        service.delete(r);
    }

    @DeleteMapping("deleteById")
    void deleteById(@RequestParam String id) {
        service.deleteById(id);
    }

    @PostMapping("ajouterReservationEtAssignerAChambreEtAEtudiant")
    Reservation ajouterReservationEtAssignerAChambreEtAEtudiant(@RequestParam Long numChambre, @RequestParam long cin) {
        return service.ajouterReservationEtAssignerAChambreEtAEtudiant(numChambre, cin);
    }

    @GetMapping("getReservationParAnneeUniversitaire")
    long getReservationParAnneeUniversitaire(@RequestParam LocalDate debutAnnee, @RequestParam LocalDate finAnnee) {
        return service.getReservationParAnneeUniversitaire(debutAnnee, finAnnee);
    }

    @DeleteMapping("annulerReservation")
    String annulerReservation(@RequestParam long cinEtudiant) {
        return service.annulerReservation(cinEtudiant);
    }
}
