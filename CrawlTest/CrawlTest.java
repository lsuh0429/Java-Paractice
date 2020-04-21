package javaTest;
import java.util.Iterator;

import javax.imageio.ImageIO;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.net.URL;
import java.awt.image.BufferedImage;

public class CrawlTest {
	public String saveDir = null;
	public String fileformat = null;
	public String url = null;
	public BufferedImage bi = null;
	public URL imageurl = null;
	public String saveFileName = null;
	Iterator<Element> titleIe = null;
	Iterator<Element> imgName = null;
	Iterator<Element> imgIe = null;
	Iterator<Element> page = null;
	
	private Document doc = null;   //Document에는 페이지의 전체 소스가 저장됨
	private static int i = 1;
	
	public CrawlTest(String url, String saveDir, String fileformat)
	{
		this.url = url;
		this.saveDir = saveDir;
		this.fileformat = fileformat;
	}
	
	public void getList() throws IOException
	{
		try {
			doc = Jsoup.connect(url).get(); //get()메소드를 통해 Document타입의 변수 doc에 대입
		} catch (IOException e) {
			//Connection Fail 예외처리
			e.printStackTrace();
		}
		
		//select를 이용하여 원하는 태그를 선택한다.
		Elements element = doc.select(".list_body.newsflash_body");
		//Iterator을 사용하여 하나씩 값 가져오기
		
		titleIe = element.select("li > dl > dt > a").iterator();
		while (titleIe.hasNext()) {
			String title = titleIe.next().text();
			if(title.length()<1 || title.compareTo("동영상기사")==0)
				continue;
			System.out.println(i++ +". " + title);
		}
	}
	
	public void imageDown() throws IOException
	{
		Elements element = doc.select(".photo");
		imgName = element.select("img").iterator();
		imgIe = element.select("img").iterator();
		
		while (imgIe.hasNext()) {
			String image = imgIe.next().attr("src");
			imageurl = new URL(image);
			bi = ImageIO.read(imageurl);
			
			saveFileName = imgName.next().attr("alt").replace("/", ".").
					replace(":", ";").replace("*", ".").replace("?", ".").
					replace("\"", "'").replace("<", "(").replace(">", ")").
					replace("|", ".").replace("\\", ".") + ".jpg";
			
			File saveFile = new File(saveDir + saveFileName);
			ImageIO.write(bi, fileformat, saveFile);
		}
		System.out.println("이미지 다운로드 완료");
	}
	
	public String getNextpage() {
		Elements element = doc.select("div.paging [href]");
		String p = element.attr("href");
		p = "https://news.naver.com/main/list.nhn" + p;
		return p;
	}
	
	public static void main(String[] args) throws IOException {
		/*
		 *
			문제 : 네이버 경제 뉴스 리스트를 크롤링 해온다.
			※ https://news.naver.com/main/list.nhn?mode=LS2D&mid=shm&sid1=101&sid2=263
			※ 네이버 뉴스 경제일반 파트(2페이지까지)
			※ JSOUP 활용
		              ※이미지파일은 upload 폴더 밑에 저장(제목은 뉴스타이틀)
		*/
		
		// Jsoup를 이용해서 네이버 경제 뉴스 리스트 크롤링
		
		//크롤링할 url, 저장위치, 이미지 파일형식 지정
		CrawlTest crw = new CrawlTest("https://news.naver.com/main/list.nhn?mode=LS2D&mid=shm&sid1=101&sid2=263",
				"C:\\Users\\lsuh0\\eclipse-workspace\\INCOPR~1\\upload\\", "jpg");
		CrawlTest crw2;
		System.out.println("네이버 경제 뉴스 1페이지");
		System.out.println("============================================================");
		crw.getList(); //뉴스 리스트 받아오기
		System.out.println("============================================================");
		crw.imageDown(); //뉴스 이미지파일 저장
		System.out.println("============================================================");
		
		System.out.println("2페이지");
		crw2 = new CrawlTest(crw.getNextpage(),"C:\\Users\\lsuh0\\eclipse-workspace\\INCOPR~1\\upload\\", "jpg");
		System.out.println("============================================================");
		crw2.getList(); //뉴스 리스트 받아오기
		System.out.println("============================================================");
		crw2.imageDown(); //뉴스 이미지파일 저장
		System.out.println("============================================================");
		
	}
}