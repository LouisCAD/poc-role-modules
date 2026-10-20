package com.louiscad.poc.rolemodules.core.common.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Suppress("UnusedReceiverParameter")
expect val Dispatchers.IoOrDefault: CoroutineDispatcher
