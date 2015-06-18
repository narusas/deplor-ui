package net.narusas.tools.deplor.crowler;

import java.io.File;

import net.narusas.tools.deplor.config.ApplicationConfig;
import net.narusas.tools.deplor.svn.Svn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ApplicationConfig.class })

public class SvnCrowlerTest {

	@Test
	public void test() {

	}

}
