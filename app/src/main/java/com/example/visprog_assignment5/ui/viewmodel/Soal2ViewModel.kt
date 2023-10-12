package com.example.visprog_assignment5.ui.viewmodel

import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.example.visprog_assignment5.ui.model.CourseData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class Soal2ViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(CourseData())
    val uiState: StateFlow<CourseData> = _uiState.asStateFlow()

    var courseList = mutableListOf<CourseData>()
    var totalSKS = 0
    var IPK = 0.0

    fun addCourse(course: CourseData){
        courseList.add(course)
        totalSKS += course.sks
        IPK = calculateIPK()
    }

    fun deleteCourse(course: CourseData){
        courseList.remove(course)
        totalSKS -= course.sks
        IPK = calculateIPK()
    }

    private fun calculateIPK(): Double{
        val IPK: Double
        val weightList = mutableListOf<Double>()
        
        for (course in courseList){
            val courseWeight:Double = course.sks * course.score
            weightList.add(courseWeight)
        }

        val totalSKS = courseList.sumOf { it.sks }
        val totalWeight = weightList.sumOf { it }

        return (totalWeight/totalSKS)
    }
}