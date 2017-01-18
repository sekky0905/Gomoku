package jp.co.topgate.kai.sekiguchi.ox.logic;

import jp.co.topgate.kai.sekiguchi.ox.board.Board;
import jp.co.topgate.kai.sekiguchi.ox.board.TicTacToeBoard;
import jp.co.topgate.kai.sekiguchi.ox.calculator.ScoreCalculator;
import jp.co.topgate.kai.sekiguchi.ox.calculator.TicTacToeScoreCalculator;
import jp.co.topgate.kai.sekiguchi.ox.judge.TicTacToeJudge;
import jp.co.topgate.kai.sekiguchi.ox.minimax.MiniMax;
import jp.co.topgate.kai.sekiguchi.ox.constantset.Result;
import jp.co.topgate.kai.sekiguchi.ox.io.TicTacToeCommandLineIO;
import jp.co.topgate.kai.sekiguchi.ox.judge.Judge;
import jp.co.topgate.kai.sekiguchi.ox.player.Cpu;
import jp.co.topgate.kai.sekiguchi.ox.player.Player;
import jp.co.topgate.kai.sekiguchi.ox.player.User;


/**
 * 三目並べのゲームの処理を進めていくクラス
 * Created by sekiguchikai on 2016/12/20.
 */
public class TicTacToeGameLogic extends GameLogic {
    // とりあえず user= ○, cpu= ×

    /**
     * ゲームを進めていくロジックを担当するメソッド
     *
     * @throws java.io.IOException コンソールからの入力を正常に受けてれませんでした
     */
    @Override
    public void playGame() {

        final int rowSize = 3;
        final int columnSize = 3;

        final Board ticTacToeBoard = new TicTacToeBoard(rowSize, columnSize);
        final TicTacToeCommandLineIO ticTacToeCommandLineIO = new TicTacToeCommandLineIO();

        final ScoreCalculator ticTacToeScoreCalculator = new TicTacToeScoreCalculator();

        final MiniMax miniMax = new MiniMax(ticTacToeScoreCalculator);
        final Player user = new User(ticTacToeBoard, miniMax, ticTacToeCommandLineIO, "あなた");
        final Player cpu = new Cpu(ticTacToeBoard, miniMax, ticTacToeCommandLineIO, "AI");
        final Judge ticTacToeJudge = new TicTacToeJudge();


        ticTacToeCommandLineIO.drawUI(ticTacToeBoard);

        final int depthCount = 2;


        while (ticTacToeJudge.judgeResult(ticTacToeBoard) == Result.PENDING) {
            user.doMove(depthCount);

            if (ticTacToeJudge.judgeResult(ticTacToeBoard) == Result.PENDING) {
                cpu.doMove(depthCount);
            }
        }
        ticTacToeCommandLineIO.drawResult(ticTacToeJudge.judgeResult(ticTacToeBoard));
    }
}
