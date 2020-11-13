package info.noip.darkportal.finance.data.service.jpa;

import info.noip.darkportal.finance.data.Messages;
import info.noip.darkportal.finance.data.model.Person;
import info.noip.darkportal.finance.data.repository.EntityNotFoundException;
import info.noip.darkportal.finance.data.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class JpaPersonServiceTest {

    private static final String FIRST_NAME = "James";
    private static final String LAST_NAME = "Bonds";
    private static final Long PERSON_ID = 1L;

    @Mock
    private PersonRepository personRepository;
    @Mock
    private Messages messages;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        //delegate call to repository, nothing to test
    }

    @Test
    void shouldThrowExceptionBecausePersonNotExists() {
        //given
        final Long nonExistingId = 9999L;
        Person newPerson = new Person().firstName(FIRST_NAME).lastName(LAST_NAME).id(nonExistingId);
        when(personRepository.existsById(nonExistingId)).thenReturn(false);
        JpaPersonService jpaPersonService = new JpaPersonService(personRepository, messages);

        //act and validate
        assertThrows(EntityNotFoundException.class,
                () -> {
                    jpaPersonService.update(newPerson);
                });
    }

    @Test
    void shouldUpdateTheGivenPerson() {
        //given
        Person existingPerson = new Person().firstName(FIRST_NAME).lastName(LAST_NAME).id(PERSON_ID);
        Person updatePerson = new Person().firstName("Jesus").lastName("Davinci").id(PERSON_ID);
        when(personRepository.existsById(PERSON_ID)).thenReturn(true);
        JpaPersonService jpaPersonService = new JpaPersonService(personRepository, messages);

        //act and validate
        jpaPersonService.update(updatePerson);
        verify(personRepository, times(1)).save(updatePerson);
    }
}