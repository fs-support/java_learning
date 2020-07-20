package fs05.refactoring;

// 【プレイヤーの並べ替えを管理するクラス】

public class SortOrder {

	private int sortVer; // 並べ替え仕様の設定用
	private int standard; // 並べ替え対象の基準用
	private int comparison; // 並べ替え対象の比較用

	// 実際に並べ替えるプレイヤーの「キープ」と「移動」に使用する
	private Player keepPlayer;
	private Player shiftPlayer;

	// コンストラクタ
	public SortOrder() {
		// 並べ替え仕様の初期設定は「0」(agi対象)
		this.sortVer = 0;
	}

	// 並べ替えの仕様(どのステータスを対象に並べ替えるか)を設定する
	public void setSortVer(int verNumber) {
		this.sortVer = verNumber;
	}

	// 並び替え前の準備 ※現時点ではGameManagerクラスのみで使用
	public void gathering(Party allMembers, Party register) {
		for (int i = 0; i < register.getMembers().size(); i++) {
			allMembers.getMembers().add(register.getMembers().get(i));
		}
	}

	// 条件毎に並べ替え
	public void orderBySort(Party sortTarget, String order) {
		for (int i = 0; i < sortTarget.getMembers().size() - 1; i++) {
			for (int j = 0; j < sortTarget.getMembers().size() - i - 1; j++) {

				// ソートの条件式にそのまま入れると長文になるので変数へ代入
				if (this.sortVer == 0) {
					// 並べ替え仕様が「初期設定」なら対象ステータスは「agi」
					standard = sortTarget.getMembers().get(j).getAgi();
					comparison = sortTarget.getMembers().get(j + 1).getAgi();
					// ▼並べ替え仕様が「それ以外」なら、変数sortVerの中身に合わせて対象ステータスを決定
					//   (現時点のソースコードでは別Verの対象ステータスは「hp」のみ)
				} else {
					standard = sortTarget.getMembers().get(j).getHp();
					comparison = sortTarget.getMembers().get(j + 1).getHp();
				}

				if (order.equals("昇順")) {
					// 昇順ソート(小さい順に並べ替え)
					if (comparison < standard) {
						sortExecution(sortTarget, j);
					}
				} else {
					// 降順ソート(大きい順に並べ替え)
					if (standard < comparison) {
						sortExecution(sortTarget, j);
					}
				}
			}
		}

		// 並べ替えが済んだら並べ替え仕様の設定を初期化
		this.sortVer = 0;
	}

	// 並べ替え実行(バブルソート)
	public void sortExecution(Party sortTarget, int lap) {
		keepPlayer = sortTarget.getMembers().get(lap);
		shiftPlayer = sortTarget.getMembers().get(lap + 1);
		sortTarget.getMembers().set(lap, shiftPlayer);
		sortTarget.getMembers().set(lap + 1, keepPlayer);
	}

}