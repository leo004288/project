package ex02;

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

class AptVo {
//  field
	private String donho;
	private String name;
	private int    elc;
	private int    wat;
	private int    basic;
	private String id;
	
	private double elcBill;
	private double watBill;
	private double Bill;
	private String idName;

//	constructor
	public AptVo(String donho, String name, int elc, int wat, int basic, String id) {
		super();
		this.donho = donho;
		this.name = name;
		this.elc = elc;
		this.wat = wat;
		this.basic = basic;
		this.id = id;
	}

//	get set
	public String getDonho() {
		return donho;
	}

	public void setDonho(String donho) {
		this.donho = donho;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getElc() {
		return elc;
	}

	public void setElc(int elc) {
		this.elc = elc;
	}

	public int getWat() {
		return wat;
	}

	public void setWat(int wat) {
		this.wat = wat;
	}

	public int getBasic() {
		return basic;
	}

	public void setBasic(int basic) {
		this.basic = basic;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getElcBill() {
		return elcBill;
	}

	public void setElcBill(double elcBill) {
		this.elcBill = elcBill;
	}

	public double getWatBill() {
		return watBill;
	}

	public void setWatBill(double watBill) {
		this.watBill = watBill;
	}

	public double getBill() {
		return Bill;
	}

	public void setBill(double bill) {
		Bill = bill;
	}

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

// toString
	@Override
	public String toString() {
		return "AptVo [donho=" + donho + ", name=" + name + ", elc=" + elc + ", wat=" + wat + ", basic=" + basic
				+ ", id=" + id + ", elcBill=" + elcBill + ", watBill=" + watBill + ", Bill=" + Bill + ", idName="
				+ idName + "]";
	}	
	
}

class Apt implements Ipo {
	
	List<AptVo> list = new ArrayList();
	
	@Override
	public void input() {
		Scanner in = new Scanner(System.in);
		
		System.out.println("입력:동호수,세대주명,전기사용량,수도사용량,기본관리비,평형코드");
		
		while (true) {
			String line = in.nextLine();
			if(line.equals("quit")) break;
			
			String [] li = line.trim().split(",");
			String donho = li[0].trim();
			String name  = li[1].trim();
			int    elc   = Integer.parseInt(li[2].trim());
			int    wat   = Integer.parseInt(li[3].trim());
			int    basic = Integer.parseInt(li[4].trim());
			String id    = li[5].trim();
			
			AptVo artvo = new AptVo(donho, name, elc, wat, basic, id);
			list.add(artvo);
		}
		
		
	}

	@Override
	public void process() {
		
		for (int i = 0; i < list.size(); i++) {
			AptVo Vo = list.get(i);
			
			Vo.setElcBill( Vo.getElc() * 120 );
			Vo.setWatBill( Vo.getWat() * 900 );
			Vo.setBill( Vo.getElcBill() + Vo.getWatBill() + Vo.getBasic() );
			
			Map<String,String> map = new HashMap<>();
			map.put("A", "20평형");
			map.put("B", "30평형");
			map.put("C", "40평형");
			map.put("D", "50평형");
			String idName = map.get( Vo.getId() );
			Vo.setIdName(idName);
		}
		
	}

	@Override
	public void output() {
		String title = "동호수 세대주명 전기요금 수도요금 총관리비 평형명";
		System.out.println(title);
		
		String fmt	 = "%s %s %.2f %.2f %.2f %s\n";
		for (int i = 0; i < list.size(); i++) {
			AptVo Vo = list.get(i);
			
			System.out.printf(fmt,
					Vo.getDonho(),Vo.getName(),Vo.getElcBill(),Vo.getWatBill(),Vo.getBill(),Vo.getIdName());
		}
		
	}
	
}

public class AptTest {

	public static void main(String[] args) {
		Apt a = new Apt();
		a.input();
		a.process();
		a.output();

	}

}
