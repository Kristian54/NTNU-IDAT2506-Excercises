package com.example.myapplication

import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.Socket
import kotlin.printStackTrace
import kotlin.text.clear
import kotlin.toString

class Client(private val textViewReceived: TextView, private val textViewSent: TextView, private val sendButton: Button, private val textField: EditText, private val SERVER_IP: String = "10.0.2.2", private val SERVER_PORT: Int = 12345) {
    private var uiReceived: String? = ""
        set(str) {
            MainScope().launch { textViewReceived.text = str }
            field = str
        }

    private var uiSent: String? = ""
        set(str) {
            MainScope().launch { textViewSent.text = str }
            field = str
        }

    private var socket: Socket? = null

    fun start() {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("Client", "Connecting to server...")
            try {
                socket = Socket(SERVER_IP, SERVER_PORT)
                Log.d("Client", "Connected to server: $socket")
                MainScope().launch {
                    sendButton.setOnClickListener {
                        val message = textField.text.toString()
                        if (message.isNotEmpty()) {
                            CoroutineScope(Dispatchers.IO).launch {
                                sendToServer(socket!!, message)
                            }
                            textField.text.clear()
                        }
                    }
                }
                delay(1000)
                readFromServer(socket!!)
            } catch (e: IOException) {
                e.printStackTrace()
                uiReceived = e.message
            }
        }
    }

    private fun readFromServer(socket: Socket) {
        val reader = socket.getInputStream().bufferedReader()
        var message = reader.readLine()
        while (reader.readLine().also { message = it } != null) {
            uiReceived = message
        }
    }

    private fun sendToServer(socket: Socket, message: String) {
        val writer = socket.getOutputStream().bufferedWriter()
        writer.write(message)
        writer.newLine()
        writer.flush()
        uiSent = message
    }
}