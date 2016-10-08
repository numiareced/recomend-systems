package data_structure;

import java.util.HashMap;
import java.util.Random;

public class Rating {
	public int userId; // user id, starts from 0
	public int itemId; // item id, starts from 0
	public float score;
	public long timestamp;
	public static HashMap<String,Integer> userIds; 
	
	public Rating(int userId, int itemId, float score, long timestamp) {
		this.userId = userId;
		this.itemId = itemId;
		this.score = score;
		this.timestamp = timestamp;
	}
	
	public Rating(String line) {
		String[] arr = line.split("\t");
		//String[] arr = line.split(",");
		try{
		userId = Integer.parseInt(arr[0]);
		}
		catch (NumberFormatException e){
			if ( userIds.get(arr[0]) != null){
				userId = userIds.get(arr[0]);
			}
		}
		
		itemId = Integer.parseInt(arr[1]);
		score = Float.parseFloat(arr[2]);
		if (arr.length > 3)	timestamp = Long.parseLong(arr[3]);
	}
	
	public String toString() {
		return "<" + userId + "," + itemId + "," + score + "," + timestamp + ">";
	}
}