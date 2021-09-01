package com.exe.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.exe.dto.BasketDTO;
import com.exe.dto.CommentDTO;
import com.exe.dto.FaqDTO;
import com.exe.dto.ImageFileDTO;
import com.exe.dto.LoginDTO;
import com.exe.dto.NesAoDTO;
import com.exe.dto.NesCoDTO;
import com.exe.dto.NesCvDTO;
import com.exe.dto.NesMoDTO;
import com.exe.dto.NesMvDTO;
import com.exe.dto.NoticeDTO;
import com.exe.dto.ProfileDTO;
import com.exe.dto.StoryDTO;

	public class BasketDAO {
	
	private SqlSessionTemplate sessionTemplate;

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}
	
	//maxNum
	public int getBasketMaxNum() {
		System.out.println("basketMaxNum");
		int maxNum = 0;
			
		maxNum = sessionTemplate.selectOne("com.basketMapper.getBasketMaxNum");
			
		return maxNum;
	}
	
	//��ٱ��� �߰�
	public void insertData(BasketDTO dto) {
		System.out.println("��ٱ��� �߰� DAO");
		sessionTemplate.insert("com.basketMapper.insertBasket", dto);
		
	}
	
	//��ٱ��� ����
	public void updateDate(BasketDTO dto) {
		System.out.println("��ٱ��� ���� DAO");
		sessionTemplate.update("com.basketMapper.updateBasket", dto);
	}
	
	//��ٱ��� ����
	public void deleteDate(BasketDTO dto) {
		System.out.println("��ٱ��� ���� DAO");
		sessionTemplate.delete("com.basketMapper.deleteBasket", dto);
	}
	
	//��ٱ��� ����Ʈ �˻�(�� ��ǰ�� ����)
	//����Ʈ ��¿�
	public List<BasketDTO> getBasketList(String email,String type1, String type2){		
		System.out.print("��ٱ��� ����Ʈ DAO: " + email + ", " + type1 + ", " + type2);
		Map<String, Object> hmap = new HashMap<String, Object>();
		
		hmap.put("email", email);
		hmap.put("type1", type1);
		hmap.put("type2", type2);
		
		List<BasketDTO> lists = 
				sessionTemplate.selectList("com.basketMapper.getBasketList", hmap);		
		System.out.println("lists������: " + lists.size());
		return lists;
	}
	
	//��ٱ��� ����Ʈ �˻�(��ǰ�� ����)
	//��ǰ �߰� �� ���� �����
	public List<BasketDTO> getBasketListSearch(BasketDTO dto){		
		System.out.print("��ٱ��� ����Ʈ �˻� DAO: ");
			
		List<BasketDTO> lists = 
				sessionTemplate.selectList("com.basketMapper.getBasketListSearch", dto);		
		System.out.println("lists������: " + lists.size());
		return lists;
		}
	
	//��ٱ��� ��ü ����Ʈ
	public List<BasketDTO> getBasketAllList(String email){		
		System.out.print("��ٱ��� ��ü ����Ʈ DAO: " + email);
		
		List<BasketDTO> lists = 
				sessionTemplate.selectList("com.basketMapper.getBasketAllList", email);	
		
		System.out.println(", " + lists.size());
		return lists;
	}
	//��ٱ��� ����Ʈ ����
	public int getBasketListCount(String email){		
		System.out.print("��ٱ��� ����Ʈ ���� DAO: " + email);
		
		int su = 0;
		
		su = sessionTemplate.selectOne("com.basketMapper.getBasketListCount", email);
		
		System.out.println(", " + su);
		return su;
	}
	
	//��ٱ��� ��ǰ �� ����
	public int getBasketCount(String email){		
		System.out.print("��ٱ��� ��ǰ �� ���� DAO: " + email);
		int su = 0;
		
		su = sessionTemplate.selectOne("com.basketMapper.getBasketCount", email);
				
		System.out.println(", " + su);
		return su;
	}
	
	//��ٱ��� �ѱݾ�
	public int getTotalPrice(String email){		
		System.out.println("��ٱ��� �ѱݾ� DAO: " + email);
		
		int totalPrice = 0;
		
		totalPrice = sessionTemplate.selectOne("com.basketMapper.getTotalPrice", email);	
						
		return totalPrice;
	}
	
	//��ٱ��� ��� ����
	public void deleteAllBasket(String email){		
		System.out.println("��ٱ��� ��� ���� DAO: " + email);
			
		sessionTemplate.selectOne("com.basketMapper.deleteAllBasket", email);			
	}		
	
	//�α���
	public LoginDTO getLoginUser(String email){		
		System.out.println("email: " + email);
		
		LoginDTO dto = sessionTemplate.selectOne("com.exe.mainPage.UserDaoInterface.loginUser", email);	
		
		return dto;
	}
	
	
	
	
	
//-��
	//FAQ����/////////////�Է�
	public void insertData(FaqDTO dto) {
		
		sessionTemplate.insert("com.faqMapper.faqinsertData", dto);
		
	}
	
	public int faqgetMaxNum() {
		
		int maxNum=0;
		
		maxNum = sessionTemplate.selectOne("com.faqMapper.faqmaxNum");
		
		return maxNum;
	}
	
	//��ü�������� ����   //option,�˻�����
	public int getDataCount(String searchKey,String searchValue) {
		
		int totalDataCount=0;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchKey", searchKey);
		params.put("searchValue", searchValue);
		
		totalDataCount = sessionTemplate.selectOne("com.faqMapper.faqgetDataCount",params);
		
		return totalDataCount;
	}
	
	//---------------------------------------------------------------------------------------
	//��ü�������� ����   //option,�˻�����
	public int getDataCount1(String searchKey,String searchValue,String cate) {
		
		int totalDataCount=0;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchKey", searchKey);
		params.put("searchValue", searchValue);
		params.put("cate", cate);
		
		totalDataCount = sessionTemplate.selectOne("com.faqMapper.faqgetDataCount1",params);
		
		return totalDataCount;
	}
	//---------------------------------------------------------------------------------------

	
	
	//ǥ���� ������(rownum ����) ������
	public List<FaqDTO> getList(int start,int end,String searchKey,String searchValue) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("end", end);
		params.put("searchKey", searchKey);
		params.put("searchValue", searchValue);
		
		List<FaqDTO> lists = sessionTemplate.selectList("com.faqMapper.faqgetLists",params);
		
		return lists;
		
	}
	//------------------------------------------------------------------------------------------------------------
	//ǥ���� ������(rownum ����) ������
	public List<FaqDTO> getList1(int start,int end,String searchKey,String searchValue,String cate) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("end", end);
		params.put("searchKey", searchKey);
		params.put("searchValue", searchValue);
		params.put("cate", cate);
		
		List<FaqDTO> lists = sessionTemplate.selectList("com.faqMapper.faqgetLists1",params);
		
		return lists;
		
	}
	//------------------------------------------------------------------------------------------------------------
	
	//num���� ��ȸ�� �Ѱ��� ������
	public FaqDTO getReadData(int fnum) {
		
		FaqDTO dto = sessionTemplate.selectOne("com.faqMapper.faqgetReadData",fnum);
		
		return dto;
	}
	
	//��ȸ�� ����
	public void updateHitCount(int num) {
		
		sessionTemplate.update("com.faqMapper.faqupdateHitCount", num);
	
	}
	
	//����
	public void updateData(FaqDTO dto) {

		sessionTemplate.update("com.faqMapper.faqupdateData", dto);
	
	}
	
	//����
	public void deleteData(int num) {
		
		sessionTemplate.delete("com.faqMapper.faqdeleteData", num);
	
	}
	
//------------------------NOTICE
	public int ngetMaxNum() {
		
		int maxNum=0;
		
		maxNum = sessionTemplate.selectOne("com.faqMapper.faqnoticemaxNum");
		
		return maxNum;
	}
	
	//�Է�
	public void ninsertData(NoticeDTO dto) {
		
		sessionTemplate.insert("com.faqMapper.faqnoticeinsertData", dto);
		
	}
	
	//��ü�������� ����   //option,�˻�����
	public int ngetDataCount(String searchKey,String searchValue) {
		
		int totalDataCount=0;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchKey", searchKey);
		params.put("searchValue", searchValue);
		
		totalDataCount = sessionTemplate.selectOne("com.faqMapper.faqngetDataCount",params);
		
		return totalDataCount;
	}
	
	//ǥ���� ������(rownum ����) ������
	public List<NoticeDTO> ngetList(int start,int end,String searchKey,String searchValue) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("end", end);
		params.put("searchKey", searchKey);
		params.put("searchValue", searchValue);
		
		List<NoticeDTO> lists = sessionTemplate.selectList("com.faqMapper.faqngetLists",params);
		
		return lists;
		
	}
	//num���� ��ȸ�� �Ѱ��� ������
		public NoticeDTO ngetReadData(int nnum) {
			
			NoticeDTO dto = sessionTemplate.selectOne("com.faqMapper.faqngetReadData",nnum);
			
			return dto;
		}
	
	
	
	
	//------------MACHINE@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	public int mgetMaxNum() {
		
		int maxNum=0;
		
		maxNum = sessionTemplate.selectOne("com.faqMapper.faqmachinemaxNum");
		
		return maxNum;
	}
	
	
	//��ü�������� ����   //option,�˻�����
	public int mgetDataCount(String searchKey,String searchValue) {
		
		int totalDataCount=0;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchKey", searchKey);
		params.put("searchValue", searchValue);
		
		totalDataCount = sessionTemplate.selectOne("com.faqMapper.faqmgetDataCount",params);
		
		return totalDataCount;
	}
	

	//����
	public void nupdateData(NoticeDTO dto) {

		sessionTemplate.update("com.faqMapper.faqnupdateData", dto);
	
	}

//������ǰ
	//maxNum
	public int getMaxNum1() {
			
		int maxNum1 = 0;
			
		maxNum1 = sessionTemplate.selectOne("com.nesPdMapper.pdmaxNum1");
			
		return maxNum1;
	}
	
	public int getMaxNum2() {
		
		int maxNum2 = 0;
				
		maxNum2 = sessionTemplate.selectOne("com.nesPdMapper.pdmaxNum2");
				
		return maxNum2;
	}

	public int getMaxNum3() {
		
		int maxNum3 = 0;
				
		maxNum3 = sessionTemplate.selectOne("com.nesPdMapper.pdmaxNum3");
				
		return maxNum3;
	}
	
	public int getMaxNum4() {
		
		int maxNum4 = 0;
				
		maxNum4 = sessionTemplate.selectOne("com.nesPdMapper.pdmaxNum4");
				
		return maxNum4;
	}
	
	public int getMaxNum5() {
		
		int maxNum5 = 0;
				
		maxNum5 = sessionTemplate.selectOne("com.nesPdMapper.pdmaxNum5");
				
		return maxNum5;
	}

	//insert
	public void insertData1(NesCoDTO dto) {
		
		sessionTemplate.insert("com.nesPdMapper.pdinsertData1", dto);
		
	}
	
	public void insertData2(NesCvDTO dto) {
		
		sessionTemplate.insert("com.nesPdMapper.pdinsertData2", dto);
		
	}
	
	public void insertData3(NesMoDTO dto) {
		
		sessionTemplate.insert("com.nesPdMapper.pdinsertData3", dto);
		
	}
	
	public void insertData4(NesMvDTO dto) {
		
		sessionTemplate.insert("com.nesPdMapper.pdinsertData4", dto);
		
	}
	
	public void insertData5(NesAoDTO dto) {
		
		sessionTemplate.insert("com.nesPdMapper.pdinsertData5", dto);
		
	}
	
	//list(Ŀ��)
	public List<NesCoDTO> getLists1() {
		
		List<NesCoDTO> lists1 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists1");
				
		return lists1;
		
	}
	
	public List<NesCoDTO> getLists2() {
		
		List<NesCoDTO> lists2 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists2");
				
		return lists2;
		
	}
	
	public List<NesCoDTO> getLists3() {
		
		List<NesCoDTO> lists3 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists3");
				
		return lists3;
		
	}
	
	public List<NesCoDTO> getLists4() {
		
		List<NesCoDTO> lists4 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists4");
				
		return lists4;
		
	}
	
	public List<NesCoDTO> getLists5() {
		
		List<NesCoDTO> lists5 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists5");
				
		return lists5;
		
	}

	public List<NesCoDTO> getLists6() {
	
		List<NesCoDTO> lists6 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists6");
				
		return lists6;
	
	}
	
	public List<NesCvDTO> getLists7() {
		
		List<NesCvDTO> lists7 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists7");
				
		return lists7;
	
	}
	
	public List<NesCvDTO> getLists8() {
		
		List<NesCvDTO> lists8 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists8");
				
		return lists8;
	
	}
	
	public List<NesCvDTO> getLists9() {
		
		List<NesCvDTO> lists9 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists9");
				
		return lists9;
	
	}
	
	public List<NesCvDTO> getLists10() {
		
		List<NesCvDTO> lists10 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists10");
				
		return lists10;
	
	}
	
	public List<NesCvDTO> getLists11() {
		
		List<NesCvDTO> lists11 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists11");
				
		return lists11;
	
	}
	
	public List<NesCvDTO> getLists12() {
		
		List<NesCvDTO> lists12 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists12");
				
		return lists12;
	
	}
	
	public List<NesCvDTO> getLists13() {
		
		List<NesCvDTO> lists13 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists13");
				
		return lists13;
	
	}
	
	public List<NesCvDTO> getLists14() {
		
		List<NesCvDTO> lists14 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists14");
				
		return lists14;
	
	}
	
	//list(�ӽ�)
	public List<NesMoDTO> getLists15() {
		
		List<NesMoDTO> lists15 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists15");
				
		return lists15;
	
	}
	
	public List<NesMvDTO> getLists16() {
		
		List<NesMvDTO> lists16 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists16");
					
		return lists16;
		
	}
	
	//list(�׼�����)
	public List<NesAoDTO> getLists17() {
		
		List<NesAoDTO> lists17 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists17");
					
		return lists17;
		
	}
	
	public List<NesAoDTO> getLists18() {
		
		List<NesAoDTO> lists18 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists18");
					
		return lists18;
		
	}
	
	public List<NesAoDTO> getLists19() {
		
		List<NesAoDTO> lists19 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists19");
					
		return lists19;
		
	}
	
	public List<NesAoDTO> getLists20() {
		
		List<NesAoDTO> lists20 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists20");
					
		return lists20;
		
	}
	
	public List<NesAoDTO> getLists21() {
		
		List<NesAoDTO> lists21 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists21");
					
		return lists21;
		
	}
	
	public List<NesAoDTO> getLists22() {
		
		List<NesAoDTO> lists22 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists22");
					
		return lists22;
		
	}
	
	public List<NesAoDTO> getLists23() {
		
		List<NesAoDTO> lists23 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists23");
					
		return lists23;
		
	}
	
	public List<NesAoDTO> getLists24() {
		
		List<NesAoDTO> lists24 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists24");
					
		return lists24;
		
	}
	
	public List<NesAoDTO> getLists25() {
		
		List<NesAoDTO> lists25 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists25");
					
		return lists25;
		
	}
	
	public List<NesAoDTO> getLists26() {
		
		List<NesAoDTO> lists26 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists26");
					
		return lists26;
		
	}
	
	public List<NesAoDTO> getLists27() {
		
		List<NesAoDTO> lists27 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists27");
					
		return lists27;
		
	}
	
	public List<NesAoDTO> getLists28() {
		
		List<NesAoDTO> lists28 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists28");
					
		return lists28;
		
	}
	
	public List<NesAoDTO> getLists29() {
		
		List<NesAoDTO> lists29 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists29");
					
		return lists29;
		
	}
	
	public List<NesAoDTO> getLists30() {
		
		List<NesAoDTO> lists30 = sessionTemplate.selectList("com.nesPdMapper.pdgetLists30");
					
		return lists30;
		
	}
	
	//article(ĸ��Ŀ��)
	public NesCoDTO getReadData1(int co_num) {
		
		NesCoDTO dto = sessionTemplate.selectOne("com.nesPdMapper.pdgetReadData1", co_num);
		
		return dto;
		
	}
	
	public NesCvDTO getReadData2(int cv_num) {
		
		NesCvDTO dto = sessionTemplate.selectOne("com.nesPdMapper.pdgetReadData2", cv_num);
		
		return dto;
		
	}
	
	//article(�ӽ�)
	public NesMoDTO getReadData3(int mo_num) {
		
		NesMoDTO dto = sessionTemplate.selectOne("com.nesPdMapper.pdgetReadData3", mo_num);
		
		return dto;
		
	}
	
	public NesMvDTO getReadData4(int mv_num) {
		
		NesMvDTO dto = sessionTemplate.selectOne("com.nesPdMapper.pdgetReadData4", mv_num);
		
		return dto;
		
	}
	
	//article(�׼�����)
	public NesAoDTO getReadData5(int ao_num) {
		
		NesAoDTO dto = sessionTemplate.selectOne("com.nesPdMapper.pdgetReadData5", ao_num);
		
		return dto;
		
	}
//������ǰ��
	
	
//��ȯ����//ȸ������
	public void bpinsertData(ProfileDTO dto) {
		sessionTemplate.insert("com.boardMapper.bpinsertData",dto);
	}
	
	//������ ���� ����
	public void bpupdateData(ProfileDTO dto) {
		sessionTemplate.update("com.boardMapper.bpupdateData", dto);
	}
	
	//������ ������ ����
	public void bpupdateData2(ProfileDTO dto) {
		sessionTemplate.update("com.boardMapper.bpupdateData2", dto);
	}
	
	//���� ���ε�
	public void insertData(List<ImageFileDTO> lists,StoryDTO dto2) {
		Iterator<ImageFileDTO> it=lists.iterator();
		
		ImageFileDTO dto1;
		
		while(it.hasNext()) {
			dto1=it.next();
			sessionTemplate.insert("com.boardMapper.biinsertData",dto1);
		}
		
		sessionTemplate.insert("com.boardMapper.bsinsertData",dto2);
	}
	
	//��� ���ε�
	public void bcinsertData(CommentDTO dto) {
		sessionTemplate.insert("com.boardMapper.bcinsertData",dto);
	}
	
	//num�� �ִ밪
	public int getMaxNum() {
		int maxNum=0;

		maxNum=sessionTemplate.selectOne("com.boardMapper.bsmaxNum");

		return maxNum;
	}
	
	//comment_num�� �ִ밪
	public int bcmaxNum() {
		int maxNum=0;

		maxNum=sessionTemplate.selectOne("com.boardMapper.bcmaxNum");

		return maxNum;
	}
	
	//������ ������
	public ProfileDTO bpgetReadData(String pageUserEmail) {
		ProfileDTO dto=sessionTemplate.selectOne("com.boardMapper.bpgetReadData", pageUserEmail);;
		
		return dto;
	}
	
	//������ �̹��� ����Ʈ
	public List<ImageFileDTO> getList(String pageUserEmail){
		List<ImageFileDTO> lists=sessionTemplate.selectList("com.boardMapper.bigetLists1", pageUserEmail);
		
		return lists;
	}
	
	//���丮 ���� ������
	public StoryDTO bsgetReadData(String pageUserEmail,int num) {
		HashMap<String, Object> params=new HashMap<String, Object>();
		
		params.put("pageUserEmail", pageUserEmail);
		params.put("num", num);
		
		StoryDTO dto=sessionTemplate.selectOne("com.boardMapper.bsgetReadData", params);
		
		return dto;	
	}
	
	//���丮 �̹��� ����Ʈ
	public List<CommentDTO> bcgetLists(String pageUserEmail,int num) {
		HashMap<String, Object> params=new HashMap<String, Object>();
		
		params.put("pageUserEmail", pageUserEmail);
		params.put("num", num);	
		
		List<CommentDTO> lists=sessionTemplate.selectList("com.boardMapper.bcgetLists", params);
		
		return lists;	
	}
	
	//���丮 �̹��� ����Ʈ
	public List<ImageFileDTO> bigetLists2(String pageUserEmail,int num) {
		HashMap<String, Object> params=new HashMap<String, Object>();
		
		params.put("pageUserEmail", pageUserEmail);
		params.put("num", num);
		
		List<ImageFileDTO> lists=sessionTemplate.selectList("com.boardMapper.bigetLists2", params);
		
		return lists;	
	}
	
	
	//��� ����
	public void bcdeleteData(String pageUserEmail,int num,int comment_num) {
		HashMap<String, Object> params=new HashMap<String, Object>();
		
		params.put("pageUserEmail", pageUserEmail);
		params.put("num", num);
		params.put("comment_num", comment_num);
		
		sessionTemplate.delete("com.boardMapper.bcdeleteData",params);
	}
	
	//���� �˻�
	public List<ProfileDTO> bpgetList(String searchValue){
		List<ProfileDTO> lists=sessionTemplate.selectList("com.boardMapper.bpgetList",searchValue);
		
		return lists;
	}
	
//��ȯ���峡
	

	
	
}
