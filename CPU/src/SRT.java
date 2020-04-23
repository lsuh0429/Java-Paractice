
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SRT extends SchedulerManage {
    @Override
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
        
        List<Row> rows = Utility.deepCopy(this.getRows());
        int time = rows.get(0).getArrivalTime();
        
        while (!rows.isEmpty()) {
            List<Row> availableRows = new ArrayList();
            
            for (Row row : rows) {
                if (row.getArrivalTime() <= time) {
                    availableRows.add(row);
                }
            }
            
            Collections.sort(availableRows, (Object o1, Object o2) -> {
                if (((Row) o1).getBurstTime() == ((Row) o2).getBurstTime()) {
                    return 0;
                }
                else if (((Row) o1).getBurstTime() < ((Row) o2).getBurstTime()) {
                    return -1;
                }
                else {
                    return 1;
                }
            });
            
            Row row = availableRows.get(0);
            this.getTimeflow().add(new Event(row.getProcessName(), time, ++time,row.getColor()));
            row.setBurstTime(row.getBurstTime() - 1);
            
            if (row.getBurstTime() == 0) {
                for (int i = 0; i < rows.size(); i++) {
                    if (rows.get(i).getProcessName().equals(row.getProcessName())) {
                        rows.remove(i);
                        break;
                    }
                }
            }
        }
        
        for (int i = this.getTimeflow().size() - 1; i > 0; i--) {
            List<Event> timeflow = this.getTimeflow();
            
            if (timeflow.get(i - 1).getProcessName().equals(timeflow.get(i).getProcessName())) {
                timeflow.get(i - 1).setFinishTime(timeflow.get(i).getFinishTime());
                timeflow.remove(i);
            }
        }
        
        Map map = new HashMap();
        
        for (Row row : this.getRows()) {
            map.clear();
            
            for (Event event : this.getTimeflow()) {
                if (event.getProcessName().equals(row.getProcessName())) {
                    if (map.containsKey(event.getProcessName())) {
                        int w = event.getStartTime() - (int) map.get(event.getProcessName());
                        row.setWaitingTime(row.getWaitingTime() + w);
                    }
                    else {
                        row.setWaitingTime(event.getStartTime() - row.getArrivalTime());
                    }
                    
                    map.put(event.getProcessName(), event.getFinishTime());
                }
            }
            row.setTurnaroundTime(row.getWaitingTime() + row.getBurstTime());
        }
    }
}
