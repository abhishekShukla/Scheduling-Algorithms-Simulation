package edu.emsys.scheduling;
import java.util.ArrayList;
import java.io.*;

public class ReadInput {
	
	public static ArrayList<Tasks> ReadInputFile(String fileName){
		
		String eachLine = null;
		ArrayList<Tasks> taskList = new ArrayList<Tasks>();
		FileInputStream fstream;
		DataInputStream in;
		BufferedReader input = null;
		
		try {
			
			fstream = new FileInputStream(fileName);
			in = new DataInputStream(fstream);
			input = new BufferedReader(new InputStreamReader(in));
			
			while((eachLine = input.readLine()) != null){
				String[] tokens = eachLine.split(",");
				Tasks task = new Tasks();
				
				task.setTaskName(Integer.parseInt(tokens[0].trim()));
				task.setTaskPeriod(Integer.parseInt(tokens[1].trim()));
				task.setTaskWcet(Integer.parseInt(tokens[2].trim()));
				
				int deadLine = task.getArrivalTime() + task.getTaskPeriod();
				task.setDeadLine(deadLine);
				
				int slackTime = (task.getDeadLine() - task.getArrivalTime()) 
										- (task.getTaskWcet() - task.getExecutedTime()) ;
				task.setSlackTime(slackTime);
				
				taskList.add(task);
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException i){
			i.printStackTrace();
		}
		
		finally{
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return taskList;
	}
	
	public static void printTask(ArrayList<Tasks> taskList){
		
		for(Tasks T:taskList){
			System.out.println("Task Name\t" + "Period\t" + "WCET\t" 
								+ "Arrival Time\t" +  "Deadline\t" + "Slacktime\t" + "Deadline Misses\t" 
								+ "PreEmptions\t" + "TotalResponseTime\t" + "Number of Completions");
			
			System.out.println(T.getTaskName() + "\t\t" + T.getTaskPeriod() + "\t" + T.getTaskWcet() + 
					          "\t" + T.getArrivalTime() + "\t\t" + T.getDeadLine() + "\t\t" + T.getSlackTime()
					          + "\t\t" + T.getMissDeadine() + "\t\t" + T.getPreEmption() + "\t\t" + T.getTotalResponseTime()
					          + "\t\t\t" + T.getNumberOfCompletions());
		}
	}
	
	public static void calculateAverageResponseTime(ArrayList<Tasks> taskList){
		
		
		
		for(Tasks T:taskList){
			
			float avgRespTime = 0;
			
			if(T.getNumberOfCompletions() > 0){
				
				avgRespTime = (float)T.getTotalResponseTime()/T.getNumberOfCompletions();
				
				System.out.println("Average Response Time for Task " + T.getTaskName() 
						+ " = " + avgRespTime);
			}
			else{
				System.out.println("Average Response Time for Task " + T.getTaskName() 
						+ " = " + avgRespTime);
			}
		}
		
	}
	
	public static void main(String Args[]){
		
		ArrayList<Tasks> taskList = new ArrayList<Tasks>();
		taskList = ReadInput.ReadInputFile("input.txt");
		ReadInput.printTask(taskList);
		System.out.println("\n\n");
		RmsScheduler.RmsSchedule(taskList);
		
		
		taskList = new ArrayList<Tasks>();
		taskList = ReadInput.ReadInputFile("input.txt");
		System.out.println("\n\n");
		EdfScheduler.EdfSchedule(taskList);
		
		
		taskList = new ArrayList<Tasks>();
		taskList = ReadInput.ReadInputFile("input.txt");
		System.out.println("\n\n");
		LstScheduler.LstSchedule(taskList);
		
		
	}
	
}
