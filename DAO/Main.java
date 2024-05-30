/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Expert;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Main {
    public static void main(String[] args) {
        // Database credentials
     dao dao = new dao();
     //List<Users> u = new ArrayList<>();
     //u = dao.login();
     //for (Users user : u) {
        // System.out.println(user);
     //}
     List<Expert> e  = dao.getExpert();

     for (Expert expert: e) {
         System.out.println(expert);
     }
     Expert t = dao.getExpertById(1);
      System.out.println(t);
      int failedAttemp = dao.getFailedAttempt("thanh");
        System.err.println(failedAttemp);
}
}
