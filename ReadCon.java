import java.util.*;
import java.io.*;

public class ReadCon{
    public static int roomCap(String file) throws FileNotFoundException{
	File pref = new File(file);
	Scanner in = new Scanner(pref);

	String time = in.nextLine();
	String[] timetokens = time.split("\\s+");
	int times = Integer.parseInt(timetokens[2]);
	return times;
    }


    public static Room[] readCon(String file, ArrayList<Course> course) throws FileNotFoundException{
	ArrayList<Course> courses = new ArrayList<>();
	//read file
	File pref = new File(file);
	Scanner in = new Scanner(pref);

	String time = in.nextLine();
	String[] timetokens = time.split("\\s+");
	int times = Integer.parseInt(timetokens[2]);

	String room = in.nextLine();
	String[] roomtokens = room.split("\\s+");
	int rooms = Integer.parseInt(roomtokens[1]);

	int[][] slots = new int[times][rooms];

	Room[] size = new Room[rooms];
	for(int i = 0; i < rooms; i++){
	    String line = in.nextLine();
	    String[] tokens = line.split("\\s+");
      int id = Integer.parseInt(tokens[0]);
	    int cap = Integer.parseInt(tokens[1]);
      Room token = new Room(id,cap);

      size[i] = token;
	}

	in.nextLine();
	in.nextLine();
	while(in.hasNextLine()){
	    String line = in.nextLine();
	    course.add(ReadCon.parseLine(line));
	}
	in.close();
	return size;
    }



    //store professor into course
    public static Course parseLine(String line){
	String[] tokens = line.split("\\s+");
	int courseid = Integer.parseInt(tokens[0]);
	int prof = Integer.parseInt(tokens[1]);

	Course course = new Course(courseid, prof);
	return course;
    }
}
