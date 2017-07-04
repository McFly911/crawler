package com.mcflykid.crawler.jpa;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public class BaseTable implements Serializable{

	public BaseTable(Long id){
		this.id = id;
	}
	
	public BaseTable(){
	}
	
	@Id
	private Long id;
	
	private String log1;
	private String log2;
	private String log3;
	private String log4;
	private String log5;
	
	private String string01;
	private String string02;
	private String string03;
	private String string04;
	private String string05;
	private String string06;
	private String string07;
	private String string08;
	private String string09;
	private String string10;
	private String string11;
	private String string12;
	private String string13;
	private String string14;
	private String string15;
	private String string16;
	private String string17;
	private String string18;
	private String string19;
	private String string20;
	
	private Long long01;
	private Long long02;
	private Long long03;
	private Long long04;
	private Long long05;
	private Long long06;
	private Long long07;
	private Long long08;
	private Long long09;
	private Long long10;
	private Long long11;
	private Long long12;
	private Long long13;
	private Long long14;
	private Long long15;
	private Long long16;
	private Long long17;
	private Long long18;
	private Long long19;
	private Long long20;
	
	@Lob private String lob01;
	@Lob private String lob02;
	@Lob private String lob03;
	@Lob private String lob04;
	@Lob private String lob05;
	@Lob private String lob06;
	@Lob private String lob07;
	@Lob private String lob08;
	@Lob private String lob09;
	@Lob private String lob10;
	@Lob private String lob11;
	@Lob private String lob12;
	@Lob private String lob13;
	@Lob private String lob14;
	@Lob private String lob15;
	@Lob private String lob16;
	@Lob private String lob17;
	@Lob private String lob18;
	@Lob private String lob19;
	@Lob private String lob20;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLog1() {
		return log1;
	}
	public void setLog1(String log1) {
		this.log1 = log1;
	}
	public String getLog2() {
		return log2;
	}
	public void setLog2(String log2) {
		this.log2 = log2;
	}
	public String getLog3() {
		return log3;
	}
	public void setLog3(String log3) {
		this.log3 = log3;
	}
	public String getLog4() {
		return log4;
	}
	public void setLog4(String log4) {
		this.log4 = log4;
	}
	public String getLog5() {
		return log5;
	}
	public void setLog5(String log5) {
		this.log5 = log5;
	}
	
	public String getString10() {
		return string10;
	}
	public void setString10(String string10) {
		this.string10 = string10;
	}
	public String getString11() {
		return string11;
	}
	public void setString11(String string11) {
		this.string11 = string11;
	}
	public String getString12() {
		return string12;
	}
	public void setString12(String string12) {
		this.string12 = string12;
	}
	public String getString13() {
		return string13;
	}
	public void setString13(String string13) {
		this.string13 = string13;
	}
	public String getString14() {
		return string14;
	}
	public void setString14(String string14) {
		this.string14 = string14;
	}
	public String getString15() {
		return string15;
	}
	public void setString15(String string15) {
		this.string15 = string15;
	}
	public String getString16() {
		return string16;
	}
	public void setString16(String string16) {
		this.string16 = string16;
	}
	public String getString17() {
		return string17;
	}
	public void setString17(String string17) {
		this.string17 = string17;
	}
	public String getString18() {
		return string18;
	}
	public void setString18(String string18) {
		this.string18 = string18;
	}
	public String getString19() {
		return string19;
	}
	public void setString19(String string19) {
		this.string19 = string19;
	}
	public String getString20() {
		return string20;
	}
	public void setString20(String string20) {
		this.string20 = string20;
	}
	
	public Long getLong10() {
		return long10;
	}
	public void setLong10(Long long10) {
		this.long10 = long10;
	}
	public Long getLong11() {
		return long11;
	}
	public void setLong11(Long long11) {
		this.long11 = long11;
	}
	public Long getLong12() {
		return long12;
	}
	public void setLong12(Long long12) {
		this.long12 = long12;
	}
	public Long getLong13() {
		return long13;
	}
	public void setLong13(Long long13) {
		this.long13 = long13;
	}
	public Long getLong14() {
		return long14;
	}
	public void setLong14(Long long14) {
		this.long14 = long14;
	}
	public Long getLong15() {
		return long15;
	}
	public void setLong15(Long long15) {
		this.long15 = long15;
	}
	public Long getLong16() {
		return long16;
	}
	public void setLong16(Long long16) {
		this.long16 = long16;
	}
	public Long getLong17() {
		return long17;
	}
	public void setLong17(Long long17) {
		this.long17 = long17;
	}
	public Long getLong18() {
		return long18;
	}
	public void setLong18(Long long18) {
		this.long18 = long18;
	}
	public Long getLong19() {
		return long19;
	}
	public void setLong19(Long long19) {
		this.long19 = long19;
	}
	public Long getLong20() {
		return long20;
	}
	public void setLong20(Long long20) {
		this.long20 = long20;
	}
	
	public String getLob10() {
		return lob10;
	}
	public void setLob10(String lob10) {
		this.lob10 = lob10;
	}
	public String getLob11() {
		return lob11;
	}
	public void setLob11(String lob11) {
		this.lob11 = lob11;
	}
	public String getLob12() {
		return lob12;
	}
	public void setLob12(String lob12) {
		this.lob12 = lob12;
	}
	public String getLob13() {
		return lob13;
	}
	public void setLob13(String lob13) {
		this.lob13 = lob13;
	}
	public String getLob14() {
		return lob14;
	}
	public void setLob14(String lob14) {
		this.lob14 = lob14;
	}
	public String getLob15() {
		return lob15;
	}
	public void setLob15(String lob15) {
		this.lob15 = lob15;
	}
	public String getLob16() {
		return lob16;
	}
	public void setLob16(String lob16) {
		this.lob16 = lob16;
	}
	public String getLob17() {
		return lob17;
	}
	public void setLob17(String lob17) {
		this.lob17 = lob17;
	}
	public String getLob18() {
		return lob18;
	}
	public void setLob18(String lob18) {
		this.lob18 = lob18;
	}
	public String getLob19() {
		return lob19;
	}
	public void setLob19(String lob19) {
		this.lob19 = lob19;
	}
	public String getLob20() {
		return lob20;
	}
	public void setLob20(String lob20) {
		this.lob20 = lob20;
	}

	public String getString01() {
		return string01;
	}

	public void setString01(String string01) {
		this.string01 = string01;
	}

	public String getString02() {
		return string02;
	}

	public void setString02(String string02) {
		this.string02 = string02;
	}

	public String getString03() {
		return string03;
	}

	public void setString03(String string03) {
		this.string03 = string03;
	}

	public String getString04() {
		return string04;
	}

	public void setString04(String string04) {
		this.string04 = string04;
	}

	public String getString05() {
		return string05;
	}

	public void setString05(String string05) {
		this.string05 = string05;
	}

	public String getString06() {
		return string06;
	}

	public void setString06(String string06) {
		this.string06 = string06;
	}

	public String getString07() {
		return string07;
	}

	public void setString07(String string07) {
		this.string07 = string07;
	}

	public String getString08() {
		return string08;
	}

	public void setString08(String string08) {
		this.string08 = string08;
	}

	public String getString09() {
		return string09;
	}

	public void setString09(String string09) {
		this.string09 = string09;
	}

	public Long getLong01() {
		return long01;
	}

	public void setLong01(Long long01) {
		this.long01 = long01;
	}

	public Long getLong02() {
		return long02;
	}

	public void setLong02(Long long02) {
		this.long02 = long02;
	}

	public Long getLong03() {
		return long03;
	}

	public void setLong03(Long long03) {
		this.long03 = long03;
	}

	public Long getLong04() {
		return long04;
	}

	public void setLong04(Long long04) {
		this.long04 = long04;
	}

	public Long getLong05() {
		return long05;
	}

	public void setLong05(Long long05) {
		this.long05 = long05;
	}

	public Long getLong06() {
		return long06;
	}

	public void setLong06(Long long06) {
		this.long06 = long06;
	}

	public Long getLong07() {
		return long07;
	}

	public void setLong07(Long long07) {
		this.long07 = long07;
	}

	public Long getLong08() {
		return long08;
	}

	public void setLong08(Long long08) {
		this.long08 = long08;
	}

	public Long getLong09() {
		return long09;
	}

	public void setLong09(Long long09) {
		this.long09 = long09;
	}

	public String getLob01() {
		return lob01;
	}

	public void setLob01(String lob01) {
		this.lob01 = lob01;
	}

	public String getLob02() {
		return lob02;
	}

	public void setLob02(String lob02) {
		this.lob02 = lob02;
	}

	public String getLob03() {
		return lob03;
	}

	public void setLob03(String lob03) {
		this.lob03 = lob03;
	}

	public String getLob04() {
		return lob04;
	}

	public void setLob04(String lob04) {
		this.lob04 = lob04;
	}

	public String getLob05() {
		return lob05;
	}

	public void setLob05(String lob05) {
		this.lob05 = lob05;
	}

	public String getLob06() {
		return lob06;
	}

	public void setLob06(String lob06) {
		this.lob06 = lob06;
	}

	public String getLob07() {
		return lob07;
	}

	public void setLob07(String lob07) {
		this.lob07 = lob07;
	}

	public String getLob08() {
		return lob08;
	}

	public void setLob08(String lob08) {
		this.lob08 = lob08;
	}

	public String getLob09() {
		return lob09;
	}

	public void setLob09(String lob09) {
		this.lob09 = lob09;
	}
	
	

	
}
