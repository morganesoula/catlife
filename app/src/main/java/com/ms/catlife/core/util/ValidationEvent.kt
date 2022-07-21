package com.ms.catlife.core.util

sealed class ValidationEvent {
    object Success : ValidationEvent()
}
