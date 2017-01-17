package jp.co.topgate.kai.sekiguchi.ox.calculator;

import jp.co.topgate.kai.sekiguchi.ox.constantset.Moves;
import jp.co.topgate.kai.sekiguchi.ox.util.Counter;

/**
 * ScoreCalculator系クラスの継承元となるクラス
 * Created by sekiguchikai on 2017/01/06.
 */
public abstract class ScoreCalculator {


    /**
     * 現在のゲーム盤の点数を計算するためのメソッド
     *
     * @param gameBoard ゲーム盤
     * @return そのゲーム盤の点数の合計
     */
    public abstract int calcScore(Moves[][] gameBoard);

    /**
     * 引数として受け取った3つの打ち手の点数の合計を求める
     *
     * @param movesArray 一列分のMovesを格納した配列
     * @param maxPoint   自分の打ち手がラインが揃った時の点数
     * @param minPoint   相手の打ち手がラインが揃った時の点数
     * @return ラインの合計点数
     */
    protected int calcLineScore(final Moves[] movesArray, final int maxPoint, final int minPoint) {

        int score = 0;

        final int perTernPoint = 10;

        for (Moves moves : movesArray) {

            if (moves == Moves.CPU_MOVE) {
                score += perTernPoint;
            } else if (moves == Moves.USER_MOVE) {
                score -= perTernPoint;
            }
        }


        int counter = Counter.getCount();
        final int correctionValue = 100;

        int counterCorrectionValue = counter * correctionValue;


        final int finalMaxPoint = 100000;
        final int finalMinPoint = -100000;

        // 勝敗がつくときには、点数の差を大きくする
        if (score == maxPoint) {
            score = finalMaxPoint - counterCorrectionValue;
        } else if (score == minPoint) {
            score = finalMinPoint + counterCorrectionValue;
        }


        Counter.upCount();


        return score;


    }

}