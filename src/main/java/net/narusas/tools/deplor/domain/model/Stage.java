package net.narusas.tools.deplor.domain.model;

import lombok.Getter;

public enum Stage {
	개발("DEV"), 테스트("TST"), 운영("PRD");

	@Getter
	private final String	code;

	private Stage(String code) {
		this.code = code;
	}
}
