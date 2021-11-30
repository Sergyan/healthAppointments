package es.uca.spifm.citasapi;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import es.uca.spifm.citasapi.appointment.AppointmentService;
import es.uca.spifm.citasapi.appointment.AppointmentType;
import es.uca.spifm.citasapi.user.User;
import es.uca.spifm.citasapi.user.UserService;

@SpringBootApplication
@EnableJpaRepositories
public class AppoinmentApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private AppointmentService appointmentService;

//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any()).build();
//	}

	public static void main(String[] args) {
		SpringApplication.run(AppoinmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if (userService.count() == 0) {
			User doctor = new User();
			doctor.setFirstName("Doctor");
			doctor.setLastName("Facultativo");
			doctor.setIdentityDocument("0101010");
			doctor=userService.save(doctor);
			
			
			User user = new User();
			user.setFirstName("Pepe");
			user.setLastName("Andaluz");
			user.setIdentityDocument("123");
			user.setDoctor(doctor);
			user=userService.save(user);
			
			appointmentService.confirmAppointment(user.getIdentityDocument(), LocalDateTime.of(2021, 12, 20, 18, 0),
					AppointmentType.FACE_TO_FACE, "dolor garganta");

			
			user = new User();
			user.setFirstName("María");
			user.setLastName("Andaluza");
			user.setIdentityDocument("456");
			user.setDoctor(doctor);
			user=userService.save(user);

			appointmentService.confirmAppointment(user.getIdentityDocument(), LocalDateTime.of(2021, 12, 30, 12, 0),
					AppointmentType.FACE_TO_FACE, "revisión");

		}

		// TODO Auto-generated method stub

	}

}
