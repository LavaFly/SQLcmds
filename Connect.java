import java.sql.*;
import java.util.*;
import java.io.File;
import java.util.ArrayList;
public class Connect {  
  public static void main(String[] args) {
    //https://dev.mysql.com/downloads/connector/j/
    //muss runtergeladen und eingerichtet sein
    ArrayList<String> all = new ArrayList<>();
    Statement stmt;
    Connection con;
    Scanner in;
    try {
      doStuffth(all);
      in = new Scanner(new File("Produktliste.txt"));
      doStuffrd(in,all);
      in = new Scanner(new File("src/NamenListe.txt"));
      doStuffnd(all);
      doStuffst(in,all);
      
      //doStuff(all);
      Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
      con = DriverManager.getConnection("jdbc:mysql://http://10.22.8.16/mysql?user=root&password=&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
      stmt = con.createStatement();
      for(int i = 0;i<all.size();i++) {
        stmt.executeUpdate(all.get(i));
      }
      System.out.println("really done");
    } catch(Exception e) {
      System.out.println(e);
      System.out.println("Idiot");
    }
  }
  public static String randStr(int a){
    int x;
    char y;
    String xx = "";
    for(int i=0;i<a;i++){
      x = (int)(Math.random()*26)+97;
      y = (char)x;
      xx += y;
    }
    return xx;
  }
  public static String type(){
    if (Math.random()<0.005) {
      return "Moderator";  
    } else{
      return "User";
    }
  }
  public static String name(){
    String[] name = {"Mueller","Janssen","Schroeder","Behrens","Schmidt","Becker","Kramer","Menke","Pfefferle","Riesterer","Drechsler"};
    return name[(int)Math.floor(Math.random()*name.length)];  
    
  }
  public static String email(){
    String[] emails = {"gmail.com","gmx.de","t-online.de","web.de","yahoo.com"};
    return "@" + emails[(int)Math.floor(Math.random()*emails.length)];      
  }
  public static void doStuffst(Scanner x,ArrayList<String> all){
    int i = 0;
    String c = "USERS";
    all.add("INSERT INTO "+c+" VALUES(0,'Luca Kannenberg','lucakannenberg@gmail.com','1234','WebWizard');");
    all.add("INSERT INTO "+c+" VALUES(1,'Robert Hufflepuff','kpWieDeineEmailIs','1234','WebMaster');");
    String a;
    String b;
    while (x.hasNext()) {
      a = x.next();
      b = name();
      all.add("INSERT INTO "+c+" VALUES(" + (i + 2) + ",'" + a + " " + b + "','" + a + "."+ b + email() + "','" + randStr(5) + "','" + type() + "');");
      i++;
    }
    //     all.add("Constraint c1 Primary Key(UserID);");
    System.out.println("done");
    
  }
  public static void doStuffnd(ArrayList<String> a){
    a.add("CREATE TABLE USERS(UserID INT,UserName VARCHAR(30),UserEmail VARCHAR(35),UserCode VARCHAR(20),UserType VARCHAR(10));");
  }
  public static int randValue(int a){
    int[] x = {10,40,100};
    return (int)Math.floor(Math.random()*x[a-1]);
  }
  public static void doStuffrd(Scanner x,ArrayList<String> all){
    all.add("CREATE TABLE Products(PrID INT,PrName VARCHAR(30),PrValue INT,PrAmount INT);");
    int i = 0;
    String[] a;
    while (x.hasNext()) {
      a = x.nextLine().split("-");
      all.add("INSERT INTO Products VALUES("+i+",'"+a[0].substring(0,a[0].length()-1)+"','"+randValue(Integer.parseInt(a[1].replace(" ","")))+"',"+(int)Math.floor(Math.random()*100)+");");
      i++;
    }
    //all.add("Constraint c1 Primary Key(PrID);");
  }
  public static void doStuffth(ArrayList<String> a){
    String b = "webpagedata";
    a.add("Drop DATABASE IF EXISTS "+b+";");
    a.add("Create DATABASE "+b+";");
    a.add("Use "+b+";");
  }
  public static void doStuff(ArrayList<String> all) {
    all.add("CREATE TABLE webpagedata.products as SELECT * FROM test.products;");
    all.add("Create Table webpagedata.users as select * from test.users;");
    all.add("drop database test;");
    all.add("create database test");
    
  }
}