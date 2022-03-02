import java.util.*;
import java.io.*;

public class Room implements Comparable<Room>{
    public int id;
    public int cap;

    public Room(int id, int cap){
	this.id = id;
	this.cap = cap;
    }

    public int compareTo(Room other) {
	return (this.cap < other.cap) ? -1:(this.cap > other.cap) ? 1:0 ;

    }


    public String toString(){
	return ""+cap;
    }
}
