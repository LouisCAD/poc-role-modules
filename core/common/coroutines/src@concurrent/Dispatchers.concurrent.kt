package com.louiscad.poc.rolemodules.core.common.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

actual val Dispatchers.IoOrDefault: CoroutineDispatcher get() = Dispatchers.IO
