package com.example.visprog_assignment5.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.visprog_assignment5.model.CourseData
import com.example.visprog_assignment5.model.StudentData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class Soal2ViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(StudentData())
    val uiState: StateFlow<StudentData> = _uiState.asStateFlow()

//    private val _courseList = mutableStateListOf<CourseData>()
//    val courseList: List<CourseData> = _courseList
//    var ipk by mutableStateOf(0.0)
//        private set
//    var totalSKS by mutableStateOf(0)
//        private set
//    this method was used before making a 2nd model

    fun addCourse(name: String, sks: Int, score: Double){
        val newCourseData = CourseData(name, sks, score)
        val newCourseList = _uiState.value.courseList.toMutableList()
        newCourseList.add(newCourseData)

        _uiState.update { currentState->
            currentState.copy(
                courseList = newCourseList,
                totalSKS = _uiState.value.totalSKS + sks,
                ipk = calculateIPK(newCourseList)
            )
        }
    }

    fun deleteCourse(course: CourseData){
        val newCourseList = _uiState.value.courseList.toMutableList()
        newCourseList.remove(course)

        _uiState.update { currentState->
            currentState.copy(
                courseList = newCourseList,
                totalSKS = _uiState.value.totalSKS - course.sks,
                ipk = calculateIPK(newCourseList)
            )
        }
    }

//    asks for parameter of courseList, because calling it when updating would make it use data
//    prior to the update
    private fun calculateIPK(courseList:List<CourseData>): Double{
//    after deleting a course to become empty, will display NaN without this
        if(courseList.isEmpty())return 0.0

//        make a list of the weight; = sks * score
        val weightList = mutableListOf<Double>()

        for(course in courseList){
            val courseWeight:Double = course.sks * course.score
            weightList.add(courseWeight)
        }

        val totalSKS = courseList.sumOf { it.sks }
        val totalWeight = weightList.sumOf { it }

        return (totalWeight/totalSKS)
    }

    fun scoreValidityChecker(score: String): Boolean {
        val regex = "^[0-9.]+$".toRegex()
        return regex.matches(score)
    }
}