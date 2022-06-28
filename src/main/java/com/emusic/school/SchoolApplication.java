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


			Client client1 = new Client("Juan","Perez","juanperez@gmail.com", passwordEncoder.encode("1234"), true);
			client1.setVerified(true);
			clientRepository.save(client1);
			Client client2 = new Client("Paul","Gray","paulgray@gmail.com", passwordEncoder.encode("1234"), true);
			clientRepository.save(client2);
			Client client3 = new Client("Emilia","Bailey","emiliabailey@gmail.com", passwordEncoder.encode("1234"), true);
			clientRepository.save(client3);
			Client client4 = new Client("Riley","Douglas","rileydouglas@gmail.com", passwordEncoder.encode("1234"), true);
			clientRepository.save(client4);

			Teacher teacher = new Teacher("Mike","Portnoy","mikeportnoy@gmail.com",passwordEncoder.encode("123456"), "Drums");
			teacherRepository.save(teacher);
			Teacher teacher1 = new Teacher("Mike","Thompson","mikethompson@gmail.com",passwordEncoder.encode("123456"), "Guitar");
			teacherRepository.save(teacher1);
			Teacher teacher2 = new Teacher("Richard","Kotzen","richiekotzen@gmail.com",passwordEncoder.encode("123456"), "Sing");
			teacherRepository.save(teacher2);
			Teacher teacher3 = new Teacher("Tom","Morello","tommorello@gmail.com",passwordEncoder.encode("123456"), "Bass");
			teacherRepository.save(teacher3);
			Teacher teacher4 = new Teacher("Maynard","Keenan","maynardkeenan@gmail.com",passwordEncoder.encode("123456"), "Piano");
			teacherRepository.save(teacher4);
			Teacher teacher5 = new Teacher("Joey","Jordison","joeyjordison@gmail.com",passwordEncoder.encode("123456"), "Sax");
			teacherRepository.save(teacher5);


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

			Merch merch1= new Merch(10,"cap",650,df,true);
			merchRepository.save(merch1);
			Merch merch2= new Merch(10,"t-shirt",1200,m,true);
			merchRepository.save(merch2);
			Merch merch3= new Merch(10,"key-ring",250,m,true);
			merchRepository.save(merch3);
			Merch merch4= new Merch(10,"logbook",420,m,true);
			merchRepository.save(merch4);
			Merch merch5= new Merch(10,"cover",3700,m,true);
			merchRepository.save(merch5);
			Merch merch6= new Merch(10,"cup",450,m,true);
			merchRepository.save(merch6);
			Merch merch7= new Merch(10,"t-shirt",1200,m,true);
			merchRepository.save(merch7);
			Merch merch8= new Merch(10,"cap",650,m,true);
			merchRepository.save(merch8);


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
