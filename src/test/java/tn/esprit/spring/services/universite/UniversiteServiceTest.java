package tn.esprit.spring.services.universite;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.dao.entities.Foyer;
import tn.esprit.spring.dao.entities.Universite;
import tn.esprit.spring.dao.repositories.UniversiteRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UniversiteServiceTest {
    @Mock
    private Universite universite ;
    @InjectMocks
    private UniversiteService universiteService ;
    @Mock
    private UniversiteRepository universiteRepository;

    @Test
    void addOrUpdate() {
        Universite u = new Universite() ;
        Foyer f = new Foyer (); //creation objet
        u.setAdresse("tebourba");
        u.setNomUniversite("esprit");
        f.setNomFoyer("espoir");
        u.setFoyer(f);
        when ( universiteRepository.save(u)).thenReturn (u);

        //instance service
        Universite savedUni = universiteService.addOrUpdate(u);
        assertNotNull(savedUni);
        assertNotNull(u);
        assertEquals("esprit" , savedUni.getNomUniversite());

    }
   @Test
    void  findAll(){
       Universite u = new Universite() ;
       Universite u2 = new Universite() ;
       Foyer f = new Foyer (); //creation objet
       Foyer f2 = new Foyer (); //creation objet

       u.setAdresse("tebourba");
       u.setNomUniversite("esprit");
       f.setNomFoyer("espoir");
       u.setFoyer(f);
       //U2
       u2.setAdresse("Parix");
       u2.setNomUniversite("IMT");
       f2.setNomFoyer("espoir");
       u2.setFoyer(f);

      when ( universiteRepository.findAll() ).thenReturn (Arrays.asList( u , u2))  ;
       List<Universite> listUni  = universiteService.findAll(); // List University

       assertNotNull(listUni);
       assertEquals(2, listUni.size());

   }

    @Test
    void findById() {
        Universite u = new Universite();
        u.setIdUniversite(1L);
        u.setNomUniversite("esprit");

        when(universiteRepository.findById(1L)).thenReturn(Optional.of(u));

        Universite foundUni = universiteService.findById(1L);

        assertNotNull(foundUni);
        assertEquals("esprit", foundUni.getNomUniversite());
    }

    @Test
    void deleteById() {
        long idToDelete = 1L;

        doNothing().when(universiteRepository).deleteById(idToDelete);

        universiteService.deleteById(idToDelete);

        verify(universiteRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    void delete() {
        Universite u = new Universite();
        u.setIdUniversite(1L);

        doNothing().when(universiteRepository).delete(u);

        universiteService.delete(u);

        verify(universiteRepository, times(1)).delete(u);
    }


}
