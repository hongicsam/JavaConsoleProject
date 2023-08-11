package banking4;

public class MenuSelectException extends Exception{
	public MenuSelectException() {
		super("범위가 잘못되었습니다.\n1~6사이의 숫자를 입력해주세요");
	}
}
