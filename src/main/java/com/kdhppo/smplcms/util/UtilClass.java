package com.kdhppo.smplcms.util;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import jakarta.servlet.http.HttpServletRequest;

public class UtilClass {

	public static String parNvl(HttpServletRequest request, Object p_key, String p_replace){
		String result = "";
		try{
			result = nvl(request.getParameter(nvl(p_key)),p_replace);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public static String parNvl(HttpServletRequest request, Object p_key){
		return parNvl(request, p_key, "");
	}

	public static String attNvl(HttpServletRequest request, Object p_key, String p_replace){
		String result = "";
		try{
			result = nvl(request.getAttribute(nvl(p_key)),p_replace);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public static String attNvl(HttpServletRequest request, Object p_key){
		return attNvl(request, p_key, "");
	}

	public static Object attNvl(HttpServletRequest request, Object p_key, Object p_rep){
		Object result = request.getAttribute(nvl(p_key));
		return (result==null ? p_rep : result);
	}

	public static String nvl(Object pstr){
		if(pstr!=null){
			return pstr.toString();
		}
		return "";
	}

	public static String nvl(Object pstr,String preplace){
		if(pstr!=null){
			String result = pstr.toString();
			if(result.equals("")){
				return preplace;
			}else{
				return result;
			}
		}
		return preplace;
	}

	public static int nvl(Object pstr,int preplace){
		return Integer.parseInt(nvl(pstr,"0"));
	}

	public static long nvl(Object pstr,long preplace){
		return Long.parseLong(nvl(pstr,"0"));
	}

	public static String nvl(String pstr){
		if(pstr!=null){
			return pstr;
		}
		return "";
	}

	public static String nvl(String pstr,String preplace){
		if(pstr!=null){
			String result = pstr;
			if(result.equals("")){
				return preplace;
			}else{
				return result;
			}
		}
		return preplace;
	}

	// BingDecimal 타입을 문자열형으로 반환
	public static String bcToString(Object p_val){
		BigDecimal v_bigNum = p_val==null ? new BigDecimal(0) : (BigDecimal)p_val;
		return v_bigNum.toString();
	}

	// BigInteger 타입을 문자열형으로 반환
	public static String bigIntToString(Object p_val){
		BigInteger v_bigNum = p_val==null ? BigInteger.valueOf(0) : (BigInteger)p_val;
		return v_bigNum.toString();
	}

	/**
	 * lpad 같이 포맷에 해당 포맷문자열을 주면, 본 문자열을 우측에 설정하고서, 그 길이만큼 포맷문자열에서 빼서, 좌측에 붙임.
	 * formatString("1","00") => "01" 반환
	 * @param p_str : 본 문자열.
	 * @param p_format : 포맷 문자열.
	 * @return lpad 같이 값을 반환.
	 */
	public static String formatString(int p_str,String p_format){
		return formatString(String.valueOf(p_str), p_format);
	}

	/**
	 * lpad 같이 포맷에 해당 포맷문자열을 주면, 본 문자열을 우측에 설정하고서, 그 길이만큼 포맷문자열에서 빼서, 좌측에 붙임.
	 * formatString("1","00") => "01" 반환
	 * @param p_str : 본 문자열.
	 * @param p_format : 포맷 문자열.
	 * @return lpad 같이 값을 반환.
	 */
	public static String formatString(String p_str,String p_format){
		p_str = nvl(p_str);
		p_format = nvl(p_format);

		if(p_str.length()>=p_format.length()){
			return p_str;
		}else{
			return p_format.substring(0,p_format.length()-p_str.length())+p_str;
		}
	}

	public static String formatNumber(int p_num){
		NumberFormat nf = NumberFormat.getNumberInstance();
		return nf.format(p_num);
	}

	public static String formatNumber(long p_num){
		NumberFormat nf = NumberFormat.getNumberInstance();
		return nf.format(p_num);
	}

	public static String formatNumber(BigDecimal p_num){
		NumberFormat nf = NumberFormat.getNumberInstance();
		return nf.format(p_num);
	}

	public static String formatNumber(BigInteger p_num){
		NumberFormat nf = NumberFormat.getNumberInstance();
		return nf.format(p_num);
	}

	public static String formatNumber(double p_num){
		NumberFormat nf = NumberFormat.getNumberInstance();
		return nf.format(p_num);
	}

	public static String formatNumber(float p_num){
		NumberFormat nf = NumberFormat.getNumberInstance();
		return nf.format(p_num);
	}

	public static String lpad(String p_format, String p_val){
		if(p_format == null) return p_val;
		if(p_val == null) return "";

		int v_len1 = p_format.length();
		int v_len2 = p_val.length();

		if(v_len1 > v_len2){
			return p_format.substring(0,(v_len1-v_len2))+p_val;
		}else{
			return p_val;
		}
	}

	//str 문자열을, from_charset 문자열셋에서 to_charset문자열 셋으로 변환
	//charset : 8859_1, euc-kr, utf-8, ISO-8859-1, KSC5601
	public static String change_charset(String str, String from_charset, String to_charset) {
        String temp = "";
        try
        {
            temp = new String(str.getBytes(from_charset), to_charset);
        }
        catch (Exception e)
        {}
        return temp;
	}

	public static String to_euckr(String str) {
	    return change_charset(str, "8859_1","euc-kr");
	}

	public static String to_ksc5601(String str) {
	    return change_charset(str, "8859_1","KSC_5601");
	}

	public static String to_8859_1(String str) {
		return change_charset(str, "KSC_5601","8859_1");
	}

	public static String to_utf8(String str) {
		return change_charset(str, "8859_1","utf-8");
	}

	public static void setPaging(Map<String,Object> paramMap, int pageNum, int pageSize) {
		int startNum=0, endNum=0;
		startNum = (pageNum-1) * pageSize;
		endNum = pageSize;
		paramMap.put("START_NUM", startNum);
		paramMap.put("END_NUM", endNum);
	}

	public static int getRandom(int p_start,int p_end){
		Random rnd = new Random();
		if(p_start >= p_end){
			return 0;
		}else{
			return rnd.nextInt(p_end-p_start+1)+p_start;
		}
	}

	//--------------- 시간관련

	// p_format : yyyyMMddHHmmss
	// p_date : 20090301112013
	public static Date getFormatDateObject(String p_date, String p_format){
		try{
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(p_format);
			return formatter.parse(p_date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	// format : yyyyMMddHHmmss
    public static String getForamtDate(String format) {
    	java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(format);
        return simpleDateFormat.format(new Date());
    }

    //getWeekDay("2011-11-11", "yyyy-MM-dd")
    //getWeekDay("20111111", "yyyyMMdd")
    public static String getWeekDay(String p_date, String p_format) {
    	p_date = nvl(p_date).trim();
    	p_format = nvl(p_format).trim();

    	if(p_date.equals(""))return "";
    	if(p_format.equals(""))return "";
    	if(p_date.length()!=p_format.length())return "";

    	Calendar cal = Calendar.getInstance();
    	cal.setTime(getFormatDateObject(p_date,p_format));
        int iDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        String sDayOfWeek = "";
        switch(iDayOfWeek){
        case 1:sDayOfWeek = "일";break;
        case 2:sDayOfWeek = "월";break;
        case 3:sDayOfWeek = "화";break;
        case 4:sDayOfWeek = "수";break;
        case 5:sDayOfWeek = "목";break;
        case 6:sDayOfWeek = "금";break;
        case 7:sDayOfWeek = "토";break;
        }
        return sDayOfWeek;
    }

    public static String getFileExt(String fileName){
    	String result = "";
    	try{
    		fileName = nvl(fileName);

    		int dot_idx = fileName.lastIndexOf(".");

    		if(dot_idx!=-1){
    			result = fileName.substring(dot_idx+1);
    		}

    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return result;
    }

    public static String getFileName(String filePath){
    	String result = filePath;
    	try{
    		filePath = nvl(filePath);

    		int v_idx = filePath.lastIndexOf("/");
    		if(v_idx!=-1){
    			result = filePath.substring(v_idx+1);
    		}else{
    			v_idx = filePath.lastIndexOf("\\");
    			if(v_idx!=-1){
    				result = filePath.substring(v_idx+1);
    			}
    		}

    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return result.trim();
    }

    //UtilClass.inArray("jpg,gif,png","gif") ==> true 반환
    public static boolean inArray(String p_ary_str,String p_str){
    	boolean result = false;

    	p_ary_str = nvl(p_ary_str).trim();

    	if(!p_ary_str.equals("")){
    		String[] p_ary = p_ary_str.split(",");

    		for(int i=0;i<p_ary.length;i++){
    			if(p_ary[i].equals(p_str)){
    				result = true;
    				break;
    			}
    		}
    	}

    	return result;
    }

	//--------------- path
	public static String getServletPath(HttpServletRequest request){
		String result = "";
		try{
			result = request.getServletPath() + "?" + request.getQueryString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public static String encodeHtmlChar(Object str){
		return encodeHtmlChar(str==null ? "" : str.toString());
	}
	public static String encodeHtmlChar(String str){
		str = (str==null) ? "" : str.toString();
		//str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll(" ", "&nbsp;");

		str = str.replaceAll("&lt;br&nbsp;/&gt;", "<br />");
		str = str.replaceAll("'", "&#39;");

		return str;
	}

	//chkNum 이 startNum 과 endNum 사이에 있으면 true, 사이에 없으면 false
	public static boolean chkNumScope(int chkNum, int startNum, int endNum){
		if(startNum<=chkNum && chkNum<=endNum){
			return true;
		}else{
			return false;
		}
	}
	public static boolean chkNumScope(String chkNumStr, int startNum, int endNum){
		int chkNum = Integer.parseInt(chkNumStr);
		return chkNumScope(chkNum,startNum,endNum);
	}

	public static String nl2br(String pstr){
		return nvl(pstr).replace("\n", "<br />");
	}

	public static String cutString(String p_str,int p_len,String p_suffix){
		p_str = UtilClass.nvl(p_str);
		if(p_str.length()>p_len){
			p_str = p_str.substring(0,p_len)+p_suffix;
		}
		return p_str;
	}

	public static String cutString(Object p_str,int p_len,String p_suffix){
		return cutString(UtilClass.nvl(p_str),p_len,p_suffix);
	}

	public static String cutString(Object p_str,int p_len){
		return cutString(UtilClass.nvl(p_str),p_len,"..");
	}

	public static String cutString(String p_str,int p_len){
		return cutString(p_str,p_len,"..");
	}

	public static String repeat(String p_str,int p_len){
		StringBuffer sbuf = new StringBuffer();

		for(int i=0;i<p_len;i++){
			sbuf.append(p_str);
		}

		return sbuf.toString();
	}

	public static long toLong(String p_num){
		p_num = nvl(p_num,"0").replaceAll(",", "").trim();
		long l_num = 0;
		if(p_num.indexOf(".")!=-1){
			l_num = Double.valueOf(p_num).longValue();
		}else{
			l_num = Long.parseLong(p_num);
		}
		return l_num;
	}

	public static int toInt(String p_num){
		p_num = nvl(p_num,"0").replaceAll(",", "").trim();
		int i_num = 0;
		if(p_num.indexOf(".")!=-1){
			i_num = Float.valueOf(p_num).intValue();
		}else{
			i_num = Integer.parseInt(p_num);
		}
		return i_num;
	}

	public static float toFloat(String p_num){
		p_num = p_num==null ? "0.0" : p_num.replaceAll(",", "").trim();
		return (float)Float.parseFloat(p_num);
	}

	public static float toFloat(Float p_num){
		p_num = p_num==null ? 0.0f : p_num;
		return (float)p_num;
	}

	public static double toDouble(String p_num){
		p_num = p_num==null ? "0.0" : p_num.replaceAll(",", "").trim();
		return (double)Double.parseDouble(p_num);
	}
	public static String substring(String pstr, int start)	{
		return substring(pstr, start, nvl(pstr).length());
	}
	public static String substring(String pstr, int start, int end)	{
		pstr = nvl(pstr);
		String result = "";
		int strlen = pstr.length();
		if(strlen < start) return result;
		if(strlen < end) end = strlen;
		result = pstr.substring(Math.max(start,0), Math.max(end,0));
		return result;
	}

	public static String rpad(String p_format, String p_val){
		if(p_format == null) return p_val;
		if(p_val == null) return "";

		int v_len1 = p_format.length();
		int v_len2 = p_val.length();

		if(v_len1 > v_len2){
			return p_val+p_format.substring(p_val.length());
		}else{
			return p_val;
		}
	}

	public static boolean isEmpty(String v_val){
		if( nvl(v_val).trim().equals("") ){
			return true;
		}else{
			return false;
		}
	}

	public static boolean isEmpty(String[] v_arr){
		if(v_arr==null || v_arr.length == 0) {
			return true;
		}else{
			return false;
		}
	}

	public static boolean isEmpty(Object v_val){
		if( v_val==null || v_val.toString().trim().equals("") ){
			return true;
		}else{
			return false;
		}
	}

	public static boolean isNotEmpty(String v_val){
		return !isEmpty(v_val);
	}

	public static boolean isNotEmpty(String[] v_arr){
		return !isEmpty(v_arr);
	}

	public static boolean isNotEmpty(Object v_val){
		return !isEmpty(v_val);
	}

	public static String wrap(String p_prefix, String p_cont, String p_suffix){
		String result = p_cont;

		p_prefix = nvl(p_prefix);
		p_suffix = nvl(p_suffix);

		if(!nvl(p_cont).equals("")){
			result = p_prefix + p_cont + p_suffix;
		}

		return result;
	}

	public static String getRandFileName(String orgFileName){
		if(orgFileName==null || nvl(orgFileName).trim().equals("")){
			return "";
		}else{
			return getForamtDate("yyyyMMddHHmmss") + String.valueOf(getRandom(1000, 9999)) + wrap(".", getFileExt(orgFileName), "");
		}
	}

	/**
	 * 해당 파일명에서 fileExts("png,jpg,gif")에 확장자가 포함되어있으면, true, 안되어있으면 false
	 * @param orgFileName
	 * @param fileExts
	 * @return
	 */
	public static boolean checkFileExt(String orgFileName, String fileExts){
		boolean result = false;

		if( !nvl(orgFileName).trim().equals("") && !nvl(fileExts).trim().equals("") ){
			String fileExt = UtilClass.getFileExt(nvl(orgFileName).trim().toLowerCase());
			if( (","+fileExts+",").indexOf(","+fileExt+",") != -1){
				result = true;
			}
		}

		return result;
	}

	/**
	 * 해당 년월의 마지막 일자 구하기
	 * @param yearmon: 201401, 2014-01, 2014.01, ...
	 * @return
	 */
	public static int getCalLastDayMonth(String yearmon){
		yearmon = nvl(yearmon).trim();

		Calendar cal = Calendar.getInstance();

		yearmon = yearmon.replaceAll("\\.", "");
		yearmon = yearmon.replaceAll("/", "");
		yearmon = yearmon.replaceAll("-", "");
		yearmon = yearmon.replaceAll(" ", "");

		int year = Integer.parseInt(substring(yearmon, 0, 4));
		int mon = Integer.parseInt(substring(yearmon, 4, 6));

		cal.set(year, mon-1, 1);

		return cal.getActualMaximum(Calendar.DATE);
	}

	public static String getCalLastDateMonth(String yearmon){
		yearmon = nvl(yearmon).trim();
		yearmon = yearmon.length() > 6 ? yearmon.substring(0, 6) : yearmon;
		int lastDay = getCalLastDayMonth(yearmon);
		return yearmon+formatString(lastDay, "00");
	}

    /**
     * 요일 번호반환
     * @param p_date
     * @param p_format
     * @return: 일(1),월(2),화(3),수(4),목(5),금(6),토(7)
     */
    public static int getWeekDayNum(String p_date, String p_format) {
    	p_date = nvl(p_date).trim();
    	p_format = nvl(p_format).trim();

    	if(p_date.equals(""))return 0;
    	if(p_format.equals(""))return 0;
    	if(p_date.length()!=p_format.length())return 0;

    	Calendar cal = Calendar.getInstance();
    	cal.setTime(getFormatDateObject(p_date,p_format));
    	return cal.get(Calendar.DAY_OF_WEEK);
    }

	/**
	 * baseStr 이 compareStr 보다 큰 문자열인지 여부. (baseStr > compareStr)
	 * 날짜 문자열 비교 등. "20180301", "20180302" ==> false 반환.
	 * @param baseStr
	 * @param compareStr
	 * @return
	 */
	public static boolean isGreat(String baseStr, String compareStr) {
		baseStr = nvl(baseStr);
		compareStr = nvl(compareStr);

		int compare = baseStr.compareTo(compareStr);

		if( compare > 0 ){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * baseStr 이 compareStr 보다 크거나 같은 문자열인지 여부. (baseStr >= compareStr)
	 * 날짜 문자열 비교 등. "20180301", "20180302" ==> false 반환.
	 * @param baseStr
	 * @param compareStr
	 * @return
	 */
	public static boolean isGreatOrEqual(String baseStr, String compareStr) {
		baseStr = nvl(baseStr);
		compareStr = nvl(compareStr);

		if( isGreat(baseStr, compareStr) || baseStr.equals(compareStr) ){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * baseStr 이 compareStr 보다 작은 문자열인지 여부. (baseStr < compareStr)
	 * 날짜 문자열 비교 등. "20180301", "20180302" ==> true 반환.
	 * @param baseStr
	 * @param compareStr
	 * @return
	 */
	public static boolean isSmall(String baseStr, String compareStr) {
		return !isGreat(baseStr, compareStr) && !nvl(baseStr).equals(compareStr);
	}

	/**
	 * baseStr 이 compareStr 보다 크거나 같은 문자열인지 여부. (baseStr <= compareStr)
	 * 날짜 문자열 비교 등. "20180301", "20180302" ==> false 반환.
	 * @param baseStr
	 * @param compareStr
	 * @return
	 */
	public static boolean isSmallOrEqual(String baseStr, String compareStr) {
		baseStr = nvl(baseStr);
		compareStr = nvl(compareStr);

		if( isSmall(baseStr, compareStr) || baseStr.equals(compareStr) ){
			return true;
		} else {
			return false;
		}
	}

	public static String[] toArray(List<String> list){
		String[] arr = null;

		if(list!=null){
			int len = list.size();
			arr = new String[len];
			for(int i=0;i<len;i++){
				arr[i] = list.get(i);
			}
		}

		return arr;
	}

	public static String right(String str, int len) {
		str = str==null ? "" : str;

		if(str.equals("") || len < 0) return "";

		int len2 = str.length();
		return str.substring(Math.max(len2 - len, 0));
	}

	public static boolean isBetween(String str1, String str2, String str3) {
		if( isGreatOrEqual(str2, str1) && isGreatOrEqual(str3, str2) ){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 특정 날짜에 대하여 요일을 구함(일 ~ 토)
	 * @param date			: 20190101
	 * @return
	 */
	public static String weekNameKor(String date) {
		return weekNameKor(date, "yyyyMMdd");
	}

	/**
	 * 특정 날짜에 대하여 요일을 구함(일 ~ 토)
	 * @param date					: 20190101
	 * @param dateFormat		: yyyyMMdd
	 * @return
	 * @throws Exception
	 */
	public static String weekNameKor(String date, String dateFormat) {
		String day = "" ;

		date = nvl(date).trim();
		dateFormat = nvl(dateFormat).trim();

		if(date.equals("") || dateFormat.equals("")) {
			return "";
		}

		try {
			SimpleDateFormat dateFormatObj = new SimpleDateFormat(dateFormat);
			Date nDate = dateFormatObj.parse(date);

			Calendar cal = Calendar.getInstance();
			cal.setTime(nDate);

			int dayNum = cal.get(Calendar.DAY_OF_WEEK) ;

			switch(dayNum){
			case 1:
				day = "일";
				break ;
			case 2:
				day = "월";
				break ;
			case 3:
				day = "화";
				break ;
			case 4:
				day = "수";
				break ;
			case 5:
				day = "목";
				break ;
			case 6:
				day = "금";
				break ;
			case 7:
				day = "토";
				break ;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return day ;
	}

	/**
	 * 두개의 경로를 합침.
	 * @param path1
	 * @param path2
	 * @return
	 */
	public static String combinePath(String path1, String path2) {
		String result = null;
		String joinStr = File.separator;

		path1 = nvl(path1).trim();
		path2 = nvl(path2).trim();

		if(path1.equals("")) return path2;
		if(path2.equals("")) return path1;

		if(path1.indexOf("\\")!=-1 || path2.indexOf("\\")!=-1) {
			joinStr = "\\";
		} else if(path1.indexOf("/")!=-1 || path2.indexOf("/")!=-1) {
			joinStr = "/";
		}

		if(!path1.endsWith(joinStr)) {
			path1 += joinStr;
		}

		if(path2.startsWith(joinStr)) {
			path2 = path2.substring(joinStr.length());
		}

		result = path1 + path2;
		result = result.replaceAll("\\\\\\\\", "\\\\");
		result = result.replaceAll("//", "/");

		return result;
	}

	public static String arr2str(String[] arr) {
		if(arr==null) {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		for(int i=0;i<arr.length;i++) {
			sb.append("\n *** "+i+" : "+arr[i]);
		}
		return sb.toString();
	}

	public static String[] getArrVal(Object obj) {
		return obj instanceof String[] ? (String[])obj : new String[] {(String)obj};
	}

	public static String[] getSeqs( HttpServletRequest request ) {
		String seqs = parNvl(request, "seqs").trim();
		String[] seqsArr = seqs.split(",");
		return seqsArr;
	}

	/**
	 * 둘중에 큰 숫자 반환
	 * @param n1 : 숫자1
	 * @param n2 : 숫자2
	 * @return : 큰 숫자 반환
	 */
	public static int max(int n1, int n2){
		if(n1 > n2) {
			return n1;
		} else {
			return n2;
		}
	}

	/**
	 * 둘중에 작은 숫자 반환
	 * @param n1 : 숫자1
	 * @param n2 : 숫자2
	 * @return : 작은 숫자 반환
	 */
	public static int min(int n1, int n2){
		if(n1 > n2) {
			return n2;
		} else {
			return n1;
		}
	}

	/**
	 * 둘중에 큰 숫자 반환
	 * @param n1 : 숫자1
	 * @param n2 : 숫자2
	 * @return : 큰 숫자 반환
	 */
	public static long max(long n1, long n2){
		if(n1 > n2) {
			return n1;
		} else {
			return n2;
		}
	}

	/**
	 * 둘중에 작은 숫자 반환
	 * @param n1 : 숫자1
	 * @param n2 : 숫자2
	 * @return : 작은 숫자 반환
	 */
	public static long min(long n1, long n2){
		if(n1 > n2) {
			return n2;
		} else {
			return n1;
		}
	}

	/**
	 * 둘중에 큰 숫자 반환
	 * @param n1 : 숫자1
	 * @param n2 : 숫자2
	 * @return : 큰 숫자 반환
	 */
	public static float max(float n1, float n2){
		if(n1 > n2) {
			return n1;
		} else {
			return n2;
		}
	}

	/**
	 * 둘중에 작은 숫자 반환
	 * @param n1 : 숫자1
	 * @param n2 : 숫자2
	 * @return : 작은 숫자 반환
	 */
	public static float min(float n1, float n2){
		if(n1 > n2) {
			return n2;
		} else {
			return n1;
		}
	}

	/**
	 * 둘중에 큰 숫자 반환
	 * @param n1 : 숫자1
	 * @param n2 : 숫자2
	 * @return : 큰 숫자 반환
	 */
	public static double max(double n1, double n2){
		if(n1 > n2) {
			return n1;
		} else {
			return n2;
		}
	}

	/**
	 * 둘중에 작은 숫자 반환
	 * @param n1 : 숫자1
	 * @param n2 : 숫자2
	 * @return : 작은 숫자 반환
	 */
	public static double min(double n1, double n2){
		if(n1 > n2) {
			return n2;
		} else {
			return n1;
		}
	}

}