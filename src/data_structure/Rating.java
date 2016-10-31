package data_structure;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

public class Rating {
	public int userId; // user id, starts from 0
	public int itemId; // item id, starts from 0
	public float score;
	public long timestamp;
	public static LinkedHashMap<String,Integer> userIds; 
	public static LinkedHashMap<String,Integer> ItemIds; 
	public static int itemCount = 0 ; 
	
	public Rating(int userId, int itemId, float score, long timestamp) {
		this.userId = userId;
		this.itemId = itemId;
		this.score = score;
		this.timestamp = timestamp;
	}
	
	public Rating(String line) {
		String[] arr = line.split(",");
		//String[] arr = line.split(",");
		//System.out.println("type is:" + 		arr[0].getClass().getName());
		if (arr[0] instanceof String){
			if ( userIds.get(arr[0]) != null){
				userId = userIds.get(arr[0]);
				//System.out.println("userid is:" + userId);
			}else {
				System.out.println("Smth went wrong?? No user of this name!!");
			}
		}else {
			try{
				userId = Integer.parseInt(arr[0]);
				}
				catch (NumberFormatException e){
					System.out.println(e);
				}
		}
		try {
			if ( ItemIds.get(arr[1]) != null){
				itemId =ItemIds.get(arr[1]);
			}
		score = Float.parseFloat(arr[2]);
		if (arr.length > 3)	timestamp = Long.parseLong(arr[3]);
		}
		catch(NumberFormatException e) {
			
			System.out.println(e);
			System.out.println(line);
		}
	}
	
	public String toString() {
		return "<" + userId + "," + itemId + "," + score + "," + timestamp + ">";
	}
}