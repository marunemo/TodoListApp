package com.todo.service;

import java.text.SimpleDateFormat;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("추가할 Todo 항목을 입력하세요.");
		System.out.print("제목 >>> ");
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.println("이미 존재하는 제목입니다!");
			return;
		}

		System.out.print("내용 >>> ");
		desc = sc.nextLine();

		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("삭제할 Todo 항목을 입력하세요.");
		System.out.print("제목 >>> ");
		
		String title = sc.nextLine();
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public static void updateItem(TodoList list) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("변경할 Todo 항목의 제목을 입력하세요.");
		System.out.print("제목 >>> ");
		String title = sc.nextLine();
		if (!list.isDuplicate(title)) {
			System.out.println("존재하지 않는 제목입니다!");
			return;
		}

		System.out.println("새로 추가할 Todo 항목을 입력하세요.");
		System.out.print("제목 >>> ");
		String new_title = sc.next().trim();
		if (!title.equals(new_title) && list.isDuplicate(new_title)) {
			System.out.println("이미 존재하는 제목입니다!");
			return;
		}
		System.out.print("내용 >>> ");
		String new_description = sc.next().trim();
		for (TodoItem item : list.getList()) {
			if (item.getTitle().equals(title)) {
				list.editItem(item, new TodoItem(new_title, new_description));
				System.out.println("항목이 변경되었습니다.");
			}
		}

	}

	public static void listAll(TodoList l) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		for (TodoItem item : l.getList()) {
			System.out.println(String.format("[%s] %s | %s",
					format.format(item.getCurrent_date()), item.getTitle(), item.getDesc()));
		}
	}
}
