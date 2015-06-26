package net.narusas.tools.deplor.domain.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import lombok.Getter;

public enum DeploymentStatus {
	미제출("U"), 제출됨("S"), 거부됨("R"), 승인됨("A"), 완료됨("C");

	@Getter
	final private String	code;

	private DeploymentStatus(String code) {
		this.code = code;
	}

	@Converter(autoApply = true)
	public static class DeploymentStatusConverter implements AttributeConverter<DeploymentStatus, String> {

		@Override
		public String convertToDatabaseColumn(DeploymentStatus status) {
			return status.getCode();
		}

		@Override
		public DeploymentStatus convertToEntityAttribute(String status) {
			for (DeploymentStatus item : values()) {
				if (item.getCode().equals(status)) {
					return item;
				}
			}
			return 미제출;
		}

	}
}
