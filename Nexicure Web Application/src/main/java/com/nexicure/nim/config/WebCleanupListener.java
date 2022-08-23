package com.nexicure.nim.config;

import javax.servlet.ServletContextEvent;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.context.ContextCleanupListener;

public class WebCleanupListener extends ContextCleanupListener {
	protected static final Logger logCtx = LogManager.getLogger(WebCleanupListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		 logCtx.info("STOP Service Start");
		 System.gc();
		 logCtx.info("STOP Service End");
    }

}
