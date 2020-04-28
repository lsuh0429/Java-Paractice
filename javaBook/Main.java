package javaBook;

public class Main {
	static String id = "1";
	static String isbn = "9788972782810";
	static String name = "아홉살 인생";
	static String info = "지은이가 스물아홉해 살아오면서 느끼고 배웠던 인생이야기를 아홉 살짜리 주인공을 통해 정리한 책";
	static int price = 12000;
	static String author = "위기철";
	static String genre = "소설";
	static String cycle = "월간";
	static String gift = "손소독제";

	public static void main(String[] args) {
		Book book = new Book(id, isbn, name, info, price);
		book.show();
		Novel novel = new Novel("2", isbn, name, info, price, author, genre);
		novel.show();
		Magazine magazine = new Magazine("3", "12345678980123","PC사랑",
				"매달 알아보는 PC와 IT정보", 10000, "월간");
		magazine.show();
		Sport sport = new Sport("4", "9876543210987", "베스트일레븐",
				"축구정보를 한눈에 볼수있는 잡지", 20000, "월간", "http://www.besteleven.com/");
		sport.show();
		Hobby hobby = new Hobby("5", "1234567890123", "PC사랑",
				"매달 알아보는 PC와 IT정보", 10000, "게이밍마우스");
		hobby.show();
	}

}
