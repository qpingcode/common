package me.qping.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Designed For
 *
 * @auther:you
 * @date:2018/6/20 上午12:04
 */

public class DateUtil {

	private static final DateTimeFormatter	df						= DateTimeFormatter
			.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static final SimpleDateFormat	sdf						= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat	sdf_date				= new SimpleDateFormat("yyyy-MM-dd");

	private static final DateTimeFormatter	date_formatter			= DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private static final DateTimeFormatter	dateTimeFormatter_day	= DateTimeFormatter.ofPattern("yyyyMMdd");

	private static final DateTimeFormatter	dateTimeFormatter_mili	= DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

	private static final SimpleDateFormat	dateFormat_T			= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	/**
	 *
	 * @Author: mayoucai
	 * @Description: 判断time是否是当天
	 * @Param: time
	 * @Date:2018/6/20 上午6:59
	 *
	 */
	public static boolean isDateToday(String time) {
		// DateTimeFormatter dtf =
		// DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		// LocalDateTime localTime = LocalDateTime.parse(time, dtf);
		ZonedDateTime zonedDateTime = ZonedDateTime.parse(time);
		LocalDateTime localTime = zonedDateTime.toLocalDateTime();

		LocalDateTime startTime = LocalDate.now().atTime(0, 0, 0);
		LocalDateTime endTime = LocalDate.now().atTime(23, 59, 59);
		// 如果小于今天的开始日期
		// 如果大于今天的开始日期，小于今天的结束日期
		return localTime.isAfter(startTime) && localTime.isBefore(endTime);
	}

	/**
	 *
	 * @Author: mayoucai
	 * @Description: 判断time是否是当天
	 * @Param: time
	 * @Date:2018/6/20 上午6:59
	 *
	 */
	public static boolean isDateToday(LocalDateTime localTime) {
		if (localTime == null)
			return false;
		LocalDateTime startTime = LocalDate.now().atTime(0, 0, 0);
		LocalDateTime endTime = LocalDate.now().atTime(23, 59, 59);
		// 如果小于今天的开始日期
		// 如果大于今天的开始日期，小于今天的结束日期
		return localTime.isAfter(startTime) && localTime.isBefore(endTime);
	}

	/**
	 *
	 * @Author: mayoucai
	 * @Description: 转换string为localDateTime
	 * @Param:time
	 * @Date:2018/6/20 上午7:01
	 *
	 */
	public static LocalDateTime parseStringToDate(String time) {
		return LocalDateTime.parse(time, df);
	}

	/**
	 *
	 * @Description: str转成Localdate
	 * @Param:
	 * @Return:
	 * @Date: 2018/7/14 下午11:56
	 * @Author: mayoucai
	 *
	 */
	public static LocalDate parseStringToLocalDate(String time) {
		return LocalDate.parse(time, dateTimeFormatter_day);
	}

	/**
	 *
	 * @Author: mayoucai
	 * @Description: 把日期转为string
	 * @Param:time
	 * @Date:2018/6/20 上午7:03
	 *
	 */
	public static String parseDateToString(LocalDateTime time) {
		return df.format(time);
	}

	public static String longParseToDateString(long time) {
		return sdf.format(time);
	}

	public static final String parseDateWithT(Date date) {
		return dateFormat_T.format(date);
	}

	/**
	 * 获取yyyyMMddHHmmssSSS格式字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String parseDateToMiliTimeString(LocalDateTime time) {
		return dateTimeFormatter_mili.format(time);
	}

	/**
	 *
	 * @Description: 把localDate转string
	 * @Param:
	 * @Return:
	 * @Date: 2018/8/23 下午4:30
	 * @Author: mayoucai
	 *
	 */
	public static String parseDateToString(LocalDate date) {
		return date_formatter.format(date);
	}

	/**
	 * 将指定日期转化为时间戳格式
	 * 
	 * @param target
	 *            被转化的日期，如果传null默认为当前时间
	 * @param isLinuxTimeStamp
	 *            是否为linux格式
	 * @return 转化后的时间戳
	 */
	public static final Long dateToTimeStamp(Date target, Boolean isLinuxTimeStamp) {
		target = target == null ? new Date() : target;
		Long result = isLinuxTimeStamp ? (target.getTime() / 1000) : target.getTime();
		return result;
	}

	/**
	 * 将指定时间戳转化为日期对象
	 * 
	 * @param timeStamp
	 *            被转化的时间戳对象
	 * @param isLinuxTimeStamp
	 *            是否为linux格式
	 * @return 转化后的日期对象
	 */
	public static final Date timeStampToDate(Long timeStamp, Boolean isLinuxTimeStamp) {
		timeStamp = isLinuxTimeStamp ? timeStamp * 1000 : timeStamp;
		java.sql.Timestamp ts = new java.sql.Timestamp(timeStamp);
		Date result = ts;
		return result;
	}

	/**
	 * 将指定时间戳转化为日期字符串对象
	 * 
	 * @param timeStamp
	 *            被转化的时间戳对象
	 * @param fmt
	 *            日期格式化字符串
	 * @param isLinuxTimeStamp
	 *            是否为linux格式
	 * @return 转化后的日期对象
	 */
	public static final String timeStampToStr(Long timeStamp, String fmt, Boolean isLinuxTimeStamp) {
		timeStamp = isLinuxTimeStamp ? timeStamp * 1000 : timeStamp;
		java.sql.Timestamp ts = new java.sql.Timestamp(timeStamp);
		Date result = ts;
		return dateToString(result, fmt);
	}

	/**
	 * 将日期字符串格式，转换为时间戳格式
	 * 
	 * @param strDate
	 *            日期字符串
	 * @param fmt
	 *            字符串格式
	 * @param isLinuxTimeStamp
	 *            是否为linux时间戳
	 * @return 日期格式时间戳
	 */
	public static final Long strToTimeStamp(String strDate, String fmt, Boolean isLinuxTimeStamp) {
		Date target = stringToDate(strDate, fmt);
		return dateToTimeStamp(target, isLinuxTimeStamp);
	}

	/**
	 * 将指定日期转换成字符串
	 * 
	 * @param target
	 *            目标日期,如果日期为空默认为当前日期
	 * @param fmt
	 *            格式化字符串
	 * @return 格式化后的日期字符串
	 */
	public static final String dateToString(Date target, String fmt) {
		target = target == null ? new Date() : target;
		SimpleDateFormat format = new SimpleDateFormat(fmt);
		String str = format.format(target);
		return str;
	}

	/**
	 * 将日期转换为Long类型
	 * 
	 * @param target
	 *            目标日期类型,如果日期为空默认为当前日期
	 * @param fmt
	 *            格式化字符串，年月日不能有符号分割
	 * @return 转换后的Long类型
	 */
	public static final Long dateToLong(Date target, String fmt) {
		target = target == null ? new Date() : target;
		SimpleDateFormat format = new SimpleDateFormat(fmt);
		String str = format.format(target);
		return Long.parseLong(str);
	}

	/**
	 * 将字符串转换为Date日期对象
	 * <p>
	 * 
	 * <pre>
	 * 例：str："2015-08-09" , fmt："yyyy-MM-dd"
	 * </pre>
	 * 
	 * @param str
	 *            日期字符串
	 * @param fmt
	 *            该日期字符串的格式
	 * @return 日期对象Date类型
	 */
	public static final Date stringToDate(String str, String fmt) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(fmt);
			Date date = format.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将字符串转换为字符串形式日期对象
	 * <p>
	 * 
	 * <pre>
	 * 例：str："2015-08-09" , fmt："yyyy-MM-dd"
	 * </pre>
	 * 
	 * @param str
	 *            日期字符串
	 * @param sourcefmt
	 *            源字符串日期的格式
	 * @param targetFmt
	 *            目标字符串日期格式
	 * @return 日期对象Date类型
	 * @throws ParseException
	 */
	public static final String stringFormat(String str, String sourcefmt, String targetFmt) {
		Date date = stringToDate(str, sourcefmt);
		return dateToString(date, targetFmt);
	}

	/**
	 * 将指定日期加上指定的单位时间，并返回日期对象
	 * 
	 * @param target
	 *            指定日期，如果传null默认为当前系统时间
	 * @param calendarValue
	 *            被加上的单位时间，int类型枚举，参见：java.util.Calendar
	 *            <p>
	 * 
	 *            <pre>
	 * 	例：
	 * 	Calendar.YEAR：指定的年数
	 * 	Calendar.MONTH：指定的月数
	 * 	Calendar.DAY_OF_MONTH : 指定的天数
	 * 	Calendar.HOUR_OF_DAY：指定的小时数
	 * 	Calendar.MINUTE：指定的分钟数
	 * 	Calendar.MILLISECOND：指定的秒数
	 *            </pre>
	 * 
	 * @param times
	 *            单位时间值
	 * @return 操作后的日期对象
	 */
	public static final Date addTime(Date target, int calendarValue, Integer times) {
		target = target == null ? new Date() : target;
		Calendar calendarDate = Calendar.getInstance();
		calendarDate.setTime(target);
		calendarDate.add(calendarValue, times);
		return calendarDate.getTime();
	}

	/**
	 * 将指定日期减去指定的单位时间，并返回日期对象
	 * 
	 * @param target
	 *            指定日期，如果传null默认为当前系统时间
	 * @param calendarValue
	 *            被减去的单位时间，int类型枚举，参见：java.util.Calendar
	 *            <p>
	 * 
	 *            <pre>
	 * 	例：
	 * 	Calendar.YEAR：指定的年数
	 * 	Calendar.MONTH：指定的月数
	 * 	Calendar.DAY_OF_MONTH : 指定的天数
	 * 	Calendar.HOUR_OF_DAY：指定的小时数
	 * 	Calendar.MINUTE：指定的分钟数
	 * 	Calendar.SECOND：指定的秒数
	 *            </pre>
	 * 
	 * @param times
	 *            单位时间值
	 * @return 操作后的日期对象
	 */
	public static final Date cutTime(Date target, int calendarValue, Integer times) {
		target = target == null ? new Date() : target;
		Calendar calendarDate = Calendar.getInstance();
		calendarDate.setTime(target);
		calendarDate.add(calendarValue, -times);
		return calendarDate.getTime();
	}

	private DateUtil() {

	}

	public static Date getDateBegin(Date date) {
		Calendar calendarDate = Calendar.getInstance();
		calendarDate.setTime(date);
		calendarDate.set(Calendar.HOUR_OF_DAY, 0);
		calendarDate.set(Calendar.MINUTE, 0);
		calendarDate.set(Calendar.SECOND, 0);
		calendarDate.set(Calendar.MILLISECOND, 0);
		return calendarDate.getTime();
	}

	public static Date getDateEnd(Date date) {
		Calendar calendarDate = Calendar.getInstance();
		calendarDate.setTime(date);
		calendarDate.set(Calendar.HOUR_OF_DAY, 23);
		calendarDate.set(Calendar.MINUTE, 59);
		calendarDate.set(Calendar.SECOND, 59);
		calendarDate.set(Calendar.MILLISECOND, 999);
		return calendarDate.getTime();
	}

	public static String getCNWeek(Date date, String prefix) {
		if (prefix == null) {
			prefix = "周";
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int w = c.get(Calendar.DAY_OF_WEEK);
		switch (w) {
			case 1:
				return prefix + "日";
			case 2:
				return prefix + "一";
			case 3:
				return prefix + "二";
			case 4:
				return prefix + "三";
			case 5:
				return prefix + "四";
			case 6:
				return prefix + "五";
			case 7:
				return prefix + "六";
		}

		return "";
	}

	public static Date getBirthdayByIdcard(String idcard){
        if(idcard == null || idcard.length() != 18){
            throw new RuntimeException("身份证号不能为空且该方法只支持18位身份证：" + idcard);
        }
        String birthString = idcard.substring(6, 14);
        try {
            Date birthDate = new SimpleDateFormat("yyyyMMdd").parse(birthString);
            return birthDate;
        } catch (ParseException e) {
            throw new RuntimeException("身份证号中生日格式错误：" + birthString);
        }
    }

	public static int getAgeByIdcard(String idcard){
       return getAgeByBirthday(getBirthdayByIdcard(idcard));
    }

	/**
	 * 根据生日获取年龄
	 * 
	 * @param birthday
	 * @return
	 */
	public static int getAgeByBirthday(Date birthday) {
		int age = 0;
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthday)) { // 出生日期晚于当前时间，无法计算
			throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR); // 当前年份
		int monthNow = cal.get(Calendar.MONTH); // 当前月份
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); // 当前日期
		cal.setTime(birthday);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		age = yearNow - yearBirth; // 计算整岁数
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth)
					age--;// 当前日期在生日之前，年龄减一
			} else {
				age--;// 当前月份在生日之前，年龄减一
			}
		}
		return age;

	}

    public static int getAgeByBirthday(Date birthday, Date cardFillingTime) {
        int age = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(cardFillingTime);

        if (cal.before(birthday)) { // 出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR); // 当前年份
        int monthNow = cal.get(Calendar.MONTH); // 当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); // 当前日期
        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth; // 计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth)
                    age--;// 当前日期在生日之前，年龄减一
            } else {
                age--;// 当前月份在生日之前，年龄减一
            }
        }
        return age;
    }


	// 根据年龄获取生日
	public static Date getBrithdatByAge(Integer age) {
		Calendar calendar = Calendar.getInstance();

		int yearNow = calendar.get(Calendar.YEAR);
		int yearBrith = yearNow - age;
		String birthdayStr = yearBrith + "01-01";
		return stringToDate(birthdayStr, "yyyy-MM-dd");
	}

	public static final String convertGMTToStr(String str, String sourcefmt, String targetFmt) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(sourcefmt);
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date date = null;
		try {
			date = dateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateToString(date, targetFmt);
	}

	public static final String convertHbaseTimeToStr(String dateStr, String targetFmt) {
		Date date = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
		try {
			Date dateTmp = dateFormat.parse(dateStr);
			SimpleDateFormat df1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
			date = df1.parse(dateTmp.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateToString(date, targetFmt);
	}

	public static String calcDateInterval(Date start) {
		Date end = new Date();
		long ms = end.getTime() - start.getTime();
		double s = Double.valueOf(ms) / 1000;
		return s + "s";
	}

	public static List<String> getBetweenDates(String dateType, String start, String end, int interval) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dBegin = sdf.parse(start);
			Date dEnd = sdf.parse(end);
			return findDates(dateType, dBegin, dEnd, interval);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<String> findDates(String dateType, Date dBegin, Date dEnd, int interval) throws Exception {
		List<String> listDate = new ArrayList<>();
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();

		SimpleDateFormat sdf = null;
		if (dateType.equals("M")) {
			sdf = new SimpleDateFormat("yyyy-MM");
			calEnd.setTime(dEnd);
		} else if (dateType.equals("D")) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			calEnd.setTime(dEnd);
		} else if (dateType.equals("H")) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			calEnd.setTime(sdf.parse(new SimpleDateFormat("yyyy-MM-dd").format(dEnd)+" 23:59"));
		} else if (dateType.equals("MI")) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			calEnd.setTime(sdf.parse(new SimpleDateFormat("yyyy-MM-dd").format(dEnd)+" 23:59"));
		}

		listDate.add(sdf.format(calBegin.getTime()));
		while (sdf.format(calEnd.getTime()).compareTo(sdf.format(calBegin.getTime())) >= 0) {
			switch (dateType) {
				case "M":
					calBegin.add(Calendar.MONTH, interval);
					break;
				case "D":
					calBegin.add(Calendar.DAY_OF_YEAR, interval);
					break;
				case "H":
					calBegin.add(Calendar.HOUR, interval);
					break;
				case "MI":
					calBegin.add(Calendar.MINUTE, interval);
					break;
			}
			if (sdf.format(calEnd.getTime()).compareTo(sdf.format(calBegin.getTime())) < 0) {
				break;
			}
			listDate.add(sdf.format(calBegin.getTime()));
		}
		return listDate;
	}

	/**
	 *
	 * @Description: 获取offset天的日期 当offset为正时，表示超前日期，当offset为负时，表示滞后日期
	 * @Param:
	 * @Return:
	 * @Date: 2019/12/9 下午5:11
	 * @Author: mayoucai
	 *
	 */
	public static Date getOffsetDate(Date date, Integer offset) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, offset);
		return calendar.getTime();
	}

	/**
	 * 获取两个时间间隔天数
	 * 
	 * @param startDate
	 * @param endDate
	 *            停止时间为null时 默认取当前日期
	 * @return
	 */
	public static int getDateInterval(Date startDate, Date endDate) {
		if (startDate == null) {
			return 0;
		}
		if (endDate == null) {
			endDate = new Date();
		}
		// 获取相差的天数
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		long timeInMillis1 = calendar.getTimeInMillis();
		calendar.setTime(endDate);
		long timeInMillis2 = calendar.getTimeInMillis();
		long betweenDays = (timeInMillis2 - timeInMillis1) / (1000L * 3600L * 24L);
		return (int) betweenDays;
	}

	public static void main(String[] args) {
		// System.out.println(convertGMTToStr("2013-01-31T22:17:14.000Z",
		// "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd"));
		// System.out.println(convertHbaseTimeToStr("2016-11-02T15:53:06+08:00",
		// "yyyy-MM-dd"));
		// System.out.println(convertGMTToStr("2013-01-31T22:17:14.000Z","yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
		// "yyyy-MM-dd"));
		// System.out.println(convertHbaseTimeToStr("2016-11-02T15:53:06+08:00",
		// "yyyy-MM-dd"));

		String start = "2016-03-01";
		String end = "2016-03-02";
		// List<String> list = getBetweenDates("H", start, end, 1);

		 List<String> list = DateUtil.getBetweenDates("MI", "2019-09-04",
		 "2019-09-04", 30);
//		List<String> list = DateUtil.getBetweenDates("D",
//				sdf.format(DateUtil.addTime(new Date(), Calendar.DAY_OF_MONTH, 1)).substring(0, 10),
//				sdf.format(DateUtil.addTime(new Date(), Calendar.DAY_OF_MONTH, 3)).substring(0, 10), 1);
		// List<String> list =DateUtil.getBetweenDates("M", "2019-06-07",
		// "2019-09-05", 1);
		for (String str : list) {
			System.out.println(str);
		}
	}

    public static void each(Date begin, Date end, EachOrder order, int calendarUnit, int intervalTime, EachCallBack eachCallBack) {

	    if(order  == null || begin == null || end == null || eachCallBack == null || intervalTime == 0 || calendarUnit == 0){
	        return;
        }

        if(begin.compareTo(end) >=0 ){
	        throw new RuntimeException("开始时间必须小于结束时间");
        }

        Calendar calendar = Calendar.getInstance();
        intervalTime = Math.abs(intervalTime);
        Date current = order.equals(EachOrder.ASC) ? begin : end;
        int times = 0;

	    while (true){

	        // 从过去到现在遍历
	        if(order.equals(EachOrder.ASC)){

	            if(current.compareTo(end) >= 0){
	                break;
                }

                calendar.setTime(current);
                calendar.add(calendarUnit, intervalTime);
                Date sectionEnd = calendar.getTime();

                eachCallBack.execute(current, sectionEnd, times++);

                current = sectionEnd;

            }else{
	            if(current.compareTo(begin) <= 0){
	                break;
                }

                calendar.setTime(current);
                calendar.add(calendarUnit, intervalTime * -1);

                Date sectionBegin = calendar.getTime();

                eachCallBack.execute(sectionBegin, current, times++);

                current = sectionBegin;
            }

        }





    }


    public enum EachOrder{
	    DESC, ASC
    }

    public interface EachCallBack{
        public void execute(Date sectionBegin, Date sectionEnd, int times);
    }

}
