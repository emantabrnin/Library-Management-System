package com.example.librarymanagment.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.librarymanagment.ValidationException;
import com.example.librarymanagment.entity.Patron;
import com.example.librarymanagment.repository.PatronRepository;


@ExtendWith(MockitoExtension.class)
public class PatronServiceTests {
      @Mock
    private PatronService patronService;

    @Mock
    private PatronRepository patronRepository;

    @Test
    public void createPatron_thenValidate() throws Exception {
        patronService = new PatronService(patronRepository);
        UUID id = UUID.randomUUID();
        Patron patron = createPatronWithId(id, "Patron1", "contactInformation");
    
        Mockito.when(patronRepository.save(patron)).thenReturn(patron);
    
        Patron PatronSaved = patronService.save(patron);
        
        Assertions.assertEquals(id, PatronSaved.getId());
        Assertions.assertEquals("Patron1", PatronSaved.getName());
        Assertions.assertEquals("contactInformation", PatronSaved.getContactInformation());
    }

    private Patron createPatronWithId(UUID id,String name,String contactInformation){
        return new Patron(id, name,contactInformation);
    }

   
    @Test
    public void editPatron_thenValidate() throws ValidationException{
        patronService = new PatronService(patronRepository);
        UUID id = UUID.randomUUID();
        Patron patron = createPatronWithId(id, "Patron1", "contactInformation");
    
        Mockito.when(patronRepository.save(patron)).thenReturn(createPatronWithId(id, "Patron1-edit", "contactInformation"));

        Patron PatronEdit = patronService.save(patron);
        Assertions.assertEquals(id, PatronEdit.getId());
        Assertions.assertEquals("Patron1-edit", PatronEdit.getName());
        Assertions.assertEquals("contactInformation", PatronEdit.getContactInformation());
    }

    @Test
    public void getPatronById_thenValidate(){
        patronService = new PatronService(patronRepository);
        UUID id = UUID.randomUUID();
        Patron patron = createPatronWithId(id, "Patron1", "contactInformation");
        Mockito.when(patronRepository.findById(id)).thenReturn(Optional.of(patron));
        Patron Patron2 = patronRepository.findById(id).get();
        Assertions.assertEquals(id, Patron2.getId());
        Assertions.assertEquals("Patron1", Patron2.getName());
        Assertions.assertEquals("contactInformation", Patron2.getContactInformation());
    }

    @Test
    public void deletePatron_thenValidate(){
        patronService = new PatronService(patronRepository);
        UUID id = UUID.randomUUID();
        patronService.delete(id);
        Mockito.verify(patronRepository,Mockito.times(1)).deleteById(id);
    }
}
