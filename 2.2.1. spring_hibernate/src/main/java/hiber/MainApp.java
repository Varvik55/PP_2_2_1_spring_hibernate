package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      Car car1 = new Car("sa1",11);
      Car car2 = new Car("sa2",12);
      Car car3 = new Car("sa3",13);
      Car car4 = new Car("sa4",14);


      userService.add(new User("User1", "Lastname1", "user1@mail.ru",car1),car1);
      userService.add(new User("User2", "Lastname2", "user2@mail.ru",car2),car2);
      userService.add(new User("User3", "Lastname3", "user3@mail.ru",car3),car3);
      userService.add(new User("User4", "Lastname4", "user4@mail.ru",car4),car4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car: "+user.getCar().getModel()+" ("+user.getCar().getSeries()+") ");
         System.out.println();
      }

      userService.lastUsеrByModel("sa4").forEach(System.out::println);

      context.close();
   }
}
