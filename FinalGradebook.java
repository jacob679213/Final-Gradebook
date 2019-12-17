/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalgradebook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Akatosh(Jacob Bitter)
 */
public class FinalGradebook {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Setup
        Scanner kb = new Scanner(System.in);
        boolean running = true;
        
        ArrayList<Student> students = null;
        
        String password = null;
        int studentCount;
        
        //Bedore the program runs, gives a title.
        System.out.println("\t~~| GRADEBOOK |~~\n\tBy: Jacob Bitter\n\n");
        
        //Runs the Main Program. While this is going, the program is running.
        while(running){
            if(students == null){
                System.out.println("Welcome new teacher! Before we begin. please set up your password.");
                
                //set up password for the teacher
                boolean passwordSetup = true;
                while(passwordSetup){
                    //get password from teacher
                    System.out.println("What would you like your password to be?");
                    password = kb.nextLine();
                    if(password.equals("quit")){
                        System.out.println("Sorry, but that cannot be you're password. Please choose a new password");
                        continue;
                    }
                    System.out.println("Your new password is "+password+"?");
                    if(kb.nextLine().contains("yes")){
                        passwordSetup = false;
                    }
                }
                //check for files in the save folder
                File temp = new File("H:/Files-to-read/Gradebook-Students");
                //make sure the selected file is a folder
                if(temp.exists()){
                    //test if the foler is empty
                    if(temp.list().length > 0){
                        //offer to recall
                        System.out.println("We see you have past data. Would you like to recall it?");
                        //teacher confirms
                        boolean aaa = true;
                        while(aaa){
                            if(kb.nextLine().equals("yes")){
                                students = new ArrayList<Student>();
                                recall(temp, students);
                                aaa = false;
                            }
                            else{
                                System.out.println("Are you sure?");
                                //Teacher doesn't want to recall
                                if(kb.nextLine().equals("yes")){
                                    //empty folder
                                    for(File file: temp.listFiles()){
                                        if(!file.isDirectory()){
                                            file.delete();
                                        }
                                    }
                                    //set up list of students
                                    System.out.println("Ok. We will set up your list of students. Please note that you can Add or Remove students later. How many students do you have?");
                                    studentCount = kb.nextInt();

                                    //Name the students. After this, setup is done.
                                    students = nameStudents(studentCount);
                                    aaa = false;
                                }
                            }
                        }
                    }
                    else{
                        //set up list of students
                        System.out.println("Now we will set up your list of students. Please note that you can Add or Remove students later. How many students do you have?");
                        studentCount = kb.nextInt();

                        //Name the students. After this, setup is done.
                        students = nameStudents(studentCount);
                    }
                }
                else{
                    temp.mkdir();

                    //set up list of students
                    System.out.println("Now we will set up your list of students. Please note that you can Add or Remove students later. How many students do you have?");
                    studentCount = kb.nextInt();

                    //Name the students. After this, setup is done.
                    students = nameStudents(studentCount);
                }
            }
            System.out.println("Welcome to Gradebook! Are you a teacher or a student?");
            
            boolean loggedIn = false;
            
            //log in as teacher
            if(kb.nextLine().contains("teacher")){
                boolean locked = true;
                //request password
                while(locked){
                    System.out.println("Please input your password. Otherwise, input \"quit\"");
                    
                    //password is inputed right
                    if(kb.nextLine().equals(password)){
                        System.out.println("Password Accepted");
                        System.out.println("For a list of commands, please input \"help\"");
                        loggedIn = true;
                        locked = false;
                    }
                    //User requests to quit
                    else if(kb.nextLine().equals("quit")){
                        System.out.println("Quiting Log in");
                        locked = false;
                    }
                    //Passsword is inputed wrong
                    else{
                        System.out.println("Wrong Password. If you would like to quit, please input \"quit\"");
                    }
                }
                
                //runs if the teacher is logged in
                while(loggedIn){
                    //Teacher controls will go here!!!
                    //prompt teacher to give command
                    System.out.println("Command:");
                    String command = kb.nextLine();
                    
                    //Process the commands
                    
                    //ask for help
                    if(command.equals("help")){
                        //Tell the teacher commands :P
                        System.out.println("Commands you can use are...\n");
                        //list grades-              
                        System.out.println("list grades \t\tLists all students grades");
                        //add student-              
                        System.out.println("add student \t\tAdds a student");
                        //remove student            
                        System.out.println("remove student \t\tRemoves a student");
                        //add assignment            
                        System.out.println("add assignment \t\tCreatee a new assignment");
                        //remove assignment         
                        System.out.println("remove assignment \tRemove an assignment from all students");
                        //see individual grade      
                        System.out.println("see grade \t\tSee an individual student's grade");
                        //log out                   
                        System.out.println("log out \t\tLog out of the program, allowing students to log in");
                        //quit                      
                        System.out.println("quit \t\t\tQuit the progrram, closing it in the process");
                        //save                      
                        System.out.println("save \t\t\tSave the data for later");
                        //uninstall                 
                        System.out.println("uninstall \t\tUninstall saved data");
                    }
                    
                    //list students and grades
                    else if(command.equals("list grades")){
                        System.out.println("You have "+students.size()+" students, and their names and grades are:");
                        for(int i = 0; i < students.size(); i++){
                            System.out.println(students.get(i).getName()+"\t"+students.get(i).getGrades());
                        }
                    }
                    
                    //add a student
                    else if(command.equals("add student")){
                        //confirm the teacher wants to add a student
                        System.out.println("Just to confirm, you want to add a student?");
                        //teacher says yes
                        if(kb.nextLine().equals("yes")){
                            boolean naming = true;
                            //loop to catch mistakes
                            while(naming){
                                //ask for name
                                System.out.println("What is the name of this new student?");
                                String name = kb.nextLine();
                                //confirm name
                                System.out.println("The new student is named "+name+"?");
                                if(kb.nextLine().equals("yes")){
                                    //finnaly add the new student
                                    Student temp = new Student();
                                    temp.setName(name);
                                    students.add(temp);
                                    //exit the loop
                                    naming = false;
                                }
                            }
                        }
                        //teacher says no
                        else{
                            System.out.println("Okay. We won't add any students to the list.");
                        }
                    }
                    
                    //remove student
                    else if(command.equals("remove student")){
                        //confirm the teacher wants to remove a student
                        System.out.println("Just to confirm, you want to remove a student?");
                        //teacher says yes
                        if(kb.nextLine().equals("yes")){
                            boolean naming = true;
                            //loop to catch mistakes
                            while(naming){
                                //ask for name
                                System.out.println("What is the name of the student you would like to remove?");
                                String name = kb.nextLine();
                                //confirm name
                                System.out.println("You want to remove "+name+"?");
                                if(kb.nextLine().equals("yes")){
                                    //finnaly remove the student
                                    for(int i = 0; i < students.size(); i++){
                                        if(name == students.get(i).getName()){
                                            students.remove(i);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        //teacher says no
                        else{
                            System.out.println("Okay. We won't add any students to the list.");
                        }
                    }
                    
                    //add assignment
                    if(command.equals("add assignment")){
                        //confirm command
                        System.out.println("Just to confirm, you want to add a new assignment?");
                        //teacher says yes
                        if(kb.nextLine().equals("yes")){
                            
                            //get name
                            System.out.println("What is the name of this assignment?");
                            String name = kb.nextLine();
                            //get total points
                            System.out.println("What was the total point count of the assignment?");
                            int total = kb.nextInt();
                            
                            //confirm assignment
                            System.out.println("The assignment " + name + " is worth " + total + " points?");
                            kb.nextLine();
                            //teacher says yes
                            if(kb.nextLine().equals("yes")){
                                //create assignment
                                Assignment temp = new Assignment();
                                //set assignments main data points
                                temp.setName(name);
                                temp.setTotal(total);
                            
                                //add assignment to student lists
                                for(int i = 0; i < students.size(); i++){
                                    //get earned points
                                    System.out.println("What did "+students.get(i).getName()+" get on the assignment?");
                                    int earned = kb.nextInt();
                                    //add assignment
                                    students.get(i).addAssignment(temp, earned);
                                }
                            
                            }
                            //teacher doesn't say yes
                            else{
                                System.out.println("Okay. No new assignments were added");
                            }
                        }
                        //teacher doesn't say yes
                        else{
                            System.out.println("Okay. No new assignemnts were added.");
                        }
                    }
                    
                    //remove assignment
                    if(command.equals("remove assignment")){
                        //confirm command
                        System.out.println("Just to confirm, you want to remove an assignment?");
                        //teacher says yes+
                        if(kb.nextLine().equals("yes")){
                            //get asignmnet name
                            System.out.println("Ok. What is the name of the assignment?");
                            String name = kb.nextLine();
                            //confirm one more time
                            System.out.println("Ok. One last time, you would like to remove the assignment named "+name+"?");
                            
                            //teacher says yes
                            if(kb.nextLine().equals("yes")){
                                //remove from students
                                for(int i = 0; i < students.size(); i++){
                                    students.get(i).removeAssignment(name);
                                }
                            }
                            //teacher doesn't say yes
                            else{
                                System.out.println("Okay. No assignments were removed.");
                            }
                        }
                        
                        //teacher doesn't say yes
                        else{
                            System.out.println("Okay. No assignments were removed.");
                        }
                    }
                    
                    //see individual student grades
                    if(command.equals("see grade")){
                        //get name
                        System.out.println("Okay. Whose grades would you like to see?");
                        String name = kb.nextLine();
                        Student temp = null;
                        
                        //find and get student
                        for(int i = 0; i < students.size(); i++){
                            if(students.get(i).getName().equals(name)){
                                temp = students.get(i);
                                break;
                            }
                        }
                        
                        //print info
                        System.out.println(temp.getName()+" has a "+temp.getGrades()+"% in the class. Their grades are as follows:");
                        for(int i = 0; i < temp.getAssignmentSize(); i++){
                            System.out.println(temp.getAssignment(i));
                        }
                    }
                    
                    //log out
                    if(command.equals("log out")){
                        //confirm
                        System.out.println("Just to confirm, you want to log out?");
                        //teacher says yes
                        if(kb.nextLine().equals("yes")){
                            //log out
                            System.out.println("Logging out");
                            locked = true;
                            loggedIn = false;
                        }
                        //teacher says no
                        else{
                            System.out.println("Okay. We won't log you out.");
                        }
                    }
                    
                    //quit
                    if(command.equals("quit")){
                        //warn and confirm
                        System.out.println("WARNING: THIS WILL CANCEL THE PROGRAM. If you do not want to cancel the program, you can use the command \"log out\"");
                        System.out.println("Are you sure you want to quit?");
                        //teacher says yes
                        if(kb.nextLine().equals("yes")){
                            //quit
                            System.out.println("Quitting...");
                            running = false;
                            loggedIn = false;
                        }
                        //teacher says no
                        else{
                            System.out.println("Okay. The program will not quit.");
                        }
                        
                    }
                    
                    //save
                    if(command.equals("save")){
                        save(students);
                    }
                    
                    //clear folder
                    if(command.equals("uninstall")){
                        //confirm
                        System.out.println("You would like to delete the folder created while saving data?");
                        //teacher says yes
                        if(kb.nextLine().equals("yes")){
                            //locate directory
                            File folder = new File("H:/Files-to-read/Gradebook-Students");
                            
                            //confirm directory is a directory
                            if(folder.isDirectory()){
                                //clear directory
                                for(File file : folder.listFiles()){
                                    file.delete();
                                }
                                //delete the cleared directoy
                                if(folder.listFiles().length == 0){
                                    folder.delete();
                                }
                            }
                            
                            System.out.println("Text Files Deleted.");
                        }
                    }
                    //next command
                }
            }
            
            //log in as student
            if(kb.nextLine().equals("student")){
                //get name
                System.out.println("What is your name?");
                String name = kb.nextLine();
                
                //take name and look for student with matching name
                for(int i = 0; i <= students.size(); i++){
                    if(i == students.size()){
                        System.out.println("Sorry, but we couldn't seem to find you. Make sure you check the name for typos.");
                    }
                    else if(students.get(i).getName().equals(name)){
                        System.out.println("You have a "+students.get(i).getGrades()+"%, and your grdes are as follows:");
                        for(int j = 0; j < students.get(i).getAssignmentSize(); j++){
                            System.out.println(students.get(i).getAssignment(j));
                        }
                    }
                }
            }
        }
    }
    
    
    //names the students
    public static ArrayList<Student> nameStudents(int studentCount){
        Scanner kb = new Scanner(System.in);
        
        ArrayList<Student> students = new ArrayList<Student>();
        for(int i = 0; i < studentCount; i++){
            boolean naming = true;
            while(naming){
                
                //get name from teacher
                System.out.println("What is the name of Student number "+(i+1)+"?");
                String name = kb.nextLine();
                //confirm name
                System.out.println("Student number "+(i+1)+" is named "+name+"?");
                if(kb.nextLine().contains("yes")){
                    Student temp = new Student();
                    temp.setName(name);
                    students.add(temp);
                    naming = false;
                }
            }
        }
        
        return students;
        
    }
    
    //method to save data to text files
    public static void save(ArrayList<Student> s){
        for(int i = 0; i < s.size(); i++){
            //looks for file. If there isn't one, it should create it.
            File file = new File("H:/Files-to-read/Gradebook-Students/"+s.get(i).getName().replace(" ", "-")+".txt");
           
            
            //try and save
            try{
                //if a new file is created
                if(!file.exists()){
                    //creates file
                    file.createNewFile();
                }
                //create a writer
                    FileWriter writer = new FileWriter(file);
                    //write the students name
                    writer.write(s.get(i).getName());
                    //new line
                    writer.write(System.lineSeparator());
                    //write tons of grades
                    for(int e = 0; e < s.get(i).getCount(); e++){
                        //write name
                        writer.write(s.get(i).getAssignmentToSave(e).getName());
                        writer.write(System.lineSeparator());
                        //write earned, then total
                        writer.write(s.get(i).getAssignmentToSave(e).getEarned()+"/"+s.get(i).getAssignmentToSave(e).getTotal());
                        writer.write(System.lineSeparator());
                    }
                    //stop
                    writer.close();
                    System.out.println("Saved");
            }
            catch(IOException error){
                System.out.println("There was an error saving the data. Sorry for the inconvinience");
            }
        }
    }

    private static void recall(File folder, ArrayList<Student> students) {
        //get all files
        File[] studentFiles = folder.listFiles();

        //read student files
        for(int j = 0; j < studentFiles.length; j++){
            File file = studentFiles[j];
            try{
                //make a new scanner for the file
                Scanner input = new Scanner(file);

                //make the arraylist of assignments for later
                ArrayList<Assignment> assignments = new ArrayList<Assignment>();
                //get the student's name, which will be on the first line
                String name = input.nextLine();

                //read the rest of the file
                boolean aaa = true;
                while(aaa){
                    //check to ensure that the file has another line
                    if(input.hasNext()){
                        //create assignment
                        Assignment temp = new Assignment();
                        //set name
                        temp.setName(input.nextLine());
                        //split earned and total
                        String[] points = input.nextLine().split("/");
                        //turn earned and total into ints
                        int earned = Integer.parseInt(points[0]);
                        int total = Integer.parseInt(points[1]);
                        //set earned and total
                        temp.setEarned(earned);
                        temp.setTotal(total);

                        //add assignment to arrayList
                        assignments.add(temp);
                    }
                    //there is nothing else in the file
                    else{
                        aaa = false;
                    }
                }
                //create student
                Student student = new Student();
                student.setName(name);
                student.setGrades(assignments);
                students.add(student);
                
            }
            catch(IOException error){
                System.out.println("There was an error recalling the data.");
            }
        }
    }
}