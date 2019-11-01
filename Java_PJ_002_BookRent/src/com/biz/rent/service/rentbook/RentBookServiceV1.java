package com.biz.rent.service.rentbook;

import java.util.List;
import java.util.Scanner;

import com.biz.rent.config.DBConnection;
import com.biz.rent.dao.BookDao;
import com.biz.rent.dao.RentBookDao;
import com.biz.rent.dao.UserDao;
import com.biz.rent.persistence.RentBookDTO;
import com.biz.rent.persistence.UserDTO;

public class RentBookServiceV1 {
	
	protected BookDao bookDao;
	protected UserDao userDao;
	protected RentBookDao rentBookDao;
	protected Scanner scanner;
	
	public RentBookServiceV1() {
		this.bookDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(BookDao.class);
		this.userDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(UserDao.class);
		this.rentBookDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(RentBookDao.class);
		scanner = new Scanner(System.in);
	}
	
	/*

	public void rentBookInsert() {
		System.out.println("===============================================================");
		System.out.println("빛고을 대여 등록");
		System.out.println("---------------------------------------------------------------");
		
		while(true) {
			System.out.print("대여 등록을 하시겠습니까?(Enter:Yes, -Q:quit) >> ");
			String strYesNO = scanner.nextLine();
			if(strYesNO.equals("-Q")) break;
			
			if(strYesNO.trim().isEmpty()) {
				break;
			} else {
				System.out.println("Enter 또는 -Q를 눌러주세요.");
				continue;
			}
		}
		
		System.out.print("회원 코드 검색(-Q:quit) >> ");
		String strUCode = scanner.nextLine();
		
		if(!strUCode.trim().isEmpty()) {
			if(userDao.findById(strUCode) != null) {
				this.viewUDetail(userDTO);
			}
			
		}
		
		
		
		
		
		
		
	} // rentBookInsert end
	
	*/
	
	
	
	/*
	 * 	 * 일련번호	숫자	RENT_SEQ	NUMBER
대출일	문자열(30)	RENT_DATE	VARCHAR2(10)
반납예정일	문자열(10)	RENT_RETURN_DATE	VARCHAR2(10)
도서코드	문자열(6)	RENT_BCODE	VARCHAR2(6)
회원코드	문자열(6)	RENT_UCODE	VARCHAR2(6)
도서반납여부	문자열(1)	RENT_RETUR_YN	VARCHAR2(1)
포인트	숫자	RENT_POINT	NUMBER

	 */
	
	/*
	
	
	public void viewRentBookList() {
		List<RentBookDTO> rentBookList = rentBookDao.selectAll();
		System.out.println("===============================================================");
		System.out.println("빛고을 대여 목록");
		System.out.println("===============================================================");
		System.out.println("SEQ\t대출일\t반납예정일\t도서코드\t회원코드\t도서반납여부\t포인트");
		System.out.println("---------------------------------------------------------------");
		for(RentBookDTO rentBookDTO : rentBookList) {
			System.out.print(rentBookDTO.getRent_seq().getU_code() + "\t");
			System.out.print(rentBookDTO.getRent_date().getU_name() + "\t");
			System.out.print(rentBookDTO.getU_tel() + "\t");
			System.out.print(rentBookDTO.getU_addr() + "\n");
			System.out.print(rentBookDTO.getU_code() + "\t");
			System.out.print(rentBookDTO.getU_name() + "\t");
			System.out.print(rentBookDTO.getU_tel() + "\t");
			System.out.print(rentBookDTO.getU_addr() + "\n");

		}
		System.out.println("===============================================================");
	}
	
	
	
	protected void viewUDetail(UserDTO userDTO) {
		System.out.println("회원코드 : " + userDTO.getU_code());
		System.out.println("회원명 : " + userDTO.getU_name());
		System.out.println("전화번호 : " + userDTO.getU_tel());
		System.out.println("주소 : " + userDTO.getU_addr());
	}
	
	*/

	/*
	
	public void userInsert() {

		System.out.print("회원 코드 검색(-Q:quit) >> ");
		String strUCode = scanner.nextLine();
		
		if(!strUCode.trim().isEmpty()) {
			if(userDao.findById(strUCode) != null) {
				
			}
			
		}
		

		
		String strUCode;
		while(true) {
			System.out.print("회원코드(Enter:자동생성, Make:직접입력, -Q:quit) >> ");
			strUCode = scanner.nextLine();
			if(strUCode.trim().isEmpty()) {
				String strTMPCode = userDao.getMaxUCode();
				
				int intUCode = Integer.valueOf(strTMPCode.substring(1));
				intUCode++;
				strUCode = strTMPCode.substring(0, 1);
				strUCode += String.format("%05d", intUCode);
				
				System.out.println("생성된 코드 : " + strUCode);
				System.out.print("사용하시겠습니까?(Enter:Yes, 돌아가기:아무키나 누르세요.) >> ");
				String strYesNo = scanner.nextLine();
				if(strYesNo.trim().isEmpty()) break;
				else continue;
			} else if(strUCode.equals("Make")) {
				System.out.print("생성할 코드 입력 >> ");
				strUCode = scanner.nextLine();
				
				if(strUCode.length() != 6) {
					System.out.println("회원코드 길이가 맞지 않습니다.");
					continue;
				}
				
				strUCode = strUCode.toUpperCase();
				if (!strUCode.substring(0, 1).equals("S")) {
					System.out.println("회원코드는 첫글자는 S로 시작되어야 합니다.");
					continue;
				}
				
				try {
					Integer.valueOf(strUCode.substring(1));
				} catch (Exception e) {
					System.out.println("도서코드 2번째 이후는 숫자만 올 수 있습니다.");
					continue;
				}
				
				if (userDao.findById(strUCode) != null) {
					System.out.println("이미 등록된(사용중인) 코드입니다.");
					continue;
				}
				break;
			} else if (strUCode.equals("-Q")) {
				return;
			} else {
				System.out.println("Enter, Make, -Q 중에서 입력해주세요.");
				continue;
			}
		}
		
		String strUName;
		while(true) {
			System.out.print("회원명(-Q:quit) >> ");
			strUName = scanner.nextLine();
			if(strUName.equals("-Q")) break;
			
			if(strUName.trim().isEmpty()) {
				System.out.println("회원명은 반드시 입력해야 합니다.");
				continue;
			}
			
			UserDTO userDTO = userDao.findBySName(strUName);
			if(userDao.findBySName(strUName) != null) {
				this.viewUDetails(userDTO);
				continue;
			}
			break;
		}
			
		String strUTel;
		while(true) {
			System.out.print("전화번호(-Q:quit) >> ");
			strUTel = scanner.nextLine();
			if(strUTel.equals("-Q")) break;
			
			if(strUTel.trim().isEmpty()) {
				System.out.println("전화번호는 반드시 입력해야 합니다.");
				continue;
			}
			
			UserDTO userDTO = userDao.findBySName(strUTel);
			if(userDao.findBySTel(strUTel) != null) {
				this.viewUDetails(userDTO);
			}
			break;
		}

		String strUAddr;
		while(true) {
			System.out.print("주소(-Q:quit) >> ");
			strUAddr = scanner.nextLine();
			if(strUAddr.equals("-Q")) break;
			
			if(strUAddr.trim().isEmpty()) {
				System.out.println("주소는 반드시 입력해야 합니다.");
				continue;
			}
			break;
		}

		UserDTO userDTO = UserDTO.builder()
				.u_code(strUCode)
				.u_name(strUName)
				.u_tel(strUTel)
				.u_addr(strUAddr)
				.build();

		int ret = userDao.insert(userDTO);
		System.out.println("-------------------------------------------------------");
		System.out.print("이대로 등록하시겠습니까?(Enter:Yes, -Q:quit) >> ");
		String strYes = scanner.nextLine();
		if(strYes.equals("-Q")) return;
		
		if(strYes.trim().isEmpty()) {
			if(ret > 0) System.out.println("회원 등록 성공");
			else System.out.println("회원 등록 실패");
		}
	}
	*/
	
	/*
	 * 
	 * 
	 * 일련번호	숫자	RENT_SEQ	NUMBER
대출일	문자열(30)	RENT_DATE	VARCHAR2(10)
반납예정일	문자열(10)	RENT_RETURN_DATE	VARCHAR2(10)
도서코드	문자열(6)	RENT_BCODE	VARCHAR2(6)
회원코드	문자열(6)	RENT_UCODE	VARCHAR2(6)
도서반납여부	문자열(1)	RENT_RETUR_YN	VARCHAR2(1)
포인트	숫자	RENT_POINT	NUMBER



	
	
	
	
	protected void viewRDetail(UserDTO userDTO) {
		일련번호 도서정보 회원정보 대여일 반납예정일 반납여부 
		System.out.println("회원코드 : " + userDTO.getU_code());
		System.out.println("회원명 : " + userDTO.getU_name());
		System.out.println("전화번호 : " + userDTO.getU_tel());
		System.out.println("주소 : " + userDTO.getU_addr());
	}
	
	protected void viewUDetails(UserDTO userDTO) {
		System.out.println("=====================================");
		System.out.println("이미 같은 회원이 있습니다.");
		System.out.println("-------------------------------------");
		this.viewUDetail(userDTO);
		System.out.println("=====================================");
	}
	
	public void viewUserList() {
		List<UserDTO> userList = userDao.selectAll();
		System.out.println("===============================================================");
		System.out.println("빛고을 회원 목록");
		System.out.println("===============================================================");
		System.out.println("회원코드\t회원명\t전화번호\t주소");
		System.out.println("---------------------------------------------------------------");
		for(UserDTO user : userList) {
			System.out.print(user.getU_code() + "\t");
			System.out.print(user.getU_name() + "\t");
			System.out.print(user.getU_tel() + "\t");
			System.out.print(user.getU_addr() + "\n");

		}
		System.out.println("===============================================================");
	}
	
	
	
	 */

	
	
}