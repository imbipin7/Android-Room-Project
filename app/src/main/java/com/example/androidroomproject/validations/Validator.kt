package com.example.androidroomproject.validations

interface Validator {
    fun isValid(): Boolean
    fun message(): String?
}