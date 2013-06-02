package com.example.collject_android.utils;


public class Helper {

	private static final String SERVER_URL = "http://23.21.187.163/api/";
	private static final String PROBLEM_URL = "problem/";
	private static final String PROJECT_URL = "project/";
	private static final String SOLUTION_URL = "solution/";
	private static final String LIST_URL = "list/";
	private static final String GENERAL_URL = "info/";
	private static final String FOLLOWER_URL = "follower/";
	private static final String SEARCH_URL = "search/";
	private static final String PSEARCH_URL = "psearch/";

	public static enum StuffType {
		Problem, Solution, Project
	}

	public static enum InfoType {
		List, General, Solution, Follower
	}

	public static enum SearchType {
		Skill, Position
	}

	public static String serverGetRequestBuilder(StuffType st, InfoType it,
			Integer info) {
		switch (st) {
		case Problem:
			switch (it) {
			case List:
				return SERVER_URL + PROBLEM_URL + LIST_URL;
			case General:
				return SERVER_URL + PROBLEM_URL + info + "/" + GENERAL_URL;
			case Solution:
				return SERVER_URL + PROBLEM_URL + info + "/" + SOLUTION_URL;
			case Follower:
				return SERVER_URL + PROBLEM_URL + info + "/" + FOLLOWER_URL;
			default:
				break;
			}
		case Solution:
			switch (it) {
			case List:
				return SERVER_URL + SOLUTION_URL + LIST_URL;
			case General:
				return SERVER_URL + SOLUTION_URL + info + "/" + GENERAL_URL;
			case Solution:
				throw new IllegalStateException(
						"You can't request a solution for a solution.");
			case Follower:
				return SERVER_URL + SOLUTION_URL + info + "/" + FOLLOWER_URL;
			default:
				break;

			}
		case Project:
			switch (it) {
			case List:
				return SERVER_URL + PROJECT_URL + LIST_URL;
			case General:
				return SERVER_URL + PROJECT_URL + info + "/" + GENERAL_URL;
			case Solution:
				throw new IllegalStateException("A project is a solution");
			case Follower:
				return SERVER_URL + PROJECT_URL + info + "/" + FOLLOWER_URL;
			default:
				break;
			}
		default:
			break;
		}
		return null;
	}

	public static String projectSearchRequestBuilder(SearchType st) {
		switch (st) {
		case Skill:
			return SERVER_URL + PROJECT_URL + SEARCH_URL;

		default:
			return SERVER_URL + PROJECT_URL + PSEARCH_URL;
		}
	}
}
