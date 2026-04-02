package ex03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

interface Ipo {
	void input();
	void process();
	void output();
}

class MovVo {
//	field
	private String num;
	private String name;
	private String mCode;
	private int    people;
	private int    basic;
	private String tCode;
	
	private String mName;
	private double money;
	private double sc;
	private double bill;
	private String tName;

//	constructor
	public MovVo(String num, String name, String mCode, 
			int people, int basic, String tCode) {
		this.num = num;
		this.name = name;
		this.mCode = mCode;
		this.people = people;
		this.basic = basic;
		this.tCode = tCode;
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

	public String getmCode() {
		return mCode;
	}

	public void setmCode(String mCode) {
		this.mCode = mCode;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	public int getBasic() {
		return basic;
	}

	public void setBasic(int basic) {
		this.basic = basic;
	}

	public String gettCode() {
		return tCode;
	}

	public void settCode(String tCode) {
		this.tCode = tCode;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public double getSc() {
		return sc;
	}

	public void setSc(double sc) {
		this.sc = sc;
	}

	public double getBill() {
		return bill;
	}

	public void setBill(double bill) {
		this.bill = bill;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}
	
//	toString
	@Override
	public String toString() {
		return "movVo [num=" + num + ", name=" + name + ", mCode=" + mCode + ", people=" + people + ", basic=" + basic
				+ ", tCode=" + tCode + ", mName=" + mName + ", money=" + money + ", sc=" + sc + ", bill=" + bill
				+ ", tName=" + tName + "]";
	}
	
}

class Movie implements Ipo {

	List<MovVo> list = new ArrayList<>();
	
	@Override
	public void input() {
		Scanner in = new Scanner(System.in);
		
		System.out.println("입력:예매번호 이름 영화코드 관람인원 기본요금 시간대코드");
		
		while (true) {
			String line = in.nextLine();
			if ( line.equals("quit") ) break;
			
			String [] li = line.trim().split(",");
			String num    = li[0].trim();
			String name   = li[1].trim();
			String mCode  = li[2].trim();
			int    people = Integer.parseInt(li[3].trim());
			int    basic  = Integer.parseInt(li[4].trim());
			String tCode  = li[5].trim();
			
			MovVo movvo = new MovVo(num, name, mCode, people, basic, tCode);
			list.add(movvo);
		}
	}

	@Override
	public void process() {
		
		for (int i = 0; i < list.size(); i++) {
			MovVo Vo = list.get(i);
			
			double money = Vo.getBasic() * Vo.getPeople();
			Vo.setMoney(money);
			
			Map<String, String> mName = new HashMap<>();
			
			
		}
		
	}

	@Override
	public void output() {
		// TODO Auto-generated method stub
		
	}
	
}

public class MovieTest {

	public static void main(String[] args) {
		Movie m = new Movie();
		m.input();
		m.process();
		m.output();

	}

}
