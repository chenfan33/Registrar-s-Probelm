import java.util.*;
import java.io.*;

public class CheckStu{
    public static void checkStu(ArrayList<Course> courses, ArrayList<Student> students, int[][] combine){
	int classNum = courses.size()-1;

	//skip the first empty student, from index 1
	for(int i = 1; i < students.size();i++){
	    Student temp = students.get(i);
	    ArrayList<Integer> lists = temp.prefList;

	    int size0 = courses.get(lists.get(0)).stuSize();//the size of the first course
	    int size1 = courses.get(lists.get(1)).stuSize();
	    int size2 = courses.get(lists.get(2)).stuSize();//the size of the first course
	    int size3 = courses.get(lists.get(3)).stuSize();

	    if(combine[ReadPref.getIndex(classNum, lists.get(0), lists.get(1))][3] == 1){
		//the course we need to remove student
		Course remove = (size0 > size1)? courses.get(lists.get(0)) : courses.get(lists.get(1));
		//System.out.println(temp.id + " 1+2 " + remove.id);
		remove.removeStu(temp.id);
	    }

	    if(combine[ReadPref.getIndex(classNum, lists.get(0), lists.get(2))][3] == 1){
		//the course we need to remove student
		Course remove = (size0 > size2)? courses.get(lists.get(0)) : courses.get(lists.get(2));
		//System.out.println(temp.id + " 1+3 " + remove.id);

		remove.removeStu(temp.id);
	    }

	    if(combine[ReadPref.getIndex(classNum, lists.get(0), lists.get(3))][3] == 1){
		//the course we need to remove student
		Course remove = (size0 > size3)? courses.get(lists.get(0)) : courses.get(lists.get(3));
		//System.out.println(temp.id + " 1+4 " + remove.id);

		remove.removeStu(temp.id);
	    }

	    if(combine[ReadPref.getIndex(classNum, lists.get(1), lists.get(2))][3] == 1){
		//the course we need to remove student
		Course remove = (size1 > size2)? courses.get(lists.get(1)) : courses.get(lists.get(2));
		//System.out.println(temp.id + " 2+3 " + remove.id);

		remove.removeStu(temp.id);
	    }

	    if(combine[ReadPref.getIndex(classNum, lists.get(1), lists.get(3))][3] == 1){
		//the course we need to remove student
		Course remove = (size1 > size3)? courses.get(lists.get(1)) : courses.get(lists.get(3));
		//System.out.println(temp.id + " 2+4 " + remove.id);

		remove.removeStu(temp.id);
	    }

	    if(combine[ReadPref.getIndex(classNum, lists.get(2), lists.get(3))][3] == 1){
		//the course we need to remove student
		Course remove = (size2 > size3)? courses.get(lists.get(2)) : courses.get(lists.get(3));
				//System.out.println(temp.id + " 3+4 " + remove.id);

		remove.removeStu(temp.id);
	    }
	}


    }

    public static void checkCap(ArrayList<Course> courses, Room[] capacity){
	for(int i = 1; i < courses.size(); i++){
    if(courses.get(i).scheduled){
      int cap = capacity[courses.get(i).room -1].cap;
	    int current = courses.get(i).stuSize();
	    int numRemove = current - cap;
      //System.out.println(courses.get(i).id + ",  " + current + " " + capacity[courses.get(i).room -1]);
	    if(numRemove > 0){
        //System.out.println("Removing" + courses.get(i).id);
		for(int j = 0; j < numRemove;){
		    int index = courses.get(i).studentList.size()-1;
		    if(courses.get(i).studentList.get(index) != -1){
			courses.get(i).studentList.remove(index);
      //System.out.print(index + ", ");
			j++;
		    }
      //  System.out.println();
		    index--;
		}
	    }
    }


	}
    }

    //take a sorted capacity [ 10, 20, 50, 80]
    public static void assignRoom(int[][] schedule,ArrayList<Course> courses, Room[] capacitySort){
	for(int i = 1; i < schedule.length; i++){
	    for(int j = schedule[i][0]; j > 0; j--){
		      courses.get(schedule[i][j]).room = capacitySort[capacitySort.length -1 - (schedule[i][0] - j)].id;
      }
	}



    }
}
