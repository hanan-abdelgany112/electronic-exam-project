package afx1pro;
import java.sql.*;

public class dataLink {
    Connection con;
      private dataLink(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/electronicexam", "root", "root");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
      }

    private static dataLink single_instance = null;


    public static dataLink getInstance()
    {
        if (single_instance == null)
            single_instance = new dataLink();

        return single_instance;
    }

    public  boolean Minsert(String statement,String ch1,String ch2,String ch3,String ch4,String rch,String exam_name)
    {
        try{
            String str1="insert into  mchoice (statement,ch1,ch2,ch3,ch4,rch,exam_name) values (?,?,?,?,?,?,?)";
            PreparedStatement stmt=con.prepareStatement(str1);
            stmt.setString(1,statement);
            stmt.setString(2,ch1);
            stmt.setString(3,ch2);
            stmt.setString(4,ch3);
            stmt.setString(5,ch4);
            stmt.setString(6,rch);
            stmt.setString(7,exam_name);
            stmt.executeUpdate();
            return true;
        }catch (Exception ex)
        {
            System.out.println("exception from minsert class \n"+ex);
        }
        return false;
    }

    public boolean tfinsert(String statement,String rch)
    {
        try{

            String str1="insert into  choicetf (statement,rch) values (?,?)";
            PreparedStatement stmt=con.prepareStatement(str1);
            stmt.setString(1,statement);
            stmt.setString(2,rch);
            stmt.executeUpdate();
            return true;
        }catch (Exception ex)
        {
            System.out.println("exception from tfinsert class \n"+ex);
        }
        return false;
    }

    public ResultSet MQuestions(String examName){
        try{
            char help= '"';
            Statement stmt=con.createStatement();
            ResultSet rs=
                    stmt.executeQuery
                            ("select statement,ch1,ch2,ch3,ch4,rch from mchoice where exam_name = "+help+examName+help);
            return rs;
        }catch (Exception ex)
        {
            System.out.println("exception from MQuestions class \n"+ex);
        }
        return null;
    }

    public ResultSet TFQuestions()
    {
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=
                    stmt.executeQuery
                            ("select statement,rch from choicetf");
            return rs;
        }catch (Exception ex)
        {
            System.out.println("exception from TFQuestion class \n"+ex);
        }
        return null;
    }
// not tested code*************************************************************************************************

    //password incription
    public static int incrept(String str)//tested
    {
        int x=32;
        int p=100000;
        int res=0;
        for(int i=0;i<str.length();i++)
        {
            int temp=(int)(str.charAt(i));
            res=((res*32)%p+temp)%p;
        }
        return res;
    }

    //add user or student

    public boolean addUser(int id,String name,String privillage,String password)
    {
        //we will use it later
        //int pass=incrept(password);
        try{
            String str1="insert into users (id,name,privillage,password) values (?,?,?,?)";
            PreparedStatement stmt=con.prepareStatement(str1);
            stmt.setInt(1, id);
            stmt.setString(2,name);
            stmt.setString(3,privillage);
            stmt.setInt(4, Integer.parseInt(password));
            stmt.executeUpdate();
            return true;
        }catch (Exception ex)
        {
            System.out.println("exception from add user class \n"+ex);
        }
        return false;
    }

    //add exam result for student
    public boolean addExamResult(int id,int marks,String exam_name)
    {
        try{
            String str1="insert into marks (id,mark,exam_name) values (?,?,?)";
            PreparedStatement stmt=con.prepareStatement(str1);
            stmt.setInt(1, id);
            stmt.setInt(2,marks);
            stmt.setString(3,exam_name);
            stmt.executeUpdate();
            return true;
        }catch (Exception ex)
        {
            System.out.println("exception from add mark class \n"+ex);
        }
        return false;
    }

    //specific exam student result
    public ResultSet getSpecificExamResult(String examName)
    {
        try{
            PreparedStatement stmt=con.prepareStatement("select id,mark from marks where exam_name=(?)");
            stmt.setString(1,examName);
            ResultSet rs= stmt.executeQuery();
            return rs;
        }catch (Exception ex)
        {
            System.out.println("exception from get specific exam result class \n"+ex);
        }
        return null;
    }

    //all exam results
    public ResultSet getAllExamResult(String examName)
    {
        try{
            PreparedStatement stmt=con.prepareStatement("select id,mark from marks");
            ResultSet rs= stmt.executeQuery();
            return rs;
        }catch (Exception ex)
        {
            System.out.println("exception from get specific exam result class \n"+ex);
        }
        return null;
    }

    //authorization

    public boolean checkPassword(String id,String password)//tested
    {

        try {
            int pass=Integer.parseInt(password);
            PreparedStatement stmt=con.prepareStatement("select password from users where id=(?)");
            stmt.setInt(1, Integer.parseInt(id));
            ResultSet rs= stmt.executeQuery();
            if(rs == null)return false;
            rs.next();
            int x = 0;
            try {
                x=rs.getInt("password");
            } catch (Exception ex) {
                System.out.println("error :"+ex.getMessage());
            }
            //System.out.println("here  " + x + "   "+pass);
            if(x==pass)
            {

                return true;
            }
            else
            {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("i catch you finally ):):");
            e.printStackTrace();
        }
        return false;
    }

    public String checkIfAdmin(int id)  //tested
    {

        try {
            PreparedStatement stmt=con.prepareStatement("select privillage from users where id=(?)");
            stmt.setInt(1, id);
            ResultSet rs= stmt.executeQuery();
            rs.next();
            String x=rs.getString("privillage");

            return x;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet examsNames() {
        try{
            PreparedStatement stmt=con.prepareStatement("select DISTINCT exam_name from mchoice");
            ResultSet rs= stmt.executeQuery();
            return rs;
        }catch (Exception ex)
        {
            System.out.println("exception from examsNames class :\n"+ex);
        }
        return null;
    }

    //public String whoIsThatUser(int id)

    public boolean isUserExist(int id) {
        try{
            PreparedStatement stmt=con.prepareStatement("select id from users where id="+id);
            ResultSet rs= stmt.executeQuery();
            if(rs.next()) {
                return true;
            } else {
                return false;
            }
        }catch (Exception ex)
        {
            System.out.println("exception from examsNames class :\n"+ex);
        }
        return false;
    }

    public void deleteUserFromDB(int id) {
        try{
            PreparedStatement stmt=con.prepareStatement("delete from users where id = "+id);
            stmt.executeUpdate();
        }catch (Exception ex)
        {
            System.out.println("exception from examsNames class :\n"+ex);
        }
    }

    public void deleteExamFromDataBase (String examName) {
        try{
            PreparedStatement stmt=con.prepareStatement("DELETE FROM mchoice" +
                    "WHERE   exam_name = '" +
                     examName+"'" +
                    "LIMIT  100000");
            stmt.executeUpdate();
        }catch (Exception ex)
        {
            System.out.println("exception from examsNames class :\n"+ex);
        }
    }

    public ResultSet getAllStudentId()
    {
        try{
            PreparedStatement stmt=con.prepareStatement("select id from users where privillage = 'student'");
            ResultSet rs= stmt.executeQuery();
            return rs;
        }catch (Exception ex)
        {
            System.out.println("exception from get student exams result class \n"+ex);
        }
        return null;
    }

    /////////////////////////////////////////////////////
    //////////////////////////////////////////////////
    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////
    //new fun need review
    public ResultSet getStudentExams(int student_id)
    {
        try{
            PreparedStatement stmt=con.prepareStatement("select exam_name from student_courses where student_id=(?)");
            stmt.setInt(1,student_id);
            ResultSet rs= stmt.executeQuery();
            return rs;
        }catch (Exception ex)
        {
            System.out.println("exception from get student exams result class \n"+ex);
        }
        return null;
    }

    public boolean deleteStudentExams(int student_id,String examName) {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM student_courses where student_id=(?) and exam_name=(?)");
            stmt.setInt(1, student_id);
            stmt.setString(2, examName);
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("exception from get student exams result class \n" + ex);
        }
        return false;
    }

    public void insertStudentExam(int student_id,String examName) {
        try {
            PreparedStatement stmt = con.prepareStatement("insert into student_courses(student_id,exam_name) values  (?,?)");
            stmt.setInt(1, student_id);
            stmt.setString(2, examName);
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("exception from get student exams result class \n" + ex);
        }
    }

}
