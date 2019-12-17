/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalgradebook;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Akatosh(Jacob Bitter)
 */
class Student {
    String name;
    ArrayList<Assignment> grades = new ArrayList<Assignment>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addAssignment(Assignment a, int earned){
        Assignment b = new Assignment();
        
        b.setName(a.getName());
        b.setTotal(a.getTotal());
        b.setEarned(earned);
        this.grades.add(b);
    }
    
    public double getGrades(){
        double earnedPoints = 0;
        double totalPoints = 0;
        
        for(int i = 0; i < grades.size(); i++){
            earnedPoints += grades.get(i).getEarned();
            totalPoints += grades.get(i).getTotal();
        }
        
        double grade = (earnedPoints/totalPoints)*100;
        return grade;
    }

    public void removeAssignment(String name) {
        for(int i = 0; i < grades.size(); i++){
            if(grades.get(i).getName().equals(name)){
                grades.remove(i);
                break;
            }
        }
    }

    public String getAssignment(int i) {
        Assignment temp = grades.get(i);
        
        double grade;
        grade = (double)(temp.getEarned()/temp.getTotal());
        String res = temp.getName()+":\t\t"+grade+"%";
        
        return res;
    }
    
    public int getCount(){
        return grades.size();
    }
    
    public int getAssignmentSize(){
        return grades.size();
    }
    
    public Assignment getAssignmentToSave(int i){
        return grades.get(i);
    }

    public void setGrades(ArrayList<Assignment> grades) {
        this.grades = grades;
    }
    
    
    
}
