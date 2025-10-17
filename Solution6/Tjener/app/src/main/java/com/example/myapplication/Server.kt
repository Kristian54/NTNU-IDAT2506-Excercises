package com.example.myapplication

import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.printStackTrace
import kotlin.toString

class Server(private val textViewSent: TextView, private val textViewReceived: TextView, private val sendButton: Button, private val textField: EditText, private val PORT: Int = 12345) {
    private var uiSent: String? = ""
        set(str) {
            MainScope().launch { textViewSent.text = str }
            field = str
        }

    private var uiReceived: String? = ""
        set(str) {
            MainScope().launch { textViewReceived.text = str }
            field = str
        }

    private val clients = CopyOnWriteArrayList<Socket>()


    fun start() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                ServerSocket(PORT).use { serverSocket ->
                    while (true) {
                        val clientSocket = serverSocket.accept()
                        clients.add(clientSocket)
                        CoroutineScope(Dispatchers.IO).launch {
                            readFromClient(clientSocket)
                        }
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                uiSent = e.message
            }
        }

        sendButton.setOnClickListener {
            val message = textField.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                for (client in clients) {
                    sendToClient(client, message)
                }
            }
        }
    }

    private fun readFromClient(socket: Socket) {
        val reader = BufferedReader(InputStreamReader(socket.inputStream))
        var message: String?
        while (reader.readLine().also { message = it } != null) {
            uiReceived = message
        }
    }

    private fun sendToClient(socket: Socket, message: String) {
        try {
            val writer = socket.getOutputStream().bufferedWriter()
            writer.write(message)
            writer.newLine()
            writer.flush()
            uiSent = message
        } catch (e: IOException) {
            clients.remove(socket)
            try { socket.close() } catch (_: IOException) {}
            Log.e("Server", "Failed to send to client, removed from list: ${e.message}")
        }
    }
}