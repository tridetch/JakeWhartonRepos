package com.example.user.jakewhartonrepos.utils

import java.util.concurrent.Executor

class TestExecutor : Executor {
    override fun execute(runnable: Runnable) {
        runnable.run()
    }
}
