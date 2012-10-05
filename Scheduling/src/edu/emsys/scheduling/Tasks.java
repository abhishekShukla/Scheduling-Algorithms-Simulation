package edu.emsys.scheduling;

import java.util.Comparator;

public class Tasks {
	
	private int taskName;
	private int taskPeriod;
	private int taskWcet;
	private int deadLine;
	private int executedTime;
	private int slackTime; 
	private int arrivalTime;
	private int preEmption;
	private int missDeadine;
	private int numberOfCompletions;
	private int totalResponseTime;


	public Tasks(){
		this.taskName = 0;
		this.taskPeriod = 0;
		this.taskWcet = 0;
		this.arrivalTime = 0;
		this.deadLine = 0;
		this.executedTime = 0;
		this.slackTime = 0;
		this.missDeadine = 0;
		this.preEmption = 0;
		this.numberOfCompletions = 0;
		this.totalResponseTime = 0;
	}
	
	public int getTaskName() {
		return taskName;
	}


	public void setTaskName(int taskName) {
		this.taskName = taskName;
	}


	public int getTaskPeriod() {
		return taskPeriod;
	}


	public void setTaskPeriod(int taskPeriod) {
		this.taskPeriod = taskPeriod;
	}


	public int getTaskWcet() {
		return taskWcet;
	}


	public void setTaskWcet(int taskWcet) {
		this.taskWcet = taskWcet;
	}


	public int getDeadLine() {
		return deadLine;
	}


	public void setDeadLine(int deadLine) {
		this.deadLine = deadLine;
	}


	public int getExecutedTime() {
		return executedTime;
	}


	public void setExecutedTime(int executedTime) {
		this.executedTime = executedTime;
	}


	public int getSlackTime() {
		return slackTime;
	}


	public void setSlackTime(int slactTime) {
		this.slackTime = slactTime;
	}


	public int getArrivalTime() {
		return arrivalTime;
	}


	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	
	public int getPreEmption() {
		return preEmption;
	}

	
	public void setPreEmption(int preEmption) {
		this.preEmption = preEmption;
	}

	
	public int getMissDeadine() {
		return missDeadine;
	}

	
	public void setMissDeadine(int missDeadine) {
		this.missDeadine = missDeadine;
	}
	
	
	public int getNumberOfCompletions() {
		return numberOfCompletions;
	}

	
	public void setNumberOfCompletions(int numberOfCompletions) {
		this.numberOfCompletions = numberOfCompletions;
	}
	
	
	public int getTotalResponseTime() {
		return totalResponseTime;
	}

	
	public void setTotalResponseTime(int totalResponseTime) {
		this.totalResponseTime = totalResponseTime;
	}


	// Comparator by Period
    public static class Period implements Comparator<Tasks> {
    	@Override
        public int compare(Tasks Task1, Tasks Task2) {
            return Task1.getTaskPeriod() - Task2.getTaskPeriod();
        }
    }
    
    // Comparator by Deadline
    public static class Deadline implements Comparator<Tasks> {
    	@Override
        public int compare(Tasks Task1, Tasks Task2) {
            return Task1.getDeadLine() - Task2.getDeadLine();
        }
    }
    
 // Comparator by SlackTime
    public static class SlackTime implements Comparator<Tasks> {
    	@Override
        public int compare(Tasks Task1, Tasks Task2) {
            return Task1.getSlackTime() - Task2.getSlackTime();
        }
    }
    
}
