package com.harmontronics.lego.util;

import com.harmontronics.lego.common.Converter;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.*;

/**
 * 描述
 *
 * @author Five.Liu
 * @date 2017/5/10
 */
public class DateUtils {

	private static final String YEAR_FORMAT = "yyyy";
	private static final String MONTH_FORMAT = "yyyy-MM";

	private static final String TIME_FORMAT = "HH:mm:ss";
	private static final String SHORT_MINSECOND_FORMAT = "mm:ss";
	private static final String SHORT_HOURMIN_FORMAT = "HH:mm";

	private static final String DATE_FORMAT_STRING = "yyyy-MM-dd";
	private static final String SHORT_MONTHDATE_FORMAT_STRING = "MM-dd";

	private static final String DATEHOUR_FORMAT_STRING = "yyyy-MM-dd HH";
	private static final String SHORT_DATEHOUR_FORMAT_STRING = "dd-HH";

	private static final String DATEHOURMIN_FORMAT_STRING = "yyyy-MM-dd HH:mm";

	private static final String DATETIME_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";

	private static final String EXACT_DATETIME_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss.SSS";

	private static ThreadLocal<DateFormat> timeFormat = new ThreadLocal<DateFormat>() {

		protected synchronized DateFormat initialValue() {
			return new SimpleDateFormat(TIME_FORMAT, Locale.ENGLISH);
		}
	};

	/** 由于DateFormat不是线程安全的，存在性能问题，故设计为ThreadLocal */
	private static ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>() {

		protected synchronized DateFormat initialValue() {
			return new SimpleDateFormat(DATE_FORMAT_STRING, Locale.ENGLISH);
		}
	};

	/** 由于DateFormat不是线程安全的，存在性能问题，故设计为ThreadLocal */
	private static ThreadLocal<DateFormat> dateMinuteFormat = new ThreadLocal<DateFormat>() {

		protected synchronized DateFormat initialValue() {
			return new SimpleDateFormat(DATEHOURMIN_FORMAT_STRING, Locale.ENGLISH);
		}
	};

	/** 由于DateFormat不是线程安全的，存在性能问题，故设计为ThreadLocal */
	private static ThreadLocal<DateFormat> dateHourFormat = new ThreadLocal<DateFormat>() {

		protected synchronized DateFormat initialValue() {
			return new SimpleDateFormat(DATEHOUR_FORMAT_STRING, Locale.ENGLISH);
		}
	};

	private static ThreadLocal<DateFormat> shortDateHourFormat = new ThreadLocal<DateFormat>() {
		protected synchronized DateFormat initialValue() {
			return new SimpleDateFormat(SHORT_DATEHOUR_FORMAT_STRING, Locale.ENGLISH);
		}
	};

	private static ThreadLocal<DateFormat> shortHourMinFormat = new ThreadLocal<DateFormat>() {
		protected synchronized DateFormat initialValue() {
			return new SimpleDateFormat(SHORT_HOURMIN_FORMAT, Locale.ENGLISH);
		}
	};

	private static ThreadLocal<DateFormat> shortSecondFormat = new ThreadLocal<DateFormat>() {
		protected synchronized DateFormat initialValue() {
			return new SimpleDateFormat(SHORT_MINSECOND_FORMAT, Locale.ENGLISH);
		}
	};

	private static ThreadLocal<DateFormat> shortDateFormat = new ThreadLocal<DateFormat>() {
		protected synchronized DateFormat initialValue() {
			return new SimpleDateFormat(SHORT_MONTHDATE_FORMAT_STRING, Locale.ENGLISH);
		}
	};

	/** 由于DateFormat不是线程安全的，存在性能问题，故设计为ThreadLocal */
	private static ThreadLocal<DateFormat> datetimeFormat = new ThreadLocal<DateFormat>() {

		protected synchronized DateFormat initialValue() {
			return new SimpleDateFormat(DATETIME_FORMAT_STRING, Locale.ENGLISH);
		}
	};

	/** 由于DateFormat不是线程安全的，存在性能问题，故设计为ThreadLocal */
	private static ThreadLocal<DateFormat> exactDatetimeFormat = new ThreadLocal<DateFormat>() {

		protected synchronized DateFormat initialValue() {
			return new SimpleDateFormat(EXACT_DATETIME_FORMAT_STRING, Locale.ENGLISH);
		}
	};

	private static DateFormat getDateFormat() {
		return dateFormat.get();
	}

	private static DateFormat getDatetimeFormat() {
		return datetimeFormat.get();
	}

	private static DateFormat getTimeFormat() {
		return timeFormat.get();
	}


	private static DateFormat getDateMinuteFormat() {
		return dateMinuteFormat.get();
	}

	private static DateFormat getDateHourFormat() {
		return dateHourFormat.get();
	}

	private static DateFormat getExactDatetimeFormat() {
		return exactDatetimeFormat.get();
	}

	/**
	 * 转换日期对象为yyyy-MM-dd格式的字符串
	 */
	public static String toDateString(Date date) {
		return date == null ? StringUtils.EMPTY : getDateFormat().format(date);
	}

	/**
	 * 转换日期对象为yyyy-MM-dd hh:mm:ss格式的字符串
	 */
	public static String toDatetimeString(Date datetime) {
		return datetime == null ? StringUtils.EMPTY : getDatetimeFormat().format(datetime);
	}

	public static String toDateminuteString(Date datetime) {
		return datetime == null ? StringUtils.EMPTY : getDateMinuteFormat().format(datetime);
	}

	public static String toDateHourString(Date datetime) {
		return datetime == null ? StringUtils.EMPTY : getDateHourFormat().format(datetime);
	}


	/**
	 * 转换日期对象为yyyy-MM-dd hh:mm:ss.SSS格式的字符串
	 */
	public static String toExactDatetimeString(Date datetime) {
		return datetime == null ? StringUtils.EMPTY : getExactDatetimeFormat().format(datetime);
	}

	/**
	 * 将yyyy-MM-dd格式的字符串转换为日期对象
	 */
	public static Date fromDateString(String dateString) {
		try {
			return StringUtils.isBlank(dateString) ? null : getDateFormat().parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将hh:mm:ss格式的字符串转换为日期时间对象
	 */
	public static Date fromTimeString(String timeString) {
		try {
			return StringUtils.isBlank(timeString) ? null : getTimeFormat().parse(timeString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将yyyy-MM-dd hh:mm:ss格式的字符串转换为日期时间对象
	 */
	public static Date fromDatetimeString(String datetimeString) {
		try {
			return StringUtils.isBlank(datetimeString) ? null : getDatetimeFormat().parse(datetimeString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将yyyy-MM-dd hh:mm:ss.SSS格式的字符串转换为日期时间对象
	 */
	public static Date fromExactDatetimeString(String datetimeString) {
		try {
			return StringUtils.isBlank(datetimeString) ? null : getExactDatetimeFormat().parse(datetimeString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static Calendar Date2Calendar(Date dt){
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		return cal;
	}

	public static String Date2ShortMinSecString(Date date){
		DateFormat sdf = new SimpleDateFormat(SHORT_MINSECOND_FORMAT);
		return sdf.format(date);
	}

	public static String Date2ShortHourMinString(Date date){
		DateFormat sdf = new SimpleDateFormat(SHORT_HOURMIN_FORMAT);
		return sdf.format(date);
	}

	public static String Date2ShortDayHourString(Date date){
		DateFormat sdf = new SimpleDateFormat(SHORT_DATEHOUR_FORMAT_STRING);
		return sdf.format(date);
	}

	public static String Date2ShortMonDateString(Date date){
		DateFormat sdf = new SimpleDateFormat(SHORT_MONTHDATE_FORMAT_STRING);
		return sdf.format(date);
	}

	public static String Date2ShortWeekString(Date date){
		Calendar cal = Date2Calendar(date);
		String week = String.format("%d:%d", cal.get(Calendar.MONTH) + 1, cal.get(Calendar.WEEK_OF_MONTH));
		return week;
	}

	public static String Date2YearString(Date date){
		DateFormat sdf = new SimpleDateFormat(YEAR_FORMAT);
		return sdf.format(date);
	}

	public static String Date2MonthString(Date date){
		DateFormat sdf = new SimpleDateFormat(MONTH_FORMAT);
		return sdf.format(date);
	}

	public static String Date2DateString(Date date){
		DateFormat sdf = new SimpleDateFormat(DATE_FORMAT_STRING);
		return sdf.format(date);
	}

	public static String Date2ShortDateString(Date date){
		DateFormat sdf = new SimpleDateFormat("dd");
		return sdf.format(date);
	}

	public static Date TimeString2Date(String dateStr) throws ParseException {
		DateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
		return sdf.parse(dateStr);
	}

	/** 获取当前时间戳并转换成yyyy-MM-dd格式的日期字符串 */
	public static String getCurrentDate() {
		return DateUtils.toDateString(new Date());
	}

	/** 获取当前时间戳并转换成yyyy-MM-dd hh:mm:ss格式的日期时间字符串 */
	public static String getCurrentDatetime() {
		return DateUtils.toDatetimeString(new Date());
	}

	/** 获取当前时间戳并转换成yyyy-MM-dd hh:mm:ss格式的日期时间字符串 */
	public static String getCurrentExactDatetime() {
		return DateUtils.toExactDatetimeString(new Date());
	}

	public static void main(String[] args) throws Exception {
		Converter<Double,String> converter =  from ->
			new DecimalFormat("######0.00").format(from);
		System.out.println(converter.convert(3.15678));
	}

	// 获得当天0点时间
	public static Date getTimesmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	// 获得昨天0点时间
	public static Date getYesterdaymorning() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(getTimesmorning().getTime()-3600*24*1000);
		return cal.getTime();
	}

	// 获得当天近7天时间
	public static Date getWeekFromNow() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis( getTimesmorning().getTime()-3600*24*1000*7);
		return cal.getTime();
	}

	// 获得当天24点时间
	public static Date getTimesnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	// 获得本周一0点时间
	public static Date getTimesWeekmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}

	// 获得本周日24点时间
	public static Date getTimesWeeknight() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getTimesWeekmorning());
		cal.add(Calendar.DAY_OF_WEEK, 7);
		return cal.getTime();
	}

	// 获得本月第一天0点时间
	public static Date getTimesMonthmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	// 获得本月最后一天24点时间
	public static Date getTimesMonthnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 24);
		return cal.getTime();
	}

	public static Date getLastMonthStartMorning() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getTimesMonthmorning());
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}

	public static Date getCurrentQuarterStartTime() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3)
				c.set(Calendar.MONTH, 0);
			else if (currentMonth >= 4 && currentMonth <= 6)
				c.set(Calendar.MONTH, 3);
			else if (currentMonth >= 7 && currentMonth <= 9)
				c.set(Calendar.MONTH, 4);
			else if (currentMonth >= 10 && currentMonth <= 12)
				c.set(Calendar.MONTH, 9);
			c.set(Calendar.DATE, 1);
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 当前季度的结束时间，即2012-03-31 23:59:59
	 * @return
	 */

	public static Date getCurrentHourStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), 0, 0);
		return cal.getTime();
	}

	public static Date getCurrentHourEndTime() {
		Calendar now = Calendar.getInstance();

		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), 59, 59);

		return now.before(cal) ? now.getTime(): cal.getTime();
	}

	public static Date getCurrentMinuteStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), 0);

		return cal.getTime();
	}

	public static Date getCurrentMinuteEndTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), 59);

		return cal.getTime();
	}

	public static Date getCurrentQuarterEndTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentQuarterStartTime());
		cal.add(Calendar.MONTH, 3);
		return cal.getTime();
	}

	public static Date getCurrentYearStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.YEAR));
		return cal.getTime();
	}

	public static Date getCurrentYearEndTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentYearStartTime());
		cal.add(Calendar.YEAR, 1);
		return cal.getTime();
	}

	public static Date getLastYearStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentYearStartTime());
		cal.add(Calendar.YEAR, -1);
		return cal.getTime();
	}

}
