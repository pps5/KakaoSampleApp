package io.github.pps5.kakaosampleapp.common.vo

data class Resource<T>(
    val status: Status,
    val value: T?
) {
    enum class Status {
        Loading, Success, Failure
    }

    companion object {
        fun <T> success(value: T) = Resource(
            Status.Success,
            value
        )
        fun <T> failure() = Resource<T>(
            Status.Failure,
            null
        )
        fun <T> loading() = Resource<T>(
            Status.Loading,
            null
        )
    }
}
