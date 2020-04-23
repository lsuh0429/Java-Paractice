import java.awt.Color;

public class Row {
    private String processName;
    private int arrivalTime;
    private int burstTime;
    private int priorityLevel;
    private int waitingTime;
    private int turnaroundTime;
    private Color color;
    
    private Row(String processName, int arrivalTime, int burstTime,
    		int priorityLevel, int waitingTime, int turnaroundTime, Color color) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priorityLevel = priorityLevel;
        this.waitingTime = waitingTime;
        this.turnaroundTime = turnaroundTime;
        this.color = color;
    }
    
    public Row(String processName, int arrivalTime, int burstTime,
    		int priorityLevel,Color color) {
        this(processName, arrivalTime, burstTime, priorityLevel, 0, 0,color);
    }
    
    public Row(String processName, int arrivalTime, int burstTime, Color color) {
        this(processName, arrivalTime, burstTime, 0, 0, 0,color);
    }
    
    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }
    
    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }
    
    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }
    
    public String getProcessName() {
        return this.processName;
    }
    
    public int getArrivalTime() {
        return this.arrivalTime;
    }
    
    public int getBurstTime() {
        return this.burstTime;
    }
    
    public int getPriorityLevel() {
        return this.priorityLevel;
    }
    
    public int getWaitingTime() {
        return this.waitingTime;
    }
    
    public int getTurnaroundTime() {
        return this.turnaroundTime;
    }
    
    public void setColor(Color color) {
    	this.color = color;
    }
    
    public Color getColor() {
    	return this.color;
    }
    
    public int getHRNpl() {
    	return (this.getBurstTime()+this.getWaitingTime()/this.getBurstTime());
    }
}
