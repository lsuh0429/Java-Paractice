
import java.util.Collections;
import java.util.List;

public class FCFS extends SchedulerManage {
    public void process() {        
        Collections.sort(this.getRows(), (Object o1, Object o2) -> {
            if (((Row) o1).getArrivalTime() == ((Row) o2).getArrivalTime()) {
                return 0;
            }
            else if (((Row) o1).getArrivalTime() < ((Row) o2).getArrivalTime()) {
                return -1;
            }
            else {
                return 1;
            }
        });
        
        List<Event> timeflow = this.getTimeflow();
        
        for (Row row : this.getRows()) {
            if (timeflow.isEmpty()) {
                timeflow.add(new Event(row.getProcessName(), row.getArrivalTime(),
                		row.getArrivalTime() + row.getBurstTime(),row.getColor()));
            }
            else {
                Event event = timeflow.get(timeflow.size() - 1);
                timeflow.add(new Event(row.getProcessName(), event.getFinishTime(),
                		event.getFinishTime() + row.getBurstTime(),row.getColor()));
            }
        }
        
        for (Row row : this.getRows()) {
            row.setWaitingTime(this.getEvent(row).getStartTime() - row.getArrivalTime());
            row.setTurnaroundTime(row.getWaitingTime() + row.getBurstTime());
        }
    }
}
