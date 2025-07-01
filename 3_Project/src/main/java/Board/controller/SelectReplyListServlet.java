package Board.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Board.model.service.BoardService;
import Board.model.vo.Reply;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SelectReplyListServlet
 */
@WebServlet("/selectReplyList.bo")
public class SelectReplyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectReplyListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bId = Integer.parseInt(request.getParameter("bId"));
		
		ArrayList<Reply> list = new BoardService().selectReplyList(bId);
		//response.getWriter().print(list);
		//int, String... =>객체를 보낼 때는 부적절한 방법
		//	<json>
		//ArrayList(Reply객체들이 담긴) 이거를
		//json Object로 바꿔서->json Array에 저장함
//		response.setContentType("application/json;charset=UTF-8");
//		//외부 라이브러리에서 가져와야 쓸 수있음 . ->다운받아야댐
//		JSONArray array = new JSONArray();
//		for(Reply r : list) {
//			JSONObject json = new JSONObject();
//			json.put("replyNo", r.getReplyNo());
//			json.put("content", r.getContent());
//			json.put("writer", r.getWriter());
//			json.put("createDate", r.getCreateDate() + ""); //json으로 date형태를 보낼 때 에러(방지 : 스트링 형태로 바꿔주기)
//			
//			array.add(json);
//		}
//		 response.getWriter().print(array);
//		
		//	<gson>
		response.setContentType("application/json; charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(list, response.getWriter());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
