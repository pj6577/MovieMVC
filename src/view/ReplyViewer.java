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
		System.out.println("1. �� �ۼ� 2. �� ���� 3. �ڷΰ���");
		int userChoice = scanner.nextInt();
		if (userChoice == 1) {
			// writeReply();
		} else if (userChoice == 2) {
			// �ۻ���
		} else if (userChoice == 3) {
			MovieViewer.showMoive();
		}
	}

	public void writeReply() {
		ReplyDTO r = new ReplyDTO();
		System.out.println("------");
		String message = "��� �ۼ� ";
		r.setReplyContent(ScannerUtil.nextLine(scanner, message));
		ReplyController.ReplyInsert(r);
		System.out.println("------");

		System.out.println("1�� �ٸ� ��ȭ ���� 0�� �ڷΰ���");
		int userChoice = scanner.nextInt();
		if (userChoice == 0) {
			replyMenu();
		} else if (userChoice == 1) {
			System.out.println("�ٸ� ��ȭ ����");
			MovieViewer.showMoive();
		}
	}

	// ��� ���
	public void printReplyAll(int movieNum) {
		ArrayList<ReplyDTO> temp = ReplyController.selectReplyAll();
		for (ReplyDTO r : temp) {
			System.out.printf("��۹�ȣ: %d ��۳���: %s\n", r.getReplyId(), r.getReplyContent(movieNum));
		}
	}

}
