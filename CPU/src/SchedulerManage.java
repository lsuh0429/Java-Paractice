
import java.util.ArrayList;
import java.util.List;

public abstract class SchedulerManage
{
    private final List<Row> rows;
    private final List<Event> timeflow;
    private int timeQuantum;
    
    public SchedulerManage() {
        rows = new ArrayList();
        timeflow = new ArrayList();
        timeQuantum = 1;
    }
    
    public boolean add(Row row) {
        return rows.add(row);
    }
    
    public void setTimeQuantum(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }
    
    public int getTimeQuantum() {
        return timeQuantum;
    }
    
    public double getAverageWaitingTime() {
        double avg = 0.0;
        
        for (Row row : rows) {
            avg += row.getWaitingTime();
        }
        
        return avg / rows.size();
    }
    
    public double getAverageTurnAroundTime() {
        double avg = 0.0;
        
        for (Row row : rows) {
            avg += row.getTurnaroundTime();
        }
        
        return avg / rows.size();
    }
    
    public Event getEvent(Row row) {
        for (Event event : timeflow) {
            if (row.getProcessName().equals(event.getProcessName())) {
                return event;
            }
        }
        
        return null;
    }
    
    public Row getRow(String process) {
        for (Row row : rows)  {
            if (row.getProcessName().equals(process)) {
                return row;
            }
        }
        
        return null;
    }
    
    public List<Row> getRows() {
        return rows;
    }
    
    public List<Event> getTimeflow() {
        return timeflow;
    }
    
    public abstract void process();
}
