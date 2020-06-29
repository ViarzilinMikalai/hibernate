package by.itstep.viarzilinFour;

import by.itstep.viarzilinFour.daos.AutoDao;
import by.itstep.viarzilinFour.domain.Auto;
import by.itstep.viarzilinFour.domain.User;
import by.itstep.viarzilinFour.services.AutoService;
import by.itstep.viarzilinFour.services.UserService;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        AutoDao autoDao = new AutoDao();
        UserService userService = new UserService();
        AutoService autoService = new AutoService();
        User user = new User("Masha",26);
        userService.saveUser(user);
        Auto ferrari = new Auto("Ferrari", "red");
        ferrari.setUser(user);
        autoService.saveAuto(ferrari);
        user.addAuto(ferrari);
        userService.updateUser(user);


        Auto ford = new Auto("Ford", "black");
        ford.setUser(user);
        user.addAuto(ford);
        autoService.saveAuto(ford);
        userService.updateUser(user);

//        System.out.println("User 1:" + userService.findUser(1).toString());
//
//        System.out.println("Auto 1:" + autoService.findAuto(2).toString());

//
        List<User> users = userService.findAllUsers();

        User userRead = users.get(0);
        System.out.println(userRead);

//        for(User us:users){
//            System.out.println(us.toString());
//        }
//
//        List<Auto> autos = autoDao.findAll();
//        System.out.println("Список авто:");
//        for (Auto au : autos){
//            System.out.println(au.getModel() + " " + au.getUser().getName());
//        }

//        System.out.println("Auto 2:");
//        autoService.findAuto(2).toString();
        System.exit(0);
    }

}
