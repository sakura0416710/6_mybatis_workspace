package common;

import Board.model.vo.PageInfo;

public class Pageination {
	//페이지 처리를 하기위한 클래스 만들어두기
	//페이징처리 : 현재 페이지와 전체 게시글 개수가 필요
	public static PageInfo getPageInfo(int currentPage, int listCount) {
		int pageLimit=10;    //한 화면에서 보일 페이징 수
		int boardLimit = 5;  //한 페이지에 보일 게시글 수
		int maxPage;     //전체 페이지 중 가장 마지막 페이지
		int startPage;  // 현재 페이지 기준에서 보이는 시작하는 페이지 
		int endPage;	// 현제 페이지 기준에서 끝나는 페이지 
		
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		//1,11,21,31,, 이 스타트 페이지로 됨 => 수열 
		//10n + 1( n >= 0)
		//currentPage : 1~10사이면 스타트 페이지가 1 => n = 0
		//currentPage : 2~20사이면 스타트 페이지가 11 => n = 1
		//currentPage : 3~30사이면 스타트 페이지가 21 = > n = 2
		//즉 스타트 페이지는 현재 페이지의 영향을 받음
		// n= (currentPage-1) / pageLimit
		startPage = (currentPage-1) / pageLimit * pageLimit + 1;
		endPage = startPage + pageLimit -1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		return new PageInfo(currentPage,listCount,pageLimit,boardLimit, maxPage,startPage, endPage);
	}
}
