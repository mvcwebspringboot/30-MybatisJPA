package com.study.boot.util;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagingUtil {
	private int page;//현재 보이는 페이지의 번호 저장 변수
	private int size;//한 페이지 당 보일 게시글의 개수 저장 변수
	private int displayPagiNum;//보여질 페이지 번호 개수 저장 변수
	private int totalCount;//전체 글 개수를 저장하는 변수
	private int totalPage;
	
	//페이징용 html 코드를 만드는 메소드
	public String makePaging() {
		StringBuilder sb = new StringBuilder();
		
		//1. 전체 페이지 개수 구하기(페이지 당 size(10개) 만큼 출력)
		//전체 게시글 9개 : 1 페이지
		//전체 게시글 11개 : 2 페이지
		int totalPage = (totalCount % size) > 0 ?
				totalCount / size + 1 :
				totalCount / size;
		
		log.info("★★★★★★★★★★ totalPage = "+totalPage);

		int endPage = (int)(Math.ceil((double)page/displayPagiNum)*displayPagiNum);
		int startPage = (endPage - displayPagiNum) +1;
		int realEndPage = (int)(Math.ceil((double)totalCount/size));
	    //endPage 재계산
	    if(endPage>realEndPage){
	      endPage = realEndPage;
	    }
	    
		//이전 버튼 처리
		if(startPage != 1) {
			sb.append("<li><a href='./list?page="
					+ (startPage - 1) + "&amp;size="+size+"'>");
			sb.append("◀</a></li>");
		}//<li><a herf='./list?page=5'> 이전 </a></li>
		//페이지 번호를 5개씩 보여주는 경우,
		//6페이지가 보이는 화면에서 이전 버튼이 보이게 되고
		//그 이전 버튼의 링크는 5페이지가 된다.
		
		//중간 페이지 번호 버튼 처리
		for(int i = startPage; i <= endPage; i++) {
			if(page != i) {//현재 페이지가 아닌 페이지 번호
				sb.append("<li><a href='./list?page="
						+ i + "&amp;size="+size+"'>");
				sb.append(i + "</a></li>");
			}//<li><a href='./list?page=3> 3 </a></li>
			else {//현재 보이는 페이지
				//현재 보이는 페이지 번호에는 링크를 걸지 않는다.
				sb.append("<li><a class='on' href='#'>");
				sb.append(i + "</a></li>");
			}
		}
		
		//다음 버튼 처리
		if(endPage != totalPage) {
			sb.append("<li><a href='./list?page="
					+ (endPage + 1) + "&amp;size="+size+"'>");
			sb.append("▶</a></li>");
		}//<li><a href='./list?page=6'> 다음 </a></li>
		
		//StringBuffer에 저장된 내용을 문자열로 변환
		return sb.toString();
	}
}//class end




