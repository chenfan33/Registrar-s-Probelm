

import java.util.*;
import java.io.*;

public class ReadPref{
    public static ArrayList<Student> readPref(String file, int classNum, int[][] combine, ArrayList<Course> courses)
    throws FileNotFoundException{
    	ArrayList<Student> students = new ArrayList<>();
      ArrayList<Integer> emptycourse = new ArrayList<>();
      Student empty = new Student(-1, emptycourse);
      students.add(empty);
    	//read file
    	File pref = new File(file);
    	Scanner in = new Scanner(pref);
    	in.nextLine(); //skip first line

    	while(in.hasNextLine()){
    	    String line = in.nextLine();
    	    students.add(ReadPref.parseLine(line, classNum, combine, courses));
    	}
    	in.close();
    	return students;
    }

    public static int getIndex(int classNum, int i, int j){
      return (2*classNum - i)*(i -1)/2 + j - i -1;
    }

    //read the id and four courses
    public static Student parseLine(String line, int classNum, int[][] combine, ArrayList<Course> addStu){
	   ArrayList<Integer> courses = new ArrayList<>();
	   String[] tokens = line.split("\\s+");
	   int stid = Integer.parseInt(tokens[0]);

	   int course1 = Integer.parseInt(tokens[1]);
	   int course2 = Integer.parseInt(tokens[2]);
	   int course3 = Integer.parseInt(tokens[3]);
	   int course4 = Integer.parseInt(tokens[4]);

	   addStu.get(course1).addStu(stid);
	   addStu.get(course2).addStu(stid);
	   addStu.get(course3).addStu(stid);
	   addStu.get(course4).addStu(stid);


	   courses.add(course1);
	   courses.add(course2);
	   courses.add(course3);
	   courses.add(course4);


  	Collections.sort(courses);
    //increment combination arrays
    combine[getIndex(classNum, courses.get(0), courses.get(1))][2] ++;
    combine[getIndex(classNum, courses.get(0), courses.get(2))][2] ++;
    combine[getIndex(classNum, courses.get(0), courses.get(3))][2] ++;
    combine[getIndex(classNum, courses.get(1), courses.get(2))][2] ++;
    combine[getIndex(classNum, courses.get(1), courses.get(3))][2] ++;
    combine[getIndex(classNum, courses.get(2), courses.get(3))][2] ++;

  	Student student = new Student(stid, courses);
  	return student;
  }

    public static int[][] Combination(int classNum){
      int[][] combine = new int[classNum*(classNum-1)/2][4];
      //combine[][], first column: i, second column: j, third column:
      int index = 0;
      for(int i = 1; i < classNum; i ++){
        for(int j = i + 1; j <= classNum; j ++){
          combine[index][0] = i;
          combine[index][1] = j;
          combine[index][2] = 0;
          combine[index][3] = 0;
          index ++;
        }

      }
      return combine;
    }

    //sort combination array by second column
    public static void sortbyColumn(int arr[][], int col){
    // Using built-in sort function Arrays.sort
    Arrays.sort(arr, new Comparator<int[]>() {
      @Override
      // Compare values according to columns
      public int compare(final int[] entry1,
                         final int[] entry2) {
        // To sort in descending order revert
        // the '>' Operator
        if (entry1[col] < entry2[col])
            return 1;
        else
            return -1;
      }
    });  // End of function call sort().
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
