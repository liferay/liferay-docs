package com.liferay.documentation.util;

import java.util.HashSet;
import java.util.Set;

public class JimDemoSetInteger {

	public static void main(String[] args) {
		Set<Integer> mySet = new HashSet<Integer>();

		for (int i=0; i < 10; i++) {
			Integer myInt = Integer.valueOf(i);
			mySet.add(myInt);
		}

		mySet.add(Integer.valueOf(1));

		for (Integer someInt : mySet) {
			System.out.println(someInt);
		}
	}

}
