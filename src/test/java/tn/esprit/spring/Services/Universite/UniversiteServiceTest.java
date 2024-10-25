package tn.esprit.spring.Services.Universite;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.DAO.Entities.Foyer;
import tn.esprit.spring.DAO.Entities.Universite;
import tn.esprit.spring.DAO.Repositories.UniversiteRepository;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


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

   };

    ///find by id
    //deleteById
    //delete

}
