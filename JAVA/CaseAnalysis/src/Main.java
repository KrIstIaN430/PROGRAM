
import java.util.Scanner;


/**
 *
 * @author KrIstIaN
 */
public class Main {
    static Scanner input = new Scanner(System.in);
    static Teacher[] teach = new Teacher[10];
    static Student[] stud = new Student[10];
    static int sCtr = 0, tCtr = 0;
    public static void main(String[] args) {
        while(true){
            try{
                int choice;
                while(true){
                    System.out.println("[1] - Student");
                    System.out.println("[2] - Teacher");
                    System.out.print("Enter your choice: ");
                    choice = input.nextInt();

                    switch(choice){

                        default:
                            System.out.println("Invalid input. Try again..");
                            break;
                        case 1:
                            studMenu();
                            break;
                        case 2:
                            teachMenu();
                            break;
                    }
                }
            }  
            catch(Exception e)
            {
                input.nextLine();
                System.out.println("Invalid input. Try again..");
                pressAnyKeyToContinue();
            }
        }
    }
    
    static boolean checkID(int ID){

        for(int i = 0; i < sCtr; i++){
            if(ID == stud[i].ID){
                System.out.println("ID already taken. try again..");
                return true;
            }
        }
        for(int i = 0; i < tCtr; i++){
            if(ID == teach[i].ID){
                System.out.println("ID already taken. try again..");
                return true;
            }
        }
        return false;
    }

    static void studMenu(){
        int choice;
        double amount;

        do{
            System.out.println("[1] - Add new student");
            System.out.println("[2] - Update student");
            System.out.println("[3] - Show remaining balance");
            System.out.println("[4] - Fee deposit");
            System.out.println("[5] - Display all students with zero balance");
            System.out.println("[6] - Display all students with non-zero balance");
            System.out.println("[7] - Go back to main menu");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            switch(choice){
                default:
                    System.out.println("Invalid input. Try again..");
                    pressAnyKeyToContinue();
                    break;
                case 1:
                    stud[sCtr] = new Student();
                    stud[sCtr] = addStud(teach, stud);
                    sCtr++;
                    break;  
                case 2:
                    showStud(stud, 0);
                    if(sCtr != 0){
                        choice = 0;
                        do{
                        System.out.print("choose student to update: ");
                        choice = input.nextInt() - 1;
                        if(choice >= sCtr){
                                System.out.println("Chosen student is not yet enrolled. try again..");
                        }
                        else{
                            stud[choice] = addStud(teach, stud);
                            System.out.println("Student updated!");
                        }
                        }while(choice >= sCtr);
                    }
                    pressAnyKeyToContinue();                
                    break;    
                case 3:
                    showStud(stud, 0);
                    if(sCtr != 0){
                        choice = 0;
                        do{
                            System.out.print("choose student: ");
                            choice = input.nextInt() - 1;
                            if(choice >= sCtr){
                                System.out.println("Chosen student is not yet enrolled. try again..");
                            }
                            else
                                System.out.println("remaining balance: " + stud[choice].balance);
                        }while(choice >= sCtr);
                    }
                    pressAnyKeyToContinue();
                    break;
                case 4:
                    showStud(stud, 0);
                    if(sCtr != 0){
                        choice = 0;
                        do{
                            System.out.print("choose student: ");
                            choice = input.nextInt() - 1;
                            if(choice >= sCtr){
                                System.out.println("Chosen student is not yet enrolled. try again..");
                            }
                            else
                            {
                                if(stud[choice].balance == 0)
                                    System.out.println("Student has no remaining balance");
                                else{
                                    System.out.print("Enter amount to deposit: ");
                                    amount = input.nextDouble();
                                    if(amount >= stud[choice].balance){
                                        System.out.println("Change: " + (amount - stud[choice].balance));
                                        stud[choice].balance = 0;
                                    }
                                    else
                                        stud[choice].balance -= amount;
                                    System.out.println("Remaining balance: " + stud[choice].balance);
                                }
                            }
                        }while(choice >= sCtr);
                        pressAnyKeyToContinue();
                    }
                    break; 
                case 5:
                    showStud(stud, 1);
                    pressAnyKeyToContinue();
                    break;
                case 6:
                    showStud(stud, 2);
                    pressAnyKeyToContinue();
                    break;
                case 7:
                    break;    
            }
        }while(choice != 7);
        
        
    }
    
    static Student addStud(Teacher[] teach, Student[] stud){
        Student s = new Student();
        int temp;
        do{
            System.out.print("Enter ID: ");
            temp = input.nextInt();
        }while(checkID(temp));
        s.ID = temp;
        input.nextLine();
        System.out.print("Enter First Name: ");
        s.fName = input.nextLine();
        System.out.print("Enter Last Name: ");
        s.lName = input.nextLine();
        System.out.print("Enter Gender: ");
        s.gnder = input.next();
        System.out.print("Enter Phone Number: ");
        s.pNumber = input.nextLong();
        input.nextLine();
        System.out.print("Enter Address: ");
        s.add = input.nextLine();
        System.out.print("Enter number of repeated modules to enroll: ");
        s.rMods = input.nextInt();

        if(s.rMods <= 2){
           System.out.println((6-s.rMods) + " new modules remaining");
           do{
               System.out.print("Enter number of new modules to enroll: ");
               s.mods = input.nextInt();
               if((s.rMods + s.mods) > 6)
               System.out.println("Exceeded the number of allowable new modules. try again..");
           }
           while((s.rMods + s.mods) > 6);
        }
        s.balance = (s.mods * 525) + (s.rMods * 110);
        System.out.println("Remaining balance: " + s.balance);
        System.out.print("Enter amount paid: ");
        s.paid = input.nextDouble();
        if(s.paid >= s.balance){
            System.out.println("Change: " + (s.paid - s.balance));
            s.balance = 0;
        }
        else{
            s.balance -= s.paid;
        }
        return s;
    }
    
    static void showStud(Student[] s, int x){
        int num = 1, ctr = 0;
        System.out.format("%-5s%-10s%-15s%-15s%-10s%-15s%-30s%n", "No.", "ID", "Last Name", "First Name",
                "Gender", "Phone Number", "Address");
        for(int i = 0; i < sCtr; i++){
            if(x == 0){
                System.out.format("%-5s%-10d%-15s%-15s%-10s%-15d%-30s%n", num + ".", s[i].ID, s[i].lName, 
                    s[i].fName, s[i].gnder, s[i].pNumber, s[i].add);
                    ctr = 1;
                    num++;
            }

            if(x == 1 &&s[i].balance == 0){
                System.out.format("%-5s%-10d%-15s%-15s%-10s%-15d%-30s%n", num + ".", s[i].ID, s[i].lName, 
                    s[i].fName, s[i].gnder, s[i].pNumber, s[i].add);
                    ctr = 1;
                    num++;
            }

            if(x == 2 && s[i].balance != 0){
                System.out.format("%-5s%-10d%-15s%-15s%-10s%-15d%-30s%n", num + ".", s[i].ID, s[i].lName, 
                    s[i].fName, s[i].gnder, s[i].pNumber, s[i].add);
                    ctr = 1;
                    num++;
            }

        }
        if(x == 0 && ctr == 0)
            System.out.format("%49s%n", "No student enrolled..");
        else if(x == 1 && ctr == 0)
            System.out.format("%53s%n", "No student with zero balance..");
        else if(x == 2 && ctr == 0)
            System.out.format("%56s%n", "No student with remaining balance..");

    }
              
    static void teachMenu(){
        int choice;

        do{
        System.out.println("[1] - Add new teacher");
        System.out.println("[2] - Update teacher");
        System.out.println("[3] - Calculate salary of a teacher");
        System.out.println("[4] - Show all teachers");
        System.out.println("[5] - Go back to main menu");
        System.out.print("Enter your choice: ");
        choice = input.nextInt();
                switch(choice){
            default:
                System.out.println("Invalid input. Try again..");
                pressAnyKeyToContinue();
                break;
            case 1:
                teach[tCtr] = new Teacher();
                teach[tCtr] = addTeach(teach, stud);
                if("HOF".equals(teach[tCtr].desig))
                    teach[tCtr].tHours = 8;
                else if("CO".equals(teach[tCtr].desig))
                    teach[tCtr].tHours = 13;
                else if("L".equals(teach[tCtr].desig))
                    teach[tCtr].tHours = 18;
                tCtr++;
                break;
            case 2:
                showTeach(teach);
                if(tCtr != 0){
                    choice = 0;
                    do{
                    System.out.print("choose teacher to update: ");
                    choice = input.nextInt() - 1;
                    if(choice >= tCtr){
                            System.out.println("Chosen teacher is not yet available. try again..");
                    }
                    else{
                        teach[choice] = addTeach(teach, stud);
                        System.out.println("Teacher updated!");
                    }
                    }while(choice >= tCtr);
                }
                pressAnyKeyToContinue();
                break;    
            case 3:
                showTeach(teach);
                if(tCtr != 0){
                    choice = 0;
                    do{
                        System.out.print("choose which teacher's salary to compute: ");
                        choice = input.nextInt() - 1;
                        if(choice >= tCtr){
                                System.out.println("Chosen teacher is not yet available. try again..");
                        }
                        else{
                            calcTeach(choice, teach);
                        }
                    }while(choice >= tCtr);
                    pressAnyKeyToContinue();
                }
                break;
            case 4:
                showTeach(teach);
                break; 
            case 5:
                break;
        }

        }while(choice != 5);
    }
    
    static private void pressAnyKeyToContinue(){ 
        System.out.print("Press Enter key to continue...");
        try
        {
            System.in.read();
        }  
        catch(Exception e)
        {}  
 }
    
    static Teacher addTeach(Teacher[] teach, Student[] stud){
        Teacher t = new Teacher();
        int temp;
        do{
            System.out.print("Enter ID: ");
            temp = input.nextInt();
        }while(checkID(temp));
        t.ID = temp;
        input.nextLine();
        System.out.print("Enter First Name: ");
        t.fName = input.nextLine();
        System.out.print("Enter Last Name: ");
        t.lName = input.nextLine();
        System.out.print("Enter Gender: ");
        t.gnder = input.next();
        System.out.print("Enter Phone Number: ");
        t.pNumber = input.nextLong();
        input.nextLine();
        System.out.print("Enter Address: ");
        t.add = input.nextLine();
        System.out.print("Enter Department: ");
        t.dept = input.next();
        System.out.print("Enter Designation: ");
        t.desig = input.next();
        return t;
    }
    
    static void calcTeach(int x, Teacher[] t){
        double salary, ha, ma, ta;
        
        System.out.print("Enter no. of teaching hours: ");
        int hrs = input.nextInt();
        salary = 1200 + ((hrs - t[x].tHours)*325);
        ha = salary * .10;
        ma = salary * .03;
        ta = salary * .05;
        salary = salary + ha + ma + ta;
        System.out.println("Net salary: " + salary);
        pressAnyKeyToContinue();
    }
    
    static void showTeach(Teacher[] t){
        int ctr = 0;
        System.out.format("%-5s%-10s%-15s%-15s%-10s%-15s%-30s%n", "No.", "ID", "Last Name", "First Name",
                "Gender", "Phone Number", "Address");
        for(int i = 0; i < tCtr; i++){
            System.out.format("%-5s%-10d%-15s%-15s%-10s%-15d%-30s%n", i+1 + ".", t[i].ID, t[i].lName, 
                    t[i].fName, t[i].gnder, t[i].pNumber, t[i].add);
            ctr = 1;
        }
        if(ctr == 0){
            System.out.format("%49s%n", "No teacher employed..");
        }
    }
}