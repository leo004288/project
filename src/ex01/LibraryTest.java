package ex01;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

interface Ipo {
	void input();
	void process();
	void output();
}

class LbVo {
//	field
	private String num;
	private String name;
	private String book;
	private String ldate;
	private String ddate;
	private String id;
	
	private long days;
	private String grade;
	private String idname;
	
//	constructor
	public LbVo(String num, String name, String book, String ldate, String ddate, String id) {
		super();
		this.num = num;
		this.name = name;
		this.book = book;
		this.ldate = ldate;
		this.ddate = ddate;
		this.id = id;
	}
	
//	get set
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public String getLdate() {
		return ldate;
	}
	public void setLdate(String ldate) {
		this.ldate = ldate;
	}
	public String getDdate() {
		return ddate;
	}
	public void setDdate(String ddate) {
		this.ddate = ddate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getDays() {
		return days;
	}
	public void setDays(long days) {
		this.days = days;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getIdname() {
		return idname;
	}
	public void setIdname(String idname) {
		this.idname = idname;
	}
	
//	toString
	@Override
	public String toString() {
		return "LbVo [num=" + num + ", name=" + name + ", book=" + book + ", ldate=" + ldate + ", ddate=" + ddate
				+ ", id=" + id + ", days=" + days + ", grade=" + grade + ", idname=" + idname + "]";
	}
	
}

class Library implements Ipo {
	
	private LbVo l;	
	
	@Override
	public void input() {
		Scanner in = new Scanner(System.in);
		
		System.out.println("입력:회원번호,이름,도서명,대출일,반납예정일,회원구분");
		String line = in.nextLine();
		
		String [] li =  line.trim().split(",");
		String num   = li[0].trim();
		String name  = li[1].trim();
		String book  = li[2].trim();
		String ldate = li[3].trim();
		String ddate = li[4].trim();
		String id    = li[5].trim();
		
		l            = new LbVo(num,name,book,ldate,ddate,id);
	}

	@Override
	public void process() {
		
		DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate ldate = LocalDate.parse(l.getLdate(),d ); 
		LocalDate today = LocalDate.now(); 
		long days = ChronoUnit.DAYS.between(ldate, today);
		l.setDays(days);
		
		String grade = "";
		if (days <= 7) {
			grade = "정상";
		} else {
			if (8 <= days && days <= 14) {
				grade = "주의";				
			} else {
				if (15 <= days) {
					grade = "연체";				
				}
			}
		}
		l.setGrade(grade);
		
		String idname = "";
		switch (l.getId()) {
		case "A" : idname = "일반회원"; break;
		case "B" : idname = "우수회원"; break;
		case "C" : idname = "특별회원"; break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + l.getId());
		}
		l.setIdname(idname);
		
	}

	@Override
	public void output() {
		String title = "회원번호 이름   도서명    대출일수 대출등급 회원구분명";
		String fmt	 = "%s      %s  %s    %d    %s   %s";
		System.out.println(title);
		System.out.printf(fmt,
				l.getNum(),l.getName(),l.getBook(),l.getDays(),l.getGrade(),l.getIdname());
		
	}
	
}

public class LibraryTest {

	public static void main(String[] args) {
		Library l = new Library();
		l.input();
		l.process();
		l.output();
		
	}

}
