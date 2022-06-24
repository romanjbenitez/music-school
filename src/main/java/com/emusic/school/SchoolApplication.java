package com.emusic.school;

import com.emusic.school.models.*;
import com.emusic.school.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}
	@Bean
	public CommandLineRunner initDate(ClientRepository clientRepository, MerchRepository merchRepository,
									  TicketRepository ticketRepository, PurchaseOrderRepository purchaseOrderRepository,
									  CourseRepository courseRepository,CourseTicketRepository courseTicketRepository,
									  TeacherRepository teacherRepository) {
		return (args) -> {

			Teacher teacher = new Teacher("ale","rodriguez","ale@gmail.com","123456");
			teacherRepository.save(teacher);

			Course course = new Course("principiante","guitarra",4,20000,20,true,teacher);
			courseRepository.save(course);


			Client client1 = new Client("Juan","Perez","dsada@gmail.com","1234",true);
			clientRepository.save(client1);

			Merch merch1= new Merch(10,"Gorra",200,"Adulto");
			Merch merch2= new Merch(10,"Remara",200,"Adulto");
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



			System.out.println("Aplicacion iniciada");
		};
	}
}
