package tdog.lib.crawler.table;

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
	
	private String string1;
	private String string2;
	private String string3;
	private String string4;
	private String string5;
	private String string6;
	private String string7;
	private String string8;
	private String string9;
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
	
	private Long long1;
	private Long long2;
	private Long long3;
	private Long long4;
	private Long long5;
	private Long long6;
	private Long long7;
	private Long long8;
	private Long long9;
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
	
	@Lob private String lob1;
	@Lob private String lob2;
	@Lob private String lob3;
	@Lob private String lob4;
	@Lob private String lob5;
	@Lob private String lob6;
	@Lob private String lob7;
	@Lob private String lob8;
	@Lob private String lob9;
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
	public String getString1() {
		return string1;
	}
	public void setString1(String string1) {
		this.string1 = string1;
	}
	public String getString2() {
		return string2;
	}
	public void setString2(String string2) {
		this.string2 = string2;
	}
	public String getString3() {
		return string3;
	}
	public void setString3(String string3) {
		this.string3 = string3;
	}
	public String getString4() {
		return string4;
	}
	public void setString4(String string4) {
		this.string4 = string4;
	}
	public String getString5() {
		return string5;
	}
	public void setString5(String string5) {
		this.string5 = string5;
	}
	public String getString6() {
		return string6;
	}
	public void setString6(String string6) {
		this.string6 = string6;
	}
	public String getString7() {
		return string7;
	}
	public void setString7(String string7) {
		this.string7 = string7;
	}
	public String getString8() {
		return string8;
	}
	public void setString8(String string8) {
		this.string8 = string8;
	}
	public String getString9() {
		return string9;
	}
	public void setString9(String string9) {
		this.string9 = string9;
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
	public Long getLong1() {
		return long1;
	}
	public void setLong1(Long long1) {
		this.long1 = long1;
	}
	public Long getLong2() {
		return long2;
	}
	public void setLong2(Long long2) {
		this.long2 = long2;
	}
	public Long getLong3() {
		return long3;
	}
	public void setLong3(Long long3) {
		this.long3 = long3;
	}
	public Long getLong4() {
		return long4;
	}
	public void setLong4(Long long4) {
		this.long4 = long4;
	}
	public Long getLong5() {
		return long5;
	}
	public void setLong5(Long long5) {
		this.long5 = long5;
	}
	public Long getLong6() {
		return long6;
	}
	public void setLong6(Long long6) {
		this.long6 = long6;
	}
	public Long getLong7() {
		return long7;
	}
	public void setLong7(Long long7) {
		this.long7 = long7;
	}
	public Long getLong8() {
		return long8;
	}
	public void setLong8(Long long8) {
		this.long8 = long8;
	}
	public Long getLong9() {
		return long9;
	}
	public void setLong9(Long long9) {
		this.long9 = long9;
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
	public String getLob1() {
		return lob1;
	}
	public void setLob1(String lob1) {
		this.lob1 = lob1;
	}
	public String getLob2() {
		return lob2;
	}
	public void setLob2(String lob2) {
		this.lob2 = lob2;
	}
	public String getLob3() {
		return lob3;
	}
	public void setLob3(String lob3) {
		this.lob3 = lob3;
	}
	public String getLob4() {
		return lob4;
	}
	public void setLob4(String lob4) {
		this.lob4 = lob4;
	}
	public String getLob5() {
		return lob5;
	}
	public void setLob5(String lob5) {
		this.lob5 = lob5;
	}
	public String getLob6() {
		return lob6;
	}
	public void setLob6(String lob6) {
		this.lob6 = lob6;
	}
	public String getLob7() {
		return lob7;
	}
	public void setLob7(String lob7) {
		this.lob7 = lob7;
	}
	public String getLob8() {
		return lob8;
	}
	public void setLob8(String lob8) {
		this.lob8 = lob8;
	}
	public String getLob9() {
		return lob9;
	}
	public void setLob9(String lob9) {
		this.lob9 = lob9;
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
	

	
}
