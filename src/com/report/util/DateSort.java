package com.report.util;

import java.util.Comparator;
import java.util.Date;

import com.report.pojo.Meeting;

/**
 * className:DateSort 
 * function:实现按日期排序
 * @author Vera Jiang
 *
 */
@SuppressWarnings("rawtypes")
public class DateSort implements Comparator {

	public int compare(Object o1, Object o2) {
		Meeting m1 = (Meeting) o1;
		Meeting m2 = (Meeting) o2;
		Date d1, d2;
		d1 = m1.getMeetingDate();
		d2 = m2.getMeetingDate();

		if (d1.before(d2)) {
			return 1;
		} else {
			return -1;
		}
	}
}
