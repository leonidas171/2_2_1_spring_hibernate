package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@gmail.com");
        User user2 = new User("User2", "Lastname2", "user2@gmail.com");
        User user3 = new User("User3", "Lastname3", "user3@gmail.com");
        User user4 = new User("User4", "Lastname4", "user4@gmail.com");

        Car car1 = new Car("car1", 1000);
        Car car2 = new Car("car2", 2000);
        Car car3 = new Car("car3", 3000);
        Car car4 = new Car("car4", 4000);

        userService.add(user1.setCar(car1).setUser(user1));
        userService.add(user2.setCar(car2).setUser(user2));
        userService.add(user3.setCar(car3).setUser(user3));
        userService.add(user4.setCar(car4).setUser(user4));

        for (User user : userService.listUsers()) {
            System.out.println(user + " " + user.getCar());
        }

        System.out.println(userService.getUserByCar("car2", 2000));

        try {
            User notFound = userService.getUserByCar("car0", 0);
        } catch (NoResultException e) {
            System.out.println("User not found");
        }

        context.close();
    }
}
