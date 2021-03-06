package com.RINEX_parser.models;

import java.util.ArrayList;
import java.util.HashMap;

import com.RINEX_parser.utility.Time;

public class ObservationMsg {

	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private double second;
	private int ObsvSatCount;
	private HashMap<Character, HashMap<Integer, HashMap<Character, ArrayList<Observable>>>> obsvSat = new HashMap<Character, HashMap<Integer, HashMap<Character, ArrayList<Observable>>>>();
	private double tRX;
	private long weekNo;

	public void setObsvSat(HashMap<Character, HashMap<Integer, HashMap<Character, ArrayList<Observable>>>> obsvSat) {
		this.obsvSat = obsvSat;
		ObsvSatCount = obsvSat.size();
	}

	public void set_RxTime(String[] RxTime) {
		year = Integer.parseInt(RxTime[0]);
		month = Integer.parseInt(RxTime[1]);
		day = Integer.parseInt(RxTime[2]);
		hour = Integer.parseInt(RxTime[3]);
		minute = Integer.parseInt(RxTime[4]);
		second = Double.parseDouble(RxTime[5]);
		double[] GPStime = Time.getGPSTime(year, month - 1, day, hour, minute, second);
		tRX = GPStime[0];
		weekNo = (long) GPStime[1];
	}

	public int getYear() {
		return year;
	}

	public int getObsvSatCount() {
		return ObsvSatCount;
	}

	public void setObsvSatCount(int obsvSatCount) {
		ObsvSatCount = obsvSatCount;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public double getSecond() {
		return second;
	}

	@Override
	public String toString() {
		return "ObservationMsg [ year=" + year + ", month=" + month + ", day=" + day + ", hour=" + hour + ", minute="
				+ minute + ", second=" + second + ", ObsvSatCount=" + ObsvSatCount + ", obsvSat=" + obsvSat + "]";
	}

	public HashMap<Character, HashMap<Integer, HashMap<Character, ArrayList<Observable>>>> getObsvSat() {
		return obsvSat;
	}

	public ArrayList<Observable> getObsvSat(String obsvCode) {
		char SSI = obsvCode.charAt(0);
		int freqID = Integer.parseInt(obsvCode.charAt(1) + "");
		char codeID = obsvCode.charAt(2);

		if (obsvSat.containsKey(SSI)) {
			if (obsvSat.get(SSI).containsKey(freqID)) {
				return obsvSat.get(SSI).get(freqID).getOrDefault(codeID, null);
			}
			return null;
		}
		return null;
	}

	public double getTRX() {
		return tRX;
	}

	public long getWeekNo() {
		return weekNo;
	}
}
