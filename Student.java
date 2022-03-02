import java.util.*;
import java.io.*;

public class Student{
  public int id;
  public ArrayList<Integer> prefList = new ArrayList<Integer>();

  public Student(int id, ArrayList<Integer> prefList){
      this.id = id;
      this.prefList = prefList;
  }

  public String toString(){
    return "id: " + id + ", prefList: " + prefList + "\n";
  }
}
