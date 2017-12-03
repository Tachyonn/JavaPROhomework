package JDBCDemo;

import java.util.List;
import java.util.Scanner;

public class LogicLayer {
    private ClientDAO dao;

    public LogicLayer(ClientDAO dao) {
        this.dao = dao;
    }

    public void menuStart() {

        dao.init();

        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                printMenu();
                String s = sc.nextLine();
                switch (s) {
                    case "1":
                        addClient(sc);
                        break;
                    case "2":
                        deleteClient(sc);
                        break;
                    case "3":
                        updateClient(sc);
                        break;
                    case "4":
                        viewAllClients();
                        break;
                    case "5":
                        System.out.println(dao.count() + " lines.");
                    default:
                        return;
                }
            }
        } finally {
            sc.close();
        }
    }

    private void printMenu() {
        System.out.println("1: add client");
        System.out.println("2: delete client");
        System.out.println("3: change client");
        System.out.println("4: view clients");
        System.out.println("5: view lines count");
        System.out.println("Enter empty line to exit");
        System.out.print("-> ");
    }

    private void addClient(Scanner scanner) {
        System.out.print("Enter name of the client: ");
        String name = scanner.nextLine();
        System.out.print("Enter age of the client: ");
        int age = scanner.nextInt();
        dao.addClient(name, age);
    }

    private void deleteClient(Scanner scanner) {
        System.out.print("Enter client's ID to remove: ");
        int id = scanner.nextInt();
        dao.deleteClient(id);
    }

    private void updateClient(Scanner scanner) {
        System.out.print("Enter client's ID to update: ");
        int id = scanner.nextInt();
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new age: ");
        int newAge = scanner.nextInt();
        dao.changeClient(id, newName, newAge);

    }

    private void viewAllClients() {
        List<Client> clientsList = dao.getAll();
        clientsList.forEach(System.out::println);
    }
}

