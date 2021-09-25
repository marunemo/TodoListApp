package com.todo.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
				return;
			}
		}
		
		System.out.println("해당하는 Todo 항목이 존재하지 않습니다.");
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
		String new_title = sc.nextLine().trim();
		if (!title.equals(new_title) && list.isDuplicate(new_title)) {
			System.out.println("이미 존재하는 제목입니다!");
			return;
		}
		System.out.print("내용 >>> ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : list.getList()) {
			if (item.getTitle().equals(title)) {
				list.editItem(item, new TodoItem(new_title, new_description));
				System.out.println("항목이 변경되었습니다.");
			}
		}

	}

	public static void listAll(TodoList l) {
		ArrayList<TodoItem> list = l.getList();
		int len = list.size();
		System.out.println("총 " + len + "개의 todo 항목이 있습니다.");
		for(int i = 0; i < len; i++) {
			TodoItem item = list.get(i);
			System.out.println(String.format("[%s] %s | %s",
					item.getCurrent_date(), item.getTitle(), item.getDesc()));
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			FileWriter writer = new FileWriter(filename);
			
			for (TodoItem item : l.getList())
				writer.write(item.toSaveString());
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			
			String readStr = reader.readLine();
			while(readStr != null) {
				StringTokenizer strtok = new StringTokenizer(readStr, "##");
				String title = strtok.nextToken();
				String desc = strtok.nextToken();
				String date = strtok.nextToken();
				
				TodoItem item = new TodoItem(title, desc);
				item.setCurrent_date(date);
				l.addItem(item);
				
				readStr = reader.readLine();
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
