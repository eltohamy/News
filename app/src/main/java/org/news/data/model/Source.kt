package org.news.data.model

import android.os.Parcel
import android.os.Parcelable

class Source(
    val id: String,
    val name: String
) : Parcelable {

    constructor(source: Parcel) : this(
        source.readString()!!,
        source.readString()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(name)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Source> = object : Parcelable.Creator<Source> {
            override fun createFromParcel(source: Parcel): Source = Source(source)
            override fun newArray(size: Int): Array<Source?> = arrayOfNulls(size)
        }
    }
}