package net.narusas.tools.deplor.deploy.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DateLabel {
	@Getter
	final Date	date;

	@Override
	public String toString() {
		return new SimpleDateFormat("yyyy.MM.dd hh:mm:ss").format(date);
	}
}
