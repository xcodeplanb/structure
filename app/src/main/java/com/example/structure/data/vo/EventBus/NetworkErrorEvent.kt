package com.smallticket.petping.data.vo.EventBus

class NetworkErrorEvent<T>(val apiCall: suspend () -> T) {
}