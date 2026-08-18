import java.util*;
class Student{


  public static void main(String){
   Scanner sc=new Scanner(System.in);
   void  Studetail(String name,String roll_no){
                   
       System.out.println("Name: "+name);
       System.out.println("CMS: "+roll_no);
    }
    System.out.println("Enter name:");
   String name=sc.nextLine();
  System.out.println("Enter CMS:");
   String CMS=sc.nextLine();
  Studetail(name,CMS);

  
  }
}