import java.util.*;
import java.io.*;

public class Course{
  public int id;
  public int prof;
  public int time;
  public int room;
  public boolean scheduled;
  public ArrayList<Integer> studentList = new ArrayList<Integer>();

  public Course(int id, int prof){
      this.id = id;
      this.prof = prof;
      this.time = -1;
      this.room = -1;
      this.scheduled = false;
  }

    public int stuSize(){
	return studentList.size();
    }

    public void addStu(int id){
	studentList.add(id);
    }

    public void removeStu(int id){
	int index = studentList.indexOf(id);
	if(index >= 0){
	    studentList.remove(index);
	}
    }

  public String toString(){
    return "id: " + id + ", prof:" + prof + ", time:" + time + ", room: " + room
            + ", studentList: " + studentList + '\n';
  }
}
