package com.example.visprog_assignment5.model

data class StudentData(
    val courseList: List<CourseData> = mutableListOf(),
    val totalSKS: Int = 0,
    val ipk: Double = 0.0
)