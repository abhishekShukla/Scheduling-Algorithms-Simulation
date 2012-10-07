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
		
		String fileName = null;
		int hyperPeriod = 0;
		String scheduler = null;
		ArrayList<Tasks> taskList = new ArrayList<Tasks>();
		
		if(Args.length != 3){
			System.out.println("Usage: java ReadInput \"<input file>\" \"<scheduler>\" <simulation time>");
			System.out.println("input file: Absolute path  to the Input File");
			System.out.println("Scheduler name: RMS or EDF or LST or ALL");
			System.out.println("Simulation Time must be an Integer > 0");
			System.out.println("If Simulation Time = n, simulation will run for n time units");
			System.exit(1);
		}
		else{
			fileName = Args[0];
			
			if(Args[1].toLowerCase().equals("rms") || 
			   Args[1].toLowerCase().equals("edf") ||
			   Args[1].toLowerCase().equals("lst") ||
			   Args[1].toLowerCase().equals("all")){
				
				scheduler = Args[1];
				
			}
			else{
				System.out.println("Scheduler name must be: RMS or EDF or LST or ALL");
				System.exit(1);
			}
			
			try{
				hyperPeriod = Integer.parseInt(Args[2]);
				
				if(hyperPeriod <= 0){
					System.out.println("Simulation Time must be an Integer > 0");
					System.exit(1);
				}
			}
			catch(NumberFormatException N){
				System.out.println("TIME MUST BE AN INTEGER!");
				N.printStackTrace();
				System.exit(1);
			}
		}
		
		
		if(scheduler.toLowerCase().equals("rms")){
			
			taskList = ReadInput.ReadInputFile(fileName);
			ReadInput.printTask(taskList);
			System.out.println("\n\n");
			RmsScheduler.RmsSchedule(taskList, hyperPeriod);
			
		}
		else if(scheduler.toLowerCase().equals("edf")){
			
			taskList = new ArrayList<Tasks>();
			taskList = ReadInput.ReadInputFile(fileName);
			System.out.println("\n\n");
			EdfScheduler.EdfSchedule(taskList, hyperPeriod);
			
		}
		else if(scheduler.toLowerCase().equals("lst")){
			
			taskList = new ArrayList<Tasks>();
			taskList = ReadInput.ReadInputFile(fileName);
			System.out.println("\n\n");
			LstScheduler.LstSchedule(taskList, hyperPeriod);
			
		}
		else if(scheduler.toLowerCase().equals("all")){
			
			taskList = ReadInput.ReadInputFile(fileName);
			ReadInput.printTask(taskList);
			System.out.println("\n\n");
			RmsScheduler.RmsSchedule(taskList, hyperPeriod);
			
			
			taskList = new ArrayList<Tasks>();
			taskList = ReadInput.ReadInputFile(fileName);
			System.out.println("\n\n");
			EdfScheduler.EdfSchedule(taskList, hyperPeriod);
			
			
			taskList = new ArrayList<Tasks>();
			taskList = ReadInput.ReadInputFile(fileName);
			System.out.println("\n\n");
			LstScheduler.LstSchedule(taskList, hyperPeriod);
			
		}
		else{
			System.out.println("Scheduler name: RMS or EDF or LST or ALL");
			System.exit(1);
		}
		
	}
	
}
