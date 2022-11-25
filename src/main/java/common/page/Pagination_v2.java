package common.page;

/**
 * 
 * @author 5563a 문수형 Pagination 기반으로 링크걸기 기능을 추가하였습니다. css는 재량껏 수정하시면 됩니다.
 *         --------------------------------------------------------------------------------------
 *         model에 "pagination" 명으로 속성을 추가해주시면 됩니다.
 * 
 *         ex) Pagination_v2 pagination=new Pagination_v2(page,
 *         total,0,limit,link); model.addAttribute("pagination", pagination);
 *         --------------------------------------------------------------------------------------
 *         타임리프 태그(\webapp\WEB-INF\view\layouts\commons\pagination_v2.html) 와
 *         연동해 놓았으며 사용하실 페이지에 추가하시면 됩니다.
 * 
 *         ex) <th:block th:replace=
 *         "layouts/commons/pagination_v2::pagination_v2"></th:block>
 * 
 *         --------------------------------------------------------------------------------------
 * 
 */

public class Pagination_v2 extends Pagination {

	/**
	 * 링크 자동으로 걸어주기 ex) link =
	 * "/shop/search?searchValue="+searchValue+"&searchType="+searchType+"&page="
	 */
	private String link;

	public Pagination_v2(int page, int total, int pageCnt, int limit, String link) {
		super(page, total, pageCnt, limit);
		this.link = link;
		// TODO Auto-generated constructor stub
	}

	public Pagination_v2(int page, int total, String link) {
		super(page, total);
		this.link = link;
		// TODO Auto-generated constructor stub
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	@Override
	public String toString() {
		return "Pagination_v2 [link=" + link + ", toString()=" + super.toString() + "]";
	}

}
