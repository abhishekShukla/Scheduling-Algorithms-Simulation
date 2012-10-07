package edu.emsys.scheduling;

import java.util.ArrayList;
import java.util.Collections;

public class LstScheduler {

	public static void LstSchedule(ArrayList<Tasks> taskList, int hyperPeriod){
		
		int time = 0;
		int previousTask = 0;
		
		System.out.println("LST SCHEDULE\n");
		System.out.println("Arranging by Deadlines\n");
		Collections.sort(taskList, new Tasks.SlackTime());
		ReadInput.printTask(taskList);
		
while(time < hyperPeriod){
			
			int currentTask = -1;
			
			for(int i = 0; i < taskList.size(); i++){
				
				if(taskList.get(i).getArrivalTime() <= time){
					
					//Execute First Task in the list
					System.out.println("Time = " + time + "\n" + "\tExecuting Task = " + taskList.get(i).getTaskName());
					currentTask = i;
					break;
					
				}
				
			}
			
			
			
			if(previousTask > -1){
				if(currentTask != previousTask 
						   && taskList.get(previousTask).getExecutedTime() > 0){
					
					int preEmption = taskList.get(previousTask).getPreEmption() + 1;
					taskList.get(previousTask).setPreEmption(preEmption);
					//Update Pre-Emp Count
					System.out.println("\tPre-empting Task = " + taskList.get(previousTask).getTaskName());
					
				}
			}
			
			
			if(currentTask > -1){
				
				taskList.get(currentTask).setExecutedTime(taskList.get(currentTask).getExecutedTime() + 1);
				
				if(taskList.get(currentTask).getExecutedTime() == taskList.get(currentTask).getTaskWcet()){
					
					System.out.println("\tCompleted Task = " + taskList.get(currentTask).getTaskName());
					//Updating Total Response time
					int totalResponseTime = taskList.get(currentTask).getTotalResponseTime() + 
											   (time + 1 - taskList.get(currentTask).getArrivalTime());
					taskList.get(currentTask).setTotalResponseTime(totalResponseTime); 
					//Updating Number of Completions
					int numberOfCompletions = taskList.get(currentTask).getNumberOfCompletions() + 1;
					taskList.get(currentTask).setNumberOfCompletions(numberOfCompletions);
					
					//Make Executed Time = 0
					taskList.get(currentTask).setExecutedTime(0);
					//Calculate New Arrival Time
					int arrivalTime = taskList.get(currentTask).getArrivalTime() + taskList.get(currentTask).getTaskPeriod();
					taskList.get(currentTask).setArrivalTime(arrivalTime);		
					//Calculate New Deadline
					int deadLine = taskList.get(currentTask).getArrivalTime() + taskList.get(currentTask).getTaskPeriod();
					taskList.get(currentTask).setDeadLine(deadLine);
					
				}
				
			}
			
			for(int i = 0; i < taskList.size(); i++){
				
				if(taskList.get(i).getDeadLine() < time){
					
					System.out.println("\tTask which Missed Deadline = " + taskList.get(i).getTaskName());
					//Make Executed Time = 0
					taskList.get(i).setExecutedTime(0);
					//Calculate New Arrival Time
					int arrivalTime = taskList.get(i).getArrivalTime() + taskList.get(i).getTaskPeriod();
					taskList.get(i).setArrivalTime(arrivalTime);		
					//Calculate New Deadline
					int deadLine = taskList.get(i).getArrivalTime() + taskList.get(i).getTaskPeriod();
					taskList.get(i).setDeadLine(deadLine);
					//Update Miss Count
					int deadLineMiss = taskList.get(i).getMissDeadine() + 1;
					taskList.get(i).setMissDeadine(deadLineMiss);
		
				}
				
			}
			
			Collections.sort(taskList, new Tasks.SlackTime());
			previousTask = currentTask;
			time++;
			
		}
		
		ReadInput.printTask(taskList);
		ReadInput.calculateAverageResponseTime(taskList);
	}
}
