//package com.example.structure.ui.old.bluetooth
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.View
//import androidx.fragment.app.Fragment
//
///**
// * 블루투스 관련 클래스
// * old code
// */
//open class BluetoothFragment : Fragment(),
//    BluetoothListener {
//    val bluetoothService : BluetoothService =
//        BluetoothService(this)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onStart() {
//        super.onStart()
//        bluetoothService.start()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        bluetoothService.stop()
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        bluetoothService.onActivityResult(requestCode, resultCode, data)
//    }
//
//    override fun setUpFailed(message: String) {
//        //Do Nothing
//    }
//}
