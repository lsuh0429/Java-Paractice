import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main extends JFrame {
    private JFrame frame;
    private JPanel mainPanel;
    private CustomPanel chartPanel;
    private JScrollPane tablePane, chartPane;
    private JTable table;
    private JButton addBtn, removeBtn, executeBtn, randomBtn;
    private JLabel wtLabel, wtResultLabel, tatLabel, tatResultLabel;
    private JComboBox option;
    private DefaultTableModel model;
    private Color color[] = {Color.ORANGE, Color.cyan, Color.YELLOW, Color.MAGENTA, Color.LIGHT_GRAY,
    		 Color.gray, Color.green};
    private int pastX=24;
    private int t=0;
    
    public Main() {
        setTitle("CPU 스케줄러 시뮬레이터");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
        model = new DefaultTableModel(new String[]{"ID", "도착시간", "실행시간", "우선순위", "대기시간", "반환시간"}, 0);
        
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        tablePane = new JScrollPane(table);
        tablePane.setBounds(300, 25, 450, 250);
        
        addBtn = new JButton("추가");
        addBtn.setBounds(575, 280, 85, 25);
        addBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                model.addRow(new String[]{"", "", "", "", "", ""});
            } 
        });
        
        removeBtn = new JButton("제거");
        removeBtn.setBounds(665, 280, 85, 25);
        removeBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                
                if (row > -1) {
                    model.removeRow(row);
                }
            }
        });
        
        randomBtn = new JButton("랜덤");
        randomBtn.setBounds(300, 280, 85, 25);
        randomBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                model.addRow(new String[] {"P"+Integer.toString(t++),Integer.toString((int)(Math.random()*10)+1),
                		Integer.toString((int)(Math.random()*10)+1),
                		Integer.toString((int)(Math.random()*10)+1),"",""});
            } 
        });
        		
        
        chartPanel = new CustomPanel();
        chartPanel.setBackground(Color.WHITE);
        chartPane = new JScrollPane(chartPanel);
        chartPane.setBounds(50, 310, 900, 100);
        
        wtLabel = new JLabel("평균 대기 시간:");
        wtLabel.setBounds(50, 425, 180, 25);
        tatLabel = new JLabel("평균 반환 시간:");
        tatLabel.setBounds(50, 450, 180, 25);
        wtResultLabel = new JLabel();
        wtResultLabel.setBounds(430, 425, 180, 25);
        tatResultLabel = new JLabel();
        tatResultLabel.setBounds(430, 450, 180, 25);
        
        option = new JComboBox(new String[]{"FCFS", "SJF", "SRT", "PSN", "PSP", "RR", "HRN"});
        option.setBounds(755, 425, 85, 20);
        
        executeBtn = new JButton("계산");
        executeBtn.setBounds(755, 280, 85, 25);
        executeBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) option.getSelectedItem();
                SchedulerManage scheduler;
                pastX=30;
                switch (selected) {
                    case "FCFS":
                        scheduler = new FCFS();
                        break;
                    case "SJF":
                        scheduler = new SJF();
                        break;
                    case "SRT":
                        scheduler = new SRT();
                        break;
                    case "PSN":
                        scheduler = new PNP();
                        break;
                    case "PSP":
                        scheduler = new PSP();
                        break;
                    case "HRN":
                        scheduler = new HRN();
                        break;
                    case "RR":
                        String tq = JOptionPane.showInputDialog("규정 시간량(Time Quantum)");
                        if (tq == null) {
                            return;
                        }
                        scheduler = new RR();
                        scheduler.setTimeQuantum(Integer.parseInt(tq)); 
                        break;
                    default:
                        return;
                }
                
                for (int i = 0; i < model.getRowCount(); i++) {
                    String process = (String) model.getValueAt(i, 0);
                    int at = Integer.parseInt((String) model.getValueAt(i, 1));
                    int bt = Integer.parseInt((String) model.getValueAt(i, 2));
                    int pl;
                    
                    if (selected.equals("PSN") || selected.equals("PSP")) {
                        if (!model.getValueAt(i, 3).equals("")) {
                            pl = Integer.parseInt((String) model.getValueAt(i, 3));
                        }
                        else {
                            pl = 1;
                        }
                    }
                    else {
                        pl = 1;
                    }
                                        
                    scheduler.add(new Row(process, at, bt, pl,new Color(
                    		(int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255))));
                }
                
                scheduler.process();
                
                for (int i = 0; i < model.getRowCount(); i++) {
                    String process = (String) model.getValueAt(i, 0);
                    Row row = scheduler.getRow(process);
                    model.setValueAt(row.getWaitingTime(), i, 4);
                    model.setValueAt(row.getTurnaroundTime(), i, 5);
                }
                
                wtResultLabel.setText(Double.toString(scheduler.getAverageWaitingTime()));
                tatResultLabel.setText(Double.toString(scheduler.getAverageTurnAroundTime()));
                chartPanel.setTimeflow(scheduler.getTimeflow());
            }
        });
        
        mainPanel = new JPanel(null);
        mainPanel.add(tablePane);
        mainPanel.add(addBtn);
        mainPanel.add(removeBtn);
        mainPanel.add(executeBtn);
        mainPanel.add(randomBtn);
        mainPanel.add(chartPane);
        mainPanel.add(wtLabel);
        mainPanel.add(tatLabel);
        mainPanel.add(wtResultLabel);
        mainPanel.add(tatResultLabel);
        mainPanel.add(option);
        
        setSize(1050,550);
        setVisible(true);
        add(mainPanel);
    }
    
    public static void main(String[] args){
        new Main();
    }
    
    class CustomPanel extends JPanel{   
        private List<Event> timeflow;
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
       
            if (timeflow != null) {
            	
                for (int i = 0; i < timeflow.size(); i++) {
                    Event event = timeflow.get(i);
                    int x = 30 * (i + 1);
                    int y = 40;
                    int x2 = 40 * (i+2);
                    int y2 = 5;
                    
                    if(i/model.getRowCount()==0) {
                    	g.setColor(event.getColor());
                    	g.drawRect(x2, y2, 10, 10);
                    	g.fillRect(x2, y2, 10, 10);
                    	g.setColor(Color.black);
                    	g.drawString(event.getProcessName(), x2, y2+25);
                    }
                   
                    g.drawString(Integer.toString(event.getStartTime()), pastX - 5, y + 45);
                    g.setColor(event.getColor());
                    g.drawRect(pastX, y, (event.getFinishTime()-event.getStartTime())*12, 24);
                    g.fillRect(pastX, y, (event.getFinishTime()-event.getStartTime())*12, 24);
                    g.setColor(Color.black);
                    if((event.getFinishTime()-event.getStartTime())==1) {
                    g.drawString(event.getProcessName(), pastX + 5, y + 18);
                    }
                    else
                    	g.drawString(event.getProcessName(), pastX + 10, y + 18);
                    pastX += (event.getFinishTime()-event.getStartTime())*12;
                    
                    if (i == timeflow.size() - 1)
                    {
                        g.drawString(Integer.toString(event.getFinishTime()), pastX , y + 45);
                    }
                    
                }
            }
        }
        
        public void setTimeflow(List<Event> timeflow) {
            this.timeflow = timeflow;
            repaint();
        }
    }
}
