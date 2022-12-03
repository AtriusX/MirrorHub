package xyz.atrius.logging

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.*

import xyz.atrius.logging.config.LogLevel

class LoggerSpec : DescribeSpec({

    isolationMode = IsolationMode.InstancePerLeaf

    describe("Logger tests") {

        val logger = spyk(Logger.create<LoggerSpec>())

        describe("Logging a message at the default logging level") {
            beforeTest {
                logger.info("Foo")
            }

            it("Should verify println was called correctly") {
                verify {
                    logger.printMessage(any(), LogLevel.INFO)
                }
            }
        }

        describe("Logging a message at a lower logging level") {
            beforeTest {
                logger.fatal("Bar")
            }

            it("Should verify println was called correctly") {
                verify {
                    logger.printMessage(any(), LogLevel.FATAL)
                }
            }
        }

        describe("When a message above the allowed logging level is sent") {
            beforeTest {
                logger.trace("Baz")
            }

            it("Should verify println was called correctly") {
                verify(exactly = 0) {
                    logger.printMessage(any(), LogLevel.TRACE)
                }
            }
        }
    }
})