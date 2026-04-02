package ex03;

import java.util.ArrayList;
import java.util.Collections;
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
	private double basic;
	private String tCode;
	
	private String mName;
	private double money;
	private double sc;
	private double bill;
	private String tName;

//	constructor
	public MovVo(String num, String name, String mCode, 
			int people, double basic, String tCode) {
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

	public double getBasic() {
		return basic;
	}

	public void setBasic(double basic) {
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
			double basic  = Double.parseDouble(li[4].trim());
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

			double scp    = 0.0;
			String tName = ""; 
			
			if ( Vo.gettCode().equals("M") ) {
				scp = 0.0; tName = "조조";
			} else {
				if (Vo.gettCode().equals("D")) {
					scp = 0.05; tName = "일반";
				} else {
					if (Vo.gettCode().equals("N")) {
						scp = 0.1;  tName = "심야";
					}
				}
			}
			double sc = Vo.getMoney() * scp;
			Vo.setSc(sc);	
			Vo.settName(tName);				
					
			double bill = ( Vo.getBasic() * Vo.getPeople() ) + Vo.getSc();
			Vo.setBill(bill);
			
			Map<String, String> map = new HashMap<>();
			map.put("A1", "액션대작");
			map.put("R1", "로맨스극장");
			map.put("C1", "코미디쇼");
			map.put("H1", "공포특집");
			String mCode = Vo.getmCode();
			String mName = map.get(mCode); 
			Vo.setmName(mName);
			
		}
		
		
	}

	@Override
	public void output() {
		String title = "예매번호 이름  영화명   총요금   할증액  최종결제금액  시간대명";
		String fmt   = "%s   %s %s  %.2f  %.2f  %.2f    %s\n";
		System.out.println(title);
		Collections.sort(list, (a, b) -> Double.compare(b.getBill(), a.getBill()));
		
		
		for (int i = 0; i < list.size(); i++) {
			MovVo Vo = list.get(i);
			
			System.out.printf(fmt,
					Vo.getNum(),Vo.getName(),Vo.getmName(),Vo.getMoney(),Vo.getSc(),Vo.getBill(),Vo.gettName());
		}
		
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
