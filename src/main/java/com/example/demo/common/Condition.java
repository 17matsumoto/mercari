package com.example.demo.common;

/**
 * コンディションを管理する定数クラス群.
 * 
 * @author matsumotoyuyya
 *
 */
public enum Condition {
	EXCELLENT("excellent", 1), VERY_GOOD("very good", 2), GOOD("good", 3), FAIR("fair", 4), POOR("poor", 5);

	private final String key;
	private final int value;

	/**
	 * コンディションのIDから一致するインスタンスを返す.
	 * 
	 * @param id コンディションID
	 * @return 一致するインスタンス
	 */
	public static Condition getByValue(int id) {
		for (Condition condition : Condition.values()) {
			if (condition.getValue() == id) {
				return condition;
			}
		}
		throw new IllegalArgumentException("No such value for enum Condition: " + id);
	}

	private Condition(final String key, final int value) {
		this.key = key;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public String getKey() {
		return key;
	}
}
