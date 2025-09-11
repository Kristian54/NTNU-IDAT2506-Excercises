package com.example.myapplication

class Book {
    private var title: String = ""
    private var description: String = ""

    constructor(title: String, description: String) {
        this.title = title
        this.description = description
    }

    fun getTitle(): String {
        return title
    }

    fun getDescription(): String {
        return description
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun setDescription(description: String) {
        this.description = description
    }
}