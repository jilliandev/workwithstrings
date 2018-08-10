package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static String output = "";

	public static void main(String[] args) throws IOException {
		System.out.println("Write input string:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = reader.readLine();
		/*StringBuilder outputBuilder = new StringBuilder(input);
		int inputLength = input.length();
		output = reverseWithSetCharAt(input, outputBuilder, inputLength);*/
		output = reverseWithXOR(input);

		System.out.println("Input: \"" + input + "\"\nOutput: \"" + output + "\"");
		System.out.println("\n----------------------------------------------------------------------\n");

		isIsomorphic("asd", "dsa");
		isIsomorphic("geg", "foo");
		isIsomorphic("egg", "foo");
		isIsomorphic("bar", "foo");
		isIsomorphic("aaaaasdaaaa", "qqqqweqqqq");
		isIsomorphic("aaaaasdaaaa", "qqqqqweqqqf");
		isIsomorphic("aaaaasdaaaa", "qqqqqweqqqq");
	}

	public static String reverseWithSetCharAt(String input, StringBuilder outputBuilder, int inputLength) {
		int k = inputLength - 1;

		for (int i = 0; i < inputLength; i++) {
			outputBuilder.setCharAt(i, input.charAt(k));
			k--;
		}

		output = outputBuilder.toString();

		return output;
	}

	public static String reverseWithXOR(String input) {
		char[] outputArray = input.toCharArray();
		int length = outputArray.length;
		int halfLength = (int) Math.floor(length / 2);

		for (int i = 0; i < halfLength; i++) {
			outputArray[i] ^= outputArray[length - i - 1];
			outputArray[length - i - 1] ^= outputArray[i];
			outputArray[i] ^= outputArray[length - i - 1];
		}

		output = String.valueOf(outputArray);

		return output;
	}


	public static void isIsomorphic(String t, String s) {
		AtomicInteger index = new AtomicInteger();

		if (s.length() == t.length()) {
			AtomicBoolean flag = new AtomicBoolean(true);
			Map<Character, Character> dictionary = new HashMap<>();
			Set<Character> alreadyUsed = new HashSet<>();
			t.chars().forEachOrdered(i -> {
				char charFromT = t.charAt(index.get());
				char charFromS = s.charAt(index.get());
				if (dictionary.containsKey(charFromT)) {
					if (dictionary.get(charFromT) != charFromS) {
						flag.set(false);
						return;
					}
				} else {
					if (alreadyUsed.contains(charFromS)) {
						flag.set(false);
						return;
					} else {
						dictionary.put(charFromT, charFromS);
						alreadyUsed.add(charFromS);
					}
				}
				index.getAndIncrement();
			});
			System.out.println(flag.get() ? "strings " + t + " and " + s + " are isomorphic\n" : "strings " + t + " and " + s + " aren't "
					+ "isomorphic\n");
		} else {
			System.out.println("strings should have same length\n");
		}
	}
