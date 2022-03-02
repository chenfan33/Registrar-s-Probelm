import java.util.*;
import java.io.*;

public class PutClass{
  public static int putFirst(int[][] schedule, int[][] combineSort, ArrayList<Course> course){
    int iCombinition = 0;
    boolean putF;
    boolean putS;
    for(int iSlot = 1; iSlot < schedule.length; iSlot ++){
      putF = false; putS = false;
      //put the first class in the combinition
      if(course.get(combineSort[iCombinition][0]).scheduled == false){
        schedule[iSlot][1] = combineSort[iCombinition][0];
        schedule[iSlot][0] ++;
        course.get(combineSort[iCombinition][0]).scheduled = true;
        course.get(combineSort[iCombinition][0]).time = iSlot;
        iSlot ++;
        putF = true;
      }

      //out of slot check
      if(iSlot == schedule.length){
        break;
      }

      //put the second class in the combinition
      if(course.get(combineSort[iCombinition][1]).scheduled == false){
        schedule[iSlot][1] = combineSort[iCombinition][1];
        schedule[iSlot][0] ++;
        course.get(combineSort[iCombinition][1]).scheduled = true;
        course.get(combineSort[iCombinition][1]).time = iSlot;
        putS = true;
      }

      if(putS == false)
        iSlot--;
      iCombinition++;
    }
    return iCombinition;
  }


  public static void putRest(int end, int[][] schedule, int[][] combineSort, ArrayList<Course> course, int[][] combine){
    int assigned = schedule.length - 1;
    int iCombinition = combineSort.length - 1;

    while(iCombinition != end && assigned <= course.size() && assigned <= (schedule.length - 1)*(schedule[0].length - 1)){
      //if [0] is assigned and [1] is not, then schedule [1] to [0]'s slot
      if(course.get(combineSort[iCombinition][0]).scheduled &&
        !course.get(combineSort[iCombinition][1]).scheduled){

        int slot = course.get(combineSort[iCombinition][0]).time;

        //check prof for every class in that slot
        boolean profcheck = true;
        for(int i = 1; i <= schedule[slot][0]; i ++){
          if(course.get(schedule[slot][i]).prof == course.get(combineSort[iCombinition][1]).prof){
            profcheck = false;
            break;
          }
        }

        //slot is not full and professor is not the same
        if(schedule[slot][0] < schedule[0].length - 1 && profcheck){
          assigned++;
          course.get(combineSort[iCombinition][1]).time = slot;
          course.get(combineSort[iCombinition][1]).scheduled = true;
          schedule[slot][++schedule[slot][0]] = combineSort[iCombinition][1];

          //change all combine[][3]
          for(int i = 1; i < schedule[slot][0]; i ++){
            int a = schedule[slot][i];
            int b = combineSort[iCombinition][1];
            int max = (a > b)? a : b;
            int min = (a <= b)? a : b;
            combine[ReadPref.getIndex(course.size() - 1, min, max)][3] = 1;
          }
        }
      }

      //if [1] is assigned and [0] is not, then schedule [0] to [1]'s slot
      if(course.get(combineSort[iCombinition][1]).scheduled &&
        !course.get(combineSort[iCombinition][0]).scheduled){

        int slot = course.get(combineSort[iCombinition][1]).time;

        //check prof for every class in that slot
        boolean profcheck = true;
        for(int i = 1; i <= schedule[slot][0]; i ++){
          if(course.get(schedule[slot][i]).prof == course.get(combineSort[iCombinition][0]).prof){
            profcheck = false;
            break;
          }
        }

        //slot is not full
        if(schedule[slot][0] < schedule[0].length - 1 && profcheck){
          assigned++;
          course.get(combineSort[iCombinition][0]).time = slot;
          course.get(combineSort[iCombinition][0]).scheduled = true;
          schedule[slot][++schedule[slot][0]] = combineSort[iCombinition][0];
          //change all combine[][3]
          for(int i = 1; i < schedule[slot][0]; i ++){
            int a = schedule[slot][i];
            int b = combineSort[iCombinition][0];
            int max = (a > b)? a : b;
            int min = (a <= b)? a : b;
            combine[ReadPref.getIndex(course.size() - 1, min, max)][3] = 1;
          }
        }
      }
      iCombinition--;
    }
  }

}
