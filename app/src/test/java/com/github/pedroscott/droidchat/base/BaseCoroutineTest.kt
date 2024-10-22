package com.github.pedroscott.droidchat.base

import com.github.pedroscott.droidchat.extension.CoroutineExtension
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
abstract class BaseCoroutineTest {

    protected val testScope = TestScope()
    protected val testDispatcher = UnconfinedTestDispatcher(testScope.testScheduler)

    @RegisterExtension
    protected val coroutineExtension = CoroutineExtension(testDispatcher)

    abstract fun setup()

    abstract fun tearDown()
}