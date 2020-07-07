package com.nnx.common;

import static com.nnx.common.CommonConstants.CARRIAGE_RETURN_CHARACTER;
import static com.nnx.common.CommonConstants.LINE_FEED_CHARACTER;
import static com.nnx.common.CommonConstants.TAB_CHARACTER;
import static com.nnx.common.CommonConstants.UNDERSCORE_CHARACTER;

import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

public final class LoggerUtil {

    private LoggerUtil() {
    }

    public static void logInfo(Logger logger, String message, Object... args) {
        logger.info(message, args);
    }

    public static void logError(Logger logger, String message, Object... args) {
        logger.error(message, args);
    }

    public static void logDebug(Logger logger, String message, Object... args) {
        logger.debug(message, args);
    }

    public static void putParametersToLog(String event, String originator) {
        MDC.put(LoggingKey.EVENT.name(), encodeParameter(event));
        MDC.put(LoggingKey.ORIGINATOR.name(), encodeParameter(originator));
    }

    private static String encodeParameter(final String parameter) {
        String tempParameter = parameter;
        if (!StringUtils.isEmpty(tempParameter)) {
            tempParameter = tempParameter.replace(LINE_FEED_CHARACTER, UNDERSCORE_CHARACTER).replace(CARRIAGE_RETURN_CHARACTER, UNDERSCORE_CHARACTER)
                    .replace(TAB_CHARACTER, UNDERSCORE_CHARACTER);
        }
        return tempParameter;
    }
}
