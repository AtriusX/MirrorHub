package xyz.atrius.logging

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.koin.KoinExtension
import io.kotest.koin.KoinLifecycleMode
import io.mockk.*
import org.koin.ksp.generated.module
import org.koin.test.KoinTest
import org.koin.test.inject
import xyz.atrius.config.AppConfig

import xyz.atrius.logging.config.LogLevel

class LoggerSpec : DescribeSpec(), KoinTest {

    private val loggerProvider by inject<LoggerProvider>()

    override fun extensions() = listOf(
        KoinExtension(
            AppConfig().module,
            mode = KoinLifecycleMode.Root
        )
    )

    init {
        describe("Logger tests") {
            val logger = spyk(loggerProvider.create<LoggerSpec>())

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
    }
}