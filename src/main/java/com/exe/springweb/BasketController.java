package com.exe.springweb;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exe.dao.BasketDAO;
import com.exe.dto.BasketDTO;
import com.exe.dto.LoginDTO;
import com.exe.dto.MyOrderDTO;
import com.exe.dto.OrderListDTO;
import com.user.service.MemberService;


@Controller
public class BasketController {

	@Autowired
	@Qualifier("basketDAO")
	BasketDAO bDao;
	
	@Autowired
	@Qualifier("memberService")
	MemberService ms;

	// 장바구니에 추가&수정
	@RequestMapping(value = "/basketUpd", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int basketUpt(BasketDTO dto, HttpServletRequest request) {
		
		System.out.println("장바구니 추가&수정 호출: " + dto.getNum() + dto.getModelName1());
		int result = 0;
		HttpSession session = request.getSession();
		LoginDTO vo =  (LoginDTO) session.getAttribute("userSession");	
		
		if(vo==null) {
			return result;
		}	
		String email = vo.getEmail();
		dto.setEmail(email);			
		
		if(dto.getType1()!=null) {
			List<BasketDTO> lists = 
					bDao.getBasketListSearch(dto);
			if(lists.size()==0) {
				System.out.println("추가");
				int num = bDao.getBasketMaxNum();

				dto.setNum(num + 1);

				bDao.insertData(dto);

				result = 1;
			}
		}
		
		System.out.println("수정" + dto.getModelName1());
		bDao.updateDate(dto);

		result = 1;		

		//오리지널********
		//커피
		List<BasketDTO> listsCo = bDao.getBasketList(email,"coffee","original");
		//머신		
		List<BasketDTO> listsMo = bDao.getBasketList(email,"machine","original");

		//버츄오**********
		//커피
		List<BasketDTO> listsCv = bDao.getBasketList(email,"coffee","vertuo");
		//머신
		List<BasketDTO> listsMv = bDao.getBasketList(email,"machine","vertuo");

		//액새서리********
		List<BasketDTO> listsAc = bDao.getBasketList(email,"acc","acc");

		//전체리스트 수******
		int totalLength = bDao.getBasketListCount(email);

		//전체리스트 제품수******
		int totalCount = bDao.getBasketCount(email);

		//총금액******
		int totalPrice = bDao.getTotalPrice(email);

		session.setAttribute("listsCo", listsCo);
		session.setAttribute("listsMo", listsMo);
		session.setAttribute("listsCv", listsCv);
		session.setAttribute("listsMv", listsMv);
		session.setAttribute("listsAc", listsAc);
		session.setAttribute("totalLength", totalLength);
		session.setAttribute("totalCount", totalCount);
		session.setAttribute("totalPrice", totalPrice);
		
		request.setAttribute("listsCo", listsCo); 
		request.setAttribute("listsMo",listsMo); 
		request.setAttribute("listsCv", listsCv);
		request.setAttribute("listsMv", listsMv); 
		request.setAttribute("listsAc",listsAc);
		request.setAttribute("totalLength", totalLength);
		request.setAttribute("totalPrice", totalPrice);
		

		System.out.println(result);

		return result;

	}

	// 장바구니에 삭제	
	@RequestMapping(value = "/basketDel", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int basketDel(BasketDTO dto, HttpServletRequest request) {
		System.out.println("장바구니 삭제 호출: " + dto.getModelName1());
		
		int result = 0;
		
		HttpSession session = request.getSession();
		LoginDTO vo =  (LoginDTO) session.getAttribute("userSession");	
		String email = vo.getEmail();
		System.out.println(email);
		
		dto.setEmail(email);
		
		bDao.deleteDate(dto);
		
		//오리지널********
		//커피
		List<BasketDTO> listsCo = bDao.getBasketList(email,"coffee","original");
		//머신		
		List<BasketDTO> listsMo = bDao.getBasketList(email,"machine","original");

		//버츄오**********
		//커피
		List<BasketDTO> listsCv = bDao.getBasketList(email,"coffee","vertuo");
		//머신
		List<BasketDTO> listsMv = bDao.getBasketList(email,"machine","vertuo");

		//액새서리********
		List<BasketDTO> listsAc = bDao.getBasketList(email,"acc","acc");

		//전체리스트 수******
		int totalLength = bDao.getBasketListCount(email);
		if(totalLength!=0) {
			//전체리스트 제품수******
			int totalCount = bDao.getBasketCount(email);

			//총금액******
			int totalPrice = bDao.getTotalPrice(email);
			session.setAttribute("totalCount", totalCount);
			session.setAttribute("totalPrice", totalPrice);
		}else {
			session.removeAttribute("totalCount");
			session.removeAttribute("totalPrice");
		}

		session.setAttribute("listsCo", listsCo);
		session.setAttribute("listsMo", listsMo);
		session.setAttribute("listsCv", listsCv);
		session.setAttribute("listsMv", listsMv);
		session.setAttribute("listsAc", listsAc);
		session.setAttribute("totalLength", totalLength);		
		
		result = 1;
		System.out.println(result);
		return result;

	}

	// 장바구니 Step1
	@RequestMapping(value = "/checkoutStep1", method = { RequestMethod.GET, RequestMethod.POST })
	public String checkoutStep1(BasketDTO dto, HttpServletRequest request) {
		System.out.println("장바구니 Step1 호출");
		
		HttpSession session = request.getSession();
		LoginDTO vo =  (LoginDTO) session.getAttribute("userSession");	
		String email = vo.getEmail();
				
		//오리지널********
		//커피			
		List<BasketDTO> listsCo = bDao.getBasketList(email,"coffee","original");
		//머신		
		List<BasketDTO> listsMo = bDao.getBasketList(email,"machine","original");
		
		//버츄오**********
		//커피
		List<BasketDTO> listsCv = bDao.getBasketList(email,"coffee","vertuo");
		//머신
		List<BasketDTO> listsMv = bDao.getBasketList(email,"machine","vertuo");
		
		//액새서리********
		List<BasketDTO> listsAc = bDao.getBasketList(email,"acc","acc");
		
		//전체리스트 수******
		int totalLength = bDao.getBasketCount(email);

		//총금액******
		int totalPrice = bDao.getTotalPrice(email);
		
		request.setAttribute("listsCo", listsCo);
		request.setAttribute("listsMo", listsMo);
		request.setAttribute("listsCv", listsCv);
		request.setAttribute("listsMv", listsMv);
		request.setAttribute("listsAc", listsAc);
		request.setAttribute("totalLength", totalLength);
		request.setAttribute("totalPrice", totalPrice);

		return "basket/checkoutStep1";

	}

	// 장바구니 Step2	
	@RequestMapping(value = "/checkoutStep2", method = { RequestMethod.GET, RequestMethod.POST })
	public String checkoutStep2(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("장바구니 Step2 호출");
		
		HttpSession session = request.getSession();
		LoginDTO vo =  (LoginDTO) session.getAttribute("userSession");	
		String email = vo.getEmail();
		
		//오리지널********
		//커피			
		List<BasketDTO> listsCo = bDao.getBasketList(email,"coffee","original");
		//머신		
		List<BasketDTO> listsMo = bDao.getBasketList(email,"machine","original");
		
		//버츄오**********
		//커피
		List<BasketDTO> listsCv = bDao.getBasketList(email,"coffee","vertuo");
		//머신
		List<BasketDTO> listsMv = bDao.getBasketList(email,"machine","vertuo");
		
		//액새서리********
		List<BasketDTO> listsAc = bDao.getBasketList(email,"acc","acc");
		
		//전체리스트 수******
		int totalLength = bDao.getBasketCount(email);

		//총금액******
		int totalPrice = bDao.getTotalPrice(email);
		
		request.setAttribute("listsCo", listsCo);
		request.setAttribute("listsMo", listsMo);
		request.setAttribute("listsCv", listsCv);
		request.setAttribute("listsMv", listsMv);
		request.setAttribute("listsAc", listsAc);
		request.setAttribute("totalLength", totalLength);		
		request.setAttribute("totalPrice", totalPrice);
		
		return "basket/checkoutStep2";

	}

	// 장바구니 Step3	
	@RequestMapping(value = "/checkoutStep3", method = { RequestMethod.GET, RequestMethod.POST })
	public String checkoutStep3(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		LoginDTO vo =  (LoginDTO) session.getAttribute("userSession");	
		String email = vo.getEmail();
		
		//오리지널********
		//커피			
		List<BasketDTO> listsCo = bDao.getBasketList(email,"coffee","original");
		//머신		
		List<BasketDTO> listsMo = bDao.getBasketList(email,"machine","original");
		
		//버츄오**********
		//커피
		List<BasketDTO> listsCv = bDao.getBasketList(email,"coffee","vertuo");
		//머신
		List<BasketDTO> listsMv = bDao.getBasketList(email,"machine","vertuo");
		
		//액새서리********
		List<BasketDTO> listsAc = bDao.getBasketList(email,"acc","acc");
		
		//전체리스트 수******
		int totalLength = bDao.getBasketCount(email);

		//총금액******
		int totalPrice = bDao.getTotalPrice(email);
		
		request.setAttribute("listsCo", listsCo);
		request.setAttribute("listsMo", listsMo);
		request.setAttribute("listsCv", listsCv);
		request.setAttribute("listsMv", listsMv);
		request.setAttribute("listsAc", listsAc);
		request.setAttribute("totalLength", totalLength);		
		request.setAttribute("totalPrice", totalPrice);

		return "basket/checkoutStep3";

	}

	// 장바구니 Step4	
	@RequestMapping(value = "/checkoutStep4", method = { RequestMethod.GET, RequestMethod.POST })
	public String checkoutStep4(HttpServletRequest request, HttpServletResponse response) {
		
		String cardNum = request.getParameter("cardNum");
		String cardMon = request.getParameter("cardMon");
		String cardYear = request.getParameter("cardYear");
		String installments = request.getParameter("installments");
		System.out.println(cardNum);
		System.out.println(cardMon);
		System.out.println(cardYear);
		System.out.println(installments);
		HttpSession session = request.getSession();
		LoginDTO vo =  (LoginDTO) session.getAttribute("userSession");	
		String email = vo.getEmail();
		
		//오리지널********
		//커피			
		List<BasketDTO> listsCo = bDao.getBasketList(email,"coffee","original");
		//머신		
		List<BasketDTO> listsMo = bDao.getBasketList(email,"machine","original");
		
		//버츄오**********
		//커피
		List<BasketDTO> listsCv = bDao.getBasketList(email,"coffee","vertuo");
		//머신
		List<BasketDTO> listsMv = bDao.getBasketList(email,"machine","vertuo");
		
		//액새서리********
		List<BasketDTO> listsAc = bDao.getBasketList(email,"acc","acc");
		
		//전체리스트 수******
		int totalLength = bDao.getBasketListCount(email);
		
		//전체리스트 제품수******
		int totalCount = bDao.getBasketCount(email);

		//총금액******
		int totalPrice = bDao.getTotalPrice(email);
		
		request.setAttribute("listsCo", listsCo);
		request.setAttribute("listsMo", listsMo);
		request.setAttribute("listsCv", listsCv);
		request.setAttribute("listsMv", listsMv);
		request.setAttribute("listsAc", listsAc);
		request.setAttribute("totalLength", totalLength);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("cardNum", cardNum);
		request.setAttribute("cardMon", cardMon);
		request.setAttribute("cardYear", cardYear);
		request.setAttribute("installments", installments);
		
		return "basket/checkoutStep4";

	}

	// 장바구니 Step5
	@RequestMapping(value = "/checkoutStep5", method = { RequestMethod.GET, RequestMethod.POST })
	public String checkoutStep5(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("장바구니 Step5 호출");
		
		HttpSession session = request.getSession();
		LoginDTO vo =  (LoginDTO) session.getAttribute("userSession");	
		String email = vo.getEmail();
		
		//장바구니 총 리스트
		List<BasketDTO> listsAll = bDao.getBasketAllList(email);
		
		//주문내역(MyOrder)
		MyOrderDTO mdto = new MyOrderDTO();
		
		String status = "대기 중";
		String oPlace ="온라인";
		String transport = "일반 택배배송";
		//총금액
		int totalPrice = bDao.getTotalPrice(email);
		
		int maxOrderNum = ms.maxOrderNum();
		int orderNum = 0;
		System.out.println("컨트롤 맥스넘: " + maxOrderNum);
		if(maxOrderNum==0) {
			orderNum = 10000001;
		}else {
			orderNum = maxOrderNum+1;
		}		
		System.out.println("orderNum: " + orderNum);
		mdto.setEmail(email);
		mdto.setStatus(status);
		mdto.setoPlace(oPlace);
		mdto.setTransport(transport);
		mdto.setOrderNum(orderNum);
		mdto.setTotalPrice(totalPrice);
		
		ms.insertMyOrder(mdto);
		
		Iterator<BasketDTO> it = listsAll.iterator();
		
		while(it.hasNext()) {
			
			BasketDTO bdto = it.next();
			
			OrderListDTO odto = new OrderListDTO();
			
			int num = ms.maxNumOrderList();
			
			odto.setNum(num+1);
			odto.setEmail(email);
			odto.setOrderNum(orderNum);
			odto.setType1(bdto.getType1());
			odto.setType2(bdto.getType2());
			odto.setModelName1(bdto.getModelName1());
			odto.setImageUrl(bdto.getImageUrl());
			odto.setPrice(bdto.getPrice());
			odto.setQuantity(bdto.getQuantity());
			
			ms.insertOrderList(odto);
			
		}
		
		bDao.deleteAllBasket(email);
		
		//오리지널********
				//커피
				List<BasketDTO> listsCo = bDao.getBasketList(email,"coffee","original");
				//머신		
				List<BasketDTO> listsMo = bDao.getBasketList(email,"machine","original");

				//버츄오**********
				//커피
				List<BasketDTO> listsCv = bDao.getBasketList(email,"coffee","vertuo");
				//머신
				List<BasketDTO> listsMv = bDao.getBasketList(email,"machine","vertuo");

				//액새서리********
				List<BasketDTO> listsAc = bDao.getBasketList(email,"acc","acc");

				//전체리스트 수******
				int totalLength = bDao.getBasketListCount(email);

				//전체리스트 제품수******
				int totalCount = 0;
				int totalPrices = 0;
				
				if(totalLength!=0) {
					 totalCount = bDao.getBasketCount(email);
					 totalPrices = bDao.getTotalPrice(email);
					
				}
				//총금액******
				

				session.setAttribute("listsCo", listsCo);
				session.setAttribute("listsMo", listsMo);
				session.setAttribute("listsCv", listsCv);
				session.setAttribute("listsMv", listsMv);
				session.setAttribute("listsAc", listsAc);
				session.setAttribute("totalLength", totalLength);
				session.setAttribute("totalCount", totalCount);
				session.setAttribute("totalPrice", totalPrices);
		

		return "basket/checkoutStep5";
	}

	
	
}
