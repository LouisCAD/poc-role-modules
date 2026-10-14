package com.louiscad.poc.rolemodules.core.common.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Suppress("UnusedReceiverParameter")
val Dispatchers.IO get() = ioDispatcher

internal expect val ioDispatcher: CoroutineDispatcher
