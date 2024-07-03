package org.acme;

import org.junit.jupiter.api.Test;
import org.mockito.*;

import dto.request.*;
import jakarta.validation.Validator;
import mapper.AppointmentMapper;
import models.*;
import models.enumerations.SpecialityType;
import repositories.AppointmentRepository;
import services.*;
import services.impl.AppointmentServiceImp;
import services.impl.UserServiceImp;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.time.*;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;

public class AppointmentTest{

    @InjectMocks //aplico a la clase que estoy testeando
    private AppointmentServiceImp appointmentService;

    @Mock //para dependencias q quiero simular
    private AppointmentRepository appointmentRepository;

    @Mock
    private UserServiceImp userService;

    @Mock
    private MedicalService medicalService;

    @Mock
    private Validator validator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks antes de cada prueba
        validator = Mockito.mock(Validator.class);
        assertNotNull(validator); 
    }

    @Test
    public void createAppointmentTest() throws Exception {

        //simulo los datos que un usuario ingresa al sistema
        AppointmentDto appointmentDto = new AppointmentDto(
            "Roberto", "Razon consulta", 
            LocalTime.now(), UUID.randomUUID(), UUID.randomUUID());

        when(userService.findUserById(any(UUID.class))).thenReturn(new User(
            "robert", "robert1234", "foto", "Roberto", "Baez", 
            "28469721", LocalDate.of(1990, 5, 15), false, LocalDateTime.now(), LocalDateTime.now()));
        
        List<Schedules> consultingDates = new ArrayList<>();
        consultingDates.add(new Schedules(DayOfWeek.MONDAY,LocalTime.of(8, 0), LocalTime.of(12, 0), true));

        when(medicalService.getMedicalById(any(UUID.class))).thenReturn(new Medical(
            "Medico 1", "M2547", "Hospital de Dios", SpecialityType.CARDIOLOGY, consultingDates));

        //creo un conjunto vacío de violaciones de restricción, lo que indica que la validación ha sido exitosa (no se han encontrado violaciones de restricción)
        when(validator.validate(appointmentDto)).thenReturn(Set.of());
        
        //llamo al verdadero metodo con los datos de la prueba cargados 
        appointmentService.createAppointment(appointmentDto);

        Appointment appointment = AppointmentMapper.dtoToAppointment(appointmentDto, null, null);
        //when(appointmentRepository.persist(any())).thenReturn(appointment);
        
        // verifico que se llamaron los métodos necesarios con los argumentos correctos
        verify(userService).findUserById(UUID.randomUUID());
        verify(medicalService).getMedicalById(UUID.randomUUID());
        verify(validator).validate(appointmentDto);
        verify(appointmentRepository).persist(appointment);
    }
}