import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.DAO.Entities.Reservation;
import tn.esprit.spring.DAO.Repositories.ChambreRepository;
import tn.esprit.spring.DAO.Repositories.EtudiantRepository;
import tn.esprit.spring.DAO.Repositories.ReservationRepository;
import tn.esprit.spring.Services.Reservation.ReservationService;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class ReservationTest {
    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ChambreRepository chambreRepository;

    @Mock
    private EtudiantRepository etudiantRepository;

    private Reservation reservation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reservation = new Reservation();
        reservation.setIdReservation("123");
        reservation.setEstValide(true);
        reservation.setAnneeUniversitaire(LocalDate.now());
        reservation.setEtudiants(new ArrayList<>());
    }

    @Test
    void testAddOrUpdate() {
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
        Reservation result = reservationService.addOrUpdate(reservation);
        assertNotNull(result);
        assertEquals("123", result.getIdReservation());
    }

    @Test
    void testFindAll() {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);
        when(reservationRepository.findAll()).thenReturn(reservations);
        List<Reservation> result = reservationService.findAll();
        assertEquals(1, result.size());
    }

    @Test
    void testFindById() {
        when(reservationRepository.findById("123")).thenReturn(java.util.Optional.of(reservation));
        Reservation result = reservationService.findById("123");
        assertEquals("123", result.getIdReservation());
    }

    @Test
    void testDeleteById() {
        reservationService.deleteById("123");
        verify(reservationRepository, times(1)).deleteById("123");
    }

}
