package com.example.jetpackdemeo.viewmodel

import androidx.lifecycle.*
import com.example.jetpackdemeo.data.User

class UserModel(firstNameReversed: String, lastNameReversed: String) : ViewModel() {
    private val user = MutableLiveData<User>()

    init {
        user.value = User(firstNameReversed, lastNameReversed)
    }

    val userName: LiveData<String> = Transformations.map(user) {
        "${user.value?.firstName} ${user.value?.lastName}"
    }

    class UserModelFactory(
        private val firstNameReversed: String,
        private val lastNameReversed: String
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserModel(firstNameReversed, lastNameReversed) as T
        }
    }
}