package com.example.myapplication

import java.io.Serializable

data class Book(val title: String, val description: String, val imageName: String) : Serializable {
}