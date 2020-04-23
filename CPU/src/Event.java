import java.awt.Color;

public class Event {
    private final String processName;
    private final int startTime;
    private int finishTime;
    private Color color;

    
    public Event(String processName, int startTime, int finishTime, Color color) {
        this.processName = processName;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.color = color;
    }
    
    public String getProcessName() {
        return processName;
    }
    
    public int getStartTime() {
        return startTime;
    }
    
    public int getFinishTime() {
        return finishTime;
    }
    
    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }
    
    public Color getColor() {
    	return this.color;
    }
}
