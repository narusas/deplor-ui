package net.narusas.tools.deplor.domain.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import lombok.Getter;

public enum DeploySetStatus {
	미처리("U"), 취소됨("C"), 개발("D"), 테스트("T"), 운영("P"), LSB("L");
	@Getter
	final String	code;

	private DeploySetStatus(String code) {
		this.code = code;
	}

	@Converter(autoApply = true)
	public static class DeploySetStatusConverter implements AttributeConverter<DeploySetStatus, String> {

		@Override
		public String convertToDatabaseColumn(DeploySetStatus status) {
			return status.getCode();
		}

		@Override
		public DeploySetStatus convertToEntityAttribute(String status) {
			for (DeploySetStatus item : values()) {
				if (item.getCode().equals(status)) {
					return item;
				}
			}
			return 미처리;
		}

	}
}
