//package com.example.structure.ui.old.bluetooth
//
//import android.app.Activity
//import android.bluetooth.BluetoothAdapter
//import android.bluetooth.BluetoothDevice
//import android.bluetooth.BluetoothSocket
//import android.content.Intent
//import androidx.fragment.app.Fragment
//import java.io.IOException
//import java.io.InputStream
//import java.io.OutputStream
//import java.util.*
//
//class BluetoothService(val fragment: Fragment) {
//    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
//    var connectThread: ConnectThread? = null
//    var connectedThread: ConnectedThread? = null
//
//    fun start() {
//        if (bluetoothAdapter == null) {
//            return
//        }
//
//        if (!bluetoothAdapter.isEnabled) {
//            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
//            fragment.startActivityForResult(
//                enableBtIntent,
//                REQUEST_ENABLE_BLUETOOTH_PRINT
//            )
//            return
//        }
//
//        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter.bondedDevices
//        var prindAddress = ""
//
//        pairedDevices?.forEach { device ->
//            prindAddress = device.address
//            return@forEach
//        }
//
//        bluetoothAdapter.getRemoteDevice(prindAddress)?.let {
//            connect(it, true)
//        }
//    }
//
//    @Synchronized
//    fun connect(device: BluetoothDevice, secure: Boolean) {
//        stop()
//
//        // Start the thread to connect with the given device
//        connectThread = ConnectThread(device)
//        connectThread?.start()
//    }
//
//    @Synchronized
//    fun connected(
//        socket: BluetoothSocket?
//    ) {
//        stop()
//
//        // Start the thread to manage the connection and perform transmissions
//        connectedThread = socket?.let { ConnectedThread(it) }
//        connectedThread?.start()
//    }
//
//    fun stop() {
//
//        if (connectThread != null) {
//            connectThread?.cancel()
//            connectThread = null
//        }
//        if (connectedThread != null) {
//            connectedThread?.cancel()
//            connectedThread = null;
//        }
//
//    }
//
//    inner class ConnectThread(mmDevice: BluetoothDevice) :
//        Thread() {
//        private val mmSocket: BluetoothSocket?
//        override fun run() {
//            // Always cancel discovery because it will slow down a connection
//            bluetoothAdapter?.cancelDiscovery()
//
//            // Make a connection to the BluetoothSocket
//            try {
//                // This is a blocking call and will only return on a
//                // successful connection or an exception
//                mmSocket?.connect()
//            } catch (e: IOException) {
//                // Close the socket
//                try {
//                    mmSocket?.close()
//                } catch (e: IOException) {
//                    //Do Nothing
//                }
//                this@BluetoothService.stop()
//                return
//            }
//
//            // Reset the ConnectThread because we're done
//            synchronized(this@BluetoothService) { connectThread = null }
//
//            // Start the connected thread
//            connected(mmSocket)
//        }
//
//        fun cancel() {
//            try {
//                mmSocket?.close()
//            } catch (e: IOException) {
//                //Do Nothing
//            }
//        }
//
//        init {
//            var tmp: BluetoothSocket? = null
//            // Get a BluetoothSocket for a connection with the
//            // given BluetoothDevice
//            try {
//                tmp = mmDevice.createRfcommSocketToServiceRecord(uuid)
//            } catch (e: IOException) {
//                //Do Nothing
//            }
//            mmSocket = tmp
//        }
//    }
//
//    inner class ConnectedThread(socket: BluetoothSocket) :
//        Thread() {
//        private val mmSocket: BluetoothSocket
//        private val mmInStream: InputStream?
//        private val mmOutStream: OutputStream?
//        override fun run() {
//            val buffer = ByteArray(1024)
//            var bytes: Int
//
//            // Keep listening to the InputStream while connected
//            while (true) {
//                try {
//                    //Do Nothing
//                } catch (e: IOException) {
//                    this@BluetoothService.stop()
//                    break
//                }
//            }
//        }
//
//        /**
//         * Write to the connected OutStream.
//         *
//         * @param buffer The bytes to write
//         */
//        fun write(buffer: ByteArray?) {
//            try {
//                mmOutStream?.write(buffer)
//            } catch (e: IOException) {
//                //Do Nothing
//            }
//        }
//
//        fun cancel() {
//            try {
//                mmSocket.close()
//            } catch (e: IOException) {
//                //Do Nothing
//            }
//        }
//
//        init {
//            mmSocket = socket
//            var tmpIn: InputStream? = null
//            var tmpOut: OutputStream? = null
//
//            // Get the BluetoothSocket input and output streams
//            try {
//                tmpIn = socket.inputStream
//                tmpOut = socket.outputStream
//            } catch (e: IOException) {
//                //Do Nothing
//            }
//            mmInStream = tmpIn
//            mmOutStream = tmpOut
//        }
//    }
//
//    fun write(out: ByteArray?) {
//        // Create temporary object
//        var r: ConnectedThread?
//        // Synchronize a copy of the ConnectedThread
//        synchronized(this) {
//            r = connectedThread
//        }
//        // Perform the write unsynchronized
//        r?.write(out)
//    }
//
//    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (requestCode == REQUEST_ENABLE_BLUETOOTH_PRINT && resultCode == Activity.RESULT_OK) {
//            start()
//        }
//    }
//
//    companion object {
//        const val TAG = "BluetoothService"
//        const val REQUEST_ENABLE_BLUETOOTH_PRINT = 1111
//        val uuid: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
//    }
//}