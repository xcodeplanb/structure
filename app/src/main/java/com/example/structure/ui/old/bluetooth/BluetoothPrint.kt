package com.example.structure.ui.old.bluetooth

import java.io.ByteArrayOutputStream

open class BluetoothPrint {
    var buffer : ByteArrayOutputStream? = null

    init {
        buffer = ByteArrayOutputStream()
    }

    fun writeTitle(str: String) {
        writeBuffer(str.trimIndent())
        writeBuffer("\n")
    }

    fun writeLarge(str: String) {
        writeBuffer(str.trimIndent())
    }

    fun writeLargeln(str: String) {
        writeBuffer(str.trimIndent())
        writeBuffer("\n")
    }

    fun writeDottedLine() {
        writeBuffer("--------------------------------\n")
    }

    fun writeDottedDoubleLine() {
        writeBuffer("================================\n")
    }

    fun writeBuffer(str: String) {
        buffer?.write(str.toByteArray(charset("EUC-KR")))
    }
}