package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Владимир", "Петров", (byte) 55);
        service.saveUser("Александр", "Кузнецов", (byte) 33);
        service.saveUser("Елена", "Шолохова", (byte) 33);
        service.saveUser("Анна", "Волкова", (byte) 23);
        System.out.println(service.getAllUsers().toString());
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
