package com.todo.menu;
public class Menu {

    public static void displaymenu() {
        System.out.println("\n=== Todo List 명령어 일람 ===");
        System.out.println("add : Todo 항목을 추가합니다.");
        System.out.println("del : Todo 항목을 삭제합니다.");
        System.out.println("edit : Todo 항목을 수정합니다.");
        System.out.println("ls : Todo 항목들을 조회합니다.");
        System.out.println("ls_name_asc : Todo 항목들을 제목순으로 정렬합니다.");
        System.out.println("ls_name_desc : Todo 항목들을 제목역순으로 정렬합니다.");
        System.out.println("ls_date : Todo 항목들을 날짜순으로 정렬합니다.");
        System.out.println("exit : 프로그램을 종료합니다.");
    }
    
    public static void prompt() {
    	System.out.print("\n 명령어 입력하세요 >>> ");
    }
}
