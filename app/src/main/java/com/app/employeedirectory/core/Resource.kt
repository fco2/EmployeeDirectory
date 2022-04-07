package com.app.employeedirectory.core

//wrapper class to show state of data for Loading, Success and Error, and enable propagation of either data or messages in the case of an Error encountered to the presentation layer.
sealed class Resource<T>(val data: T? = null, val message: String? = null){
    class Loading<T>(data: T? = null): Resource<T>(data)
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
}