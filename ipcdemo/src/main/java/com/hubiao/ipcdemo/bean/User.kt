package com.hubiao.ipcdemo.bean

import android.os.Parcel
import android.os.Parcelable

class User() : Parcelable {

    var mName: String? = null
    var mAge: Int = 0

    constructor(parcel: Parcel) : this() {
        mName = parcel.readString()
        mAge = parcel.readInt()
    }

    constructor(name: String, age: Int) : this() {
        mName = name
        mAge = age
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(mName)
        parcel.writeInt(mAge)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "mName = $mName, mAge = $mAge"
    }
}