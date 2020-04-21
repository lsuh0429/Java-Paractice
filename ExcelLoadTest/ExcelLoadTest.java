package javaTest;

import java.util.*;
import java.io.IOException;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;

//정렬기준을 구현하기 위해 Comparable 인터페이스 implements
class Kteam implements Comparable {
	String name;
	int match;
	int win;
	int lose;
	int draw;
	int score;
	
	public Kteam(String name, int match, int win, int lose, int draw, int score) {
		this.name = name;
		this.match = match;
		this.win = win;
		this.lose = lose;
		this.draw = draw;
		this.score = score;
	}
	
	//승점 동률의 경우를 위한 정렬기준 변경
	public int compareTo(Object o) {
		if(this.score == ((Kteam)o).score) {
			if(this.win > ((Kteam)o).score) {
				return -1;
			} // 동률의 경우 승수가 많은 팀 우선
			  // 승수도 동일할 경우 추후 골득실 차이를 기준으로...
			else return 1;
		}
		return this.score - ((Kteam)o).score;
	}
	
	public String toString() {
		return "팀 = "+ name +",승점 = "+score+" 순위 = ";
	}
}

public class ExcelLoadTest {
	String tmpName;
	int tmpMatch;
	int tmpWin;
	int tmpDraw;
	int tmpLose;
	int tmpScore;
	
	public void run()
	{
		List<Kteam> list = new ArrayList<Kteam>();
		
		 try {
	            FileInputStream file = new FileInputStream("C:\\Users\\lsuh0\\eclipse-workspace\\INCOPR~1\\2018K리그.xlsx");
	            //파일 경로 입력
	            XSSFWorkbook workbook = new XSSFWorkbook(file);
	 
	            int rowindex=0;
	            int columnindex=0;
	            //시트 수 (첫번째에만 존재하므로 0)
	            
	            XSSFSheet sheet=workbook.getSheetAt(0);
	            //행의 수
	            
	            int rows=sheet.getPhysicalNumberOfRows();
	            for(rowindex=1;rowindex<rows;rowindex++){
	                //행을 읽어들임
	                XSSFRow row=sheet.getRow(rowindex);
	                if(row !=null){
	                	//셀의 내용을 저장
	                	 XSSFCell cell=row.getCell(0);
	                	 tmpName = cell.getStringCellValue();
	                	 cell=row.getCell(1);
                     	 tmpMatch = (int)cell.getNumericCellValue();
                     	 cell=row.getCell(2);
                     	 tmpWin = (int)cell.getNumericCellValue();
                     	 cell=row.getCell(3);
                     	 tmpDraw = (int)cell.getNumericCellValue();
                      	 cell=row.getCell(4);
                     	 tmpLose = (int)cell.getNumericCellValue();
                     	 tmpScore = (tmpWin*3) + tmpDraw;
                     	 
                     	 list.add(new Kteam(tmpName, tmpMatch, tmpWin, tmpDraw, tmpLose, tmpScore));
                     	 //리스트에 팀 추가
	                }
	            }
	            
	            Collections.sort(list); //컬렉션의 sort 메소드 이용
	            Collections.reverse(list); // 내림차순으로 정렬하기 위해 역순으로 변경
	            Iterator i = list.iterator(); //Iterator으로 탐방
	            int rank=1;
	            while(i.hasNext()) {
	            	System.out.println(i.next().toString()+rank++);
	            }
	 
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	}
	
	public static void main(String[] args) throws IOException {
		/*
		 *
			문제 : 2017-2018 K리그팀들의 규칙에 따른 승점과 순위를 구하시오.
			※ 규칙에 따르면 경기에서 이긴 팀은 3점을 얻고 비기면 1을 지면 0점을 받는다.
			※ (첨부된 2018K리그 엑셀 파일 리스트를 불러와서 구현)->API 활용(POI)

			결과 예)  팀 = 대구 ,승점 = 101 순위= 1
			                 팀 = 울산  ,승점 =94 순위=  2
			                 팀 = 서울  ,승점 =68 순위=  3
			                 팀 = 수원  ,승점 =48 순위=  4
			                 팀 = 전북  ,승점 =41 순위=  5
		       ...
	   *
		*/
		ExcelLoadTest test = new ExcelLoadTest();
		test.run();
	}
}
