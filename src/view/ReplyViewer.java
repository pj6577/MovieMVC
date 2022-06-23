package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.ReplyDTO;
import controller.ReplyController;
import util.ScannerUtil;
import view.MovieViewer;

public class ReplyViewer {
	private ReplyController ReplyController;
	private Scanner scanner;
	private MovieViewer MovieViewer;
	private UserViewer UserViewer;

	public ReplyViewer() {
		ReplyController = new ReplyController();
		scanner = new Scanner(System.in);
	}

	public void setUserViewer(UserViewer UserViewer) {
		this.UserViewer = UserViewer;
	}

	public void setMovieViewer(MovieViewer MovieViewer) {
		this.MovieViewer = MovieViewer;
	}

	public void replyMenu() {
		System.out.println("1. 글 작성 2. 글 삭제 3. 뒤로가기");
		int userChoice = scanner.nextInt();
		if (userChoice == 1) {
			// writeReply();
		} else if (userChoice == 2) {
			// 글삭제
		} else if (userChoice == 3) {
			MovieViewer.showMoive();
		}
	}

	public void writeReply() {
		ReplyDTO r = new ReplyDTO();
		System.out.println("------");
		String message = "댓글 작성 ";
		r.setReplyContent(ScannerUtil.nextLine(scanner, message));
		ReplyController.ReplyInsert(r);
		System.out.println("------");

		System.out.println("1번 다른 영화 보기 0번 뒤로가기");
		int userChoice = scanner.nextInt();
		if (userChoice == 0) {
			replyMenu();
		} else if (userChoice == 1) {
			System.out.println("다른 영화 보기");
			MovieViewer.showMoive();
		}
	}

	// 댓글 출력
	public void printReplyAll(int movieNum) {
		ArrayList<ReplyDTO> temp = ReplyController.selectReplyAll();
		for (ReplyDTO r : temp) {
			System.out.printf("댓글번호: %d 댓글내용: %s\n", r.getReplyId(), r.getReplyContent(movieNum));
		}
	}

}
