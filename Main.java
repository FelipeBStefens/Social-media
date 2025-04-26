package socialmedia;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        while (true) { 
            
            System.out.println("""
                    \n\nWelcome to the social media:
                    1 - Sign up a new User;
                    2 - Sign in a new User;
                    3 - Leave;\n
                    """);
            int logInMenu = scanner.nextInt();

            if (logInMenu == 1) {
                
                User user;

                System.out.println("Create a new User!!\n\nPut the username:");
                String username = scanner.next();

                System.out.println("Put the At-Sign:");
                String atSign = scanner.next();

                System.out.println("Put the E-mail:");
                String email = scanner.next();

                System.out.println("Put the Password");
                String password = scanner.next();
                
                user = new User(username, email, password, atSign);
                try {
                    UserDAO.addUser(user);
                    enterUser(user);
                } 
                catch (Exception e) {
                    System.out.println("\n\nError: User cannot be added!!");
                }
            }
            else if (logInMenu == 2) {

                User user;

                System.out.println("Put the E-mail:");
                String email = scanner.next();

                System.out.println("Put the Password");
                String password = scanner.next();

                try {
                    user = UserDAO.findUser(email, password);  
                    enterUser(user);  
                }
                catch (Exception e) {
                    System.out.println("\n\nError: User cannot be founded!!");
                }
            }
            else if (logInMenu == 3) {

                System.out.println("Leaving the code, goodbye!");
                break;
            }
            else {

                System.out.println("Invalid value...");
            }
        }
    }

    public static void enterUser(User user) {

        while (true) { 
            
            System.out.println("""
                    \n\nUser was connected!!
                    1 - Create, delete or update a new mensage;
                    2 - See all your mensages;
                    3 - See or comment the mensages of other user;
                    4 - Configuration your user account;
                    5 - Leave;
                    """);
            int menuValue = scanner.nextInt();

            if (menuValue == 1) {

                System.out.println("Will you:\n1 - Create\n2 - Delete\n3 - Update");
                int valueMensage = scanner.nextInt();

                try {

                    if (valueMensage == 1) {

                        System.out.println("Put the value of the mensage:");
                        scanner.nextLine();
                        String value = scanner.nextLine();
                        
                        MensageDAO.addMensage(new Mensage(value, user, null));
                                
                        System.out.println("The mensage have been added!");
                    }
                    else if (valueMensage == 2) {
                        
                        ArrayList<Mensage> allMensages = MensageDAO.showAllMensagesByUser(user);

                        System.out.println("All your mensages:");
                        for (int i = 0; i < allMensages.size(); i++) {
                            System.out.printf("""
                                %n%d - %s
                                id : %d ; idFather : %d%n%n    
                                """, i + 1, allMensages.get(i).getValue(), allMensages.get(i).getIdMensage(), 
                                allMensages.get(i).getIdMensageFather());
                        }

                        System.out.println("What mensage do you want to delete?");
                        int deleteMensage = scanner.nextInt();

                        if (deleteMensage > 0 && deleteMensage <= allMensages.size()) {

                            MensageDAO.deleteMensage(allMensages.get(deleteMensage - 1).getIdMensage());                            
                            System.out.println("The mensage has been deleted!");
                        }
                        else {

                            System.out.println("Invalid value...");
                        }
                    }
                    else if (valueMensage == 3) {
                        
                        ArrayList<Mensage> allMensages = MensageDAO.showAllMensagesByUser(user);

                        System.out.println("All your mensages:");
                        for (int i = 0; i < allMensages.size(); i++) {
                            System.out.printf("""
                                %n%d - %s
                                id : %d ; idFather : %d%n%n    
                                """, i + 1, allMensages.get(i).getValue(), allMensages.get(i).getIdMensage(), 
                                allMensages.get(i).getIdMensageFather());
                        }

                        System.out.println("What mensage do you want to update?");
                        int updateMensage = scanner.nextInt();

                        if (updateMensage > 0 && updateMensage <= allMensages.size()) {

                            System.out.println("Insert the new value:");
                            scanner.nextLine();
                            String value = scanner.nextLine();
                            
                            MensageDAO.updateMensage(allMensages.get(updateMensage - 1).getIdMensage(), 
                                value);

                            System.out.println("The mensage has been updated!");
                        }
                        else {

                            System.out.println("Invalid value...");
                        }
                    }
                    else {
    
                        System.out.println("Invalid value...");
                    }
                } 
                catch (Exception e) {

                    e.printStackTrace();
                    System.out.println("Cannot find or create or delete or update your mensages...");
                }
                
            }
            else if (menuValue == 2) {

                try {

                    ArrayList<Mensage> allUserMensages = MensageDAO.showAllMensagesByUser(user);
                    for (Mensage mensage : allUserMensages) {
                        System.out.printf("""
                        %n%s
                        id : %d ; idFather : %d%n%n    
                        """, mensage.getValue(), mensage.getIdMensage(), mensage.getIdMensageFather());
                    }
                } 
                catch (Exception e) {

                    e.printStackTrace();
                    System.out.println("Cannot find your mensages...");
                }
            }
            else if (menuValue == 3) {

                try {
                    
                    ArrayList<Mensage> allMensages = MensageDAO.showAllMensages();
                    for (int i = 0; i < allMensages.size(); i++) {

                        System.out.printf("""
                        %n%d - %s
                        user : %s ; id : %d ; idFather : %d%n%n    
                        """, i + 1, allMensages.get(i).getValue(), allMensages.get(i).getUser().getName(), 
                        allMensages.get(i).getIdMensage(), allMensages.get(i).getIdMensageFather());
                    }

                    System.out.println("What mensage do you want to comment?");
                    int commentMensage = scanner.nextInt();

                    if (commentMensage > 0 && commentMensage <= allMensages.size()) {

                        System.out.println("Put the value of the mensage:");
                        scanner.nextLine();
                        String value = scanner.nextLine();
                        
                        MensageDAO.addMensage(new Mensage(value, user, 
                            allMensages.get(commentMensage - 1).getIdMensage()));
                            
                        System.out.println("The comments have been added!");
                    }
                    else {

                        System.out.println("Invalid value...");
                    }
                } 
                catch (Exception e) {
                    
                    e.printStackTrace();
                    System.out.println("Cannot find your mensages...");
                }
            }
            else if (menuValue == 4) {

                try {

                    boolean configurationsUser = configurationUser(user);

                    if (configurationsUser) {
                        break;
                    }
                } 
                catch (Exception e) {

                    e.printStackTrace();
                    System.out.println("Cannot delete and update the User...");
                }

            }
            else if (menuValue == 5) {

                System.out.println("Leaving the account, goodbye!\n\n");
                break;
            }
            else {

                System.out.println("Invalid value... \n\n");
            }
        } 
    }

    public static boolean configurationUser(User user) throws Exception {

        while (true) { 
            
            System.out.println("""
                    \n\nUser Configuration:
                    1 - Delete this user;
                    2 - Update the values of the users;
                    3 - Leave;  
                    """);
            int menuValue = scanner.nextInt();

            if (menuValue == 1) {

                System.out.println("Deleting the account, goodbye!!\n\n");
                UserDAO.deleteUser(user);
                return true;
            }
            else if (menuValue == 2) {

                System.out.println("Put the new username:");
                String userName = scanner.next();
                System.out.println("Put the new password:");
                String password = scanner.next();

                UserDAO.updateUser(user, userName, password);

                System.out.println("The User has been updated...");
            }
            else if (menuValue == 3) {

                System.out.println("Leaving the configurations...\n\n");
                return false;
            }
            else {

                System.out.println("Invalid value... \n\n");
            }
        }
    }
}