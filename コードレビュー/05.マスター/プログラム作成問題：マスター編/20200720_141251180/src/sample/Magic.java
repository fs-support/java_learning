package sample;

public class Magic {
	private String maginame; // 魔法名
	private int effect; // 効果
	private int consMP; // 消費MP

	// コンストラクタ
	Magic(String maginame, int effect, int consMP) {
		this.maginame = maginame;
		this.effect = effect;
		this.consMP = consMP;
	}

	// 魔法名を取得する
	String MagiName() {
		return maginame;
	}

	// 効果を取得する
	int Effect() {
		return effect;
	}

	// 消費MPを取得する
	int ConsMP() {
		return consMP;
	}
}
