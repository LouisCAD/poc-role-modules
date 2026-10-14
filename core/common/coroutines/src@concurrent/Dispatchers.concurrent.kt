package com.louiscad.poc.rolemodules.core.common.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

internal actual val ioDispatcher: CoroutineDispatcher
    get() = Dispatchers.IO
