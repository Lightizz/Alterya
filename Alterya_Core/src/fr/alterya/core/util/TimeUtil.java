package fr.alterya.core.util;

public class TimeUtil
{
	public static int secondsToMins(int sec) {
		return sec * 60;
	}
	public static int secondsToHours(int sec) {
		return sec * 3600;
	}
	public static int secondsToDays(int sec) {
		return sec * 86400;
	}
	public static int secondsToMonths(int sec) {
		return sec * 8467200;
	}
	public static int secondsToYears(int sec) {
		return sec * 31557600;
	}
	
	public static int minsToSeconds(int min) {
		return min / 60;
	}
	public static int minsToHours(int min) {
		return min * 60;
	}
	public static int minsToDays(int min) {
		return min * 1440;
	}
	public static int minsToMonths(int min) {
		return min * 40320;
	}
	public static int minToYears(int min) {
		return min * 525960;
	}
	
	public static int hoursToMins(int hours) {
		return hours / 60;
	}
	public static int hoursToSeconds(int hours) {
		return hours / 3600;
	}
	public static int hoursToDays(int hours) {
		return hours * 24;
	}
	public static int hoursToMonths(int hours) {
		return hours * 672;
	}
	public static int hoursToYears(int hours) {
		return hours * 8766;
	}
	
	public static int daysToMins(int days) {
		return days / 1440;
	}
	public static int daysToSeconds(int days) {
		return days / 46400;
	}
	public static int daysToHours(int days) {
		return days / 24;
	}
	public static int daysToMonths(int days) {
		return days * 28;
	}
	public static int daysToYears(int days) {
		return days * 365;
	}
}
