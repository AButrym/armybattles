package softserve.academy.demo

import org.slf4j.LoggerFactory

/**
 * Example class demonstrating SLF4J and Logback usage
 */
class LoggingExample {
    companion object {
        private val logger = LoggerFactory.getLogger(LoggingExample::class.java)

        @JvmStatic
        fun main(args: Array<String>) {
            logger.trace("This is a TRACE message")
            logger.debug("This is a DEBUG message")
            logger.info("This is an INFO message")
            logger.warn("This is a WARN message")
            logger.error("This is an ERROR message")

            try {
                throw RuntimeException("Test exception")
            } catch (e: Exception) {
                logger.error("An error occurred", e)
            }
        }
    }
}