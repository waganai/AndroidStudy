package com.example.jetpackdemeo.viewmodel

import androidx.lifecycle.*
import com.example.jetpackdemeo.data.User
import com.example.jetpackdemeo.repository.Respository

class UserModel(
    firstNameReversed: String,
    lastNameReversed: String,
    idReversed: Long
) : ViewModel() {
    private val userIdLiveData = MutableLiveData<String>()
    val user :LiveData<User> = Transformations.switchMap(userIdLiveData){
        Respository.getUser(it)
    }

    init {
        userIdLiveData.value = idReversed.toString()
    }

    val userName: LiveData<String> = Transformations.map(user) {
        "${user.value?.firstName} ${user.value?.lastName}"
    }

    fun getUser(userId: String) {
        userIdLiveData.value = userId
    }

    class UserModelFactory(
        private val firstNameReversed: String,
        private val lastNameReversed: String,
        private val idReversed: Long
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserModel(firstNameReversed, lastNameReversed, idReversed) as T
        }
    }
}