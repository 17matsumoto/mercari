package com.example.demo.common;

/**
 * ユーザーの権限を管理する定数クラウ群.
 * @author matsumotoyuyya
 *
 */
public enum Authority {
	
	GUEST("guest", 0), USER("user", 1), ADMIN("admin", 2);

	private final String key;
	private final int value;
	
	
	/**
	 * 権限のIDから一致するインスタンスを返す.
	 * 
	 * @param id コンディションID
	 * @return 一致するインスタンス
	 */
	public static Authority getByValue(int id) {
		for (Authority authority : Authority.values()) {
			if (authority.getValue() == id) {
				return authority;
			}
		}
		throw new IllegalArgumentException("No such value for enum Authority: " + id);
	}

	private Authority(final String key, final int value) {
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
