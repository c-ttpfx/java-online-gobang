package message;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/10/01/20:40
 * @Description: 为人所不为, 能人所不能
 */
public interface MessageType {
    String REGISTER = "1";
    String SUCCEED_REGISTER = "2";
    String FAIL_REGISTER = "3";
    String LOG_IN = "4";
    String SUCCEED_LOG_IN = "5";
    String FAIL_LOG_IN = "6";
    String CLOSE = "7";
    String SUCCEED_CLOSE = "8";
    String FAIL_CLOSE = "9";
    String PLAY_CHESS_NEXT = "10";
    String PLAY_CHESS_NEXT_SUCCEED = "11";
    String PLAY_CHESS_NEXT_FAIL = "12";
    String GIVE_MYSELF_INFO = "13";
    String GIVE_MYSELF_INFO_SUCCEED = "14";
    String GIVE_MYSELF_INFO_FAIL = "15";
    String GIVE_RIVAL_INFO = "16";
    String GIVE_RIVAL_INFO_SUCCEED = "17";
    String GIVE_RIVAL_INFO_FAIL = "18";
    String GIVE_ONLINE_USER_LIST = "19";
    String GIVE_ONLINE_USER_LIST_SUCCEED = "20";
    String GIVE_ONLINE_USER_LIST_FAIL = "21";
    String SET_PRICE_COLOR = "22";
    String PLAY_GAME = "23";
    String PLAY_GAME_SUCCEED = "24";
    String PLAY_GAME_FAIL = "25";
    String PLAY_GAME_RIVAL_CLOSE = "26";
    String REPEAT_LOGIN = "27";
    String GAME_OVER_VICTORY = "28";
    String GAME_OVER_FAIL = "29";
    String FREE_USER = "30";
    String GAME_USER = "31";
    String ALL_USER_STATE = "32";
    String GAME_START = "33";
    String GAME_OVER = "34";
}
