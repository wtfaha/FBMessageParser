package ntou.FBParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App {
	public static void main(String[] args) {
		Output output = new Output();
		//String[] filenames = {"1.txt", "34.txt", "56.txt", "78.txt", "910.txt" , "1112.txt"};
		String[] filenames = {"1.txt"};
		for(int i = 0; i < 1; i++){	//記得要改成數量
		    System.out.println("Parse Group" + (i+1));
			output.insert(i, extractFB("E:/fbMessage/"+filenames[i]));
		}
	    System.out.println("Output file");
		output.wirteFile();
	}
	
	public static ArrayList<ArrayList<String>> extractFB(String filename) {
		ArrayList<ArrayList<String>> all = new ArrayList<ArrayList<String>>();
		try {
			//readFile
			String html = readFile(filename);
			
			//spilt message to one person
			String[] message = html.split("\\n");
			
			for(int i = 0; i < message.length; i++){
				//spilt message detail(name、time、content)
				String[] messageDetail = message[i].split("\\\\n");
				
				//get name
				String name = messageDetail[0];	
				
				String nameUrl = messageDetail[1];
				
				//get time
				String time = messageDetail[2];
				
				//get content
				String content = "";
				
				//System.out.println("messageDetail.length : " + messageDetail.length);

				for(int j = 3; j < messageDetail.length; j++){
					//System.out.println("messageDetail[j] : " + messageDetail[j]);
										
					Pattern pattern = Pattern.compile("<br\\>");
					Matcher matcher = pattern.matcher(messageDetail[j]);
					messageDetail[j] = matcher.replaceAll("\n");
					
					//System.out.println("replace messageDetail[j] : " + messageDetail[j]);
					
					content += messageDetail[j];
					if(j != messageDetail.length-1){	//最後一行不加換行
						content += "\n";
					}
				}
				//System.out.println("content : " + content);


				
				//put in temp arrayList
				ArrayList<String> temp = new ArrayList();
				temp.add(name);
				temp.add(time);
				temp.add(content);
				
				//put temp arrayList in all arrayList
				all.add(temp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return all;
	}
	
	public static String readFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}
}
