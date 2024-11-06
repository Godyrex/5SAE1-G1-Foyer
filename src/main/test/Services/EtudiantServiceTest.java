package Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.DAO.Entities.Etudiant;
import tn.esprit.spring.DAO.Repositories.EtudiantRepository;
import tn.esprit.spring.Services.Etudiant.EtudiantService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EtudiantServiceTest {

    @Mock
    private EtudiantRepository repo;

    @InjectMocks
    private EtudiantService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddOrUpdate() {
        Etudiant etudiant = new Etudiant();
        when(repo.save(etudiant)).thenReturn(etudiant);

        Etudiant result = service.addOrUpdate(etudiant);
        assertEquals(etudiant, result);
        verify(repo, times(1)).save(etudiant);
    }

    @Test
    void testFindAll() {
        List<Etudiant> etudiants = Arrays.asList(new Etudiant(), new Etudiant());
        when(repo.findAll()).thenReturn(etudiants);

        List<Etudiant> result = service.findAll();
        assertEquals(etudiants, result);
        verify(repo, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Etudiant etudiant = new Etudiant();
        when(repo.findById(1L)).thenReturn(Optional.of(etudiant));

        Etudiant result = service.findById(1L);
        assertEquals(etudiant, result);
        verify(repo, times(1)).findById(1L);
    }

    @Test
    void testDeleteById() {
        service.deleteById(1L);
        verify(repo, times(1)).deleteById(1L);
    }

    @Test
    void testDelete() {
        Etudiant etudiant = new Etudiant();
        service.delete(etudiant);
        verify(repo, times(1)).delete(etudiant);
    }
}