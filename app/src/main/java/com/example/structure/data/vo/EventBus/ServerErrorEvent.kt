package com.smallticket.petping.data.vo.EventBus

/**
 * petping
 * Class: ServerErrorEvent
 * Created by Chan on 2022/12/07.
 *
 * Description:
 */
class ServerErrorEvent<T>(val apiCall: suspend () -> T) {

}