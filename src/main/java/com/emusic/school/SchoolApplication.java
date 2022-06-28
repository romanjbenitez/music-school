package com.emusic.school;

import com.emusic.school.models.Client;
import com.emusic.school.models.Merch;
import com.emusic.school.models.PurchaseOrder;
import com.emusic.school.models.Ticket;
import com.emusic.school.repositories.ClientRepository;
import com.emusic.school.repositories.MerchRepository;
import com.emusic.school.repositories.PurchaseOrderRepository;
import com.emusic.school.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.emusic.school.models.*;
import com.emusic.school.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.emusic.school.models.MerchWaist.df;
import static com.emusic.school.models.MerchWaist.m;

@SpringBootApplication
public class SchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner initDate(ClientRepository clientRepository, MerchRepository merchRepository,
									  TicketRepository ticketRepository, PurchaseOrderRepository purchaseOrderRepository,
									  CourseRepository courseRepository,CourseTicketRepository courseTicketRepository,
									  TeacherRepository teacherRepository) {
		return (args) -> {


			Client client1 = new Client("Juan","Perez","dsada@gmail.com", passwordEncoder.encode("1234"), true);
			clientRepository.save(client1);

			Teacher teacher = new Teacher("ale","rodriguez","ale@gmail.com",passwordEncoder.encode("123456"));
			teacherRepository.save(teacher);

			Course course = new Course("principiante","Guitar",4,20000D,20,true,teacher);
			courseRepository.save(course);
			Course course1 = new Course("principiante", "Drums", 4, 30000D, 23, true,teacher);
			courseRepository.save(course1);
			Course course2 = new Course("principiante", "Sing", 8, 18500D, 25, true,teacher);
			courseRepository.save(course2);
			Course course3 = new Course("expert", "Sax", 6, 15900D, 18, true,teacher);
			courseRepository.save(course3);
			Course course4 = new Course("middle", "Bass", 6, 18500D, 21, true,teacher);
			courseRepository.save(course4);
			Course course5 = new Course("expert", "Piano", 10, 12000D, 20, true,teacher);
			courseRepository.save(course5);

			Merch merch1= new Merch(10,"Gorra",200,df,true);
			Merch merch2= new Merch(10,"Remara",200,m,true);
			merchRepository.save(merch1);
			merchRepository.save(merch2);

			Ticket ticket1 = new Ticket(500,client1);
			ticketRepository.save(ticket1);

			CourseTicket courseTicket = new CourseTicket(ticket1,course);
			courseTicketRepository.save(courseTicket);

			Ticket ticket2 = new Ticket(400,client1);
			ticketRepository.save(ticket2);

			PurchaseOrder purchaseOrder = new PurchaseOrder(ticket1,merch1);
			PurchaseOrder purchaseOrder2 = new PurchaseOrder(ticket2,merch2);
			purchaseOrderRepository.save(purchaseOrder);
			purchaseOrderRepository.save(purchaseOrder2);


			System.out.println("---------------------------------");
			System.out.println("EMusic App iniciada, Let's Rock! ");
			System.out.println("---------------------------------");
		};
	}
}
