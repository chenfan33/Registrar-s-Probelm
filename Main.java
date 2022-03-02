import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws FileNotFoundException{
	System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
	
	int slot = ReadCon.roomCap(args[0]);
  	ArrayList<Course> course = new ArrayList<>();
	Course empty = new Course(-1,-1);
	course.add(empty);
	
	Room[] capacity = ReadCon.readCon(args[0], course);
	Room[] capacitySort = capacity.clone();
	Arrays.sort(capacitySort);
	
	/**
	  for(int i = 0; i < capacity.length; i ++)
	  System.out.print(capacity[i] + ", ");
	  System.out.println();
	*/
	int[][] schedule = new int[slot + 1][capacity.length + 1];
	
	int[][] combine = ReadPref.Combination(course.size()-1);
	
	ArrayList<Student> student = ReadPref.readPref(args[1], course.size()-1, combine, course);
	int[][] combineSort = copy(combine);
	ReadPref.sortbyColumn(combineSort, 2);
	
	PutClass.putRest(PutClass.putFirst(schedule, combineSort, course),schedule, combineSort, course, combine);
	
  
	/**
	for (int i = 1; i < schedule.length; i++){
	    Arrays.sort(schedule[i],1,schedule[i][0] + 1);
	    }*/
	CheckStu.assignRoom(schedule,course,capacitySort);
	//System.out.println(course);
	
	CheckStu.checkStu(course, student, combine, capacity);
	
	CheckStu.checkCap(course, capacity);
	
	
	
	System.out.println("Course" + "\t"+ "Room" + "\t"+"Teacher" + "\t" + "Time" + "\t"+"Students");
	for(int i = 1; i < course.size(); i ++){
	    if(course.get(i).room != -1){
		System.out.printf("%d\t%d\t%d\t%d\t", i, course.get(i).room, course.get(i).prof, course.get(i).time);
		for(int j = 0; j < course.get(i).studentList.size(); j ++){
		    System.out.print(course.get(i).studentList.get(j) + " ");
		}
		System.out.println();
	    }
	}
	
    }
    
    
    public static int[][] copy(int matrix[][]){
	int [][] myInt = new int[matrix.length][];
	for(int i = 0; i < matrix.length; i++){
	    int[] aMatrix = matrix[i];
	    int   aLength = aMatrix.length;
	    myInt[i] = new int[aLength];
	    System.arraycopy(aMatrix, 0, myInt[i], 0, aLength);
	}
	return myInt;
    }
}
